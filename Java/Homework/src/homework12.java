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
public final class homework12 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private homework12() {
    }

    /**
     * Returns the number of digits of {@code n}.
     *
     * @param n
     *            {@code NaturalNumber} whose digits to count
     * @return the number of digits of {@code n}
     * @ensures numberOfDigits = [number of digits of n]
     */
    private static int numberOfDigits(NaturalNumber n) {
        int count = 0;
        if (n.compareTo(new NaturalNumber2(0)) > 0) {
            int remainder = n.divideBy10();
            count = 1 + numberOfDigits(n);
        }
        return count;
    }

    /**
     * Returns the sum of the digits of {@code n}.
     *
     * @param n
     *            {@code NaturalNumber} whose digits to add
     * @return the sum of the digits of {@code n}
     * @ensures sumOfDigits = [sum of the digits of n]
     */
    private static int sumOfDigits(NaturalNumber n) {
        int sum = 0;
        while (n.compareTo(new NaturalNumber2(0)) > 0) {
            int hold = n.divideBy10();
            sum = hold + sumOfDigits(n);
        }
        return sum;
    }

    /**
     * Returns the sum of the digits of {@code n}.
     *
     * @param n
     *            {@code NaturalNumber} whose digits to add
     * @return the sum of the digits of {@code n}
     * @ensures sumOfDigits = [sum of the digits of n]
     */
    private static NaturalNumber sumOfDigits2(NaturalNumber n) {

        NaturalNumber sum = new NaturalNumber2();
        while (n.compareTo(new NaturalNumber2(0)) > 0) {
            int hold = n.divideBy10();
            NaturalNumber temp = new NaturalNumber2(hold);
            sum.add(temp);
            NaturalNumber copy = sumOfDigits2(n);
            sum.add(copy);
        }

        return sum;
    }

    /**
     * Divides {@code n} by 2.
     *
     * @param n
     *            {@code NaturalNumber} to be divided
     * @updates n
     * @ensures 2 * n <= #n < 2 * (n + 1)
     */
    private static void divideBy2(NaturalNumber n) {
        NaturalNumber copy = new NaturalNumber2(n);

        System.out.println("n before = " + n);
        if (n.compareTo(new NaturalNumber2(0)) > 0) {
            int digit = n.divideBy10();
            System.out.println("digit = " + digit);
            digit = digit / 2;
            System.out.println("digit / 2 = " + digit);
            System.out.println("n = " + n);
            if (n.compareTo(new NaturalNumber2(0)) > 0) {
                divideBy2(n);
                n.multiplyBy10(digit);
            }
            if (n.compareTo(new NaturalNumber2(0)) == 0) {
                n.multiplyBy10(digit);
                ;
            }

        }
    }

    /**
     * Checks whether a {@code String} is a palindrome.
     *
     * @param s
     *            {@code String} to be checked
     * @return true if {@code s} is a palindrome, false otherwise
     * @ensures isPalindrome = (s = rev(s))
     */
    private static boolean isPalindrome(String s) {
        boolean toRet = false;
        String opp = "";
        if (s.length() != 0) {
            if (s.length() == 1) {
                toRet = true;
            } else if (s.length() == 2) {
                int bottom = 0;
                int top = s.length() - 1;
                char front = s.charAt(bottom);
                char back = s.charAt(top);
                if (front == back) {
                    toRet = true;
                }
            } else {
                int bottom = 0;
                int top = s.length() - 1;
                char front = s.charAt(bottom);
                char back = s.charAt(top);
                if (front == back) {
                    String next = s.substring(bottom + 1, top);
                    boolean mid = isPalindrome(next);
                    if (mid) {
                        toRet = true;
                    }
                }
            }
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

        NaturalNumber numDig = new NaturalNumber2(1234567);
        int num = numberOfDigits(numDig);
        System.out.println("number of digits: " + num);

        NaturalNumber sumDig = new NaturalNumber2(505);
        int sum = sumOfDigits(sumDig);
        System.out.println("sum of digits: " + sum);

        NaturalNumber sumDig2 = new NaturalNumber2(505);
        NaturalNumber sum2 = sumOfDigits2(sumDig2);
        System.out.println("NN sum: " + sum2);

        NaturalNumber n1 = new NaturalNumber2(44);
        NaturalNumber n2 = new NaturalNumber2(51);
        divideBy2(n1);
        divideBy2(n2);
        System.out.println(n1);
        System.out.println(n2);

        String s1 = "racecar";
        boolean palindrome = isPalindrome(s1);
        if (palindrome) {
            System.out.println("Palindrome!");
        } else {
            System.out.println("Not a palindrome!");
        }

        String s2 = "andrew";
        boolean palindrome2 = isPalindrome(s2);
        if (palindrome2) {
            System.out.println("Palindrome!");
        } else {
            System.out.println("Not a palindrome!");
        }
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
