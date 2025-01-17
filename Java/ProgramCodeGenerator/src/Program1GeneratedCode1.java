import components.map.Map;
import components.program.Program;
import components.program.Program1;
import components.sequence.Sequence;
import components.sequence.Sequence1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.statement.Statement;
import components.statement.StatementKernel.Condition;
import components.utilities.Reporter;

/**
 * Layered implementation of secondary method {@code generatedCode} for
 * {@code Program}.
 *
 * @author Put your name here
 *
 */
public final class Program1GeneratedCode1 extends Program1 {

    /*
     * Private members --------------------------------------------------------
     */

    /**
     * Constructs into the given {@code Program} the program read from the given
     * input file.
     *
     * @param fileName
     *            the name of the file containing the program
     * @param p
     *            the constructed program
     * @replaces p
     * @requires [fileName is the name of a file containing a valid BL program]
     * @ensures p = [program from file fileName]
     */
    private static void loadProgram(String fileName, Program p) {
        SimpleReader in = new SimpleReader1L(fileName);
        p.parse(in);
        in.close();
    }

    /**
     * Saves the given compiled program {@code cp} to the given output file.
     *
     * @param fileName
     *            the name of the file containing the program
     * @param cp
     *            the compiled program
     * @requires <pre>
     * [fileName is the name of a file to be used to save the compiled program]
     * </pre>
     * @ensures [cp is saved to to file fileName prefixed by the length of cp]
     */
    private static void saveCompiledProgram(String fileName,
            Sequence<Integer> cp) {
        SimpleWriter out = new SimpleWriter1L(fileName);
        out.println(cp.length());
        for (Integer i : cp) {
            out.println(i);
        }
        out.close();
    }

    /**
     * Converts {@code Condition} into corresponding conditional jump
     * instruction byte code.
     *
     * @param c
     *            the {@code Condition} to be converted
     * @return the conditional jump instruction byte code corresponding to
     *         {@code c}
     * @ensures <pre>
     * conditionalJump =
     *  [conditional jump instruction byte code corresponding to c]
     * </pre>
     */
    private static Instruction conditionalJump(Condition c) {
        assert c != null : "Violation of: c is not null";
        Instruction result;
        switch (c) {
            case NEXT_IS_EMPTY: {
                result = Instruction.JUMP_IF_NOT_NEXT_IS_EMPTY;
                break;
            }
            case NEXT_IS_NOT_EMPTY: {
                result = Instruction.JUMP_IF_NOT_NEXT_IS_NOT_EMPTY;
                break;
            }
            case NEXT_IS_ENEMY: {
                result = Instruction.JUMP_IF_NOT_NEXT_IS_ENEMY;
                break;
            }
            case NEXT_IS_NOT_ENEMY: {
                result = Instruction.JUMP_IF_NOT_NEXT_IS_NOT_ENEMY;
                break;
            }
            case NEXT_IS_FRIEND: {
                result = Instruction.JUMP_IF_NOT_NEXT_IS_FRIEND;
                break;
            }
            case NEXT_IS_NOT_FRIEND: {
                result = Instruction.JUMP_IF_NOT_NEXT_IS_NOT_FRIEND;
                break;
            }
            case NEXT_IS_WALL: {
                result = Instruction.JUMP_IF_NOT_NEXT_IS_WALL;
                break;
            }
            case NEXT_IS_NOT_WALL: {
                result = Instruction.JUMP_IF_NOT_NEXT_IS_NOT_WALL;
                break;
            }
            case RANDOM: {
                result = Instruction.JUMP_IF_NOT_RANDOM;
                break;
            }
            default: { // case TRUE
                result = Instruction.JUMP_IF_NOT_TRUE;
                break;
            }
        }
        return result;
    }

