package Trees;

import org.apache.commons.lang3.tuple.Triple;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


public class RedBlackTreeTest {

    private RedBlackTree<Integer> tree;

    private boolean compareTwoInorderArrays(List<Triple<Integer, RedBlackTree.Color,Integer>> array_1,
                                            List<Triple<Integer, RedBlackTree.Color,Integer>> array_2){

        if (array_1.size() != array_2.size()){
            return false;
        }

        for (int i = 0; i < array_1.size(); i++) {

            Triple<Integer,RedBlackTree.Color,Integer> pairOfArray_1 = array_1.get(i);
            Triple<Integer,RedBlackTree.Color,Integer> pairOfArray_2 = array_2.get(i);

            if (!pairOfArray_1.getLeft().equals(pairOfArray_2.getLeft())
                    || !pairOfArray_1.getMiddle().equals(pairOfArray_2.getMiddle())
                    || !pairOfArray_1.getRight().equals(pairOfArray_2.getRight())) {
                return false;
            }
        }
        return true;
    }


    // insertion

    @Test
    void Test1(){
        tree = new RedBlackTree<>();

        Assertions.assertTrue(tree.insert(5));
        Assertions.assertTrue(tree.insert(1));
        Assertions.assertFalse(tree.insert(5));
    }


    @Test
    void Test2(){
        tree = new RedBlackTree<>();

        Assertions.assertTrue(tree.insert(5));
        Assertions.assertTrue(tree.insert(1));
        Assertions.assertTrue(tree.insert(6));
        // value , color , height
        List<Triple<Integer, RedBlackTree.Color,Integer>> expected = new ArrayList<>(List.of(
                Triple.of(1, RedBlackTree.Color.RED,1),
                Triple.of(5, RedBlackTree.Color.BLACK,2),
                Triple.of(6, RedBlackTree.Color.RED,1)
        ));

        Assertions.assertTrue(compareTwoInorderArrays(tree.inOrderArray(),expected));
    }
    @Test
    void Test3(){
        tree = new RedBlackTree<>();

        Assertions.assertTrue(tree.insert(5));
        Assertions.assertTrue(tree.insert(1));
        Assertions.assertTrue(tree.insert(6));
        Assertions.assertTrue(tree.insert(10));
        Assertions.assertTrue(tree.insert(47));
        Assertions.assertTrue(tree.insert(4));
        Assertions.assertTrue(tree.insert(11));
        Assertions.assertTrue(tree.insert(12));


        // value , color , height
        List<Triple<Integer, RedBlackTree.Color,Integer>> expected = new ArrayList<>(List.of(
                Triple.of(1, RedBlackTree.Color.BLACK,2),
                Triple.of(4, RedBlackTree.Color.RED,1),
                Triple.of(5, RedBlackTree.Color.BLACK,4),
                Triple.of(6, RedBlackTree.Color.BLACK,1),
                Triple.of(10, RedBlackTree.Color.RED,3),
                Triple.of(11, RedBlackTree.Color.RED,1),
                Triple.of(12, RedBlackTree.Color.BLACK,2),
                Triple.of(47, RedBlackTree.Color.RED,1)
        ));

        Assertions.assertTrue(compareTwoInorderArrays(tree.inOrderArray(),expected));
    }

    @Test
    void Test4(){
        tree = new RedBlackTree<>();

        Assertions.assertTrue(tree.insert(6));
        Assertions.assertTrue(tree.insert(12));
        Assertions.assertTrue(tree.insert(47));
        Assertions.assertTrue(tree.insert(11));
        Assertions.assertTrue(tree.insert(4));
        Assertions.assertTrue(tree.insert(10));
        Assertions.assertTrue(tree.insert(5));
        Assertions.assertTrue(tree.insert(1));

        // value , color , height
        List<Triple<Integer, RedBlackTree.Color,Integer>> expected = new ArrayList<>(List.of(
                Triple.of(1, RedBlackTree.Color.RED,1),
                Triple.of(4, RedBlackTree.Color.BLACK,2),
                Triple.of(5, RedBlackTree.Color.RED,1),
                Triple.of(6, RedBlackTree.Color.RED,3),
                Triple.of(10, RedBlackTree.Color.RED,1),
                Triple.of(11, RedBlackTree.Color.BLACK,2),
                Triple.of(12, RedBlackTree.Color.BLACK,4),
                Triple.of(47, RedBlackTree.Color.BLACK,1)
        ));

        Assertions.assertTrue(compareTwoInorderArrays(tree.inOrderArray(),expected));
    }

