package Trees;

import org.junit.jupiter.api.Assertions;

public class RedBlackTreeTest {
    @org.junit.jupiter.api.Test
    void Test1() {
        RedBlackTree<Integer> t = new RedBlackTree<>();
        Assertions.assertTrue(t.insert(5));
        Assertions.assertTrue(t.insert(6));
        Assertions.assertTrue(t.insert(7));
        Assertions.assertTrue(t.insert(1));
        Assertions.assertTrue(t.insert(0));
        Assertions.assertFalse(t.insert(5));
        Assertions.assertFalse(t.insert(0));
    }
}
