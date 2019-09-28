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
public final class Hailstone3 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Hailstone3() {
    }

    /**
     * Generates and outputs the Hailstone Series starting with the given
     * integer and then outputs the length and max integer of the series
     *
     * @param n
     *            the starting integer
     * @param out
     *            the output stream
     * @param max
     *            current max value
     * @param length
     *            current length of series
     *
     */
    private static void generateSeries(int n, SimpleWriter out, int max,
            int length) {
        boolean even = false;
        int size = length;
        int maxH = max;
        if (n > max) {
            maxH = n;
        }
        if (n == 1) {
            size++;
            out.print(n);
            out.println();
            out.println();
            out.println("The length for this Hailstone Series: " + size);
            out.println("The max for this Hailstone Series: " + maxH);
        } else {
            out.print(n + ", ");

            if (n % 2 == 0) {
                even = true;
                n = n / 2;
                size++;
                generateSeries(n, out, maxH, size);
            } else {
                n = (3 * n) + 1;
                size++;
                generateSeries(n, out, maxH, size);
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
        int max = x;
        int length = 0;
        generateSeries(x, out, max, length);

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