    @Test
    void Test5(){
        tree = new RedBlackTree<>();
        Assertions.assertTrue(tree.insert(6));
        Assertions.assertTrue(tree.insert(5));
        Assertions.assertFalse(tree.insert(6));
        Assertions.assertTrue(tree.insert(1));
        // value , color , height
        List<Triple<Integer, RedBlackTree.Color,Integer>> expected = new ArrayList<>(List.of(
                Triple.of(1, RedBlackTree.Color.RED,1),
                Triple.of(5, RedBlackTree.Color.BLACK,2),
                Triple.of(6, RedBlackTree.Color.RED,1)
        ));

        Assertions.assertTrue(compareTwoInorderArrays(tree.inOrderArray(),expected));
    }

    @Test
    void Test6(){
        tree = new RedBlackTree<>();

        Assertions.assertTrue(tree.insert(5));
        Assertions.assertTrue(tree.insert(1));
        Assertions.assertTrue(tree.insert(6));
        Assertions.assertTrue(tree.delete(6));

        // value , color , height
        List<Triple<Integer, RedBlackTree.Color,Integer>> expected = new ArrayList<>(List.of(
                Triple.of(1, RedBlackTree.Color.RED,1),
                Triple.of(5, RedBlackTree.Color.BLACK,2)
        ));

        Assertions.assertTrue(compareTwoInorderArrays(tree.inOrderArray(),expected));
    }

    @Test
    void Test7(){
        tree = new RedBlackTree<>();

        Assertions.assertTrue(tree.insert(10));
        Assertions.assertTrue(tree.insert(1));
        Assertions.assertTrue(tree.insert(6));
        Assertions.assertTrue(tree.insert(15));
        // value , color , height
        List<Triple<Integer, RedBlackTree.Color,Integer>> expected = new ArrayList<>(List.of(
                Triple.of(1, RedBlackTree.Color.BLACK,1),
                Triple.of(6, RedBlackTree.Color.BLACK,3),
                Triple.of(10, RedBlackTree.Color.BLACK,2),
                Triple.of(15, RedBlackTree.Color.RED,1)
        ));

        Assertions.assertTrue(compareTwoInorderArrays(tree.inOrderArray(),expected));
    }


    // deletion


    @Test // delete root
    void Test8(){
        tree = new RedBlackTree<>();

        Assertions.assertTrue(tree.insert(5));
        Assertions.assertTrue(tree.delete(5));
        Assertions.assertFalse(tree.delete(0));


        List<Triple<Integer, RedBlackTree.Color,Integer>> expected = new ArrayList<>(List.of());
        Assertions.assertTrue(compareTwoInorderArrays(expected,tree.inOrderArray()));
    }

    @Test // delete left red leaf node
    void Test9(){
        tree = new RedBlackTree<>();

        Assertions.assertTrue(tree.insert(5));
        Assertions.assertTrue(tree.insert(1));
        Assertions.assertTrue(tree.insert(6));
        Assertions.assertTrue(tree.delete(1));


        List<Triple<Integer, RedBlackTree.Color,Integer>> expected = new ArrayList<>(List.of(
                Triple.of(5, RedBlackTree.Color.BLACK,2),
                Triple.of(6, RedBlackTree.Color.RED,1)
        ));

        Assertions.assertTrue(compareTwoInorderArrays(tree.inOrderArray(),expected));
    }

    @Test // delete right red leaf node
    void Test10(){
        tree = new RedBlackTree<>();

        Assertions.assertTrue(tree.insert(5));
        Assertions.assertTrue(tree.insert(1));
        Assertions.assertTrue(tree.insert(6));
        Assertions.assertTrue(tree.delete(6));


        List<Triple<Integer, RedBlackTree.Color,Integer>> expected = new ArrayList<>(List.of(
                Triple.of(1, RedBlackTree.Color.RED,1),
                Triple.of(5, RedBlackTree.Color.BLACK,2)
        ));

        Assertions.assertTrue(compareTwoInorderArrays(tree.inOrderArray(),expected));
    }

