package _Dictionary;

import Trees.IBST;
import Trees.Tree;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DictionaryTest {

    Dictionary dictionary;

    @Test
    void AVLTest(){
        dictionary = DictionaryFactory.getDictionary(Tree.AVL);

        // insertions
        Assertions.assertTrue(dictionary.insert("mohamed elhabiby"));
        Assertions.assertTrue(dictionary.insert("hussein mohamed"));
        Assertions.assertTrue(dictionary.insert("mohamed anwar"));
        Assertions.assertTrue(dictionary.insert("abdo alaa"));
        Assertions.assertTrue(dictionary.insert("mohamed arous"));

        // check size
        Assertions.assertEquals(5,dictionary.size());

        // check height
        Assertions.assertEquals(3,dictionary.treeHeight());

        // search for a string in dictionary
        Assertions.assertTrue(dictionary.search("mohamed elhabiby"));

        // delete string in dictionary
        Assertions.assertTrue(dictionary.delete("hussein mohamed"));

        // check size
        Assertions.assertEquals(4,dictionary.size());

        // check height
        Assertions.assertEquals(3,dictionary.treeHeight());

        // search for a string deleted form dictionary
        Assertions.assertFalse(dictionary.search("hussein mohamed"));

        // search for a string not in dictionary
        Assertions.assertFalse(dictionary.search("amr ahmed"));

        // delete a string not in dictionary
        Assertions.assertFalse(dictionary.delete("hussein khaled"));

        // check size
        Assertions.assertEquals(4,dictionary.size());

        // check height
        Assertions.assertEquals(3,dictionary.treeHeight());

        // insert a string already in dictionary
        Assertions.assertFalse(dictionary.insert("mohamed anwar"));

        // check size
        Assertions.assertEquals(4,dictionary.size());

        // check height
        Assertions.assertEquals(3,dictionary.treeHeight());
    }

    @Test
    void RedBlackTest(){
        dictionary = DictionaryFactory.getDictionary(Tree.RED_BLACK);

        // insertions
        Assertions.assertTrue(dictionary.insert("mohamed elhabiby"));
        Assertions.assertTrue(dictionary.insert("hussein mohamed"));
        Assertions.assertTrue(dictionary.insert("mohamed anwar"));
        Assertions.assertTrue(dictionary.insert("abdo alaa"));
        Assertions.assertTrue(dictionary.insert("mohamed arous"));

        // check size
        Assertions.assertEquals(5,dictionary.size());

        // check height
        Assertions.assertEquals(3,dictionary.treeHeight());

        // search for a string in dictionary
        Assertions.assertTrue(dictionary.search("mohamed elhabiby"));

        // delete string in dictionary
        Assertions.assertTrue(dictionary.delete("hussein mohamed"));

        // check size
        Assertions.assertEquals(4,dictionary.size());

        // check height
        Assertions.assertEquals(3,dictionary.treeHeight());

        // search for a string deleted form dictionary
        Assertions.assertFalse(dictionary.search("hussein mohamed"));

        // search for a string not in dictionary
        Assertions.assertFalse(dictionary.search("amr ahmed"));

        // delete a string not in dictionary
        Assertions.assertFalse(dictionary.delete("hussein khaled"));

        // check size
        Assertions.assertEquals(4,dictionary.size());

        // check height
        Assertions.assertEquals(3,dictionary.treeHeight());

        // insert a string already in dictionary
        Assertions.assertFalse(dictionary.insert("mohamed anwar"));

        // check size
        Assertions.assertEquals(4,dictionary.size());

        // check height
        Assertions.assertEquals(3,dictionary.treeHeight());
    }

    @Test
    void testBatchAVL(){
        dictionary = DictionaryFactory.getDictionary(Tree.AVL);

        assertEquals(0,dictionary.size());

        var insertResult = dictionary.batchInsert("testBatchInsert.txt");
        assertEquals(insertResult.getLeft(),10);
        assertEquals(insertResult.getRight(),3);

        assertEquals(10,dictionary.size());

        var deleteResult = dictionary.batchDelete("testBatchDelete.txt");
        assertEquals(deleteResult.getLeft(),4);
        assertEquals(deleteResult.getRight(),2);

        assertEquals(6,dictionary.size());
    }

    @Test
    void testBatchRedBlack(){
        dictionary = DictionaryFactory.getDictionary(Tree.RED_BLACK);

        assertEquals(0,dictionary.size());

        var insertResult = dictionary.batchInsert("testBatchInsert.txt");
        assertEquals(insertResult.getLeft(),10);
        assertEquals(insertResult.getRight(),3);

        assertEquals(10,dictionary.size());

        var deleteResult = dictionary.batchDelete("testBatchDelete.txt");
        assertEquals(deleteResult.getLeft(),4);
        assertEquals(deleteResult.getRight(),2);

        assertEquals(6,dictionary.size());
    }

}