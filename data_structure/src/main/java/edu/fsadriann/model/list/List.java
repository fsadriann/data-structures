package edu.fsadriann.model.list;

import java.util.function.Predicate;
import java.util.function.ToIntFunction;

import edu.fsadriann.model.collection.Collection;

public interface List<E> {
  boolean add(E element);

  boolean add(E[] array);

  boolean add(Collection<E> collection);

  boolean addFirst(E element);

  boolean addFirst(E[] array);

  boolean addFirst(Collection<E> collection);

  E peek();

  E peekLast();

  E[] peekArray(int n);

  E[] peekLastArray(int n);

  List<E> peekCollection(int n);

  List<E> peekLastCollection(int n);

  E poll();

  E pollLast();

  E[] pollArray(int n);

  E[] pollLastArray(int n);

  List<E> pollCollection(int n);

  List<E> pollLastCollection(int n);

  boolean remove(E element);

  boolean remove(E[] array);

  boolean remove(Collection<E> collection);

  boolean remove(Predicate<E> filter);

  boolean replace(E element, E newElement, Predicate<E> comparator);

  boolean replace(E[] array, E[] newArray, Predicate<E> comparator);

  boolean replace(Collection<E> collection, Collection<E> newCollection, Predicate<E> comparator);

  boolean retain(E[] array);

  boolean retain(Collection<E> collection);

  boolean set(E index, E element);

  boolean sort(ToIntFunction<E> toInt);

  List<E> subList(E from, E to);

  E[] toArray();
}
