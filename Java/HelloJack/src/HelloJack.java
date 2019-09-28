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
public final class HelloJack {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private HelloJack() {
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

        out.println("Hello! What is your name?");
        String name = in.nextLine();
        if (name.isEmpty()) {
            out.println("You did not enter your name. Please try Again.");
            name = in.nextLine();
        }
        if (!name.isEmpty()) {
            out.println("Hello " + name + "!");
        }

        /*
         * Put your main program code here
         */
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
