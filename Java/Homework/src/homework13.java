import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree2;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class homework13 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private homework13() {
    }

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
        NaturalNumber hold = new NaturalNumber2();

        while (n.compareTo(new NaturalNumber2(0)) > 0) {
            int digit = n.divideBy10();
            hold.add(new NaturalNumber2(digit));
        }
        n.clear();
        return hold;
    }

    /**
     * Returns the product of the digits of {@code n}.
     *
     * @param n
     *            {@code NaturalNumber} whose digits to multiply
     * @return the product of the digits of {@code n}
     * @ensures productOfDigits2 = [product of the digits of n]
     */
    private static NaturalNumber productOfDigits2(NaturalNumber n) {
        NaturalNumber hold = new NaturalNumber2();
        NaturalNumber temp = new NaturalNumber2(n);

        while (n.compareTo(new NaturalNumber2(0)) > 0) {
            int digit = n.divideBy10();
            hold.add(new NaturalNumber2(digit));
        }
        n.copyFrom(temp);
        return hold;
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
        int end = 0;
        if (n.compareTo(new NaturalNumber2(2147483647)) < 0) {
            end = n.toInt();
        }
        return end;
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
        boolean ret = false;
        int i = 0;
        int child = xml.numberOfChildren();
        if (xml.label().equals(tag)) {
            ret = true;
        }
        while (child > i) {
            ret = findTag(xml.child(i), tag);
            i++;
        }

        return ret;
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

        NaturalNumber first = new NaturalNumber2(12345);
        NaturalNumber sum = productOfDigits1(first);
        out.println("Sum of 12345: " + sum);

        NaturalNumber second = new NaturalNumber2(2222);
        NaturalNumber sum2 = productOfDigits2(second);
        out.println("Sum of 2222: " + sum2);

        NaturalNumber ints = new NaturalNumber2(12345);
        int change = toInt(ints);
        out.println("To int: " + change);

        out.println("Please enter name of XML file: ");
        String address = in.nextLine();
        XMLTree xml = new XMLTree2(address);
        out.println("Enter the name of a tag to locate: ");
        String tag = in.nextLine();
        boolean hasTag = findTag(xml, tag);
        if (hasTag) {
            out.println("Found the tag.");
        }

        NaturalNumber[] array = new NaturalNumber[5];
        NaturalNumber count = new NaturalNumber2(1);
        for (int i = 0; i < array.length; i++) {
            array[i] = count;
            count.increment();
        }
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
