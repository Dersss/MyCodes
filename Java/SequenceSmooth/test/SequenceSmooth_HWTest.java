import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.sequence.Sequence;
import components.sequence.Sequence1L;

/**
 * Sample JUnit test fixture for SequenceSmooth.
 *
 * @author Andrew Schneider
 *
 */
public final class SequenceSmooth_HWTest {

    /**
     * Constructs and returns a sequence of the integers provided as arguments.
     *
     * @param args
     *            0 or more integer arguments
     * @return the sequence of the given arguments
     * @ensures createFromArgs= [the sequence of integers in args]
     */
    private Sequence<Integer> createFromArgs(Integer... args) {
        Sequence<Integer> s = new Sequence1L<Integer>();
        for (Integer x : args) {
            s.add(s.length(), x);
        }
        return s;
    }

    /**
     * Test smooth with s1 = <2, 4, 6> and s2 = <-5, 12>.
     */
    @Test
    public void test1() {
        /*
         * Set up variables and call method under test
         */
        Sequence<Integer> seq1 = this.createFromArgs(2, 4, 6);
        Sequence<Integer> expectedSeq1 = this.createFromArgs(2, 4, 6);
        Sequence<Integer> seq2 = this.createFromArgs(-5, 12);
        Sequence<Integer> expectedSeq2 = this.createFromArgs(3, 5);
        Sequence<Integer> finalEXP = SequenceSmooth_HW.smooth(seq1);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedSeq2, finalEXP);
        assertEquals(expectedSeq1, seq1);
    }

    /**
     * Test smooth with s1 = <7> and s2 = <13, 17, 11>.
     */
    @Test
    public void test2() {
        /*
         * Set up variables and call method under test
         */
        Sequence<Integer> seq1 = this.createFromArgs(7);
        Sequence<Integer> expectedSeq1 = this.createFromArgs(7);
        Sequence<Integer> seq2 = this.createFromArgs(13, 17, 11);
        Sequence<Integer> expectedSeq2 = this.createFromArgs();
        Sequence<Integer> finalEXP = SequenceSmooth_HW.smooth(seq1);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedSeq2, finalEXP);
        assertEquals(expectedSeq1, seq1);
    }

    /**
     * Test smooth with s1 = <2> and s2 = < >.
     */
    @Test
    public void test3() {
        /*
         * Set up variables and call method under test
         */
        Sequence<Integer> seq1 = this.createFromArgs(2);
        Sequence<Integer> expectedSeq1 = this.createFromArgs(2);
        Sequence<Integer> seq2 = this.createFromArgs();
        Sequence<Integer> expectedSeq2 = this.createFromArgs();
        Sequence<Integer> finalEXP = SequenceSmooth_HW.smooth(seq1);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedSeq2, finalEXP);
        assertEquals(expectedSeq1, seq1);
    }

    /**
     * Test smooth with s1 = <7, 8, 9> and s2 = <2, 3, 4>.
     */
    @Test
    public void test4() {
        /*
         * Set up variables and call method under test
         */
        Sequence<Integer> seq1 = this.createFromArgs(7, 8, 9);
        Sequence<Integer> expectedSeq1 = this.createFromArgs(7, 8, 9);
        Sequence<Integer> seq2 = this.createFromArgs(2, 3, 4);
        Sequence<Integer> expectedSeq2 = this.createFromArgs(7, 8);
        Sequence<Integer> finalEXP = SequenceSmooth_HW.smooth(seq1);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedSeq2, finalEXP);
        assertEquals(expectedSeq1, seq1);
    }

    /**
     * Test smooth with s1 = <11, 21, 15> and s2 = <2, 3>.
     */
    @Test
    public void test5() {
        /*
         * Set up variables and call method under test
         */
        Sequence<Integer> seq1 = this.createFromArgs(11, 21, 15);
        Sequence<Integer> expectedSeq1 = this.createFromArgs(11, 21, 15);
        Sequence<Integer> seq2 = this.createFromArgs(2, 3);
        Sequence<Integer> expectedSeq2 = this.createFromArgs(16, 18);
        Sequence<Integer> finalEXP = SequenceSmooth_HW.smooth(seq1);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedSeq2, finalEXP);
        assertEquals(expectedSeq1, seq1);
    }

    /**
     * Test smooth with s1 = <2, 3> and s2 = < >.
     */
    @Test
    public void test6() {
        /*
         * Set up variables and call method under test
         */
        Sequence<Integer> seq1 = this.createFromArgs(2, 3);
        Sequence<Integer> expectedSeq1 = this.createFromArgs(2, 3);
        Sequence<Integer> seq2 = this.createFromArgs();
        Sequence<Integer> expectedSeq2 = this.createFromArgs(2);
        Sequence<Integer> finalEXP = SequenceSmooth_HW.smooth(seq1);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedSeq2, finalEXP);
        assertEquals(expectedSeq1, seq1);
    }

    /**
     * Test smooth with s1 = <7> and s2 = <13, 17, 11>.
     */
    @Test
    public void test7() {
        /*
         * Set up variables and call method under test
         */
        Sequence<Integer> seq1 = this.createFromArgs(7);
        Sequence<Integer> expectedSeq1 = this.createFromArgs(7);
        Sequence<Integer> seq2 = this.createFromArgs(13, 17, 11);
        Sequence<Integer> expectedSeq2 = this.createFromArgs();
        Sequence<Integer> finalEXP = SequenceSmooth_HW.smooth(seq1);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedSeq2, finalEXP);
        assertEquals(expectedSeq1, seq1);
    }

    /**
     * Test smooth with s1 = <1073741825, 1073741825> and s2 = < >.
     */
    @Test
    public void test8() {
        /*
         * Set up variables and call method under test
         */
        Sequence<Integer> seq1 = this.createFromArgs(1073741825, 1073741825);
        Sequence<Integer> expectedSeq1 = this.createFromArgs(1073741825,
                1073741825);
        Sequence<Integer> seq2 = this.createFromArgs();
        Sequence<Integer> expectedSeq2 = this.createFromArgs(1073741825);
        Sequence<Integer> finalEXP = SequenceSmooth_HW.smooth(seq1);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedSeq2, finalEXP);
        assertEquals(expectedSeq1, seq1);
    }

    /**
     * Test smooth with s1 = <134, -134> and s2 = < >.
     */
    @Test
    public void test9() {
        /*
         * Set up variables and call method under test
         */
        Sequence<Integer> seq1 = this.createFromArgs(134, -134);
        Sequence<Integer> expectedSeq1 = this.createFromArgs(134, -134);
        Sequence<Integer> seq2 = this.createFromArgs();
        Sequence<Integer> expectedSeq2 = this.createFromArgs(0);
        Sequence<Integer> finalEXP = SequenceSmooth_HW.smooth(seq1);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedSeq2, finalEXP);
        assertEquals(expectedSeq1, seq1);
    }

    /**
     * Test smooth with s1 = <134, -134> and s2 = < >.
     */
    @Test
    public void test10() {
        /*
         * Set up variables and call method under test
         */
        Sequence<Integer> seq1 = this.createFromArgs(1073741825, -1073741825);
        Sequence<Integer> expectedSeq1 = this.createFromArgs(1073741825,
                -1073741825);
        Sequence<Integer> seq2 = this.createFromArgs();
        Sequence<Integer> expectedSeq2 = this.createFromArgs(0);
        Sequence<Integer> finalEXP = SequenceSmooth_HW.smooth(seq1);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedSeq2, finalEXP);
        assertEquals(expectedSeq1, seq1);
    }

    /**
     * Test smooth with s1 = <134, -134> and s2 = < >.
     */
    @Test
    public void test11() {
        /*
         * Set up variables and call method under test
         */
        Sequence<Integer> seq1 = this.createFromArgs(-1073741823, 1073741824);
        Sequence<Integer> expectedSeq1 = this.createFromArgs(-1073741823,
                1073741824);
        Sequence<Integer> seq2 = this.createFromArgs();
        Sequence<Integer> expectedSeq2 = this.createFromArgs(0);
        Sequence<Integer> finalEXP = SequenceSmooth_HW.smooth(seq1);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedSeq2, finalEXP);
        assertEquals(expectedSeq1, seq1);
    }
}
