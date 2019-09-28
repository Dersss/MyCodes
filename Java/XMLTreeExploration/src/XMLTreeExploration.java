import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Put a short phrase describing the program here.
 *
 * @author Andrew Schneider
 *
 */

//LINK: http://web.cse.ohio-state.edu/software/2221/web-sw1/extras/instructions/xmltree-model/columbus-weather.xml

public final class XMLTreeExploration {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private XMLTreeExploration() {
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

        XMLTree xml = new XMLTree1(
                "http://web.cse.ohio-state.edu/software/2221/web-sw1/extras/instructions/xmltree-model/columbus-weather.xml");
        String xmlStr = xml.toString();
        //        out.println(xmlStr);
        xml.display();

        int numChild = xml.numberOfChildren();
        int counter = 0;
        if (numChild >= 0) {
            if (xml.isTag()) {
                out.println("Root node is a tag.");
                out.println("The tag is: " + xml.label());
            } else {
                out.println("Root node is a text node.");
                out.println("The text is: " + xml.label());
            }
        }
        while (counter < numChild) {
            if (xml.child(counter).isTag()) {
                String label = xml.child(counter).label();
                if (label.equals("results")) {
                    XMLTree results = xml.child(counter);
                    int resNumChld = results.numberOfChildren();
                    int counter2 = 0;
                    while (counter2 < resNumChld) {

                        if (results.child(counter2).isTag()) {

                            String label2 = results.child(counter2).label();
                            if (label2.equals("channel")) {
                                XMLTree channel = results.child(counter2);
                                int channelChild = channel.numberOfChildren();
                                out.println(
                                        "Channel Children: " + channelChild);
                                int counter3 = 0;
                                while (counter3 < channelChild) {
                                    if (channel.child(counter3).isTag()) {
                                        String label3 = channel.child(counter3)
                                                .label();
                                        if (label3.equals("title")) {
                                            XMLTree title = channel
                                                    .child(counter3);
                                            XMLTree titleText = title.child(0);
                                            out.println(titleText.label());
                                        }
                                    }
                                    counter3++;
                                }
                            }
                            if (label2.equals("yweather:astronomy")) {
                                XMLTree astronomy = results.child(counter);
                                out.println("here");
                            }
                        }

                        counter2++;
                    }

                }
            }
            counter++;
        }

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
