import components.sequence.Sequence;
import components.sequence.Sequence1L;

/**
 * Implements method to smooth a {@code Sequence<Integer>}.
 *
 * @author Andrew Schneider
 *
 */
public final class SequenceSmooth_HW {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private SequenceSmooth_HW() {
    }

    /**
     * RECURSIVE SOLUTION
     */

    /**
     * Returns a smoothed Sequence<Integer> from a given
     * {@code Sequence<Integer>}.
     *
     * @param s1
     *            the sequence to smooth
     * @requires |s1| >= 1
     * @return the resulting sequence
     * @ensures <pre>
     * |s2| = |s1| - 1  and
     *  for all i, j: integer, a, b: string of integer
     *      where (s1 = a * <i> * <j> * b)
     *    (there exists c, d: string of integer
     *       (|c| = |a|  and
     *        {@code Sequence<Integer} = c * <(i+j)/2> * d))
     * </pre>
     */
    public static Sequence<Integer> smooth(Sequence<Integer> s1) {
        assert s1 != null : "Violation of: s1 is not null";
        assert s1.length() >= 1 : "|s1| >= 1";

        // TODO - fill in body

        Sequence<Integer> next = new Sequence1L<>();
        if (s1.length() != 1) {
            if (s1.length() == 2) {
                int first = s1.entry(0);
                int second = s1.entry(1);
                if (first != second) {
                    long average = ((long) first + (long) second) / 2L;
                    next.add(next.length(), ((int) average));
                } else {
                    int hold = s1.entry(0);
                    next.add(next.length(), hold);
                }
            } else {
                Sequence<Integer> replace = new Sequence1L<>();
                int first = s1.remove(0);
                int second = s1.remove(0);
                long average = ((long) first + (long) second) / 2L;
                replace.add(replace.length(), ((int) average));
                next.add(next.length(), ((int) average));
                s1.add(0, second);
                Sequence<Integer> temp = smooth(s1);
                int i = 0;
                while (i < temp.length()) {
                    int hold = temp.remove(i);
                    replace.add(replace.length(), hold);
                    next.add(next.length(), hold);
                }
                s1.add(0, first);
            }

        }
        return next;
    }

    /**
     * NONRECURSIVE SOLUTION
     */

    /**
     * Returns a smoothed Sequence<Integer> from a given
     * {@code Sequence<Integer>}.
     *
     * @param s1
     *            the sequence to smooth
     * @requires |s1| >= 1
     * @return the resulting sequence
     * @ensures <pre>
     * |s2| = |s1| - 1  and
     *  for all i, j: integer, a, b: string of integer
     *      where (s1 = a * <i> * <j> * b)
     *    (there exists c, d: string of integer
     *       (|c| = |a|  and
     *        {@code Sequence<Integer} = c * <(i+j)/2> * d))
     * </pre>
     */
    //    public static Sequence<Integer> smooth(Sequence<Integer> s1) {
    //        assert s1 != null : "Violation of: s1 is not null";
    //        assert s1.length() >= 1 : "|s1| >= 1";
    //
    //        // TODO - fill in body
    //
    //        Sequence<Integer> replace = new Sequence1L<>();
    //        int index = 0;
    //        while (index < s1.length() - 1) {
    //            int first = s1.entry(index);
    //            int second = s1.entry(index + 1);
    //            if (first != second) {
    //                int total = first + second;
    //                total /= 2;
    //                replace.add(replace.length(), total);
    //            } else {
    //                replace.add(replace.length(), first);
    //            }
    //
    //            index++;
    //        }
    //        return replace;
    //    }

}
