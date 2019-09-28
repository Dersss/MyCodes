import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.xmltree.XMLTree;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class NNtester2 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private NNtester2() {
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

    public static void reduceToGCD(NaturalNumber n, NaturalNumber m) {

        /*
         * Use Euclid's algorithm; in pseudocode: if m = 0 then GCD(n, m) = n
         * else GCD(n, m) = GCD(m, n mod m)
         */

        // TODO - fill in body

        NaturalNumber tempN = new NaturalNumber2(n);
        NaturalNumber tempM = new NaturalNumber2(m);
        NaturalNumber zero = new NaturalNumber2(0);

        if (tempM.equals(zero)) {
            n.copyFrom(tempN);
        } else {
            int intN = n.toInt();
            int intM = m.toInt();
            int mod = intN % intM;
            NaturalNumber temp3 = new NaturalNumber2(mod);
            reduceToGCD(tempN, temp3);
        }
    }

    public static int gcd(int p, int q) {
        if (q == 0) {
            return p;
        } else {
            return gcd(q, p % q);
        }
    }

    public static void main(String[] args) {
        /*
         * Put your main program code here
         */
        //        SimpleWriter out = new SimpleWriter1L();
        //        out.println("Please enter a number: ");
        //
        //        SimpleReader in = new SimpleReader1L();
        //        int one = in.nextInteger();
        //
        //        NaturalNumber temp = new NaturalNumber2(one);
        //        int num3 = toInt(temp);
        //        out.println("To Integer: " + num3);
        //        productOfDigits1(temp);
        //
        //        XMLTree exp = new XMLTree1("xml7.xml");
        //        boolean hasIt = findTag(exp, "divide");
        //
        //        NaturalNumber[] array = new NaturalNumber[5];
        //        NaturalNumber count = new NaturalNumber2();
        //        for (int i = 0; i < array.length; i++) {
        //            //            array[i] = count;
        //            //            count.increment();
        //            out.print("[" + array[i] + "]");
        //        }

        //        NaturalNumber n = new NaturalNumber2(4);
        //        NaturalNumber m = new NaturalNumber2(5);
        //        int iN = 4;
        //        int iM = 5;
        //
        //        System.out.println(gcd(iN, iM));
        //        reduceToGCD(n, m);
        //        System.out.println(n);

        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        out.println("Enter a value for n: ");
        int n = in.nextInteger();
        out.println("Enter a value for p: ");
        int p = in.nextInteger();
        int total = 1;

        for (int i = 0; i < p; i++) {
            total *= n;
        }
        out.println("Enter a value for m: ");
        int m = in.nextInteger();

        int mod = total % m;

        out.println("n % m = " + mod);
        System.out.println(16 % 3);

        NaturalNumber n1 = new NaturalNumber2(17);
        NaturalNumber p1 = new NaturalNumber2(18);
        NaturalNumber tempDiv = new NaturalNumber2(p1);
        NaturalNumber m1 = new NaturalNumber2(19);

        tempDiv.divideBy10();
        tempDiv.multiplyBy10(0);

        int seven = 17;
        int total2 = 1;
        for (int i = 0; i < 18; i++) {
            total *= seven;
        }
        System.out.println(total2 % 19);
    }

}
