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
public final class XMLTreeNNExpressionEvaluator2 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private XMLTreeNNExpressionEvaluator2() {
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

        /**
         * output for error messages
         */
        SimpleWriter out = new SimpleWriter1L();

        /**
         * NaturalNumbers for end result of expression, num1, and num2. As well
         * as a string to hold the operand.
         */
        NaturalNumber end = new NaturalNumber2(0);
        NaturalNumber num1 = new NaturalNumber2(0);
        NaturalNumber num2 = new NaturalNumber2();
        String operand = "";

        /**
         * Since arithmetic is being performed there must be two numbers, and
         * therefore two children of the first tag.
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
                int numA = Integer.parseInt(hold);
                num1.copyFrom(new NaturalNumber2(numA));
            } else {
                num1.copyFrom(evaluate(exp.child(0)));
            }
            if (exp.child(1).hasAttribute("value")) {
                String hold2 = exp.child(1).attributeValue("value");
                int numB = Integer.parseInt(hold2);
                num2.copyFrom(new NaturalNumber2(numB));
            } else {
                num2.copyFrom(evaluate(exp.child(1)));
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
            if (exp.label().equals("times")) {
                operand = "*";
            }
            if (exp.label().equals("divide")) {
                operand = "/";
            }
            if (exp.label().equals("power")) {
                operand = "^";
            }
            if (exp.label().equals("mod")) {
                operand = "%";
            }

            /**
             * Performs operation once both num1 and num2 values have been
             * changed from their initial values of 0. Uses the stored value of
             * operand to perform.
             */
            if (num1.compareTo(new NaturalNumber2(0)) != 0
                    && num2.compareTo(new NaturalNumber2(0)) != 0) {

                // Addition
                if (operand == "+") {
                    end.add(num1);
                    end.add(num2);
                }

                // Subtraction
                if (operand == "-") {
                    if (num1.compareTo(num2) < 0) {
                        Reporter.fatalErrorToConsole(
                                "Error! Possible negative number.");
                    } else {
                        end.add(num1);
                        end.subtract(num2);
                    }
                }

                // Multiplication
                if (operand == "*") {
                    end.add(num1);
                    end.multiply(num2);
                }

                // Division
                if (operand == "/") {
                    if (num2.compareTo(new NaturalNumber2(0)) == 0) {
                        Reporter.fatalErrorToConsole(
                                "Error! Can not divde by zero.");
                    }
                    end.add(num1);
                    end.divide(num2);
                }

                // Power
                if (operand == "^") {
                    end.add(num1);
                    int temp = num2.toInt();
                    end.power(temp);
                }

            }

        }
        out.close();
        return end;
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
