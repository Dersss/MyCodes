import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.Reporter;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Program to evaluate XMLTree expressions of {@code int}.
 *
 * @author Andrew Schneider
 *
 */
public final class XMLTreeNNExpressionEvaluator {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private XMLTreeNNExpressionEvaluator() {
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
    private static NaturalNumber evaluate(XMLTree exp) {
        assert exp != null : "Violation of: exp is not null";

        /*
         * Checks to ensure that the tag is not <expression> Records number of
         * Children (should be two)
         */

        boolean expError = exp.hasAttribute("expression");
        int numChild = exp.numberOfChildren();

        /*
         * Values to hold the initial natural numbers, the final return, and the
         * operand
         */

        NaturalNumber num1NN = new NaturalNumber2();
        String operand = "";
        NaturalNumber num2NN = new NaturalNumber2();
        NaturalNumber toReturn = new NaturalNumber2();
        SimpleWriter out = new SimpleWriter1L();

        /*
         * loops while the expression does not have the tag <expression> and
         * there are two children (values in the expression)
         */

        while (!expError && numChild == 2) {

            /*
             * Checks label of XMLTree exp and tests to see what operation is to
             * be performed
             */

            if (exp.label().equals("plus") == true) {
                operand = "+";
            }
            if (exp.label().equals("minus") == true) {
                operand = "-";
            }
            if (exp.label().equals("times") == true) {
                operand = "*";
            }
            if (exp.label().equals("divide") == true) {
                operand = "/";
            }

            /*
             * Because an expression consists of two values having arithmetic
             * performed it is assumed that there will be 2 children of each
             * call of evaluate. If the child is a number then record it, if not
             * it is likely to be another expression. Recursively call.
             */

            if (exp.child(0).hasAttribute("value") == true) {
                String numStr = exp.child(0).attributeValue("value");
                int numInt = Integer.parseInt(numStr);
                if (numInt < 0) {

                    /*
                     * Checks to ensure initial value is not negative
                     */

                    Reporter.fatalErrorToConsole(
                            "ERROR: Initial value can't be negative");
                } else {
                    num1NN.setFromString(numStr);
                }
            } else {
                num1NN = evaluate(exp.child(0));
            }
            if (exp.child(1).hasAttribute("value") == true) {
                String numStr2 = exp.child(1).attributeValue("value");
                int numInt2 = Integer.parseInt(numStr2);
                if (numInt2 < 0) {

                    /*
                     * Checks to ensure initial value is not negative
                     */

                    Reporter.fatalErrorToConsole(
                            "ERROR: Initial value can't be negative");
                } else {
                    num2NN.setFromString(numStr2);
                }
            } else {
                num2NN = evaluate(exp.child(1));
            }

            /*
             * Final test to set final numbers to perform operation on and
             * created temporary natural numbers to hold those values. Checks to
             * make sure that numbers are not negative and performs operation
             */

            boolean end1 = false;
            boolean end2 = false;
            if (num1NN.toInt() >= 0) {
                end1 = true;
            }
            if (num2NN.toInt() >= 0) {
                end2 = true;
            }

            /*
             * If both numbers are not negative: perform final arithmetic
             */

            if (end1 && end2) {
                NaturalNumber temp = new NaturalNumber2(num1NN);
                NaturalNumber temp2 = new NaturalNumber2(num2NN);
                int tempI1 = temp.toInt();
                int tempI2 = temp2.toInt();

                if (operand == "+") {
                    temp.add(temp2);
                }
                if (operand == "-") {
                    if (tempI1 >= tempI2) {
                        temp.subtract(temp2);
                    } else {

                        /*
                         * If performing the subtract operation and there's a
                         * result of a negative number, the system reports an
                         * error and then closes
                         */

                        Reporter.fatalErrorToConsole(
                                "ERROR: possible negative number");
                    }
                }
                if (operand == "*") {
                    temp.multiply(temp2);
                }
                if (operand == "/") {
                    boolean num1Zero = temp.isZero();
                    boolean num2Zero = temp2.isZero();
                    if (num1Zero || num2Zero) {

                        /*
                         * If the the one of the values to be divided is zero,
                         * the system reports an error and then closes
                         */

                        Reporter.fatalErrorToConsole(
                                "ERROR: can't divide by zero");
                    } else {
                        temp.divide(temp2);
                    }
                }
                expError = true;
                toReturn.transferFrom(temp);
            }

            break;
        }
        out.close();

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
