package edu.fsadriann.app.queue.list;
import edu.fsadriann.model.collection.Collection;
import edu.fsadriann.model.iterator.Iterator;
import edu.fsadriann.model.queue.AbstractQueue;
import edu.fsadriann.app.linkedlist.singly.singly.LinkedList;

import java.util.function.Function;

public class Queue<E> extends AbstractQueue<E> {

    private final LinkedList<E> list;

    public Queue() {
        list = new LinkedList<>();
    }

    public Queue(LinkedList<E> list) {
        this.list = list;
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
    public boolean insert(E element){
        return list.add(element);
    }

    @Override
    public int size(){
        return list.size();
    }

    @Override
    public boolean isEmpty(){
        return list.isEmpty();
    }

    @Override
    public boolean contains(E element){
        return list.contains(element);
    }

    @Override
    public boolean clear() {
        return list.clear();
    }

    @Override
    public boolean reverse() {
        return list.reverse();
    }

    @Override
    public boolean contains(Collection<E> collection) {
        return list.contains(collection);
    }

    @Override
    public boolean contains(E[] array) {
        return list.contains(array);
    }

    @Override
    public Iterator<E> iterator() {
        return list.iterator();
    }

    @Override
    public void forEach(Function<E, Void> action) {
        list.forEach(action);
    }
}