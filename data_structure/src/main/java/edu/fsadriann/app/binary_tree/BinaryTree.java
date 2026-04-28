package edu.fsadriann.app.binary_tree;

import edu.fsadriann.app.linkedlist.singly.singly.LinkedList;
import edu.fsadriann.app.queue.list.Queue;
import edu.fsadriann.model.list.List;
import edu.fsadriann.model.node.AbstractNode;
import edu.fsadriann.model.tree.AbstractTree;
import edu.fsadriann.model.tree.Tree;
import edu.fsadriann.app.binary_tree.Root;


public class BinaryTree<E> extends AbstractTree<E> {

    protected Root<E> root;

    public BinaryTree(Root<E> root) {
        this.root = root;
    }

    @Override
    public LinkedList<E> preOrder() {
        LinkedList<E> list=new LinkedList<>();
        if(isEmpty()){
            return list;
        }

        list.add(root.get());
        if(root.getLeft()!=null){
            BinaryTree<E> leftTree=new BinaryTree<>(root.getLeft());
            list.add(leftTree.preOrder());
        }
        if(root.getRight()!=null){
            BinaryTree<E> rightTree=new BinaryTree<>(root.getRight());
            list.add(rightTree.preOrder());
        }
        return list;
    }

    @Override
    public LinkedList<E> inOrder() {
        LinkedList<E> list = new LinkedList<>();
        if (isEmpty()) return list;

        if (root.getLeft() != null) {
            list.add(new BinaryTree<>(root.getLeft()).inOrder());
        }

        list.add(root.get());

        if (root.getRight() != null) {
            list.add(new BinaryTree<>(root.getRight()).inOrder());
        }
        return list;
    }

    @Override
    public LinkedList<E> posOrder() {
        LinkedList<E> list=new LinkedList<>();
        if(isEmpty()){
            return list;
        }
        if(root.getLeft()!=null){
            BinaryTree<E> tree=new BinaryTree<>(root.getLeft());
            list.add(tree.posOrder());
        }
        if(root.getRight()!=null){
            BinaryTree<E> tree=new BinaryTree<>(root.getRight());
            list.add(tree.posOrder());
        }
        list.add(root.get());

        return list;
    }

    @Override
    public List<E> levelOrder() {
        List<E> list = new LinkedList<>();
        if (isEmpty()) return list;

        Queue<Root<E>> queue = new Queue<>();
        queue.insert(root);

        while (!queue.isEmpty()) {
            Root<E> current = queue.extract();
            list.add(current.get());

            if (current.getLeft() != null) {
                queue.insert(current.getLeft());
            }
            if (current.getRight() != null) {
                queue.insert(current.getRight());
            }
        }
        return list;
    }

    @Override
    public void insert(E element) {
        Root<E> newNode = new Root<>(element);
        if (isEmpty()) {
            root = newNode;
            return;
        }
        insert(root, newNode);
    }

    private void insert(Root<E> current, Root<E> newNode) {
        Comparable<E> comp = (Comparable<E>) newNode.get();
        if (comp.compareTo(current.get()) < 0) {
            if (current.getLeft() == null) {
                current.setLeft(newNode);
            } else {
                insert(current.getLeft(), newNode);
            }
        } else {
            if (current.getRight() == null) {
                current.setRight(newNode);
            } else {
                insert(current.getRight(), newNode);
            }
        }
    }

    @Override
    public void remove(E element) {
        if (isEmpty() || element == null) return;
        root = remove(root, element);
    }

    private Root<E> getMin(Root<E> node) {
        while (node.getLeft() != null) node = node.getLeft();
        return node;
    }

    private Root<E> remove(Root<E> node, E element) {
        if (node == null) return null;

        Comparable<E> comp = (Comparable<E>) element;
        int cmp = comp.compareTo(node.get());

        if (cmp < 0) {
            node.setLeft(remove(node.getLeft(), element));
        } else if (cmp > 0) {
            node.setRight(remove(node.getRight(), element));
        } else {
            if (node.getLeft() == null) return node.getRight();
            if (node.getRight() == null) return node.getLeft();

            Root<E> successor = getMin(node.getRight());
            node.set(successor.get());
            node.setRight(remove(node.getRight(), successor.get()));
        }
        return node;
    }

    @Override
    public boolean search(E element) {
        if (isEmpty()) {
            return false;
        }
        if (root.get().equals(element)) {
            return true;
        }

        boolean foundLeft = false;
        boolean foundRight = false;

        if (root.getLeft() != null) {
            foundLeft = new BinaryTree<>(root.getLeft()).search(element);
        }
        if (root.getRight() != null) {
            foundRight = new BinaryTree<>(root.getRight()).search(element);
        }

        return foundLeft || foundRight;
    }

    @Override
    public int getHeight() {
        if (isEmpty()) return 0;

        int h1 = 0;
        int h2 = 0;

        if (root.getLeft() != null)
            h1 = new BinaryTree<>(root.getLeft()).getHeight();

        if (root.getRight() != null)
            h2 = new BinaryTree<>(root.getRight()).getHeight();

        if (h1 > h2) return 1 + h1;
        else return 1 + h2;
    }

    @Override
    public int size() {
        return size(root);
    }

    public int size(Root<E> node) {
        if (node == null) return 0;
        return 1 + size(node.getLeft()) + size(node.getRight());
    }

    @Override
    public double getLCI() {
        int LCI=0;
        if(root==null){
            return 0;
        }
        Queue<Root<E>> queue= new Queue<>();
        queue.insert(root);
        Root<E> nextLevel=root.getLeft();
        LCI++;
        int level=2;

        while(!queue.isEmpty()){
            Root<E> current=queue.extract();
            if (current.equals(nextLevel)){
                level++;
                nextLevel=current.getLeft();
            }
            if(current.getLeft()!=null){
                queue.insert(current.getLeft());
                LCI+=level;

            }
            if(current.getRight()!=null){
                queue.insert(current.getRight());
                LCI+=level;
            }
        }

        return LCI;
    }

