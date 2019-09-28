import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Tests for StringAssemblyFromFragments methods.
 *
 * @author andrewschneider schneider.911
 *
 */
public class StringReassemblyTest {

    /**
     * Test for combination()
     */

    @Test
    public void testCombination_1() {
        String s1 = "how firm thy friend";
        String s2 = "friendship...OHIIIOOOO";
        int overlap = 6;
        String result = StringReassembly.combination(s1, s2, overlap);
        assertEquals("how firm thy friendship...OHIIIOOOO", result);
    }

    @Test
    public void testCombination_2() {
        String s1 = "The Ohio State Computer";
        String s2 = "ate Computer Science and Engineering";
        int overlap = 12;
        String result = StringReassembly.combination(s1, s2, overlap);
        assertEquals("The Ohio State Computer Science and Engineering", result);
    }

    @Test
    public void testCombination_3() {
        String s1 = "Carmen Ohio";
        String s2 = "Ohio";
        int overlap = 4;
        String result = StringReassembly.combination(s1, s2, overlap);
        assertEquals("Carmen Ohio", result);
    }

    @Test
    public void testCombination_4() {
        String s1 = "racecar";
        String s2 = "r driver";
        int overlap = 1;
        String result = StringReassembly.combination(s1, s2, overlap);
        assertEquals("racecar driver", result);
    }

    /**
     * Tests for addToSetAvoidingSubstring()
     */

    @Test
    public void testAddToSetAvoidingSubstrings_1() {
        Set<String> temp = new Set1L<>();
        Set<String> temp2 = new Set1L<>();
        String s1 = "alpha";
        String s2 = "bravo";
        String send = "charlie";
        temp.add(s1);
        temp.add(s2);
        temp2.add(s1);
        temp2.add(s2);
        temp2.add(send);

        StringReassembly.addToSetAvoidingSubstrings(temp, send);
        assertEquals(temp2, temp);
    }

    @Test
    public void testAddToSetAvoidingSubstrings_2() {
        Set<String> temp = new Set1L<>();
        Set<String> temp2 = new Set1L<>();
        String s1 = "alpha";
        String s2 = "bravo";
        String s3 = "charlie";
        String send = "avo";
        temp.add(s1);
        temp.add(s2);
        temp.add(s3);
        temp2.add(s1);
        temp2.add(s2);
        temp2.add(s3);

        StringReassembly.addToSetAvoidingSubstrings(temp, send);
        assertEquals(temp2, temp);
    }

    @Test
    public void testAddToSetAvoidingSubstrings_3() {
        Set<String> temp = new Set1L<>();
        Set<String> temp2 = new Set1L<>();
        String s1 = "Software Engineering One";
        temp2.add(s1);

        StringReassembly.addToSetAvoidingSubstrings(temp, s1);
        assertEquals(temp2, temp);
    }

    @Test
    public void testAddToSetAvoidingSubstrings_4() {
        Set<String> temp = new Set1L<>();
        Set<String> temp2 = new Set1L<>();
        String s1 = "Software";
        String s2 = "Engine";
        String s3 = "One";
        String send = "Engineering";
        temp.add(s1);
        temp.add(s2);
        temp.add(s3);
        temp2.add(s1);
        temp2.add(s3);
        temp2.add(send);

        StringReassembly.addToSetAvoidingSubstrings(temp, send);
        assertEquals(temp2, temp);
    }

    @Test
    public void testAddToSetAvoidingSubstrings_5() {
        Set<String> temp = new Set1L<>();
        Set<String> temp2 = new Set1L<>();
        String s1 = "foxtrot";
        String s2 = "uniform";
        String s3 = "charlie";
        String send = "charlie kilo";
        temp.add(s1);
        temp.add(s2);
        temp.add(s3);
        temp2.add(s1);
        temp2.add(s2);
        temp2.add(send);

        StringReassembly.addToSetAvoidingSubstrings(temp, send);
        assertEquals(temp2, temp);
    }

