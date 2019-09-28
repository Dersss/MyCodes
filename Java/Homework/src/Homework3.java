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
public final class Homework3 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Homework3() {
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();
        SimpleReader in = new SimpleReader1L();

        out.println("Enter value for n: ");
        double n = in.nextDouble();
        int pos = 0;
        int pos2 = 0;
        int pos3 = 0;

        while (pos < n) {
            if (Math.sqrt(pos) % 1 == 0) {
                out.print(pos + " ");
            }
            pos++;
        }
        out.println();

        while (pos2 < n) {
            double hold = pos2 % 10;
            if (hold == 0 && pos2 != 0) {
                out.print(pos2 + " ");
            }
            pos2++;
        }
        out.println();

        int exponent = 0;
        while (pos3 < n) {
            double hold2 = 2;

            if (Math.pow(hold2, exponent) == pos3) {
                out.print(pos3 + " ");
                exponent++;
            }
            pos3++;
        }

        out.close();
        in.close();
    }

}
