import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * A program that takes a user input file, counts the occurences of each word,
 * and created an HTML file with a TAG WORD CLOUD.
 *
 * @author Andrew Schneider
 * @author Eric Sullivan
 * @author hitesh
 *
 */

public final class JCFTag {
    /**
     * Private constructor.
     */
    private JCFTag() {

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
     *
     * Sorts the map by integer values.
     *
     */
    private static class EntryVLT
            implements Serializable, Comparator<Map.Entry<String, Integer>> {

        @Override
        public int compare(Map.Entry<String, Integer> mp1,
                Map.Entry<String, Integer> mp2) {
            return mp2.getValue().compareTo(mp1.getValue());
        }
    }

    /**
     *
     * Sorts the map by string values.
     *
     */
    private static class EntrySLT
            implements Serializable, Comparator<Map.Entry<String, Integer>> {

        @Override
        public int compare(Map.Entry<String, Integer> mp3,
                Map.Entry<String, Integer> mp4) {
            return mp3.getKey().toLowerCase()
                    .compareTo(mp4.getKey().toLowerCase());
        }
    }

    /**
     * Separates tokens words and separators from input line.
     *
     * @param line
     *            the line to be broken up
     * @param pos
     *            current position within the line
     * @param separators
     *            separator characters
     * @requires |line| /= 0
     * @requires {@code pos} < |line|
     * @ensures token is substring of line token = string of Characters or
     *          Separators
     * @return {@code word}
     */
    public static String nextWordOrSeparator(String line, int pos,
            Set<Character> separators) {
        String word = "";
        int index = pos;
        boolean tokenCapture = false;

        while (index < line.length() && !tokenCapture) {
            boolean isSeparator = separators.contains(line.charAt(pos));
            if (isSeparator) {
                if (separators.contains(line.charAt(index))) {
                    word += line.charAt(index);
                } else {
                    tokenCapture = true;
                }
            } else {
                if (!separators.contains(line.charAt(index))) {
                    word += line.charAt(index);
                } else {
                    tokenCapture = true;
                }
            }
            index++;
        }
        return word;
    }

    /**
     * Generates separator characters into a set.
     *
     * @param seps
     *            characters to be added
     * @param separators
     *            set to be added too
     *
     * @requires |seps| /= 0
     * @ensures separators = set of characters contained in seps
     *
     */
    public static void createSeparatorSet(String seps,
            Set<Character> separators) {
        String keep = "";
        for (char c : seps.toCharArray()) {
            if (keep.indexOf(c) == -1) {
                separators.add(c);
                keep += c;
            }
        }
    }

    /**
     * Creates linked list of Map.Entries and sorts it by number of appearances
     * of each word.
     *
     * @param map
     *            map of with pairs with the key being the term and value being
     *            the number appearances
     * @param size
     *            desired size of map
     * @requires |map| /= 0
     * @requires size > 0
     * @ensures sorter = list of entries(map)
     * @return {@code sorter}
     */
    public static List<Map.Entry<String, Integer>> createListAndSort(
            Map<String, Integer> map, int size) {
        List<Map.Entry<String, Integer>> sorter = new LinkedList<>();
        Comparator<Map.Entry<String, Integer>> ci = new EntryVLT();
        for (Map.Entry<String, Integer> pair : map.entrySet()) {
            sorter.add(pair);
        }
        Collections.sort(sorter, ci);

        Comparator<Map.Entry<String, Integer>> cs = new EntrySLT();
        List<Map.Entry<String, Integer>> sizeList = new LinkedList<>();

        int index = 0;
        while (sizeList.size() < size) {
            Map.Entry<String, Integer> transferPair = sorter.get(index);
            sizeList.add(transferPair);
            index++;
        }
        Collections.sort(sizeList, cs);
        return sizeList;
    }

    /**
     * Sorts loaded map to correct size and prints correct output to HTML.
     *
     * @param map
     *            map containing words
     * @param size
     *            size of word cloud to be generated
     * @param outFile
     *            output file
     * @param inFile
     *            input file
     * @requires |map| /= 0
     * @requires size > 0
     * @ensures Map sorted and is printed to html
     */
    public static void sortToSizeAndPrint(Map<String, Integer> map, int size,
            String outFile, String inFile) {

        List<Map.Entry<String, Integer>> si2 = createListAndSort(map, size);

        int valueTotal = 0;
        for (Map.Entry<String, Integer> pair : si2) {
            int value = pair.getValue();
            valueTotal += value;
        }
        createHTML(outFile, inFile, size, si2, valueTotal);

    }

    /**
     * Prints the Word Cloud into the html document.
     *
     * @param outFile
     * @param inFile
     * @param size
     * @param terms
     * @param values
     * @requires size > 0
     * @requires size < |terms|
     * @ensures html document is printed
     */
    public static void createHTML(String outFile, String inFile, int size,
            List<Map.Entry<String, Integer>> terms, int total) {

        try {
            PrintWriter printWrite = new PrintWriter(
                    new BufferedWriter(new FileWriter(outFile)));

            printWrite.println("<html>");
            printWrite.println("<head>");
            printWrite.println("<title> Word Cloud of top " + size
                    + " words in " + outFile + "</title>");
            printWrite.println(
                    "<link href=\"http://cse.osu.edu/software/2231/web-sw2/assignments/projects/tag-cloud-generator/data/tagcloud.css\" rel=\"stylesheet\" type=\"text/css\"");

            printWrite.println("</head>");
            printWrite.println("<body>");
            printWrite.println("<h1> Word Cloud of top " + size + " words in "
                    + inFile + "</h1>");
            printWrite.println("<hr>");
            printWrite.println("<div class=\"cdiv\">");
            printWrite.println("<p class=\"cbox\">");
            int average = total;
            average /= size;

            while (terms.size() > 0) {
                //        for (Map.Pair<String, Integer> m : sort) {
                Map.Entry<String, Integer> m = terms.remove(0);
                printWrite.print("<span style=\"cursor:default\" class=\"f");

                double percentage = (double) (m.getValue() * FIVE) / average;

                percentage += ELEVEN;

                if (percentage > FORTY_EIGHT) {
                    percentage = FORTY_EIGHT;
                } else if (percentage < ELEVEN) {
                    percentage = ELEVEN;
                }

                printWrite.println((int) percentage);
                printWrite.println("\" title=\"count: ");
                printWrite
                        .println(m.getValue() + "\">" + m.getKey() + "</span>");
            }
            printWrite.println("</p>");
            printWrite.println("</div>");

            printWrite.println("</body>");

            printWrite.println("</html>");
            printWrite.close();
        } catch (IOException e) {
            System.err.println("There was an error closing the file.");
        }

    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     * @throws FileNotFoundException
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print(
                "Please enter the name of a file to generate word cloud for: ");
        String userFile = in.nextLine();
        System.out.print(
                "Please enter the name of an output file (including .html): ");
        String outFile = in.nextLine();
        System.out.print(
                "Please enter number of words to be included in cloud: ");
        int cloudSize = in.nextInt();

        String line = null;
        final String separatorChars = "\"'*,.:_?;!-()/\'][1234567890 ";
        Set<Character> separators = new HashSet<>();
        createSeparatorSet(separatorChars, separators);
        Map<String, Integer> loadedMap = new HashMap<>();

        try {

            BufferedReader buffRead = new BufferedReader(
                    new FileReader(userFile));
            while ((line = buffRead.readLine()) != null) {
                int position = 0;
                while (position < line.length()) {
                    String token = nextWordOrSeparator(line, position,
                            separators);
                    position += token.length();
                    if (!separators.contains(token.charAt(0))) {
                        if (loadedMap.containsKey(token)) {
                            int value = loadedMap.get(token);
                            loadedMap.put(token, value + 1);
                        } else {
                            loadedMap.put(token, 1);
                        }
                    }
                }

            }

            buffRead.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to open the file " + userFile);
            in.close();
            return;
        } catch (IOException e) {
            System.out.println("Unable to read the file " + userFile);
        }
        while (cloudSize > loadedMap.size() || cloudSize == 0) {
            System.out.println(
                    "The number of words is more than the size of the Map ("
                            + loadedMap.size() + ") or is zero.");
            System.out.print("Please enter a number within the domain: ");
            cloudSize = in.nextInt();

        }
        sortToSizeAndPrint(loadedMap, cloudSize, outFile, userFile);
        in.close();
    }
}
