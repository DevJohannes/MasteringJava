package de.appiqstudios.cachesystem.cache.builtin;

import de.appiqstudios.cachesystem.cache.IStorage;

import java.util.concurrent.ConcurrentHashMap;

public class Storage implements IStorage {

    private final ConcurrentHashMap<String, String> storage = new ConcurrentHashMap<>();

    @Override
    public void set(String key, String value) {
        storage.put(key, value);
    }

    @Override
    public String get(String key) {
        return storage.get(key);
    }

    @Override
    public void delete(String key) {
        storage.remove(key);
    }

    @Override
    public boolean exists(String key) {
        return storage.containsKey(key);
    }

    @Override
    public int size() {
        return storage.size();
    }
}
