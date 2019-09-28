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
public final class NaturalNumberRoot {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private NaturalNumberRoot() {
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

        /**
         * Creates initial high and low bounds of interval in which to find the
         * root.
         */
        NaturalNumber low = new NaturalNumber2();
        NaturalNumber high = new NaturalNumber2(n);
        high.increment();

        /**
         * Creates a temporary variable for n in which to manipulate to find the
         * root.
         */
        NaturalNumber temp = new NaturalNumber2(n);
        temp.increment();

        /**
         * Subtracts the low bound from the current temporary variable being
         * tested and checks whether there is atleast one number in between them
         * so that the interval is not size 2.
         */
        temp.subtract(low);
        NaturalNumber one = new NaturalNumber2(1);
        int compare = temp.compareTo(one);

        /**
         * Loop while the temporary variable is greater than 1
         */
        while (compare > 0) {

            /**
             * Creates a new Natural Number variable to hold the guess for the
             * root of n starting at the top bound. It then adds the low bound
             * and divides by two to find the midpoint of that interval.
             */
            NaturalNumber guess = new NaturalNumber2(high);
            guess.add(low);
            NaturalNumber two = new NaturalNumber2(2);
            guess.divide(two);

            /**
             * Checks whether the current NN guess is the correct root of NN n.
             * Uses NN power() method
             */
            NaturalNumber power = new NaturalNumber2(guess);
            power.power(r);

            /**
             * Compares the NN power variable to the original value of n. If
             * power is greater than n, the new top bound is guess. If it is
             * lower, guess is the new low bound.
             */
            int compare2 = power.compareTo(n);
            if (compare2 > 0) {
                high.copyFrom(guess);
            } else {
                low.copyFrom(guess);
            }

            /**
             * Copies the value of High to temp and subtract the value of the
             * low bound to test for interval size.
             */
            temp.copyFrom(high);
            temp.subtract(low);
            /**
             * If temp is equal to one, we have changed the interval only to
             * include the correct root of n so the value of the low bound gets
             * transferred to n.
             */
            compare = temp.compareTo(one);
            if (compare == 0) {
                n.transferFrom(low);
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
