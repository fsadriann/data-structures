package edu.fsadriann.app.linkedlist.singly.singly;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;

import edu.fsadriann.app.linkedlist.node.singly.LinkedNode;
import edu.fsadriann.model.collection.Collection;
import edu.fsadriann.model.iterator.Iterator;
import edu.fsadriann.model.list.AbstractList;
import edu.fsadriann.model.list.List;

public class LinkedList<E> extends AbstractList<E> {

    private int size;
    private LinkedNode<E> head;
    private LinkedNode<E> tail;

    public LinkedList() {
        size = 0;
        this.head = this.tail = null;
    }

    public LinkedList(E element) {
        size = 1;
        LinkedNode<E> node = new LinkedNode<>(element);
        this.head = this.tail = node;
    }

    @Override
    public boolean isEmpty() {
        return this.head == null;
    }

    @Override
    public String toString() {
        return "LinkedList [head=" + head + "]";
    }

    @Override
    public E peek() {
        if (isEmpty()) return null;
        return this.head.get();
    }

    @Override
    public E peekLast() {
        if (isEmpty()) return null;
        return this.tail.get();
    }

    @Override
    public boolean add(E element) {
        if (element == null) return false;
        if (isEmpty()) {
            LinkedNode<E> node = new LinkedNode<>(element);
            this.head = this.tail = node;
        } else {
            LinkedNode<E> node = new LinkedNode<>(element);
            this.tail.setNext(node);
            this.tail = node;
        }
        size++;
        return true;
    }

    @Override
    public boolean add(E[] array) {
        if (array == null) return false;
        for (E element : array) add(element);
        return true;
    }

    @Override
    public boolean add(Collection<E> collection) {
        if (collection == null) return false;
        Iterator<E> iterator = collection.iterator();
        while (iterator.hasNext()) add(iterator.next());
        return true;
    }

    @Override
    public boolean addFirst(E element) {
        if (element == null) return false;
        LinkedNode<E> node = new LinkedNode<>(element);
        if (isEmpty()) {
            this.head = this.tail = node;
        } else {
            node.setNext(this.head);
            this.head = node;
        }
        size++;
        return true;
    }

    @Override
    public boolean addFirst(E[] array) {
        if (array == null) return false;
        for (E element : array) addFirst(element);
        return true;
    }

