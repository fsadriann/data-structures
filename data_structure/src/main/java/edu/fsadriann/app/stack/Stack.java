package edu.fsadriann.app.stack;

public interface Stack<E> {

    public E peek();

    public E pop();

    public boolean push(E element);
}