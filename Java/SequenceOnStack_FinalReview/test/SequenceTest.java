import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.sequence.Sequence;

/**
 * JUnit test fixture for {@code Sequence<String>}'s constructor and kernel
 * methods.
 *
 * @author Put your name here
 *
 */
public abstract class SequenceTest {

    /**
     * Invokes the appropriate {@code Sequence} constructor for the
     * implementation under test and returns the result.
     *
     * @return the new sequence
     * @ensures constructorTest = <>
     */
    protected abstract Sequence<String> constructorTest();

    /**
     * Invokes the appropriate {@code Sequence} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new sequence
     * @ensures constructorRef = <>
     */
    protected abstract Sequence<String> constructorRef();

    /**
     *
     * Creates and returns a {@code Sequence<String>} of the implementation
     * under test type with the given entries.
     *
     * @param args
     *            the entries for the sequence
     * @return the constructed sequence
     * @ensures createFromArgsTest = [entries in args]
     */
    private Sequence<String> createFromArgsTest(String... args) {
        Sequence<String> sequence = this.constructorTest();
        for (String s : args) {
            sequence.add(sequence.length(), s);
        }
        return sequence;
    }

    /**
     *
     * Creates and returns a {@code Sequence<String>} of the reference
     * implementation type with the given entries.
     *
     * @param args
     *            the entries for the sequence
     * @return the constructed sequence
     * @ensures createFromArgsRef = [entries in args]
     */
    private Sequence<String> createFromArgsRef(String... args) {
        Sequence<String> sequence = this.constructorRef();
        for (String s : args) {
            sequence.add(sequence.length(), s);
        }
        return sequence;
    }

    // TODO - add test cases for constructor, add, remove, and length

    @Test
    public final void testEmptyConstructor() {
        /*
         * Set up variables
         */
        Sequence<String> s = this.constructorTest();
        Sequence<String> sExpected = this.constructorRef();

        assertEquals(sExpected, s);
    }

    @Test
    public final void testAddToEmptySequence() {
        /*
         * Set up variables
         */
        Sequence<String> s = this.createFromArgsTest();
        Sequence<String> sExpected = this.createFromArgsRef("2");

        s.add(s.length(), "2");
        assertEquals(sExpected, s);
    }

    @Test
    public final void testAddToNonEmptySequence() {
        /*
         * Set up variables
         */
        Sequence<String> s = this.createFromArgsTest("1");
        Sequence<String> sExpected = this.createFromArgsRef("1", "2");

        s.add(s.length(), "2");
        assertEquals(sExpected, s);
    }

    @Test
    public final void testAddToNonEmptySequence2() {
        /*
         * Set up variables
         */
        Sequence<String> s = this.createFromArgsTest("1", "2", "3");
        Sequence<String> sExpected = this.createFromArgsRef("1", "2", "3", "4",
                "5");

        s.add(s.length(), "4");
        s.add(s.length(), "5");
        assertEquals(sExpected, s);
    }

    @Test
    public final void testRemoveNonEmptySequence() {
        /*
         * Set up variables
         */
        Sequence<String> s = this.createFromArgsTest("1", "2", "3");
        Sequence<String> sExpected = this.createFromArgsRef("1", "2");

        s.remove(2);
        assertEquals(sExpected, s);
    }

    @Test
    public final void testRemoveNonEmptySequence2() {
        /*
         * Set up variables
         */
        Sequence<String> s = this.createFromArgsTest("1", "2", "3");
        Sequence<String> sExpected = this.createFromArgsRef("1");

        s.remove(2);
        s.remove(1);
        assertEquals(sExpected, s);
    }

    @Test
    public final void testRemoveLeavingEmptySequence() {
        /*
         * Set up variables
         */
        Sequence<String> s = this.createFromArgsTest("1");
        Sequence<String> sExpected = this.createFromArgsRef();

        s.remove(0);
        assertEquals(sExpected, s);
    }

    @Test
    public final void testLengthOfSequence1() {
        /*
         * Set up variables
         */
        Sequence<String> s = this.createFromArgsTest("1");
        int length = s.length();
        assertEquals(1, length);
    }

    @Test
    public final void testLengthOfSequence2() {
        /*
         * Set up variables
         */
        Sequence<String> s = this.createFromArgsTest("1", "2", "3");
        int length2 = s.length();
        assertEquals(3, length2);
    }

    @Test
    public final void testLengthOfSequence3() {
        /*
         * Set up variables
         */
        Sequence<String> s = this.createFromArgsTest("1", "2", "3", "4", "5",
                "6", "7");
        int length = s.length();
        assertEquals(7, length);
    }
}
