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

        // output for error messages
        SimpleWriter out = new SimpleWriter1L();

        /**
         * int expr holds the value for operation being performed, num1/num2
         * hold value of numbers being manipulated. Also creates a string to
         * hold operand.
         */
        int expr = 0;
        int num1 = 0;
        int num2 = 0;
        String operand = "";

        /**
         * Since arithmetic involves two numbers there should be at least two
         * children of the operation being performed.
         */
        if (exp.numberOfChildren() > 0) {

            /**
             * Checks to see whether child 0 and 1 have an attribute of a value.
             * If child 0 has a value then store into num1, if not then
             * recursively call on child 0. If child 1 has a value then store in
             * num2. If not then recursively call on child 1.
             */
            if (exp.child(0).hasAttribute("value")) {
                String hold = exp.child(0).attributeValue("value");
                num1 = Integer.parseInt(hold);
            } else {
                num1 = evaluate(exp.child(0));
            }
            if (exp.child(1).hasAttribute("value")) {
                String hold2 = exp.child(1).attributeValue("value");
                num2 = Integer.parseInt(hold2);
            } else {
                num2 = evaluate(exp.child(1));
            }

            /**
             * Checks to see what operation is being performed ans stores it
             * into the string variable "operand".
             */
            if (exp.label().equals("plus")) {
                operand = "+";
            }
            if (exp.label().equals("minus")) {
                operand = "-";
            }
            if (exp.label().equals("divide")) {
                operand = "/";
            }
            if (exp.label().equals("times")) {
                operand = "*";
            }
            if (exp.label().equals("power")) {
                operand = "^";
            }
            if (exp.label().equals("mod")) {
                operand = "%";
            }
            if (exp.label().equals("root")) {
                operand = "v";
            }

            /**
             * Performs operation once both num1 and num2 values have been
             * changed from their initial values of 0. Uses the stored value of
             * operand to perform.
             */
            if (num1 != 0 && num2 != 0) {

                // Addition
                if (operand == "+") {
                    expr = num1 + num2;
                }

                // Subtraction
                if (operand == "-") {
                    expr = num1 - num2;
                }

                // Multiplication
                if (operand == "*") {
                    expr = num1 * num2;
                }

                // Division
                if (operand == "/") {
                    if (num2 == 0) {
                        out.println("Error! Can not divide by zero");
                    } else {
                        expr = num1 / num2;
                    }
                }

                // Power
                if (operand == "^") {
                    expr = (int) Math.pow(num1, num2);
                }

                // Mod
                if (operand == "%") {
                    expr = num1 % num2;
                }
            }

        }

        out.close();
        /**
         * Returns the end value of the expression performed.
         */
        return expr;
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
