package OOP;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class TestMyQueue {

    MyQueue queue;

    @Before
    public void setup() {
        queue = new MyQueue();
    }

    @Test
    public void testIsEmpty() {
        assertTrue(queue.isEmpty());
    }

    @Test
    public void testOffer() {
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        assertEquals(3, queue.size());
    }

    @Test
    public void testPeek() {
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        assertEquals(1, queue.peek());
    }

    @Test
    public void testPoll() {
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        assertEquals(1, queue.poll());
        assertEquals(2, queue.size());

        assertEquals(2, queue.poll());
        assertEquals(1, queue.size());

        assertEquals(3, queue.poll());
        assertEquals(0, queue.size());


    }

    @Test
    public void testSize() {
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.poll();
        queue.poll();
        queue.poll();
        assertEquals(0, queue.size());
    }

    @After
    public void cleanup() {
        queue = null;
    }
}
