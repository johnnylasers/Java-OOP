package OOP;

import java.util.NoSuchElementException;

public class MyQueue<T> {
    // Use cases:
    // boolean isEmpty();
    // int size();
    // void offer();
    // int poll();
    // int peek();
    //

    // data fields:
    private int size;
    private Node head;
    private Node tail;

    //initialization
    public MyQueue() {
        size = 0;
        head = tail = null;
    }

    // Methods:
    public synchronized void offer(T val) {
        Node node = new Node(val);

        if (size == 0) {
            head = tail = node;
        } else {
            tail.next = node;
            tail = tail.next;
        }
        size++;
    }

    public synchronized T peek() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        return head.val;
    }

    public synchronized T poll() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        T output = head.val;
        head = head.next;
        size--;

        if (isEmpty()) tail = null; //Actually, at this point, head == tail

        return output;
    }

    public synchronized int size() {
        return size;
    }

    public synchronized boolean isEmpty() {
        return size == 0;
    }

    private class Node {
        private T val;
        private Node next;

        // it's better to be package-private than being public
        Node(T val) {
            this.val = val;
            this.next = null;
        }
    }

}


