package edu.fsadriann.app.priorityqueue;

import edu.fsadriann.app.queue.array.Queue;
import edu.fsadriann.model.iterator.Iterator;
import edu.fsadriann.app.array.Array;
import edu.fsadriann.model.collection.Collection;
import edu.fsadriann.model.priorityqueue.AbstractPriorityQueue;

import java.util.NoSuchElementException;
import java.util.function.Function;


public class PriorityQueue<E> extends AbstractPriorityQueue<E> {

    private final int prioridades;
    private final Array<Queue<E>> array;


    public PriorityQueue(int prioridades) {
        if (prioridades <= 0) {
            throw new IllegalArgumentException("El número de prioridades debe ser mayor a 0");
        }
        this.prioridades = prioridades;
        array = new Array<>(prioridades);
        for (int i = 0; i < prioridades; i++) {
            array.add(new Queue<>());
        }
    }

    // Retorna el índice de la primera cola no vacía, o -1 si todas están vacías
    private int firstNonEmptyIndex() {
        for (int i = 0; i < prioridades; i++) {
            if (!array.get(i).isEmpty()) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public E peek() {
        int index = firstNonEmptyIndex();
        if (index == -1) {
            throw new NoSuchElementException("La cola de prioridad está vacía");
        }
        return array.get(index).peek();
    }

    @Override
    public E extract() {
        int index = firstNonEmptyIndex();
        if (index == -1) {
            throw new NoSuchElementException("La cola de prioridad está vacía");
        }
        return array.get(index).extract();
    }

    @Override
    public boolean insert(E element) {
        // Inserta en la última prioridad (la más baja) por defecto
        return array.get(prioridades - 1).insert(element);
    }

    @Override
    public boolean insert(int index, E element) {
        if (index < 0 || index >= prioridades) {
            return false;
        }
        return array.get(index).insert(element);
    }

    @Override
    public int size() {
        int total = 0;
        for (int i = 0; i < prioridades; i++) {
            total += array.get(i).size();
        }
        return total;
    }

    @Override
    public boolean isEmpty() {
        return firstNonEmptyIndex() == -1;
    }

    @Override
    public boolean clear() {
        for (int i = 0; i < prioridades; i++) {
            array.get(i).clear();
        }
        return true;
    }

    @Override
    public boolean contains(Collection<E> collection) {
        for (int i = 0; i < prioridades; i++) {
            if (array.get(i).contains(collection)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean contains(E element) {
        for (int i = 0; i < prioridades; i++) {
            if (array.get(i).contains(element)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean contains(E[] elements) {
        for (int i = 0; i < prioridades; i++) {
            if (this.array.get(i).contains(elements)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean reverse() {
        for (int i = 0; i < prioridades; i++) {
            array.get(i).reverse();
        }
        return true;
    }

    @Override
    public void forEach(Function<E, Void> action) {
        for (int i = 0; i < prioridades; i++) {
            array.get(i).forEach(action);
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            int prioridad = 0;
            Iterator<E> iteratorQueue = array.get(0).iterator();

            @Override
            public boolean hasNext() {
                while (prioridad < prioridades) {
                    if (iteratorQueue.hasNext()) {
                        return true;
                    }
                    prioridad++;
                    if (prioridad < prioridades) {
                        iteratorQueue = array.get(prioridad).iterator();
                    }
                }
                return false;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("No hay más elementos");
                }
                return iteratorQueue.next();
            }
        };
    }
}