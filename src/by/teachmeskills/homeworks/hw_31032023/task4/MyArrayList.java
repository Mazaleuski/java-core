package by.teachmeskills.homeworks.hw_31032023.task4;

import java.util.Arrays;

public class MyArrayList<E> {
    private int size = 0;
    private static final int DEFAULT_CAPACITY = 7;
    private Object[] elements;

    public MyArrayList() {
        this.elements = new Object[DEFAULT_CAPACITY];
    }

    public MyArrayList(int capacity) {
        this.elements = new Object[capacity];
    }

    public E get(int index) {
        indexCheck(index);
        return (E) elements[index];
    }

    public void add(int index, E element) {
        indexCheck(index);
        ensureCapacity();
        for (int i = size - 1; i >= index; i--) {
            elements[i + 1] = elements[i];
        }
        elements[index] = element;
        size++;
    }

    public E remove(int index) {
        indexCheck(index);
        Object item = elements[index];
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        size--;
        return (E) item;
    }

    public void clear() {
        final Object[] tmp = elements;
        for (int to = size, i = size = 0; i < to; i++)
            tmp[i] = null;
    }

    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    private int size() {
        return size;
    }

    private void ensureCapacity() {
        int newSize = elements.length * 2;
        Arrays.copyOf(elements, newSize);
    }

    private int indexOf(Object o) {
        return indexOfRange(o, 0, size);
    }

    int indexOfRange(Object o, int start, int end) {
        Object[] es = elements;
        if (o == null) {
            for (int i = start; i < end; i++) {
                if (es[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = start; i < end; i++) {
                if (o.equals(es[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    private void indexCheck(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size " + size());
        }
    }
}


