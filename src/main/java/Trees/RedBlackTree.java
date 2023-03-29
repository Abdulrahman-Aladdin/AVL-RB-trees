package Trees;

import org.apache.commons.lang3.tuple.Triple;

import java.util.ArrayList;
import java.util.List;

public class RedBlackTree<T extends Comparable<T>> implements IBST<T> {

    public enum Color {
        BLACK, RED
    }

    private class Node {

        private T value;
        private Color color;
        private Node parent, leftChild, rightChild;


        Node(Color color, T value) {
            this.color = color;
            this.value = value;
            this.parent = null;
            this.leftChild = null;
            this.rightChild = null;
        }

        public Node getUncle() {

            Node grandParent = this.parent.parent;
            if (grandParent == null) {
                return null;
            }
            if (grandParent.leftChild != null && this.parent != grandParent.leftChild) {
                return grandParent.leftChild;
            } else if (grandParent.rightChild != null && this.parent != grandParent.rightChild) {
                return grandParent.rightChild;
            }
            return null;
        }


        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public Color getColor() {
            return color;
        }

        public void setColor(Color color) {
            this.color = color;
        }

        public Node getParent() {
            return parent;
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }

        public Node getLeftChild() {
            return leftChild;
        }

        public void setLeftChild(Node leftChild) {
            this.leftChild = leftChild;
        }

        public Node getRightChild() {
            return rightChild;
        }

        public void setRightChild(Node rightChild) {
            this.rightChild = rightChild;
        }
    }

    Node root, nil;
    int size = 0;

    public RedBlackTree() {
        this.root = null;
        this.nil = new Node(Color.BLACK, null);
    }

    public boolean insert(T value) {
        Node node = new Node(Color.RED, value);
        Node current = this.root, current2 = null;
        if (search(value)) {
            return false;
        }
        if (current == null) {
            this.root = node;
            this.root.setColor(Color.BLACK);
            size++;
            return true;
        }

        while (current != null) {
            current2 = current;
            if (current.getValue().compareTo(value) > 0) {
                current = current.getLeftChild();

            } else if (current.getValue().compareTo(value) < 0) {
                current = current.getRightChild();
            } else {
                return false;
            }
        }
        if (current2.getValue().compareTo(value) > 0) {
            current2.leftChild = node;
            node.parent = current2;
        } else {
            current2.rightChild = node;
            node.parent = current2;
        }
        size++;
        insertFixup(node);

        return true;
    }

    private void insertFixup(Node node) {

        if (node.getParent().getColor() == Color.BLACK) {
            return;
        }
        Node grandparent = node.getParent().getParent();
        if (grandparent == null) {
            return;
        }

        Node parent = node.getParent();
        Node uncle = node.getUncle();


        if (uncle != null && uncle.getColor() == Color.RED) {
            uncle.setColor(Color.BLACK);
            parent.setColor(Color.BLACK);
            //We then check if the grandparent is not root to recolor it
            //System.out.println(grandparent.parent.leftChild.color);
            if (grandparent != root) {
                grandparent.setColor(Color.RED);
                insertFixup(grandparent);
            }
            return;
        }
        if (parent.getRightChild() == node && grandparent.getRightChild() == parent) {

            grandparent.setColor(Color.RED);
            parent.setColor(Color.BLACK);
            rotateLeftAndAdjustParent(grandparent);
        } else if (parent.getLeftChild() == node && grandparent.getLeftChild() == parent) {
            grandparent.setColor(Color.RED);
            parent.setColor(Color.BLACK);
            rotateRightAndAdjustParent(grandparent);

        } else if (parent.getLeftChild() == node && grandparent.getRightChild() == parent) {
            node.parent.parent.setColor(Color.RED);
            rotateRightAndAdjustParent(parent);
            node.setColor(Color.BLACK);
            rotateLeftAndAdjustParent(node.parent);
        } else if (parent.getRightChild() == node && grandparent.getLeftChild() == parent) {
            node.parent.parent.setColor(Color.RED);
            rotateLeftAndAdjustParent(parent);
            node.setColor(Color.BLACK);
            rotateRightAndAdjustParent(node.parent);
        }

    }


