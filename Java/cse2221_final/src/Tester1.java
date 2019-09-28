import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
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
public final class Tester1 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Tester1() {
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

        int x = 10;
        String str = "hello";
        NaturalNumber num = new NaturalNumber2(7);
        int[] array = { 1, 2, 3, 4 };
        foo(x, str, num, array);
        out.println("x = " + x);
        out.println("str = " + str);
        out.println("num = " + num);
        out.println("array = " + array);

        NaturalNumber n2 = new NaturalNumber2(123456789);
        int look = 3;
        boolean contain = containsDigit(n2, look);

        NaturalNumber example = new NaturalNumber2(11);
        NaturalNumber[] array2 = new NaturalNumber[2];
        array2[0] = new NaturalNumber2(4);
        array2[1] = new NaturalNumber2(9);

        NaturalNumber num2 = new NaturalNumber2(7);
        foo2(array2, num2);
        out.println();
        out.println(array[0] + ", " + array[1] + ", " + num);

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

    static void foo(int i, String s, NaturalNumber n, int[] a) {
        i += 3;
        s = "goodbye";
        n.increment();
        n = new NaturalNumber2(14);
        a[0] = a.length;
    }

    private static void foo2(NaturalNumber[] a, NaturalNumber n) {
        n.increment();
        System.out.println("n (after incremnent: " + n);
        n = a[0];
        System.out.println("n = a[0]: " + n);
        a[0] = a[1];
        System.out.println("a[0]: " + a[0] + " a[1]: " + a[1]);
        a[1] = n;
        System.out.println("a[1]: " + a[1]);
        n.decrement();
        System.out.println("n (after decreement): " + n);
    }

    private static boolean containsDigit(NaturalNumber n, int d) {
        boolean toRet = false;
        int hold = n.divideBy10();
        System.out.println("hold: " + hold);
        if (hold == d) {
            toRet = true;
            System.out.println("found");
        } else {
            toRet = containsDigit(n, d);
        }
        return toRet;
    }

}
