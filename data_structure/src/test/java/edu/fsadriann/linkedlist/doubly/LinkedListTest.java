package edu.fsadriann.linkedlist.doubly;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.function.ToIntFunction;
import org.junit.jupiter.api.Test;

import java.util.function.Function;
import java.util.function.Predicate;

import edu.fsadriann.app.linkedlist.doubly.doubly.LinkedList;
import edu.fsadriann.model.iterator.Iterator;

public class LinkedListTest {
    
    @Test
    void testAdd() {
        LinkedList<Integer> list = new LinkedList<>();
        assertTrue(list.add(1));
        assertTrue(list.add(2));
        assertTrue(list.add(3));
        System.out.println(list.toString());
    }

    @Test
    void testAdd2() {
        LinkedList<Integer> list = new LinkedList<>();
        Integer[] array = {1, 2, 3, 4, 5};
        assertTrue(list.add(array));
        System.out.println(list.toString());
    }

    @Test
    void testAdd3() {
        LinkedList<Integer> list = new LinkedList<>();
        LinkedList<Integer> list2 = new LinkedList<>();
        list2.add(1);
        list2.add(2);
        list2.add(3);
        list2.add(4);
        assertTrue(list.add(list2));
        System.out.println(list.toString());
    }

    @Test
    void testAddFirst() {
        LinkedList<Integer> list = new LinkedList<>();
        assertTrue(list.addFirst(1));
        assertTrue(list.addFirst(2));
        assertTrue(list.addFirst(3));
        System.out.println(list.toString());
    }

    @Test
    void testAddFirst2() {
        LinkedList<Integer> list = new LinkedList<>();
        Integer[] array = {1, 2, 3, 4, 5};
        assertTrue(list.addFirst(array));
        System.out.println(list.toString());
    }

    @Test
    void testAddFirst3() {
        LinkedList<Integer> list = new LinkedList<>();
        LinkedList<Integer> list2 = new LinkedList<>();
        list2.add(1);
        list2.add(2);
        list2.add(3);
        list2.add(4);
        assertTrue(list.addFirst(list2));
        System.out.println(list.toString());
    }

    @Test
    void testClear() {
        LinkedList<Integer> list = new LinkedList<>();
        Integer[] array = {1, 2, 3, 4, 5};
        assertTrue(list.addFirst(array));
        System.out.println(list.toString());
        assertTrue(list.clear());
        System.out.println(list.toString());
    }

    @Test
    void testContains() {
        LinkedList<Integer> list = new LinkedList<>();
        Integer[] array = {1, 2, 3, 4, 5};
        assertTrue(list.addFirst(array));
        System.out.println(list.toString());
        assertTrue(list.contains(2));
        list.clear();
        assertFalse(list.contains(2));
    }

    @Test
    void testContains2() {
        LinkedList<Integer> list = new LinkedList<>();
        Integer[] array = {1, 2, 3, 4, 5};
        assertTrue(list.addFirst(array));
        System.out.println(list.toString());
        assertTrue(list.contains(array));
        list.clear();
        assertFalse(list.contains(array));
    }

    @Test
    void testContains3() {
        LinkedList<Integer> list = new LinkedList<>();
        LinkedList<Integer> list2 = new LinkedList<>();
        list2.add(1);
        list2.add(2);
        list2.add(3);
        list2.add(4);
        assertTrue(list.addFirst(list2));
        System.out.println(list.toString());
        assertTrue(list.contains(list2));
        list.clear();
        assertFalse(list.contains(list2));
    }

    @Test
    void testForEach() {
        LinkedList<Integer> list = new LinkedList<>();
        Integer[] array = {1, 2, 3, 4, 5};
        assertTrue(list.addFirst(array));
        Function<Integer, Void> imprimir = elemento -> {
            if (!(elemento == null)) {
                System.out.println(elemento);
            }
            return null;
        };
        list.forEach(imprimir);
    }

    @Test
    void testIsEmpty() {
        LinkedList<Integer> list = new LinkedList<>();
        assertTrue(list.isEmpty());
        System.out.println(list.toString());
    }

    @Test
    void testIterator() {
        LinkedList<Integer> list = new LinkedList<>();
        Integer[] array = {1, 2, 3, 4, 5};
        list.add(array);

        Iterator<Integer> iterator = list.iterator();

        int i = 0;
        while (iterator.hasNext()) {
            assertEquals(array[i], iterator.next());
            i++;
        }

        assertEquals(array.length, i);
    }

