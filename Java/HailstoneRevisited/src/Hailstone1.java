import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber1L;
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

    public static final int THREE = 3;

    /**
     * Generates and outputs the Hailstone series starting with the given
     * {@code NaturalNumber}.
     *
     * @param n
     *            the starting natural number
     * @param out
     *            the output stream
     * @updates out.content
     * @requires n > 0 and out.is_open
     * @ensures out.content = #out.content * [the Hailstone series starting with
     *          n]
     */
    private static void generateSeries(NaturalNumber n, SimpleWriter out) {
        boolean end = false;
        NaturalNumber hold = new NaturalNumber1L(n);
        out.print(n + " ");
        while (!end) {
            if (n.compareTo(new NaturalNumber1L(1)) == 0) {
                end = true;
            } else {
                if (n.divide(new NaturalNumber1L(2))
                        .compareTo(new NaturalNumber1L(0)) > 0) {
                    n.multiply(new NaturalNumber1L(2));
                    n.increment();
                    n.multiply(new NaturalNumber1L(3));
                    n.increment();
                }
                out.print(n + " ");
            }
        }
        out.println();
        n.copyFrom(hold);

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

        NaturalNumber orig;
        out.println("Please enter a number greater than zero: ");
        int num = in.nextInteger();
        if (num <= 0) {
            out.println("Not a valid number. Try again: ");
            num = in.nextInteger();
        }
        orig = new NaturalNumber1L(num);
        generateSeries(orig, out);
        out.println("n after: " + orig);
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
