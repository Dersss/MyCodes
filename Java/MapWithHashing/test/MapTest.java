import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.map.Map;

/**
 * JUnit test fixture for {@code Map<String, String>}'s constructor and kernel
 * methods.
 *
 * @author Andrew Schneider
 * @author Eric Sullivan
 * @author Hitesh Bavisetty
 *
 */
public abstract class MapTest {

    /**
     * Invokes the appropriate {@code Map} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new map
     * @ensures constructorTest = {}
     */
    protected abstract Map<String, String> constructorTest();

    /**
     * Invokes the appropriate {@code Map} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new map
     * @ensures constructorRef = {}
     */
    protected abstract Map<String, String> constructorRef();

    /**
     *
     * Creates and returns a {@code Map<String, String>} of the implementation
     * under test type with the given entries.
     *
     * @param args
     *            the (key, value) pairs for the map
     * @return the constructed map
     * @requires <pre>
     * [args.length is even]  and
     * [the 'key' entries in args are unique]
     * </pre>
     * @ensures createFromArgsTest = [pairs in args]
     */
    private Map<String, String> createFromArgsTest(String... args) {
        assert args.length % 2 == 0 : "Violation of: args.length is even";
        Map<String, String> map = this.constructorTest();
        for (int i = 0; i < args.length; i += 2) {
            assert !map.hasKey(args[i]) : ""
                    + "Violation of: the 'key' entries in args are unique";
            map.add(args[i], args[i + 1]);
        }
        return map;
    }

    /**
     *
     * Creates and returns a {@code Map<String, String>} of the reference
     * implementation type with the given entries.
     *
     * @param args
     *            the (key, value) pairs for the map
     * @return the constructed map
     * @requires <pre>
     * [args.length is even]  and
     * [the 'key' entries in args are unique]
     * </pre>
     * @ensures createFromArgsRef = [pairs in args]
     */
    private Map<String, String> createFromArgsRef(String... args) {
        assert args.length % 2 == 0 : "Violation of: args.length is even";
        Map<String, String> map = this.constructorRef();
        for (int i = 0; i < args.length; i += 2) {
            assert !map.hasKey(args[i]) : ""
                    + "Violation of: the 'key' entries in args are unique";
            map.add(args[i], args[i + 1]);
        }
        return map;
    }

    // TODO - add test cases for constructor, add, remove, removeAny, value, hasKey, and size

    /**
     * Test for empty constructor.
     */
    @Test
    public final void testEmptyConstructor() {
        Map<String, String> m1 = this.createFromArgsTest();
        Map<String, String> m1EXP = this.createFromArgsRef();

        assertEquals(m1EXP, m1);
    }

    /**
     * Test for non empty constructor.
     */
    @Test
    public final void testNonEmptyConstructor() {
        Map<String, String> m1 = this.createFromArgsTest("initial", "value");
        Map<String, String> m1EXP = this.createFromArgsRef("initial", "value");

        assertEquals(m1EXP, m1);
    }

    /**
     * Test for non empty constructor.
     */
    @Test
    public final void testNonEmptyConstructor2() {
        Map<String, String> m1 = this.createFromArgsTest("initial", "value",
                "class", "cse2231", "java", "abstract");
        Map<String, String> m1EXP = this.createFromArgsRef("initial", "value",
                "class", "cse2231", "java", "abstract");

        assertEquals(m1EXP, m1);
    }

    /**
     * Test for add() to empty Map.
     */
    @Test
    public final void testAdd_1() {
        Map<String, String> m1 = this.createFromArgsTest();
        Map<String, String> m1EXP = this.createFromArgsRef("a", "1");

        m1.add("a", "1");

        assertEquals(m1EXP, m1);
    }

    /**
     * Test for add() to non empty Map.
     */
    @Test
    public final void testAdd_2() {
        Map<String, String> m1 = this.createFromArgsTest("a", "1");
        Map<String, String> m1EXP = this.createFromArgsRef("a", "1", "b", "2");

        m1.add("b", "2");

        assertEquals(m1EXP, m1);
    }

    /**
     * Test for add() to non empty Map.
     */
    @Test
    public final void testAdd_3() {
        Map<String, String> m1 = this.createFromArgsTest("dora", "1");
        Map<String, String> m1EXP = this.createFromArgsRef("dora", "1", "road",
                "2");

        m1.add("road", "2");

        assertEquals(m1EXP, m1);
    }

    /**
     * Test for value().
     */
    @Test
    public final void testValue() {
        Map<String, String> m1 = this.createFromArgsTest("dora", "1");

        String value = m1.value("dora");

        assertEquals("1", value);
    }

    /**
     * Test for value().
     */
    @Test
    public final void testValue2() {
        Map<String, String> m1 = this.createFromArgsTest("alpha", "1", "bravo",
                "2", "charlie", "3");

        String value = m1.value("charlie");

        assertEquals("3", value);
    }

