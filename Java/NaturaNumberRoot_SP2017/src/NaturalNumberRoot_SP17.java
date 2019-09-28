import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Program with implementation of {@code NaturalNumber} secondary operation
 * {@code root} implemented as static method.
 *
 * @author Andrew Schneider
 *
 */
public final class NaturalNumberRoot_SP17 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private NaturalNumberRoot_SP17() {
    }

    /**
     * Updates {@code n} to the {@code r}-th root of its incoming value.
     *
     * @param n
     *            the number whose root to compute
     * @param r
     *            root
     * @updates n
     * @requires r >= 2
     * @ensures n ^ (r) <= #n < (n + 1) ^ (r)
     */
    public static void root(NaturalNumber n, int r) {
        assert n != null : "Violation of: n is  not null";
        assert r >= 2 : "Violation of: r >= 2";

        // TODO - fill in body

        /**
         * Natural number to hold top bound value equals n + 1
         */
        NaturalNumber top = new NaturalNumber2(n);
        top.increment();

        /**
         * Natural number to hold low bound, a temp value of n, and number one
         * temp equals n minus low
         */
        NaturalNumber low = new NaturalNumber2();
        NaturalNumber temp = new NaturalNumber2(n);
        NaturalNumber one = new NaturalNumber2();
        one.increment();
        temp.subtract(low);

        /**
         * compares temp to one value equaks n + 1 if greater than 0 executes
         * loop
         */
        int compare = temp.compareTo(one);
        while (compare > 0) {

            /**
             * manipulates NaturalNumber guess to correspond to the new bounds
             * determined by if the value is greater than n
             */
            NaturalNumber guess = new NaturalNumber2(top);
            guess.add(low);
            NaturalNumber two = new NaturalNumber2(one);
            two.increment();
            guess.divide(two);

            /**
             * NaturalNumber power equals guess to the power of r if the value
             * if power is greater than n the top bound is updated else the low
             * bound is updated
             */
            NaturalNumber power = new NaturalNumber2(guess);
            power.power(r);
            int compare2 = power.compareTo(n);
            if (compare2 == 1) {
                top.copyFrom(guess);
            } else {
                low.copyFrom(guess);
            }

            /**
             * Copies temp from top then subtracts low compares to 1 and if zero
             * root is found and update to n takes place
             */
            temp.copyFrom(top);
            temp.subtract(low);
            compare = temp.compareTo(one);
            if (compare == 0) {
                n.copyFrom(low);
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
        SimpleWriter out = new SimpleWriter1L();

        final String[] numbers = { "0", "1", "13", "1024", "189943527", "0",
                "1", "13", "4096", "189943527", "0", "1", "13", "1024",
                "189943527", "82", "82", "82", "82", "82", "9", "27", "81",
                "243", "143489073", "2147483647", "2147483648",
                "9223372036854775807", "9223372036854775808",
                "618970019642690137449562111",
                "162259276829213363391578010288127",
                "170141183460469231731687303715884105727" };
        final int[] roots = { 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 15, 15, 15, 15, 15,
                2, 3, 4, 5, 15, 2, 3, 4, 5, 15, 2, 2, 3, 3, 4, 5, 6 };
        final String[] results = { "0", "1", "3", "32", "13782", "0", "1", "2",
                "16", "574", "0", "1", "1", "1", "3", "9", "4", "3", "2", "1",
                "3", "3", "3", "3", "3", "46340", "46340", "2097151", "2097152",
                "4987896", "2767208", "2353973" };

        for (int i = 0; i < numbers.length; i++) {
            NaturalNumber n = new NaturalNumber2(numbers[i]);
            NaturalNumber r = new NaturalNumber2(results[i]);
            root(n, roots[i]);
            if (n.equals(r)) {
                out.println("Test " + (i + 1) + " passed: root(" + numbers[i]
                        + ", " + roots[i] + ") = " + results[i]);
            } else {
                out.println("*** Test " + (i + 1) + " failed: root("
                        + numbers[i] + ", " + roots[i] + ") expected <"
                        + results[i] + "> but was <" + n + ">");
            }
        }

        out.close();
    }

}
