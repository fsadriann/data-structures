package edu.fsadriann.app.binary_tree;

import edu.fsadriann.model.node.AbstractNode;

public class Root<E> extends AbstractNode<E> {
    Root<E> left;
    Root<E> right;

    public Root() {
        super();
        this.right = null;
        this.left = null;
    }

    public Root(E element) {
        super(element);
        this.right = null;
        this.left = null;
    }

    public Root<E> getRight() {
        return right;
    }

    public void setRight(Root<E> right) {
        this.right = right;
    }

    public Root<E> getLeft() {
        return left;
    }

    public void setLeft(Root<E> left) {
        this.left = left;
    }

    public String toString(){
        return "Root{" +
                "element=" + element +
                ", left=" + left +
                ", right" + right +
                '}';
    }
}