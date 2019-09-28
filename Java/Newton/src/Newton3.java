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
public final class Newton3 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Newton3() {
    }

    /**
     * @HALF value for 0.5
     */
    private static final double HALF = 0.5;
    /**
     * @EPSILON value for e
     */
    private static final double EPSILON = 0.0001;

    /**
     * Computes estimate of square root of x to within relative error 0.01%.
     *
     * @param x
     *            number greater or equal to zero to compute square root of
     * @param e
     *            value of e for percent error
     *
     * @return estimate of square root
     */
    private static double sqrt(double x, double e) {
        double guess = x;
        double epsilon = e;
        if (guess == 0) {
            return 0;
        } else {
            while (((Math.abs((guess * guess) - x)) / x) > epsilon) {
                guess = HALF * (guess + (x / guess));
            }
        }
        return guess;
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

        /*
         * Prompt user to calculate another root if "y" or "Y" continue if
         * anything else, quit
         */

        boolean quit = false;
        out.println("Please enter a number for e: ");
        double e = in.nextDouble();
        out.println("Please enter a number to calculate the square root of:");
        double num = in.nextDouble();

        double newNum = sqrt(num, e);
        out.println("The square root is: " + newNum);
        out.println();

        while (!quit) {

            out.println("Would you like to calculate another root?");
            String resp = in.nextLine();
            if (resp.equals("y") || resp.equals("Y") || resp.charAt(0) == 'y'
                    || resp.charAt(0) == 'Y') {
                out.println();
                out.println(
                        "Please enter a number to calculate the square root of:");
                double num2 = in.nextDouble();
                double newNum2 = sqrt(num2, e);
                out.println("The square root is: " + newNum2);
                out.println();
            } else {
                out.println("Quitting...");
                quit = true;
            }
        }
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
