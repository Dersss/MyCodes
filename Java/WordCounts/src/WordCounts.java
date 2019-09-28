import java.util.Comparator;

import components.map.Map;
import components.map.Map1L;
import components.queue.Queue;
import components.queue.Queue1L;
import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Program that takes a user inputed text file and counts the occurrences of
 * each word. Program then outputs words and their counts onto an HTML document
 * with a user specified name.
 *
 * @author Andrew Schneider
 *
 */
public final class WordCounts {

    /**
     * Compare {@code String}s in lexicographic order ignoring the case of the
     * first letter.
     */
    private static class StringLT implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return o1.compareToIgnoreCase(o2);
        }
    }

    /**
     * Sorts into alphabetical order and updates Queue @q1.
     *
     * @param q1
     *            Queue to be sorted into alphabetical order
     *
     * @updates q1 to alphabetical order
     *
     * @ensures Given {@code Queue} is sorted into alphabetical order using
     *          comparator StringLT()
     *
     */
    public static void sortAndUpdate(Queue<String> q1) {
        // Creates comparator
        Comparator<String> cs = new StringLT();

        // Sorts given Queue
        q1.sort(cs);
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
     * Returns the first "word" (maximal length string of characters not in
     * {@code separators}) or "separator string" (maximal length string of
     * characters in {@code separators}) in the given {@code text} starting at
     * the given {@code position}.
     *
     * @param text
     *            the {@code String} from which to get the word or separator
     *            string
     * @param position
     *            the starting index
     * @param separators
     *            the {@code Set} of separator characters
     * @return the first word or separator string found in {@code text} starting
     *         at index {@code position}
     * @requires 0 <= position < |text|
     * @ensures <pre>
     * nextWordOrSeparator =
     *   text[position, position + |nextWordOrSeparator|)  and
     * if entries(text[position, position + 1)) intersection separators = {}
     * then
     *   entries(nextWordOrSeparator) intersection separators = {}  and
     *   (position + |nextWordOrSeparator| = |text|  or
     *    entries(text[position, position + |nextWordOrSeparator| + 1))
     *      intersection separators /= {})
     * else
     *   entries(nextWordOrSeparator) is subset of separators  and
     *   (position + |nextWordOrSeparator| = |text|  or
     *    entries(text[position, position + |nextWordOrSeparator| + 1))
     *      is not subset of separators)
     * </pre>
     */
    public static String nextWordOrSeparator(String text, int position,
            Set<Character> separators) {
        assert text != null : "Violation of: text is not null";
        assert separators != null : "Violation of: separators is not null";
        assert 0 <= position : "Violation of: 0 <= position";
        assert position < text.length() : "Violation of: position < |text|";

        /**
         * Boolean to test initially whether the string is a word or composed of
         * separator values.
         */
        boolean sepString = false;

        /**
         * Boolean to test whether text has switched from a word or separator or
         * vice versa.
         */
        boolean done = false;

        // Determine if the initial "position" is the start of a word or a separator.
        if (separators.contains(text.charAt(position))) {
            sepString = true;
        }

        // Starts the search index at the specified position.
        int index = position;
        while (index < text.length() && !done) {

            // Separator String
            if (sepString) {
                /**
                 * If the index contains another separator value then move onto
                 * next index. Else, the index is the start of the next word and
                 * boolean moveOn should be true.
                 */
                if (separators.contains(text.charAt(index))) {
                    index++;
                } else {
                    done = true;
                }
            }

            // Word String
            if (!sepString) {
                /**
                 * If the index contains another non separator value then move
                 * onto next index. Else, the index is the start of the next
                 * separator string and boolean moveOn should be true.
                 */
                if (!separators.contains(text.charAt(index))) {
                    index++;
                } else {
                    done = true;
                }
            }
        }

        /**
         * Returns the substring of text from initial position to index that
         * ends the separator or characters string.
         */
        return text.substring(position, index);
    }

    /**
     *
     * Takes each individual line and separates each unique word and the number
     * of times it appears and adds it to a map. Also creates a Queue<String>
     * for each word to be sorted later. Method also takes a string of unique
     * {@code separators} and creates a set to be used in unpacking of each line
     * for words/
     *
     * @param in
     *            SimpleReader to read in each line of input from txt file.
     * @param m1
     *            Map holding each pair of unique words and their counts.
     * @param q1
     *            Queue holding alphabetically sorted words from given file
     *
     * @ensures Creates map that has unique pairs of (word, count) and adds all
     *          unique words into the queue
     */
    public static void getWordsFromLine(SimpleReader in,
            Map<String, Integer> m1, Queue<String> q1) {
        /**
         * Creates a set of unique separators to be used in identifying words
         * from lines of input.
         */
        final String seperators = " ,.?;!-";
        Set<Character> seperatorSet = new Set1L<>();
        generateElements(seperators, seperatorSet);

        /**
         * Takes each line from given txt file and starts and index zero and
         * separates each individual character/separator string. If word is
         * separator string it is skipped. If word is unique, it checks the map
         * to see if that word is already in the map. iF not it is added.If the
         * word already exists, the value for that pair is increased.
         *
         */

        // Holds each incoming line from the file
        String temp = in.nextLine();
        // Tracks the position of where the program is at within the individual line
        int position = 0;

        /**
         * @updates position, m1, q1
         * @maintains position < temp.length()
         * @increases position
         */
        while (position < temp.length()) {
            // String for each separator or word string
            String token = nextWordOrSeparator(temp, position, seperatorSet);
            // number to add to position to account for each separator/word string
            int length = token.length();
            position += length;
            // If {@code Map} already has a key for that word,
            // remove pair and increase value
            // Else add new pair for word into {@code Map} and word into {@code Queue}
            if (m1.hasKey(token) && !seperatorSet.contains(token.charAt(0))) {
                Map.Pair<String, Integer> tempPair = m1.remove(token);
                int tempI = tempPair.value();
                tempI++;
                m1.add(token, tempI);
            } else {
                if (!seperatorSet.contains(token.charAt(0))) {
                    m1.add(token, 1);
                    q1.enqueue(token);
                }
            }
        }
    }

    /**
     * Creates HTML page to hold table for terms and their respective counts.
     *
     *
     * @param outFile
     *            Name of file to write too
     * @param inFile
     *            Name of file that was read in
     * @param q1
     *            Sorted Queue of words to be used in creation of table
     * @param m1
     *            Map holding words and their counts to be used with with q1
     *
     * @ensures HTML page is created with table for each word and a count for
     *          how many times it appears in the given file.
     */
    private static void createHTML(String outFile, String inFile,
            Queue<String> q1, Map<String, Integer> m1) {

        // SimpleWriter to write to user specified file
        SimpleWriter out = new SimpleWriter1L(outFile);
        // Queue to hold the terms after they are removed from q1
        Queue<String> q1Temp = q1.newInstance();

        // Prints HTML formatting to specified file
        out.println("<html>");
        out.println("<head><title>Word Counts</title></head>");
        out.println(
                "<h1 style=\"background-color:Red;\"><center><font color=\"white\"> Word Counts from "
                        + inFile + " </font></center></h1>");
        out.println("<body bgcolor=\"LightGray\">");
        out.println("<hr />");
        out.println("<style>");
        out.println("table, th, td {");
        out.println("border: 3px solid black;");
        out.println("border-collapse: collapse;");
        out.println("padding: 10px;");
        out.println("}");
        out.println("</style>");
        out.println("<table style = \"width=100%\"");
        out.println("<tr>");
        out.println("<th><font size = \"6\">" + "Word" + "</font></th>");
        out.println("<th><font size = \"6\">" + "Count" + "</font></th>");
        out.println("</tr>");

        /**
         * @updates q1
         * @maintains |q1| > 0
         * @decreases |q1|
         */
        while (q1.length() != 0) {
            // Holds each term
            String word = q1.dequeue();
            // Corresponding Pairs for every item in
            Map.Pair<String, Integer> wordPair = m1.remove(word);
            // Prints words and their counts into table in HTML file
            out.println("<tr>");
            out.println("<td>" + wordPair.key() + "</td>");
            out.println("<td><font size = \"4\"><font color = \"blue\"><center>"
                    + wordPair.value() + "</center></font></font></td>");
            out.println("</tr>");
            q1Temp.enqueue(word);
        }
        out.println("</table>");
        out.println("</body>");
        out.println("</html>");

        // Transfers terms back into q1
        q1.transferFrom(q1Temp);

        // Closes output
        out.close();
    }

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private WordCounts() {
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

        // Prompts user for an input file name to read from
        out.println("Please enter the name of an input file: ");
        String input = in.nextLine();

        // Prompts user for an output file to write too.
        out.println("Please enter the name of an output file: ");
        String output = in.nextLine();

        // Sets up SimpleReader to read specified file
        SimpleReader inRead = new SimpleReader1L(input);

        // Queue to hold sorted words
        Queue<String> wordSort = new Queue1L<>();
        // Map to hold words and the counts of the number of appearances
        Map<String, Integer> tally = new Map1L<>();

        // Reads lines from input file
        while (!inRead.atEOS()) {
            getWordsFromLine(inRead, tally, wordSort);
        }
        // Sorts and updates the Queue of terms
        sortAndUpdate(wordSort);
        // Creates HTML page for words and their counts
        createHTML(output, input, wordSort, tally);
        /*
         * Close input and output streams
         */
        inRead.close();
        in.close();
        out.close();
    }

}
