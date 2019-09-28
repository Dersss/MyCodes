import java.util.Comparator;

import components.queue.Queue;
import components.queue.Queue1L;

/**
 * Layered implementations of secondary method {@code sort} for
 * {@code Queue<String>}.
 */
public final class Queue1LSort1 extends Queue1L<String> {

    /**
     * No-argument constructor.
     */
    public Queue1LSort1() {
        super();
    }

    /**
     * Removes and returns the minimum value from {@code q} according to the
     * ordering provided by the {@code compare} method from {@code order}.
     *
     * @param q
     *            the queue
     * @param order
     *            ordering by which to compare entries
     * @return the minimum value from {@code q}
     * @updates q
     * @requires <pre>
     * q /= empty_string  and
     *  [the relation computed by order.compare is a total preorder]
     * </pre>
     * @ensures <pre>
     * (q * <removeMin>) is permutation of #q  and
     *  for all x: string of character
     *      where (x is in entries (q))
     *    ([relation computed by order.compare method](removeMin, x))
     * </pre>
     */
    private static String removeMin(Queue<String> q, Comparator<String> order) {
        assert q != null : "Violation of: q is not null";
        assert order != null : "Violation of: order is not null";

        // TODO - fill in body

        boolean end = false;
        int counter = 0;
        Queue<String> new1 = new Queue1L<>();
        String temp = q.dequeue();
        while (q.length() > 0) {
            String comp = q.dequeue();
            int comp0 = order.compare(temp, comp);
            if (comp0 > 0) {
                new1.enqueue(temp);
                temp = comp;
            }
            if (comp0 < 0) {
                new1.enqueue(comp);
            }
        }
        q.transferFrom(new1);

        return temp;

    }

    @Override
    public void sort(Comparator<String> order) {
        assert order != null : "Violation of: order is not null";

        // TODO - fill in body
        Queue<String> qStr = new Queue1L<>();
        qStr.transferFrom(this);
        String min = removeMin(qStr, order);
        this.transferFrom(qStr);
        qStr.clear();
    }

}
