package OOP.heap;

public class TestMinHeap {


    public static void main(String[] args) {

        MinHeap minHeap = new MinHeap();

        int[] array = {2, 4, 6, 8, 10};
        MinHeap minHeap2 = new MinHeap(array);

        System.out.println(minHeap.size());
        System.out.println(minHeap.isEmpty());
        System.out.println(minHeap.isFull());

        minHeap.offer(3);
        minHeap.offer(7);
        minHeap.offer(6);

        System.out.println(minHeap.size());
        System.out.println(minHeap.peek());
        System.out.println(minHeap.poll());
        System.out.println(minHeap.size());
        System.out.println(minHeap.peek());
        System.out.println(minHeap.update(0, 9));
        System.out.println(minHeap.peek());
        System.out.println(minHeap.size());
        System.out.println(minHeap.poll());
        System.out.println(minHeap.peek());
        System.out.println(minHeap.isFull());
        System.out.println(minHeap.isEmpty());

        //testing minHeap2
        System.out.println(minHeap2.size());
        System.out.println(minHeap2.peek());
        System.out.println(minHeap2.update(0, 12));
        System.out.println(minHeap2.peek());
        System.out.println(minHeap2.poll());
        System.out.println(minHeap2.peek());
        System.out.println(minHeap2.size());
    }
}
