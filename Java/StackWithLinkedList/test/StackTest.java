import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.stack.Stack;

/**
 * JUnit test fixture for {@code Stack<String>}'s constructor and kernel
 * methods.
 *
 * @author Put your name here
 *
 */
public abstract class StackTest {

    /**
     * Invokes the appropriate {@code Stack} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new stack
     * @ensures constructorTest = <>
     */
    protected abstract Stack<String> constructorTest();

    /**
     * Invokes the appropriate {@code Stack} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new stack
     * @ensures constructorRef = <>
     */
    protected abstract Stack<String> constructorRef();

    /**
     *
     * Creates and returns a {@code Stack<String>} of the implementation under
     * test type with the given entries.
     *
     * @param args
     *            the entries for the stack
     * @return the constructed stack
     * @ensures createFromArgsTest = [entries in args]
     */
    private Stack<String> createFromArgsTest(String... args) {
        Stack<String> stack = this.constructorTest();
        for (String s : args) {
            stack.push(s);
        }
        stack.flip();
        return stack;
    }

    /**
     *
     * Creates and returns a {@code Stack<String>} of the reference
     * implementation type with the given entries.
     *
     * @param args
     *            the entries for the stack
     * @return the constructed stack
     * @ensures createFromArgsRef = [entries in args]
     */
    private Stack<String> createFromArgsRef(String... args) {
        Stack<String> stack = this.constructorRef();
        for (String s : args) {
            stack.push(s);
        }
        stack.flip();
        return stack;
    }

    // TODO - add test cases for constructor, push, pop, and length

    @Test
    public final void testConstructor_1() {
        Stack<String> stk = this.createFromArgsTest();
        Stack<String> stkEXP = this.createFromArgsRef();
        assertEquals(stkEXP, stk);
    }

    @Test
    public final void testConstructor_2() {
        Stack<String> stk = this.createFromArgsTest("alpha");
        Stack<String> stkEXP = this.createFromArgsRef("alpha");
        assertEquals(stkEXP, stk);
    }

    @Test
    public final void testConstructor_3() {
        Stack<String> stk = this.createFromArgsTest("alpha", "bravo",
                "charlie");
        Stack<String> stkEXP = this.createFromArgsRef("alpha", "bravo",
                "charlie");
        assertEquals(stkEXP, stk);
    }

    @Test
    public final void testPush_1() {
        Stack<String> stk = this.createFromArgsTest();
        Stack<String> stkEXP = this.createFromArgsRef("alpha");
        stk.push("alpha");
        assertEquals(stkEXP, stk);
    }

    @Test
    public final void testPush_2() {
        Stack<String> stk = this.createFromArgsTest("alpha");
        Stack<String> stkEXP = this.createFromArgsRef("bravo", "alpha");
        stk.push("bravo");
        assertEquals(stkEXP, stk);
    }

    @Test
    public final void testPush_3() {
        Stack<String> stk = this.createFromArgsTest("bravo", "alpha");
        Stack<String> stkEXP = this.createFromArgsRef("charlie", "bravo",
                "alpha");
        stk.push("charlie");
        assertEquals(stkEXP, stk);
    }

    @Test
    public final void testPop_1() {
        Stack<String> stk = this.createFromArgsTest("alpha");
        Stack<String> stkEXP = this.createFromArgsRef();
        String s = stk.pop();
        assertEquals("alpha", s);
        assertEquals(stkEXP, stk);
    }

    @Test
    public final void testPop_2() {
        Stack<String> stk = this.createFromArgsTest("bravo", "alpha");
        Stack<String> stkEXP = this.createFromArgsRef("alpha");
        String s = stk.pop();
        assertEquals("bravo", s);
        assertEquals(stkEXP, stk);
    }

    @Test
    public final void testPop_3() {
        Stack<String> stk = this.createFromArgsTest("delta", "charlie", "bravo",
                "alpha");
        Stack<String> stkEXP = this.createFromArgsRef("charlie", "bravo",
                "alpha");
        String s = stk.pop();
        assertEquals("delta", s);
        assertEquals(stkEXP, stk);
    }

    @Test
    public final void testSize_1() {
        Stack<String> stk = this.createFromArgsTest("alpha");
        int size = stk.length();
        assertEquals(1, size);
    }

    @Test
    public final void testTop_1() {
        Stack<String> stk = this.createFromArgsTest("alpha");
        Stack<String> stkEXP = this.createFromArgsRef("alpha");
        String hold = stk.top();
        assertEquals("alpha", hold);
        assertEquals(stkEXP, stk);
    }

    @Test
    public final void testReplaceTop_1() {
        Stack<String> stk = this.createFromArgsTest("alpha");
        Stack<String> stkEXP = this.createFromArgsRef("bravo");
        String hold = stk.replaceTop("bravo");
        assertEquals("alpha", hold);
        assertEquals(stkEXP, stk);
    }
}