    @Test // delete left node with one left child , node is black , child is red
    void Test11(){
        tree = new RedBlackTree<>();

        Assertions.assertTrue(tree.insert(50));
        Assertions.assertTrue(tree.insert(20));
        Assertions.assertTrue(tree.insert(100));
        Assertions.assertTrue(tree.insert(15));
        Assertions.assertTrue(tree.delete(20));


        List<Triple<Integer, RedBlackTree.Color,Integer>> expected = new ArrayList<>(List.of(
                Triple.of(15, RedBlackTree.Color.BLACK,1),
                Triple.of(50, RedBlackTree.Color.BLACK,2),
                Triple.of(100, RedBlackTree.Color.BLACK,1)
        ));

        Assertions.assertTrue(compareTwoInorderArrays(tree.inOrderArray(),expected));
    }

    @Test // delete left node with one right child , node is black , child is red
    void Test12(){
        tree = new RedBlackTree<>();

        Assertions.assertTrue(tree.insert(50));
        Assertions.assertTrue(tree.insert(20));
        Assertions.assertTrue(tree.insert(100));
        Assertions.assertTrue(tree.insert(25));
        Assertions.assertTrue(tree.delete(20));

        List<Triple<Integer, RedBlackTree.Color,Integer>> expected = new ArrayList<>(List.of(
                Triple.of(25, RedBlackTree.Color.BLACK,1),
                Triple.of(50, RedBlackTree.Color.BLACK,2),
                Triple.of(100, RedBlackTree.Color.BLACK,1)
        ));

        Assertions.assertTrue(compareTwoInorderArrays(tree.inOrderArray(),expected));
    }



    @Test // delete right node with one left child , node is black , child is red
    void Test13(){
        tree = new RedBlackTree<>();

        Assertions.assertTrue(tree.insert(50));
        Assertions.assertTrue(tree.insert(20));
        Assertions.assertTrue(tree.insert(100));
        Assertions.assertTrue(tree.insert(90));
        Assertions.assertTrue(tree.delete(100));

        List<Triple<Integer, RedBlackTree.Color,Integer>> expected = new ArrayList<>(List.of(
                Triple.of(20, RedBlackTree.Color.BLACK,1),
                Triple.of(50, RedBlackTree.Color.BLACK,2),
                Triple.of(90, RedBlackTree.Color.BLACK,1)
        ));

        Assertions.assertTrue(compareTwoInorderArrays(tree.inOrderArray(),expected));
    }

    @Test // delete right node with one right child , node is black , child is red
    void Test14(){
        tree = new RedBlackTree<>();

        Assertions.assertTrue(tree.insert(50));
        Assertions.assertTrue(tree.insert(20));
        Assertions.assertTrue(tree.insert(100));
        Assertions.assertTrue(tree.insert(120));
        Assertions.assertTrue(tree.delete(100));

        List<Triple<Integer, RedBlackTree.Color,Integer>> expected = new ArrayList<>(List.of(
                Triple.of(20, RedBlackTree.Color.BLACK,1),
                Triple.of(50, RedBlackTree.Color.BLACK,2),
                Triple.of(120, RedBlackTree.Color.BLACK,1)
        ));

        Assertions.assertTrue(compareTwoInorderArrays(tree.inOrderArray(),expected));
    }

    @Test // db sibling is black and children are black , node deleted is right and handles if db is root
    void Test15(){
        tree = new RedBlackTree<>();

        Assertions.assertTrue(tree.insert(50));
        Assertions.assertTrue(tree.insert(20));
        Assertions.assertTrue(tree.insert(100));
        Assertions.assertTrue(tree.insert(19));
        Assertions.assertTrue(tree.delete(19));
        Assertions.assertTrue(tree.delete(100));

        List<Triple<Integer, RedBlackTree.Color,Integer>> expected = new ArrayList<>(List.of(
                Triple.of(20, RedBlackTree.Color.RED,1),
                Triple.of(50, RedBlackTree.Color.BLACK,2)
        ));

        Assertions.assertTrue(compareTwoInorderArrays(tree.inOrderArray(),expected));
    }

