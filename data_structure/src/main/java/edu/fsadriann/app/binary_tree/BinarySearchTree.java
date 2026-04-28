package edu.fsadriann.app.binary_tree;

public class BinarySearchTree<E extends Comparable<E>> extends BinaryTree<E> {

    public BinarySearchTree() {
        super(null);
    }

    public BinarySearchTree(Root<E> root) {
        super(root);
    }

    @Override
    public boolean search(E value) {
        return searchBST(root, value);
    }

    private boolean searchBST(Root<E> node, E value) {
        if (node == null) {
            return false;
        }

        int cmp = value.compareTo(node.get());

        if (cmp == 0) {
            return true;
        } else if (cmp < 0) {
            return searchBST(node.getLeft(), value);
        } else {
            return searchBST(node.getRight(), value);
        }
    }
}