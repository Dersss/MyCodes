import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.map.Map;
import components.map.Map.Pair;
import components.program.Program;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.statement.Statement;

/**
 * JUnit test fixture for {@code Program}'s constructor and kernel methods.
 *
 * @author Wayne Heym
 * @author Andrew Schneider
 * @author Eric Sullivan
 * @author Hitesh Bavisetti
 */
public abstract class ProgramTest {

    /**
     * The name of a file containing a BL program.
     */
    private static final String FILE_NAME_1 = "data/program-sample.bl";

    // TODO: define file names for additional test inputs

    private static final String FILE_NAME_2 = "data/emptyProgram.bl";

    /**
     * Invokes the {@code Program} constructor for the implementation under test
     * and returns the result.
     *
     * @return the new program
     * @ensures constructor = ("Unnamed", {}, compose((BLOCK, ?, ?), <>))
     */
    protected abstract Program constructorTest();

    /**
     * Invokes the {@code Program} constructor for the reference implementation
     * and returns the result.
     *
     * @return the new program
     * @ensures constructor = ("Unnamed", {}, compose((BLOCK, ?, ?), <>))
     */
    protected abstract Program constructorRef();

    /**
     *
     * Creates and returns a {@code Program}, of the type of the implementation
     * under test, from the file with the given name.
     *
     * @param filename
     *            the name of the file to be parsed to create the program
     * @return the constructed program
     * @ensures createFromFile = [the program as parsed from the file]
     */
    private Program createFromFileTest(String filename) {
        Program p = this.constructorTest();
        SimpleReader file = new SimpleReader1L(filename);
        p.parse(file);
        file.close();
        return p;
    }

    /**
     *
     * Creates and returns a {@code Program}, of the reference implementation
     * type, from the file with the given name.
     *
     * @param filename
     *            the name of the file to be parsed to create the program
     * @return the constructed program
     * @ensures createFromFile = [the program as parsed from the file]
     */
    private Program createFromFileRef(String filename) {
        Program p = this.constructorRef();
        SimpleReader file = new SimpleReader1L(filename);
        p.parse(file);
        file.close();
        return p;
    }

    /**
     * Test constructor.
     */
    @Test
    public final void testConstructor() {
        /*
         * Setup
         */
        Program pRef = this.constructorRef();

        /*
         * The call
         */
        Program pTest = this.constructorTest();

        /*
         * Evaluation
         */
        assertEquals(pRef, pTest);
    }

    /**
     * Test replaceName.
     */
    @Test
    public final void testReplaceName() {
        /*
         * Setup
         */
        Program pTest = this.createFromFileTest(FILE_NAME_1);
        Program pRef = this.createFromFileRef(FILE_NAME_1);
        String newName = "Replacement";
        String nameRef = pRef.replaceName(newName);

        /*
         * The call
         */
        String nameTest = pTest.replaceName(newName);

        /*
         * Evaluation
         */
        assertEquals(pRef, pTest);
        assertEquals(nameRef, nameTest);
    }

    /**
     * Test newContext.
     */
    @Test
    public final void testNewContext() {
        /*
         * Setup
         */
        Program pTest = this.createFromFileTest(FILE_NAME_1);
        Program pRef = this.createFromFileRef(FILE_NAME_1);
        Map<String, Statement> cRef = pRef.newContext();

        /*
         * The call
         */
        Map<String, Statement> cTest = pTest.newContext();

        /*
         * Evaluation
         */
        assertEquals(pRef, pTest);
        assertEquals(cRef, cTest);
    }

    /**
     * Test replaceContext.
     */
    @Test
    public final void testReplaceContext() {
        /*
         * Setup
         */
        Program pTest = this.createFromFileTest(FILE_NAME_1);
        Program pRef = this.createFromFileRef(FILE_NAME_1);
        Map<String, Statement> cReplacementRef = pRef.newContext();
        Map<String, Statement> cReplacementTest = pTest.newContext();
        String oneName = "one";
        Map<String, Statement> cObtainedRef = pRef
                .replaceContext(cReplacementRef);
        Pair<String, Statement> oneRef = cObtainedRef.remove(oneName);
        /* cObtainedRef now has just "two" */
        cReplacementRef.add(oneRef.key(), oneRef.value());
        pRef.replaceContext(cReplacementRef);
        /* pRef's context now has just "one" */

        /* Make the reference call, replacing "one" with "two": */
        cReplacementRef = pRef.replaceContext(cObtainedRef);

        Map<String, Statement> cObtainedTest = pTest
                .replaceContext(cReplacementTest);
        Pair<String, Statement> oneTest = cObtainedTest.remove(oneName);
        /* cObtainedTest now has just "two" */
        cReplacementTest.add(oneTest.key(), oneTest.value());
        pTest.replaceContext(cReplacementTest);
        /* pTest's context now has just "one" */

        /*
         * The call
         */
        cReplacementTest = pTest.replaceContext(cObtainedTest);

        /*
         * Evaluation
         */
        assertEquals(pRef, pTest);
        assertEquals(cReplacementRef, cReplacementTest);
        assertEquals(cObtainedRef, cObtainedTest);
    }

    /**
     * Test newBody.
     */
    @Test
    public final void testNewBody() {
        /*
         * Setup
         */
        Program pTest = this.createFromFileTest(FILE_NAME_1);
        Program pRef = this.createFromFileRef(FILE_NAME_1);
        Statement bRef = pRef.newBody();

        /*
         * The call
         */
        Statement bTest = pTest.newBody();

        /*
         * Evaluation
         */
        assertEquals(pRef, pTest);
        assertEquals(bRef, bTest);
    }

