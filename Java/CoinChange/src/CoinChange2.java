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
public final class CoinChange2 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private CoinChange2() {
    }

    /**
     * WHOLE DOLLAR.
     */
    public static final int DOLLAR = 100;
    /***
     * HALF DOLLAR.
     */
    public static final int HALF = 50;
    /**
     * QUARTER DOLLAR.
     */
    public static final int QUARTER = 25;
    /**
     * DIME.
     */
    public static final int DIME = 10;
    /**
     * NICKEL.
     */
    public static final int NICKEL = 5;
    /**
     * PENNY.
     */
    public static final int PENNY = 1;

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
        int wholes = 0;
        int halfs = 0;
        int quarters = 0;
        int dimes = 0;
        int nickels = 0;
        int pennys = 0;

        out.println("Enter an amount in cents: ");
        double orig = in.nextDouble();
        int current = (int) orig;
        if (current == 0) {
            out.println("You entered 0 cents. That makes zero sense.");
        }
        if (current != 0) {

            wholes = current / DOLLAR;
            current -= (DOLLAR * wholes);
            halfs = current / HALF;
            current -= (HALF * halfs);
            quarters = current / QUARTER;
            current -= (QUARTER * quarters);
            dimes = current / DIME;
            current -= (DIME * dimes);
            nickels = current / NICKEL;
            current -= (NICKEL * nickels);
            pennys = current / PENNY;
            current -= (PENNY * pennys);

            out.println();
            out.println("Whole Dollars: " + wholes);
            out.println("Half Dollars: " + halfs);
            out.println("Quarters: " + quarters);
            out.println("Dimes: " + dimes);
            out.println("Nickels: " + nickels);
            out.println("Pennies: " + pennys);

        }
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
