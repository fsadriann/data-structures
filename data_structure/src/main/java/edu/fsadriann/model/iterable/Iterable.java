package edu.fsadriann.model.iterable;

import java.util.function.Function;

import edu.fsadriann.model.iterator.Iterator;

public interface Iterable<E> {

    void forEach(Function<E,Void> action );
    Iterator<E> iterator();

}