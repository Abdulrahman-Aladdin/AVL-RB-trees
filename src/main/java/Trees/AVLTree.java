package Trees;

public class AVLTree<T extends Comparable<T>> implements AVLTreeInterface<T> {
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
    private Node root = nill;
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
        x.height = 1 + Math.max(height(x.left), height(x.right));
    }

    private int getBalanceFactor(Node node) {
        if (node == nill) {
            return 0;
        }
        return height(node.left) - height(node.right);
    }

    private Node minValueNode(Node node) {
        Node current = node;
        while (current.left != nill) {
            current = current.left;
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

    private Node Balance (Node x, T value) {
        int balance = getBalanceFactor(x);
        if (balance > 1 && value.compareTo(x.left.value) < 0) { // left-left
            return rotateRight(x);
        } else if (balance > 1 && value.compareTo(x.left.value) > 0) { // left-right
            x.left = rotateLeft(x.left);
            return rotateRight(x);
        }else if (balance < -1 && value.compareTo(x.right.value) > 0) { // right-right
            return rotateLeft(x);
        } else if (balance < -1 && value.compareTo(x.right.value) < 0) { // right-left
            x.right = rotateRight(x.right);
            return rotateLeft(x);
        }
        return x;
    }

    public void insert (T value) {
        this.root = this.insertNode(this.root, value);
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

        boolean found = false;
        this.root = delete(this.root, value, found);
        return found;
    }

    private Node delete(Node node,T value, boolean found) {
        if (node == nill) {
            return nill;
        }

        if (value.compareTo(node.value) < 0) {
            node.left = delete(node.left, value, found);
        } else if (value.compareTo(node.value) > 0) {
            node.right = delete(node.right, value, found);
        } else {
            found = true;
            if (node.left == nill || node.right == nill) {
                Node temp = nill;
                if (temp == node.left) {
                    temp = node.right;
                } else {
                    temp = node.left;
                }

                if (temp == nill) {
                    node = nill;
                } else {
                    node = temp;
                }
            }
            else {
                Node temp = minValueNode(node.right);
                node.value = temp.value;
                node.right = delete(node.right, temp.value, found);
            }
        }

        if (node == nill) {
            return nill;
        }

        updateHeight(node);
        int balance = getBalanceFactor(node);

        if (balance > 1 && getBalanceFactor(node.left) >= 0) {
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

    public T search(T value) {
        return (T) nill;
    }

    public int getHeight() {
        return this.root.height;
    }

    public void printInOrder () {
        inOrder (this.root);
        System.out.println();
    }

}
