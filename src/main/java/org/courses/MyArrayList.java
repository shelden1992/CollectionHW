package org.courses;


import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class MyArrayList<T> implements Iterable<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static int ACTUAL_CAPACITY = 10;
    private T[] array;
    private int size = 0;

    public MyArrayList() {
        array = (T[]) new Object[DEFAULT_CAPACITY];
    }

    public MyArrayList(int initialCapacity) {
        array = (T[]) new Object[initialCapacity];
    }

    public void add(T t) {
        if (growthRequired()) {
            grow();
        }
        array[size++] = t;
    }

    public void add(int index, T element) {
        if (growthRequired()) {
            grow();
        }

        if (size - index >= 0)
            System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = element;
        size++;
    }

    private boolean growthRequired() {
        if (size + 1 >= ACTUAL_CAPACITY) {
            grow();
            return true;
        }
        return false;
    }

    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyListIterator();

    }

    private void grow() {
        T[] localArray = (T[]) new Object[getActualCapacity(ACTUAL_CAPACITY)];
        System.arraycopy(array, 0, localArray, 0, size);
        array = localArray;

    }

    private int getActualCapacity(int actualCapacity) {
        ACTUAL_CAPACITY = actualCapacity + actualCapacity / 2 + 1;
        return ACTUAL_CAPACITY;
    }

    public T get(int index) {
        if (size < index && index > 0) {
            throw new IndexOutOfBoundsException();
        }
        return array[index];
    }

    @Override
    public String toString() {
        return Arrays.toString(array);
    }

    void addAll(T... t) {
        Arrays.stream(t).forEach(this::add);
    }

    void addAll(Collection<T> collection) {
        collection.stream().forEach(f -> add(f));
    }

    private T remove(int index) {
        T removeItem = array[index];
        for (int i = index; i < size() - 1; i++) {
            array[i] = array[i + 1];
        }
        size--;
        return removeItem;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        size = 0;
        array = (T[]) new Object[DEFAULT_CAPACITY];
        ACTUAL_CAPACITY = DEFAULT_CAPACITY;
    }

    private class MyListIterator implements Iterator<T> {
        private int current = 0;

        public boolean hasNext() {
            return current < size();
        }

        public T next() {
            if (!hasNext()) throw new java.util.NoSuchElementException();
            return array[current++];
        }

//        public void remove() {
//            MyArrayList.this.remove(--current);
//        }
    }
}
