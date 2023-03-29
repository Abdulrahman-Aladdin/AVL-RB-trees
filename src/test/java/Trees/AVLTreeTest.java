package Trees;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class AVLTreeTest {
    private AVLTree<Integer> avlTree;

    @BeforeEach
    void setUp() {
        avlTree = new AVLTree<>();
    }


    @Test
    void testInsert() {
        avlTree.insert(5);
        avlTree.insert(3);
        avlTree.insert(7);
        assertTrue(avlTree.isBalanced());
        assertEquals(3, avlTree.size());
        assertEquals(3, avlTree.getMinValue(avlTree.root));
        assertEquals(7, avlTree.getMaxValue(avlTree.root));
    }

    @Test
    void testInsertMultipleValues() {
        avlTree.insert(5);
        avlTree.insert(3);
        avlTree.insert(7);
        avlTree.insert(1);
        avlTree.insert(4);
        avlTree.insert(6);
        avlTree.insert(9);
        //System.out.println(avlTree.toString());
        assertTrue(avlTree.isBalanced());
        assertEquals(7, avlTree.size());
        assertEquals(Integer.valueOf(1), avlTree.getMinValue(avlTree.root));
        assertEquals(Integer.valueOf(9), avlTree.getMaxValue(avlTree.root));
    }

    @Test
    public void testRandomInsertAndDelete() {
        for (int i = 0; i < 1000; i++) {
            avlTree.insert(i);
        }
        for (int i = 0; i < 500; i++) {
            int value = (int) (Math.random() * 1000);
            avlTree.delete(value);
        }
        assertTrue(avlTree.isBalanced());
        assertTrue(avlTree.size() >= 500 && avlTree.size() <= 1000);
    }

    @Test
    public void testDuplicateInsert() {
        avlTree.insert(10);
        avlTree.insert(10);
        assertEquals( 1, avlTree.size());
    }

    @Test
    void testDelete() {
        avlTree.insert(5);
        avlTree.insert(3);
        avlTree.insert(7);
        avlTree.delete(5);
        assertTrue(avlTree.isBalanced());
        assertEquals(2, avlTree.size());
        assertEquals(3, avlTree.getMinValue(avlTree.root));
        assertEquals(7, avlTree.getMaxValue(avlTree.root));
    }

    @Test
    void testDeleteEmptyTree() {
        assertFalse(avlTree.delete(5));
        assertEquals(0, avlTree.size());
    }
    @Test
    void testDeleteNonexistentValue() {
        avlTree.insert(5);
        avlTree.insert(3);
        avlTree.insert(7);
        assertTrue(avlTree.isBalanced());
        assertFalse(avlTree.delete(2));
        assertEquals(3, avlTree.size());
    }

    @Test
    void testDeleteRoot() {
        avlTree.insert(5);
        avlTree.insert(3);
        avlTree.insert(7);
        assertTrue(avlTree.delete(5));
        assertTrue(avlTree.isBalanced());
        assertEquals(2, avlTree.size());
        assertEquals(Integer.valueOf(3), avlTree.getMinValue(avlTree.root));
        assertEquals(Integer.valueOf(7), avlTree.getMaxValue(avlTree.root));
    }

    @Test
    void testDeleteLeaf() {
        avlTree.insert(5);
        avlTree.insert(3);
        avlTree.insert(7);
        assertTrue(avlTree.delete(3));
        assertTrue(avlTree.isBalanced());
        assertEquals(2, avlTree.size());
        assertEquals(Integer.valueOf(5), avlTree.getMinValue(avlTree.root));
        assertEquals(Integer.valueOf(7), avlTree.getMaxValue(avlTree.root));
    }

    @Test
    void testDeleteNodeWithOneChild() {
        avlTree.insert(5);
        avlTree.insert(3);
        avlTree.insert(7);
        avlTree.insert(4);
        assertTrue(avlTree.isBalanced());
        assertTrue(avlTree.delete(3));
        assertEquals(3, avlTree.size());
        assertEquals(Integer.valueOf(4), avlTree.getMinValue(avlTree.root));
        assertEquals(Integer.valueOf(7), avlTree.getMaxValue(avlTree.root));
    }

    @Test
    void testDeleteNodeWithTwoChildren() {
        avlTree.insert(5);
        avlTree.insert(3);
        avlTree.insert(7);
        avlTree.insert(4);
        avlTree.insert(6);
        assertTrue(avlTree.delete(7));
        assertTrue(avlTree.isBalanced());
        assertEquals(4, avlTree.size());
        assertEquals(Integer.valueOf(3), avlTree.getMinValue(avlTree.root));
        assertEquals(Integer.valueOf(6), avlTree.getMaxValue(avlTree.root));
    }
    @Test
    void testDeleteCornerCases() {
        avlTree.insert(Integer.MIN_VALUE);
        avlTree.insert(Integer.MAX_VALUE);
        avlTree.insert(0);
        avlTree.insert(-1);
        avlTree.insert(1);
        avlTree.insert(-2);
        avlTree.insert(2);
        assertTrue(avlTree.isBalanced());
        assertTrue(avlTree.delete(Integer.MIN_VALUE));
        assertTrue(avlTree.delete(Integer.MAX_VALUE));
        assertTrue(avlTree.delete(0));
        assertTrue(avlTree.delete(-1));
        assertTrue(avlTree.delete(1));
        assertTrue(avlTree.delete(-2));
        assertTrue(avlTree.delete(2));
        assertTrue(avlTree.isBalanced());
        assertEquals(0, avlTree.size());
    }
    @Test
    void testDeleteWithRotation() {
        avlTree.insert(10);
        avlTree.insert(6);
        avlTree.insert(14);
        avlTree.insert(4);
        avlTree.insert(8);
        avlTree.insert(12);
        avlTree.insert(16);
        avlTree.insert(3);
        avlTree.insert(5);
        avlTree.insert(7);
        avlTree.insert(9);
        avlTree.insert(11);
        avlTree.insert(13);
        avlTree.insert(15);
        avlTree.insert(17);

        // Before deletion
        assertEquals(15, avlTree.size());
        assertEquals(Integer.valueOf(3), avlTree.getMinValue(avlTree.root));
        assertEquals(Integer.valueOf(17), avlTree.getMaxValue(avlTree.root));
        assertEquals(4, avlTree.height());
        assertTrue(avlTree.isBalanced());
        assertTrue(avlTree.delete(14));

        // After deletion
        assertEquals(14, avlTree.size());
        assertEquals(Integer.valueOf(3), avlTree.getMinValue(avlTree.root));
        assertEquals(Integer.valueOf(17), avlTree.getMaxValue(avlTree.root));
        assertEquals(4, avlTree.height());
    }
    @Test
    void testSearchEmptyTree() {
        assertFalse(avlTree.search(5));
    }
    @Test
    void testSearchNonExistentValue() {
        avlTree.insert(5);
        avlTree.insert(3);
        avlTree.insert(7);
        assertFalse(avlTree.search(2));
    }

    @Test
    void testSearchExistingValue() {
        avlTree.insert(5);
        avlTree.insert(3);
        avlTree.insert(7);
        assertTrue(avlTree.search(3));
    }

    @Test
    public void testEmpty() {
        assertTrue(avlTree.isEmpty());
    }




}