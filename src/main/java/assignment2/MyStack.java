package assignment2;

import assignment2.interfaces.MyList;

public class MyStack<T extends Comparable<T>>  {

    private MyList<T> list;

    public MyStack() {
        list = new MyArrayList<>();
    }

    public void push(T item) {
        list.addLast(item);
    }

    public T pop() {
        T value = list.getLast();
        list.removeLast();
        return value;
    }

    public T peek() {
        return list.getLast();
    }

    public boolean isEmpty() {
        return list.size() == 0;
    }

    public int size() {
        return list.size();
    }
}