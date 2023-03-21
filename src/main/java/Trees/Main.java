package Trees;

public class Main {
    public static void main(String[] args) {
        AVLTree<Double> t = new AVLTree();
        System.out.println(t.getHeight() + " " + t.size() + " " + t.isEmpty());
        t.insert(5.0);
        t.insert(6.0);
        t.insert(2.0);
        t.insert(1.0);
        t.insert(10.0);
        t.insert(8.0);
        t.insert(2.0);
        t.insert(9.0);
        t.insert(0.0);
        t.printInOrder();
        System.out.println(t.getHeight() + " " + t.size() + " " + t.isEmpty());

        t.delete(1.0);
        System.out.println();
        t.printInOrder();
        System.out.println(t.getHeight() + " " + t.size() + " " + t.isEmpty());

        AVLTree<String> s = new AVLTree();
        System.out.println(s.getHeight() + " " + s.size() + " " + s.isEmpty());
        s.insert("hbj");
        s.insert("hc");
        s.insert("iopo");
        s.insert("atdytq");
        s.insert("quwdq");
        s.printInOrder();
        System.out.println(s.getHeight() + " " + s.size() + " " + s.isEmpty());
    }
}
