import java.util.Iterator;

import components.binarytree.BinaryTree;
import components.binarytree.BinaryTree1;
import components.set.Set;
import components.set.SetSecondary;

/**
 * @author Eric Sullivan
 * @author Hitesh Bavisetti
 * @author Andrew Schneider
 */
/**
 * {@code Set} represented as a {@code BinaryTree} (maintained as a binary
 * search tree) of elements with implementations of primary methods.
 *
 * @param <T>
 *            type of {@code Set} elements
 * @mathdefinitions <pre>
 * IS_BST(
 *   tree: binary tree of T
 *  ): boolean satisfies
 *  [tree satisfies the binary search tree properties as described in the
 *   slides with the ordering reported by compareTo for T, including that
 *   it has no duplicate labels]
 * </pre>
 * @convention IS_BST($this.tree)
 * @correspondence this = labels($this.tree)
 */
public class Set3a<T extends Comparable<T>> extends SetSecondary<T> {

    /*
     * Private members --------------------------------------------------------
     */

    /**
     * Elements included in {@code this}.
     */
    private BinaryTree<T> tree;

    /**
     * Returns whether {@code x} is in {@code t}.
     *
     * @param <T>
     *            type of {@code BinaryTree} labels
     * @param t
     *            the {@code BinaryTree} to be searched
     * @param x
     *            the label to be searched for
     * @return true if t contains x, false otherwise
     * @requires IS_BST(t)
     * @ensures isInTree = (x is in labels(t))
     */
    private static <T extends Comparable<T>> boolean isInTree(BinaryTree<T> t,
            T x) {
        assert t != null : "Violation of: t is not null";
        assert x != null : "Violation of: x is not null";

        // TODO - fill in body
        boolean found = false;

        if (t.height() != 0 && !found) {
            if (t.root().equals(x)) {
                found = true;
            } else {
                BinaryTree<T> left = new BinaryTree1<>();
                BinaryTree<T> right = new BinaryTree1<>();
                T root = t.disassemble(left, right);
                found = isInTree(left, x);
                if (!found) {
                    found = isInTree(right, x);
                }
                t.assemble(root, left, right);
            }
        }

        return found;
    }

    /**
     * Inserts {@code x} in {@code t}.
     *
     * @param <T>
     *            type of {@code BinaryTree} labels
     * @param t
     *            the {@code BinaryTree} to be searched
     * @param x
     *            the label to be inserted
     * @aliases reference {@code x}
     * @updates t
     * @requires IS_BST(t) and x is not in labels(t)
     * @ensures IS_BST(t) and labels(t) = labels(#t) union {x}
     */
    private static <T extends Comparable<T>> void insertInTree(BinaryTree<T> t,
            T x) {
        assert t != null : "Violation of: t is not null";
        assert x != null : "Violation of: x is not null";

        BinaryTree<T> left = new BinaryTree1<>();
        BinaryTree<T> right = new BinaryTree1<>();

        if (t.size() == 0) {
            t.assemble(x, left, right);
        } else {
            T root = t.disassemble(left, right);
            if (x.compareTo(root) < 0) {
                insertInTree(left, x);
            } else {
                insertInTree(right, x);
            }
            t.assemble(root, left, right);
        }

    }

    /**
     * Removes and returns the smallest (left-most) label in {@code t}.
     *
     * @param <T>
     *            type of {@code BinaryTree} labels
     * @param t
     *            the {@code BinaryTree} from which to remove the label
     * @return the smallest label in the given {@code BinaryTree}
     * @updates t
     * @requires IS_BST(t) and |t| > 0
     * @ensures <pre>
     * IS_BST(t)  and  removeSmallest = [the smallest label in #t]  and
     *  labels(t) = labels(#t) \ {removeSmallest}
     * </pre>
     */
    private static <T> T removeSmallest(BinaryTree<T> t) {
        assert t != null : "Violation of: t is not null";

        T smallest = null;
        BinaryTree<T> left = new BinaryTree1<>();
        BinaryTree<T> right = new BinaryTree1<>();
        T root = t.disassemble(left, right);
        if (left.size() == 0) {
            smallest = root;
            t.transferFrom(right);
        } else {
            smallest = removeSmallest(left);
            t.assemble(root, left, right);
        }

        return smallest;
    }

