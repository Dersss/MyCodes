import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import components.map.Map;
import components.map.Map1L;
import components.queue.Queue;
import components.queue.Queue1L;
import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;

public class GlossaryTest {

    /**
     * Tests for Glossary.nextWordOrSeparator().
     */
    @Test
    public void testNextWordOrSeparator_1() {
        String test = "Andrew Schneider";
        final String separatorStr = " ,";
        Set<Character> separators = new Set1L<>();
        Glossary.generateElements(separatorStr, separators);
        int position = 0;
        String result = Glossary.nextWordOrSeparator(test, position,
                separators);
        assertEquals("Andrew", result);
    }

    @Test
    public void testNextWordOrSeparator_2() {
        String test = "Andrew Schneider";
        final String separatorStr = " ,";
        Set<Character> separators = new Set1L<>();
        Glossary.generateElements(separatorStr, separators);
        int position = 6;
        String result = Glossary.nextWordOrSeparator(test, position,
                separators);
        assertEquals(" ", result);
    }

    @Test
    public void testNextWordOrSeparator_3() {
        String test = "Andrew Schneider";
        final String separatorStr = " ,";
        Set<Character> separators = new Set1L<>();
        Glossary.generateElements(separatorStr, separators);
        int position = 7;
        String result = Glossary.nextWordOrSeparator(test, position,
                separators);
        assertEquals("Schneider", result);
    }

    @Test
    public void testNextWordOrSeparator_4() {
        String test = "Computer Science and Engineering";
        final String separatorStr = " ,";
        Set<Character> separators = new Set1L<>();
        Glossary.generateElements(separatorStr, separators);
        int position = 0;
        String result = Glossary.nextWordOrSeparator(test, position,
                separators);
        assertEquals("Computer", result);
    }

    @Test
    public void testNextWordOrSeparator_5() {
        String test = "Computer- Science and Engineering";
        final String separatorStr = " ,-";
        Set<Character> separators = new Set1L<>();
        Glossary.generateElements(separatorStr, separators);
        int position = 8;
        String result = Glossary.nextWordOrSeparator(test, position,
                separators);
        assertEquals("- ", result);
    }

    @Test
    public void testNextWordOrSeparator_6() {
        String test = "            ,,,,,a";
        final String separatorStr = " ,-";
        Set<Character> separators = new Set1L<>();
        Glossary.generateElements(separatorStr, separators);
        int position = 0;
        String result = Glossary.nextWordOrSeparator(test, position,
                separators);
        assertEquals("            ,,,,,", result);
    }

    @Test
    public void testNextWordOrSeparator_7() {
        String test = "            ,,,,,a";
        final String separatorStr = " ,-";
        Set<Character> separators = new Set1L<>();
        Glossary.generateElements(separatorStr, separators);
        int position = 17;
        String result = Glossary.nextWordOrSeparator(test, position,
                separators);
        assertEquals("a", result);
    }

    /**
     * Tests for Glossary.generateElements().
     */

    @Test
    public void testGenerateElements_1() {
        String first = " , /t ";
        Set<Character> hold = new Set1L<>();
        Glossary.generateElements(first, hold);
        assertTrue(hold.size() == 4);
        assertTrue(hold.contains(' '));
        assertTrue(hold.contains(','));
        assertTrue(hold.contains('/'));
        assertTrue(hold.contains('t'));
    }

    @Test
    public void testGenerateElements_2() {
        String first = " ,,,ttt///~ /t ";
        Set<Character> hold = new Set1L<>();
        Glossary.generateElements(first, hold);
        assertTrue(hold.size() == 5);
        assertTrue(hold.contains(' '));
        assertTrue(hold.contains(','));
        assertTrue(hold.contains('/'));
        assertTrue(hold.contains('t'));
        assertTrue(hold.contains('~'));
    }

