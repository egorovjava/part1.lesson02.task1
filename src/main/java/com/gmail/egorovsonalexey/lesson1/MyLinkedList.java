package com.gmail.egorovsonalexey.lesson1;

class MyLinkedList {

    private MyLinkedListItem tail;

    void append(Object k, Object v) {
        MyLinkedListItem item = new MyLinkedListItem(k, v);
        if(tail != null) {
            tail.next = item;
            item.previous = tail;
        }
        tail = item;
    }

    boolean remove(Object o) {
        MyLinkedListItem searched = tail;
        while(searched != null && !searched.key.equals(o)) {
            searched = searched.previous;
        }

        if(searched != null) {

            if(searched.previous != null) {
                searched.previous.next = searched.next;
            }

            if(searched.next != null) {
                searched.next.previous = searched.previous;
            }
            else {
                tail = searched.previous;
            }
            return true;
        }
        else {
            return false;
        }
    }

    MyLinkedListItem search(Object o) {
        MyLinkedListItem searched = tail;
        while(searched != null && !searched.key.equals(o)) {
            searched = searched.previous;
        }

        return searched;
    }

    void push(Object key) {
        MyLinkedListItem item = new MyLinkedListItem(key, null);
        if(tail != null) {
            tail.next = item;
        }
        item.previous = tail;
        tail = item;
    }

    Object pop() {
        if(tail != null) {
            Object ret = tail.key;
            if(tail.previous != null) {
                tail.previous.next = null;
            }
            tail = tail.previous;
            return ret;
        }
        else {
            return null;
        }
    }

    boolean isEmpty() {
        return tail == null;
    }

    class MyLinkedListItem {

        private MyLinkedListItem next;
        private MyLinkedListItem previous;
        private Object value;
        private Object key;

        MyLinkedListItem(Object k, Object v) {
            key = k;
            value = v;
        }

        Object getValue() {
            return value;
        }

        void setValue(Object o) {
            value = o;
        }
    }
}
