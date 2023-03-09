package guiao01;

public class Node {
    String value;
    Node left;
    Node right;

    Node(String value) {
        this.value = value;
        right = null;
        left = null;
    }

    @Override
    public String toString() {
        return "Node " + value;
    }

}
