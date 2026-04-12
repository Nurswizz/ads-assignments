package assignment2;

import assignment2.interfaces.MyList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedList<T> implements MyList<T> {

    private class Node {
        T data;
        Node next;
        Node prev;

        Node(T data) {
            this.data = data;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    @Override
    public void add(T item) {
        addLast(item);
    }

    @Override
    public void addFirst(T item) {
        Node newNode = new Node(item);

        if (size == 0) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        size++;
    }

    @Override
    public void addLast(T item) {
        Node newNode = new Node(item);

        if (size == 0) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    @Override
    public void add(int index, T item) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException();

        if (index == 0) {
            addFirst(item);
            return;
        }
        if (index == size) {
            addLast(item);
            return;
        }

        Node current = getNode(index);
        Node newNode = new Node(item);

        Node prev = current.prev;

        prev.next = newNode;
        newNode.prev = prev;

        newNode.next = current;
        current.prev = newNode;

        size++;
    }

    @Override
    public void set(int index, T item) {
        getNode(index).data = item;
    }

    @Override
    public T get(int index) {
        return getNode(index).data;
    }

    @Override
    public T getFirst() {
        if (size == 0) throw new NoSuchElementException();
        return head.data;
    }

    @Override
    public T getLast() {
        if (size == 0) throw new NoSuchElementException();
        return tail.data;
    }

    private Node getNode(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();

        Node current;

        // optimization: start from head or tail
        if (index < size / 2) {
            current = head;
            for (int i = 0; i < index; i++)
                current = current.next;
        } else {
            current = tail;
            for (int i = size - 1; i > index; i--)
                current = current.prev;
        }

        return current;
    }

    @Override
    public void remove(int index) {
        Node node = getNode(index);

        if (node.prev != null)
            node.prev.next = node.next;
        else
            head = node.next;

        if (node.next != null)
            node.next.prev = node.prev;
        else
            tail = node.prev;

        size--;
    }

    @Override
    public void removeFirst() {
        if (size == 0) throw new NoSuchElementException();
        remove(0);
    }

    @Override
    public void removeLast() {
        if (size == 0) throw new NoSuchElementException();
        remove(size - 1);
    }

    @Override
    public void sort() {
        // simple bubble sort (same limitation: requires Comparable)
        if (size < 2) return;

        for (int i = 0; i < size - 1; i++) {
            Node current = head;

            for (int j = 0; j < size - i - 1; j++) {
                Comparable a = (Comparable) current.data;
                Comparable b = (Comparable) current.next.data;

                if (a.compareTo(b) > 0) {
                    T temp = current.data;
                    current.data = current.next.data;
                    current.next.data = temp;
                }
                current = current.next;
            }
        }
    }

    @Override
    public int indexOf(Object object) {
        Node current = head;
        int index = 0;

        while (current != null) {
            if (current.data.equals(object)) return index;
            current = current.next;
            index++;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object object) {
        Node current = tail;
        int index = size - 1;

        while (current != null) {
            if (current.data.equals(object)) return index;
            current = current.prev;
            index--;
        }
        return -1;
    }

    @Override
    public boolean exists(Object object) {
        return indexOf(object) != -1;
    }

    @Override
    public Object[] toArray() {
        Object[] arr = new Object[size];
        Node current = head;

        for (int i = 0; i < size; i++) {
            arr[i] = current.data;
            current = current.next;
        }
        return arr;
    }

    @Override
    public void clear() {
        head = tail = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (!hasNext()) throw new NoSuchElementException();
                T data = current.data;
                current = current.next;
                return data;
            }
        };
    }
}