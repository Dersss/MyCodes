import java.io.Serializable;
import java.util.Comparator;

import components.map.Map;
import components.map.Map1L;
import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.sortingmachine.SortingMachine;
import components.sortingmachine.SortingMachine1L;

/**
 * A program that takes a user input file, counts the occurences of each word,
 * and created an HTML file with a TAG WORD CLOUD.
 *
 * @author Andrew Schneider
 * @author Eric Sullivan
 * @author hitesh
 *
 */
public final class CloudGeneratorMain {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private CloudGeneratorMain() {
    }

    /**
     * Useful constants.
     */
    public static final int FOUR = 4;
    /**
     * Useful constants.
     */
    public static final int ELEVEN = 11;
    /**
     * Useful constants.
     */
    public static final int FORTY_EIGHT = 48;
    /**
     * Useful constants.
     */
    public static final int FIVE = 5;

    /**
     * Compare {@code Map.Pair<String, Integer>}s in alphabetical order
     * according to key value.
     */
    private static class EntrySLT
            implements Serializable, Comparator<Map.Pair<String, Integer>> {
        @Override

        public int compare(Map.Pair<String, Integer> o1,
                Map.Pair<String, Integer> o2) {

            String s1 = o1.key().toLowerCase();
            String s2 = o2.key().toLowerCase();

            return s1.compareTo(s2);
        }
    }

    /**
     * Compare {@code Map.Pair<String, Integer>}s by decreasing order by value.
     */
    private static class EntryVLT
            implements Serializable, Comparator<Map.Pair<String, Integer>> {
        @Override
        public int compare(Map.Pair<String, Integer> o1,
                Map.Pair<String, Integer> o2) {

            Integer i1 = o1.value();
            Integer i2 = o2.value();
            return i2.compareTo(i1);
        }
    }

    /**
     * Processes the input from the user given file.
     *
     * @param in
     *            Reads in from .txt file
     * @param m1
     *            Map holding words and their counts
     *
     * @requires in is open
     * @ensures m1 is a map containing pairs where the word is the key and the
     *          number of appearances of the value in the file
     */
    public static void processNewFile(SimpleReader in,
            Map<String, Integer> m1) {

        final String separators = " \"'*,.:_?;!-()/\'][1234567890";
        Set<Character> separatorSet = new Set1L<>();
        generateElements(separators, separatorSet);

        while (!in.atEOS()) {
            String line = in.nextLine();
            int position = 0;
            while (position < line.length()) {
                String next = nextWordOrSeparator(line, position, separatorSet);
                if (separators.indexOf(next.charAt(0)) < 0) {
                    if (m1.hasKey(next)) {
                        Map.Pair<String, Integer> foundWord = m1.remove(next);
                        int value = foundWord.value();
                        value++;
                        m1.add(next, value);
                    } else {
                        m1.add(next, 1);
                    }
                }
                position += next.length();

            }

        }
    }

    /**
     * Separates strings of characters and strings of separators from each line
     * of input for processing.
     *
     * @param line
     *            The input line to be separated
     * @param pos
     *            The current position within the line
     * @param charac
     *            Set of separator characters to distinguish from words
     * @return token string containg either characters or separators
     *
     * @requires |line| /= 0 pos < |line|
     * @ensures token is substring of line token is either string of characters
     *          or separators
     */
    public static String nextWordOrSeparator(String line, int pos,
            Set<Character> charac) {
        boolean isSep = charac.contains(line.charAt(pos));
        boolean switcheroo = false;
        String token = "";
        int index = pos;
        while (index < line.length() && !switcheroo) {
            if (isSep) {
                if (charac.contains(line.charAt(index))) {
                    token += line.charAt(index);
                } else {
                    switcheroo = true;
                }
            } else {
                if (!charac.contains(line.charAt(index))) {
                    token += line.charAt(index);
                } else {
                    switcheroo = true;
                }
            }
            index++;
        }

        return token;
    }

    /**
     * Generates the set of characters in the given {@code String} into the
     * given {@code Set}.
     *
     * @param str
     *            the given {@code String}
     * @param strSet
     *            the {@code Set} to be replaced
     * @replaces strSet
     * @ensures strSet = entries(str)
     */
    public static void generateElements(String str, Set<Character> strSet) {
        assert str != null : "Violation of: str is not null";
        assert strSet != null : "Violation of: strSet is not null";

        /**
         * Creates String keep to hold characters without repeated values. Also
         * iterates over each character in @str and adds it to strSet if that
         * character is not already in @str
         */
        String keep = "";
        for (char c : str.toCharArray()) {
            if (keep.indexOf(c) == -1) {
                strSet.add(c);
                keep += c;
            }
        }

    }

