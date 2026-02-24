package edu.fsadriann.app.queue.staticqueue;

import edu.fsadriann.app.array.Array;
import edu.fsadriann.model.collection.Collection;
import edu.fsadriann.model.iterator.Iterator;
import edu.fsadriann.model.queue.AbstractQueue;

import java.util.function.Function;

public class StaticQueue<E> extends AbstractQueue<E> {

    private final Array<E> array;
    private int top; // es el tail
    private int head;

    public StaticQueue(){
        array=new Array<>();
        this.head = -1;
        this.top = -1;
    }

    public StaticQueue(Array<E> array){
        this.array=array;
    }

    @Override
    public E peek() {
        if (isEmpty()) return null;
        return array.get(head);
    }

    @Override
    public E extract() {

        if (isEmpty()) return null;

        E element = array.get(head);

        if (head == top) {
            head = -1;
            top = -1;
        } else {
            head = (head + 1) % 1000;
        }

        return element;
    }

    @Override
    public boolean insert(E element) {

        if (size() == 1000) return false; // llena

        if (isEmpty()) {
            array.add(element);
            head = 0;
            top = 0;
            return true;
        }

        top = (top + 1) % 1000;

        if (top >= array.size()) {
            array.add(element);   // todavía estamos llenando por primera vez
        } else {
            array.set(top, element); // reutilizamos espacio
        }

        return true;
    }

    @Override
    public int size() {

        if (head == -1) return 0;

        if (top >= head)
            return top - head + 1;

        return 1000 - head + top + 1;
    }

    @Override
    public boolean reverse() {
        return array.reverse();
    }

    @Override
    public boolean isEmpty() {
        return head == -1;
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
        head = -1;
        top = -1;
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