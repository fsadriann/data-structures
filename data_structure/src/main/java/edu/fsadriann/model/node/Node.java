package edu.fsadriann.model.node;

public interface Node<E> {

  void set(E element);

  E get();

  String toString();
}
