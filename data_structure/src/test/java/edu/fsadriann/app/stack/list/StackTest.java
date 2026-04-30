package edu.fsadriann.app.stack.list;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StackTest {

    @Test
    void peek() {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertEquals(3, stack.peek());
    }

    @Test
    void pop() {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertEquals(3, stack.pop());
        assertEquals(2, stack.size());
    }

    @Test
    void push() {
        Stack<Integer> stack = new Stack<>();
        assertTrue(stack.push(1));
        assertTrue(stack.push(2));
        assertEquals(2, stack.size());
    }

    @Test
    void clear() {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        assertTrue(stack.clear());
        assertTrue(stack.isEmpty());
    }

    @Test
    void isEmpty() {
        Stack<Integer> stack = new Stack<>();
        assertTrue(stack.isEmpty());
        stack.push(1);
        assertFalse(stack.isEmpty());
    }

    @Test
    void contains() {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        assertTrue(stack.contains(1));
        assertFalse(stack.contains(99));
    }

    @Test
    void testContains() {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        Integer[] array = {1, 2};
        assertTrue(stack.contains(array));
    }

    @Test
    void testContains1() {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        Stack<Integer> other = new Stack<>();
        other.push(1);
        other.push(2);
        assertTrue(stack.contains(other));
    }

    @Test
    void reverse() {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertTrue(stack.reverse());
        assertEquals(1, stack.peek());
    }

    @Test
    void size() {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertEquals(3, stack.size());
    }

    @Test
    void forEach() {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        int[] sum = {0};
        stack.forEach(e -> { sum[0] += e; return null; });
        assertEquals(6, sum[0]);
    }

    @Test
    void iterator() {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        var iterator = stack.iterator();
        int count = 0;
        while (iterator.hasNext()) {
            assertNotNull(iterator.next());
            count++;
        }
        assertEquals(3, count);
    }
}