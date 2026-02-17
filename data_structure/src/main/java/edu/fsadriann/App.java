package edu.fsadriann;

import edu.fsadriann.linkedlist.singly.singly.LinkedList;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello Lists");

        LinkedList<Integer> list = new LinkedList<>();

        list.add(10);
        list.add(2);
        list.add(130);
        list.add(1044444);

        list.remove(1);

    }
}
