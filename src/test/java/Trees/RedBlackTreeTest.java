package Trees;

import org.junit.jupiter.api.Assertions;

public class RedBlackTreeTest {
    @org.junit.jupiter.api.Test
    void Test1() {
        RedBlackTree<Integer> t = new RedBlackTree<>();
        Assertions.assertTrue(t.insert(5));
        t.printInOrder();
        Assertions.assertTrue(t.insert(6));
        t.printInOrder();
        Assertions.assertTrue(t.insert(7));
        t.printInOrder();
        Assertions.assertTrue(t.insert(1));
        t.printInOrder();
        Assertions.assertTrue(t.insert(0));
        t.printInOrder();
        Assertions.assertFalse(t.insert(5));
        t.printInOrder();
        Assertions.assertFalse(t.insert(0));
        t.printInOrder();
    }
}
