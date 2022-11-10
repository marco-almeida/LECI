/**
 * BinaryTree
 */
public class BinaryTree {
    private Node root;
    private int size;

    public BinaryTree() {
        root = null;
    }

    public void add(int elem) {
        if (root == null) {
            root = new Node(elem);
        } else {
            addNode(root, elem);
        }
        size++;
    }

    public boolean remove(int elem) {
        if (!contains(elem)) {
            return false;
        } else {
            Node nodeToRemove = findNode(elem, root);
            System.out.println(nodeToRemove.leftChild);
            nodeToRemove = null;
            size--;
            return true;
        }
    }

    private Node removeNode(int elem, Node node, Node nodeDad) {
        if (root == null || !contains(elem)) {
            return null;
        }
        if (node.elem == elem) {
            if (node == nodeDad.leftChild) {
                if (node.leftChild != null) {
                    node = node.rightChild;
                } else {
                    node = node.leftChild;
                }
            } else {
                
            }
        }
        if (elem < node.elem) {
            return removeNode(elem, node.leftChild, node);
        } else {
            return removeNode(elem, node.rightChild, node);
        }
    }

    private void addNode(Node node, int elem) {
        if (elem >= node.elem) {
            if (node.rightChild == null) {
                node.rightChild = new Node(elem);
            } else {
                addNode(node.rightChild, elem);
            }
        } else {
            if (node.leftChild == null) {
                node.leftChild = new Node(elem);
            } else {
                addNode(node.leftChild, elem);
            }
        }
    }

    private Node findNode(int elem, Node node) {
        if (root == null || !contains(elem)) {
            return null;
        }
        if (node.elem == elem) {
            return node;
        }
        if (elem < node.elem) {
            return findNode(elem, node.leftChild);
        } else {
            return findNode(elem, node.rightChild);
        }
    }

    public boolean contains(int elem) {
        if (root == null) {
            return false;
        }
        return contains(elem, root);
    }

    private boolean contains(int elem, Node node) {
        if (node.elem == elem) {
            return true;
        }
        if (elem < node.elem) {
            if (node.leftChild != null) {
                return contains(elem, node.leftChild);
            } else {
                return false;
            }
        } else {
            if (node.rightChild != null) {
                return contains(elem, node.rightChild);
            } else {
                return false;
            }
        }
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public String toString() {
        String output = "";
        Node temp = root;
        while (temp != null) {
            output += temp.toString() + ", ";
            temp = temp.leftChild;
        }
        return output;
    }

}