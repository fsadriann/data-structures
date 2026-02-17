package edu.fsadriann.linkedlist.node.doubly.circular;
import edu.fsadriann.model.node.AbstractNode;

public class LinkedNode<E> extends AbstractNode<E> {

    private LinkedNode<E> next;
    private LinkedNode<E> previous;

    public LinkedNode(){
        super();
        this.next=this.previous=null;
    }

    public LinkedNode(E element){
        super(element);
        this.next=this.previous=null;
    }

    public LinkedNode(E element, LinkedNode<E> next){
        super(element);
        this.next=next;
        this.previous=null;
    }

    public LinkedNode(E element, LinkedNode<E> next, LinkedNode<E> previous){
        super(element);
        this.next=next;
        this.previous=previous;
    }

    public LinkedNode<E> getNext(){
        return this.next;
    }
    public LinkedNode<E> getPrevious(){
        return this.previous;
    }
    public void setNext(LinkedNode<E> next){
        this.next=next;
    }

    public void setPrevious(LinkedNode<E> previous){
        this.previous=previous;
    }
    @Override
    public String toString(){
        return "LinkedNode\n( Element: "+ get().toString() +")";
    }
}