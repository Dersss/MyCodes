import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.FormatChecker;

/**
 * Put a short phrase describing the program here.
 *
 * @author Andrew Schneider
 *
 */
public final class Hailstone5 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */

    /**
     * MAGIC NUMBER ELIMINATION.
     */
    public static final int THREE = 3;
    /**
     * MAGIC NUMBER ELIMINATION.
     */
    public static final int TWO = 2;
    /**
     * MAGIC NUMBER ELIMINATION.
     */
    private static final int ONE = 1;

    /**
     * constructor
     */
    private Hailstone5() {
    }

    /**
     * Repeatedly asks the user for a positive integer until the user enters
     * one. Returns the positive integer.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive integer entered by the user
     */
    private static int getPositiveInteger(SimpleReader in, SimpleWriter out) {
        out.println("Please enter a positive integer: ");
        String user = in.nextLine();
        int toRet = 0;
        if (FormatChecker.canParseInt(user) && (Integer.parseInt(user) >= 0)) {
            toRet = Integer.parseInt(user);
        } else {
            out.println("Not a valid number. Try again: ");
            user = in.nextLine();
        }
        return toRet;

    }

    /**
     * Generates and outputs the Hailstone Series starting with the given
     * integer and then outputs the length and max integer of the series.
     *
     *
     * @param n
     *            the starting integer
     * @param out
     *            the output stream
     *
     */
    private static void generateSeries(int n, SimpleWriter out) {
        int x = n;
        int length = 1;
        int max = n;
        while (x != 1) {
            out.print(x + ", ");
            if (x % TWO == 0) {
                x = x / TWO;
            } else {
                x = THREE * x + ONE;
            }
            length++;
            if (x > max) {
                max = x;
            }
        }
        if (x == 1) {
            out.print(x);
            out.println();
            out.println();
            out.println("The length of the Hailstone Series: " + length);
            out.println("The max value of the Hailstone Series: " + max);
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

        int hold = getPositiveInteger(in, out);

        generateSeries(hold, out);

        boolean end = false;
        while (!end) {
            out.println();
            out.println("Calculate another series? ");
            String answer = in.nextLine();
            if (answer.contains("y")) {

                int userNum2 = getPositiveInteger(in, out);
                int hold2 = userNum2;

                generateSeries(hold2, out);
            } else {
                out.println("Goodbye!");
                end = true;
            }
        }
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
