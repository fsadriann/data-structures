package edu.fsadriann.app.binary_tree;

import edu.fsadriann.app.linkedlist.singly.singly.LinkedList;
import org.junit.jupiter.api.Test;
import edu.fsadriann.model.tree.Tree;

import static org.junit.jupiter.api.Assertions.*;

class BinTreeTest {

    @Test
    void isFull() {
        BinaryTree<Integer> binTree = new BinaryTree<>(new Root<>(10));
        binTree.insert(5);
        binTree.insert(15);
        binTree.insert(3);
        binTree.insert(7);
        binTree.insert(12);
        binTree.insert(20);
        assertTrue(binTree.isFull());
    }

    @Test
    void isComplete() {
        BinaryTree<Integer> binTree = new BinaryTree<>(new Root<>(10));
        binTree.insert(5);
        binTree.insert(15);
        binTree.insert(3);
        binTree.insert(7);
        binTree.insert(12);
        binTree.insert(20);
        assertTrue(binTree.isComplete());
    }

    @Test
    void getLCI() {
        BinaryTree<Integer> binTree = new BinaryTree<>(new Root<>(10));
        binTree.insert(5);
        binTree.insert(15);
        binTree.insert(3);
        binTree.insert(7);
        binTree.insert(12);
        binTree.insert(20);
        System.out.println("LCI: " + binTree.getLCI());
    }

    @Test
    void getLCIM() {
        BinaryTree<Integer> binTree = new BinaryTree<>(new Root<>(10));
        binTree.insert(5);
        binTree.insert(15);
        binTree.insert(3);
        binTree.insert(7);
        binTree.insert(12);
        binTree.insert(20);
        System.out.println("LCIM: " + binTree.getLCIM());
    }

    @Test
    void getSubtree() {
        BinaryTree<Integer> binTree = new BinaryTree<>(new Root<>(10));
        binTree.insert(5);
        binTree.insert(15);
        binTree.insert(3);
        binTree.insert(7);
        Root<Integer> node = new Root<>(5);
        Tree<Integer> subTree = binTree.getSubtree(node);
        System.out.println("Subtree levelOrder: " + subTree.levelOrder());
    }

    @Test
    void getRightSubtree() {
        BinaryTree<Integer> binTree = new BinaryTree<>(new Root<>(10));
        binTree.insert(5);
        binTree.insert(15);
        binTree.insert(3);
        binTree.insert(7);
        Root<Integer> node = new Root<>(5);
        Tree<Integer> subTree = binTree.getRightSubtree(node);
        System.out.println("RightSubtree levelOrder: " + subTree.levelOrder());
    }

    @Test
    void getLeftSubtree() {
        BinaryTree<Integer> binTree = new BinaryTree<>(new Root<>(10));
        binTree.insert(5);
        binTree.insert(15);
        binTree.insert(3);
        binTree.insert(7);
        Root<Integer> node = new Root<>(5);
        Tree<Integer> subTree = binTree.getLeftSubtree(node);
        System.out.println("LeftSubtree levelOrder: " + subTree.levelOrder());
    }

    @Test
    void isEmpty() {
        BinaryTree<Integer> binTree = new BinaryTree<>(new Root<>(10));
        assertFalse(binTree.isEmpty());
        BinaryTree<Integer> emptyTree = new BinaryTree<>(null);
        assertTrue(emptyTree.isEmpty());
    }

    @Test
    void getGrade() {
        BinaryTree<Integer> binTree = new BinaryTree<>(new Root<>(10));
        binTree.insert(5);
        binTree.insert(15);
        System.out.println("Grade: " + binTree.getGrade());
    }

    @Test
    void getHeight() {
        BinaryTree<Integer> binTree = new BinaryTree<>(new Root<>(10));
        binTree.insert(5);
        binTree.insert(15);
        binTree.insert(3);
        binTree.insert(7);
        System.out.println("Height: " + binTree.getHeight()); // esperado: 3
    }

    @Test
    void size() {
        BinaryTree<Integer> binTree = new BinaryTree<>(new Root<>(10));
        binTree.insert(5);
        binTree.insert(15);
        binTree.insert(3);
        binTree.insert(7);
        System.out.println("Size: " + binTree.size()); // esperado: 5
    }

    @Test
    void search() {
        BinaryTree<Integer> binTree = new BinaryTree<>(new Root<>(10));
        binTree.insert(5);
        binTree.insert(15);
        assertTrue(binTree.search(5));
        assertFalse(binTree.search(99));
    }

    @Test
    void remove() {
        BinaryTree<Integer> binTree = new BinaryTree<>(new Root<>(10));
        binTree.insert(5);
        binTree.insert(15);
        binTree.insert(3);
        binTree.insert(7);
        System.out.println("Antes: " + binTree.levelOrder());
        binTree.remove(5);
        System.out.println("Después: " + binTree.levelOrder());
    }

    @Test
    void preOrder() {
        BinaryTree<Integer> binTree = new BinaryTree<>(new Root<>(10));
        binTree.insert(5);
        binTree.insert(15);
        binTree.insert(3);
        binTree.insert(7);
        System.out.println("PreOrder: " + binTree.preOrder());
    }

    @Test
    void inOrder() {
        BinaryTree<Integer> binTree = new BinaryTree<>(new Root<>(10));
        binTree.insert(5);
        binTree.insert(15);
        binTree.insert(3);
        binTree.insert(7);
        System.out.println("InOrder: " + binTree.inOrder()); // esperado: [3,5,7,10,15]
    }

    @Test
    void posOrder() {
        BinaryTree<Integer> binTree = new BinaryTree<>(new Root<>(10));
        binTree.insert(5);
        binTree.insert(15);
        binTree.insert(3);
        binTree.insert(7);
        System.out.println("PosOrder: " + binTree.posOrder());
    }

    @Test
    void levelOrder() {
        BinaryTree<Integer> binTree = new BinaryTree<>(new Root<>(10));
        binTree.insert(5);
        binTree.insert(15);
        binTree.insert(3);
        binTree.insert(7);
        System.out.println("LevelOrder: " + binTree.levelOrder());
    }
}