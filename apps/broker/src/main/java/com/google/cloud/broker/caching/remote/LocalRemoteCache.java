package com.google.cloud.broker.caching.remote;

import com.google.common.base.Supplier;
import com.google.common.cache.*;
import com.google.protobuf.ByteString;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/** Not actually Remote - for testing only
 *
 */
public class LocalRemoteCache extends AbstractRemoteCache {
    private LoadingCache<String, ByteString> cache = CacheBuilder.newBuilder()
            .build(CacheLoader.from(new Supplier<ByteString>() {
        @Override
        public ByteString get() {
            return null;
        }
    }));

    @Override
    public byte[] get(String key) {
        try {
            ByteString byteString = cache.get(key);
            if (byteString != null) {
                return byteString.toByteArray();
            } else return null;
        } catch (ExecutionException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void set(String key, byte[] value) {
        ByteString byteString = ByteString.copyFrom(value);
        cache.put(key, byteString);
    }

    @Override
    public void set(String key, byte[] value, int expireIn) {
        set(key, value);
    }

    @Override
    public void delete(String key) {
        cache.invalidate(key);
    }

    public static class NoOpLock implements Lock {

        @Override
        public void lock() {

        }

        @Override
        public void lockInterruptibly() throws InterruptedException {

        }

        @Override
        public boolean tryLock() {
            return true;
        }

        @Override
        public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
            return true;
        }

        @Override
        public void unlock() {

        }

        @Override
        public Condition newCondition() {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public Lock acquireLock(String lockName) {
        return new NoOpLock();
    }
}
