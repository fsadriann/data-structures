package edu.fsadriann.app.stack.list;

import java.util.function.Function;

import edu.fsadriann.app.linkedlist.singly.singly.LinkedList;
import edu.fsadriann.model.collection.Collection;
import edu.fsadriann.model.iterator.Iterator;
import edu.fsadriann.model.stack.AbstractStack;

public class Stack<E> extends AbstractStack<E> {

    private final LinkedList<E> list;


    public Stack(){

        this.list=null;
    }

    public Stack(LinkedList<E> element){

        this.list=element;
    }

    @Override
    public E peek(){

        return list.peekLast();
    }

    @Override
    public E pop(){

        return list.pollLast();
    }

    @Override
    public boolean push(E element){

        return list.add(element);
    }

    @Override
    public boolean clear(){

        return list.clear();
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
    public boolean contains(E[] element){

        return list.contains(element);
    }

    @Override
    public boolean contains(Collection<E> collection){

        return list.contains(collection);
    }

    @Override
    public boolean reverse(){

        return list.reverse();
    }

    @Override
    public int size(){

        return list.size();
    }

    @Override
    public void forEach(Function<E,Void> action){

        list.forEach(action);
    }

    @Override
    public Iterator<E> iterator(){

        return list.iterator();
    }

}