    @Override
    public double getLCIM() {
        return (double) getLCI() /size();
    }

    @Override
    public boolean isFull() {
        if (isEmpty()){
            return true;
        }
        if(root.getLeft()!= null && root.getRight()!= null) {
            BinaryTree<E> leftTree = new BinaryTree<>(root.getLeft());
            BinaryTree<E> rightTree = new BinaryTree<>(root.getRight());
            return leftTree.isFull()&& rightTree.isFull();
        }
        return (root.getRight()==null && root.getLeft()== null);
    }

    @Override
    public boolean isComplete() {
        if (isEmpty()){
            return true;
        }
        if(root.getLeft()!= null && root.getRight()!= null) {
            BinaryTree<E> leftTree = new BinaryTree<>(root.getLeft());
            BinaryTree<E> rightTree = new BinaryTree<>(root.getRight());
            if(leftTree.getHeight()==rightTree.getHeight()){
                return leftTree.isFull()&& rightTree.isFull();
            } else{
                return false;
            }
        }
        return (root.getRight()==null && root.getLeft()== null);
    }

    @Override
    public int getGrade() {
        int grade=0;
        if(isEmpty()||(root.getLeft()==null&&root.getRight()==null)){
            return grade;
        }
        if(root.getLeft()!=null){
            grade++;

        }
        if(root.getRight()!=null){
            grade++;
        }
        BinaryTree<E> treeLeft=new BinaryTree<>(root.getLeft());
        BinaryTree<E> treeRight=new BinaryTree<>(root.getRight());
        int gradeLeft=treeLeft.getGrade();
        int gradeRight=treeRight.getGrade();
        if(gradeLeft>grade){
            return gradeLeft;
        }
        else if(gradeRight>gradeLeft){
            return gradeRight;
        }
        return grade;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public Tree<E> getSubtree(AbstractNode<E> node) {
        if(isEmpty()){
            return null;
        }
        if(this.root.get().equals(node.get())){
            return new BinaryTree<>(this.root);
        }

        if(this.root.getLeft()!=null){
            Tree<E> tree=new BinaryTree<>(this.root.getLeft());
            Tree<E> found=tree.getSubtree(node);
            if(found!=null){
                return found;
            }

        }
        if(this.root.getRight()!=null){
            Tree<E> tree=new BinaryTree<>(this.root.getRight());
            Tree<E> found=tree.getSubtree(node);
            if(found!=null){
                return found;
            }
        }
        return null;
    }

    @Override
    public Tree<E> getLeftSubtree(AbstractNode<E> root) {
        if(isEmpty()){
            return null;
        }
        if(this.root.get().equals(root.get())){
            return new BinaryTree<>(this.root.getLeft());
        }

        if(this.root.getRight()!=null){
            Tree<E> tree=new BinaryTree<>(this.root.getRight());
            Tree<E> found=tree.getLeftSubtree(root);
            if(found!=null){
                return found;
            }

        }
        if(this.root.getLeft()!=null){
            Tree<E> tree=new BinaryTree<>(this.root.getLeft());
            Tree<E> found=tree.getLeftSubtree(root);
            if(found!=null){
                return found;
            }
        }
        return null;
    }

    @Override
    public Tree<E> getRightSubtree(AbstractNode<E> root) {
        if(isEmpty()){
            return null;
        }
        if(this.root.get().equals(root.get())){
            return new BinaryTree<>(this.root.getRight());
        }

        if(this.root.getLeft()!=null){
            Tree<E> tree=new BinaryTree<>(this.root.getLeft());
            Tree<E> found=tree.getRightSubtree(root);
            if(found!=null){
                return found;
            }

        }
        if(this.root.getRight()!=null){
            Tree<E> tree=new BinaryTree<>(this.root.getRight());
            Tree<E> found=tree.getRightSubtree(root);
            if(found!=null){
                return found;
            }
        }
        return null;
    }

    public void printTree() {
        printTree(root, "", true, true);
    }

    private void printTree(Root<E> node, String prefix, boolean isRoot, boolean isLeft) {
        if (node == null) return;

        String label = "\"" + node.get().toString() + "\"";

        if (isRoot) {
            System.out.println(label);
        } else {
            System.out.println(prefix + (isLeft ? "-- " : "-- ") + label);
        }

        boolean hasLeft  = node.getLeft()  != null;
        boolean hasRight = node.getRight() != null;

        if (!hasLeft && !hasRight) return;

        String childPrefix = isRoot ? "  " : prefix + (isLeft ? "|   " : "    ");

        if (hasLeft) {
            System.out.println(childPrefix + "|");
            printTree(node.getLeft(), childPrefix, false, true);
        }

        if (hasRight) {
            System.out.println(childPrefix + "|");
            printTree(node.getRight(), childPrefix, false, false);
        }
    }

    public boolean searchWithSteps(E element, int[] steps) {
        Root<E> node = root;

        while (node != null) {
            steps[0]++;
            Comparable<E> comp = (Comparable<E>) element;
            int cmp = comp.compareTo(node.get());

            if (cmp == 0) return true;
            node = cmp < 0 ? node.getLeft() : node.getRight();
        }
        return false;
    }

    public boolean isEquilibrated(){
        if (isEmpty()){
            return false;
        }
        int hl = getLeftSubtree(root).getHeight();
        int hr = getRightSubtree(root).getHeight();
        return hr - hl <= 1 && hl - hr >= -1;
    }

}