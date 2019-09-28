import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

public class FunTimes {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        char testing = 'a';
        int totalA = 0;
        boolean end = false;
        while (!end) {
            out.println("Enter line: ");
            String next = in.nextLine();
            if (next.isEmpty()) {
                end = true;
            } else {
                int size = next.length();
                for (int i = 0; i < size; i++) {
                    if (next.charAt(i) == testing) {
                        totalA++;
                    }
                }
                out.println("Total a's: " + totalA);
                totalA = 0;
            }

        }

        out.close();
        in.close();
    }

}
