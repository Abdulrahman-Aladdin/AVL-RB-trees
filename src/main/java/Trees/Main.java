package Trees;

public class Main {
    public static void main(String[] args) {
        AVLTree<Double> t = new AVLTree();
        System.out.println(t.height() + " " + t.size() + " " + t.isEmpty());
        t.insert(5.0);
        t.PrintInOrder();
        t.insert(6.0);
        t.PrintInOrder();
        t.insert(2.0);
        t.PrintInOrder();
        t.insert(1.0);
        t.PrintInOrder();
        t.insert(10.0);
        t.PrintInOrder();
        t.insert(8.0);
        t.PrintInOrder();
        t.insert(2.0);
        t.PrintInOrder();
        t.insert(9.0);
        t.PrintInOrder();
        t.insert(0.0);
        t.PrintInOrder();
        System.out.println(t.height() + " " + t.size() + " " + t.isEmpty());
    }
}
