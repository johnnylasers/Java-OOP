package OOP.heap;

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

    public boolean offer() {

    }

    public int poll() {

    }

    public boolean update() {

    }

    private void heapify() {
        for (int i = size / 2 - 1; i >= 0; i--) {
            percolateDown(i);
        }
    }

    private void percolateDown(int index) {

    }

    private void percolateUp(int index) {

    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
