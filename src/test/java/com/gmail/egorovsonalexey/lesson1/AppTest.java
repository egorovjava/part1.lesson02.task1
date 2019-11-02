package com.gmail.egorovsonalexey.lesson1;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    @Test
    public void MyLinkedListTest() {
        LinkedListTest.MyLinkedListTest();
    }

    @Test
    public void MyHashMapTest() {
        HashMapTest.MyHashMapTest();
    }

    @Test(expected = IllegalArgumentException.class)
    public void MyHashMapPutTest() {
        HashMapTest.MyHashMapPutTest();
    }

    @Test(expected = IllegalArgumentException.class)
    public void MyHashMapSetTest() {
        HashMapTest.MyHashMapSetTest();
    }

    @Test(expected = IllegalArgumentException.class)
    public void MyHashMapDeleteTest() {
       HashMapTest.MyHashMapDeleteTest();
    }

    @Test(expected = IllegalArgumentException.class)
    public void equalsHashTest() {
        HashMapTest.equalsHashTest();
    }

    @Test
    public void equalsHashTest1() {
        HashMapTest.equalsHashTest1();
    }

    @Test
    public void MyTreeHashMapTest() {
        TreeHashMapTest.MyTreeHashMapTest();
    }

    @Test(expected = IllegalArgumentException.class)
    public void MyTreeHashMapPutTest() {
        TreeHashMapTest.MyTreeHashMapPutTest();
    }

    @Test(expected = IllegalArgumentException.class)
    public void MyTreeHashMapSetTest() {
        TreeHashMapTest.MyTreeHashMapSetTest();
    }

    @Test(expected = IllegalArgumentException.class)
    public void MyTreeHashMapDeleteTest() {
        TreeHashMapTest.MyTreeHashMapDeleteTest();
    }

    @Test(expected = IllegalArgumentException.class)
    public void equalsHasForTreeMapTest() {
        TreeHashMapTest.equalsHashTest();
    }

    @Test
    public void equalsHashForTreeMapTest1() {
        TreeHashMapTest.equalsHashTest1();
    }
}
