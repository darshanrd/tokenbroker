package com.google.cloud.broker.database.backends;

import com.google.cloud.broker.database.DatabaseObjectNotFound;
import com.google.cloud.broker.database.models.Model;
import com.google.common.base.Supplier;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.ExecutionException;

/** Uses a Guava LoadingCache instead of an external database */
public class LocalDatabaseBackend extends AbstractDatabaseBackend {
    private LoadingCache<String,Object> cache;
    public LocalDatabaseBackend(){
        cache = CacheBuilder.newBuilder()
                .build(CacheLoader.from(new Supplier<Object>() { @Override public Object get() { return null; } }));
    }

    @Override
    public Model get(Class modelClass, String objectId) throws DatabaseObjectNotFound {
        try {
            return (Model) cache.get(objectId);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Model model) {
        cache.put((String) model.getValue("id"), model);
    }

    @Override
    public void delete(Model model) {
        cache.invalidate((String) model.getValue("id"));
    }
}
