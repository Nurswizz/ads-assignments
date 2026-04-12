package assignment2;

public class MyMinHeap<T extends Comparable<T>> {

    private MyArrayList<T> heap;

    public MyMinHeap() {
        heap = new MyArrayList<>();
    }

    public void add(T item) {
        heap.add(item);
        heapifyUp(heap.size() - 1);
    }

    public T peek() {
        if (heap.size() == 0) throw new RuntimeException();
        return heap.get(0);
    }

    public T remove() {
        if (heap.size() == 0) throw new RuntimeException();

        T root = heap.get(0);
        T last = heap.get(heap.size() - 1);

        heap.set(0, last);
        heap.removeLast();

        heapifyDown(0);

        return root;
    }

    private void heapifyUp(int index) {
        while (index > 0) {
            int parent = (index - 1) / 2;

            if (heap.get(index).compareTo(heap.get(parent)) >= 0)
                break;

            swap(index, parent);
            index = parent;
        }
    }

    private void heapifyDown(int index) {
        int size = heap.size();

        while (true) {
            int left = 2 * index + 1;
            int right = 2 * index + 2;
            int smallest = index;

            if (left < size &&
                    heap.get(left).compareTo(heap.get(smallest)) < 0)
                smallest = left;

            if (right < size &&
                    heap.get(right).compareTo(heap.get(smallest)) < 0)
                smallest = right;

            if (smallest == index) break;

            swap(index, smallest);
            index = smallest;
        }
    }

    private void swap(int i, int j) {
        T temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    public int size() {
        return heap.size();
    }

    public boolean isEmpty() {
        return heap.size() == 0;
    }
}