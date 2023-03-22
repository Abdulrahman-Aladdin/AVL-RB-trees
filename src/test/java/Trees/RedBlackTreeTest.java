package Trees;

import org.apache.commons.lang3.tuple.Triple;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


public class RedBlackTreeTest {

    private RedBlackTree<Integer> tree;

    @Test
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
        Assertions.assertTrue(t.insert(9));
        s = t.inorderTraversal(t.root);
        s2 = t.inorderTraversalColor(t.root);
        System.out.println(s);
        System.out.println(s2);
        Assertions.assertTrue(t.insert(151));
        s = t.inorderTraversal(t.root);
        s2 = t.inorderTraversalColor(t.root);
        System.out.println(s);
        System.out.println(s2);
    }

    @Test
    void test2(){
        tree = new RedBlackTree<>();

        for (int i = 0; i < 20; i += 2) {
            tree.insert(i);
        }

        // value , color , height
        List<Triple<Integer, RedBlackTree.Color,Integer>> expected = new ArrayList<>(List.of(
                Triple.of(1, RedBlackTree.Color.BLACK,2), Triple.of(1, RedBlackTree.Color.RED,5)
        ));

        Assertions.assertTrue(compareTwoInorderArrays(tree.inOrderArray(),expected));

    }

     private boolean compareTwoInorderArrays(List<Triple<Integer, RedBlackTree.Color,Integer>> array_1,
                                             List<Triple<Integer, RedBlackTree.Color,Integer>> array_2){

        if (array_1.size() != array_2.size()){
            return false;
        }

         for (int i = 0; i < array_1.size(); i++) {

             Triple<Integer,RedBlackTree.Color,Integer> pairOfArray_1 = array_1.get(i), pairOfArray_2 = array_2.get(i);

             if ( pairOfArray_1.getLeft().equals(pairOfArray_2.getLeft())
                     || pairOfArray_1.getMiddle().equals(pairOfArray_2.getMiddle())
                     || pairOfArray_1.getRight().equals(pairOfArray_2.getRight())) {
                 return false;
             }
         }
        return true;
     }

}