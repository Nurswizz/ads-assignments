package assignment3;

public class MyHashTable<K, V> {

    private class HashNode<K, V> {
        private K key;
        private V value;
        private HashNode<K, V> next;
        public HashNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "HashNode{" + "key=" + key + ", value=" + value + '}';
        }
    }

    private HashNode<K, V>[] chainArray;
    private int M;
    private int size = 0;

    public MyHashTable() {
        this.M = 11;
        chainArray = (HashNode<K, V>[]) new HashNode[M];
    }

    public MyHashTable(int M) {
        this.M = M;
        chainArray = (HashNode<K, V>[]) new HashNode[M];
    }

    private int hash(K key) {
        if (key == null) {
            throw new IllegalArgumentException("key can't be null");
        }
        return (key.hashCode() & 0x7FFFFFFF) % M;
    }

    public void put(K key, V value) {
        int h = hash(key);
        HashNode<K, V> temp = chainArray[h];

        while (temp != null) {
            if (temp.key.equals(key)) {
                temp.value = value; // update existing
                return;
            }
            temp = temp.next;
        }

        HashNode<K, V> newNode = new HashNode<>(key, value);
        newNode.next = chainArray[h];
        chainArray[h] = newNode;
        size++;
    }

    public V get(K key) {
        int h = hash(key);
        HashNode<K, V> temp = chainArray[h];

        while (temp != null) {
            if (temp.key.equals(key)) {
                return temp.value;
            }
            temp = temp.next;
        }

        return null;
    }
    public V remove(K key) {
        int h = hash(key);

        HashNode<K, V> temp = chainArray[h];
        HashNode<K, V> prev = null;

        while (temp != null) {
            if (temp.key.equals(key)) {
                if (prev == null) {
                    chainArray[h] = temp.next;
                } else {
                    prev.next = temp.next;
                }

                size--;
                return temp.value;
            }

            prev = temp;
            temp = temp.next;
        }

        return null;
    }

    public boolean contains(V value) {
        for (HashNode<K, V> temp : chainArray) {
            while (temp != null) {
                if (temp.value.equals(value)) {
                    return true;
                }
                temp = temp.next;
            }
        }
        return false;
    }

    public K getKey(V value) {
        for (HashNode<K, V> temp : chainArray) {
            while (temp != null) {
                if (temp.value.equals(value)) {
                    return temp.key;
                }
                temp = temp.next;
            }
        }
        return null;
    }

    public void printBucketSizes() {
        for (int i = 0; i < M; i++) {
            int count = 0;
            HashNode<K, V> temp = chainArray[i];
            while (temp != null) {
                count++;
                temp = temp.next;
            }
            System.out.println("Bucket " + i + ": " + count + " elements");
        }
    }
}
