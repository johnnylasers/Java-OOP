package OOP;

import java.util.NoSuchElementException;

public class MyQueue {
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
    public void offer(int val) {
        Node node = new Node(val);

        if (size == 0) {
            head = tail = node;
        } else {
            tail.next = node;
            tail = tail.next;
        }
        size++;
    }

    public int peek() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        return head.val;
    }

    public int poll() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        int output = head.val;
        head = head.next;
        size--;
        return output;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private class Node {
        private int val;
        private Node next;

        // it's better to be package-private than being public
        Node(int val) {
            this.val = val;
            this.next = null;
        }
    }

}


