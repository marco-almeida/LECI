public class Node {
    int elem;
    Node leftChild;
    Node rightChild;

    public Node(int elem, Node leftChild, Node rightChild) {
        this.elem = elem;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    public Node(int elem) {
        this.elem = elem;
        this.leftChild = null;
        this.rightChild = null;
    }

    @Override
    public String toString() {
        return String.valueOf(elem);
    }

    
}
