import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.program.Program;
import components.queue.Queue;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.utilities.Tokenizer;

/**
 * JUnit test fixture for {@code Program}'s constructor and kernel methods.
 *
 * @author Andrew Schneider
 * @author Eric Sullivan
 *
 */
public abstract class ProgramTest {

    /**
     * The names of a files containing a (possibly invalid) BL programs.
     */
    private static final String FILE_NAME_1 = "test/program1.bl",
            FILE_NAME_2 = "test/program2.bl", FILE_NAME_3 = "test/program3.bl",
            FILE_NAME_4 = "test/program4.bl", FILE_NAME_5 = "test/program5.bl",
            FILE_NAME_6 = "test/program6.bl", FILE_NAME_7 = "test/program7.bl",
            FILE_NAME_8 = "test/program8.bl", FILE_NAME_9 = "test/program9.bl",
            FILE_NAME_10 = "test/program10.bl",
            FILE_NAME_11 = "test/program11.bl",
            FILE_NAME_12 = "test/program12.bl",
            FILE_NAME_13 = "test/program13.bl",
            FILE_NAME_14 = "test/program14.bl",
            FILE_NAME_15 = "test/program15.bl",
            FILE_NAME_16 = "test/program16.bl",
            FILE_NAME_17 = "test/program17.bl",
            FILE_NAME_18 = "test/program18.bl",
            FILE_NAME_19 = "test/program19.bl",
            FILE_NAME_20 = "test/program20.bl",
            FILE_NAME_21 = "test/program21.bl",
            FILE_NAME_22 = "test/program22.bl",
            FILE_NAME_23 = "test/program23.bl",
            FILE_NAME_24 = "test/program24.bl",
            FILE_NAME_25 = "test/program25.bl",
            FILE_NAME_26 = "test/program26.bl";

    /**
     * Invokes the {@code Program} constructor for the implementation under test
     * and returns the result.
     *
     * @return the new program
     * @ensures constructorTest = ("Unnamed", {}, compose((BLOCK, ?, ?), <>))
     */
    protected abstract Program constructorTest();

    /**
     * Invokes the {@code Program} constructor for the reference implementation
     * and returns the result.
     *
     * @return the new program
     * @ensures constructorRef = ("Unnamed", {}, compose((BLOCK, ?, ?), <>))
     */
    protected abstract Program constructorRef();

    /**
     * Test of parse on syntactically valid input.
     */
    @Test
    public final void testParseValidExample() {
        /*
         * Setup
         */
        Program pRef = this.constructorRef();
        SimpleReader file = new SimpleReader1L(FILE_NAME_1);
        pRef.parse(file);
        file.close();
        Program pTest = this.constructorTest();
        file = new SimpleReader1L(FILE_NAME_1);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call
         */
        pTest.parse(tokens);
        /*
         * Evaluation
         */
        assertEquals(pRef, pTest);
    }

    /**
     * Test of parse on syntactically invalid input.
     */
    @Test(expected = RuntimeException.class)
    public final void testParseErrorExample() {
        /*
         * Setup
         */
        Program pTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(FILE_NAME_2);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in a syntax error being found
         */
        pTest.parse(tokens);
    }

    // TODO - add more test cases for valid inputs

    /**
     * Test of parse on syntactically valid input.
     */
    @Test
    public final void testParseValidExample2() {
        /*
         * Setup
         */
        Program pRef = this.constructorRef();
        SimpleReader file = new SimpleReader1L(FILE_NAME_17);
        pRef.parse(file);
        file.close();
        Program pTest = this.constructorTest();
        file = new SimpleReader1L(FILE_NAME_17);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call
         */
        pTest.parse(tokens);
        /*
         * Evaluation
         */
        assertEquals(pRef, pTest);
    }

    /**
     * Test of parse on syntactically valid input.
     */
    @Test
    public final void testParseValidExample3() {
        /*
         * Setup
         */
        Program pRef = this.constructorRef();
        SimpleReader file = new SimpleReader1L(FILE_NAME_18);
        pRef.parse(file);
        file.close();
        Program pTest = this.constructorTest();
        file = new SimpleReader1L(FILE_NAME_18);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call
         */
        pTest.parse(tokens);
        /*
         * Evaluation
         */
        assertEquals(pRef, pTest);
    }

