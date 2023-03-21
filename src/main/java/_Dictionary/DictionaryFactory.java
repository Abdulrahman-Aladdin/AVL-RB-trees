package _Dictionary;

import Trees.AVLTree;
import Trees.IBST;
import Trees.RedBlackTree;
import Trees.Tree;

public class DictionaryFactory {

    public static Dictionary getDictionary(Tree treeType){
        IBST<String> tree;
        tree = switch (treeType){
            case AVL -> new AVLTree<>();
            case RED_BLACK -> new RedBlackTree<>();
        };
        return new Dictionary(tree);
    }
}
