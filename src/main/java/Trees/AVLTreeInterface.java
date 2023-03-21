package Trees;

public interface AVLTreeInterface <T extends Comparable<T>>{

    /**
     * Inserts a new value into the AVL tree.
     * @param value the value which want to insert
     * @return true if the value was found and insert, false otherwise
     */
    void insert (T value);

    /**
     * Removes the value from the AVL tree.
     * @return true if the value was found and removed, false otherwise
     */
    boolean delete(T value);

    /**
     * Returns the value in the AVL tree, or null if the value is not found.
     * @return the value associated with the key, or null if the key is not found
     */
    boolean search(T value);


    /**
     * Returns the height of the AVL tree.
     * @return the height of the AVL tree
     */
    int getHeight();

    /**
     * Returns true if the AVL tree is empty, false otherwise.
     * @return true if the AVL tree is empty, false otherwise
     */
    boolean isEmpty();
}