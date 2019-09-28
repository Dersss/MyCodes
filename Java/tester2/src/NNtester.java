import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class NNtester {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private NNtester() {
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */

    /**
     * Returns the product of the digits of {@code n}.
     *
     * @param n
     *            {@code NaturalNumber} whose digits to multiply
     * @return the product of the digits of {@code n}
     * @clears n
     * @ensures productOfDigits1 = [product of the digits of n]
     */
    private static NaturalNumber productOfDigits1(NaturalNumber n) {
        SimpleWriter out = new SimpleWriter1L();

        NaturalNumber first = new NaturalNumber2(n);
        NaturalNumber total = new NaturalNumber2();

        boolean zero = n.isZero();

        while (!zero) {
            boolean zero2 = first.isZero();
            if (zero2) {
                out.println(total);
                break;
            }
            NaturalNumber div10 = new NaturalNumber2(first);
            div10.divideBy10();
            NaturalNumber mult10 = new NaturalNumber2(div10);
            mult10.multiplyBy10(0);
            NaturalNumber difference = new NaturalNumber2(first);
            difference.subtract(mult10);
            total.add(difference);
            first.copyFrom(div10);

        }

        n.clear();
        return first;

    }

    /**
     * Reports the value of {@code n} as an {@code int}, when {@code n} is small
     * enough.
     *
     * @param n
     *            the given {@code NaturalNumber}
     * @return the value
     * @requires n <= Integer.MAX_VALUE
     * @ensures toInt = n
     */
    private static int toInt(NaturalNumber n) {
        SimpleWriter out = new SimpleWriter1L();
        NaturalNumber temp = new NaturalNumber2(n);
        int a = n.toInt();
        return a;
    }

    /**
     * Reports whether the given tag appears in the given {@code XMLTree}.
     *
     * @param xml
     *            the {@code XMLTree}
     * @param tag
     *            the tag name
     * @return true if the given tag appears in the given {@code XMLTree}, false
     *         otherwise
     * @ensures <pre>
     * findTag =
     *    [true if the given tag appears in the given {@code XMLTree}, false otherwise]
     * </pre>
     */
    private static boolean findTag(XMLTree xml, String tag) {
        boolean toRet = false;
        int numChild = xml.numberOfChildren();
        SimpleWriter out = new SimpleWriter1L();
        for (int i = 0; i < numChild; i++) {
            if (xml.child(i).label() == tag) {
                toRet = true;
                break;
            } else {
                if (xml.child(i).numberOfChildren() > 0) {
                    boolean check1 = findTag(xml.child(i), tag);
                    if (check1 == true) {
                        toRet = true;
                        break;
                    }
                }
            }
        }

        if (toRet == true) {
            out.println("Found");
        }
        if (toRet == false) {
            out.println("Not found");
        }
        return toRet;
    }

    public static void main(String[] args) {
        /*
         * Put your main program code here
         */
        SimpleWriter out = new SimpleWriter1L();
        out.println("Please enter a number: ");

        SimpleReader in = new SimpleReader1L();
        int one = in.nextInteger();

        NaturalNumber temp = new NaturalNumber2(one);
        int num3 = toInt(temp);
        out.println("To Integer: " + num3);
        productOfDigits1(temp);

        XMLTree exp = new XMLTree1("xml7.xml");
        boolean hasIt = findTag(exp, "divide");

        NaturalNumber[] array = new NaturalNumber[5];
        NaturalNumber count = new NaturalNumber2();
        for (int i = 0; i < array.length; i++) {
            //            array[i] = count;
            //            count.increment();
            out.print("[" + array[i] + "]");
        }

    }

}