    @Test
    public void testGenerateElements_3() {
        String first = "aaabbbcccdddeeefffggg";
        Set<Character> hold = new Set1L<>();
        Glossary.generateElements(first, hold);
        assertTrue(hold.size() == 7);
        assertTrue(hold.contains('a'));
        assertTrue(hold.contains('b'));
        assertTrue(hold.contains('c'));
        assertTrue(hold.contains('d'));
        assertTrue(hold.contains('e'));
        assertTrue(hold.contains('f'));
        assertTrue(hold.contains('g'));
    }

    /**
     * Tests for Glossary.sortAndUpdate().
     */

    @Test
    public void testSortAndUpdate_1() {
        Queue<String> original = new Queue1L<>();
        Queue<String> expected = new Queue1L<>();
        String a = "apple";
        String b = "banana";
        String c = "carrot";
        String d = "dinner";
        String e = "Apple";
        original.enqueue(c);
        original.enqueue(a);
        original.enqueue(d);
        original.enqueue(b);
        original.enqueue(e);

        expected.enqueue(e);
        expected.enqueue(a);
        expected.enqueue(b);
        expected.enqueue(c);
        expected.enqueue(d);
        Glossary.sortAndUpdate(original);
        assertEquals(original, expected);
    }

    @Test
    public void testSortAndUpdate_2() {
        Queue<String> original = new Queue1L<>();
        Queue<String> expected = new Queue1L<>();
        String a = "apple";
        String b = "banana";
        String c = "carrot";
        String d = "dinner";
        original.enqueue(d);
        original.enqueue(a);
        original.enqueue(b);
        original.enqueue(c);

        expected.enqueue(a);
        expected.enqueue(b);
        expected.enqueue(c);
        expected.enqueue(d);
        Glossary.sortAndUpdate(original);
        assertEquals(original, expected);
    }

    @Test
    public void testSortAndUpdate_3() {
        Queue<String> original = new Queue1L<>();
        Queue<String> expected = new Queue1L<>();
        String a = "apple";
        String b = "banana";
        String b2 = "bananas";
        String c = "carrot";
        String d = "dinner";
        original.enqueue(d);
        original.enqueue(a);
        original.enqueue(b);
        original.enqueue(c);
        original.enqueue(b2);

        expected.enqueue(a);
        expected.enqueue(b);
        expected.enqueue(b2);
        expected.enqueue(c);
        expected.enqueue(d);
        Glossary.sortAndUpdate(original);
        assertEquals(original, expected);
    }

    @Test
    public void testSortAndUpdate_4() {
        Queue<String> original = new Queue1L<>();
        Queue<String> expected = new Queue1L<>();
        String a = "apple";
        String b = "bamanas";
        String b2 = "bananas";
        String c = "carrot";
        String d = "dinner";
        original.enqueue(d);
        original.enqueue(a);
        original.enqueue(b);
        original.enqueue(c);
        original.enqueue(b2);

        expected.enqueue(a);
        expected.enqueue(b);
        expected.enqueue(b2);
        expected.enqueue(c);
        expected.enqueue(d);
        Glossary.sortAndUpdate(original);
        assertEquals(original, expected);
    }

    /**
     * Test for Glossary.processTermsIntoMapandQueue().
     *
     */

    @Test
    public void testProcessTermsIntoMapAndQueue_1() {
        Map<String, String> expected = new Map1L<>();
        Queue<String> expectedQ = new Queue1L<>();

        Map<String, String> actual = new Map1L<>();
        Queue<String> actualQ = new Queue1L<>();
        String[] terms = { "meaning", "term", "word", "definition", "glossary",
                "language", "book" };
        String[] definitions = {
                "something that one wishes to convey, especially by language",
                "a word whose definition is in a glossary",
                "a string of characters in a language, which has at least one character",
                "a sequence of words that gives meaning to a term",
                "a list of difficult or specialized terms, with their definitions, usually near the end of a book",
                "a set of strings of characters, each of which has meaning",
                "a printed or written literary work" };
        int i = 0;
        while (i < terms.length) {
            expected.add(terms[i], definitions[i]);
            expectedQ.enqueue(terms[i]);
            i++;
        }

        String testFile = "terms.txt";
        Glossary.processTermsIntoMapAndQueue(testFile, actual, actualQ);

        assertEquals(expected, actual);
        assertEquals(expectedQ, actualQ);
    }