    /**
     * Test replaceBody.
     */
    @Test
    public final void testReplaceBody() {
        /*
         * Setup
         */
        Program pTest = this.createFromFileTest(FILE_NAME_1);
        Program pRef = this.createFromFileRef(FILE_NAME_1);
        Statement bReplacementRef = pRef.newBody();
        Statement bReplacementTest = pTest.newBody();
        Statement bObtainedRef = pRef.replaceBody(bReplacementRef);
        Statement firstRef = bObtainedRef.removeFromBlock(0);
        /* bObtainedRef now lacks the first statement */
        bReplacementRef.addToBlock(0, firstRef);
        pRef.replaceBody(bReplacementRef);
        /* pRef's body now has just the first statement */

        /* Make the reference call, replacing first with remaining: */
        bReplacementRef = pRef.replaceBody(bObtainedRef);

        Statement bObtainedTest = pTest.replaceBody(bReplacementTest);
        Statement firstTest = bObtainedTest.removeFromBlock(0);
        /* bObtainedTest now lacks the first statement */
        bReplacementTest.addToBlock(0, firstTest);
        pTest.replaceBody(bReplacementTest);
        /* pTest's context now has just the first statement */

        /*
         * The call
         */
        bReplacementTest = pTest.replaceBody(bObtainedTest);

        /*
         * Evaluation
         */
        assertEquals(pRef, pTest);
        assertEquals(bReplacementRef, bReplacementTest);
        assertEquals(bObtainedRef, bObtainedTest);
    }

    // TODO: provide additional test cases to thoroughly test ProgramKernel

    /**
     * Test replaceContext with an empty context.
     */
    @Test
    public final void testReplaceContextToEmpty() {
        Program pTest = this.createFromFileTest(FILE_NAME_1);
        Program pRef = this.createFromFileRef(FILE_NAME_1);
        Map<String, Statement> cObtainedRef = pRef.newContext();
        Map<String, Statement> cObtainedTest = pTest.newContext();
        Map<String, Statement> cReplacementRef = pRef.newContext();
        Map<String, Statement> cReplacementTest = pTest.newContext();

        cReplacementRef = pRef.replaceContext(cObtainedRef);
        cReplacementTest = pTest.replaceContext(cObtainedTest);

        assertEquals(pRef, pTest);
        assertEquals(cReplacementRef, cReplacementTest);
        assertEquals(cObtainedRef, cObtainedTest);

    }

    /**
     * Test replaceBody with an empty body.
     */
    @Test
    public final void testReplaceBodyToEmpty() {
        Program pTest = this.createFromFileTest(FILE_NAME_1);
        Program pRef = this.createFromFileRef(FILE_NAME_1);

        Statement emptyBody1 = pRef.newBody();
        Statement emptyBody2 = pRef.newBody();
        Statement bReplacementRef = pRef.replaceBody(emptyBody1);
        Statement bReplacementTest = pTest.replaceBody(emptyBody2);

        assertEquals(pRef, pTest);
        assertEquals(bReplacementRef, bReplacementTest);
        assertEquals(emptyBody1, emptyBody2);
    }

    /**
     * Test replaceBody from an empty body.
     */
    @Test
    public final void testReplaceEmptyBody() {
        Program pTest = this.createFromFileTest(FILE_NAME_2);
        Program pRef = this.createFromFileRef(FILE_NAME_2);
        Program nonemptyProgram1 = this.createFromFileRef(FILE_NAME_1);
        Program nonemptyProgram2 = this.createFromFileRef(FILE_NAME_1);

        Statement emptyBody1 = nonemptyProgram1.newBody();
        Statement emptyBody2 = nonemptyProgram2.newBody();
        Statement nonemptyBody1 = nonemptyProgram1.replaceBody(emptyBody1);
        Statement nonemptyBody2 = nonemptyProgram2.replaceBody(emptyBody2);
        Statement bReplacementRef = pRef.replaceBody(nonemptyBody1);
        Statement bReplacementTest = pTest.replaceBody(nonemptyBody2);

        assertEquals(pRef, pTest);
        assertEquals(bReplacementRef, bReplacementTest);
        assertEquals(nonemptyBody1, nonemptyBody2);
    }

    /**
     * Test replaceContext from an empty context.
     */
    @Test
    public final void testReplaceEmptyContext() {
        Program pTest = this.createFromFileTest(FILE_NAME_2);
        Program pRef = this.createFromFileRef(FILE_NAME_2);
        Program nonemptyProgram1 = this.createFromFileRef(FILE_NAME_1);
        Program nonemptyProgram2 = this.createFromFileRef(FILE_NAME_1);
        Map<String, Statement> emptyContext1 = nonemptyProgram1.newContext();
        Map<String, Statement> emptyContext2 = nonemptyProgram2.newContext();
        Map<String, Statement> nonemptyContext1 = nonemptyProgram1
                .replaceContext(emptyContext1);
        Map<String, Statement> nonemptyContext2 = nonemptyProgram2
                .replaceContext(emptyContext2);

        Map<String, Statement> cReplacementRef = pRef
                .replaceContext(nonemptyContext1);
        Map<String, Statement> cReplacementTest = pTest
                .replaceContext(nonemptyContext2);

        assertEquals(pRef, pTest);
        assertEquals(cReplacementRef, cReplacementTest);
        assertEquals(nonemptyContext1, nonemptyContext2);

    }

}
