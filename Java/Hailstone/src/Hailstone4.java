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
public final class Hailstone4 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Hailstone4() {
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
        int hold = n;
        int size = length;
        int maxH = max;
        if (hold > max) {
            maxH = hold;
        }
        if (n == 1) {
            size++;
            out.print(hold);
            out.println();
            out.println();
            out.println("The length for this Hailstone Series: " + size);
            out.println("The max for this Hailstone Series: " + maxH);
        } else {
            out.print(hold + ", ");

            if (hold % 2 == 0) {
                even = true;
                hold = hold / 2;
                size++;
                generateSeries(hold, out, maxH, size);
            } else {
                hold = (3 * hold) + 1;
                size++;
                generateSeries(hold, out, maxH, size);
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

        out.println();
        out.println("Calculate another series? ");
        String resp = in.nextLine();
        if (resp.contains("y")) {
            length = 0;
            out.println("Please enter a positive integer: ");
            double nextNum = in.nextDouble();
            if (nextNum <= 0) {
                out.println("Not a positive integer. Try again.");
                nextNum = in.nextDouble();
            }
            int conv = (int) nextNum;
            max = (int) nextNum;
            generateSeries(conv, out, max, length);
        } else {
            out.println("Quitting... see you next time!");
        }

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