    public boolean delete(T value) {

        Node node = search(value, this.root);
        if (node == null) return false;


        Node dbNode;
        Color deletedNodeColor = null;

        if (node.leftChild == null || node.rightChild == null) {
            dbNode = deleteNodeWithZeroOrOneChild(node);

            if (isBlack(node) && isRed(dbNode)) {
                dbNode.color = Color.BLACK;
                deletedNodeColor = Color.RED;
            } else if (isRed(node) && dbNode != null && isBlack(dbNode)) {
                deletedNodeColor = Color.RED;
            } else if (isBlack(node) && dbNode != null && isBlack(dbNode)) {
                deletedNodeColor = Color.BLACK;
            } else if (dbNode == null) {
                deletedNodeColor = Color.RED;
            }


        } else {
            Node inOrderSuccessor = findMaximum(node.leftChild);
            node.value = inOrderSuccessor.value;

            dbNode = deleteNodeWithZeroOrOneChild(inOrderSuccessor);

            if (isBlack(inOrderSuccessor) && isRed(dbNode)) {
                dbNode.color = Color.BLACK;
                deletedNodeColor = Color.RED;
            } else if (isRed(inOrderSuccessor) && dbNode != null && isBlack(dbNode)) {
                deletedNodeColor = Color.RED;
            } else if (isBlack(inOrderSuccessor) && dbNode != null && isBlack(dbNode)) {
                deletedNodeColor = Color.BLACK;
            } else if (dbNode == null) {
                deletedNodeColor = Color.RED;
            }
        }

        // case 1 (red leaf node) is handled since it goes
        // to deleteFixup() only if the deleted node was black
        if (deletedNodeColor == Color.BLACK) {
            deleteFixup(dbNode);

            if (dbNode == nil) {
                replaceParentsChild(dbNode.parent, dbNode, null);
            }
        }

        size--;
        return true;
    }

    private Node deleteNodeWithZeroOrOneChild(Node node) {


        // Node has ONLY a left child --> replace by its left child
        if (node.leftChild != null) {
            replaceParentsChild(node.parent, node, node.leftChild);
            return node.leftChild; // moved-up node
        }

        // Node has ONLY a right child --> replace by its right child
        else if (node.rightChild != null) {
            replaceParentsChild(node.parent, node, node.rightChild);
            return node.rightChild; // moved-up node
        }

        // Node has no children -->
        // * node is red --> just remove it
        // * node is black --> replace it by a temporary NIL node (needed to fix the R-B rules)
        else {
            Node newChild = node.color == Color.BLACK ? nil : null;
            replaceParentsChild(node.parent, node, newChild);
            return newChild;
        }
    }

    private void deleteFixup(Node db) {

        Node dbParent = db.parent;


        // case 2 : dbNode is root
        if (db == root) {
            db.color = Color.BLACK;
            return;
        }

        // case 3 : db sibling is black , sibling children are black (2 times for mirroring)
        // db is left
        if (dbParent.leftChild == db) {
            Node dbSibling = dbParent.rightChild;

            if (isBlack(dbSibling) && isBlack(dbSibling.leftChild) && isBlack(dbSibling.rightChild)) {
                dbSibling.color = Color.RED;

                if (dbParent.color == Color.RED) dbParent.color = Color.BLACK;
                else deleteFixup(dbParent);
                return;
            }
        }

        // db is right
        if (dbParent.rightChild == db) {
            Node dbSibling = dbParent.leftChild;

            if (isBlack(dbSibling) && isBlack(dbSibling.leftChild) && isBlack(dbSibling.rightChild)) {
                dbSibling.color = Color.RED;

                if (dbParent.color == Color.RED) dbParent.color = Color.BLACK;
                else deleteFixup(dbParent);
                return;
            }
        }


        // case 4 : db sibling is red

        // db is left
        if (dbParent.leftChild == db) {
            Node dbSibling = dbParent.rightChild;

            if (dbSibling.color == Color.RED) {
                swapColor(dbSibling, dbParent);
                rotateLeftAndAdjustParent(dbParent);
                deleteFixup(db);
                return;
            }
        }

        //db is right
        if (dbParent.rightChild == db) {
            Node dbSibling = dbParent.leftChild;

            if (dbSibling.color == Color.RED) {
                swapColor(dbSibling, dbParent);
                rotateRightAndAdjustParent(dbParent);
                deleteFixup(db);
                return;
            }
        }


        // case 5 : db sibling is black , near sibling child is red
        // db is right
        if (dbParent.rightChild == db) {
            Node dbSibling = dbParent.leftChild;
            Node dbSiblingLeft = dbSibling.leftChild;
            Node dbSiblingRight = dbSibling.rightChild;

            if (isBlack(dbSibling) && isBlack(dbSiblingLeft) && isRed(dbSiblingRight)) {
                dbSibling.color = Color.RED;
                dbSiblingRight.color = Color.BLACK;
                rotateLeftAndAdjustParent(dbSibling);
            }
        }

        // db is left
        if (dbParent.leftChild == db) {
            Node dbSibling = dbParent.rightChild;
            Node dbSiblingLeft = dbSibling.leftChild;
            Node dbSiblingRight = dbSibling.rightChild;

            if (isBlack(dbSibling) && isRed(dbSiblingLeft) && isBlack(dbSiblingRight)) {
                dbSibling.color = Color.RED;
                dbSiblingLeft.color = Color.BLACK;
                rotateRightAndAdjustParent(dbSibling);
            }
        }


        // case 6 : db sibling is black , far sibling child is red
        if (dbParent.rightChild == db) {
            Node dbSibling = dbParent.leftChild;
            Node dbSiblingLeft = dbSibling.leftChild;
            Node dbSiblingRight = dbSibling.rightChild;
            if (isBlack(dbSibling) && isRed(dbSiblingLeft)) {
                swapColor(dbSibling, dbParent);
                rotateRightAndAdjustParent(dbParent);
                dbSiblingLeft.color = Color.BLACK;
            }
        }

        if (dbParent.leftChild == db) {
            Node dbSibling = dbParent.rightChild;
            Node dbSiblingLeft = dbSibling.leftChild;
            Node dbSiblingRight = dbSibling.rightChild;
            if (isBlack(dbSibling) && isRed(dbSiblingRight)) {
                swapColor(dbSibling, dbParent);
                rotateLeftAndAdjustParent(dbParent);
                dbSiblingRight.color = Color.BLACK;
            }
        }
    }