    @Test
    public void testProcessTermsIntoMapAndQueue_2() {
        Map<String, String> expected = new Map1L<>();
        Queue<String> expectedQ = new Queue1L<>();

        Map<String, String> actual = new Map1L<>();
        Queue<String> actualQ = new Queue1L<>();
        String[] terms = { "alpha", "bravo", "charlie", "delta", "echo",
                "foxtrot", "golf", "hotel" };
        String[] definitions = { "phonetic alphabet term before bravo.",
                "phonetic alphabet term after alpha and before charlie.",
                "phonetic alphabet term after bravo and before delta.",
                "phonetic alphabet term after charlie and before echo.",
                "phonetic alphabet term after delta and before foxtrot.",
                "phonetic alphabet term after echo and before golf.",
                "phonetic alphabet term after foxtrot and before hotel.",
                "phonetic alphabet term after golf." };
        int i = 0;
        while (i < terms.length) {
            expected.add(terms[i], definitions[i]);
            expectedQ.enqueue(terms[i]);
            i++;
        }

        String testFile = "terms3.txt";
        Glossary.processTermsIntoMapAndQueue(testFile, actual, actualQ);

        assertEquals(expected, actual);
        assertEquals(expectedQ, actualQ);
    }

    /**
     * Tests for the creation of Top Level Index that also creates sub pages
     * within that method for each term.
     *
     * Because in Glossary.java sub pages are created in the
     * Glossary.createTopIndex() method, this test checks both
     */

    @Test
    public void testCreateTopIndex_1() {
        Queue<String> terms = new Queue1L<>();
        Map<String, String> termMap = new Map1L<>();
        String folder = "outTest";
        String term1 = "computer";
        String def1 = "what i am coding on";
        String term2 = "mac";
        String def2 = "main competitior of a computer";
        terms.enqueue(term1);
        terms.enqueue(term2);
        termMap.add(term1, def1);
        termMap.add(term2, def2);
        Glossary.createTopIndex(terms, folder, termMap);

        SimpleReader in = new SimpleReader1L("outTest/index.html");
        SimpleReader inEXP = new SimpleReader1L("outTest/indexEXP.html");
        SimpleReader subIn = new SimpleReader1L("outTest/computer.html");
        SimpleReader subInEXP = new SimpleReader1L("outTest/computerEXP.html");
        SimpleReader subIn2 = new SimpleReader1L("outTest/mac.html");
        SimpleReader subIn2EXP = new SimpleReader1L("outTest/macEXP.html");

        while (!in.atEOS() && !inEXP.atEOS() && !subIn.atEOS()
                && !subInEXP.atEOS()) {
            String in1 = in.nextLine();
            String in2 = inEXP.nextLine();
            String in3 = subIn.nextLine();
            String in4 = subInEXP.nextLine();
            String in5 = subIn2.nextLine();
            String in6 = subIn2EXP.nextLine();

            assertEquals(in1, in2);
            assertEquals(in3, in4);
            assertEquals(in5, in6);
        }
        in.close();
        inEXP.close();
        subIn.close();
        subInEXP.close();
        subIn2.close();
        subIn2EXP.close();

    }

