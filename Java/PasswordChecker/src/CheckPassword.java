import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Andrew Schneider
 *
 */
public final class CheckPassword {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private CheckPassword() {
    }

    /**
     * Int for min password length.
     */
    public static final int SIX = 6;

    /**
     * Int for number 3.
     */
    public static final int THREE = 3;

    /**
     * String for special characters.
     */
    public static final String SPECIAL = "!@#$%^&*()_-+={}[]:;,.?";

    /**
     * Checks if the given String contains an upper case letter.
     *
     * @param s
     *            the String to check
     * @return true if s contains an upper case letter, false otherwise
     */
    private static boolean containsUpperCaseLetter(String s) {
        boolean toRet = false;
        int i = 0;
        while (i < s.length() && !toRet) {
            if (Character.isUpperCase(s.charAt(i))) {
                toRet = true;
            }
            i++;
        }
        return toRet;
    }

    /**
     * Checks if the given String contains a lower case letter.
     *
     * @param s
     *            the String to check
     * @return true if s contains an lower case letter, false otherwise
     */
    private static boolean containsLowerCaseLetter(String s) {
        boolean toRet = false;
        int i = 0;
        while (i < s.length() && !toRet) {
            if (Character.isLowerCase(s.charAt(i))) {
                toRet = true;
            }
            i++;
        }
        return toRet;
    }

    /**
     * Checks if the given String contains a number.
     *
     * @param s
     *            the String to check
     * @return true if s contains a number, false otherwise
     */
    private static boolean containsDigits(String s) {
        boolean toRet = false;
        int i = 0;
        while (i < s.length() && !toRet) {
            if (Character.isDigit(s.charAt(i))) {
                toRet = true;
            }
            i++;
        }
        return toRet;
    }

    /**
     * Checks if the given String contains an special character.
     *
     * @param s
     *            the String to check
     * @return true if s contains an special character, false otherwise
     */
    private static boolean containsSpecial(String s) {
        boolean toRet = false;
        int i = 0;
        while (i < s.length() && !toRet) {
            String hold = s.substring(i, i + 1);
            if (SPECIAL.contains(hold)) {
                toRet = true;
            }
            i++;
        }
        return toRet;
    }

    /**
     * Checks whether the given String satisfies the CSE department criteria for
     * a valid password. Prints an appropriate message to the given output
     * stream.
     *
     * @param s
     *            the String to check
     * @param out
     *            the output stream
     */
    private static void checkPassword(String s, SimpleWriter out) {
        int threeOfFour = 0;
        boolean uppercase = false;
        boolean lowercase = false;
        boolean digits = false;
        boolean special = false;
        if (s.length() < SIX) {
            out.println("Invalid password length.");
        } else {
            uppercase = containsUpperCaseLetter(s);
            lowercase = containsLowerCaseLetter(s);
            digits = containsDigits(s);
            special = containsSpecial(s);
        }

        if (uppercase) {
            threeOfFour++;
        }
        if (lowercase) {
            threeOfFour++;
        }
        if (digits) {
            threeOfFour++;
        }
        if (special) {
            threeOfFour++;
        }

        if (threeOfFour >= THREE) {
            out.println("Valid password.");
        } else {
            out.println("Invalid password.");
        }

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

        out.println("Please enter password: ");
        String password = in.nextLine();
        checkPassword(password, out);

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