    private Node findMinimum(Node node) {
        Node temp = node;
        while (temp.leftChild != null) {
            temp = temp.leftChild;
        }
        return temp;
    }

    private Node findMaximum(Node node) {
        Node temp = node;
        while (temp.rightChild != null) {
            temp = temp.rightChild;
        }
        return temp;
    }

    public boolean search(T value) {
        return search(value, root) != null;
    }

    private Node search(T value, Node root) {
        if (root == null) {
            return null;
        } else if (root.getValue().compareTo(value) == 0) {
            return root;
        } else if (root.getValue().compareTo(value) < 0) {
            return search(value, root.getRightChild());
        } else {
            return search(value, root.getLeftChild());
        }
    }

    public int size() {
        return this.size;
    }

    public int height() {
        return heightHelper(root);
    }

    private int heightHelper(Node node) {
        if (node == null) return 0;
        return 1 + Math.max(heightHelper(node.leftChild), heightHelper(node.rightChild));
    }

    private void replaceParentsChild(Node parent, Node oldChild, Node newChild) {
        if (parent == null) {
            root = newChild;
        } else if (parent.leftChild == oldChild) {
            parent.leftChild = newChild;
        } else if (parent.rightChild == oldChild) {
            parent.rightChild = newChild;
        } else {
            throw new IllegalStateException("Node is not a child of its parent");
        }

        if (newChild != null) {
            newChild.parent = parent;
        }
    }

    private boolean isBlack(Node node) {
        return node == null || node.color == Color.BLACK;
    }

    private boolean isRed(Node node) {
        return node != null && node.color == Color.RED;
    }

    private void swapColor(Node firstNode, Node secondNode) {
        Color temp = firstNode.color;
        firstNode.color = secondNode.color;
        secondNode.color = temp;
    }


    public List<Triple<T, Color, Integer>> inOrderArray() {
        List<Triple<T, Color, Integer>> array = new ArrayList<>();
        inOrderArrayHelper(this.root, array);
        return array;
    }

    private void inOrderArrayHelper(Node x, List<Triple<T, Color, Integer>> array) {
        if (x != null) {
            inOrderArrayHelper(x.leftChild, array);
            array.add(Triple.of(x.value, x.color, heightHelper(x)));
            inOrderArrayHelper(x.rightChild, array);
        }
    }

    private void inOrder(Node x) {
        if (x != null) {
            inOrder(x.leftChild);
            System.out.print(x.value + " " + x.color + " " + heightHelper(x) + " ");
            inOrder(x.rightChild);
        }
    }

    public void printInOrder() {
        inOrder(this.root);
        if (size != 0) System.out.println();
    }

    private void rotateLeftAndAdjustParent(Node node) {
        Node parent = node.parent;
        Node rightChild = node.rightChild;

        node.rightChild = rightChild.leftChild;
        if (rightChild.leftChild != null) {
            rightChild.leftChild.parent = node;
        }

        rightChild.leftChild = node;
        node.parent = rightChild;

        replaceParentsChild(parent, node, rightChild);
    }

    private void rotateRightAndAdjustParent(Node node) {
        Node parent = node.parent;
        Node leftChild = node.leftChild;

        node.leftChild = leftChild.rightChild;
        if (leftChild.rightChild != null) {
            leftChild.rightChild.parent = node;
        }

        leftChild.rightChild = node;
        node.parent = leftChild;

        replaceParentsChild(parent, node, leftChild);
    }
}