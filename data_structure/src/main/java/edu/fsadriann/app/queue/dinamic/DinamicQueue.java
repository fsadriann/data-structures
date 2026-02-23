package edu.fsadriann.app.queue.dinamic;

import java.util.function.Function;

import edu.fsadriann.app.linkedlist.singly.singly.LinkedList;
import edu.fsadriann.model.collection.AbstractCollection;
import edu.fsadriann.model.collection.Collection;
import edu.fsadriann.model.iterator.Iterator;
import edu.fsadriann.model.queue.Queue;

public class DinamicQueue<E> extends AbstractCollection<E> implements Queue<E> {

    private LinkedList<E> list;

    public DinamicQueue() {
        this.list = new LinkedList<>();
    }

    public DinamicQueue(E element) {
        this.list = new LinkedList<>();
        this.list.add(element);
    }

    @Override
    public E peek() {
        return list.peek();
    }

    @Override
    public E extract() {
        return list.poll();
    }

    @Override
    public boolean insert(E element) {
        return list.add(element);
    }

    @Override
    public boolean clear() {
        return list.clear();
    }

    @Override
    public boolean contains(E element) {
        return list.contains(element);
    }

    @Override
    public boolean contains(E[] array) {
        return list.contains(array);
    }

    @Override
    public boolean contains(Collection<E> collection) {
        return list.contains(collection);
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public boolean reverse() {
        return list.reverse();
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public void forEach(Function<E, Void> action) {
        list.forEach(action);
    }

    @Override
    public Iterator<E> iterator() {
        return list.iterator();
    }

}