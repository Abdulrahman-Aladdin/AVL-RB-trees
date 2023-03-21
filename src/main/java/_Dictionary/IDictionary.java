package _Dictionary;

import org.apache.commons.lang3.tuple.Pair;

public interface IDictionary {

    boolean insert(String word);
    boolean delete(String word);
    boolean search(String word);

    Pair<Integer,Integer> batchInsert(String path);
    Pair<Integer,Integer> batchDelete(String path);

    int size();
    int treeHeight();

}
