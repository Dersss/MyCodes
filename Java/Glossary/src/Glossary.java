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
 * Program that given a text file containing terms, builds a glossary of terms.
 * Outputs the glossary in HTML format with sub pages for each term and
 * definition under the top level index.
 *
 * @author Andrew Schneider
 *
 */
public final class Glossary {

    /**
     * Compare {@code String}s in lexicographic order.
     */
    private static class StringLT implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return o1.compareTo(o2);
        }
    }

    /**
     * Sorts into alphabetical order and updates Queue @q1
     *
     * @param q1
     *            Queue to be sorted into alphabetical order
     *
     * @updates q1 to alphabetical order
     *
     */
    public static void sortAndUpdate(Queue<String> q1) {
        Comparator<String> cs = new StringLT();
        q1.sort(cs);
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
        boolean seperatorString = false;

        /**
         * Boolean to test whether text has switched from a word or separator or
         * vice versa.
         */
        boolean moveOn = false;

        // Determine if the initial "position" is the start of a word or a separator.
        if (separators.contains(text.charAt(position))) {
            seperatorString = true;
        }

        // Starts the search index at the specified position.
        int index = position;
        while (index < text.length() && !moveOn) {

            // Separator String
            if (seperatorString) {
                /**
                 * If the index contains another separator value then move onto
                 * next index. Else, the index is the start of the next word and
                 * boolean moveOn should be true.
                 */
                if (separators.contains(text.charAt(index))) {
                    index++;
                } else {
                    moveOn = true;
                }
            }

            // Word String
            if (!seperatorString) {
                /**
                 * If the index contains another non separator value then move
                 * onto next index. Else, the index is the start of the next
                 * separator string and boolean moveOn should be true.
                 */
                if (!separators.contains(text.charAt(index))) {
                    index++;
                } else {
                    moveOn = true;
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
     * Creates sub page for each term in the glossary.
     *
     * @param term
     *            The term in which to create a sub page for.
     * @param m1
     *            Map holding terms and their definitions
     * @param q1
     *            Queue holding all terms in alphabetical order
     *
     * @param folder
     *            folder to write sub page too
     *
     * @requires term is in m1 and q1 and both m1 and q1 are not empty
     * @ensures Sub page is created for the given term.
     *
     */
    public static void createSubpage(String term, Map<String, String> m1,
            Queue<String> q1, String folder) {
        assert term != null : "Violation of: term is not null";
        assert m1 != null : "Violation of: m1 is not null";
        assert q1 != null : "Violation of: q1 is not null";

        /**
         * Creates SimpleWriter to output to "term".html inside the user
         * specified folder.
         *
         * If the terms is upper case then add CAP to the end of the .html file
         * to write too.
         */
        String termHold = term;
        if (Character.isUpperCase(term.charAt(0))) {
            termHold = termHold + "CAP";
        }
        SimpleWriter out = new SimpleWriter1L(
                folder + "/" + termHold + ".html");

        /**
         * Removes specified pair from the map that holds the given term and
         * its' definition.
         */
        Map.Pair<String, String> hold = m1.remove(term);

        /**
         * Generates a set of separators to be able to process each definition.
         */
        final String separatorStr = " ,.?;!";
        Set<Character> separatorSet = new Set1L<>();
        generateElements(separatorStr, separatorSet);

        /**
         * Creates the .html page for the given term with specified
         * specifications.
         */
        out.println("<html>");
        out.println("<head>");
        out.println("<title>" + term + "</title>");
        out.println("</head>");
        out.println("<body bgcolor=\"LightGray\">");
        out.println("<h2><b><i><font color=\"red\">" + term
                + "</font></i></b></h2>");
        out.print("<blockquote>");

        /**
         * Takes the definition and uses the nextWordOrSeparator() method to go
         * word by word to be able to link other terms in the definition to
         * their sub pages.
         */
        String def = hold.value();

        int position = 0;
        while (position < def.length()) {
            // token will hold value of word or separator string.
            String token = nextWordOrSeparator(def, position, separatorSet);

            /**
             * Tracks the length of the initial word before changing string to
             * create link.
             */
            int add = token.length();

            /**
             * Iterates over the queue to check other terms with the value of
             * token.
             */
            for (String x : q1) {
                String check = x;
                // If word is a term then link to corresponding sub page.
                String checkHold = check;
                if (Character.isUpperCase(check.charAt(0))) {
                    checkHold = checkHold + "CAP";
                }

                if (token.equals(check)) {
                    token = "<a href=\"" + checkHold + ".html\">" + check
                            + "</a>";
                }
            }
            out.print(token);
            position += add;
        }
        /**
         * Outputs bottom lines onto .html page.
         */
        out.print("</blockquote>\n");
        out.println("<hr />");
        out.println("<p>Return to <a href=\"" + "index.html" + "\">" + "index"
                + "</a>.</p>");
        out.println("</body>");
        out.println("</html>");
        out.close();
    }

    /**
     * Creates the top level index that has links for sub pages of all terms
     * in @m1.
     *
     * @param m1
     *            Map containing terms and corresponding definitions in String
     *            format.
     * @param q1
     *            Queue that holds terms in alphabetical order
     * @param folder
     *            Name of folder that the index and sub pages should be written
     *            too.
     * @ensures Top level index is created from all of the terms in @m1
     *
     */
    public static void createTopIndex(Queue<String> q1, String folder,
            Map<String, String> m1) {

        // SimpleWriter to write to index.html within user specified folder.
        SimpleWriter out = new SimpleWriter1L(folder + "/index.html");

        /**
         * Creates top level lines of the main index page.
         */
        out.println("<html>");
        out.println("<head><title>Glossary</title></head>");
        out.println(
                "<h1 style=\"background-color:FireBrick;\"><center><font color=\"white\"> Glossary of Terms </font></center></h1>");
        out.println("<body bgcolor=\"LightGray\">");
        out.println("<h2>Index</h2>");
        out.println("<hr></hr>");
        out.println("<ul>");

        /**
         * With alphabetically ordered queue, creates sub pages for each term.
         * Creates link for each sub page.
         */

        for (String x : q1) {
            String term = x;
            String termHold = term;
            if (Character.isUpperCase(term.charAt(0))) {
                termHold = termHold + "CAP";
            }

            out.println("<li><a href=\"" + termHold + ".html\">" + term
                    + "</a></li>");
            createSubpage(term, m1, q1, folder);
        }

        /**
         * Creates bottom level lines of main index page.
         */
        out.println("</ul>");
        out.println("<hr></hr>");
        out.println("</body>");
        out.println("</html>");

        // Closes output.
        out.close();
    }

    /**
     * Processes terms {@code String} and definitions {@code String} into a map
     * {@code Map} as well as terms {@code String} into a {@code Queue} from the
     * input file (@code String).
     *
     * @param file
     *            The file in which to input
     * @param m1
     *            Map to hold terms as the key and definitions as the value.
     * @param q1
     *            Queue to hold terms in alphabetical order
     *
     * @updates m1
     *
     * @ensures All terms and definitions are loaded into the map and all terms
     *          are added into q1 to be sorted.
     */
    public static void processTermsIntoMapAndQueue(String file,
            Map<String, String> m1, Queue<String> q1) {

        /**
         * Creates SimpleReader to read terms and definitions from the user
         * specified .txt file
         */
        SimpleReader inFile = new SimpleReader1L(file);

        /**
         * Strings for terms and definitions.
         */
        String term = "";
        String definition = "";

        /**
         * Reads input from SimpleReader until it is at end of stream.
         */
        while (!inFile.atEOS()) {

            // Holds each temporary String
            String next = inFile.nextLine();

            /**
             * If line is empty or inFile is at end of stream, loads terms and
             * definitions into String variables.
             *
             * Else if nextLine doesn't contain any spaces then it is most
             * likely a definition.
             */
            if (next.isEmpty() || inFile.atEOS()) {
                if (!term.isEmpty() && definition.isEmpty()) {
                    definition = next;
                }

                /**
                 * Loads Pairs into map and loads terms into queue
                 */
                m1.add(term, definition);
                q1.enqueue(term);

                /**
                 * empties values of term and definition.
                 */

                term = "";
                definition = "";
            } else {
                /**
                 * If the String does not contain any spaces and is not empty it
                 * is most likely a term.
                 */

                if (!next.contains(" ")) {
                    term = next;
                } else {
                    definition += next;
                }
            }
        }

        // Closes inFile
        inFile.close();
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        /*
         * Creates initial SimpleWriter out and SimpleReader in to output and
         * input user specified terms .txt file and output folder.
         */
        SimpleWriter out = new SimpleWriter1L();
        SimpleReader in = new SimpleReader1L();

        out.println("Please enter name of .txt file containing terms: ");
        String fileName = in.nextLine();
        // If empty input is read, prompts user for file again.
        while (fileName.isEmpty()) {
            out.println("Nothing entered. Try again: ");
            fileName = in.nextLine();
        }
        // If user forgets to add .txt then it is concatenated onto the end.
        if (!fileName.contains(".txt")) {
            fileName = fileName + ".txt";
        }
        out.println(
                "Please enter the name of an existing folder to write too: ");
        String folder = in.nextLine();

        /**
         * Creates Map glossary to hold String values of the terms as keys and
         * their definitions as the corresponding value. Also creates queue
         * termsOrder to be able to sort terms in alphabetical order.
         */
        Map<String, String> glossary = new Map1L<>();
        Queue<String> termsOrder = new Queue1L<>();

        /**
         * Takes user given file and empty Map glossary and empty Queue
         * termsOrder and loads terms and definitions into glossary and only
         * terms into termsOrder.
         *
         */
        processTermsIntoMapAndQueue(fileName, glossary, termsOrder);

        /**
         * Sorts Queue termsOrder into alphabetical order using Comparator.
         */
        sortAndUpdate(termsOrder);

        /**
         * Takes sorted Queue and Glossary and sends to method to output top
         * level index.html page which also creates sub pages for each
         * individual term.
         */
        createTopIndex(termsOrder, folder, glossary);

        // Closes out and in.
        in.close();
        out.close();
    }

}
