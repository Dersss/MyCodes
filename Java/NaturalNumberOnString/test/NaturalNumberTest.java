import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber1L;

/**
 * JUnit test fixture for {@code NaturalNumber}'s constructors and kernel
 * methods.
 *
 * @author Andrew Schneider
 * @author Eric Sullivan
 * @author Hitesh Bavisetty
 *
 */
public abstract class NaturalNumberTest {

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @return the new number
     * @ensures constructorTest = 0
     */
    protected abstract NaturalNumber constructorTest();

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param i
     *            {@code int} to initialize from
     * @return the new number
     * @requires i >= 0
     * @ensures constructorTest = i
     */
    protected abstract NaturalNumber constructorTest(int i);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param s
     *            {@code String} to initialize from
     * @return the new number
     * @requires there exists n: NATURAL (s = TO_STRING(n))
     * @ensures s = TO_STRING(constructorTest)
     */
    protected abstract NaturalNumber constructorTest(String s);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param n
     *            {@code NaturalNumber} to initialize from
     * @return the new number
     * @ensures constructorTest = n
     */
    protected abstract NaturalNumber constructorTest(NaturalNumber n);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @return the new number
     * @ensures constructorRef = 0
     */
    protected abstract NaturalNumber constructorRef();

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param i
     *            {@code int} to initialize from
     * @return the new number
     * @requires i >= 0
     * @ensures constructorRef = i
     */
    protected abstract NaturalNumber constructorRef(int i);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param s
     *            {@code String} to initialize from
     * @return the new number
     * @requires there exists n: NATURAL (s = TO_STRING(n))
     * @ensures s = TO_STRING(constructorRef)
     */
    protected abstract NaturalNumber constructorRef(String s);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param n
     *            {@code NaturalNumber} to initialize from
     * @return the new number
     * @ensures constructorRef = n
     */
    protected abstract NaturalNumber constructorRef(NaturalNumber n);

    /**
     * Test constructor.
     */
    @Test
    public void testEmptyConstructor() {
        NaturalNumber n = this.constructorTest();
        NaturalNumber expectedN = this.constructorRef();
        assertEquals(expectedN.toString(), n.toString());
    }

    /**
     * Test constructor with zero int parameter.
     */
    @Test
    public void testConstructorIntZero() {
        NaturalNumber n = this.constructorTest(0);
        NaturalNumber expectedN = this.constructorRef(0);
        assertEquals(expectedN, n);
    }

    /**
     * Test constructor with int parameter.
     */
    @Test
    public void testConstructorInt() {
        NaturalNumber n = this.constructorTest(45);
        NaturalNumber expectedN = this.constructorRef(45);
        assertEquals(expectedN, n);
    }

    /**
     * Test constructor with large int parameter.
     */
    @Test
    public void testConstructorIntLarge() {
        NaturalNumber n = this.constructorTest(2147483647);
        NaturalNumber expectedN = this.constructorRef(2147483647);
        assertEquals(expectedN, n);
    }

    /**
     * Test constructor with zero String parameter.
     */
    @Test
    public void testConstructorString2() {
        NaturalNumber n = this.constructorTest("0");
        NaturalNumber expectedN = this.constructorRef("0");
        assertEquals(expectedN, n);
    }

    /**
     * Test constructor with String parameter.
     */
    @Test
    public void testConstructorString() {
        NaturalNumber n = this.constructorTest("587");
        NaturalNumber expectedN = this.constructorRef("587");
        assertEquals(expectedN, n);
    }

    /**
     * Test constructor with large String parameter.
     */
    @Test
    public void testConstructorStringLarge() {
        NaturalNumber n = this.constructorTest("75214371528747615246");
        NaturalNumber expectedN = this.constructorRef("75214371528747615246");
        assertEquals(expectedN, n);
    }

    /**
     * Test constructor with zero NaturalNumber parameter.
     */
    @Test
    public void testConstructorNNZero() {
        NaturalNumber n = this.constructorTest(new NaturalNumber1L(0));
        NaturalNumber expectedN = this.constructorRef(new NaturalNumber1L(0));
        assertEquals(expectedN, n);
    }

    /**
     * Test constructor with NaturalNumber parameter.
     */
    @Test
    public void testConstructorNN() {
        NaturalNumber n = this.constructorTest(new NaturalNumber1L(3765));
        NaturalNumber expectedN = this
                .constructorRef(new NaturalNumber1L(3765));
        assertEquals(expectedN, n);
    }

    /**
     * Test constructor with large NaturalNumber parameter.
     */
    @Test
    public void testConstructorNNLarge() {
        NaturalNumber n = this.constructorTest(
                new NaturalNumber1L("615427164761278564781264"));
        NaturalNumber expectedN = this.constructorRef(
                new NaturalNumber1L("615427164761278564781264"));
        assertEquals(expectedN, n);
    }

    /**
     * Test for divideBy10() with zero.
     */
    @Test
    public final void testDivideBy10Zero() {

        NaturalNumber nn1 = this.constructorTest(0);
        NaturalNumber nn2 = this.constructorRef(0);

        int hold = nn1.divideBy10();

        assertEquals(nn2, nn1);
        assertEquals(0, hold);

    }

