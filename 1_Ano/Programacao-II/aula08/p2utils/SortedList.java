package p2utils;

public class SortedList<E extends Comparable<E>> {
    // private attributes
    private Node<E> first = null;
    private int size = 0;

    public SortedList() {
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

    /**
     * @return First element in the list
     */
    public E first() {
        assert !isEmpty() : "empty!";
        return first.elem;
    }

    /**
     * Inserts a new element in the list.
     * 
     * @param e the element to be inserted
     */
    public void insert(E e) {
        first = insert(first, e);
        size++;
    }

    private Node<E> insert(Node<E> n, E e) {
        if (n == null || e.compareTo(n.elem) < 0)
            return new Node<E>(e, n);
        n.next = insert(n.next, e);
        return n;
    }

    /**
     * Removes the first element in the list.
     */
    public void removeFirst() {
        assert !isEmpty() : "empty!";
        first = first.next;
        size--;
    }

    /**
     * Checks if the list is sorted.
     * 
     * @return {@code true} if sorted, {@code false} otherwise
     */
    public boolean isSorted() {
        if (size < 2)
            return true;
        return isSorted(first, first.next);
    }

    private boolean isSorted(Node<E> prev, Node<E> n) {
        if (n == null)
            return true;
        if (n.elem.compareTo(prev.elem) < 0)
            return false;
        return isSorted(n, n.next);
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
        return contains(first, e);
    }

    private boolean contains(Node<E> n, E e) {
        if (n == null)
            return false;
        if (n.elem == null)
            return e == null; // dispensável, se impedirmos elem==null
        if (n.elem.equals(e))
            return true;
        return contains(n.next, e);
    }

    /**
     * Prints SortedList.
     *
     */
    public String toString() {
        if (size == 0) {
            return "[]";
        }
        return "[" + toString(first);
    }

    private String toString(Node<E> n) {
        if (n.next == null) {
            return n.elem + "]";
        } else {
            return n.elem + ", " + toString(n.next);
        }
    }

    /**
     * Merges two lists.
     */
    public SortedList<E> merge(SortedList<E> list) {
        SortedList<E> newList = new SortedList<E>();
        merge(first, newList);
        merge(list.first, newList);
        return newList;
    }

    private void merge(Node<E> n, SortedList<E> newList) {
        if (n == null) {
            return;
        }
        newList.insert(n.elem);
        merge(n.next, newList);

    }

}
