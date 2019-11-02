package com.gmail.egorovsonalexey.lesson1;

import org.junit.Assert;

class HashMapTest {

    static void MyHashMapTest() {
        MyHashMap map = new MyHashMap();

        for(int i = 0; i < 10; i++) {
            assert map.getCount() == i;
            map.put(i, i + 100);
        }

        int count = map.getCount();

        Integer val = (Integer)map.get(5);
        assert val == 105;

        map.set(5, 205);
        val = (Integer)map.get(5);
        assert val == 205;

        map.delete(5);
        assert map.getCount() == count - 1;
        boolean res = map.containsKey(5);
        Assert.assertFalse(res);
    }

    static void MyHashMapPutTest() {
        MyHashMap map = new MyHashMap();
        for(int i = 0; i < 10; i++) {
            map.put(i, i + 100);
        }

        map.put(5, 205);
    }

    static void MyHashMapSetTest() {
        MyHashMap map = new MyHashMap();
        for(int i = 0; i < 10; i++) {
            map.put(i, i + 100);
        }

        map.set(10, 210);
    }

    static void MyHashMapDeleteTest() {
        MyHashMap map = new MyHashMap();
        for(int i = 0; i < 10; i++) {
            map.put(i, i + 100);
        }

        map.delete(10);
    }

    static void equalsHashTest() {
        MyHashMap map = new MyHashMap();
        SimpleHashClass key1 = new SimpleHashClass();
        SimpleHashClass key2 = new SimpleHashClass();

        map.put(key1, 1);
        map.put(key2, 2);

        map.delete(key1);
        assert map.getCount() == 1;

        map.delete(key1);
    }

    static void equalsHashTest1() {
        MyHashMap map = new MyHashMap();
        SimpleHashClass key1 = new SimpleHashClass();
        SimpleHashClass key2 = new SimpleHashClass();

        map.put(key1, 1);
        map.put(key2, 2);

        assert (Integer)map.get(key2) == 2;
        assert (Integer)map.get(key1) == 1;

        map.delete(key1);
        assert map.getCount() == 1;

        map.delete(key2);
        assert map.getCount() == 0;
    }
}
