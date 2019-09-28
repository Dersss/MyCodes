import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.map.Map;

/**
 * JUnit test fixture for {@code Map<String, String>}'s constructor and kernel
 * methods.
 *
 * @author Put your name here
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

    @Test
    public final void testEmptyConstructor() {
        Map<String, String> m1 = this.createFromArgsTest();
        Map<String, String> mEXP = this.createFromArgsRef();
        assertEquals(mEXP, m1);
    }

    @Test
    public final void testOneItemConstructor() {
        Map<String, String> m1 = this.createFromArgsTest("first", "1");
        Map<String, String> mEXP = this.createFromArgsRef("first", "1");
        assertEquals(mEXP, m1);
    }

    @Test
    public final void testTwoItemConstructor() {
        Map<String, String> m1 = this.createFromArgsTest("first", "1", "two",
                "2");
        Map<String, String> mEXP = this.createFromArgsRef("first", "1", "two",
                "2");
        assertEquals(mEXP, m1);
    }

    @Test
    public final void testAddToEmptyMap() {
        Map<String, String> m1 = this.createFromArgsTest();
        Map<String, String> mEXP = this.createFromArgsRef("first", "1");
        String key = "first";
        String value = "1";
        m1.add(key, value);
        assertEquals(mEXP, m1);
    }

    @Test
    public final void testAddToNonEmptyMap() {
        Map<String, String> m1 = this.createFromArgsTest("first", "1");
        Map<String, String> mEXP = this.createFromArgsRef("first", "1", "two",
                "2");
        String key = "two";
        String value = "2";
        m1.add(key, value);
        assertEquals(mEXP, m1);
    }

    @Test
    public final void testAddToNonEmptyMap2() {
        Map<String, String> m1 = this.createFromArgsTest("first", "1", "two",
                "2");
        Map<String, String> mEXP = this.createFromArgsRef("first", "1", "two",
                "2", "three", "3");
        String key = "three";
        String value = "3";
        m1.add(key, value);
        assertEquals(mEXP, m1);
    }

    @Test
    public final void testRemoveOneItemQueue() {
        Map<String, String> m1 = this.createFromArgsTest("first", "1");
        Map<String, String> mEXP = this.createFromArgsRef();
        String key = "first";
        m1.remove(key);
        assertEquals(mEXP, m1);
    }

    @Test
    public final void testRemoveTwoItemQueue() {
        Map<String, String> m1 = this.createFromArgsTest("first", "1", "two",
                "2");
        Map<String, String> mEXP = this.createFromArgsRef("two", "2");
        String key = "first";
        m1.remove(key);
        assertEquals(mEXP, m1);
    }

    @Test
    public final void testRemoveThreeItemQueue() {
        Map<String, String> m1 = this.createFromArgsTest("first", "1", "two",
                "2", "Three", "3");
        Map<String, String> mEXP = this.createFromArgsRef("two", "2", "Three",
                "3");
        String key = "first";
        Map.Pair<String, String> hold = m1.remove(key);

        assertEquals(key, hold.key());
        assertEquals(mEXP, m1);
    }

    @Test
    public final void testRemoveThreeItemQueue2() {
        Map<String, String> m1 = this.createFromArgsTest("first", "1", "two",
                "2", "Three", "3");
        Map<String, String> mEXP = this.createFromArgsRef("first", "1", "Three",
                "3");
        String key = "two";
        Map.Pair<String, String> hold = m1.remove(key);

        assertEquals(key, hold.key());
        assertEquals(mEXP, m1);
    }

    @Test
    public final void testRemoveAnyOneItemQueue() {
        Map<String, String> m1 = this.createFromArgsTest("first", "1");
        Map<String, String> mEXP = this.createFromArgsRef();
        Map.Pair<String, String> hold = m1.removeAny();
        assertEquals(mEXP, m1);
    }

    @Test
    public final void testRemoveAnyTwoItemQueue2() {
        Map<String, String> m1 = this.createFromArgsTest("first", "1", "two",
                "2");
        Map<String, String> mEXP = this.createFromArgsRef("first", "1", "two",
                "2");
        Map.Pair<String, String> hold = m1.removeAny();
        Map.Pair<String, String> hold2 = mEXP.remove(hold.key());
        assertEquals(mEXP, m1);
    }

    @Test
    public final void testRemoveAnyThreeItemQueue3() {
        Map<String, String> m1 = this.createFromArgsTest("first", "1", "two",
                "2", "three", "3");
        Map<String, String> mEXP = this.createFromArgsRef("first", "1", "two",
                "2", "three", "3");
        Map.Pair<String, String> hold = m1.removeAny();
        Map.Pair<String, String> hold2 = mEXP.remove(hold.key());
        assertEquals(mEXP, m1);
    }

    @Test
    public final void testValue() {
        Map<String, String> m1 = this.createFromArgsTest("first", "1", "two",
                "2");
        Map<String, String> mEXP = this.createFromArgsRef("first", "1", "two",
                "2");
        String key = "two";
        Map.Pair<String, String> hold2 = m1.remove(key);
        String val = hold2.value();
        m1.add(key, val);

        assertEquals(mEXP, m1);
        assertEquals("2", val);
    }

    @Test
    public final void testValue2() {
        Map<String, String> m1 = this.createFromArgsTest("first", "1", "two",
                "2");
        Map<String, String> mEXP = this.createFromArgsRef("first", "1", "two",
                "2");
        String key = "first";
        Map.Pair<String, String> hold2 = m1.remove(key);
        String val = hold2.value();
        m1.add(key, val);
        assertEquals(mEXP, m1);
        assertEquals("1", val);
    }

    @Test
    public final void testHasKey() {
        Map<String, String> m1 = this.createFromArgsTest("first", "1", "two",
                "2");
        assertEquals(true, m1.hasKey("two"));
    }

    @Test
    public final void testHasKey2() {
        Map<String, String> m1 = this.createFromArgsTest("first", "1", "two",
                "2");
        assertEquals(false, m1.hasKey("three"));
    }

    @Test
    public final void testSize() {
        Map<String, String> m1 = this.createFromArgsTest("first", "1", "two",
                "2");
        assertEquals(2, m1.size());
    }

    @Test
    public final void testSize2() {
        Map<String, String> m1 = this.createFromArgsTest("first", "1", "two",
                "2", "three", "3", "four", "4", "five", "5");
        assertEquals(5, m1.size());
    }

}
