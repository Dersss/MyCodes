import components.map.Map;
import components.program.Program;
import components.program.Program1;
import components.queue.Queue;
import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.statement.Statement;
import components.utilities.Reporter;
import components.utilities.Tokenizer;

/**
 * Layered implementation of secondary method {@code parse} for {@code Program}.
 *
 * @author Andrew Schneider
 * @author Eric Sullivan
 * @author Hitesh Bavisetti
 */
public final class Program1Parse1 extends Program1 {

    /*
     * Private members --------------------------------------------------------
     */

    /**
     * Parses a single BL instruction from {@code tokens} returning the
     * instruction name as the value of the function and the body of the
     * instruction in {@code body}.
     *
     * @param tokens
     *            the input tokens
     * @param body
     *            the instruction body
     * @return the instruction name
     * @replaces body
     * @updates tokens
     * @requires <pre>
     * [<"INSTRUCTION"> is a prefix of tokens]  and
     *  [<Tokenizer.END_OF_INPUT> is a suffix of tokens]
     * </pre>
     * @ensures <pre>
     * if [an instruction string is a proper prefix of #tokens]  and
     *    [the beginning name of this instruction equals its ending name]  and
     *    [the name of this instruction does not equal the name of a primitive
     *     instruction in the BL language] then
     *  parseInstruction = [name of instruction at start of #tokens]  and
     *  body = [Statement corresponding to statement string of body of
     *          instruction at start of #tokens]  and
     *  #tokens = [instruction string at start of #tokens] * tokens
     * else
     *  [report an appropriate error message to the console and terminate client]
     * </pre>
     */
    private static String parseInstruction(Queue<String> tokens,
            Statement body) {
        assert tokens != null : "Violation of: tokens is not null";
        assert body != null : "Violation of: body is not null";
        assert tokens.length() > 0 && tokens.front().equals("INSTRUCTION") : ""
                + "Violation of: <\"INSTRUCTION\"> is proper prefix of tokens";

        String instruction = tokens.dequeue();
        Reporter.assertElseFatalError(instruction.equals("INSTRUCTION"),
                "Expected INSTRUCTION but was " + instruction);
        String identifier = tokens.dequeue();
        Reporter.assertElseFatalError(Tokenizer.isIdentifier(identifier),
                "Expected an IDENTIFIER but was " + identifier);
        String is = tokens.dequeue();
        Reporter.assertElseFatalError(is.equals("IS"),
                "Expected IS but was " + is);

        body.parseBlock(tokens);
        String endIs = tokens.dequeue();
        String end = tokens.dequeue();

        Reporter.assertElseFatalError(identifier.equals(end),
                "Begininng and ending identifiers do not match. ");

        return identifier;
    }

    /*
     * Constructors -----------------------------------------------------------
     */

    /**
     * No-argument constructor.
     */
    public Program1Parse1() {
        super();
    }

    /*
     * Public methods ---------------------------------------------------------
     */

    @Override
    public void parse(SimpleReader in) {
        assert in != null : "Violation of: in is not null";
        assert in.isOpen() : "Violation of: in.is_open";

        Queue<String> tokens = Tokenizer.tokens(in);
        this.parse(tokens);
    }

    @Override
    public void parse(Queue<String> tokens) {
        assert tokens != null : "Violation of: tokens is not null";
        assert tokens.length() > 0 : ""
                + "Violation of: Tokenizer.END_OF_INPUT is a suffix of tokens";

        String program = tokens.dequeue();
        Reporter.assertElseFatalError(program.equals("PROGRAM"),
                "Expected: PROGRAM , was: " + program);
        String id = tokens.dequeue();
        Reporter.assertElseFatalError(Tokenizer.isIdentifier(id),
                "Expected an identifier , was: " + id);

        this.replaceName(id);
        String wordIs = tokens.dequeue();
        Reporter.assertElseFatalError(wordIs.equals("IS"),
                "Expected: IS , was: " + wordIs);

        Map<String, Statement> context = this.newContext();
        Set<String> instr = new Set1L<>();

        while (!tokens.front().equals("BEGIN")) {
            Statement body = this.newBody();
            String instName = parseInstruction(tokens, body);
            context.add(instName, body);
            Reporter.assertElseFatalError(context.hasKey(instName),
                    "Duplicate instructions");
            instr.add(instName);

            while (instr.size() > 0) {
                String checker = instr.removeAny();
                boolean primitiveChecker = false;

                if (checker.toLowerCase().equals("turnright")
                        || checker.toLowerCase().equals("turnleft")
                        || checker.toLowerCase().equals("move")
                        || checker.toLowerCase().equals("skip")
                        || checker.toLowerCase().equals("infect")) {
                    primitiveChecker = true;
                    Reporter.assertElseFatalError(primitiveChecker,
                            "Instruction is a primitive call");
                }

                Reporter.assertElseFatalError(!Tokenizer.isCondition(checker),
                        "Instruction is a condition.");
                Reporter.assertElseFatalError(Tokenizer.isIdentifier(checker),
                        "Instruction is an identifier.");
                Reporter.assertElseFatalError(!Tokenizer.isKeyword(checker),
                        "Instruction is a condition.");

            }
        }
        Statement blockBdy = this.replaceBody(this.newBody());
        String begin = tokens.dequeue();
        blockBdy.parseBlock(tokens);

        String end = tokens.dequeue();
        String endId = tokens.dequeue();

        Reporter.assertElseFatalError(id.equals(endId),
                "Begininng and ending identifiers do not match.");
        Reporter.assertElseFatalError(
                Tokenizer.END_OF_INPUT.equals(tokens.front()),
                "Extra tokens are not allowed at the end of the program.");

        //this.replaceName(id); already done above?
        this.replaceContext(context);
        this.replaceBody(blockBdy);
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
         * Parse input file
         */
        out.println("*** Parsing input file ***");
        Program p = new Program1Parse1();
        SimpleReader file = new SimpleReader1L(fileName);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        p.parse(tokens);
        /*
         * Pretty print the program
         */
        out.println("*** Pretty print of parsed program ***");
        p.prettyPrint(out);

        in.close();
        out.close();
    }

}
