import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Andrew Schneider
 *
 */
public final class ABCDGuesser2 {

    /**
     * Repeatedly asks the user for a positive real number until the user enters
     * one. Returns the positive real number.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive real number entered by the user
     */
    private static double getPositiveDouble(SimpleReader in, SimpleWriter out) {
        boolean end = false;
        double toRet = 0;

        //While loop that runs until the user enters a valid number
        while (!end) {
            out.print("Please enter a positive real number: ");
            String input = in.nextLine();
            double posNum = Double.parseDouble(input);
            // If the number is both greater than one and a whole number it
            // is valid.

            if (posNum > 0) {
                toRet = posNum;
                end = true;
            }

        }
        return toRet;

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
     */
    private static double getPositiveDoubleNotOne(SimpleReader in,
            SimpleWriter out) {
        boolean end = false;
        double notOne = 0;
        /*
         * While loop that runs until the user enters valid input
         */
        while (!end) {
            out.print("Please enter a real number that is greater than 1: ");
            notOne = in.nextDouble();
            if (notOne > 1) {
                end = true;
            }
        }
        return notOne;

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
        double w = 0;
        double x = 0;
        double y = 0;
        double z = 0;

        double a = 0;
        double b = 0;
        double c = 0;
        double d = 0;

        double tempA = 0;
        double tempB = 0;
        double tempC = 0;
        double tempD = 0;

        double constant = 0;

        double[] exponents = { -5, -4, -3, -2, -1, -0.5, -(1 / 3), -0.25, 0,
                0.25, (1 / 3), 0.25, 1, 2, 3, 4, 5 };

        out.println("Value of constant: ");
        constant = getPositiveDouble(in, out);
        out.println("Value of w: ");
        w = getPositiveDoubleNotOne(in, out);
        out.println("Value of x: ");
        x = getPositiveDoubleNotOne(in, out);
        out.println("Value of y: ");
        y = getPositiveDoubleNotOne(in, out);
        out.println("Value of z: ");
        z = getPositiveDoubleNotOne(in, out);

        out.println("(" + w + ", " + x + ", " + y + ", " + z + ")");

        double bestEstimate = 0;

        /*
         * Finds value that is close to the constant by looping through the
         * exponent array
         */

        for (int counter1 = 0; counter1 < exponents.length; counter1++) {
            tempA = exponents[counter1];
            for (int counter2 = 0; counter2 < exponents.length; counter2++) {
                tempB = exponents[counter2];
                for (int counter3 = 0; counter3 < exponents.length; counter3++) {
                    tempC = exponents[counter3];
                    for (int counter4 = 0; counter4 < exponents.length; counter4++) {
                        tempD = exponents[counter4];
                        double current = Math.pow(w, tempA) * Math.pow(x, tempB)
                                * Math.pow(y, tempC) * Math.pow(z, tempD);
                        if (Math.abs(constant - current) < Math
                                .abs(constant - bestEstimate)) {
                            bestEstimate = current;

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

        out.println("Best Indexes: (a, b, c, d) " + a + ", " + b + ", " + c
                + ", " + d);
        out.println("Error: " + error);

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