    /**
     * Generates the sequence of virtual machine instructions ("byte codes")
     * corresponding to {@code s} and appends it at the end of {@code cp}.
     *
     * @param s
     *            the {@code Statement} for which to generate code
     * @param context
     *            the {@code Context} in which to find user defined instructions
     * @param cp
     *            the {@code Sequence} containing the generated code
     * @updates cp
     * @ensures <pre>
     * if [all instructions called in s are either primitive or
     *     defined in context]  and
     *    [context does not include any calling cycles, i.e., recursion] then
     *  cp = #cp * s[the sequence of virtual machine "byte codes" corresponding to s]
     * else
     *  [reports an appropriate error message to the console and terminates client]
     * </pre>
     */
    private static void generateCodeForStatement(Statement s,
            Map<String, Statement> context, Sequence<Integer> cp) {

        final int dummy = 0;

        switch (s.kind()) {
            case BLOCK: {

                // TODO - fill in case
                Statement hold = s.newInstance();
                int i = 0;
                while (i < s.lengthOfBlock()) {
                    hold = s.removeFromBlock(i);
                    generateCodeForStatement(hold, context, cp);
                    s.addToBlock(i, hold);
                    i++;
                }

                break;
            }
            case IF: {
                Statement b = s.newInstance();
                Condition c = s.disassembleIf(b);
                cp.add(cp.length(), conditionalJump(c).byteCode());
                int jump = cp.length();
                cp.add(cp.length(), dummy);
                generateCodeForStatement(b, context, cp);
                cp.replaceEntry(jump, cp.length());
                s.assembleIf(c, b);
                break;
            }
            case IF_ELSE: {

                // TODO - fill in case
                Statement one = s.newInstance();
                Statement two = s.newInstance();
                Condition cond = s.disassembleIfElse(one, two);
                cp.add(cp.length(), conditionalJump(cond).byteCode());
                int jump = cp.length();
                cp.add(cp.length(), dummy);
                generateCodeForStatement(one, context, cp);
                cp.add(cp.length(), Instruction.JUMP.byteCode());
                int jump2 = cp.length();
                cp.add(cp.length(), dummy);
                cp.replaceEntry(jump, cp.length());
                generateCodeForStatement(two, context, cp);
                cp.replaceEntry(jump2, cp.length());
                s.assembleIfElse(cond, one, two);

                break;
            }
            case WHILE: {

                // TODO - fill in case
                Statement one = s.newInstance();
                Condition cond = s.disassembleWhile(one);
                cp.add(cp.length(), conditionalJump(cond).byteCode());
                int jump = cp.length();
                cp.add(cp.length(), dummy);
                generateCodeForStatement(one, context, cp);
                cp.add(cp.length(), Instruction.JUMP.byteCode());
                cp.add(cp.length(), jump - 1);
                cp.replaceEntry(jump, cp.length());
                s.assembleWhile(cond, one);

                break;
            }
            case CALL: {

                // TODO - fill in case
                String call = s.disassembleCall();
                if (context.hasKey(call)) {
                    generateCodeForStatement(context.value(call),
                            context.newInstance(), cp);

                } else {
                    Reporter.assertElseFatalError(!context.hasKey(call),
                            "Instruction does not exist.");
                    call = call.toUpperCase();

                    cp.add(cp.length(), Instruction.valueOf(call).byteCode());
                    call = call.toLowerCase();
                }
                s.assembleCall(call);
                break;
            }
            default: {
                // this will never happen...
                break;
            }
        }
    }

    /*
     * Constructors -----------------------------------------------------------
     */

    /**
     * Default constructor.
     */
    public Program1GeneratedCode1() {
        super();
    }

    /*
     * Public methods ---------------------------------------------------------
     */

    @Override
    public Sequence<Integer> generatedCode() {
        Sequence<Integer> cp = new Sequence1L<Integer>();

        // TODO - fill in body
        Map<String, Statement> contextEmpty = this.newContext();
        Map<String, Statement> context = this.replaceContext(contextEmpty);
        Statement bodyEmpty = this.newBody();
        Statement body = this.replaceBody(bodyEmpty);
        generateCodeForStatement(body, context, cp);
        this.replaceBody(body);
        this.replaceContext(context);

        cp.add(cp.length(), 5);
        return cp;
    }

    /*
     * Main test method -------------------------------------------------------
     */

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        /*
         * Get input file name
         */
        out.print("Enter valid BL program file name: ");
        String fileName = in.nextLine();
        /*
         * Generate expected output in file "data/expected-output.txt"
         */
        out.println("*** Generating expected output ***");
        Program p1 = new Program1();
        loadProgram(fileName, p1);
        Sequence<Integer> cp1 = p1.generatedCode();
        saveCompiledProgram("data/expected-output.txt", cp1);
        /*
         * Disassemble generated code
         */
        out.println("*** Expected disassembled output ***");
        Program1.disassembleProgram(out, cp1);
        /*
         * Generate actual output in file "data/actual-output.txt"
         */
        out.println("*** Generating actual output ***");
        Program p2 = new Program1GeneratedCode1();
        loadProgram(fileName, p2);
        Sequence<Integer> cp2 = p2.generatedCode();
        saveCompiledProgram("data/actual-output.txt", cp2);
        /*
         * Disassemble generated code
         */
        out.println("*** Actual disassembled output ***");
        Program1.disassembleProgram(out, cp2);
        /*
         * Check that generatedCode restored the value of the program
         */
        out.println();
        if (p2.equals(p1)) {
            out.println("Program value restored correctly.");
        } else {
            out.println("Error: program value was not restored.");
        }

        in.close();
        out.close();
    }

}
