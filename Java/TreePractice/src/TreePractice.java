import components.sequence.Sequence;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.tree.Tree;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class TreePractice {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private TreePractice() {
    }

    /**
     * Returns the size of the given {@code Tree<T>}.
     *
     * @param <T>
     *            the type of the {@code Tree} node labels
     * @param t
     *            the {@code Tree} whose size to return
     * @return the size of the given {@code Tree}
     * @ensures size = |t|
     */
    public static <T> int size(Tree<T> t) {
        // Recursive solution
        int count = 0;
        Sequence<Tree<T>> subSeq = t.newSequenceOfTree();
        if (t.height() != 0) {
            T root = t.disassemble(subSeq);
            int i = 0;
            while (i < subSeq.length()) {
                count = count + size(subSeq.entry(i));
                i++;
            }
            t.assemble(root, subSeq);
            count++;
        }
        return count;

        // Iterative solution
        //        int count = 0;
        //        Iterator<T> it = t.iterator();
        //        while (it.hasNext()) {
        //            count++;
        //        }
        //        return count;
    }

    /**
     * Returns the height of the given {@code Tree<T>}.
     *
     * @param <T>
     *            the type of the {@code Tree} node labels
     * @param t
     *            the {@code Tree} whose height to return
     * @return the height of the given {@code Tree}
     * @ensures height = ht(t)
     */
    public static <T> int height(Tree<T> t) {
        // Recursive Solution
        int total = 0;
        int current = 0;
        Sequence<Tree<T>> seq = t.newSequenceOfTree();
        if (t.size() > 0) {
            T root = t.disassemble(seq);
            current++;
            int i = 0;
            while (i < seq.length()) {
                current = current + height(seq.entry(i));
                i++;
            }
            if (current > total) {
                total = current;
            }
        }

        return total;

    }

    /**
     * Returns the largest integer in the given {@code Tree<Integer>}.
     *
     * @param t
     *            the {@code Tree<Integer>} whose largest integer to return
     * @return the largest integer in the given {@code Tree<Integer>}
     * @requires |t| > 0
     * @ensures <pre>
     * max is in labels(t)  and
     * for all i: integer where (i is in labels(t)) (i <= max)
     * </pre>
     */
    public static int max(Tree<Integer> t) {
        int max = 0;
        Sequence<Tree<Integer>> sub = t.newSequenceOfTree();
        if (t.size() != 0) {
            int root = t.disassemble(sub);
            if (root > max) {
                max = root;
            }

            int i = 0;
            while (i < sub.length()) {
                if (max(sub.entry(i)) > max) {
                    max = max(sub.entry(i));
                }
                i++;
            }
            t.assemble(root, sub);
        }

        return max;
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
         * Put your main program code here
         */
        /*
         * Close input and output streams
         */

        in.close();
        out.close();
    }

}
