package edu.fsadriann.model.array;

import edu.fsadriann.model.collection.Collection;
import java.util.function.Predicate;

public interface BufferArray<E> {

    void defragment();
    boolean dimension(int newDimension);
    boolean add(E element);
    boolean add(int index, E[] array);
    boolean add(int index, Collection<E> collection);
    E get(int index);
    int indexOf(E element);
    int lastIndexOf(E element);
    boolean remove(int index);
    boolean remove(int from, int to);
    boolean remove(Predicate<E> filter);
    boolean set(int index, E element);
    
}