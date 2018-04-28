package OOP.hashmap;

import java.util.Arrays;

public class MyHashMap<K, V> {

    // Define the essential element, Entry<K, V>, as a static nested class
    static class Entry<K, V> {
        final K key;
        V value;
        Entry<K, V> next;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
            next = null;
        }

        K getKey() {return key;}

        public V getValue() {return value;}
        public void setValue(V value) {this.value = value;}
    }

    // Define the data fields for the HashMap
    private Entry<K, V>[] array;
    private double load_factor;
    private int size;

    private static final int INIT_CAP = 16;
    private static final double LOAD_FACTOR = 0.75;

    public MyHashMap(int cap, double load_factor) {
        if (cap < 0 || load_factor < 0) {
            throw new IllegalArgumentException();
        }

        array = (Entry<K, V>[]) new Entry[cap];
        this.load_factor = load_factor;
        size = 0;
    }

    public MyHashMap() {
        this(INIT_CAP, LOAD_FACTOR);
    }


    // Define public API and private methods
    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    //find the proper bucket (with linked list head) first,
    //then traverse down the linked list to find matching key, if exists
    public boolean containsKey(K key) {
        if (key == null) return false;

        int index = getIndex(key);
        Entry<K, V> head = array[index];
        while (head != null) {
            if (equalKeys(head.getKey(), key)) {
                return true;
            }
            head = head.next;
        }
        return false;
    }

    //similar to the implementation in containsKey
    public V get(K key) {
        if (key == null) return null;

        int index = getIndex(key);
        Entry<K, V> head = array[index];
        while (head != null) {
            if (equalKeys(head.getKey(), key)) {
                return head.getValue();
            }
            head = head.next;
        }
        return null;
    }

    //return the value that is being updated, which includes null or a real value
    // case 1: entry already in map --> update entry's value
    // case 2: entry not in map --> insert in the proper bucket
    public V put(K key, V value) {
        //what if key is null?

        int index = getIndex(key);
        Entry<K, V> head = array[index];

        // case 1:
        while(head != null) {
            if (equalKeys(head.getKey(), key)) {
                V oldValue = head.getValue();
                head.setValue(value);
                return oldValue;
            }
            head = head.next;
        }

        //case 2:
        Entry<K, V> newHead = new Entry<>(key, value);

        //just like in rehashing, we insert at head
        newHead.next = array[index];
        array[index] = newHead;
        size++; //DON'T FORGET this step!!!

        //since we increased the size, we need to check if we need to rehash into a larger-sized array
        if (needRehashing()) {
            rehashing();
        }

        return null;
    }

    // return the value that's being removed
    // use two pointers, prev and cur, to track where we are
    // cur to indicate the current node and target
    // prev is one node behind and is used to form connection with cur.next after cur being deleted
    public V remove(K key) {
        if (key == null) return null;

        int index = getIndex(key);
        Entry<K, V> cur = array[index];
        Entry<K, V> prev = null;

        while (cur != null) {

            if (equalKeys(cur.getKey(), key)) {
                if (prev == null) { // found the target node at the head of linked list
                    array[index] = cur.next;
//                    return cur.getValue();
                } else {
                    prev.next = cur.next;
//                    cur.next = null; //no need to have this line here.
                    System.out.println(prev.getValue());
//                    return cur.getValue();
                }
                size--; //DON'T FORGET to reduce size upon successful deletion!
                return cur.getValue();
            }
            prev = cur;
            cur = cur.next;
        }
        return null;
    }

    public void clear() {
        Arrays.fill(array, null);
        size = 0;
    }

    private int hash(K key) {
        if (key == null) return 0; // don't forget to check null keys

        int hashCode = key.hashCode();
        return hashCode & 0x7FFFFFFF;
    }

    private int getIndex(K key) {
        return hash(key) % array.length;
    }

    private boolean equalKeys(K key1, K key2) {
        return key1 == key2 || key1 != null && key1.equals(key2);
    }

    private boolean needRehashing() {
        return size > array.length * load_factor;
    }

    //new a double-sized array
    //for each entry in the old array
    // do the put() operation in the new array
    private void rehashing() {

        Entry<K, V>[] oldArray = array;

        // double the length
        array = (Entry<K, V>[]) new Entry[(oldArray.length << 1)]; //should be old.length * 2, not array.length!!!

        for (Entry<K, V> oldEntry : oldArray) {
            //now we need to map every node in the 以 old entry为头的 linked list into the new array location
            while (oldEntry != null) {
                Entry<K, V> next = oldEntry.next;

                K key = oldEntry.getKey();
                int index = getIndex(key);
                // the next two lines are a "insert at head" operation of a linked list, which is O(1)
                // If we always insert at tail, then it becomes O(N)
                oldEntry.next = array[index];
                array[index] = oldEntry;

                oldEntry = next;
            }
        }
    }

}
