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
public final class ProgramWithIOAndStaticMethod {

    public static boolean containsDigit(String s) {
        boolean toRet = false;
        String one = "0123456789";
        String temp = "";
        int length = s.length();
        for (int i = 0; i < length; i++) {
            temp += s.charAt(i);
            if (one.contains(temp)) {
                toRet = true;
            } else {
                temp = "";
            }
        }
        return toRet;

    }

    private static int digitSum(int n) {
        int sum = 0;
        int hold = n;
        while (hold > 0) {
            int temp = hold % 10;
            int remainder = hold - temp;
            remainder = remainder / 10;
            sum += temp;
            hold = remainder;
        }
        return sum;
    }

    /**
     * Put a short phrase describing the static method myMethod here.
     */

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
        /*
         * Close input and output streams
         */

        String temp = "R2D2";
        int hold = 12345;
        int count = digitSum(hold);
        System.out.println("count: " + count);
        boolean end = containsDigit(temp);
        if (end == true) {
            System.out.println("Contains Digit: " + temp);
        }
        in.close();
        out.close();
    }

}
