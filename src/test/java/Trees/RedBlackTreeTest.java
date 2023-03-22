package Trees;

import org.junit.jupiter.api.Assertions;

import java.util.List;



public class RedBlackTreeTest {
    @org.junit.jupiter.api.Test
    void Test1() {
        RedBlackTree<Integer> t = new RedBlackTree<>();
        Assertions.assertTrue(t.insert(5));
        List<Integer> s = t.inorderTraversal(t.root);
        List<String> s2 = t.inorderTraversalColor(t.root);

        System.out.println(s);
        System.out.println(s2);
        Assertions.assertTrue(t.insert(6));
        s = t.inorderTraversal(t.root);
        s2 = t.inorderTraversalColor(t.root);
        System.out.println(s);
        System.out.println(s2);
        Assertions.assertTrue(t.insert(7));
        s = t.inorderTraversal(t.root);
        s2 = t.inorderTraversalColor(t.root);
        System.out.println(s);
        System.out.println(s2);
        Assertions.assertTrue(t.insert(1));
        s = t.inorderTraversal(t.root);
        s2 = t.inorderTraversalColor(t.root);
        System.out.println(s);
        System.out.println(s2);
        Assertions.assertTrue(t.insert(0));
        s = t.inorderTraversal(t.root);
        s2 = t.inorderTraversalColor(t.root);
        System.out.println(s);
        System.out.println(s2);
        Assertions.assertFalse(t.insert(5));
        s = t.inorderTraversal(t.root);
        s2 = t.inorderTraversalColor(t.root);
        System.out.println(s);
        System.out.println(s2);
        Assertions.assertFalse(t.insert(0));
        s = t.inorderTraversal(t.root);
        s2 = t.inorderTraversalColor(t.root);
        System.out.println(s);
        System.out.println(s2);
    }
}