    /**
     * Test for divideBy10() with 100.
     */
    @Test
    public final void testDivideBy10_1() {

        NaturalNumber nn1 = this.constructorTest(100);
        NaturalNumber nn2 = this.constructorRef(10);

        int hold = nn1.divideBy10();

        assertEquals(nn2, nn1);
        assertEquals(0, hold);

    }

    /**
     * Test for divideBy10() with 113.
     */
    @Test
    public final void testDivideBy10_2() {

        NaturalNumber nn1 = this.constructorTest(113);
        NaturalNumber nn2 = this.constructorRef(11);

        int hold = nn1.divideBy10();

        assertEquals(nn2, nn1);
        assertEquals(3, hold);

    }

    /**
     * Test for divideBy10() with 14.
     */
    @Test
    public final void testDivideBy10_3() {

        NaturalNumber nn1 = this.constructorTest(14);
        NaturalNumber nn2 = this.constructorRef(1);

        int hold = nn1.divideBy10();

        assertEquals(nn2, nn1);
        assertEquals(4, hold);

    }

    /**
     * Test for divideBy10() with large number.
     */
    @Test
    public final void testDivideBy10_4() {

        NaturalNumber nn1 = this.constructorTest("52345884389089753");
        NaturalNumber nn2 = this.constructorRef("5234588438908975");

        int rem = nn1.divideBy10();

        assertEquals(nn2, nn1);
        assertEquals(3, rem);

    }

    /**
     * Test for multiply10() with zero.
     */
    @Test
    public final void testMultiplyBy10Zero() {

        NaturalNumber nn1 = this.constructorTest(0);
        NaturalNumber nn2 = this.constructorRef(1);

        nn1.multiplyBy10(1);

        assertEquals(nn2, nn1);

    }

    /**
     * Test for multiply10() with zero and zero passed.
     */
    @Test
    public final void testMultiplyBy10Zero2() {

        NaturalNumber nn1 = this.constructorTest(0);
        NaturalNumber nn2 = this.constructorRef(0);

        nn1.multiplyBy10(0);

        assertEquals(nn2, nn1);

    }

    /**
     * Test for multiply10() with 10 and a zero passed.
     */
    @Test
    public final void testMultiplyBy10_1() {

        NaturalNumber nn1 = this.constructorTest(10);
        NaturalNumber nn2 = this.constructorRef(100);

        nn1.multiplyBy10(0);

        assertEquals(nn2, nn1);

    }

    /**
     * Test for multiply10() with 15 and a 3 passed.
     */
    @Test
    public final void testMultiplyBy10_2() {

        NaturalNumber nn1 = this.constructorTest(15);
        NaturalNumber nn2 = this.constructorRef(153);

        nn1.multiplyBy10(3);

        assertEquals(nn2, nn1);

    }

    /**
     * Test for multiply10() with large number and 5 passed.
     */
    @Test
    public final void testMultiplyBy10Large() {

        NaturalNumber nn1 = this
                .constructorTest("1876871548512354871178346712");
        NaturalNumber nn2 = this
                .constructorRef("18768715485123548711783467125");

        nn1.multiplyBy10(5);

        assertEquals(nn2, nn1);

    }

    /**
     * Test isZero with empty constructor to get true.
     */
    @Test
    public final void testIsZeroEmpty() {
        NaturalNumber n = this.constructorTest();
        NaturalNumber expectedN = this.constructorRef();

        boolean isZero = n.isZero();

        assertEquals(true, isZero);
        assertEquals(expectedN, n);
    }

    /**
     * Test isZero with zero to get true.
     */
    @Test
    public final void testIsZeroIntTrue() {
        NaturalNumber n = this.constructorTest(0);
        NaturalNumber expectedN = this.constructorRef(0);

        boolean isZero = n.isZero();

        assertEquals(true, isZero);
        assertEquals(expectedN, n);
    }

    /**
     * Test isZero with 13 to get false.
     */
    @Test
    public final void testIsZeroIntFalse() {
        NaturalNumber n = this.constructorTest(13);
        NaturalNumber expectedN = this.constructorRef(13);

        boolean isZero = n.isZero();

        assertEquals(false, isZero);
        assertEquals(expectedN, n);
    }

    /**
     * Test isZero with 216871248712874872165874 to get false.
     */
    @Test
    public final void testIsZeroNNFalse() {
        NaturalNumber n = this.constructorTest(
                new NaturalNumber1L("216871248712874872165874"));
        NaturalNumber expectedN = this.constructorRef(
                new NaturalNumber1L("216871248712874872165874"));

        boolean isZero = n.isZero();

        assertEquals(false, isZero);
        assertEquals(expectedN, n);
    }

    @Test
    public final void testToString() {
        NaturalNumber zero = this.constructorTest(new NaturalNumber1L(0));
        NaturalNumber exp = this.constructorRef(new NaturalNumber1L(0));

        assertEquals(zero.toString(), exp.toString());

    }

    @Test
    public final void testToString2() {
        NaturalNumber zero = this.constructorTest(new NaturalNumber1L(143));
        NaturalNumber exp = this.constructorRef(new NaturalNumber1L(143));

        assertEquals(zero.toString(), exp.toString());

    }

}
