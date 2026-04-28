package edu.fsadriann.model.tree;

import edu.fsadriann.model.list.List;
import edu.fsadriann.model.node.AbstractNode;

public interface Tree<E> {

    List<E> preOrder();

    List<E> inOrder();

    List<E> posOrder();

    List<E> levelOrder();

    public void insert(E element);

    public void remove(E element);

    boolean search(E element);

    int getHeight();

    int size();

    double getLCI();

    double getLCIM();

    boolean isFull();

    boolean isComplete();

    int getGrade();

    boolean isEmpty();

    Tree<E> getSubtree(AbstractNode<E> root);

    Tree<E> getLeftSubtree(AbstractNode<E> root);

    Tree<E> getRightSubtree(AbstractNode<E> root);
}
