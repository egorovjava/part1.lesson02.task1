package com.gmail.egorovsonalexey.lesson1;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        // System.out.println( "Hello World!" );

        MyLinkedList myList = new MyLinkedList();

        for(int i = 0; i < 10; i++) {
            myList.append(i, 100 + i);
        }

        for(int i = 0; i < 10; i++) {
            MyLinkedList.MyLinkedListItem item = myList.search(i);
            System.out.print(item.getValue() + " ");
        }

        myList.remove(5);

        myList.remove(0);

        myList.remove(9);

        System.out.println();

        for(int i = 0; i < 10; i++) {
            MyLinkedList.MyLinkedListItem item = myList.search(i);
            if(item != null) {
                System.out.print(item.getValue() + " ");
            }
        }

        MyHashMap hmap = new MyHashMap();
        System.out.println(hmap.containsKey(12));

        for(int i = 0; i < 10; i++) {
            hmap.put(i, i + 100);
        }

        System.out.println();
        MyTreeHashMap map = new MyTreeHashMap();
        map.get(12);

        for(int i = 0; i < 10; i += 2 ) {
            map.put(i, i + 100);
        }

        for(int i = 1; i < 10; i += 2 ) {
            map.put(i, i + 100);
        }

        System.out.println();

        System.out.println(map);
    }
}