    /**
     * Finds label {@code x} in {@code t}, removes it from {@code t}, and
     * returns it.
     *
     * @param <T>
     *            type of {@code BinaryTree} labels
     * @param t
     *            the {@code BinaryTree} from which to remove label {@code x}
     * @param x
     *            the label to be removed
     * @return the removed label
     * @updates t
     * @requires IS_BST(t) and x is in labels(t)
     * @ensures <pre>
     * IS_BST(t)  and  removeFromTree = x  and
     *  labels(t) = labels(#t) \ {x}
     * </pre>
     */
    private static <T extends Comparable<T>> T removeFromTree(BinaryTree<T> t,
            T x) {
        assert t != null : "Violation of: t is not null";
        assert x != null : "Violation of: x is not null";

        // TODO - fill in body
        T ret = null;
        BinaryTree<T> left = new BinaryTree1<>();
        BinaryTree<T> right = new BinaryTree1<>();

        if (x.compareTo(t.root()) != 0) {
            T root = t.disassemble(left, right);
            if (x.compareTo(root) < 0) {
                ret = removeFromTree(left, x);
            } else {
                ret = removeFromTree(right, x);
            }
            t.assemble(root, left, right);
        } else {
            T root = t.disassemble(left, right);
            ret = root;
            if (right.size() != 0) {
                root = removeSmallest(right);
                t.assemble(root, left, right);
            } else {
                t.transferFrom(left);
            }

        }
        return ret;
    }

    /**
     * Creator of initial representation.
     */
    private void createNewRep() {

        // TODO - fill in body
        this.tree = new BinaryTree1<>();

    }

    /*
     * Constructors -----------------------------------------------------------
     */

    /**
     * No-argument constructor.
     */
    public Set3a() {

        // TODO - fill in body
        this.createNewRep();

    }

    /*
     * Standard methods -------------------------------------------------------
     */

    @SuppressWarnings("unchecked")
    @Override
    public final Set<T> newInstance() {
        try {
            return this.getClass().newInstance();
        } catch (ReflectiveOperationException e) {
            throw new AssertionError(
                    "Cannot construct object of type " + this.getClass());
        }
    }

    @Override
    public final void clear() {
        this.createNewRep();
    }

    @Override
    public final void transferFrom(Set<T> source) {
        assert source != null : "Violation of: source is not null";
        assert source != this : "Violation of: source is not this";
        assert source instanceof Set3a<?> : ""
                + "Violation of: source is of dynamic type Set3<?>";
        /*
         * This cast cannot fail since the assert above would have stopped
         * execution in that case: source must be of dynamic type Set3a<?>, and
         * the ? must be T or the call would not have compiled.
         */
        Set3a<T> localSource = (Set3a<T>) source;
        this.tree = localSource.tree;
        localSource.createNewRep();
    }

    /*
     * Kernel methods ---------------------------------------------------------
     */

    @Override
    public final void add(T x) {
        assert x != null : "Violation of: x is not null";
        assert !this.contains(x) : "Violation of: x is not in this";

        // TODO - fill in body

        insertInTree(this.tree, x);

    }

    @Override
    public final T remove(T x) {
        assert x != null : "Violation of: x is not null";
        assert this.contains(x) : "Violation of: x is in this";

        // TODO - fill in body

        T r = removeFromTree(this.tree, x);

        return r;
    }

    @Override
    public final T removeAny() {
        assert this.size() > 0 : "Violation of: this /= empty_set";

        // TODO - fill in body

        T r = removeSmallest(this.tree);

        return r;
    }

    @Override
    public final boolean contains(T x) {
        assert x != null : "Violation of: x is not null";

        // TODO - fill in body

        return isInTree(this.tree, x);
    }

    @Override
    public final int size() {

        // TODO - fill in body

        return this.tree.size();
    }

    @Override
    public final Iterator<T> iterator() {
        return this.tree.iterator();
    }

}