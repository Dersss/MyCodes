import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Debugging scenario.
 *
 * @author Andrew Schneider
 *
 */
public final class Oddity {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Oddity() {
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();
        final int microsPerDay = 24 * 60 * 60 * 1000 * 1000;
        final int millisPerDay = 24 * 60 * 60 * 1000;
        out.println(microsPerDay / millisPerDay);
        out.close();
    }

}
