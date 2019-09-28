import components.queue.Queue;
import components.queue.Queue1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class ProgramSkeleton {

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
        in.close();
        out.close();
    }
}
