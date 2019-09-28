import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

public class NNtoStringWithCommasTest {

    /**
     * Calls the method under test.
     *
     * @param n
     *            the number to pass to the method under test
     * @return the {@code String} returned by the method under test
     * @ensures <pre>
     * redirectToMethodUnderTest = [String returned by the method under test]
     * </pre>
     */
    private static String redirectToMethodUnderTest(NaturalNumber n) {
        return NNtoStringWithCommas3.toStringWithCommas(n);
    }

    @Test
    public void test1() {
        NaturalNumber nn1 = new NaturalNumber2(1000000);
        String withCommas = redirectToMethodUnderTest(nn1);
        System.out.println("n: " + nn1);
        System.out.println("with commas: " + withCommas);

        assertTrue(!nn1.isZero());
        assertEquals("1,000,000", withCommas);
    }

    @Test
    public void test2() {
        NaturalNumber nn1 = new NaturalNumber2(1);
        String withCommas = redirectToMethodUnderTest(nn1);
        System.out.println("n: " + nn1);
        System.out.println("with commas: " + withCommas);

        assertTrue(!nn1.isZero());
        assertEquals("1", withCommas);
    }

    @Test
    public void test3() {
        NaturalNumber nn1 = new NaturalNumber2(1000);
        String withCommas = redirectToMethodUnderTest(nn1);
        System.out.println("n: " + nn1);
        System.out.println("with commas: " + withCommas);

        assertTrue(!nn1.isZero());
        assertEquals("1,000", withCommas);
    }

    @Test
    public void test4() {
        NaturalNumber nn1 = new NaturalNumber2(1999);
        String withCommas = redirectToMethodUnderTest(nn1);
        System.out.println("n: " + nn1);
        System.out.println("with commas: " + withCommas);

        assertTrue(!nn1.isZero());
        assertEquals("1,999", withCommas);
    }

    @Test
    public void test5() {
        NaturalNumber nn1 = new NaturalNumber2(0);
        String withCommas = redirectToMethodUnderTest(nn1);
        System.out.println("n: " + nn1);
        System.out.println("with commas: " + withCommas);

        assertTrue(!nn1.isZero());
        assertEquals("0", withCommas);
    }

}
