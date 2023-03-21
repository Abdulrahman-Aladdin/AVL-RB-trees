package Trees;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        AVLTree<Integer> t = new AVLTree<>();
        System.out.println(t.getHeight() + " " + t.size() + " " + t.isEmpty());
        List<Integer> l = new ArrayList<>();
        Integer x;
        Random r = new Random();
        for (int i = 0; i < 20; i++) {
            x = r.nextInt(101);
            System.out.println(t.insert(x));
            l.add(x);
        }
        t.printInOrder();
        System.out.println(t.getHeight() + " " + t.size() + " " + t.isEmpty());
        for (Integer p : l) {
            System.out.println(t.delete(p));
        }
        System.out.println();
        t.printInOrder();
        System.out.println(t.getHeight() + " " + t.size() + " " + t.isEmpty());
    }
}
