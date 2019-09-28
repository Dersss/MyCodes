import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.queue.Queue;
import components.queue.Queue1L;
import components.set.Set;

/**
 * JUnit test fixture for {@code Set<String>}'s constructor and kernel methods.
 *
 * @author Put your name here
 *
 */
public abstract class SetTest {

    /**
     * Invokes the appropriate {@code Set} constructor and returns the result.
     *
     * @return the new set
     * @ensures constructorTest = {}
     */
    protected abstract Set<String> constructorTest();

    /**
     * Invokes the appropriate {@code Set} constructor and returns the result.
     *
     * @return the new set
     * @ensures constructorRef = {}
     */
    protected abstract Set<String> constructorRef();

    /**
     * Creates and returns a {@code Set<String>} of the implementation under
     * test type with the given entries.
     *
     * @param args
     *            the entries for the set
     * @return the constructed set
     * @requires [every entry in args is unique]
     * @ensures createFromArgsTest = [entries in args]
     */
    private Set<String> createFromArgsTest(String... args) {
        Set<String> set = this.constructorTest();
        for (String s : args) {
            assert !set.contains(
                    s) : "Violation of: every entry in args is unique";
            set.add(s);
        }
        return set;
    }

    /**
     * Creates and returns a {@code Set<String>} of the reference implementation
     * type with the given entries.
     *
     * @param args
     *            the entries for the set
     * @return the constructed set
     * @requires [every entry in args is unique]
     * @ensures createFromArgsRef = [entries in args]
     */
    private Set<String> createFromArgsRef(String... args) {
        Set<String> set = this.constructorRef();
        for (String s : args) {
            assert !set.contains(
                    s) : "Violation of: every entry in args is unique";
            set.add(s);
        }
        return set;
    }

    // TODO - add test cases for constructor, add, remove, removeAny, contains, and size

    @Test
    public final void testMoveToFront() {
        Queue<String> q1 = new Queue1L<>();
        Queue<String> q2 = new Queue1L<>();
        q1.enqueue("alpha");
        q1.enqueue("bravo");
        q1.enqueue("charlie");
        q2.enqueue("bravo");
        q2.enqueue("charlie");
        q2.enqueue("alpha");
        String move = "bravo";
        Set2.moveToFront(q1, move);
        assertEquals(q1, q2);
    }

    @Test
    public final void testMoveToFront2() {
        Queue<String> q1 = new Queue1L<>();
        Queue<String> q2 = new Queue1L<>();
        q1.enqueue("alpha");
        q1.enqueue("bravo");
        q1.enqueue("charlie");
        q2.enqueue("charlie");
        q2.enqueue("alpha");
        q2.enqueue("bravo");
        String move = "charlie";
        Set2.moveToFront(q1, move);
        assertEquals(q1, q2);
    }

    @Test
    public final void testAddToEmptySet() {
        /*
         * Set up variables
         */
        Set<String> s1 = this.createFromArgsTest();
        Set<String> s2 = this.createFromArgsRef("alpha");
        String move = "alpha";
        s1.add(move);

        assertEquals(s2, s1);
    }

    @Test
    public final void testAddToNonEmptySet() {
        /*
         * Set up variables
         */
        Set<String> s1 = this.createFromArgsTest("alpha");
        Set<String> s2 = this.createFromArgsRef("alpha", "bravo");
        String move = "bravo";
        s1.add(move);

        assertEquals(s2, s1);
    }

    @Test
    public final void testAddToNonEmptySet2() {
        /*
         * Set up variables
         */
        Set<String> s1 = this.createFromArgsTest("alpha", "bravo");
        Set<String> s2 = this.createFromArgsRef("alpha", "bravo", "charlie");
        String move = "charlie";
        s1.add(move);

        assertEquals(s2, s1);
    }

    @Test
    public final void testRemoveOneItemSet() {
        /*
         * Set up variables
         */
        Set<String> s1 = this.createFromArgsTest("alpha");
        Set<String> s2 = this.createFromArgsRef();
        String move = "alpha";
        String temp = s1.remove(move);

        assertEquals(s2, s1);
        assertEquals(move, temp);
    }

    @Test
    public final void testRemoveTwoItemSet() {
        /*
         * Set up variables
         */
        Set<String> s1 = this.createFromArgsTest("alpha", "bravo");
        Set<String> s2 = this.createFromArgsRef("alpha");
        String move = "bravo";
        s1.remove(move);

        assertEquals(s2, s1);
    }

    @Test
    public final void testRemoveThreeItemSet() {
        /*
         * Set up variables
         */
        Set<String> s1 = this.createFromArgsTest("alpha", "bravo", "charlie");
        Set<String> s2 = this.createFromArgsRef("alpha", "bravo");
        String move = "charlie";
        s1.remove(move);

        assertEquals(s2, s1);
    }

    @Test
    public final void testRemoveAny() {
        /*
         * Set up variables
         */
        Set<String> s1 = this.createFromArgsTest("alpha", "bravo", "charlie");
        Set<String> s2 = this.createFromArgsRef("alpha", "bravo", "charlie");
        String str = s1.removeAny();
        if (s2.contains(str)) {
            s2.remove(str);
        }
        assertEquals(s2, s1);
    }

    @Test
    public final void testRemoveAny2() {
        /*
         * Set up variables
         */
        Set<String> s1 = this.createFromArgsTest("alpha", "bravo", "charlie",
                "delta", "echo", "foxtrot");
        Set<String> s2 = this.createFromArgsRef("alpha", "bravo", "charlie",
                "delta", "echo", "foxtrot");
        String str = s1.removeAny();
        if (s2.contains(str)) {
            s2.remove(str);
        }
        assertEquals(s2, s1);
    }

    @Test
    public final void testRemoveAny3() {
        /*
         * Set up variables
         */
        Set<String> s1 = this.createFromArgsTest("alpha");
        Set<String> s2 = this.createFromArgsRef();
        String str = s1.removeAny();
        assertEquals(s2, s1);
    }

    @Test
    public final void testContains() {
        /*
         * Set up variables
         */
        Set<String> s1 = this.createFromArgsTest("alpha", "bravo", "charlie",
                "delta", "echo", "foxtrot");
        String test = "delta";
        boolean contn = s1.contains(test);
        assertEquals(contn, true);

    }

    @Test
    public final void testContains2() {
        /*
         * Set up variables
         */
        Set<String> s1 = this.createFromArgsTest("alpha", "bravo", "charlie",
                "delta", "echo", "foxtrot");
        String test = "kilo";
        boolean contn = s1.contains(test);
        assertEquals(contn, false);

    }

    @Test
    public final void testContains3() {
        /*
         * Set up variables
         */
        Set<String> s1 = this.createFromArgsTest("alpha", "bravo", "charlie",
                "delta", "echo", "foxtrot");
        String test = "foxtrot";
        boolean contn = s1.contains(test);
        assertEquals(contn, true);

    }

    @Test
    public final void testSize() {
        /*
         * Set up variables
         */
        Set<String> s1 = this.createFromArgsTest("alpha", "bravo", "charlie",
                "delta", "echo", "foxtrot");
        int size1 = s1.size();
        assertEquals(size1, 6);

    }

    @Test
    public final void testSize2() {
        /*
         * Set up variables
         */
        Set<String> s1 = this.createFromArgsTest();
        int size1 = s1.size();
        assertEquals(size1, 0);

    }

    @Test
    public final void testSize3() {
        /*
         * Set up variables
         */
        Set<String> s1 = this.createFromArgsTest("alpha");
        int size1 = s1.size();
        assertEquals(size1, 1);

    }
}
