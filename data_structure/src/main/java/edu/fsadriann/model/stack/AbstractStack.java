package edu.fsadriann.model.stack;

import java.util.function.Function;
import edu.fsadriann.linkedlist.singly.LinkedList;
import edu.fsadriann.model.collection.AbstractCollection;
import edu.fsadriann.model.collection.Collection;
import edu.fsadriann.model.iterator.Iterator;

public abstract class AbstractStack<E> extends AbstractCollection<E> implements Stack<E> {

    private LinkedList<E> list;

    public AbstractStack() {
    this.list = new LinkedList<>();
    }

    @Override
    public E peek() {
    return list.peekLast();
    }

    @Override
    public E pop() {
    return list.pollLast();
    }

    @Override
    public boolean push(E element) {
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