    @Test
    public void testCreateTopIndex_2() {
        Queue<String> terms = new Queue1L<>();
        Map<String, String> termMap = new Map1L<>();
        String[] termsArr = { "alpha", "bravo", "charlie", "delta", "echo",
                "foxtrot", "golf", "hotel" };
        String[] definitions = { "phonetic alphabet term before bravo.",
                "phonetic alphabet term after alpha and before charlie.",
                "phonetic alphabet term after bravo and before delta.",
                "phonetic alphabet term after charlie and before echo.",
                "phonetic alphabet term after delta and before foxtrot.",
                "phonetic alphabet term after echo and before golf.",
                "phonetic alphabet term after foxtrot and before hotel.",
                "phonetic alphabet term after golf." };

        int i = 0;
        while (i < termsArr.length) {
            terms.enqueue(termsArr[i]);
            termMap.add(termsArr[i], definitions[i]);
            i++;
        }
        String folder = "outTest2";

        Glossary.createTopIndex(terms, folder, termMap);

        SimpleReader in = new SimpleReader1L("outTest2/index.html");
        SimpleReader inEXP = new SimpleReader1L("outTest2/indexEXP.html");
        SimpleReader subIn = new SimpleReader1L("outTest2/alpha.html");
        SimpleReader subInEXP = new SimpleReader1L("outTest2/alphaEXP.html");
        SimpleReader subIn2 = new SimpleReader1L("outTest2/bravo.html");
        SimpleReader subIn2EXP = new SimpleReader1L("outTest2/bravoEXP.html");
        SimpleReader subIn3 = new SimpleReader1L("outTest2/charlie.html");
        SimpleReader subIn3EXP = new SimpleReader1L("outTest2/charlieEXP.html");
        SimpleReader subIn4 = new SimpleReader1L("outTest2/delta.html");
        SimpleReader subIn4EXP = new SimpleReader1L("outTest2/deltaEXP.html");
        SimpleReader subIn5 = new SimpleReader1L("outTest2/echo.html");
        SimpleReader subIn5EXP = new SimpleReader1L("outTest2/echo.html");
        SimpleReader subIn6 = new SimpleReader1L("outTest2/foxtrot.html");
        SimpleReader subIn6EXP = new SimpleReader1L("outTest2/foxtrotEXP.html");
        SimpleReader subIn7 = new SimpleReader1L("outTest2/golf.html");
        SimpleReader subIn7EXP = new SimpleReader1L("outTest2/golf.html");
        SimpleReader subIn8 = new SimpleReader1L("outTest2/hotel.html");
        SimpleReader subIn8EXP = new SimpleReader1L("outTest2/hotelEXP.html");

        while (!in.atEOS() && !inEXP.atEOS() && !subIn.atEOS()
                && !subInEXP.atEOS()) {
            String in1 = in.nextLine();
            String in2 = inEXP.nextLine();
            String in3 = subIn.nextLine();
            String in4 = subInEXP.nextLine();
            String in5 = subIn2.nextLine();
            String in6 = subIn2EXP.nextLine();
            String in7 = subIn3.nextLine();
            String in8 = subIn3EXP.nextLine();
            String in9 = subIn4.nextLine();
            String in10 = subIn4EXP.nextLine();
            String in11 = subIn5.nextLine();
            String in12 = subIn5EXP.nextLine();
            String in13 = subIn6.nextLine();
            String in14 = subIn6EXP.nextLine();
            String in15 = subIn7.nextLine();
            String in16 = subIn7EXP.nextLine();
            String in17 = subIn8.nextLine();
            String in18 = subIn8EXP.nextLine();

            assertEquals(in1, in2);
            assertEquals(in3, in4);
            assertEquals(in5, in6);
            assertEquals(in7, in8);
            assertEquals(in9, in10);
            assertEquals(in11, in12);
            assertEquals(in13, in14);
            assertEquals(in15, in16);
            assertEquals(in17, in18);
        }
        in.close();
        inEXP.close();
        subIn.close();
        subInEXP.close();
        subIn2.close();
        subIn2EXP.close();
        subIn3.close();
        subIn3EXP.close();
        subIn4.close();
        subIn4EXP.close();
        subIn5.close();
        subIn5EXP.close();
        subIn6.close();
        subIn6EXP.close();
        subIn7.close();
        subIn7EXP.close();
        subIn8.close();
        subIn8EXP.close();

    }