    @Test
    public void testAddToSetAvoidingSubstrings_6() {
        Set<String> temp = new Set1L<>();
        Set<String> temp2 = new Set1L<>();

        String s1 = "This is a bigger problem";
        String s2 = " that we can handle easily";
        String test = "bigger";

        temp.add(s1);
        temp.add(s2);
        temp2.add(s1);
        temp2.add(s2);
        StringReassembly.addToSetAvoidingSubstrings(temp, test);
        assertEquals(temp2, temp);
    }

    @Test
    public void testAddToSetAvoidingSubstrings_7() {
        Set<String> temp = new Set1L<>();
        Set<String> temp2 = new Set1L<>();

        String s1 = "This is a bigger problem";
        String s2 = " that we can handle easily";
        String test = "This is a bigger problem that";

        temp.add(s1);
        temp.add(s2);
        temp2.add(test);
        temp2.add(s2);
        StringReassembly.addToSetAvoidingSubstrings(temp, test);
        assertEquals(temp2, temp);
    }

    /**
     * Tests for printWithLineSeperators()
     */

    @Test
    public void testPrintWithLineSeperators_1() {
        SimpleWriter out = new SimpleWriter1L("testSeperators1_run.txt");
        SimpleReader in = new SimpleReader1L("testSeperators1.txt");
        while (!in.atEOS()) {
            String test = in.nextLine();
            StringReassembly.printWithLineSeparators(test, out);
        }
        SimpleReader inKey = new SimpleReader1L("testSeperators1_key.txt");
        SimpleReader inRun = new SimpleReader1L("testSeperators1_run.txt");
        while (!inKey.atEOS()) {
            String s1 = inKey.nextLine();
            String s2 = inRun.nextLine();
            assertEquals(s1, s2);
        }
        inKey.close();
        inRun.close();
        out.close();
        in.close();
    }

    @Test
    public void testPrintWithLineSeperators_2() {
        SimpleWriter out = new SimpleWriter1L("testSeperators2_run.txt");
        SimpleReader in = new SimpleReader1L("testSeperators2.txt");
        while (!in.atEOS()) {
            String test = in.nextLine();
            StringReassembly.printWithLineSeparators(test, out);
        }
        SimpleReader inKey = new SimpleReader1L("testSeperators2_key.txt");
        SimpleReader inRun = new SimpleReader1L("testSeperators2_run.txt");
        while (!inKey.atEOS()) {
            String s1 = inKey.nextLine();
            String s2 = inRun.nextLine();
            assertEquals(s2, s1);
        }
        inKey.close();
        inRun.close();
        out.close();
        in.close();
    }

    /**
     * Tests for linesFromInput()
     */

    @Test
    public void testLinesFromInput_1() {
        SimpleReader in = new SimpleReader1L("testLines.txt");
        Set<String> test = new Set1L<>();
        Set<String> comp = new Set1L<>();
        String line1 = "This is my test file.";
        String line2 = "Super awesome right?";
        String line3 = "Have a good day!";
        test.add(line1);
        test.add(line2);
        test.add(line3);
        comp = StringReassembly.linesFromInput(in);
        assertEquals(test, comp);
        in.close();
    }

    @Test
    public void testLinesFromInput_2() {
        SimpleReader in = new SimpleReader1L("testLines2.txt");
        Set<String> test = new Set1L<>();
        Set<String> comp = new Set1L<>();
        String line1 = "This is my test file.";
        String line2 = "Super awesome right?";
        String line3 = "Have a good day!";
        String line4 = "Here is another.";
        String line5 = "And another.";
        test.add(line1);
        test.add(line2);
        test.add(line3);
        test.add(line4);
        test.add(line5);
        comp = StringReassembly.linesFromInput(in);
        assertEquals(test, comp);
        in.close();
    }

    @Test
    public void testLinesFromInput_3() {
        SimpleReader in = new SimpleReader1L("testLines3.txt");
        Set<String> test = new Set1L<>();
        Set<String> comp = new Set1L<>();
        comp = StringReassembly.linesFromInput(in);
        assertEquals(test, comp);
        in.close();
    }

}
