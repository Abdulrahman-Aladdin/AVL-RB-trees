package Trees;

public interface IBST<T extends Comparable<T>> {

    boolean insert(T element);

    boolean delete(T element);

    boolean search(T element);

    int size();

    int height();

    void printInOrder();
}