    @Test
    void testPeek() {
        LinkedList<Integer> list = new LinkedList<>();
        Integer[] array = {1, 2, 3, 4, 5};
        assertTrue(list.add(array));
        assertEquals(1, list.peek());
    }

    @Test
    void testPeekArray() {
        LinkedList<Integer> list = new LinkedList<>();
        Integer[] array = {1, 2, 3, 4, 5};
        assertTrue(list.add(array));
        assertArrayEquals(array, list.peekArray(array.length));
    }

    @Test
    void testPeekCollection() {
        LinkedList<Integer> list = new LinkedList<>();
        LinkedList<Integer> list2 = new LinkedList<>();
        list2.add(1);
        list2.add(2);
        list2.add(3);
        list2.add(4);
        assertTrue(list.add(list2));
        assertArrayEquals(list2.toArray(), list.peekCollection(list2.size()).toArray());
    }

    @Test
    void testPeekLast() {
        LinkedList<Integer> list = new LinkedList<>();
        Integer[] array = {1, 2, 3, 4, 5};
        assertTrue(list.add(array));
        assertEquals(5, list.peekLast());
    }

    @Test
    void testPeekLastArray() {
        LinkedList<Integer> list = new LinkedList<>();
        Integer[] array = {1, 2, 3, 4, 5};
        assertTrue(list.add(array));
        assertArrayEquals(array, list.peekLastArray(array.length));
    }

    @Test
    void testPeekLastCollection() {
        LinkedList<Integer> list = new LinkedList<>();
        LinkedList<Integer> list2 = new LinkedList<>();
        list2.add(1);
        list2.add(2);
        list2.add(3);
        list2.add(4);
        assertTrue(list.add(list2));
        assertArrayEquals(list2.toArray(), list.peekLastCollection(list2.size()).toArray());
    }

    @Test
    void testPoll() {
        LinkedList<Integer> list = new LinkedList<>();
        Integer[] array = {1, 2, 3, 4, 5};
        assertTrue(list.add(array));
        list.poll();
        System.out.println(list.toString());
    }

    @Test
    void testPollArray() {
        LinkedList<Integer> list = new LinkedList<>();
        Integer[] array = {1, 2, 3, 4, 5};
        assertTrue(list.add(array));
        list.pollArray(2);
        System.out.println(list.toString());
    }

    @Test
    void testPollCollection() {
        LinkedList<Integer> list = new LinkedList<>();
        Integer[] array = {1, 2, 3, 4, 5};
        assertTrue(list.add(array));
        list.pollCollection(2);
        System.out.println(list.toString());
    }

    @Test
    void testPollLast() {
        LinkedList<Integer> list = new LinkedList<>();
        Integer[] array = {1, 2, 3, 4, 5};
        assertTrue(list.add(array));
        list.pollLast();
        System.out.println(list.toString());
    }

    @Test
    void testPollLastArray() {
        LinkedList<Integer> list = new LinkedList<>();
        Integer[] array = {1, 2, 3, 4, 5};
        assertTrue(list.add(array));
        list.pollLastArray(2);
        System.out.println(list.toString());
    }

    @Test
    void testPollLastCollection() {
        LinkedList<Integer> list = new LinkedList<>();
        Integer[] array = {1, 2, 3, 4, 5};
        assertTrue(list.add(array));
        list.pollLastCollection(2);
        System.out.println(list.toString());
    }

    @Test
    void testRemove() {
        LinkedList<Integer> list = new LinkedList<>();
        Integer[] array = {1, 2, 3, 4, 5};
        assertTrue(list.add(array));
        assertTrue(list.remove(2));
        System.out.println(list.toString());
        assertFalse(list.remove(2));
    }

    @Test
    void testRemove2() {
        LinkedList<Integer> list = new LinkedList<>();
        Integer[] array = {1, 2, 3, 4, 5};
        assertTrue(list.add(array));
        System.out.println(list.toString());
        assertTrue(list.remove(array));
        System.out.println(list.toString());
        assertFalse(list.remove(array));
    }

    @Test
    void testRemove3() {
        LinkedList<Integer> list = new LinkedList<>();
        LinkedList<Integer> list2 = new LinkedList<>();
        list2.add(1);
        list2.add(2);
        list2.add(3);
        list2.add(4);
        assertTrue(list.add(list2));
        System.out.println(list.toString());
        assertTrue(list.remove(list2));
        System.out.println(list.toString());
        assertFalse(list.remove(list2));
    }

