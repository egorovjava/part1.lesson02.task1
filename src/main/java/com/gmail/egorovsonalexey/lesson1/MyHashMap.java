package com.gmail.egorovsonalexey.lesson1;

class MyHashMap implements HashMap {

    private MyLinkedList[] data;
    private int count = 0;

    MyHashMap() {
        int dataSize = (int)Math.pow(2, 16);
        data = new MyLinkedList[dataSize];
    }

    public void put(Object key, Object value) {

        if(key == null) {
            throw new IllegalArgumentException("Key mast be not null.");
        }

        int hash = key.hashCode();
        int index = hash >>> 16;
        if(data[index] == null) {
            data[index] = new MyLinkedList();
        }
        MyLinkedList bucket = data[index];
        MyLinkedList.MyLinkedListItem item = bucket.search(key);
        if(item == null) {
            bucket.append(key, value);
            count++;
        }
        else {
            throw new IllegalArgumentException("The key " + key + " already exists.");
        }
    }

    public Object get(Object key) {

        if(key == null) {
            throw new IllegalArgumentException("Key mast be not null.");
        }

        MyLinkedList bucket = getBucket(key);
        if(bucket != null) {
            MyLinkedList.MyLinkedListItem item = bucket.search(key);
            if (item != null) {
                return item.getValue();
            }
        }
        return null;
    }

    public void set(Object key, Object value) {

        if(key == null) {
            throw new IllegalArgumentException("Key mast be not null.");
        }

        MyLinkedList bucket = getBucket(key);
        if(bucket != null) {
            MyLinkedList.MyLinkedListItem item = bucket.search(key);
            if(item != null) {
                item.setValue(value);
                return;
            }
        }
        throw new IllegalArgumentException("The key " + key + " not found.");
    }

    public void delete(Object key) {

        if(key == null) {
            throw new IllegalArgumentException("Key mast be not null.");
        }

        MyLinkedList bucket = getBucket(key);
        if(bucket != null) {
            boolean res = bucket.remove(key);
            if(res) {
                count--;
                return;
            }
        }
        throw new IllegalArgumentException("The key " + key + " not found.");
    }

    public boolean containsKey(Object key) {

        if(key == null) {
            throw new IllegalArgumentException("Key mast be not null.");
        }

        MyLinkedList bucket = getBucket(key);
        if(bucket != null) {
            MyLinkedList.MyLinkedListItem item = bucket.search(key);
            if(item != null) {
                return true;
            }
        }
        return false;
    }

    public int getCount() {
        return count;
    }

    private MyLinkedList getBucket(Object key) {

        if(key == null) {
            throw new IllegalArgumentException("Key mast be not null.");
        }

        int hash = key.hashCode();
        int index = hash >>> 16;
        return data[index];
    }
}

