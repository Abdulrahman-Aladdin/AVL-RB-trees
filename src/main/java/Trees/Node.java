public class Node  <T extends Comparable<T>>{
    private  T value ;
    private Color color;
    private Node parent , leftChild , rightChild ;


    Node( Color color , T value ){
        this.color = color ;
        this.value = value;
        this.parent = null;
        this.leftChild = null;
        this.rightChild = null;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public void setLeftChild(Node leftChild) {
        this.leftChild = leftChild;
    }

    public void setRightChild(Node rightChild) {
        this.rightChild = rightChild;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public T getValue() {
        return value;
    }

    public Color getColor() {
        return color;
    }

    public Node getParent() {
        return parent;
    }

    public Node getLeftChild() {
        return leftChild;
    }

    public Node getRightChild() {
        return rightChild;
    }

    public Node getUncle(){
        Node grandParent = this.parent.getParent();
        if(grandParent.leftChild != null && this.parent != grandParent.leftChild){
            return grandParent.leftChild;
        }else if(grandParent.rightChild != null&& this.parent != grandParent.rightChild){
            return grandParent.rightChild;
        }
        return null ;
    }



}