    /**
     * Test of parse on syntactically valid input.
     */
    @Test
    public final void testParseValidExample4() {
        /*
         * Setup
         */
        Program pRef = this.constructorRef();
        SimpleReader file = new SimpleReader1L(FILE_NAME_19);
        pRef.parse(file);
        file.close();
        Program pTest = this.constructorTest();
        file = new SimpleReader1L(FILE_NAME_19);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call
         */
        pTest.parse(tokens);
        /*
         * Evaluation
         */
        assertEquals(pRef, pTest);
    }

    /**
     * Test of parse on syntactically valid input.
     */
    @Test
    public final void testParseValidExample5() {
        /*
         * Setup
         */
        Program pRef = this.constructorRef();
        SimpleReader file = new SimpleReader1L(FILE_NAME_20);
        pRef.parse(file);
        file.close();
        Program pTest = this.constructorTest();
        file = new SimpleReader1L(FILE_NAME_20);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call
         */
        pTest.parse(tokens);
        /*
         * Evaluation
         */
        assertEquals(pRef, pTest);
    }

    /**
     * Test of parse on syntactically valid input.
     */
    @Test
    public final void testParseValidExample6() {
        /*
         * Setup
         */
        Program pRef = this.constructorRef();
        SimpleReader file = new SimpleReader1L(FILE_NAME_21);
        pRef.parse(file);
        file.close();
        Program pTest = this.constructorTest();
        file = new SimpleReader1L(FILE_NAME_21);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call
         */
        pTest.parse(tokens);
        /*
         * Evaluation
         */
        assertEquals(pRef, pTest);
    }

    /**
     * Test of parse on syntactically valid input.
     */
    @Test
    public final void testParseValidExample7() {
        /*
         * Setup
         */
        Program pRef = this.constructorRef();
        SimpleReader file = new SimpleReader1L(FILE_NAME_22);
        pRef.parse(file);
        file.close();
        Program pTest = this.constructorTest();
        file = new SimpleReader1L(FILE_NAME_22);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call
         */
        pTest.parse(tokens);
        /*
         * Evaluation
         */
        assertEquals(pRef, pTest);
    }

    /**
     * Test of parse on syntactically valid input.
     */
    @Test
    public final void testParseValidExample8() {
        /*
         * Setup
         */
        Program pRef = this.constructorRef();
        SimpleReader file = new SimpleReader1L(FILE_NAME_23);
        pRef.parse(file);
        file.close();
        Program pTest = this.constructorTest();
        file = new SimpleReader1L(FILE_NAME_23);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call
         */
        pTest.parse(tokens);
        /*
         * Evaluation
         */
        assertEquals(pRef, pTest);
    }

    /**
     * Test of parse on syntactically valid input.
     */
    @Test
    public final void testParseValidExample9() {
        /*
         * Setup
         */
        Program pRef = this.constructorRef();
        SimpleReader file = new SimpleReader1L(FILE_NAME_24);
        pRef.parse(file);
        file.close();
        Program pTest = this.constructorTest();
        file = new SimpleReader1L(FILE_NAME_24);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call
         */
        pTest.parse(tokens);
        /*
         * Evaluation
         */
        assertEquals(pRef, pTest);
    }

    /**
     * Test of parse on syntactically valid input.
     */
    @Test
    public final void testParseValidExample10() {
        /*
         * Setup
         */
        Program pRef = this.constructorRef();
        SimpleReader file = new SimpleReader1L(FILE_NAME_25);
        pRef.parse(file);
        file.close();
        Program pTest = this.constructorTest();
        file = new SimpleReader1L(FILE_NAME_25);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call
         */
        pTest.parse(tokens);
        /*
         * Evaluation
         */
        assertEquals(pRef, pTest);
    }

    /**
     * Test of parse on syntactically valid input.
     */
    @Test
    public final void testParseValidExample11() {
        /*
         * Setup
         */
        Program pRef = this.constructorRef();
        SimpleReader file = new SimpleReader1L(FILE_NAME_26);
        pRef.parse(file);
        file.close();
        Program pTest = this.constructorTest();
        file = new SimpleReader1L(FILE_NAME_26);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call
         */
        pTest.parse(tokens);
        /*
         * Evaluation
         */
        assertEquals(pRef, pTest);
    }

