import components.binarytree.BinaryTree;
import components.binarytree.BinaryTree1;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Utility class with implementation of {@code BinaryTree} static, generic
 * methods height and isInTree.
 *
 * @author Andrew Schneider
 *
 */
public final class BinaryTreeMethods {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private BinaryTreeMethods() {
    }

    /**
     * Returns the height of the given {@code BinaryTree<T>}.
     *
     * @param <T>
     *            the type of the {@code BinaryTree} node labels
     * @param t
     *            the {@code BinaryTree} whose height to return
     * @return the height of the given {@code BinaryTree}
     * @ensures height = ht(t)
     */
    public static <T> int height(BinaryTree<T> t) {
        assert t != null : "Violation of: t is not null";

        int sizeL = 0;
        int sizeR = 0;
        int end = 0;
        BinaryTree<T> left = new BinaryTree1<>();
        BinaryTree<T> right = new BinaryTree1<>();

        if (t.height() != 0) {
            T root = t.disassemble(left, right);

            sizeL += height(left);
            sizeR += height(right);
            t.assemble(root, left, right);
            sizeL++;
            sizeR++;
        }
        if (sizeL > sizeR) {
            end = sizeL;
        } else {
            end = sizeR;
        }

        return end;
    }

    /**
     * Returns true if the given {@code T} is in the given {@code BinaryTree<T>}
     * or false otherwise.
     *
     * @param <T>
     *            the type of the {@code BinaryTree} node labels
     * @param t
     *            the {@code BinaryTree} to search
     * @param x
     *            the {@code T} to search for
     * @return true if the given {@code T} is in the given {@code BinaryTree},
     *         false otherwise
     * @ensures isInTree = [true if x is in t, false otherwise]
     */
    public static <T> boolean isInTree(BinaryTree<T> t, T x) {
        assert t != null : "Violation of: t is not null";
        assert x != null : "Violation of: x is not null";

        // TODO - fill in body
        //        boolean found = false;
        //        BinaryTree<T> left = new BinaryTree1<>();
        //        BinaryTree<T> right = new BinaryTree1<>();
        //
        //        if (t.height() != 0) {
        //            T root = t.disassemble(left, right);
        //            if (root.equals(x)) {
        //                found = true;
        //            } else {
        //                found = isInTree(left, x) || isInTree(right, x);
        //            }
        //            t.assemble(root, left, right);
        //        }
        //        return found;

        boolean found = false;
        for (T y : t) {
            if (y.equals(x)) {
                found = true;
            }
        }

        return found;
    }

    /**
     * Returns the {@code String} prefix representation of the given
     * {@code BinaryTree<T>}.
     *
     * @param <T>
     *            the type of the {@code BinaryTree} node labels
     * @param t
     *            the {@code BinaryTree} to convert to a {@code String}
     * @return the prefix representation of {@code t}
     * @ensures treeToString = [the String prefix representation of t]
     */
    public static <T> String treeToString(BinaryTree<T> t) {
        String toRet = "";
        BinaryTree<T> left = new BinaryTree1<>();
        BinaryTree<T> right = new BinaryTree1<>();
        if (t.size() != 0) {
            T root = t.disassemble(left, right);
            toRet += (root.toString() + "(");
            toRet += treeToString(left);
            toRet += treeToString(right);
            toRet += ")";
        } else {
            toRet += "{}";
        }

        return toRet;
    }

    /**
     * Returns a copy of the the given {@code BinaryTree}.
     *
     * @param t
     *            the {@code BinaryTree} to copy
     * @return a copy of the given {@code BinaryTree}
     * @ensures copy = t
     */
    public static BinaryTree<Integer> copy(BinaryTree<Integer> t) {

        BinaryTree<Integer> copy = new BinaryTree1<>();
        BinaryTree<Integer> left = new BinaryTree1<>();
        BinaryTree<Integer> right = new BinaryTree1<>();

        boolean end = false;
        while (!end) {
            int root = t.disassemble(left, right);
            copy.assemble(root, left, right);
            t.assemble(root, left, right);
        }
        return copy;
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

        out.print("Input a tree (or just press Enter to terminate): ");
        String str = in.nextLine();
        while (str.length() > 0) {
            BinaryTree<String> t = BinaryTreeUtility.treeFromString(str);
            out.println("Tree = " + BinaryTreeUtility.treeToString(t));
            out.println("Height = " + height(t));
            out.print("  Input a label to search "
                    + "(or just press Enter to input a new tree): ");
            String label = in.nextLine();
            while (label.length() > 0) {
                if (isInTree(t, label)) {
                    out.println("    \"" + label + "\" is in the tree");
                } else {
                    out.println("    \"" + label + "\" is not in the tree");
                }
                out.print("  Input a label to search "
                        + "(or just press Enter to input a new tree): ");
                label = in.nextLine();
            }
            out.println();
            out.print("Input a tree (or just press Enter to terminate): ");
            str = in.nextLine();
        }

        in.close();
        out.close();
    }

}