    @Test // db sibling is black and children are black , node deleted is left and handles if db is root
    void Test16(){
        tree = new RedBlackTree<>();

        Assertions.assertTrue(tree.insert(50));
        Assertions.assertTrue(tree.insert(20));
        Assertions.assertTrue(tree.insert(100));
        Assertions.assertTrue(tree.insert(19));
        Assertions.assertTrue(tree.delete(19));
        Assertions.assertTrue(tree.delete(20));

        List<Triple<Integer, RedBlackTree.Color,Integer>> expected = new ArrayList<>(List.of(
                Triple.of(50, RedBlackTree.Color.BLACK,2),
                Triple.of(100, RedBlackTree.Color.RED,1)
        ));

        Assertions.assertTrue(compareTwoInorderArrays(tree.inOrderArray(),expected));
    }

    @Test // db sibling is red , node deleted is right , red node children are black
    void Test17(){
        tree = new RedBlackTree<>();

        Assertions.assertTrue(tree.insert(50));
        Assertions.assertTrue(tree.insert(20));
        Assertions.assertTrue(tree.insert(100));
        Assertions.assertTrue(tree.insert(15));
        Assertions.assertTrue(tree.insert(40));
        Assertions.assertTrue(tree.insert(35));
        Assertions.assertTrue(tree.delete(100));

        List<Triple<Integer, RedBlackTree.Color,Integer>> expected = new ArrayList<>(List.of(
                Triple.of(15, RedBlackTree.Color.BLACK,1),
                Triple.of(20, RedBlackTree.Color.BLACK,3),
                Triple.of(35, RedBlackTree.Color.BLACK,1),
                Triple.of(40, RedBlackTree.Color.RED,2),
                Triple.of(50, RedBlackTree.Color.BLACK,1)
        ));

        Assertions.assertTrue(compareTwoInorderArrays(tree.inOrderArray(),expected));
    }

    @Test // db sibling is red , node deleted is left , red node children are black
    void Test18(){
        tree = new RedBlackTree<>();

        Assertions.assertTrue(tree.insert(50));
        Assertions.assertTrue(tree.insert(20));
        Assertions.assertTrue(tree.insert(100));
        Assertions.assertTrue(tree.insert(95));
        Assertions.assertTrue(tree.insert(120));
        Assertions.assertTrue(tree.insert(115));
        Assertions.assertTrue(tree.delete(20));

        List<Triple<Integer, RedBlackTree.Color,Integer>> expected = new ArrayList<>(List.of(
                Triple.of(50, RedBlackTree.Color.BLACK,2),
                Triple.of(95, RedBlackTree.Color.RED,1),
                Triple.of(100, RedBlackTree.Color.BLACK,3),
                Triple.of(115, RedBlackTree.Color.RED,1),
                Triple.of(120, RedBlackTree.Color.BLACK,2)
        ));

        Assertions.assertTrue(compareTwoInorderArrays(tree.inOrderArray(),expected));
    }

    @Test // db sibling is black and sibling far child is black , near is red , deleted node is left
    void Test19(){
        tree = new RedBlackTree<>();
        Assertions.assertTrue(tree.insert(50));
        Assertions.assertTrue(tree.insert(20));
        Assertions.assertTrue(tree.insert(100));
        Assertions.assertTrue(tree.insert(60));
        Assertions.assertTrue(tree.insert(120));
        Assertions.assertTrue(tree.delete(120));
        Assertions.assertTrue(tree.delete(20));

        List<Triple<Integer, RedBlackTree.Color,Integer>> expected = new ArrayList<>(List.of(
                Triple.of(50, RedBlackTree.Color.BLACK,1),
                Triple.of(60, RedBlackTree.Color.BLACK,2),
                Triple.of(100, RedBlackTree.Color.BLACK,1)
        ));

        Assertions.assertTrue(compareTwoInorderArrays(tree.inOrderArray(),expected));
    }

