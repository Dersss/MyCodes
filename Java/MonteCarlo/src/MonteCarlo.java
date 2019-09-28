import components.random.Random;
import components.random.Random1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Monte Carlo Estimate: compute percentage of pseudo-random points in [0.0,1.0)
 * interval that fall in the left half subinterval [0.0,0.5).
 */
public final class MonteCarlo {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private MonteCarlo() {
    }

    /**
     *  * Checks whether the given point (xCoord, yCoord) is inside the circle
     * of  * radius 1.0 centered at the point (1.0, 1.0).  *  * @param xCoord
     *  *            the x coordinate of the point  * @param yCoord
     *  *            the y coordinate of the point  * @return true if the point
     * is inside the circle, false otherwise  
     */
    private static boolean pointIsInCircle(double xCoord, double yCoord) {
        boolean inside = false;
        if (Math.pow(xCoord, 2) + Math.pow(yCoord, 2) <= 1) {
            inside = true;
        }
        return inside;
    }

    /**
     *  * Generates n pseudo-random points in the [0.0,2.0) x [0.0,2.0) square
     * and  * returns the number that fall in the circle of radius 1.0 centered
     * at  * the point (1.0, 1.0).  *  * @param n  *            the number of
     * points to generate  * @return the number of points that fall in the
     * circle  
     */
    private static int numberOfPointsInCircle(int n) {
        int inCirc = 0;
        int inInterv = 0;
        Random rand = new Random1L();
        while (inInterv < n) {
            double x = rand.nextDouble() * 2;
            double y = rand.nextDouble() * 2;
            boolean inside = pointIsInCircle(x, y);
            if (inside) {
                inCirc++;
            }
            inInterv++;
        }
        return inCirc;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        /*
         * Open input and output streams
         */
        SimpleReader input = new SimpleReader1L();
        SimpleWriter output = new SimpleWriter1L();
        /*
         * Ask user for number of points to generate
         */
        output.print("Number of points: ");
        int n = input.nextInteger();
        /*
         * Declare counters and initialize them
         */
        int ptsInInterval = n, ptsInSubinterval = 0;
        /*
         * Create pseudo-random number generator
         */
        Random rnd = new Random1L();
        /*
         * Generate points and count how many fall in [0.0,0.5) interval
         */
        /*
         * Generate pseudo-random number in [0.0,1.0) interval
         */
        ptsInSubinterval = numberOfPointsInCircle(n);

        /*
         * Estimate percentage of points generated in [0.0,1.0) interval that
         * fall in the [0.0,0.5) subinterval
         */
        double estimate = (100.0 * ptsInSubinterval) / ptsInInterval;
        output.println("Estimate of percentage: " + estimate + "%");
        /*
         * Close input and output streams
         */
        input.close();
        output.close();
    }

}
