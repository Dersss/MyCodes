import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.FormatChecker;

/**
 * A program that uses the de Jager Formula to approximate a user given
 * constant..
 *
 * @author Andrew Schneider
 *
 */
public final class ABCDGuesser2 {

    /**
     * Array for the exponents used in the de Jager Formula.
     */
    public static final int[] EXPONENTS = { -5, -4, -3, -2, -1, -1 / 2, -1 / 3,
            -1 / 4, 0, 1 / 4, 1 / 3, 1 / 2, 1, 2, 3, 4, 5 };

    /**
     * Integer for multiply for percent error.
     */
    public static final int HUNDRED = 100;

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private ABCDGuesser2() {
    }

    /**
     * Repeatedly asks the user for a positive real number until the user enters
     * one. Returns the positive real number.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive real number entered by the user
     *
     * @ensures pos1 > 0
     */
    private static double getPositiveDouble(SimpleReader in, SimpleWriter out) {
        out.println("Please enter a positive real number: ");
        String pos = in.nextLine();
        double pos1 = 0;
        boolean test = FormatChecker.canParseDouble(pos);
        if (!test) {
            out.println("Not a positive real number. Try again.");
            pos1 = getPositiveDouble(in, out);
        } else {
            pos1 = Double.parseDouble(pos);

            if (pos1 <= 0) {
                out.println("Not a positive real number. Try again.");
                pos1 = getPositiveDouble(in, out);
            }
        }
        out.println();
        return pos1;
    }

    /**
     * Repeatedly asks the user for a positive real number not equal to 1.0
     * until the user enters one. Returns the positive real number.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive real number not equal to 1.0 entered by the user
     *
     * @ensures posNot1 > 1
     */
    private static double getPositiveDoubleNotOne(SimpleReader in,
            SimpleWriter out) {
        out.println("Please enter a positive real number greater than 1: ");
        String posNot1 = in.nextLine();
        double posNot1D = 0;
        boolean test = FormatChecker.canParseDouble(posNot1);
        if (!test) {
            out.println(
                    "Not a positive real number greater than 1. Try again.");
            posNot1D = getPositiveDouble(in, out);
        } else {
            posNot1D = Double.parseDouble(posNot1);
            if (posNot1D <= 0) {
                out.println("Not a positive real number. Try again.");
                posNot1D = getPositiveDouble(in, out);
            }

        }
        out.println();
        return posNot1D;
    }

    /**
     * Method to check the current estimate against the best estimate thus far.
     *
     * @param constant
     *            the constant to be approximated
     * @param bestEstimate
     *            the current best estimate
     * @param current
     *            the current guess
     * @return the best estimate against the constant
     *
     */

    public static double findingBestEstimate(double constant,
            double bestEstimate, double current) {
        double toRet = bestEstimate;
        if (Math.abs(constant - current) < Math.abs(constant - bestEstimate)) {
            toRet = current;
        }
        return toRet;
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

        out.println("First enter a constant to be approximated.");
        double constant = getPositiveDouble(in, out);
        out.println("PERSONAL NUMBERS");
        out.println("Value for W.");
        double w = getPositiveDoubleNotOne(in, out);
        out.println("Value for X.");
        double x = getPositiveDoubleNotOne(in, out);
        out.println("Value for Y.");
        double y = getPositiveDoubleNotOne(in, out);
        out.println("Value for Z.");
        double z = getPositiveDoubleNotOne(in, out);

        out.println("Constant: " + constant);
        out.println("w: " + w);
        out.println("x: " + x);
        out.println("y: " + y);
        out.println("z: " + z);
        out.println();

        double tempA = 0;
        double tempB = 0;
        double tempC = 0;
        double tempD = 0;
        double a = 0;
        double b = 0;
        double c = 0;
        double d = 0;
        double bestEstimate = 0;

        for (int h = 0; h < EXPONENTS.length; h++) {
            tempA = EXPONENTS[h];
            for (int i = 0; i < EXPONENTS.length; i++) {
                tempB = EXPONENTS[i];
                for (int j = 0; j < EXPONENTS.length; j++) {
                    tempC = EXPONENTS[j];
                    for (int k = 0; k < EXPONENTS.length; k++) {
                        tempD = EXPONENTS[k];
                        double current = Math.pow(w, tempA) * Math.pow(x, tempB)
                                * Math.pow(y, tempC) * Math.pow(z, tempD);
                        double compare = findingBestEstimate(constant,
                                bestEstimate, current);
                        if (compare != bestEstimate) {
                            bestEstimate = compare;
                            a = tempA;
                            b = tempB;
                            c = tempC;
                            d = tempD;
                        }

                    }
                }

            }
        }

        out.println("Best Estimate: " + bestEstimate);

        double error = Math.abs((constant - bestEstimate) / constant);
        int decimals = 2;

        out.println("Best Exponents(a, b, c, d): " + "(" + a + ", " + b + ", "
                + c + ", " + d + ") ");
        out.print("Percent Error: ");
        out.print((error * HUNDRED), decimals, false);
        out.print("%\n");

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
