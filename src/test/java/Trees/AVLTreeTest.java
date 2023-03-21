package Trees;

import org.junit.jupiter.api.Assertions;

class AVLTreeTest {

    @org.junit.jupiter.api.Test
    void Test1() {
        AVLTree<Integer> t = new AVLTree<>();
        Assertions.assertTrue(t.insert(5));
        Assertions.assertTrue(t.insert(6));
        Assertions.assertTrue(t.insert(7));
        Assertions.assertTrue(t.insert(1));
        Assertions.assertTrue(t.insert(0));
        Assertions.assertFalse(t.insert(5));
        Assertions.assertFalse(t.insert(0));
    }

    @org.junit.jupiter.api.Test
    void Test2() {
        AVLTree<Integer> t = new AVLTree<>();
        Assertions.assertTrue(t.insert(5));
        Assertions.assertTrue(t.insert(6));
        Assertions.assertTrue(t.insert(7));
        Assertions.assertTrue(t.insert(1));
        Assertions.assertTrue(t.insert(0));
        Assertions.assertFalse(t.insert(5));
        Assertions.assertFalse(t.insert(0));
    }

    @org.junit.jupiter.api.Test
    void Test3() {
        AVLTree<Integer> t = new AVLTree<>();
        Assertions.assertTrue(t.insert(5));
        Assertions.assertTrue(t.insert(6));
        Assertions.assertTrue(t.insert(7));
        Assertions.assertTrue(t.insert(1));
        Assertions.assertTrue(t.insert(0));
        Assertions.assertFalse(t.insert(5));
        Assertions.assertFalse(t.insert(0));
    }

    @org.junit.jupiter.api.Test
    void Test4() {
        AVLTree<Integer> t = new AVLTree<>();
        Assertions.assertTrue(t.insert(5));
        Assertions.assertTrue(t.insert(6));
        Assertions.assertTrue(t.insert(7));
        Assertions.assertTrue(t.insert(1));
        Assertions.assertTrue(t.insert(0));
        Assertions.assertFalse(t.insert(5));
        Assertions.assertFalse(t.insert(0));
    }

    @org.junit.jupiter.api.Test
    void Test5() {
        AVLTree<Integer> t = new AVLTree<>();
        Assertions.assertTrue(t.insert(5));
        Assertions.assertTrue(t.insert(6));
        Assertions.assertTrue(t.insert(7));
        Assertions.assertTrue(t.insert(1));
        Assertions.assertTrue(t.insert(0));
        Assertions.assertFalse(t.insert(5));
        Assertions.assertFalse(t.insert(0));
    }
}