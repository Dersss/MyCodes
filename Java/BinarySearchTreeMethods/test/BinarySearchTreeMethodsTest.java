import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.binarytree.BinaryTree;
import components.binarytree.BinaryTree1;

/**
 * JUnit test fixture for {@code BinarySearchTreeMethods}'s static methods
 * isInTree (and removeSmallest).
 */
public final class BinarySearchTreeMethodsTest {

    /**
     * Constructs and return a BST created by inserting the given {@code args}
     * into an empty tree in the order in which they are provided.
     *
     * @param args
     *            the {@code String}s to be inserted in the tree
     * @return the BST with the given {@code String}s
     * @requires [the Strings in args are all distinct]
     * @ensures createBSTFromArgs = [the BST with the given Strings]
     */
    private static BinaryTree<String> createBSTFromArgs(String... args) {
        BinaryTree<String> t = new BinaryTree1<String>();
        for (String s : args) {
            BinaryTreeUtility.insertInTree(t, s);
        }
        return t;
    }

    @Test
    public void sampleTest() {
        /*
         * Set up variables
         */
        BinaryTree<String> t1 = createBSTFromArgs("b", "a", "c");
        BinaryTree<String> t2 = createBSTFromArgs("b", "a", "c");
        /*
         * Call method under test
         */
        boolean inTree = BinarySearchTreeMethods.isInTree(t1, "a");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(true, inTree);
        assertEquals(t2, t1);
    }

    // TODO: add here other test cases for BinarySearchTreeMethods.isInTree
    // (and for BinarySearchTreeMethods.removeSmallest)

    @Test
    public void isInTreeTest_1_True() {
        /*
         * Set up variables
         */
        BinaryTree<String> t1 = createBSTFromArgs("b", "a", "c", "d", "z");
        BinaryTree<String> t2 = createBSTFromArgs("b", "a", "c", "d", "z");
        /*
         * Call method under test
         */
        boolean inTree = BinarySearchTreeMethods.isInTree(t1, "a");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(true, inTree);
        assertEquals(t2, t1);
    }

    @Test
    public void isInTreeTest_2_False() {
        /*
         * Set up variables
         */
        BinaryTree<String> t1 = createBSTFromArgs("b", "a", "c", "d", "z");
        BinaryTree<String> t2 = createBSTFromArgs("b", "a", "c", "d", "z");
        /*
         * Call method under test
         */
        boolean inTree = BinarySearchTreeMethods.isInTree(t1, "h");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(false, inTree);
        assertEquals(t2, t1);
    }

    @Test
    public void isInTreeTest_3_False() {
        /*
         * Set up variables
         */
        BinaryTree<String> t1 = createBSTFromArgs("b", "a", "c", "d", "z");
        BinaryTree<String> t2 = createBSTFromArgs("b", "a", "c", "d", "z");
        /*
         * Call method under test
         */
        boolean inTree = BinarySearchTreeMethods.isInTree(t1, "w");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(false, inTree);
        assertEquals(t2, t1);
    }

    @Test
    public void isInTreeTest_4_True() {
        /*
         * Set up variables
         */
        BinaryTree<String> t1 = createBSTFromArgs("b", "a", "c", "d", "z");
        BinaryTree<String> t2 = createBSTFromArgs("b", "a", "c", "d", "z");
        /*
         * Call method under test
         */
        boolean inTree = BinarySearchTreeMethods.isInTree(t1, "d");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(true, inTree);
        assertEquals(t2, t1);
    }

    @Test
    public void removeSmallest_1() {
        /*
         * Set up variables
         */
        BinaryTree<String> t1 = createBSTFromArgs("b", "a", "c", "d", "z");
        BinaryTree<String> t2 = createBSTFromArgs("b", "c", "d", "z");
        /*
         * Call method under test
         */
        String hold = BinarySearchTreeMethods.removeSmallest(t1);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals("a", hold);
        assertEquals(t2, t1);
    }

    @Test
    public void removeSmallest_2() {
        /*
         * Set up variables
         */
        BinaryTree<String> t1 = createBSTFromArgs("b", "c", "d", "z");
        BinaryTree<String> t2 = createBSTFromArgs("c", "d", "z");
        /*
         * Call method under test
         */
        String hold = BinarySearchTreeMethods.removeSmallest(t1);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals("b", hold);
        assertEquals(t2, t1);
    }

    @Test
    public void removeSmallest_3() {
        /*
         * Set up variables
         */
        BinaryTree<String> t1 = createBSTFromArgs("b", "a", "c", "d", "z");
        BinaryTree<String> t2 = createBSTFromArgs("b", "c", "d", "z");
        /*
         * Call method under test
         */
        String hold = BinarySearchTreeMethods.removeSmallest(t1);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals("a", hold);
        assertEquals(t2, t1);
    }
}
