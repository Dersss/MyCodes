import java.util.Comparator;

import components.map.Map;
import components.map.Map1L;
import components.queue.Queue;
import components.queue.Queue1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Creates a glossary of terms from a given .txt document. The glossary is then
 * output to a neatly formatted .html file with sub files
 *
 * @author Andrew Schneider
 *
 */
public final class Glossary_OLD {

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
     *
     * @param in
     *            Reads the incoming file
     * @param hold
     *            holds terms and their definitions in form hold<term,
     *            definition>
     *
     * @updates hold Adds each term and definition into corresponding key and
     *          value eliminates empty lines
     */
    public static void getText(SimpleReader in, Map<String, String> hold,
            Queue<String> terms) {
        int count = 0;
        String term = "";
        String def = "";

        while (!in.atEOS()) {
            String temp = in.nextLine();
            if (temp.isEmpty() || in.atEOS()) {
                hold.add(term, def);
                terms.enqueue(term);
                term = "";
                def = "";
            }
            if (!temp.contains(" ")) {
                term = temp;
            } else {
                def += temp;
            }

        }
    }

    /**
     *
     * @param q1
     *            Queue to put into alphabetical order
     *
     * @updates q1 to alphabetial order
     */
    public static void sortAndUpdate(Queue<String> q1) {
        Comparator<String> cs = new StringLT();
        q1.sort(cs);

    }

    /**
     *
     * @param out
     *            prints out to index.html
     * @param map1
     *            map holding terms and their definitions
     * @param q1
     *            queue holding the terms
     * @param outFile
     *            name of original file
     *
     *            Outputs to index.html and creates the suboages for each term
     */
    public static void linkList(SimpleWriter out, Map<String, String> map1,
            Queue<String> q1, String outFile) {
        int size = map1.size();
        int index = 0;
        Map<String, String> hold = new Map1L<String, String>();
        Queue<String> ret = new Queue1L<>();
        hold.transferFrom(map1);
        out.println("<h3> Index </h3>");
        out.println("<ul>");

        while (index < size) {
            String temp = q1.dequeue();
            Map.Pair<String, String> temp2 = hold.remove(temp);
            String term = temp2.key();
            String def = temp2.value();
            hold.add(term, def);
            out.println(
                    "<li><a href=\"" + temp + ".html\">" + temp + "</a></li>");
            String newFile = outFile + "/" + temp + ".html";
            SimpleWriter outNew = new SimpleWriter1L(newFile);
            ret.enqueue(temp);
            outputSubpage(outNew, temp, hold, outFile);
            index++;
        }

        out.println("</ul>");
    }

    /**
     *
     * @param out
     *            prints out to corresponding page
     * @param term
     *            the term to make a page for
     * @param map2
     *            map holding terms and their definitions
     * @param outFile
     *            name of original file "index.html"
     *
     *
     *            Outputs subpage data to corresponding .html page
     */
    public static void outputSubpage(SimpleWriter out, String term,
            Map<String, String> map2, String outFile) {

        Map.Pair<String, String> holder = map2.remove(term);
        out.println("<html>");
        out.println("<head>");
        out.println("<title>" + term + "</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h2><b><i><font color=\"red\">" + term
                + "</font></i></b></h2>");
        out.println("<blockquote>" + holder.value() + "</blockquote>");
        out.println("<hr />");
        out.println("<p>Return to <a href=\"" + "index.html" + "\">" + "Index"
                + "</a>.</p>");
        out.println("</body>");
        out.println("</html>");
    }

    /**
     *
     * @param out
     *            Prints to the user specified output file
     * @requires valid html file name
     *
     * @ensures Proper formatted html header printed out to file
     *
     *          Outputs header to index.html
     */
    public static void outputMainHeader(SimpleWriter out) {
        out.println("<html>");
        out.println("<head>");
        out.println("<title> Fig Newton's Glossary of Terms </title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1> Fig Newton's Glossary of Terms </h1>");
        out.println(
                "<h3> \"This sticker is higly inconvenient, but I do love Fig Newtons\" - Ricky Bobby</h3>");
        out.println("<hr />");
    }

    /**
     *
     * @param out
     *            Prints to the user specified output file
     * @requires valid html file name
     *
     * @ensures Proper formatted html footer printed out to file
     *
     *          Outputs main footer
     */
    public static void outputMainFooter(SimpleWriter out) {
        out.println("</body>");
        out.println("</html>");

    }

    public static void main(String[] args) {
        /*
         * Put your main program code here
         *
         */

        // get input
        SimpleWriter out = new SimpleWriter1L();
        out.println("Please enter name of text file: ");
        SimpleReader in = new SimpleReader1L();
        String fileName = in.nextLine();
        Map<String, String> temp = new Map1L<String, String>();
        Queue<String> terms = new Queue1L<>();
        if (fileName == null) {
            out.println("Empty string entered");
        } else {
            SimpleReader in2 = new SimpleReader1L(fileName);
            getText(in2, temp, terms);
        }
        out.println("Please name of folder to write too: ");
        String outFile = in.nextLine();
        String new1 = outFile + "/index.html";
        SimpleWriter outHTML = new SimpleWriter1L(new1);
        outputMainHeader(outHTML);
        sortAndUpdate(terms);
        linkList(outHTML, temp, terms, outFile);
        outputMainFooter(outHTML);

    }

}
