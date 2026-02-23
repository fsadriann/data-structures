package edu.fsadriann.model.stack;

import java.util.function.Function;

import edu.fsadriann.app.stack.Stack;
import edu.fsadriann.model.collection.AbstractCollection;
import edu.fsadriann.model.collection.Collection;
import edu.fsadriann.model.iterator.Iterator;

public class StaticStack<E> extends AbstractCollection<E> implements Stack<E> {

    private E[] array;
    private int top;
    private static final int DEFAULT_CAPACITY = 1000;

    @SuppressWarnings("unchecked")
    public StaticStack() {
        this.array = (E[]) new Object[DEFAULT_CAPACITY];
        this.top = -1;
    }

    @SuppressWarnings("unchecked")
    public StaticStack(E element) {
        this.array = (E[]) new Object[DEFAULT_CAPACITY];
        this.array[0] = element;
        this.top = 0;
    }

    @Override
    public int size() {
        return top + 1;
    }

    @Override
    public boolean isEmpty() {
        return top == -1;
    }

    @Override
    public boolean clear() {
        for (int i = 0; i <= top; i++) {
            array[i] = null;
        }
        top = -1;
        return true;
    }

    @Override
    public boolean reverse() {
        if (top < 1) {
            return true;
        }
        for (int i = 0; i < top / 2; i++) {
            E temp = array[i];
            array[i] = array[top - i];
            array[top - i] = temp;
        }
        return true;
    }

    @Override
    public boolean contains(E element) {
        if (element == null) {
            return false;
        }
        for (int i = 0; i <= top; i++) {
            if (array[i] != null && array[i].equals(element)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean contains(E[] elements) {
        if (elements == null) {
            return false;
        }
        for (E element : elements) {
            if (!contains(element)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean contains(Collection<E> collection) {
        if (collection == null) {
            return false;
        }
        Iterator<E> iterator = collection.iterator();
        while (iterator.hasNext()) {
            if (!contains(iterator.next())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void forEach(Function<E, Void> action) {
        if (action == null) {
            return;
        }
        Iterator<E> iterator = iterator();
        while (iterator.hasNext()) {
            action.apply(iterator.next());
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int index = top;

            @Override
            public boolean hasNext() {
                return index >= 0;
            }

            @Override
            public E next() {
                return array[index--];
            }
        };
    }

    @Override
    public boolean push(E element) {
        if (top + 1 >= array.length) {
            return false;
        }
        array[++top] = element;
        return true;
    }

    @Override
    public E pop() {
        if (isEmpty()) {
            return null;
        }
        E element = array[top];
        array[top] = null;
        top--;
        return element;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return array[top];
    }

    public boolean isFull() {
        return top + 1 >= array.length;
    }

    public int capacity() {
        return array.length;
    }
}   