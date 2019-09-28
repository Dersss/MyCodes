/**
 * Simple HelloWorld program (clear of Checkstyle and FindBugs warnings).
 *
 * @author P. Bucci
 */
public final class HelloWorld {

    /**
     * Default constructor--private to prevent instantiation.
     */
    private HelloWorld() {
        // no code needed here
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {

        String str1 = "THE GETTYSBURG ADDRESS:~~~Four";
        String str2 = "E GETTYSBURG ADDRESS:~~~Four score and se";
        int overlap = 28;

        String sub1 = str1.substring(0, str1.length() - overlap);
        String sub2 = str2.substring(0, str2.length());
        String toRet = sub1 + sub2;
        System.out.println(toRet);

    }

}
