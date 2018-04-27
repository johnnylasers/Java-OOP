package OOP.hashmap;

public class MyHashMap<K, V> {

    // Define the essential element, Entry<K, V>, as a static nested class
    static class Entry<K, V> {
        final K key;
        V value;
        Entry<K, V> next;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
            next = null;
        }

        K getKey() {return key;}
        public void setValue(V value) {this.value = value;}
        public V getValue() {return value;}
    }

    // Define the data fields for the HashMap
    private Entry<K, V>[] array;
    private int size;
    private static final double LOAD_FACTOR = 0.75;
    private static final int INIT_CAP = 16;

    public MyHashMap(int cap, double load_factor) {
        if (cap < 0 || load_factor < 0.0) {
            throw new IllegalArgumentException();
        }
        array = (Entry<K, V>[]) new Entry[cap];
        size = 0;


    }
}
