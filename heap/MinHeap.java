package OOP.heap;

import java.util.Arrays;

/**
 * for simplicity, implement a minHeap that support Integer first
 */
public class MinHeap {

    private int[] heapArray;
    private int size;

    private static final int DEFAULT_CAP = 16;

    public MinHeap() {
        this(DEFAULT_CAP);
    }

    public MinHeap(int cap) {
        if (cap < 0) {
            throw new IllegalArgumentException("Capacity can't be less than 0!");
        }

        heapArray = new int[cap];
        size = 0;
    }

    public MinHeap(int[] array) {
        if (array == null) {
            throw new IllegalArgumentException("Input array can't be null!");
        }

        heapArray = array;
        size = heapArray.length;
        heapify();
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == heapArray.length;
    }

    public int peek() {
        if (size == 0) {
            throw new NullPointerException("Heap is empty!");
        }
        return heapArray[0];
    }

    public void offer(int e) {
        if (size == heapArray.length) {
            int[] oldArray = heapArray;
            heapArray = new int[oldArray.length * 2];
            heapArray = Arrays.copyOfRange(oldArray, 0, oldArray.length - 1);
        }
        heapArray[size] = e;
        size++; // must increment the size before calling percolateUp
                // b/c in percolateUp, the boundaries are based on the new size
        percolateUp(size - 1);
    }

    public int poll() {
        if (size == 0) {
            throw new NullPointerException("Illegal operation. Heap is empty!");
        }
        int toReturn = heapArray[0];
        heapArray[0] = heapArray[size - 1];
        // or swap(heapArray, 0, size - 1);
        size--;
        percolateDown(0);

        return toReturn;
    }

    //return the original value
    // update the element at target index with a new value
    // don't forget to percolate
    public int update(int targetIndex, int newValue) {
        if (targetIndex < 0 || targetIndex >= heapArray.length) {
            throw new ArrayIndexOutOfBoundsException("Target index out of bounds!");
        }
        int oldValue = heapArray[targetIndex];
        heapArray[targetIndex] = newValue;
        if (newValue > oldValue) {
            percolateDown(targetIndex);
        } else if (newValue < oldValue){
            percolateUp(targetIndex);
        }
        return oldValue;
    }

    private void heapify() {
        for (int i = size / 2 - 1; i >= 0; i--) {
            percolateDown(i);
        }
    }

    private void percolateDown(int index) {

        while (index <= (size - 2) / 2) { // <==> 2 * index + 1 <= size - 1
            int leftChildIndex = 2 * index + 1;
            int rightChildIndex = 2 * index + 2;

            //initialize swapCandidate with leftChildIndex
            // at this point, we don't know yet if rightChildIndex is out of bounds
            int swapCandidate = leftChildIndex;

            if (rightChildIndex <= size - 1 && heapArray[leftChildIndex] > heapArray[rightChildIndex]) {
                swapCandidate = rightChildIndex;
            }

            if (heapArray[index] > heapArray[swapCandidate]) {
                swap(heapArray, index, swapCandidate);
            } else {
                break;
            }

            index = swapCandidate;
        }
    }

    private void percolateUp(int index) {
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            if (heapArray[index] < heapArray[parentIndex]) {
                swap(heapArray, parentIndex, index);

            } else {
                break;
            }
            index = parentIndex;
        }
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
