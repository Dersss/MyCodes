import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class NNRoot {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private NNRoot() {
    }

    /**
     * Returns {@code n} to the power {@code p}.
     *
     * @param n
     *            the number to which we want to apply the power
     * @param p
     *            the power
     * @return the number to the power
     * @requires Integer.MIN_VALUE <= n ^ (p) <= Integer.MAX_VALUE and p >= 0
     * @ensures power = n ^ (p)
     */
    private static int power(int n, int p) {
        int product = n;
        int i = 1;
        while (i < p) {
            product *= n;
            i++;
        }
        return product;
    }

    /**
     * Returns the {@code r}-th root of {@code n}.
     *
     * @param n
     *            the number to which we want to apply the root
     * @param r
     *            the root
     * @return the root of the number
     * @requires n >= 0 and r > 0
     * @ensures root ^ (r) <= n < (root + 1) ^ (r)
     */
    private static int root(int n, int r) {
        int guess = 1;
        int toRet = 0;
        while (guess < n) {
            int i = 1;
            int hold = guess;

            while (i < r) {
                hold *= guess;
                i++;
            }
            if (hold == n) {
                toRet = guess;
            }
            guess++;
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
        /*
         * Put your main program code here; it may call myMethod as shown
         */

        out.println("Enter an integer value for base: ");
        int a = in.nextInteger();
        out.println("Enter an integer value for expnonent: ");
        int b = in.nextInteger();
        out.println(a + " ^ " + b + " = " + power(a, b));

        out.println();
        out.println("Enter an integer value for base: ");
        int c = in.nextInteger();
        out.println("Enter an integer value for root: ");
        int d = in.nextInteger();

        out.println(c + " root " + d + " = " + root(c, d));
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
