public class RedBlackTree  <T extends Comparable<T>>{
    Node root;
    public RedBlackTree(){
        this.root = null ;
    }
    Node  rotateRight(Node node)
    {
        Node x = node.getLeftChild();
        Node y = x.getRightChild();
        x.setRightChild( node);
        node.setLeftChild(y);
        node.setParent(x);
        if(y!=null)
            y.setParent( node);
        return x;
    }

    Node  rotateleft(Node node){
        Node x = node.getRightChild();
        Node y = x.getLeftChild();
        x.setLeftChild( node);
        node.setRightChild(y);
        node.setParent(x);
        if(y!=null)
            y.setParent( node);
        return x;
    }

    void insert(T value){
        Node node = new Node(Color.red , value );
        Node current = this.root;
        if(current == null){
            this.root = node;
            this.root.setColor(Color.black);
            return ;
        }

        while(true){
            if(current.getValue().compareTo(value) > 0){
                if(current.getLeftChild() == null){
                    current.setLeftChild(node);
                    node.setParent(current);
                    break;
                }else{
                    current = current.getLeftChild();
                }
            }else if(current.getValue().compareTo(value) < 0){
                if(current.getRightChild() == null){
                    current.setRightChild(node);
                    node.setParent(current);
                    break;
                }else{
                    current = current.getRightChild();
                }
            }else{
                return ;
            }
        }
        insertFixup(node);
    }

    private void insertFixup(Node node) {
        if(node.getParent().getColor() == Color.black){
            return;
        }
        Node parent = node.getParent();
        Node uncle = node.getUncle();
        Node grandparent = node.getParent().getParent();
        if(uncle != null && uncle.getColor() == Color.red)
        {
            uncle.setColor(Color.black);
            parent.setColor(Color.black);
            //We then check if the grandparent is not root to recolor it
            if(grandparent.getParent() != null)
            {
                grandparent.setColor(Color.red);
                insertFixup(grandparent);
            }
            return;
        }

        if(parent.getRightChild() == node && grandparent.getRightChild() == parent){
            grandparent.setColor(Color.red);
            parent.setColor(Color.black);
            Node temp = rotateleft(grandparent);
            if(temp.getValue().compareTo(temp.getParent().getValue()) < 0 ){
                temp.getParent().setLeftChild(temp);
            }else{
                temp.getParent().setRightChild(temp);
            }

        }else if(parent.getLeftChild() == node && grandparent.getLeftChild() == parent){
            grandparent.setColor(Color.red);
            parent.setColor(Color.black);
            Node temp = rotateRight(grandparent) ;
            if(temp.getValue().compareTo(temp.getParent().getValue()) < 0 ){
                temp.getParent().setLeftChild(temp);
            }else{
                temp.getParent().setRightChild(temp);
            }
        }else if(parent.getLeftChild() == node && grandparent.getRightChild() == parent){
            Node temp =  rotateRight(parent);
            grandparent.setRightChild(temp);
            grandparent.setColor(Color.red);
            temp.setColor(Color.black);
            temp = rotateleft(grandparent);
            if(temp.getValue().compareTo(temp.getParent().getValue()) < 0 ){
                temp.getParent().setLeftChild(temp);
            }else{
                temp.getParent().setRightChild(temp);
            }
        }else if(parent.getRightChild() == node && grandparent.getLeftChild() == parent){
            Node temp =  rotateleft(parent);
            grandparent.setRightChild(temp);
            grandparent.setColor(Color.red);
            temp.setColor(Color.black);
            temp = rotateRight(grandparent);
            if(temp.getValue().compareTo(temp.getParent().getValue()) < 0 ){
                temp.getParent().setLeftChild(temp);
            }else{
                temp.getParent().setRightChild(temp);
            }
        }
    }

    public Node search(T value , Node root){
        if(root == null){
            return null;
        }else if(root.getValue().compareTo(value) == 0){
            return root ;
        }else if(root.getValue().compareTo(value) < 0 ){
            return search(value,root.getRightChild());
        }else{
            return search(value,root.getLeftChild());
        }
    }

    public boolean contain(T value , Node root){
        if(search(value , root) == null)
            return false;
        return true;
    }




}
