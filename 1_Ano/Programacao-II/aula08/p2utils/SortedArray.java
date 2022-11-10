package p2utils;

import java.util.Arrays;

public class SortedArray<E extends Comparable<E>> {
    private int size = 0;
    private E[] array;

    @SuppressWarnings("unchecked")
    public SortedArray(int size) {
        array = (E[]) new Comparable[size];
    }

    /**
     * @return Number of elements in the list
     */
    public int size() {
        return size;
    }

    /**
     * Checks if the list is empty.
     * 
     * @return {@code true} if list empty, otherwise {@code false}.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == array.length;
    }

    /**
     * @return First element in the list
     */
    public E first() {
        assert !isEmpty() : "empty!";
        return array[0];
    }

    /**
     * Inserts a new element in the list.
     * 
     * @param e the element to be inserted
     */
    public void insert(E e) {
        insert(e, 0);
        size++;
    }

    private void insert(E e, int i) {
        if (array[i] == null) {
            array[i] = e;
            return;
        }
        if (e.compareTo(array[i]) < 0) {
            E temp = array[i];
            array[i] = e;
            insert(temp, i + 1);
            return;
        }
        insert(e, i + 1);
    }

    /**
     * Removes the first element in the list.
     */
    public void removeFirst() {
        assert !isEmpty() : "empty!";
        array = Arrays.copyOfRange(array, 1, size);
        size--;
    }

    /**
     * Checks if the list is sorted.
     * 
     * @return {@code true} if sorted, {@code false} otherwise
     */
    public boolean isSorted() {
        for (int i = 0; i < size - 1; ++i) {
            if (array[i].compareTo(array[i + 1]) > 0)
                return false;
        }
        return true;
    }

    /**
     * Checks if the given element exists in the list.
     * 
     * @param e an element
     * @return {@code true} if the element exists and {@code false} otherwise
     */
    public boolean contains(E e) {
        if (isEmpty())
            return false;
        for (int i = 0; i < size; i++) {
            if (array[i].equals(e)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Prints SortedList.
     *
     */
    public String toString() {
        if (size == 0)
            return "[]";
        String ret = "" + array[0];
        for (int i = 1; i < size; i++) {
            ret += ", " + array[i];
        }
        return "[" + ret + "]";
    }

    public E get(int i) {
        return array[i];
    }

    /**
     * Merges two lists.
     */
    public SortedArray<E> merge(SortedArray<E> list) {
        SortedArray<E> newList = new SortedArray<E>(size + list.size);
        merge(newList, this, 0);
        merge(newList, list, 0);
        return newList;
    }

    private void merge(SortedArray<E> newArray, SortedArray<E> array, int i) {
        if (array.size() == i)
            return;
        newArray.insert(array.get(i));
        merge(newArray, array, i + 1);
    }

}
