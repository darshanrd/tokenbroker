# Demo Environment Setup

[Enable GCS Audit Logs](https://console.cloud.google.com/iam-admin/audit)

```sh
BUCKET=mybucket
PROJECT=myproject
KEYRING=mykeyring
KEYNAME=mykey
CLUSTER=mycluster
REPO_NAME=csr-repo

KEY_LOC=global
KEYID=projects/$PROJECT/locations/$KEY_LOC/keyRings/$KEYRING/cryptoKeys/$KEYNAME

gcloud config set project $PROJECT

gsutil mb -p $PROJECT gs://$BUCKET
gsutil mb -p $PROJECT gs://$BUCKET-alice
gsutil mb -p $PROJECT gs://$BUCKET-bob

gcloud iam service-accounts create "bastion"
gcloud iam service-accounts create "sv-nat"
gcloud iam service-accounts create "dataproc"

gcloud projects add-iam-policy-binding "$PROJECT" --member="serviceAccount:dataproc@$PROJECT.iam.gserviceaccount.com" --role='roles/dataproc.worker'
gcloud kms keys add-iam-policy-binding "$KEYNAME" --location "$KEY_LOC" --keyring "$KEYRING" --member "serviceAccount:dataproc@$PROJECT.iam.gserviceaccount.com" --role "roles/cloudkms.cryptoKeyDecrypter"

echo 'changeit' > password.txt

cat << EOF > config.yaml
root_principal_password_uri: gs://$BUCKET/password.enc
kms_key_uri: $KEYID
kdc_db_key_uri: gs://$BUCKET/password.enc
tgt_lifetime_hours: 8
EOF

gcloud kms keyrings create $KEYRING location $KEY_LOC

gcloud kms keys create $KEYNAME \
    --location $KEY_LOC \
    --keyring $KEYRING \
    --purpose encryption

gcloud kms encrypt \
    --plaintext-file=password.txt \
    --ciphertext-file=password.enc \
    --key=$KEYID

gsutil cp password.enc gs://$BUCKET/

gcloud beta dataproc clusters create $CLUSTER \
    --kerberos-config-file=config.yaml \
    --image-version=1.3 \
    --bucket $BUCKET \
    --region us-east1 \
    --subnet default \
    --no-address \
    --zone us-east1-b \
    --single-node \
    --master-machine-type n1-standard-4 \
    --master-boot-disk-type pd-ssd \
    --master-boot-disk-size 100 \
    --scopes 'https://www.googleapis.com/auth/cloud-platform' \
    --tags internal \
    --service-account "dataproc@$PROJECT.iam.gserviceaccount.com"

gcloud compute instances create bastion \
	--zone=us-east1-b \
	--machine-type=f1-micro \
	--subnet=default \
	--network-tier=PREMIUM \
	--maintenance-policy=MIGRATE \
	--service-account=bastion@$PROJECT.iam.gserviceaccount.com \
	--scopes=https://www.googleapis.com/auth/cloud-platform \
	--tags=bastion \
	--image-family=debian-9 \
	--image-project=debian-cloud \
	--boot-disk-size=10GB \
	--boot-disk-type=pd-standard \
	--boot-disk-device-name=bastion

gcloud compute instances create nat \
	--zone=us-east1-b \
	--machine-type=f1-micro \
	--subnet=default \
	--network-tier=PREMIUM \
	--can-ip-forward \
	--maintenance-policy=MIGRATE \
	--service-account=sv-nat@$PROJECT.iam.gserviceaccount.com \
	--scopes=https://www.googleapis.com/auth/cloud-platform \
	--tags=nat \
	--image-family=debian-9 \
	--image-project=debian-cloud \
	--boot-disk-size=10GB \
	--boot-disk-type=pd-standard \
	--boot-disk-device-name=nat

gcloud compute routes create nat-route \
    --network default \
    --destination-range 0.0.0.0/0 \
    --next-hop-instance nat \
    --next-hop-instance-zone us-east1-b \
    --tags internal \
    --priority 800

cat << EOF >> policy.yaml
auditConfigs:
- auditLogConfigs:
  - logType: DATA_WRITE
  - logType: DATA_READ
  service: storage.googleapis.com
EOF
gcloud projects get-iam-policy $PROJECT >> policy.yaml
grep -q 'storage.googleapis.com'; e=$?; [ $e -ne 0 ] && gcloud projects set-iam-policy $PROJECT policy.yaml

# Configure SSH
BASTION_IP=$(gcloud compute instances describe bastion --format='get(networkInterfaces[0].accessConfigs[0].natIP)')
DATAPROC_IP=$(gcloud compute instances describe $CLUSTER-m --format='get(networkInterfaces[0].networkIP)')
NAT_IP=$(gcloud compute instances describe nat --format='get(networkInterfaces[0].networkIP)')

cat << EOF >> ~/.ssh/config
Host bastion
  Hostname $BASTION_IP

Host dataproc
  Hostname $DATAPROC_IP
  StrictHostKeyChecking no
  ProxyCommand ssh bastion -W %h:%p

Host nat
  Hostname $NAT_IP
  StrictHostKeyChecking no
  ProxyCommand ssh bastion -W %h:%p

EOF


# Create and upload service account keyfiles

gcloud iam service-accounts create sv-alice
gcloud iam service-accounts create sv-bob
gcloud iam service-accounts create sv-eve
gcloud iam service-accounts keys create alice.json --iam-account=sv-alice@$PROJECT.iam.gserviceaccount.com
gcloud iam service-accounts keys create bob.json --iam-account=sv-bob@$PROJECT.iam.gserviceaccount.com
gcloud iam service-accounts keys create eve.json --iam-account=sv-eve@$PROJECT.iam.gserviceaccount.com

gcloud iam roles create gcsro --project $PROJECT \
  --title "gcsro" --description "list and get" \
  --permissions storage.objects.get,storage.objects.list,storage.buckets.list,storage.buckets.get \
  --stage ALPHA

gsutil iam ch serviceAccount:sv-alice@$PROJECT.iam.gserviceaccount.com:admin gs://$BUCKET-alice
gsutil iam ch serviceAccount:sv-bob@$PROJECT.iam.gserviceaccount.com:admin gs://$BUCKET-bob
gsutil iam ch serviceAccount:sv-eve@$PROJECT.iam.gserviceaccount.com:projects/$PROJECT/roles/gcsro gs://$BUCKET-alice
gsutil iam ch serviceAccount:sv-eve@$PROJECT.iam.gserviceaccount.com:projects/$PROJECT/roles/gcsro gs://$BUCKET-bob

scp -p *.json dataproc:

# Configure nat
ssh nat << EOF
sudo sysctl -w net.ipv4.ip_forward=1
sudo iptables -t nat -A POSTROUTING -o eth0 -j MASQUERADE
sudo echo 'net.ipv4.ip_forward=1' > /etc/sysctl.d/20-natgw.conf
sudo DEBIAN_FRONTEND=noninteractive apt install -y iptables-persistent
EOF


# Configure dataproc
ssh dataproc << EOF
sudo apt install -y maven openjdk-8-jdk

sudo su - broker -c 'gcloud source repos clone $REPO_NAME --project=$PROJECT; cd $REPO_NAME; mvn package; cd /home/broker/$REPO_NAME/apps/broker; ./start-broker.sh'

if [ -d "/usr/local/share/google/dataproc/lib" ]; then
	sudo rm -v /usr/local/share/google/dataproc/lib/gcs-connector*
	sudo gsutil cp "gs://gcp-token-broker/gcs-connector-hadoop2-2.0.0-SNAPSHOT-shaded.jar" "/usr/local/share/google/dataproc/lib/"
	sudo cp -v "/home/broker/$REPO_NAME/connector/target/broker-connector-hadoop2-0.2.0.jar" "/usr/local/share/google/dataproc/lib/"
	sudo ln -s /usr/lib/hadoop/lib/gcs-connector-hadoop2-2.0.0-SNAPSHOT-shaded.jar /usr/local/share/google/dataproc/lib/gcs-connector.jar
else
	sudo rm -v /usr/lib/hadoop/lib/gcs-connector*
	sudo gsutil cp "gs://gcp-token-broker/gcs-connector-hadoop2-2.0.0-SNAPSHOT-shaded.jar" "/usr/lib/hadoop/lib/"
	sudo cp -v "/home/broker/$REPO_NAME/connector/target/broker-connector-hadoop2-0.2.0.jar" "/usr/lib/hadoop/lib/"
	sudo ln -s /usr/lib/hadoop/lib/gcs-connector-hadoop2-2.0.0-SNAPSHOT-shaded.jar /usr/lib/hadoop/lib/gcs-connector.jar
fi

sudo bdconfig set_property --configuration_file "/etc/hadoop/conf/core-site.xml" --name "fs.gs.system.bucket" --value "" --create_if_absent --clobber
sudo bdconfig set_property --configuration_file "/etc/hadoop/conf/core-site.xml" --name "fs.gs.delegation.token.binding" --value "com.google.cloud.broker.hadoop.fs.BrokerDelegationTokenBinding" --create_if_absent --clobber
sudo bdconfig set_property --configuration_file "/etc/hadoop/conf/core-site.xml" --name "gcp.token.broker.uri.hostname" --value "localhost" --create_if_absent --clobber
sudo bdconfig set_property --configuration_file "/etc/hadoop/conf/core-site.xml" --name "gcp.token.broker.uri.port" --value "5000" --create_if_absent --clobber
sudo bdconfig set_property --configuration_file "/etc/hadoop/conf/core-site.xml" --name "gcp.token.broker.tls.enabled" --value "false" --create_if_absent --clobber
sudo bdconfig set_property --configuration_file "/etc/hadoop/conf/core-site.xml" --name "gcp.token.broker.spnego.enabled" --value "false" --create_if_absent --clobber

sudo useradd -s /bin/bash -U -m alice
sudo useradd -s /bin/bash -U -m bob
sudo useradd -s /bin/bash -U -m eve
sudo useradd -s /bin/bash -U -m broker

sudo kadmin.local -q "addprinc -pw changeit alice"
sudo kadmin.local -q "addprinc -pw changeit bob"
sudo kadmin.local -q "addprinc -pw changeit eve"

sudo kadmin.local -q "addprinc -randkey broker/localhost"
sudo kadmin.local -q "ktadd -k /etc/security/keytab/broker.service.keytab broker/localhost"
sudo ls -ldh /etc/security/keytab/broker.service.keytab

sudo cp -v *.json /home/broker/$REPO_NAME/apps/broker/
sudo chown broker:broker /home/broker/$REPO_NAME/apps/broker/*.json
sudo su - broker -c 'mkdir -p /home/broker/$REPO_NAME/apps/broker/keytabs; ln -s /etc/security/keytab/broker.service.keytab /home/broker/$REPO_NAME/apps/broker/keytabs/broker.service.keytab'

sudo su - alice -c 'echo changeit | kinit -S broker/localhost; klist'

sudo systemctl restart hadoop-hdfs-namenode
sudo systemctl restart hadoop-yarn-resourcemanager
sudo systemctl restart hive-server2
sudo systemctl restart hive-metastore
sudo systemctl restart hadoop-yarn-timelineserver
sudo systemctl restart hadoop-mapreduce-historyserver
sudo systemctl restart spark-history-server
sudo systemctl restart hadoop-hdfs-secondarynamenode
EOF
```


### Dev process for connector

```sh
sudo su - broker -c 'cd /home/broker/$REPO_NAME/connector;git pull;mvn package'
sudo rm -v /usr/lib/hadoop/lib/broker-connector-hadoop2-0.2.0.jar
sudo cp -v "/home/broker/$REPO_NAME/connector/target/broker-connector-hadoop2-0.2.0.jar" "/usr/lib/hadoop/lib/"
```

### Dev process for broker

```sh
sudo su - broker -c <<EOF
cd /home/broker/$REPO_NAME/apps/broker
pkill -u broker
git pull
mvn package
./start-broker.sh
tail -8f nohup.out
EOF
```


### Test commands as alice

```sh
sudo su - alice -c <<EOF
echo changeit | kinit
export HADOOP_ROOT_LOGGER=DEBUG,console
hadoop fs -ls gs://$BUCKET-alice/
echo "123" > alicefile
hadoop fs -put -f alicefile gs://$BUCKET-alice/
hadoop fs -ls gs://$BUCKET-alice/
hadoop fs -ls gs://$BUCKET-bob/
hadoop fs -put -f alicefile gs://$BUCKET-bob/
EOF
```

### Test commands as bob

```sh
sudo su - bob -c <<EOF
echo changeit | kinit
hadoop fs -ls gs://$BUCKET-bob/
echo "123" > bobfile
hadoop fs -put -f bobfile gs://$BUCKET-bob/
hadoop fs -ls gs://$BUCKET-bob/
hadoop fs -ls gs://$BUCKET-alice/
hadoop fs -put -f bobfile gs://$BUCKET-alice/
EOF
```

### Test commands as eve

```sh
sudo su - alice -c <<EOF
echo changeit | kinit
hadoop fs -ls gs://$BUCKET-alice
hadoop fs -ls gs://$BUCKET-bob
echo "123" > evefile
hadoop fs -put -f evefile gs://$BUCKET-alice/
hadoop fs -put -f evefile gs://$BUCKET-bob/
EOF
```