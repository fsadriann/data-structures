package edu.fsadriann.model.hashtable;
import edu.fsadriann.app.array.Array;
import edu.fsadriann.app.linkedlist.singly.singly.LinkedList;

public interface HashTable<E> {
    int hash(E item);
    boolean insert(E key);
    E get(E key);
    boolean search(E key);
    boolean delete(E key);
    int size();
    boolean isEmpty();
    void clear();
    Array<LinkedList<E>>getAllElements();
}