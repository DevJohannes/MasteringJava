package de.appiqstudios.cachesystem.cache;

public interface IStorage {
    void set(String key, String value);
    String get(String key);
    void delete(String key);
    boolean exists(String key);
    int size();
}
