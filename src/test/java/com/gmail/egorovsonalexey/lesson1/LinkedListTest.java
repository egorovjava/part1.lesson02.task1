package com.gmail.egorovsonalexey.lesson1;

import com.gmail.egorovsonalexey.lesson1.MyLinkedList;
import org.junit.Assert;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

class LinkedListTest {

    static void MyLinkedListTest() {
        MyLinkedList myList = new MyLinkedList();
        for(Integer i = 0; i < 10; i++) {
            myList.append(i, 100 + i);
        }

        MyLinkedList.MyLinkedListItem item = myList.search(5);
        assert (Integer)item.getValue() == 105;

        myList.remove(5);
        item = myList.search(5);
        assertNull(item);

        myList.remove(0);
        item = myList.search(0);
        assertNull(item);

        boolean res = myList.remove(9);
        assertTrue(res);
        item = myList.search(9);
        assertNull(item);

        res = myList.remove(9);
        Assert.assertFalse(res);
    }
}
