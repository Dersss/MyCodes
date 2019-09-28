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
public final class Hailstone1 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Hailstone1() {

    }

    /**
     * Generates and outputs the Hailstone Series starting with the given
     * integer
     *
     * @param n
     *            the starting integer
     * @param out
     *            the output stream
     *
     */
    private static void generateSeries(int n, SimpleWriter out) {
        boolean even = false;
        if (n == 1) {
            out.print(n);
            out.println();
        } else {
            out.print(n + ", ");

            if (n % 2 == 0) {
                even = true;
                n = n / 2;
                generateSeries(n, out);
            } else {
                n = (3 * n) + 1;
                generateSeries(n, out);
            }
        }

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

        out.println("Please enter a positive integer: ");
        double userNum = in.nextDouble();
        if (userNum <= 0) {
            out.println("Not a positive integer. Try again.");
            userNum = in.nextDouble();
        }

        int x = (int) userNum;
        generateSeries(x, out);

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
