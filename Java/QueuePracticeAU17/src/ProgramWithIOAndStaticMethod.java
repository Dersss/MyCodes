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
public final class ProgramWithIOAndStaticMethod {

    private static int TEN = 10;

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private ProgramWithIOAndStaticMethod() {
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

            }
        }
        arr[0] = min;
        arr[1] = max;
        return arr;
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
         * Put your main program code here; it may call myMethod as shown
         */
        Queue<Integer> q1 = new Queue1L<>();
        for (int i = 10; i > 0; i--) {
            int add = i * 2;
            q1.enqueue(add);
        }
        out.println(q1);
        int[] minMax = new int[2];
        minMax = minAndMax(q1);
        out.println(q1);
        out.println(minMax[0] + "    " + minMax[1]);
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
