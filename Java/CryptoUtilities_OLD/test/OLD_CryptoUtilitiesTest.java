import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

/**
 * @author Put your name here
 *
 */
public class OLD_CryptoUtilitiesTest {

    @Test
    public void generateNextLikelyPrime_13() {
        NaturalNumber n = new NaturalNumber2(13);
        OLD_CryptoUtilities.generateNextLikelyPrime(n);
        assertEquals("13", n.toString());
    }

    @Test
    public void generateNextLikelyPrime_59() {
        NaturalNumber n = new NaturalNumber2(59);
        OLD_CryptoUtilities.generateNextLikelyPrime(n);
        assertEquals("59", n.toString());
    }

    @Test
    public void generateNextLikelyPrime_73() {
        NaturalNumber n = new NaturalNumber2(73);
        OLD_CryptoUtilities.generateNextLikelyPrime(n);
        assertEquals("79", n.toString());
    }

    @Test
    public void generateNextLikelyPrime_60() {
        NaturalNumber n = new NaturalNumber2(60);
        OLD_CryptoUtilities.generateNextLikelyPrime(n);
        assertEquals("61", n.toString());
    }

    @Test
    public void isPrime1_3() {
        NaturalNumber n = new NaturalNumber2(3);
        OLD_CryptoUtilities.isPrime1(n);
        assertEquals(true, true);
    }

    @Test
    public void isPrime1_2() {
        NaturalNumber n = new NaturalNumber2(2);
        OLD_CryptoUtilities.isPrime1(n);
        assertEquals(true, true);
    }

    @Test
    public void isPrime1_9() {
        NaturalNumber n = new NaturalNumber2(9);
        OLD_CryptoUtilities.isPrime1(n);
        assertEquals(false, false);
    }

    @Test
    public void isPrime2_3() {
        NaturalNumber n = new NaturalNumber2(3);
        OLD_CryptoUtilities.isPrime2(n);
        assertEquals(true, true);
    }

    @Test
    public void isPrime2_2() {
        NaturalNumber n = new NaturalNumber2(2);
        OLD_CryptoUtilities.isPrime2(n);
        assertEquals(true, true);
    }

    @Test
    public void isPrime2_9() {
        NaturalNumber n = new NaturalNumber2(9);
        OLD_CryptoUtilities.isPrime2(n);
        assertEquals(false, false);
    }

    @Test
    public void isPrime2_98() {
        NaturalNumber n = new NaturalNumber2(98);
        OLD_CryptoUtilities.isPrime2(n);
        assertEquals(false, false);
    }

    @Test
    public void isPrime2_13() {
        NaturalNumber n = new NaturalNumber2(13);
        OLD_CryptoUtilities.isPrime2(n);
        assertEquals(true, true);
    }

    @Test
    public void isPrime2_15() {
        NaturalNumber n = new NaturalNumber2(15);
        OLD_CryptoUtilities.isPrime2(n);
        assertEquals(false, false);
    }

    @Test
    public void isPrime2_7() {
        NaturalNumber n = new NaturalNumber2(7);
        OLD_CryptoUtilities.isPrime2(n);
        assertEquals(true, true);
    }

    @Test
    public void isPrime2_395() {
        NaturalNumber n = new NaturalNumber2(395);
        OLD_CryptoUtilities.isPrime2(n);
        assertEquals(false, false);
    }

    @Test
    public void isPrime2_197() {
        NaturalNumber n = new NaturalNumber2(197);
        OLD_CryptoUtilities.isPrime2(n);
        assertEquals(true, true);
    }

    @Test
    public void testisWitnessToCompositeness_3_6() {
        NaturalNumber n = new NaturalNumber2(3);
        NaturalNumber m = new NaturalNumber2(6);
        OLD_CryptoUtilities.isWitnessToCompositeness(n, m);
        assertEquals(true, true);

    }

    @Test
    public void testisWitnessToCompositeness_4_6() {
        NaturalNumber n = new NaturalNumber2(4);
        NaturalNumber m = new NaturalNumber2(6);
        OLD_CryptoUtilities.isWitnessToCompositeness(n, m);
        assertEquals(false, false);

    }

    @Test
    public void testisWitnessToCompositeness_2_6() {
        NaturalNumber n = new NaturalNumber2(2);
        NaturalNumber m = new NaturalNumber2(6);
        OLD_CryptoUtilities.isWitnessToCompositeness(n, m);
        assertEquals(true, true);

    }

    /*
     * Tests of reduceToGCD
     */

    @Test
    public void testReduceToGCD_0_0() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber m = new NaturalNumber2(0);
        OLD_CryptoUtilities.reduceToGCD(n, m);
        assertEquals("0", n.toString());
        assertEquals("0", m.toString());
    }

    @Test
    public void testReduceToGCD_30_21() {
        NaturalNumber n = new NaturalNumber2(30);
        NaturalNumber m = new NaturalNumber2(21);
        OLD_CryptoUtilities.reduceToGCD(n, m);
        assertEquals("3", n.toString());
        assertEquals("0", m.toString());
    }

    /*
     * Tests of isEven
     */

    @Test
    public void testIsEven_0() {
        NaturalNumber n = new NaturalNumber2(0);
        boolean result = OLD_CryptoUtilities.isEven(n);
        assertEquals("0", n.toString());
        assertTrue(result);
    }

    @Test
    public void testIsEven_1() {
        NaturalNumber n = new NaturalNumber2(1);
        boolean result = OLD_CryptoUtilities.isEven(n);
        assertEquals("1", n.toString());
        assertTrue(!result);
    }

    /*
     * Tests of powerMod
     */

    @Test
    public void testPowerMod_0_0_2() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber p = new NaturalNumber2(0);
        NaturalNumber m = new NaturalNumber2(2);
        OLD_CryptoUtilities.powerMod(n, p, m);
        assertEquals("1", n.toString());
        assertEquals("0", p.toString());
        assertEquals("2", m.toString());
    }

    @Test
    public void testPowerMod_2_4_3() {
        NaturalNumber n = new NaturalNumber2(2);
        NaturalNumber p = new NaturalNumber2(4);
        NaturalNumber m = new NaturalNumber2(3);
        OLD_CryptoUtilities.powerMod(n, p, m);
        assertEquals("1", n.toString());
        assertEquals("4", p.toString());
        assertEquals("3", m.toString());
    }

    @Test
    public void testPowerMod_6_2_19() {
        NaturalNumber n = new NaturalNumber2(6);
        NaturalNumber p = new NaturalNumber2(2);
        NaturalNumber m = new NaturalNumber2(19);
        OLD_CryptoUtilities.powerMod(n, p, m);
        assertEquals("17", n.toString());
        assertEquals("2", p.toString());
        assertEquals("19", m.toString());
    }

    @Test
    public void testPowerMod_17_18_19() {
        NaturalNumber n = new NaturalNumber2(17);
        NaturalNumber p = new NaturalNumber2(18);
        NaturalNumber m = new NaturalNumber2(19);
        OLD_CryptoUtilities.powerMod(n, p, m);
        assertEquals("1", n.toString());
        assertEquals("18", p.toString());
        assertEquals("19", m.toString());
    }

}