    @Test // db sibling is black and sibling far child is black , near is red , deleted node is right
    void Test20(){
        tree = new RedBlackTree<>();
        Assertions.assertTrue(tree.insert(50));
        Assertions.assertTrue(tree.insert(20));
        Assertions.assertTrue(tree.insert(100));
        Assertions.assertTrue(tree.insert(15));
        Assertions.assertTrue(tree.insert(22));
        Assertions.assertTrue(tree.delete(15));
        Assertions.assertTrue(tree.delete(100));


        List<Triple<Integer, RedBlackTree.Color,Integer>> expected = new ArrayList<>(List.of(
                Triple.of(20, RedBlackTree.Color.BLACK,1),
                Triple.of(22, RedBlackTree.Color.BLACK,2),
                Triple.of(50, RedBlackTree.Color.BLACK,1)
        ));

        Assertions.assertTrue(compareTwoInorderArrays(tree.inOrderArray(),expected));
    }

    @Test // db sibling is black and sibling far child is red , near is black , deleted node is left
    void Test21(){
        tree = new RedBlackTree<>();
        Assertions.assertTrue(tree.insert(50));
        Assertions.assertTrue(tree.insert(20));
        Assertions.assertTrue(tree.insert(100));
        Assertions.assertTrue(tree.insert(150));
        Assertions.assertTrue(tree.delete(20));

        List<Triple<Integer, RedBlackTree.Color,Integer>> expected = new ArrayList<>(List.of(
                Triple.of(50, RedBlackTree.Color.BLACK,1),
                Triple.of(100, RedBlackTree.Color.BLACK,2),
                Triple.of(150, RedBlackTree.Color.BLACK,1)
        ));

        Assertions.assertTrue(compareTwoInorderArrays(tree.inOrderArray(),expected));
    }

    @Test // db sibling is black and sibling far child is red , near is black , deleted node is right
    void Test22(){
        tree = new RedBlackTree<>();

        Assertions.assertTrue(tree.insert(50));
        Assertions.assertTrue(tree.insert(20));
        Assertions.assertTrue(tree.insert(100));
        Assertions.assertTrue(tree.insert(15));
        Assertions.assertTrue(tree.delete(100));

        List<Triple<Integer, RedBlackTree.Color,Integer>> expected = new ArrayList<>(List.of(
                Triple.of(15, RedBlackTree.Color.BLACK,1),
                Triple.of(20, RedBlackTree.Color.BLACK,2),
                Triple.of(50, RedBlackTree.Color.BLACK,1)
        ));

        Assertions.assertTrue(compareTwoInorderArrays(tree.inOrderArray(),expected));
    }

    @Test // db sibling is black and sibling far child is red , near is red , deleted node is left
    void Test23(){
        tree = new RedBlackTree<>();
        Assertions.assertTrue(tree.insert(50));
        Assertions.assertTrue(tree.insert(20));
        Assertions.assertTrue(tree.insert(100));
        Assertions.assertTrue(tree.insert(150));
        Assertions.assertTrue(tree.insert(60));
        Assertions.assertTrue(tree.delete(20));

        List<Triple<Integer, RedBlackTree.Color,Integer>> expected = new ArrayList<>(List.of(
                Triple.of(50, RedBlackTree.Color.BLACK,2),
                Triple.of(60, RedBlackTree.Color.RED,1),
                Triple.of(100, RedBlackTree.Color.BLACK,3),
                Triple.of(150, RedBlackTree.Color.BLACK,1)
        ));

        Assertions.assertTrue(compareTwoInorderArrays(tree.inOrderArray(),expected));
    }

    @Test // db sibling is black and sibling far child is red , near is red , deleted node is right
    void Test24(){
        tree = new RedBlackTree<>();
        Assertions.assertTrue(tree.insert(50));
        Assertions.assertTrue(tree.insert(20));
        Assertions.assertTrue(tree.insert(100));
        Assertions.assertTrue(tree.insert(15));
        Assertions.assertTrue(tree.insert(22));
        Assertions.assertTrue(tree.delete(100));

        List<Triple<Integer, RedBlackTree.Color,Integer>> expected = new ArrayList<>(List.of(
                Triple.of(15, RedBlackTree.Color.BLACK,1),
                Triple.of(20, RedBlackTree.Color.BLACK,3),
                Triple.of(22, RedBlackTree.Color.RED,1),
                Triple.of(50, RedBlackTree.Color.BLACK,2)
        ));

        Assertions.assertTrue(compareTwoInorderArrays(tree.inOrderArray(),expected));
    }

