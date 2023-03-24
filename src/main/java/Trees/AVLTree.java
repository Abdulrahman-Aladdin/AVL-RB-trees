package Trees;

public class AVLTree<T extends Comparable<T>> implements IBST<T> {
    private class Node {
        Node right, left;
        T value;
        int height;

        Node () {
            this.height = 0;
        }

        Node (T value) {
            this.left = nill;
            this.right = nill;
            this.value = value;
            this.height = 1;
        }
    }

    private final Node nill = new Node();
    public Node root = nill;
    private int size = 0;

    private Node insertNode (Node node, T value) {
        if (node == nill) {
            this.size++;
            return new Node(value);
        }
        if (value.compareTo(node.value) < 0) { // value < node.value
            node.left = insertNode(node.left, value);
        } else if (value.compareTo(node.value) > 0) { // value > node.value
            node.right = insertNode(node.right, value);
        } else {
            return node;
        }
        updateHeight(node);
        return Balance(node);
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
        x.height = 1 + Math.max(height(x.left), height(x.right));
    }

    private int getBalanceFactor(Node node) {
        if (node == nill) {
            return 0;
        }
        return height(node.left) - height(node.right);
    }

    public T getMaxValue(Node node){
        return maxValueNode(node).value;
    }
    public T getMinValue(Node node){
        return minValueNode(node).value;
    }
    private Node minValueNode(Node node) {
        Node current = node;
        while (current.left != nill) {
            current = current.left;
        }
        return current;
    }
    private Node maxValueNode(Node node) {
        Node current = node;
        while (current.right != nill) {
            current = current.right;
        }
        return current;
    }

    private int height(Node node) {
        if (node == nill) {
            return 0;
        }
        return node.height;
    }


    private void inOrder (Node x) {
        if (x != nill) {
            inOrder(x.left);
            System.out.print(x.value + " ");
            inOrder(x.right);
        }
    }

    private Node Balance (Node node) {
        int balance = getBalanceFactor(node);

        if (balance > 1 && getBalanceFactor(node.left) >= 0) { // left-left
            return rotateRight(node);
        }

        if (balance > 1 && getBalanceFactor(node.left) < 0) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        if (balance < -1 && getBalanceFactor(node.right) <= 0) {
            return rotateLeft(node);
        }

        if (balance < -1 && getBalanceFactor(node.right) > 0) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }
        return node;
    }

    public boolean insert (T value) {
        int sz = this.size;
        this.root = this.insertNode(this.root, value);
        return this.size > sz;
    }

    public int size () {
        return this.size;
    }

    public boolean isEmpty () {
        return this.size == 0;
    }


    public boolean delete(T value) {
        if (this.root == nill) {
            return false;
        }
        if(!search(value)) {
            return false;
        }
        this.root = deleteNode(this.root, value);
        this.size--;
        return true;
    }

    private Node deleteNode(Node node, T value) {
        if (node == nill) {
            return nill;
        }

        if (value.compareTo(node.value) < 0) {
            node.left = deleteNode(node.left, value);
        }
        else if (value.compareTo(node.value) > 0) {
            node.right = deleteNode(node.right, value);
        }
        else {
            if (node.left == nill || node.right == nill) {
                Node temp = nill;
                if (temp == node.left) {
                    temp = node.right;
                } else {
                    temp = node.left;
                }
                node = temp;
            }
            else {
                Node temp = minValueNode(node.right);
                node.value = temp.value;
                node.right = deleteNode(node.right, temp.value);
            }
        }

        if (node == nill) {
            return nill;
        }

        updateHeight(node);
        return Balance(node);
    }

    public boolean search(T value) {
        Node currentNode = this.root;
        while (currentNode != nill) {
            if (value.compareTo(currentNode.value) == 0) {
                return true;
            } else if (value.compareTo(currentNode.value) < 0) {
                currentNode = currentNode.left;
            } else {
                currentNode = currentNode.right;
            }
        }
        return false;
    }

    public int height() {
        return height(this.root);
    }

    public void printInOrder () {
        inOrder (this.root);
        System.out.println();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        toString(root, sb);
        return sb.toString();
    }

    private void toString(Node node, StringBuilder sb) {
        if (node != nill) {
            sb.append(node.value).append(" (").append(node.left.height-node.right.height).append(")\n");
            toString(node.left, sb);
            toString(node.right, sb);
        }
    }

    public boolean isBalanced() {
        return isBalanced(this.root);
    }

    private boolean isBalanced(Node node) {
        if (node != nill) {
            if (height(node.left)-height(node.right)>1||height(node.left)-height(node.right)<-1)
                return false;
             return isBalanced(node.left) && isBalanced(node.right);
        }
        return true;
    }

}
