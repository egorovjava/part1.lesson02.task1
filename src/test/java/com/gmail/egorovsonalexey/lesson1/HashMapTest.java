package com.gmail.egorovsonalexey.lesson1;

import org.junit.Assert;
import org.junit.Test;

public class HashMapTest {

    @Test
    public void myHashMapTest() {
        MyHashMap map = new MyHashMap();

        assert map.get(1) == null;
        assert !map.containsKey(1);

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

    @Test(expected = IllegalArgumentException.class)
    public void myHashMapPutTest() {
        MyHashMap map = new MyHashMap();
        for(int i = 0; i < 10; i++) {
            map.put(i, i + 100);
        }

        map.put(5, 205);
    }

    @Test(expected = IllegalArgumentException.class)
    public void myHashMapSetTest() {
        MyHashMap map = new MyHashMap();
        for(int i = 0; i < 10; i++) {
            map.put(i, i + 100);
        }

        map.set(10, 210);
    }

    @Test(expected = IllegalArgumentException.class)
    public void myHashMapDeleteTest() {
        MyHashMap map = new MyHashMap();
        for(int i = 0; i < 10; i++) {
            map.put(i, i + 100);
        }

        map.delete(10);
    }

    @Test
    public void myHashMapGetTest() {
        MyHashMap map = new MyHashMap();
        SimpleHashClass key1 = new SimpleHashClass();
        SimpleHashClass key2 = new SimpleHashClass();

        map.put(key1, 1);

        assert map.get(key2) == null;
    }

    @Test(expected = IllegalArgumentException.class)
    public void equalsHashTest() {
        MyHashMap map = new MyHashMap();
        SimpleHashClass key1 = new SimpleHashClass();
        SimpleHashClass key2 = new SimpleHashClass();

        map.put(key1, 1);
        map.put(key2, 2);

        map.delete(key1);
        assert map.getCount() == 1;

        map.delete(key1);
    }

    @Test
    public void equalsHashTest1() {
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
