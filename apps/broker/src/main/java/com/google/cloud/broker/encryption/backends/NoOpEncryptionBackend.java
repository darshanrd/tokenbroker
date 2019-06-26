package com.google.cloud.broker.encryption.backends;

/** Doesn't use encryption */
public class NoOpEncryptionBackend extends AbstractEncryptionBackend {
    @Override
    public byte[] decrypt(String cryptoKey, byte[] cipherText) {
        return cipherText;
    }

    @Override
    public byte[] encrypt(String cryptoKey, byte[] plainText) {
        return plainText;
    }
}
