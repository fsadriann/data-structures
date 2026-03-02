package edu.fsadriann.model.priorityqueue;

import edu.fsadriann.model.queue.Queue;

public interface PriorityQueue<E> extends Queue<E> {
    boolean insert (E element, int index);
}
