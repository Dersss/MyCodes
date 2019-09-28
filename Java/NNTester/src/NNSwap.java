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
public final class NNSwap {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private NNSwap() {
    }

    /**
     * Swaps the two given {@code NaturalNumber}s.
     *
     * @param n1
     *            the first {@code NaturalNumber}
     * @param n2
     *            the second {@code NaturalNumber}
     * @updates n1
     * @updates n2
     * @ensures n1 = #n2 and n2 = #n1
     */
    private static void swapNN(NaturalNumber n1, NaturalNumber n2) {
        NaturalNumber temp = new NaturalNumber1L(n1);
        n1.copyFrom(n2);
        n2.copyFrom(temp);

    }

    /**
     * Swaps the two given {@code NaturalNumber}s.
     *
     * @param n1
     *            the first {@code NaturalNumber}
     * @param n2
     *            the second {@code NaturalNumber}
     * @updates n1
     * @updates n2
     * @ensures n1 = #n2 and n2 = #n1
     */
    private static void swapNN2(NaturalNumber n1, NaturalNumber n2) {
        NaturalNumber temp = new NaturalNumber1L(n1);
        n1.transferFrom(n2);
        n2.transferFrom(temp);

    }

    /**
     * Squares a given {@code NaturalNumber}.
     *
     * @param n
     *            the number to square
     * @updates n
     * @ensures n = #n * #n
     */
    private static void square(NaturalNumber n) {
        NaturalNumber temp = new NaturalNumber1L(n);
        n.multiply(temp);
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
        NaturalNumber a = new NaturalNumber1L(9);
        NaturalNumber b = new NaturalNumber1L(123);
        out.println("a before: " + a);
        out.println("b before: " + b);
        swapNN2(a, b);
        out.println("a after: " + a);
        out.println("b after: " + b);

        NaturalNumber c = new NaturalNumber1L(4);
        out.println("c before: " + c);
        square(c);
        out.println("c after: " + c);

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
