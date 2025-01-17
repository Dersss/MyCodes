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
public final class CountPrimitiveCalls {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private CountPrimitiveCalls() {
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

                int i = 0;
                while (i < s.lengthOfBlock()) {
                    Statement removed = s.removeFromBlock(i);
                    count += countOfPrimitiveCalls(removed);
                    s.addToBlock(i, removed);
                    i++;
                }
                break;
            }
            case IF: {
                /*
                 * Find the number of calls to primitive instructions in the
                 * body of the IF.
                 */

                // TODO - fill in case
                Statement hold = s.newInstance();
                Condition condition = s.disassembleIf(hold);
                count += countOfPrimitiveCalls(hold);
                s.assembleIf(condition, hold);

                break;
            }
            case IF_ELSE: {
                /*
                 * Add up the number of calls to primitive instructions in the
                 * "then" and "else" bodies of the IF_ELSE.
                 */

                // TODO - fill in case
                Statement blockL = s.newInstance();
                Statement blockR = s.newInstance();
                Condition condition = s.disassembleIfElse(blockL, blockR);
                count += countOfPrimitiveCalls(blockL);
                count += countOfPrimitiveCalls(blockR);
                s.assembleIfElse(condition, blockL, blockR);

                break;
            }
            case WHILE: {
                /*
                 * Find the number of calls to primitive instructions in the
                 * body of the WHILE.
                 */
                Statement block = s.newInstance();
                Condition condition = s.disassembleWhile(block);
                count += countOfPrimitiveCalls(block);
                s.assembleWhile(condition, block);

                // TODO - fill in case

                break;
            }
            case CALL: {
                /*
                 * This is a leaf: the count can only be 1 or 0. Determine
                 * whether this is a call to a primitive instruction or not.
                 */

                // TODO - fill in case
                String condition = s.disassembleCall();
                String prim = "moveturnleftturnrightinfectskip";
                if (prim.contains(condition)) {
                    count++;
                }
                s.assembleCall(condition);

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
