package _Dictionary;

import Trees.IBST;
import org.apache.commons.lang3.tuple.Pair;

import java.io.*;

public class Dictionary implements IDictionary {

    IBST<String> tree;

    public Dictionary(IBST<String> tree){
        this.tree = tree;
    }

    @Override
    public boolean insert(String word) {
        return tree.insert(word);
    }

    @Override
    public boolean delete(String word) {
        return tree.delete(word);
    }

    @Override
    public boolean search(String word) {
        return tree.search(word);
    }

    @Override
    public Pair<Integer,Integer> batchInsert(String path) {

        int numberOfWordsInserted = 0;
        int numberOfWordsExisting = 0;

        try {
            File file = new File(path);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String line;
            while ((line = br.readLine()) != null) {
                if (tree.insert(line)) numberOfWordsInserted++;
                else numberOfWordsExisting++;
            }

            br.close();
            fr.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return Pair.of(numberOfWordsInserted,numberOfWordsExisting);
    }

    @Override
    public Pair<Integer,Integer> batchDelete(String path) {

        int numberOfWordsDeleted = 0;
        int numberOfWordsExisting = 0;

        try {
            File file = new File(path);

            if (file == null) return null;

            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String line;
            while ((line = br.readLine()) != null) {
                if (tree.delete(line)) numberOfWordsDeleted++;
                else numberOfWordsExisting++;
            }

            br.close();
            fr.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return Pair.of(numberOfWordsDeleted,numberOfWordsExisting);
    }

    @Override
    public int size() {
        return tree.size();
    }

    @Override
    public int treeHeight() {
        return tree.height();
    }

    public void printDictionary(){
        tree.printInOrder();
    }
}