    @Test
    void testRemove4() {
        LinkedList<Integer> list = new LinkedList<>();
        Predicate<Integer> mayorQueDos = num -> num > 2;
        Integer[] array = {1, 2, 3, 4, 5};
        assertTrue(list.add(array));
        System.out.println(list.toString());
        assertTrue(list.remove(mayorQueDos));
        System.out.println(list.toString());
        assertFalse(list.remove(mayorQueDos));
    }

    @Test
    void testReplace() {
        LinkedList<Integer> list = new LinkedList<>();
        Predicate<Integer> Dos = num -> num == 2;
        Integer[] array = {1, 2, 3, 4, 5};
        assertTrue(list.add(array));
        System.out.println(list.toString());
        list.replace(2, 3, Dos);
        System.out.println(list.toString());
    }

    @Test
    void testReplace2() {
        LinkedList<Integer> list = new LinkedList<>();
        Predicate<Integer> Dos = num -> num == 2;
        Integer[] array = {2, 2, 2, 2, 2};
        Integer[] array2 = {4, 5, 3, 4, 5};
        assertTrue(list.add(array));
        System.out.println(list.toString());
        list.replace(array, array2, Dos);
        System.out.println(list.toString());
    }

    @Test
    void testReplace3() {
        LinkedList<Integer> list = new LinkedList<>();
        LinkedList<Integer> list2 = new LinkedList<>();
        Predicate<Integer> Dos = num -> num == 2;
        LinkedList<Integer> list3 = new LinkedList<>();
        list2.add(2);
        list2.add(2);
        list2.add(3);
        list2.add(4);
        list3.add(4);
        list3.add(4);
        list3.add(4);
        list3.add(4);

        assertTrue(list.add(list2));
        System.out.println(list.toString());
        assertTrue(list.replace(list2, list3, Dos));
        System.out.println(list.toString());
    }

    @Test
    void testRetain() {
        LinkedList<Integer> list = new LinkedList<>();
        Integer[] array = {1, 2, 2, 4, 5};
        Integer[] array2 = {4, 5, 3, 4, 5};
        assertTrue(list.add(array));
        System.out.println(list.toString());
        list.retain(array2);
        System.out.println(list.toString());
    }

    @Test
    void testRetain2() {
        LinkedList<Integer> list = new LinkedList<>();
        LinkedList<Integer> list2 = new LinkedList<>();
        LinkedList<Integer> list3 = new LinkedList<>();
        list2.add(2);
        list2.add(2);
        list2.add(3);
        list2.add(4);
        list3.add(4);
        list3.add(4);
        list3.add(4);
        list3.add(4);
        assertTrue(list.add(list2));
        System.out.println(list.toString());
        list.retain(list3);
        System.out.println(list.toString());
    }

    @Test
    void testReverse() {
        LinkedList<Integer> list = new LinkedList<>();
        Integer[] array = {1, 2, 2, 4, 5};
        assertTrue(list.add(array));
        System.out.println(list.toString());
        list.reverse();
        System.out.println(list.toString());
    }

    @Test
    void testSet() {
        LinkedList<Integer> list = new LinkedList<>();
        Integer[] array = {1, 2, 2, 4, 5};
        assertTrue(list.add(array));
        System.out.println(list.toString());
        list.set(4, 3);
        System.out.println(list.toString());
    }

    @Test
    void testSize() {
        LinkedList<Integer> list = new LinkedList<>();
        Integer[] array = {1, 2, 2, 4, 5};
        assertTrue(list.add(array));
        System.out.println(list.toString());
        assertEquals(5, list.size());
    }

    @Test
    void testSort() {
        LinkedList<String> list = new LinkedList<>();
        ToIntFunction<String> stringToInt = str -> str.charAt(0);
        String[] array = {"1", "2", "7", "3"};
        assertTrue(list.addFirst(array));
        System.out.println(list.toString());
        list.sort(stringToInt);
        System.out.println(list.toString());
    }

    @Test
    void testSubList() {
        LinkedList<Integer> list = new LinkedList<>();
        Integer[] array = {1, 2, 2, 4, 5};
        assertTrue(list.add(array));
        System.out.println(list.toString());
        System.out.println(list.subList(2, 4));
    }

    @Test
    void testToArray() {
        LinkedList<Integer> list = new LinkedList<>();
        Integer[] array = {1, 2, 2, 4, 5};
        assertTrue(list.add(array));
        System.out.println(list.toString());
        Object[] array2 = list.toArray();
        for (Object x : array2) {
            System.out.println(x);
        }
    }

    @Test
    void testToString() {
        LinkedList<Integer> list = new LinkedList<>();
        Integer[] array = {1, 2, 2, 4, 5};
        assertTrue(list.add(array));
        System.out.println(list.toString());
    }
}