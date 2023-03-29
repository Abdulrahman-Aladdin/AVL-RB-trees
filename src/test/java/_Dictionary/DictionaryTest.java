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

        // search for a string in dictionary
        Assertions.assertTrue(dictionary.search("mohamed elhabiby"));

        // delete string in dictionary
        Assertions.assertTrue(dictionary.delete("hussein mohamed"));

        // search for a string deleted form dictionary
        Assertions.assertFalse(dictionary.search("hussein mohamed"));

        // search for a string not in dictionary
        Assertions.assertFalse(dictionary.search("amr ahmed"));

        // delete a string not in dictionary
        Assertions.assertFalse(dictionary.delete("hussein khaled"));

        // insert a string already in dictionary
        Assertions.assertFalse(dictionary.insert("mohamed anwar"));
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

        // search for a string in dictionary
        Assertions.assertTrue(dictionary.search("mohamed elhabiby"));

        // delete string in dictionary
        Assertions.assertTrue(dictionary.delete("hussein mohamed"));

        // search for a string deleted form dictionary
        Assertions.assertFalse(dictionary.search("hussein mohamed"));

        // search for a string not in dictionary
        Assertions.assertFalse(dictionary.search("amr ahmed"));

        // delete a string not in dictionary
        Assertions.assertFalse(dictionary.delete("hussein khaled"));

        // insert a string already in dictionary
        Assertions.assertFalse(dictionary.insert("mohamed anwar"));
    }

}