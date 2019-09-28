import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree2;

/**
 * Program to practice recursion on {@code XMLTree}s.
 *
 * @author Andrew Schneider
 *
 */
public final class XMLTreeRecursion_OLD {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private XMLTreeRecursion_OLD() {
    }

    /**
     * Returns the number of occurrences of the given tag in the given
     * {@code XMLTree}.
     *
     * @param xml
     *            the {@code XMLTree}
     * @param tag
     *            the tag name
     * @return the number of occurrences of the given tag in the given
     *         {@code XMLTree}
     * @ensures <pre>
     * tagCount =
     *    [the number of occurrences of the given tag in the given {@code XMLTree}]
     * </pre>
     */
    private static int tagCount(XMLTree xml, String tag) {
        assert xml != null : "Violation of: xml is not null";
        assert tag != null : "Violation of: tag is not null";

        int count = 0;
        if (xml.label().equals(tag)) {
            count++;
        }
        int numChild = xml.numberOfChildren();
        for (int i = 0; i < numChild; i++) {
            count += tagCount(xml.child(i), tag);
        }
        return count;
    }

    /**
     * Outputs the text nodes in the given {@code XMLTree} on separate lines.
     *
     * @param xml
     *            the {@code XMLTree}
     * @param out
     *            the output stream
     * @updates out.content
     * @requires out.is_open
     * @ensures <pre>
     * out.content = #out.content * [the text nodes of xml on separate lines]
     * </pre>
     */
    private static void outputTextNodes(XMLTree xml, SimpleWriter out) {
        assert xml != null : "Violation of: xml is not null";
        assert out != null : "Violation of: out is not null";
        assert out.isOpen() : "Violation of: out.is_open";

        // TODO - fill in body
        if (xml.attributeNames().toString().equals("{}")) {
            // nothing
        } else {
            String temp = xml.attributeNames().toString();

            if (!temp.equals("{}")) {
                out.println("value: " + xml
                        .attributeValue(temp.substring(1, temp.length() - 1)));
            }
        }
        int numChild = xml.numberOfChildren();
        for (int i = 0; i < numChild; i++) {
            outputTextNodes(xml.child(i), out);
        }

    }

    private static int sumOfCounts(XMLTree t) {
        int toRet = 0;
        if (t.hasAttribute("value")) {
            String temp3 = t.attributeValue("value");
            System.out.println("Value: " + temp3);
            toRet += Integer.parseInt(temp3);
        }
        int numChild = t.numberOfChildren();
        for (int i = 0; i < numChild; i++) {
            int hold = sumOfCounts(t.child(i));
            toRet += hold;
        }
        return toRet;
    }

    /**
     * Outputs n spaces.
     *
     * @param n
     *            the number of spaces
     * @param out
     *            the output stream
     * @updates out.content
     * @requires out.is_open and n >= 0
     * @ensures out.content = #out.content * [n spaces]
     */
    private static void outputSpaces(int n, SimpleWriter out) {
        assert out != null : "Violation of: out is not null";
        assert out.isOpen() : "Violation of: out.is_open";
        assert n >= 0 : "Violation of: n >= 0";

        // TODO - fill in body
        for (int i = 0; i < n; i++) {
            out.print(" ");
        }

    }

    /**
     * Outputs the attributes ( name="value") of the given {@code XMLTree}'s
     * root node to the given output stream.
     *
     * @param xml
     *            the {@code XMLTree}
     * @param out
     *            the output stream
     * @updates out.content
     * @requires out.is_open and [the label of the root of xml is a tag]
     * @ensures <pre>
     * out.content =
     *    #out.content * [the attributes ( name="value") of the root of xml]
     * </pre>
     */
    private static void outputAttributes(XMLTree xml, SimpleWriter out) {
        assert xml != null : "Violation of: xml is not null";
        assert out != null : "Violation of: out is not null";
        assert xml
                .isTag() : "Violation of: the label of the root of xml is a tag";
        assert out.isOpen() : "Violation of: out.is_open";

        // TODO - fill in body
        Iterable<String> hold = xml.attributeNames();
        out.println("Attribute name: " + hold);

    }

    /**
     * Output the XML textual representation of the given {@code XMLTree}.
     *
     * @param xml
     *            the {@code XMLTree}
     * @param out
     *            the output stream
     * @param indentationLevel
     *            the level of indentation
     * @updates out.content
     * @requires out.is_open and indentationLevel >= 0
     * @ensures <pre>
     * out.content = #out.content * [the XML textual representation of xml]
     * </pre>
     */
    private static void outputXML(XMLTree xml, SimpleWriter out,
            int indentationLevel) {
        assert xml != null : "Violation of: xml is not null";
        assert out != null : "Violation of: out is not null";
        assert out.isOpen() : "Violation of: out.is_open";
        assert indentationLevel >= 0 : "Violation of: indentationLevel >= 0";

        // TODO - fill in body
        outputSpaces(indentationLevel, out);
        out.println(xml.toString());

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

        out.print("Enter a URL or file name for an XML source: ");
        String url = in.nextLine();
        XMLTree xml = new XMLTree2(url);

        out.print("Enter the name of a tag: ");
        String tag = in.nextLine();
        while (!tag.equals("")) {
            int count = tagCount(xml, tag);
            out.println("The tag <" + tag + "> appears " + count + " times.");
            out.println();
            out.print("Enter the name of a tag: ");
            tag = in.nextLine();
        }

        out.println();
        out.println("The text nodes:");
        outputTextNodes(xml, out);

        out.println();
        out.println("The XML:");
        outputXML(xml, out, 0);

        out.println();
        int temp = sumOfCounts(xml);
        out.println("Sum of counts: " + temp);

        in.close();
        out.close();
    }

}
