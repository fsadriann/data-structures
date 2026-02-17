package edu.fsadriann.linkedlist.node.singly.circular;

import edu.fsadriann.model.node.AbstractNode;

public class LinkedNode<E> extends AbstractNode<E> {

    private LinkedNode<E> next;

    public LinkedNode(){
        super();
        this.next=null;
    }

    public LinkedNode(E element){
        super(element);
        this.next=null;
    }
    
    public LinkedNode(E element, LinkedNode<E> next){
        super(element);
        this.next=next;
    }

    public LinkedNode<E> getNext(){
        return this.next;
    }

    public void setNext(LinkedNode<E> next){
        this.next=next;
    }

    @Override
    public String toString(){
        return "LinkedNode\n( Element: "+get().toString()+")";
    }

}