    @Test // random deletion
    void Test25(){
        tree = new RedBlackTree<>();
        Assertions.assertTrue(tree.insert(20));
        Assertions.assertTrue(tree.insert(6));
        Assertions.assertTrue(tree.insert(50));
        Assertions.assertTrue(tree.insert(41));
        Assertions.assertTrue(tree.insert(11));
        Assertions.assertTrue(tree.insert(64));
        Assertions.assertTrue(tree.insert(98));
        Assertions.assertTrue(tree.insert(325));
        Assertions.assertTrue(tree.insert(14));
        Assertions.assertTrue(tree.insert(67));
        Assertions.assertTrue(tree.insert(246));
        Assertions.assertTrue(tree.insert(15));
        Assertions.assertTrue(tree.insert(68));
        Assertions.assertTrue(tree.insert(97));

        Assertions.assertTrue(tree.delete(20));
        List<Triple<Integer, RedBlackTree.Color,Integer>> expected = new ArrayList<>(List.of(
                Triple.of(6, RedBlackTree.Color.BLACK,1),
                Triple.of(11, RedBlackTree.Color.RED,2),
                Triple.of(14, RedBlackTree.Color.BLACK,1),
                Triple.of(15, RedBlackTree.Color.BLACK,3),
                Triple.of(41, RedBlackTree.Color.BLACK,1),
                Triple.of(50, RedBlackTree.Color.BLACK,5),
                Triple.of(64, RedBlackTree.Color.BLACK,1),
                Triple.of(67, RedBlackTree.Color.RED,3),
                Triple.of(68, RedBlackTree.Color.BLACK,2),
                Triple.of(97, RedBlackTree.Color.RED,1),
                Triple.of(98, RedBlackTree.Color.BLACK,4),
                Triple.of(246, RedBlackTree.Color.RED,1),
                Triple.of(325, RedBlackTree.Color.BLACK,2)
        ));
        Assertions.assertTrue(compareTwoInorderArrays(tree.inOrderArray(),expected));

        Assertions.assertTrue(tree.delete(50));
        expected = new ArrayList<>(List.of(
                Triple.of(6, RedBlackTree.Color.BLACK,1),
                Triple.of(11, RedBlackTree.Color.BLACK,3),
                Triple.of(14, RedBlackTree.Color.RED,1),
                Triple.of(15, RedBlackTree.Color.BLACK,2),
                Triple.of(41, RedBlackTree.Color.BLACK,5),
                Triple.of(64, RedBlackTree.Color.BLACK,1),
                Triple.of(67, RedBlackTree.Color.RED,3),
                Triple.of(68, RedBlackTree.Color.BLACK,2),
                Triple.of(97, RedBlackTree.Color.RED,1),
                Triple.of(98, RedBlackTree.Color.BLACK,4),
                Triple.of(246, RedBlackTree.Color.RED,1),
                Triple.of(325, RedBlackTree.Color.BLACK,2)
        ));
        Assertions.assertTrue(compareTwoInorderArrays(tree.inOrderArray(),expected));

        Assertions.assertTrue(tree.delete(98));
        expected = new ArrayList<>(List.of(
                Triple.of(6, RedBlackTree.Color.BLACK,1),
                Triple.of(11, RedBlackTree.Color.BLACK,3),
                Triple.of(14, RedBlackTree.Color.RED,1),
                Triple.of(15, RedBlackTree.Color.BLACK,2),
                Triple.of(41, RedBlackTree.Color.BLACK,4),
                Triple.of(64, RedBlackTree.Color.BLACK,1),
                Triple.of(67, RedBlackTree.Color.RED,2),
                Triple.of(68, RedBlackTree.Color.BLACK,1),
                Triple.of(97, RedBlackTree.Color.BLACK,3),
                Triple.of(246, RedBlackTree.Color.RED,1),
                Triple.of(325, RedBlackTree.Color.BLACK,2)
        ));
        Assertions.assertTrue(compareTwoInorderArrays(tree.inOrderArray(),expected));

        Assertions.assertTrue(tree.delete(67));
        expected = new ArrayList<>(List.of(
                Triple.of(6, RedBlackTree.Color.BLACK,1),
                Triple.of(11, RedBlackTree.Color.BLACK,3),
                Triple.of(14, RedBlackTree.Color.RED,1),
                Triple.of(15, RedBlackTree.Color.BLACK,2),
                Triple.of(41, RedBlackTree.Color.BLACK,4),
                Triple.of(64, RedBlackTree.Color.BLACK,2),
                Triple.of(68, RedBlackTree.Color.RED,1),
                Triple.of(97, RedBlackTree.Color.BLACK,3),
                Triple.of(246, RedBlackTree.Color.RED,1),
                Triple.of(325, RedBlackTree.Color.BLACK,2)
        ));
        Assertions.assertTrue(compareTwoInorderArrays(tree.inOrderArray(),expected));

        Assertions.assertTrue(tree.delete(41));
        expected = new ArrayList<>(List.of(
                Triple.of(6, RedBlackTree.Color.BLACK,1),
                Triple.of(11, RedBlackTree.Color.BLACK,2),
                Triple.of(14, RedBlackTree.Color.BLACK,1),
                Triple.of(15, RedBlackTree.Color.BLACK,4),
                Triple.of(64, RedBlackTree.Color.BLACK,2),
                Triple.of(68, RedBlackTree.Color.RED,1),
                Triple.of(97, RedBlackTree.Color.BLACK,3),
                Triple.of(246, RedBlackTree.Color.RED,1),
                Triple.of(325, RedBlackTree.Color.BLACK,2)
        ));
        Assertions.assertTrue(compareTwoInorderArrays(tree.inOrderArray(),expected));

        Assertions.assertTrue(tree.delete(11));
        expected = new ArrayList<>(List.of(
                Triple.of(6, RedBlackTree.Color.BLACK,2),
                Triple.of(14, RedBlackTree.Color.RED,1),
                Triple.of(15, RedBlackTree.Color.BLACK,4),
                Triple.of(64, RedBlackTree.Color.BLACK,2),
                Triple.of(68, RedBlackTree.Color.RED,1),
                Triple.of(97, RedBlackTree.Color.RED,3),
                Triple.of(246, RedBlackTree.Color.RED,1),
                Triple.of(325, RedBlackTree.Color.BLACK,2)
        ));
        Assertions.assertTrue(compareTwoInorderArrays(tree.inOrderArray(),expected));

        Assertions.assertTrue(tree.delete(97));
        expected = new ArrayList<>(List.of(
                Triple.of(6, RedBlackTree.Color.BLACK,2),
                Triple.of(14, RedBlackTree.Color.RED,1),
                Triple.of(15, RedBlackTree.Color.BLACK,4),
                Triple.of(64, RedBlackTree.Color.BLACK,1),
                Triple.of(68, RedBlackTree.Color.RED,3),
                Triple.of(246, RedBlackTree.Color.RED,1),
                Triple.of(325, RedBlackTree.Color.BLACK,2)
        ));
        Assertions.assertTrue(compareTwoInorderArrays(tree.inOrderArray(),expected));

        Assertions.assertTrue(tree.delete(6));
        expected = new ArrayList<>(List.of(
                Triple.of(14, RedBlackTree.Color.BLACK,1),
                Triple.of(15, RedBlackTree.Color.BLACK,4),
                Triple.of(64, RedBlackTree.Color.BLACK,1),
                Triple.of(68, RedBlackTree.Color.RED,3),
                Triple.of(246, RedBlackTree.Color.RED,1),
                Triple.of(325, RedBlackTree.Color.BLACK,2)
        ));
        Assertions.assertTrue(compareTwoInorderArrays(tree.inOrderArray(),expected));

        Assertions.assertTrue(tree.delete(68));
        expected = new ArrayList<>(List.of(
                Triple.of(14, RedBlackTree.Color.BLACK,1),
                Triple.of(15, RedBlackTree.Color.BLACK,3),
                Triple.of(64, RedBlackTree.Color.BLACK,1),
                Triple.of(246, RedBlackTree.Color.RED,2),
                Triple.of(325, RedBlackTree.Color.BLACK,1)
        ));
        Assertions.assertTrue(compareTwoInorderArrays(tree.inOrderArray(),expected));
    }
}