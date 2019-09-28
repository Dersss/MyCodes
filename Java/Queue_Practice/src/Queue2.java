import java.util.Comparator;

import components.queue.Queue;
import components.queue.Queue1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Program to sort lines from an input file in lexicographic order by using
 * selection sort on {@code Queue<String>}.
 *
 * @author Paolo Bucci
 */
public final class Queue2 {

    /**
     * No-argument constructor--private to prevent instantiation.
     */
    private Queue2() {
        // no code needed here
    }

    /**
     * Reports an array of two {@code int}s with the smallest and the largest
     * integer in the given {@code Queue<Integer>}.
     *
     * @param q
     *            the queue of integer
     * @return an array of two {@code int}s with the smallest and the largest
     *         integer in the given queue
     * @requires q /= empty_string
     * @ensures <pre>
     * { minAndMax[0], minAndMax[1] } is subset of entries(q) and
     *  for all x: integer
     *      where (x in in entries(q))
     *    (minAndMax[0] <= x <= minAndMax[1])
     * </pre>
     */
    private static int[] minAndMax(Queue<Integer> q) {
        int[] arr = new int[2];
        int min = 0;
        int max = 0;
        int i = 0;
        while (i < q.length()) {
            if (i == 0) {
                int temp = q.dequeue();
                min = temp;
                max = temp;
                q.enqueue(temp);
            } else {
                int temp2 = q.dequeue();
                if (temp2 < min) {
                    min = temp2;
                }
                if (temp2 > max) {
                    max = temp2;
                }
                q.enqueue(temp2);
            }
            i++;
        }
        arr[0] = min;
        arr[1] = max;
        return arr;
    }

    /**
     * Reports the smallest integer in the given {@code Queue<Integer>}.
     *
     * @param q
     *            the queue of integer
     * @return the smallest integer in the given queue
     * @requires q /= empty_string
     * @ensures <pre>
     * min is in entries(q) and
     *  for all x: integer
     *      where (x is in entries(q))
     *    (min <= x)
     * </pre>
     */
    private static int min(Queue<Integer> q) {
        int low = q.front();
        int i = 0;
        while (i < q.length()) {
            int temp = q.dequeue();

            if (temp < low) {
                low = temp;
            }
            q.enqueue(temp);
            i++;
        }
        return low;
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
     * perms(q * <removeMin>, #q)  and
     *  for all x: string of character
     *      where (x is in entries (q))
     *    ([relation computed by order.compare method](removeMin, x))
     * </pre>
     */
    private static String removeMin(Queue<String> q, Comparator<String> order) {
        String min = "";
        Queue<String> temp = new Queue1L<>();
        for (String x : q) {
            String hold = q.dequeue();
            if (min.equals("")) {
                min = hold;
            } else {
                if (order.compare(min, hold) >= 0) {
                    temp.enqueue(min);
                    min = hold;
                } else {
                    temp.enqueue(hold);
                }
            }
        }
        q.transferFrom(temp);
        return min;
    }

    public void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        Queue<Integer> q1 = new Queue1L<>();
        int i = 0;
        while (i < 8) {
            q1.enqueue(i);
            i++;
        }

        System.out.println("q1: " + q1);
        int min = min(q1);
        int min2 = removeMin(q1);
        in.close();
        out.close();
    }

}
