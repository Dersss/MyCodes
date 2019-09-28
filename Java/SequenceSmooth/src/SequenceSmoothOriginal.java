import components.sequence.Sequence;

/**
 * Implements method to smooth a {@code Sequence<Integer>}.
 *
 * @author Andrew Schneider
 *
 */
public final class SequenceSmoothOriginal {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private SequenceSmoothOriginal() {
    }

    /**
     * RECURSIVE SOLUTION
     */

    /**
     * Smooths a given {@code Sequence<Integer>}.
     *
     * @param s1
     *            the sequence to smooth
     * @param s2
     *            the resulting sequence
     * @replaces s2
     * @requires |s1| >= 1
     * @ensures <pre>
     * |s2| = |s1| - 1  and
     *  for all i, j: integer, a, b: string of integer
     *      where (s1 = a * <i> * <j> * b)
     *    (there exists c, d: string of integer
     *       (|c| = |a|  and
     *        s2 = c * <(i+j)/2> * d))
     * </pre>
     */
    //    public static void smooth(Sequence<Integer> s1, Sequence<Integer> s2) {
    //        assert s1 != null : "Violation of: s1 is not null";
    //        assert s2 != null : "Violation of: s2 is not null";
    //        assert s1.length() >= 1 : "|s1| >= 1";
    //
    //        // TODO - fill in body
    //
    //        if (s1.length() == 1) {
    //            s2.clear();
    //        } else {
    //            s2.clear();
    //            if (s1.length() == 2) {
    //                int first = s1.entry(0);
    //                int second = s1.entry(1);
    //                if (first != second) {
    //                    int add = first + second;
    //                    add /= 2;
    //                    s2.add(s2.length(), add);
    //                } else {
    //                    int hold = s1.entry(0);
    //                    s2.add(s2.length(), hold);
    //                }
    //            } else {
    //                Sequence<Integer> replace = new Sequence1L<>();
    //                int first = s1.remove(0);
    //                int second = s1.remove(0);
    //                int add = first + second;
    //                add /= 2;
    //                replace.add(replace.length(), add);
    //                s1.add(0, second);
    //                smooth(s1, s2);
    //                int i = 0;
    //                while (i < s2.length()) {
    //                    int hold = s2.remove(i);
    //                    replace.add(replace.length(), hold);
    //                }
    //                s1.add(0, first);
    //                s2.transferFrom(replace);
    //            }
    //
    //        }
    //    }

    /**
     * NONRECURSIVE SOLUTION
     */

    /**
     * Smooths a given {@code Sequence<Integer>}.
     *
     * @param s1
     *            the sequence to smooth
     * @param s2
     *            the resulting sequence
     * @replaces s2
     * @requires |s1| >= 1
     * @ensures <pre>
     * |s2| = |s1| - 1  and
     *  for all i, j: integer, a, b: string of integer
     *      where (s1 = a * <i> * <j> * b)
     *    (there exists c, d: string of integer
     *       (|c| = |a|  and
     *        s2 = c * <(i+j)/2> * d))
     * </pre>
     */
    public static void smooth(Sequence<Integer> s1, Sequence<Integer> s2) {
        assert s1 != null : "Violation of: s1 is not null";
        assert s2 != null : "Violation of: s2 is not null";
        assert s1.length() >= 1 : "|s1| >= 1";

        // TODO - fill in body

        int index = 0;
        s2.clear();
        while (index < s1.length() - 1) {
            int first = s1.entry(index);
            int second = s1.entry(index + 1);
            if (first != second) {
                int total = first + second;
                total /= 2;
                s2.add(s2.length(), total);
            } else {
                s2.add(s2.length(), first);
            }

            index++;
        }

    }

}
