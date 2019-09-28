import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Simple program to exercise EmailAccount functionality.
 */
public final class EmailAccountMain {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private EmailAccountMain() {
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
        EmailAccount myAccount = new EmailAccount1("Brutus", "Buckeye");

        //        EmailAccount myAccount2 = new EmailAccount1("Andrew", "Buckeye");
        //        out.println(myAccount2.emailAddress());

        boolean end = false;

        while (!end) {
            EmailAccount myAccount2;
            out.println("Please enter a name in form (first last): ");
            String name = in.nextLine();
            if (name.length() == 0) {
                end = true;
            } else {
                if (name.contains(" ")) {
                    int space = name.indexOf(' ');
                    String first = name.substring(0, space);
                    String last = name.substring(space + 1);
                    myAccount2 = new EmailAccount1(first, last);
                    /*
                     * Should print: Brutus Buckeye
                     */
                    out.println();
                    out.println(myAccount2.name());
                    /*
                     * Should print: buckeye.1@osu.edu
                     */
                    out.println(myAccount2.emailAddress());
                }
            }
        }
        /*
         * Should print: Brutus Buckeye
         */
        out.println(myAccount.name());
        /*
         * Should print: buckeye.1@osu.edu
         */
        out.println(myAccount.emailAddress());

        /*
         * Should print: Name: Brutus Buckeye, Email: buckeye.1@osu.edu
         */
        out.println(myAccount);
        in.close();
        out.close();
    }

}
