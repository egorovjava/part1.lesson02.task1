package com.gmail.egorovsonalexey.lesson1;

public interface IHashMap {
    void put(Object key, Object value);
    Object get(Object key);
    void set(Object key, Object value);
    void delete(Object key);
    boolean containsKey(Object key);
    int getCount();
}
