package p2utils;

class Node<E> {
    String key;
    final E elem;
    Node<E> leftChild;
    Node<E> rightChild;

    public Node(E elem, Node<E> leftChild, Node<E> rightChild) {
        this.elem = elem;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    public Node(String key, E elem) {
        this.elem = elem;
        this.key = key;
        this.leftChild = null;
        this.rightChild = null;
    }

}
