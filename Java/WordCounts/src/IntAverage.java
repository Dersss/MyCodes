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
public final class IntAverage {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private IntAverage() {
    }

    /**
     * Returns the integer average of two given {@code int}s.
     *
     * @param j
     *            the first of two integers to average
     * @param k
     *            the second of two integers to average
     * @return the integer average of j and k
     * @ensures average = (j+k)/2
     */
    public static int average(int j, int k) {

        int toRet;
        if ((j % 2 != 0) && (k % 2 != 0)) {
            // If both numbers are even
            if (j > 0 && k > 0) {
                toRet = (j / 2) + (k / 2) + 1;
            } else {
                toRet = (j / 2) + (k / 2) - 1;
            }
        } else {
            toRet = (j / 2) + (k / 2);
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
         * Put your main program code here
         */

        int i = Integer.MIN_VALUE;
        ;
        int j = Integer.MIN_VALUE;
        ;
        int avg1 = average(i, j);
        System.out.println(avg1);
        System.out.println("int max: " + Integer.MAX_VALUE);

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