    /**
     * Test for value().
     */
    @Test
    public final void testValue3() {
        Map<String, String> m1 = this.createFromArgsTest("alpha", "1", "bravo",
                "2", "charlie", "3", "delta", "4", "echo", "5");

        String value = m1.value("delta");

        assertEquals("4", value);
    }

    /**
     * Test for remove() leaving emoty map.
     */
    @Test
    public final void testRemoveToEmpty() {
        Map<String, String> m = this.createFromArgsRef("a", "1");
        Map<String, String> em = this.createFromArgsRef();
        m.remove("a");
        assertEquals(em, m);
    }

    /**
     * Test for remove() leaving non emoty map.
     */
    @Test
    public final void testRemove() {
        Map<String, String> m = this.createFromArgsTest("a", "1", "b", "2", "c",
                "3");
        Map<String, String> em = this.createFromArgsRef("a", "1", "c", "3");
        m.remove("b");
        assertEquals(em, m);
    }

    /**
     * Test for removeAny() leaving emoty map.
     */
    @Test
    public final void testRemoveAnyToEmpty() {
        Map<String, String> m = this.createFromArgsTest("a", "1");
        Map<String, String> em = this.createFromArgsRef();
        m.removeAny();
        assertEquals(em, m);
    }

    /**
     * Test for removeAny() leaving non emoty map.
     */
    @Test
    public final void testRemoveAny() {
        Map<String, String> m = this.createFromArgsTest("a", "1", "b", "2", "c",
                "3");

        Map<String, String> em1 = this.createFromArgsRef("a", "1", "b", "2");
        Map<String, String> em2 = this.createFromArgsRef("a", "1", "c", "3");
        Map<String, String> em3 = this.createFromArgsRef("b", "2", "c", "3");
        m.removeAny();
        boolean passed = m.equals(em1) || m.equals(em2) || m.equals(em3);
        assertEquals(true, passed);
    }

    /**
     * Test for removeAny() leaving non emoty map.
     */
    @Test
    public final void testRemoveAny2() {
        Map<String, String> m1 = this.createFromArgsTest("alpha", "1", "bravo",
                "2");
        Map<String, String> m1EXP = this.createFromArgsTest("alpha", "1",
                "bravo", "2");

        Map.Pair<String, String> removed = m1.removeAny();
        Map.Pair<String, String> removedEXP = m1EXP.remove(removed.key());

        assertEquals(m1EXP, m1);
        assertEquals(removedEXP, removed);

    }

    /**
     * Test for hasKey() resulting in false.
     */
    @Test
    public final void testHasKeyFalse() {
        Map<String, String> m = this.createFromArgsTest("a", "1", "b", "2", "c",
                "3");
        Map<String, String> em = this.createFromArgsRef("a", "1", "b", "2", "c",
                "3");
        boolean hasKey = m.hasKey("d");
        assertEquals(false, hasKey);
        assertEquals(em, m);
    }

    /**
     * Test for hasKey() resulting in true.
     */
    @Test
    public final void testHasKeyTrue() {
        Map<String, String> m = this.createFromArgsTest("a", "1", "b", "2", "c",
                "3");
        Map<String, String> em = this.createFromArgsRef("a", "1", "b", "2", "c",
                "3");
        boolean hasKey = m.hasKey("b");
        assertEquals(true, hasKey);
        assertEquals(em, m);
    }

    /**
     * Test for hasKey() resulting in false.
     */
    @Test
    public final void testHasKeyFalse2() {
        Map<String, String> m = this.createFromArgsTest("a", "1", "b", "2", "c",
                "3");
        Map<String, String> em = this.createFromArgsRef("a", "1", "b", "2", "c",
                "3");
        boolean hasKey = m.hasKey("d");
        assertEquals(false, hasKey);
        assertEquals(em, m);
    }

    /**
     * Test for hasKey() resulting in true.
     */
    @Test
    public final void testHasKeyTrue3() {
        Map<String, String> m = this.createFromArgsTest("a", "1", "b", "2", "c",
                "3", "d", "4", "e", "5", "f", "6", "g", "7");
        Map<String, String> em = this.createFromArgsRef("a", "1", "b", "2", "c",
                "3", "d", "4", "e", "5", "f", "6", "g", "7");
        boolean hasKey = m.hasKey("b");
        assertEquals(true, hasKey);
        assertEquals(em, m);
    }

    /**
     * Test for size() of emoty map.
     */
    @Test
    public final void testSize_1() {
        Map<String, String> m1 = this.createFromArgsTest();
        int expSize = 0;

        int sizeResult = m1.size();

        assertEquals(expSize, sizeResult);
    }

    /**
     * Test for size() of non emoty map.
     */
    @Test
    public final void testSize_2() {
        Map<String, String> m1 = this.createFromArgsTest("a", "1", "b", "2",
                "c", "3");
        int expSize = 3;

        int sizeResult = m1.size();

        assertEquals(expSize, sizeResult);
    }

}
