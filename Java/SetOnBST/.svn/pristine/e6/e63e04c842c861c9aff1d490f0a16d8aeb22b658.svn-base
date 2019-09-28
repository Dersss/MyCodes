import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.set.Set;

/**
 * JUnit test fixture for {@code Set<String>}'s constructor and kernel methods.
 *
 * @author Andrew Schneider, Hitesh, and Eric Sullivan
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

    /**
     * Test for constructor with no arguments.
     */
    @Test
    public final void testConstructorEmpty() {

        Set<String> s = this.createFromArgsTest();
        Set<String> expectedS = this.createFromArgsRef();
        assertEquals(expectedS, s);
    }

    /**
     * Test for constructor [a, b, d, f].
     */
    @Test
    public final void testConstructor() {

        Set<String> s = this.createFromArgsTest("a", "b", "d", "f");
        Set<String> expectedS = this.createFromArgsRef("a", "b", "d", "f");
        assertEquals(expectedS, s);
    }

    /**
     * Test adding 5 to empty set.
     */
    @Test
    public final void testAddToEmptySet() {

        Set<String> s = this.createFromArgsTest();
        Set<String> expectedS = this.createFromArgsRef("5");
        s.add("5");
        assertEquals(expectedS, s);
    }

    /**
     * Test adding an empty string to a set, [0, 1, 2, 3].
     */
    @Test
    public final void testAddEmpty() {

        Set<String> s = this.createFromArgsTest("0", "1", "2", "3");
        Set<String> expectedS = this.createFromArgsRef("", "0", "1", "2", "3");
        s.add("");
        assertEquals(expectedS, s);
    }

    /**
     * Test adding 4 to a set, [0, 1, 2, 3].
     */
    @Test
    public final void testAdd1() {

        Set<String> s = this.createFromArgsTest("0", "1", "2", "3");
        Set<String> expectedS = this.createFromArgsRef("4", "0", "1", "2", "3");
        s.add("4");
        assertEquals(expectedS, s);
    }

    /**
     * Test adding ef to a set, [a, b, c, d].
     */
    @Test
    public final void testAdd2() {

        Set<String> s = this.createFromArgsTest("a", "b", "c", "d");
        Set<String> expectedS = this.createFromArgsRef("a", "b", "c", "d",
                "ef");
        s.add("ef");
        assertEquals(expectedS, s);
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

    /**
     * Test removing an empty string from a set, [ , a].
     */
    @Test
    public final void testRemoveEmpty() {

        Set<String> s = this.createFromArgsTest("", "a");
        Set<String> expectedS = this.createFromArgsRef("a");
        s.remove("");
        assertEquals(expectedS, s);
    }

    /**
     * Test removing a to be an empty set which is originally [a].
     */
    @Test
    public final void testRemoveToBeEmpty() {

        Set<String> s = this.createFromArgsTest("a");
        Set<String> expectedS = this.createFromArgsRef();
        String r = s.remove("a");
        assertEquals(expectedS, s);
        assertEquals("a", r);
    }

    /**
     * Test removing 2 from a set, [0, 1, 2, 3].
     */
    @Test
    public final void testRemove1() {

        Set<String> s = this.createFromArgsTest("0", "1", "2", "3");
        Set<String> expectedS = this.createFromArgsRef("0", "1", "3");
        String r = s.remove("2");
        assertEquals(expectedS, s);
        assertEquals("2", r);
    }

    /**
     * Test removing d from a set, [a, b, c, d].
     */
    @Test
    public final void testRemove2() {

        Set<String> s = this.createFromArgsTest("a", "b", "c", "d");
        Set<String> expectedS = this.createFromArgsRef("a", "b", "c");
        String r = s.remove("d");
        assertEquals(expectedS, s);
        assertEquals("d", r);
    }

    /**
     * Test removing any of a set of size one, [a].
     */
    @Test
    public final void testRemoveAnyToEmpty() {

        Set<String> s = this.createFromArgsTest("a");
        Set<String> expectedS = this.createFromArgsRef();
        String r = s.removeAny();
        assertEquals(expectedS, s);
        assertEquals("a", r);
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

    /**
     * Test if an empty set contains a to return false.
     */
    @Test
    public final void testContainsEmpty() {

        Set<String> s = this.createFromArgsTest();
        Set<String> expectedS = this.createFromArgsRef();
        boolean testC = s.contains("a");
        assertEquals(false, testC);
        assertEquals(expectedS, s);
    }

    /**
     * Test if a set, [a, , b], has an empty string to get true.
     */
    @Test
    public final void testContainsHasAnEmpty() {

        Set<String> s = this.createFromArgsTest("a", "", "b");
        Set<String> expectedS = this.createFromArgsRef("a", "", "b");
        boolean testC = s.contains("");
        assertEquals(true, testC);
        assertEquals(expectedS, s);
    }

    /**
     * Test if a set, [0, 1, 2, 3], contains 2 to get true.
     */
    @Test
    public final void testContainsValid() {

        Set<String> s = this.createFromArgsTest("0", "1", "2", "3");
        Set<String> expectedS = this.createFromArgsRef("0", "1", "2", "3");
        boolean testC = s.contains("2");
        assertEquals(true, testC);
        assertEquals(expectedS, s);
    }

    /**
     * Test is a set, [a, b, c, d], contains e to get false.
     */
    @Test
    public final void testContainsInvalid() {

        Set<String> s = this.createFromArgsTest("a", "b", "c", "d");
        Set<String> expectedS = this.createFromArgsRef("a", "b", "c", "d");
        boolean testC = s.contains("e");
        assertEquals(false, testC);
        assertEquals(expectedS, s);
    }

    /**
     * Test the size of a set which is an empty set.
     */
    @Test
    public final void testSizeEmpty() {

        Set<String> s = this.createFromArgsTest();
        Set<String> expectedS = this.createFromArgsRef();
        int size = s.size();
        assertEquals(0, size);
        assertEquals(expectedS, s);
    }

    /**
     * Test the size of set, [0, 1, 2, 3].
     */
    @Test
    public final void testSize() {

        Set<String> s = this.createFromArgsTest("0", "1", "2", "3");
        Set<String> expectedS = this.createFromArgsRef("0", "1", "2", "3");
        int size = s.size();
        assertEquals(4, size);
        assertEquals(expectedS, s);
    }

}
