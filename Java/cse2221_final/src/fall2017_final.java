import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Simple HelloWorld program (clear of Checkstyle and FindBugs warnings).
 *
 * @author P. Bucci
 */
public final class fall2017_final {

    /**
     * Default constructor--private to prevent instantiation.
     */
    private fall2017_final() {
        // no code needed here
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();
        SimpleReader in = new SimpleReader1L();
        int i = in.nextInteger();
        int pal = 1234;
        int dig = pal % 10;
        out.println(pal);

        out.close();
    }

}