    @Override
    public boolean addFirst(Collection<E> collection) {
        if (collection == null) return false;
        Iterator<E> iterator = collection.iterator();
        while (iterator.hasNext()) addFirst(iterator.next());
        return true;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E[] peekArray(int cuantity) {
        if (isEmpty() || cuantity <= 0) return null;
        if (size < cuantity) return null;
        E[] array = (E[]) new Object[cuantity];
        LinkedNode<E> n = head;
        int i = 0;
        while (i < cuantity) {
            array[i] = n.get();
            n = n.getNext();
            i++;
        }
        return array;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E[] peekLastArray(int cuantity) {
        if (isEmpty() || cuantity <= 0) return null;
        if (size < cuantity) return null;
        E[] array = (E[]) new Object[cuantity];
        LinkedNode<E> n = head;
        for (int i = 0; i < size - cuantity; i++) n = n.getNext();
        for (int i = 0; i < cuantity; i++) {
            array[i] = n.get();
            n = n.getNext();
        }
        return array;
    }

    @Override
    public List<E> peekCollection(int cuantity) {
        if (isEmpty() || cuantity <= 0) return null;
        if (size < cuantity) return null;
        List<E> list = new LinkedList<>();
        LinkedNode<E> node = head;
        for (int i = 0; i < cuantity; i++) {
            list.add(node.get());
            node = node.getNext();
        }
        return list;
    }

    @Override
    public List<E> peekLastCollection(int cuantity) {
        if (isEmpty() || cuantity <= 0) return null;
        if (size < cuantity) return null;
        List<E> list = new LinkedList<>();
        LinkedNode<E> node = head;
        for (int i = 0; i < size - cuantity; i++) node = node.getNext();
        for (int i = 0; i < cuantity; i++) {
            list.add(node.get());
            node = node.getNext();
        }
        return list;
    }

    @Override
    public E poll() {
        if (isEmpty()) return null;
        E element = head.get();
        if (size == 1) {
            clear();
            return element;
        }
        head = head.getNext();
        size--;
        return element;
    }

    @Override
    public E pollLast() {
        if (isEmpty()) return null;
        E element = tail.get();
        if (size == 1) {
            clear();
            return element;
        }
        LinkedNode<E> node = head;
        while (node.getNext() != tail) node = node.getNext();
        node.setNext(null);
        tail = node;
        size--;
        return element;
    }

    @Override
    public E[] pollArray(int cuantity) {
        if (isEmpty() || cuantity <= 0) return null;
        if (size < cuantity) return null;
        E[] array = peekArray(cuantity);
        for (int i = 0; i < cuantity; i++) poll();
        return array;
    }

    @Override
    public E[] pollLastArray(int cuantity) {
        if (isEmpty() || cuantity <= 0) return null;
        if (size < cuantity) return null;
        E[] array = peekLastArray(cuantity);
        for (int i = 0; i < cuantity; i++) pollLast();
        return array;
    }

    @Override
    public List<E> pollCollection(int cuantity) {
        if (isEmpty() || cuantity <= 0) return null;
        if (size < cuantity) return null;
        List<E> list = peekCollection(cuantity);
        for (int i = 0; i < cuantity; i++) poll();
        return list;
    }

    @Override
    public List<E> pollLastCollection(int cuantity) {
        if (isEmpty() || cuantity <= 0) return null;
        if (size < cuantity) return null;
        List<E> list = peekLastCollection(cuantity);
        for (int i = 0; i < cuantity; i++) pollLast();
        return list;
    }

    @Override
    public boolean remove(E element) {
        if (isEmpty()) return false;
        LinkedNode<E> previous = null;
        LinkedNode<E> n = head;
        while (n != null && !n.get().equals(element)) {
            previous = n;
            n = n.getNext();
        }
        if (n == null) return false;
        if (n == head) {
            if (size == 1) {
                clear();
            } else {
                head = head.getNext();
                size--;
            }
            return true;
        }
        if (n == tail) {
            previous.setNext(null);
            tail = previous;
            size--;
            return true;
        }
        previous.setNext(n.getNext());
        size--;
        return true;
    }

    @Override
    public boolean remove(E[] array) {
        if (isEmpty()) return false;
        if (size < array.length) return false;
        for (E x : array) while (remove(x)) {}
        return true;
    }

    @Override
    public boolean remove(Collection<E> collection) {
        if (isEmpty()) return false;
        if (size < collection.size()) return false;
        Iterator<E> iterator = collection.iterator();
        while (iterator.hasNext()) {
            E val = iterator.next();
            while (remove(val)) {}
        }
        return true;
    }

    @Override
    public boolean remove(Predicate<E> filter) {
        if (filter == null || isEmpty()) return false;
        LinkedNode<E> node = head;
        while (node != null) {
            LinkedNode<E> next = node.getNext();
            if (filter.test(node.get())) remove(node.get());
            node = next;
        }
        return true;
    }

    @Override
    public boolean replace(E element, E newElement, Predicate<E> comparator) {
        if (comparator == null || element == null || newElement == null) return false;
        if (isEmpty()) return false;
        for (LinkedNode<E> node = head; node != null; node = node.getNext()) {
            if (node.get().equals(element) && comparator.test(node.get())) {
                node.set(newElement);
                break;
            }
        }
        return true;
    }

    @Override
    public boolean replace(E[] array, E[] newArray, Predicate<E> comparator) {
        if (isEmpty() || comparator == null) return false;
        if (array.length != newArray.length) return false;
        if (size < array.length) return false;
        for (int i = 0; i < array.length; i++) replace(array[i], newArray[i], comparator);
        return true;
    }

    @Override
    public boolean replace(Collection<E> collection, Collection<E> newCollection, Predicate<E> comparator) {
        if (isEmpty() || collection == null || newCollection == null || comparator == null) return false;
        if (collection.size() != newCollection.size()) return false;
        if (size < collection.size()) return false;
        Iterator<E> iterator = collection.iterator();
        Iterator<E> iterator2 = newCollection.iterator();
        while (iterator.hasNext()) replace(iterator.next(), iterator2.next(), comparator);
        return true;
    }

    @Override
    public boolean set(E index, E element) {
        if (isEmpty() || element == null || index == null) return false;
        for (LinkedNode<E> node = head; node != null; node = node.getNext()) {
            if (node.get().equals(index)) {
                node.set(element);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean retain(E[] array) {
        if (isEmpty() || array == null) return false;
        LinkedNode<E> node = head;
        while (node != null) {
            boolean found = false;
            LinkedNode<E> next = node.getNext();
            for (E element : array) {
                if (node.get().equals(element)) {
                    found = true;
                    break;
                }
            }
            if (!found) remove(node.get());
            node = next;
        }
        return true;
    }

    @Override
    public boolean retain(Collection<E> collection) {
        if (isEmpty() || collection == null) return false;
        LinkedNode<E> node = head;
        while (node != null) {
            boolean found = false;
            Iterator<E> iterator = collection.iterator();
            LinkedNode<E> next = node.getNext();
            while (iterator.hasNext()) {
                if (node.get().equals(iterator.next())) {
                    found = true;
                    break;
                }
            }
            if (!found) remove(node.get());
            node = next;
        }
        return true;
    }

    @Override
    public List<E> subList(E from, E to) {
        List<E> list = new LinkedList<>();
        if (from == null || to == null || isEmpty()) return list;
        boolean start = false;
        LinkedNode<E> node = head;
        while (node != null) {
            if (node.get().equals(to) && !start) return list;
            if (node.get().equals(from)) start = true;
            if (start) list.add(node.get());
            if (node.get().equals(to)) break;
            node = node.getNext();
        }
        return list;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E[] toArray() {
        Object[] array = new Object[size];
        if (isEmpty()) return (E[]) array;
        int i = 0;
        for (LinkedNode<E> node = head; node != null; node = node.getNext()) {
            array[i] = node.get();
            i++;
        }
        return (E[]) array;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean sort(ToIntFunction<E> toInt) {
        if (toInt == null || size < 2) return false;
        Object[] array = toArray();
        for (int x = 0; x < array.length - 1; x++) {
            for (int y = 0; y < array.length - 1 - x; y++) {
                int a = toInt.applyAsInt((E) array[y]);
                int b = toInt.applyAsInt((E) array[y + 1]);
                if (a > b) {
                    Object temp = array[y];
                    array[y] = array[y + 1];
                    array[y + 1] = temp;
                }
            }
        }
        LinkedNode<E> node = head;
        int i = 0;
        while (node != null) {
            node.set((E) array[i++]);
            node = node.getNext();
        }
        return true;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean clear() {
        try {
            head = tail = null;
            size = 0;
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean contains(E element) {
        for (LinkedNode<E> node = head; node != null; node = node.getNext()) {
            if (node.get().equals(element)) return true;
        }
        return false;
    }

    @Override
    public boolean contains(E[] array) {
        if (array == null) return false;
        for (E x : array) {
            if (!contains(x)) return false;
        }
        return true;
    }

    @Override
    public boolean contains(Collection<E> collection) {
        if (collection == null) return false;
        Iterator<E> iterator = collection.iterator();
        while (iterator.hasNext()) {
            if (!contains(iterator.next())) return false;
        }
        return true;
    }

    @Override
    public boolean reverse() {
        if (isEmpty() || size < 2) return false;
        LinkedNode<E> current = head;
        LinkedNode<E> previous = null;
        LinkedNode<E> temp = tail;
        tail = head;
        head = temp;
        while (current != null) {
            LinkedNode<E> next = current.getNext();
            current.setNext(previous);
            previous = current;
            current = next;
        }
        return true;
    }

    @Override
    public void forEach(Function<E, Void> action) {
        if (isEmpty() || action == null) return;
        for (LinkedNode<E> node = head; node != null; node = node.getNext()) {
            action.apply(node.get());
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private LinkedNode<E> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new IllegalStateException("No more elements");
                }
                E element = current.get();
                current = current.getNext();
                return element;
            }
        };
    }
}