    @Test
    public void testCreateTopIndex_3() {
        Queue<String> terms = new Queue1L<>();
        Map<String, String> termMap = new Map1L<>();
        String[] termsArr = { "alpha", "bravo", "charlie", "delta", "echo",
                "foxtrot", "golf", "hotel", "Alpha", "Bravo", "Charlie",
                "Delta", "Echo", "Foxtrot", "Golf", "Hotel" };
        String[] definitions = { "phonetic alphabet term before bravo.",
                "phonetic alphabet term after alpha and before charlie.",
                "phonetic alphabet term after bravo and before delta.",
                "phonetic alphabet term after charlie and before echo.",
                "phonetic alphabet term after delta and before foxtrot.",
                "phonetic alphabet term after echo and before golf.",
                "phonetic alphabet term after foxtrot and before hotel.",
                "phonetic alphabet term after golf.",
                "phonetic alphabet term before Bravo.",
                "phonetic alphabet term after Alpha and before Charlie.",
                "phonetic alphabet term after Bravo and before Delta.",
                "phonetic alphabet term after Charlie and before Echo.",
                "phonetic alphabet term after Delta and before Foxtrot.",
                "phonetic alphabet term after Echo and before Golf.",
                "phonetic alphabet term after Foxtrot and before Hotel.",
                "phonetic alphabet term after Golf." };

        int i = 0;
        while (i < termsArr.length) {
            terms.enqueue(termsArr[i]);
            termMap.add(termsArr[i], definitions[i]);
            i++;
        }
        String folder = "outTest3";

        Glossary.createTopIndex(terms, folder, termMap);

        SimpleReader in = new SimpleReader1L("outTest3/index.html");
        SimpleReader inEXP = new SimpleReader1L("outTest3/indexEXP.html");
        SimpleReader subIn = new SimpleReader1L("outTest3/alpha.html");
        SimpleReader subInEXP = new SimpleReader1L("outTest3/alphaEXP.html");
        SimpleReader subIn2 = new SimpleReader1L("outTest3/bravo.html");
        SimpleReader subIn2EXP = new SimpleReader1L("outTest3/bravoEXP.html");
        SimpleReader subIn3 = new SimpleReader1L("outTest3/charlie.html");
        SimpleReader subIn3EXP = new SimpleReader1L("outTest3/charlieEXP.html");
        SimpleReader subIn4 = new SimpleReader1L("outTest3/delta.html");
        SimpleReader subIn4EXP = new SimpleReader1L("outTest3/deltaEXP.html");
        SimpleReader subIn5 = new SimpleReader1L("outTest3/echo.html");
        SimpleReader subIn5EXP = new SimpleReader1L("outTest3/echo.html");
        SimpleReader subIn6 = new SimpleReader1L("outTest3/foxtrot.html");
        SimpleReader subIn6EXP = new SimpleReader1L("outTest3/foxtrotEXP.html");
        SimpleReader subIn7 = new SimpleReader1L("outTest3/golf.html");
        SimpleReader subIn7EXP = new SimpleReader1L("outTest3/golf.html");
        SimpleReader subIn8 = new SimpleReader1L("outTest3/hotel.html");
        SimpleReader subIn8EXP = new SimpleReader1L("outTest3/hotelEXP.html");
        SimpleReader subIn9 = new SimpleReader1L("outTest3/AlphaCAP.html");
        SimpleReader subIn9EXP = new SimpleReader1L(
                "outTest3/AlphaCAP_EXP.html");
        SimpleReader subIn10 = new SimpleReader1L("outTest3/BravoCAP.html");
        SimpleReader subIn10EXP = new SimpleReader1L(
                "outTest3/BravoCAP_EXP.html");
        SimpleReader subIn11 = new SimpleReader1L("outTest3/CharlieCAP.html");
        SimpleReader subIn11EXP = new SimpleReader1L(
                "outTest3/CharlieCAP_EXP.html");
        SimpleReader subIn12 = new SimpleReader1L("outTest3/DeltaCAP.html");
        SimpleReader subIn12EXP = new SimpleReader1L(
                "outTest3/DeltaCAP_EXP.html");

        while (!in.atEOS() && !inEXP.atEOS() && !subIn.atEOS()
                && !subInEXP.atEOS()) {
            String in1 = in.nextLine();
            String in2 = inEXP.nextLine();
            String in3 = subIn.nextLine();
            String in4 = subInEXP.nextLine();
            String in5 = subIn2.nextLine();
            String in6 = subIn2EXP.nextLine();
            String in7 = subIn3.nextLine();
            String in8 = subIn3EXP.nextLine();
            String in9 = subIn4.nextLine();
            String in10 = subIn4EXP.nextLine();
            String in11 = subIn5.nextLine();
            String in12 = subIn5EXP.nextLine();
            String in13 = subIn6.nextLine();
            String in14 = subIn6EXP.nextLine();
            String in15 = subIn7.nextLine();
            String in16 = subIn7EXP.nextLine();
            String in17 = subIn8.nextLine();
            String in18 = subIn8EXP.nextLine();
            String in19 = subIn9.nextLine();
            String in20 = subIn9EXP.nextLine();
            String in21 = subIn10.nextLine();
            String in22 = subIn10EXP.nextLine();
            String in23 = subIn11.nextLine();
            String in24 = subIn11EXP.nextLine();
            String in25 = subIn12.nextLine();
            String in26 = subIn12EXP.nextLine();

            assertEquals(in1, in2);
            assertEquals(in3, in4);
            assertEquals(in5, in6);
            assertEquals(in7, in8);
            assertEquals(in9, in10);
            assertEquals(in11, in12);
            assertEquals(in13, in14);
            assertEquals(in15, in16);
            assertEquals(in17, in18);
            assertEquals(in19, in20);
            assertEquals(in21, in22);
            assertEquals(in23, in24);
            assertEquals(in25, in26);
        }
        in.close();
        inEXP.close();
        subIn.close();
        subInEXP.close();
        subIn2.close();
        subIn2EXP.close();
        subIn3.close();
        subIn3EXP.close();
        subIn4.close();
        subIn4EXP.close();
        subIn5.close();
        subIn5EXP.close();
        subIn6.close();
        subIn6EXP.close();
        subIn7.close();
        subIn7EXP.close();
        subIn8.close();
        subIn8EXP.close();
        subIn9.close();
        subIn9EXP.close();
        subIn10.close();
        subIn10EXP.close();
        subIn11.close();
        subIn11EXP.close();
        subIn12.close();
        subIn12EXP.close();

    }

    @Test
    public void testCreateTopIndex_4() {
        Queue<String> terms = new Queue1L<>();
        Map<String, String> termMap = new Map1L<>();
        String folder = "outTest4";
        String term1 = "term";
        String def1 = "the word term appears several times in this definition. A term next too a term... double term?";

        terms.enqueue(term1);
        termMap.add(term1, def1);
        Glossary.createTopIndex(terms, folder, termMap);

        SimpleReader in = new SimpleReader1L("outTest4/index.html");
        SimpleReader inEXP = new SimpleReader1L("outTest4/indexEXP.html");
        SimpleReader subIn = new SimpleReader1L("outTest4/term.html");
        SimpleReader subInEXP = new SimpleReader1L("outTest4/termEXP.html");

        while (!in.atEOS() && !inEXP.atEOS() && !subIn.atEOS()
                && !subInEXP.atEOS()) {
            String in1 = in.nextLine();
            String in2 = inEXP.nextLine();
            String in3 = subIn.nextLine();
            String in4 = subInEXP.nextLine();

            assertEquals(in1, in2);
            assertEquals(in3, in4);
        }
        in.close();
        inEXP.close();
        subIn.close();
        subInEXP.close();

    }
}
