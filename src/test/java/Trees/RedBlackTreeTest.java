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
    @Test
    void Test2(){
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

}