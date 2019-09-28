import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

public class StringReassemblyTest_OLD {

    @Test
    public void combination_1() {
        String s1 = "how firm thy friend";
        String s2 = "friendship...OHIIIOOOO";
        int overlap = 6;

        String result = StringReassembly_OLD.combination(s1, s2, overlap);
        assertEquals("how firm thy friendship...OHIIIOOOO", result);
    }

    @Test
    public void combination_2() {
        String s1 = "Feet don't";
        String s2 = "don't fail me now";
        int overlap = 5;

        String result = StringReassembly_OLD.combination(s1, s2, overlap);
        assertEquals("Feet don't fail me now", result);
    }

    @Test
    public void combination_3() {
        String s1 = "Love the life you live";
        String s2 = "live, live the life you love";
        int overlap = 4;

        String result = StringReassembly_OLD.combination(s1, s2, overlap);
        assertEquals("Love the life you live, live the life you love", result);
    }

    @Test
    public void combination_4() {
        String s1 = "Summer is so close yet";
        String s2 = "t so far";
        int overlap = 1;

        String result = StringReassembly_OLD.combination(s1, s2, overlap);
        assertEquals("Summer is so close yet so far", result);
    }

    @Test
    public void addToSetAvoidingSubstrings_1() {
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

        StringReassembly_OLD.addToSetAvoidingSubstrings(temp, send);
        assertEquals(temp2, temp);
    }

    @Test
    public void addToSetAvoidingSubstrings_2() {
        Set<String> temp = new Set1L<>();
        Set<String> temp2 = new Set1L<>();
        String s1 = "first";
        String s2 = "second";
        temp.add(s1);
        temp2.add(s1);
        temp2.add(s2);

        StringReassembly_OLD.addToSetAvoidingSubstrings(temp, s2);
        assertEquals(temp2, temp);
    }

    @Test
    public void addToSetAvoidingSubstrings_3() {
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

        StringReassembly_OLD.addToSetAvoidingSubstrings(temp, send);
        assertEquals(temp2, temp);
    }

    @Test
    public void addToSetAvoidingSubstrings_4() {
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

        StringReassembly_OLD.addToSetAvoidingSubstrings(temp, send);
        assertEquals(temp2, temp);
    }

    @Test
    public void addToSetAvoidingSubstrings_5() {
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

        StringReassembly_OLD.addToSetAvoidingSubstrings(temp, send);
        assertEquals(temp2, temp);
    }

    @Test
    public void linesFromInput_1() {
        String outFile = "testWriteOut.txt";
        Set<String> hold = new Set1L<>();
        hold.add("Bucks -- Beat");
        hold.add("Go Bucks");
        hold.add("Beat Mich");
        hold.add("Michigan~");
        hold.add("o Bucks -- B");
        String file = "osuChant.txt";
        SimpleReader in = new SimpleReader1L(file);
        Set<String> money = StringReassembly_OLD.linesFromInput(in);
        assertEquals(money, hold);
    }

    @Test
    public void printWithLineSeperators_1() {

        SimpleWriter out = new SimpleWriter1L();
        String before = "go buckeyes!~";
        String after = before.replaceAll("~", System.lineSeparator());

        StringReassembly_OLD.printWithLineSeparators(before, out);
        assertEquals(before, after);
    }

}
