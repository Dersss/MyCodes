import components.statement.Statement;
import components.statement.StatementKernel.Condition;

/**
 * Utility class with method to count the number of calls to primitive
 * instructions (move, turnleft, turnright, infect, skip) in a given
 * {@code Statement}.
 *
 * @author Put your name here
 *
 */
public final class CountPrimitiveCalls2 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private CountPrimitiveCalls2() {
    }

    /**
     * Reports the number of calls to primitive instructions (move, turnleft,
     * turnright, infect, skip) in a given {@code Statement}.
     *
     * @param s
     *            the {@code Statement}
     * @return the number of calls to primitive instructions in {@code s}
     * @ensures <pre>
     * countOfPrimitiveCalls =
     *  [number of calls to primitive instructions in s]
     * </pre>
     */
    public static int countOfPrimitiveCalls(Statement s) {
        int count = 0;
        switch (s.kind()) {
            case BLOCK: {
                /*
                 * Add up the number of calls to primitive instructions in each
                 * nested statement in the BLOCK.
                 */

                int index = 0;
                while (index < s.lengthOfBlock()) {
                    Statement s2 = s.removeFromBlock(index);
                    String k = s2.kind().toString();
                    System.out.println(s);
                    if (k.equals("CALL")) {
                        String call = s2.disassembleCall();
                        String copy = call;
                        s2.assembleCall(call);
                        s2.assembleCall(call);
                    }
                    System.out.println(s);
                    count += countOfPrimitiveCalls(s2);
                    s.addToBlock(index, s2);
                    index++;
                }
                break;
            }
            case IF: {
                /*
                 * Find the number of calls to primitive instructions in the
                 * body of the IF.
                 */

                // TODO - fill in case
                Statement ifStatement = s.newInstance();
                Condition cond = s.disassembleIf(ifStatement);
                count += countOfPrimitiveCalls(ifStatement);
                s.assembleIf(cond, ifStatement);

                break;
            }
            case IF_ELSE: {
                /*
                 * Add up the number of calls to primitive instructions in the
                 * "then" and "else" bodies of the IF_ELSE.
                 */

                // TODO - fill in case
                Statement ifStatement = s.newInstance();
                Statement elseStatement = s.newInstance();
                Condition ifElseCond = s.disassembleIfElse(ifStatement,
                        elseStatement);
                count += countOfPrimitiveCalls(ifStatement)
                        + countOfPrimitiveCalls(elseStatement);
                s.assembleIfElse(ifElseCond, ifStatement, elseStatement);

                break;
            }
            case WHILE: {
                /*
                 * Find the number of calls to primitive instructions in the
                 * body of the WHILE.
                 */

                // TODO - fill in case
                Statement whileStatement = s.newInstance();
                Condition cond = s.disassembleWhile(whileStatement);
                count += countOfPrimitiveCalls(whileStatement);
                s.assembleWhile(cond, whileStatement);

                break;
            }
            case CALL: {
                /*
                 * This is a leaf: the count can only be 1 or 0. Determine
                 * whether this is a call to a primitive instruction or not.
                 */

                // TODO - fill in case
                String call = "";
                call = s.disassembleCall();
                String primCalls = "moveinfectskipturnleftturnright";
                if (primCalls.contains(call)) {
                    count++;
                }
                s.assembleCall(call);

                break;
            }
            default: {
                // this will never happen...can you explain why?
                break;
            }
        }
        return count;
    }

}
