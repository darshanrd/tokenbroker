#!/usr/bin/env bash
# Broker start script
# Edit the variables in this script to match your deployment environment

# When APP_SETTING_ENV is set to "demo", backend implementations that do not require external services will be used.
APP_SETTING_ENV="demo"

REALM=$(cat /etc/krb5.conf | grep default_realm | awk {'print $3'})
HOST=$(cat /etc/krb5.conf | grep 'kdc ='| head -1 | awk {'print $3'})
HIVE_PRINCIPAL="hive/$HOST@$REALM"
YARN_PRINCIPAL="yarn/$HOST@$REALM"

# example for Dataproc cluster 'my-cluster' on 'us-central-1b'
#HIVE_HOST=my-cluster-m.us-central-1b.c.my-project.internal

# GCP settings
export APP_SETTING_GCP_PROJECT=""
export APP_SETTING_GCP_REGION=""

# Domain name
export APP_SETTING_DOMAIN_NAME=""

# Host and port to serve the broker app from
export APP_SETTING_SERVER_HOST="localhost"
export APP_SETTING_SERVER_PORT="5000"

# Path to the broker principals' keytabs
export APP_SETTING_KEYTABS_PATH="keytabs"

# Path to the broker Oauth client ID for user login
export APP_SETTING_CLIENT_SECRET_PATH="client_secret.json"

# Path to the TLS private key
export APP_SETTING_TLS_KEY_PATH="tls.pem"

# Path to the TLS certificate
export APP_SETTING_TLS_CRT_PATH="tls.crt"

# Broker principal's service name
export APP_SETTING_BROKER_SERVICE_NAME="broker"

# Broker principal's host
export APP_SETTING_BROKER_SERVICE_HOSTNAME="localhost"

# Broker's Kerberos realm
export APP_SETTING_BROKER_REALM="BROKER"

# Base level for logging
export APP_SETTING_LOGGING_LEVEL="INFO"

# Comma-separated whitelist of proxy users for impersonation
export APP_SETTING_PROXY_USER_WHITELIST="$HIVE_PRINCIPAL,$YARN_PRINCIPAL"

# Comma-separated whitelist API scopes
#export APP_SETTING_SCOPE_WHITELIST="https:#www.googleapis.com/auth/devstorage.read_write"

# Cache backend
#export APP_SETTING_REMOTE_CACHE="com.google.cloud.broker.caching.remote.RedisCache"

# Redis cache backend settings
#export APP_SETTING_REDIS_CACHE_HOST=""
#export APP_SETTING_REDIS_CACHE_PORT=""
#export APP_SETTING_REDIS_CACHE_DB=""

# Remote cache lifetime for access tokens
export APP_SETTING_ACCESS_TOKEN_REMOTE_CACHE_TIME="60"  # in seconds

# Local cache lifetime for access tokens
export APP_SETTING_ACCESS_TOKEN_LOCAL_CACHE_TIME="30"  # in seconds

# Local cache lifetime for session details
export APP_SETTING_SESSION_LOCAL_CACHE_TIME="30"  # in seconds

# Access token provider backend
#export APP_SETTING_PROVIDER=""

# Encryption backend
#export APP_SETTING_ENCRYPTION_BACKEND=""

# Database backend class
#export APP_SETTING_DATABASE_BACKEND=""

# Life duration for JWT tokens (the shorter the better)
export APP_SETTING_JWT_LIFE="30"  # in seconds

# Project containing the shadow service account
export APP_SETTING_SHADOW_PROJECT=""

# Session maximum lifetime (in milliseconds)
#export APP_SETTING_SESSION_MAXIMUM_LIFETIME=Integer.toString(7 * 24 * 3600 * 1000)  # 7 days

# Session lifetime increment (in milliseconds)
#export APP_SETTING_SESSION_RENEW_PERIOD=Integer.toString(24 * 3600 * 1000)  # 24 hours

# KMS settings
export APP_SETTING_ENCRYPTION_CRYPTO_KEY_RING="broker-key-ring"
export APP_SETTING_ENCRYPTION_REFRESH_TOKEN_CRYPTO_KEY="refresh-token-key"
export APP_SETTING_ENCRYPTION_ACCESS_TOKEN_CACHE_CRYPTO_KEY="access-token-cache-key"
export APP_SETTING_ENCRYPTION_DELEGATION_TOKEN_CRYPTO_KEY="delegation-token-key"

# Configure demo backends
if [ "$APP_SETTING_ENV" == "demo" ]; then
    # Set backends for single-node demo
    export APP_SETTING_ENCRYPTION_BACKEND="com.google.cloud.broker.encryption.backends.NoOpEncryptionBackend"
    export APP_SETTING_DATABASE_BACKEND="com.google.cloud.broker.database.backends.LocalDatabaseBackend"
    export APP_SETTING_REMOTE_CACHE="com.google.cloud.broker.caching.remote.LocalRemoteCache"
    export APP_SETTING_PROVIDER="com.google.cloud.broker.accesstokens.providers.LocalRefreshTokenProvider"

    # Uncomment to disable SPNEGO Authentication
    #export APP_SETTING_AUTHENTICATOR="com.google.cloud.broker.authentication.StaticAuthenticator"
    #export APP_SETTING_DEMO_IMPERSONATOR="yarn/$HOST@$REALM"
fi

nohup java -Xmx1g -Xmx1g -cp target/broker-0.2.0-jar-with-dependencies.jar com.google.cloud.broker.grpc.BrokerServer >broker.log 2>&1 &