    // TODO - add more test cases for as many distinct syntax errors as possible

    /**
     * Test of parse on syntactically invalid input.
     */
    @Test(expected = RuntimeException.class)
    public final void testParseErrorExample2() {
        /*
         * Setup
         */
        Program pTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(FILE_NAME_3);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in a syntax error being found
         */
        pTest.parse(tokens);
    }

    /**
     * Test of parse on syntactically invalid input.
     */
    @Test(expected = RuntimeException.class)
    public final void testParseErrorExample3() {
        /*
         * Setup
         */
        Program pTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(FILE_NAME_4);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in a syntax error being found
         */
        pTest.parse(tokens);
    }

    /**
     * Test of parse on syntactically invalid input.
     */
    @Test(expected = RuntimeException.class)
    public final void testParseErrorExample4() {
        /*
         * Setup
         */
        Program pTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(FILE_NAME_5);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in a syntax error being found
         */
        pTest.parse(tokens);
    }

    /**
     * Test of parse on syntactically invalid input.
     */
    @Test(expected = RuntimeException.class)
    public final void testParseErrorExample5() {
        /*
         * Setup
         */
        Program pTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(FILE_NAME_6);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in a syntax error being found
         */
        pTest.parse(tokens);
    }

    /**
     * Test of parse on syntactically invalid input.
     */
    @Test(expected = RuntimeException.class)
    public final void testParseErrorExample6() {
        /*
         * Setup
         */
        Program pTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(FILE_NAME_7);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in a syntax error being found
         */
        pTest.parse(tokens);
    }

    /**
     * Test of parse on syntactically invalid input.
     */
    @Test(expected = RuntimeException.class)
    public final void testParseErrorExample7() {
        /*
         * Setup
         */
        Program pTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(FILE_NAME_8);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in a syntax error being found
         */
        pTest.parse(tokens);
    }

    /**
     * Test of parse on syntactically invalid input.
     */
    @Test(expected = RuntimeException.class)
    public final void testParseErrorExample8() {
        /*
         * Setup
         */
        Program pTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(FILE_NAME_9);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in a syntax error being found
         */
        pTest.parse(tokens);
    }

    /**
     * Test of parse on syntactically invalid input.
     */
    @Test(expected = RuntimeException.class)
    public final void testParseErrorExample9() {
        /*
         * Setup
         */
        Program pTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(FILE_NAME_10);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in a syntax error being found
         */
        pTest.parse(tokens);
    }

    /**
     * Test of parse on syntactically invalid input.
     */
    @Test(expected = RuntimeException.class)
    public final void testParseErrorExample10() {
        /*
         * Setup
         */
        Program pTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(FILE_NAME_11);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in a syntax error being found
         */
        pTest.parse(tokens);
    }

    /**
     * Test of parse on syntactically invalid input.
     */
    @Test(expected = RuntimeException.class)
    public final void testParseErrorExample11() {
        /*
         * Setup
         */
        Program pTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(FILE_NAME_12);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in a syntax error being found
         */
        pTest.parse(tokens);
    }

    /**
     * Test of parse on syntactically invalid input.
     */
    @Test(expected = RuntimeException.class)
    public final void testParseErrorExample12() {
        /*
         * Setup
         */
        Program pTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(FILE_NAME_13);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in a syntax error being found
         */
        pTest.parse(tokens);
    }

    /**
     * Test of parse on syntactically invalid input.
     */
    @Test(expected = RuntimeException.class)
    public final void testParseErrorExample13() {
        /*
         * Setup
         */
        Program pTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(FILE_NAME_14);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in a syntax error being found
         */
        pTest.parse(tokens);
    }

    /**
     * Test of parse on syntactically invalid input.
     */
    @Test(expected = RuntimeException.class)
    public final void testParseErrorExample14() {
        /*
         * Setup
         */
        Program pTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(FILE_NAME_15);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in a syntax error being found
         */
        pTest.parse(tokens);
    }

    /**
     * Test of parse on syntactically invalid input.
     */
    @Test(expected = RuntimeException.class)
    public final void testParseErrorExample15() {
        /*
         * Setup
         */
        Program pTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(FILE_NAME_16);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in a syntax error being found
         */
        pTest.parse(tokens);
    }

}
