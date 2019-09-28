import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.set.Set;

/**
 * JUnit test fixture for {@code Set<String>}'s constructor and kernel methods.
 *
 * @author Put your name here
 *
 */
public abstract class SetTest {

    /**
     * Invokes the appropriate {@code Set} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new set
     * @ensures constructorTest = {}
     */
    protected abstract Set<String> constructorTest();

    /**
     * Invokes the appropriate {@code Set} constructor for the reference
     * implementation and returns the result.
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
    public final void testEmptyConstrucrtor() {
        Set<String> s1 = this.createFromArgsTest();
        Set<String> s1EXP = this.createFromArgsRef();
        assertEquals(s1EXP, s1);

    }

    @Test
    public final void testNonEmptyConstrucrtor() {
        Set<String> s1 = this.createFromArgsTest("a");
        Set<String> s1EXP = this.createFromArgsRef("a");
        assertEquals(s1EXP, s1);

    }

    @Test
    public final void testNonEmptyConstrucrtor_2() {
        Set<String> s1 = this.createFromArgsTest("a", "b", "c", "d", "e", "f");
        Set<String> s1EXP = this.createFromArgsRef("a", "b", "c", "d", "e",
                "f");
        assertEquals(s1EXP, s1);

    }

    @Test
    public final void testAdd_1() {
        Set<String> s1 = this.createFromArgsTest();
        Set<String> s1EXP = this.createFromArgsRef("d");
        s1.add("d");

        assertEquals(s1EXP, s1);
    }

    @Test
    public final void testAdd_2() {
        Set<String> s1 = this.createFromArgsTest("a", "b", "c");
        Set<String> s1EXP = this.createFromArgsRef("a", "b", "c", "d");
        s1.add("d");

        assertEquals(s1EXP, s1);
    }

    @Test
    public final void testAdd_3() {
        Set<String> s1 = this.createFromArgsTest("a", "b", "c", "e", "f");
        Set<String> s1EXP = this.createFromArgsRef("a", "b", "c", "d", "e",
                "f");
        s1.add("d");

        assertEquals(s1EXP, s1);
    }

    @Test
    public final void testAdd_4() {
        Set<String> s1 = this.createFromArgsTest("a", "b", "c", "e", "f", "z",
                "q", "w");
        Set<String> s1EXP = this.createFromArgsRef("a", "b", "c", "d", "e", "f",
                "z", "q", "w");
        s1.add("d");

        assertEquals(s1EXP, s1);
    }

    @Test
    public final void testRemove_1() {
        Set<String> s1 = this.createFromArgsTest("d");
        Set<String> s1EXP = this.createFromArgsRef();
        s1.remove("d");

        assertEquals(s1EXP, s1);
    }

    @Test
    public final void testRemove_2() {
        Set<String> s1 = this.createFromArgsTest("d", "c");
        Set<String> s1EXP = this.createFromArgsRef("c");
        s1.remove("d");

        assertEquals(s1EXP, s1);
    }

    @Test
    public final void testRemove_3() {
        Set<String> s1 = this.createFromArgsTest("d", "c", "a");
        Set<String> s1EXP = this.createFromArgsRef("c", "a");
        s1.remove("d");

        assertEquals(s1EXP, s1);
    }

    @Test
    public final void testRemoveAny_1() {
        Set<String> s1 = this.createFromArgsTest("d");
        Set<String> s1EXP = this.createFromArgsRef();
        s1.removeAny();

        assertEquals(s1EXP, s1);
    }

    @Test
    public final void testRemoveAny_2() {
        Set<String> s1 = this.createFromArgsTest("d", "c");
        Set<String> s1EXP = this.createFromArgsRef("d", "c");
        String hold = s1.removeAny();
        s1EXP.remove(hold);

        assertEquals(s1EXP, s1);
    }

    @Test
    public final void testRemoveAny_3() {
        Set<String> s1 = this.createFromArgsTest("d", "c", "a", "e", "w", "t");
        Set<String> s1EXP = this.createFromArgsRef("d", "c", "a", "e", "w",
                "t");
        String hold = s1.removeAny();
        s1EXP.remove(hold);

        assertEquals(s1EXP, s1);
    }

    @Test
    public final void testContains_1() {
        Set<String> s1 = this.createFromArgsTest("d");
        boolean contain = s1.contains("d");

        assertEquals(true, contain);
    }

    @Test
    public final void testContains_2() {
        Set<String> s1 = this.createFromArgsTest("d");
        boolean contain = s1.contains("e");

        assertEquals(false, contain);
    }

    @Test
    public final void testContains_3() {
        Set<String> s1 = this.createFromArgsTest("d", "l", "m", "r", "p", "w",
                "z");
        boolean contain = s1.contains("r");

        assertEquals(true, contain);
    }

    @Test
    public final void testContains_4() {
        Set<String> s1 = this.createFromArgsTest();
        boolean contain = s1.contains("r");

        assertEquals(false, contain);
    }

    @Test
    public final void testContains_5() {
        Set<String> s1 = this.createFromArgsTest("d", "l", "m", "r", "p", "w",
                "z");
        boolean contain = s1.contains("c");

        assertEquals(false, contain);
    }

    @Test
    public final void testSize_1() {
        Set<String> s1 = this.createFromArgsTest("d");
        int size = s1.size();

        assertEquals(1, size);
    }

    @Test
    public final void testSize_2() {
        Set<String> s1 = this.createFromArgsTest("d", "l", "m", "r", "p", "w",
                "z");
        int size = s1.size();

        assertEquals(7, size);
    }

    @Test
    public final void testSize_3() {
        Set<String> s1 = this.createFromArgsTest("d", "l", "m", "r", "p", "w",
                "z", "a", "b", "g", "s");
        int size = s1.size();

        assertEquals(11, size);
    }

    @Test
    public final void testSize_4() {
        Set<String> s1 = this.createFromArgsTest();
        int size = s1.size();

        assertEquals(0, size);
    }
}
