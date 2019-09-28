import components.map.Map;
import components.map.Map1L;

/**
 * Implementation of {@code EmailAccount}.
 *
 * @author Put your name here
 *
 */
public final class EmailAccount1 implements EmailAccount {

    /*
     * Private members --------------------------------------------------------
     */

    // TODO - declare static and instance data members
    /**
     * ja
     *
     */
    String firstName;

    /**
     * fd
     *
     */
    String lastName;
    /**
     *
     */
    String emailAddress;
    /**
     * hg
     */
    static Map<String, Integer> map = new Map1L<>();

    /*
     * Constructor ------------------------------------------------------------
     */

    /**
     * Constructor.
     *
     * @param firstName
     *            the first name
     * @param lastName
     *            the last name
     */
    public EmailAccount1(String firstName, String lastName) {

        // TODO - fill in body
        this.firstName = firstName;
        this.lastName = lastName;
        if (map.hasKey(lastName.toLowerCase())) {
            int n = map.value(lastName.toLowerCase());
            n++;
            this.emailAddress = lastName.toLowerCase() + "." + n + "@osu.edu";
            map.replaceValue(lastName.toLowerCase(), n);
        } else {
            map.add(lastName.toLowerCase(), 1);
            this.emailAddress = lastName.toLowerCase() + "." + 1 + "@osu.edu";
        }
    }

    /*
     * Methods ----------------------------------------------------------------
     */

    @Override
    public String name() {

        // TODO - fill in body

        // Added to make skeleton compilable
        return this.firstName + " " + this.lastName;
    }

    @Override
    public String emailAddress() {

        // TODO - fill in body

        // Added to make skeleton compilable
        return this.emailAddress;
    }

    @Override
    public String toString() {

        // TODO - fill in body
        String str = "Name: " + this.firstName + " " + this.lastName
                + "          Email: " + this.emailAddress;

        // Added to make skeleton compilable
        return str;
    }

}
