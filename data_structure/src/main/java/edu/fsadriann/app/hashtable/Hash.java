package edu.fsadriann.app.hashtable;

import edu.fsadriann.app.array.Array;
import edu.fsadriann.app.linkedlist.singly.singly.LinkedList;
import edu.fsadriann.model.hashtable.AbstractHashTable;
import edu.fsadriann.model.iterator.Iterator;

public class Hash<E> extends AbstractHashTable<E> {

    private final Array<LinkedList<E>> table;
    private final int prime;
    private int size;

    public Hash(Array<LinkedList<E>> table) {
        this.table = table;
        this.prime = findPrime();
    }

    public Hash(int capacity) {
        this.table = new Array<>(capacity);
        for (int i = 0; i < capacity; i++) {
            table.add(new LinkedList<>());
        }
        this.prime = findPrime();
    }

    private int findPrime() {
        for (int number = table.getCapacity() - 1; number >= 2; number--) {
            if (isPrime(number)) return number;
        }
        return -1;
    }

    private boolean isPrime(int number) {
        for (int divisor = 2; divisor <= Math.sqrt(number); divisor++) {
            if (number % divisor == 0) return false;
        }
        return true;
    }

    @Override
    public int hash(E item) {
        if (item == null) return -1;
        return Math.abs(item.hashCode() % prime);
    }

    private LinkedList<E> getChain(E key) {
        int index = hash(key);
        return index == -1 ? null : table.get(index);
    }

    @Override
    public boolean search(E key) {
        LinkedList<E> chain = getChain(key);
        return chain != null && chain.contains(key);
    }

    @Override
    public E get(E key) {
        return search(key) ? key : null;
    }

    @Override
    public boolean insert(E key) {
        LinkedList<E> chain = getChain(key);
        if (chain == null || chain.contains(key)) return false;
        chain.add(key);
        size++;
        return true;
    }

    @Override
    public boolean delete(E key) {
        LinkedList<E> chain = getChain(key);
        if (chain == null || !chain.contains(key)) return false;
        chain.remove(key);
        size--;
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        Iterator<LinkedList<E>> iterator = table.iterator();
        while (iterator.hasNext()) {
            iterator.next().clear();
        }
        size = 0;
    }

    @Override
    public Array<LinkedList<E>> getAllElements() {
        return table;
    }
}