    /**
     * Outputs the Tag Word Cloud to an HTML file with same name as input (.txt)
     * file.
     *
     *
     * @param out
     *            Prints to the specified file
     * @param sort
     *            The sorted map to be displayed
     * @param size
     *            user given size of Word Cloud
     * @param file
     *            name of file to output too
     * @param inFile
     *            name of input file
     *
     * @requires out is open |l1| /= <>
     *
     * @requires out is open |sort| /= <>
     * @ensures Tag word cloud is generated with top number of words given by
     *          user
     */
    public static void createHTML(SimpleWriter out,
            SortingMachine<Map.Pair<String, Integer>> sort, int size,
            String file, String inFile) {
        out.println("<html>");
        out.println("<head>");
        out.println("<title> Word Cloud of top " + size + " words in " + inFile
                + "</title>");
        out.println(
                "<link href=\"http://cse.osu.edu/software/2231/web-sw2/assignments/projects/tag-cloud-generator/data/tagcloud.css\" rel=\"stylesheet\" type=\"text/css\"");

        out.println("</head>");
        out.println("<body>");
        out.println("<h1> Word Cloud of top " + size + " words in " + inFile
                + "</h1>");
        out.println("<hr>");
        out.println("<div class=\"cdiv\">");
        out.println("<p class=\"cbox\">");
        int average = 0;
        for (Map.Pair<String, Integer> p : sort) {
            average += p.value();
        }

        average /= size;

        int index = 0;
        while (index < size) {
            //        for (Map.Pair<String, Integer> m : sort) {
            Map.Pair<String, Integer> m = sort.removeFirst();
            out.print("<span style=\"cursor:default\" class=\"f");

            double percentage = (double) (m.value() * FIVE) / average;

            percentage += ELEVEN;

            if (percentage > FORTY_EIGHT) {
                percentage = FORTY_EIGHT;
            } else if (percentage < ELEVEN) {
                percentage = ELEVEN;
            }

            out.print((int) percentage);

            out.print("\" title=\"count: ");
            out.println(m.value() + "\">" + m.key() + "</span>");
            index++;
        }
        out.println("</p>");
        out.println("</div>");

        out.println("</body>");

        out.println("</html>");
    }

    /**
     *
     * @param map1
     *            The map to be sorted and printed
     * @param size
     *            The given size of the word cloud
     * @param file
     *            The initial file name
     * @param inFile
     *            name of input file
     */
    public static void sortTwice(Map<String, Integer> map1, int size,
            String file, String inFile) {

        Comparator<Map.Pair<String, Integer>> ci = new EntryVLT();
        SortingMachine<Map.Pair<String, Integer>> si = new SortingMachine1L<>(
                ci);
        for (Map.Pair<String, Integer> mP : map1) {
            Map.Pair<String, Integer> holder = mP;
            si.add(holder);
        }
        si.changeToExtractionMode();
        Map<String, Integer> finalMap = new Map1L<>();

        int loadSize = 0;
        while (loadSize < size) {
            Map.Pair<String, Integer> temporary = si.removeFirst();
            finalMap.add(temporary.key(), temporary.value());
            loadSize++;
        }

        Comparator<Map.Pair<String, Integer>> ci2 = new EntrySLT();
        SortingMachine<Map.Pair<String, Integer>> si2 = new SortingMachine1L<>(
                ci2);

        for (Map.Pair<String, Integer> mP2 : finalMap) {
            Map.Pair<String, Integer> holder2 = mP2;
            si2.add(holder2);
        }
        si2.changeToExtractionMode();

        SimpleWriter outFile = new SimpleWriter1L(file);
        createHTML(outFile, si2, size, file, inFile);
        outFile.close();
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
        /*
         * Put your main program code here; it may call myMethod as shown
         */

        Map<String, Integer> terms = new Map1L<>();

        out.print("Please enter a file name for Tag Cloud Generation: ");
        String file = in.nextLine();
        String outFile = "";
        out.print(
                "Enter the number of words to be included in generation (negative to quit): ");
        int cloudSize = in.nextInteger();
        if (cloudSize == 0) {
            out.print("Did you mean to enter zero? Try again: ");
            cloudSize = in.nextInteger();
        } else if (cloudSize < 0) {
            System.exit(0);
        } else {
            SimpleReader inFile = new SimpleReader1L(file);
            out.print(
                    "Please enter the name and location of a file to write to (include .html): ");
            outFile = in.nextLine();

            out.println("Processing...");
            processNewFile(inFile, terms);
        }
        sortTwice(terms, cloudSize, outFile, file);

        out.println("Done!");
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
