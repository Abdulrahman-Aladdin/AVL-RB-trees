package Trees;

public class AVLTree {
    private class Node {
        Node right, left;
        Double value;
        int height;

        Node () {
            this.height = 0;
        }

        Node (Double value) {
            this.left = nill;
            this.right = nill;
            this.value = value;
            this.height = 1;
        }
    }

    private final Node nill = new Node();
    private Node root = nill;
    private int size = 0;

    private Node insertNode (Node node, double value) {
        if (node == nill) {
            this.size++;
            return new Node(value);
        }
        if (node.value > value) {
            node.left = insertNode(node.left, value);
        } else if (node.value < value) {
            node.right = insertNode(node.right, value);
        } else {
            return node;
        }
        updateHeight(node);
        return Balance(node, value);
    }

    private Node rotateRight (Node x) {
        Node y = x.left;
        x.left = y.right;
        y.right = x;
        updateHeight(x);
        updateHeight(y);
        return y;
    }

    private Node rotateLeft (Node x) {
        Node y = x.right;
        x.right = y.left;
        y.left = x;
        updateHeight(x);
        updateHeight(y);
        return y;
    }

    private void updateHeight (Node x) {
        x.height = 1 + Math.max(x.left.height, x.right.height);
    }

    private int isBalanced (Node x) {
        return x.left.height - x.right.height;
    }

    private void inOrder (Node x) {
        if (x != nill) {
            inOrder(x.left);
            System.out.print(x.value + " ");
            inOrder(x.right);
        }
    }

    private Node Balance (Node x, double value) {
        int balance = isBalanced(x);
        if (balance > 1 && value < x.left.value) { // left-left
            return rotateRight(x);
        } else if (balance > 1 && value > x.left.value) { // left-right
            x.left = rotateLeft(x.left);
            return rotateRight(x);
        }else if (balance < -1 && value > x.right.value) { // right-right
            return rotateLeft(x);
        } else if (balance < -1 && value < x.right.value) { // right-left
            x.right = rotateRight(x.right);
            return rotateLeft(x);
        }
        return x;
    }

    public void insert (Double value) {
        this.root = this.insertNode(this.root, value);
    }

    public int size () {
        return this.size;
    }

    public boolean isEmpty () {
        return this.size == 0;
    }

    public int height () {
        return this.root.height;
    }

    public void PrintInOrder () {
        inOrder (this.root);
        System.out.println();
    }

}
