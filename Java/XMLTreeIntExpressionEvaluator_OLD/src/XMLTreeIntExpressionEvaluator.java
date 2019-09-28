import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Program to evaluate XMLTree expressions of {@code int}.
 *
 * @author Andrew Schneider
 *
 */
public final class XMLTreeIntExpressionEvaluator {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private XMLTreeIntExpressionEvaluator() {
    }

    /**
     * Evaluate the given expression.
     *
     * @param exp
     *            the {@code XMLTree} representing the expression
     * @return the value of the expression
     * @requires <pre>
     * [exp is a subtree of a well-formed XML arithmetic expression]  and
     *  [the label of the root of exp is not "expression"]
     * </pre>
     * @ensures evaluate = [the value of the expression]
     */
    private static int evaluate(XMLTree exp) {
        assert exp != null : "Violation of: exp is not null";

        // TODO - fill in body

        /*
         * expError ensures root is not "expression"
         *
         * Creates integers (num1) and (num2) Creates a string to hold the
         * operand Creates integer to return
         *
         */

        boolean expError = exp.hasAttribute("expression");
        int numChild = exp.numberOfChildren();
        int num1 = -1;
        int num2 = -1;
        String operand = "";
        int toReturn = 0;

        SimpleWriter out = new SimpleWriter1L();

        /*
         * While expError is false and exp has children Sets operand equal to
         * the corresponding symbol
         */

        while (!expError && numChild > 0) {

            // Tests for plus
            if (exp.label().equals("plus")) {
                operand = "+";
            }

            // Tests for minus
            if (exp.label().equals("minus") == true) {
                operand = "-";
            }

            // Tests for times
            if (exp.label().equals("times") == true) {
                operand = "*";
            }

            // Tests for divide
            if (exp.label().equals("divide") == true) {
                operand = "/";
            }

            /*
             * Because an expression consists of two values having arithmetic
             * performed it is assumed that there will be 2 children of the
             * first operand call. If the child is a number then record it, if
             * not it is likely to be another expression. Recursively call.
             */

            if (exp.child(0).hasAttribute("value") == true) {
                String numStr = exp.child(0).attributeValue("value");
                num1 = Integer.parseInt(numStr);
            } else {
                num1 = evaluate(exp.child(0));
            }

            if (exp.child(1).hasAttribute("value") == true) {
                String numStr2 = exp.child(1).attributeValue("value");
                num2 = Integer.parseInt(numStr2);
            } else {
                num2 = evaluate(exp.child(1));
            }

            /*
             * Since initial num1 and num2 values are negative. The following
             * test ensures both values have been changed to be greater or equal
             * to zero
             *
             * performs correct operation corresponding to the operand
             */

            if (num1 >= 0 && num2 >= 0) {
                if (operand == "+") {
                    toReturn = num1 + num2;
                }
                if (operand == "-") {
                    toReturn = num1 - num2;
                }
                if (operand == "*") {
                    toReturn = num1 * num2;
                }
                if (operand == "/") {
                    if (num1 == 0 || num2 == 0) {
                        out.println("Error, can't divide by zero.");
                        System.exit(0);
                    }
                    toReturn = num1 / num2;
                }
                expError = true;
            }

        }
        // Closes output
        out.close();

        /*
         * returns final value
         */

        return toReturn;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        out.print("Enter the name of an expression XML file: ");
        String file = in.nextLine();
        while (!file.equals("")) {
            XMLTree exp = new XMLTree1(file);
            out.println(evaluate(exp.child(0)));
            out.print("Enter the name of an expression XML file: ");
            file = in.nextLine();
        }

        in.close();
        out.close();
    }

}
