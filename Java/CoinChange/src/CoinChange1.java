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
public final class CoinChange1 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private CoinChange1() {
    }

    /**
     * WHOLE DOLLAR.
     */
    public static final double DOLLAR = 100;
    /***
     * HALF DOLLAR.
     */
    public static final double HALF = 50;
    /**
     * QUARTER DOLLAR.
     */
    public static final double QUARTER = 25;
    /**
     * DIME.
     */
    public static final double DIME = 10;
    /**
     * NICKEL.
     */
    public static final double NICKEL = 5;
    /**
     * PENNY.
     */
    public static final double PENNY = 1;

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
            out.println();
            out.println("Whole Dollars: " + wholes);
            out.println("Half Dollars: " + halfs);
            out.println("Quarters: " + quarters);
            out.println("Dimes: " + dimes);
            out.println("Nickels: " + nickels);
            out.println("Pennies: " + pennys);
        } else {

            if (current >= 100) {
                int dolls = current / 100;
                wholes += (dolls * 1);
                current -= (dolls * 100);

            }
            if (current < DOLLAR && current >= HALF) {
                halfs++;
                current -= HALF;
            }
            if (current < HALF && current >= QUARTER) {
                quarters++;
                current -= QUARTER;

            }
            if (current < QUARTER && current >= DIME) {
                current -= DIME;
                dimes++;

            }
            if (current < DIME && current >= NICKEL) {
                current -= NICKEL;
                nickels++;
            }
            if (current < NICKEL && current >= PENNY) {
                int pennies = current / 1;
                pennys += (pennies * PENNY);
                current -= (pennies * PENNY);
            }
            if (current == 0) {
                out.println();
                out.println("Whole Dollars: " + wholes);
                out.println("Half Dollars: " + halfs);
                out.println("Quarters: " + quarters);
                out.println("Dimes: " + dimes);
                out.println("Nickels: " + nickels);
                out.println("Pennies: " + pennys);
            }
        }
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
