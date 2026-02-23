package edu.fsadriann.app.queue.staticqueue;

import edu.fsadriann.app.array.Array;
import edu.fsadriann.model.collection.Collection;
import edu.fsadriann.model.iterator.Iterator;
import edu.fsadriann.model.queue.AbstractQueue;

import java.util.function.Function;

public class StaticQueue<E> extends AbstractQueue<E> {

    private final Array<E> array;

    public StaticQueue(){
        array=new Array<>();
    }

    public StaticQueue(Array<E> array){
        this.array=array;
    }

    @Override
    public E peek() {
        return array.get(0);
    }

    @Override
    public E extract(){
        E element=array.get(0);
        array.remove(0);
        return element;
    }

    @Override
    public boolean insert(E element) {
        array.add(element);
        return true;
    }

    @Override
    public int size() {
        return array.size();
    }

    @Override
    public boolean reverse() {
        return array.reverse();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    @Override
    public boolean contains(Collection<E> collection) {
        return array.contains(collection);
    }

    @Override
    public boolean contains(E[] array) {
        return this.array.contains(array);
    }

    @Override
    public boolean contains(E element) {
        return array.contains(element);
    }

    @Override
    public boolean clear() {
        return array.clear();
    }

    @Override
    public void forEach(Function<E, Void> action) {
        array.forEach(action);
    }

    @Override
    public Iterator<E> iterator() {
        return array.iterator();
    }
}