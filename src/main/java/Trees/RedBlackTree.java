
package Trees;


public class RedBlackTree<T extends Comparable<T>> implements IBST<T> {

    private enum Color {
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
            if (grandParent.leftChild != null && this.parent != grandParent.leftChild) {
                return grandParent.leftChild;
            } else if (grandParent.rightChild != null && this.parent != grandParent.rightChild) {
                return grandParent.rightChild;
            }
            return null;
        }

        public Node getSibling(Node node) {
            Node parent = node.parent;
            if (node == parent.leftChild) {
                return parent.rightChild;
            } else if (node == parent.rightChild) {
                return parent.leftChild;
            } else {
                throw new IllegalStateException("Parent is not a child of its grandparent");
            }
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
        if(search(value)){
            return false;
        }
        if (current == null) {
            this.root = node;
            this.root.setColor(Color.BLACK);
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
        if (current2.getValue().compareTo(value) > 0)
        {
            current2.leftChild = node;
            node.parent = current2;
        }
        else
        {
            current2.rightChild =node;
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
        Node parent = node.getParent();
        Node uncle = node.getUncle();
        Node grandparent = node.getParent().getParent();

        if (uncle != null && uncle.getColor() == Color.RED) {
            uncle.setColor(Color.BLACK);
            parent.setColor(Color.BLACK);

            //We then check if the grandparent is not root to recolor it
            if (grandparent.getParent() != null) {
                grandparent.setColor(Color.RED);
                insertFixup(grandparent);
            }
            return;
        }
        boolean isLeft = false;
        if(! (grandparent == this.root) && grandparent.leftChild == parent )
        {
            isLeft = true;
        }
        Node temp ;
        if (parent.getRightChild() == node && grandparent.getRightChild() == parent) {

            grandparent.setColor(Color.RED);
            parent.setColor(Color.BLACK);
            temp = rotateLeft(grandparent);

            if(!(temp == root)){
                if(isLeft){
                    temp.parent.leftChild =  temp;
                }else{
                    temp.parent.rightChild=  temp;
                }
            }


        } else if (parent.getLeftChild() == node && grandparent.getLeftChild() == parent) {
            grandparent.setColor(Color.RED);
            parent.setColor(Color.BLACK);
            temp = rotateRight(grandparent);
            if(!(temp == root)){
                if(isLeft){
                    temp.parent.leftChild =  temp;
                }else{
                    temp.parent.rightChild=  temp;
                }
            }
        } else if (parent.getLeftChild() == node && grandparent.getRightChild() == parent) {
            temp = rotateRight(parent);
            grandparent.setRightChild(temp);
            grandparent.setColor(Color.RED);
            temp.setColor(Color.BLACK);
            temp = rotateLeft(grandparent);
            if(!(temp == root)){
                if(isLeft){
                    temp.parent.leftChild =  temp;
                }else{
                    temp.parent.rightChild=  temp;
                }
            }
        } else if (parent.getRightChild() == node && grandparent.getLeftChild() == parent) {
            temp = rotateLeft(parent);
            grandparent.setRightChild(temp);
            grandparent.setColor(Color.RED);
            temp.setColor(Color.BLACK);
            temp = rotateRight(grandparent);
            if(!(temp == root)){
                if(isLeft){
                    temp.parent.leftChild =  temp;
                }else{
                    temp.parent.rightChild=  temp;
                }
            }
        }

    }


    public boolean delete(T value) {

        Node node = search(value, this.root);
        if (node == null) return false;


        Node dbNode;
        Color deletedNodeColor;

        if (node.leftChild == null || node.rightChild == null) {
            dbNode = deleteNodeWithZeroOrOneChild(node);
            deletedNodeColor = node.color;
        } else {
            Node inOrderSuccessor = findMinimum(node);
            node.value = inOrderSuccessor.value;

            dbNode = deleteNodeWithZeroOrOneChild(inOrderSuccessor);
            deletedNodeColor = inOrderSuccessor.color;
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

    public void deleteFixup(Node db) {

        Node dbParent = db.parent;

        // case 2 : dbNode is root
        if (db == root) {
            db.color = Color.BLACK;
            return;
        }

        // case 3 : db sibling is black , sibling children are black (2 times for mirroring)
        //db is left
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
                rotateLeft(dbParent);
                deleteFixup(db);
                return;
            }
        }

        //db is right
        if (dbParent.rightChild == db) {
            Node dbSibling = dbParent.leftChild;

            if (dbSibling.color == Color.RED) {
                swapColor(dbSibling, dbParent);
                rotateRight(dbParent);
                deleteFixup(db);
                return;
            }
        }



        // case 5 : db sibling is black , near sibling child is red
        if (dbParent.rightChild == db) {
            Node dbSibling = dbParent.leftChild;
            Node dbSiblingLeft = dbParent.leftChild.leftChild;
            Node dbSiblingRight = dbParent.leftChild.rightChild;

            if (isBlack(dbSibling) && isBlack(dbSiblingLeft) && isRed(dbSiblingRight)) {
                dbSibling.color = Color.RED;
                dbSiblingRight.color = Color.BLACK;
                rotateLeft(dbSibling);
            }
        }

        if (dbParent.leftChild == db) {
            Node dbSibling = dbParent.rightChild;
            Node dbSiblingLeft = dbParent.rightChild.leftChild;
            Node dbSiblingRight = dbParent.rightChild.rightChild;

            if (isBlack(dbSibling) && isRed(dbSiblingLeft) && isBlack(dbSiblingRight)) {
                dbSibling.color = Color.RED;
                dbSiblingLeft.color = Color.BLACK;
                rotateRight(dbSibling);
            }
        }


        // case 6 : db sibling is black , far sibling child is red
        if (dbParent.rightChild == db) {
            Node dbSibling = dbParent.leftChild;
            Node dbSiblingLeft = dbParent.leftChild.leftChild;
            Node dbSiblingRight = dbParent.leftChild.rightChild;
            if (isBlack(dbSibling) && isRed(dbSiblingLeft)) {
                Color x = dbParent.color;
                dbParent.color = dbSibling.color;
                dbSibling.color = x;
                rotateRight(dbSibling);
                dbSiblingLeft.color = Color.BLACK;
            }
        }

        if (dbParent.leftChild == db) {
            Node dbSibling = dbParent.leftChild;
            Node dbSiblingLeft = dbParent.leftChild.leftChild;
            Node dbSiblingRight = dbParent.leftChild.rightChild;
            if (isBlack(dbSibling) && isRed(dbSiblingRight)) {
                Color x = dbParent.color;
                dbParent.color = dbSibling.color;
                dbSibling.color = x;
                rotateLeft(dbSibling);
                dbSiblingRight.color = Color.BLACK;
            }
        }
    }


    private Node findMinimum(Node node) {
        Node temp = node;
        while (node.leftChild != null) {
            temp = temp.leftChild;
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
        return 0;
    }

    public int height() {
        return heightHelper(root);
    }

    private int heightHelper(Node node) {
        if (node == null) return 0;
        return 1 + Math.max(heightHelper(node.leftChild), heightHelper(node.rightChild));
    }

    private Node rotateRight(Node node) {
        Node x = node.leftChild;
        Node y = x.rightChild;
        x.rightChild = node;
        node.leftChild = y;
        boolean par = false;
        if(node.parent == root){
            par = true;
        }
        node.parent = x;
        if (y != null) y.parent = node;
        if(par){
            this.root = x ;
        }
        return x;
    }

    private Node rotateLeft(Node node) {
        Node x = node.rightChild;
        Node y = x.leftChild;
        x.leftChild = node;
        node.rightChild = y;
        boolean par = false;
        if(node.parent == root){
            par = true;
        }
        node.parent = x;
        if (y != null) y.parent = node;
        if(true){
            this.root = x ;
        }
        return x;
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

    private void inOrder (Node x) {
        if (x != null) {
            inOrder(x.leftChild);
            System.out.print(x.value + " " + x.color + " ");
            inOrder(x.rightChild);
        }
    }

    public void printInOrder () {
        inOrder (this.root);
        System.out.println();
    }
}
