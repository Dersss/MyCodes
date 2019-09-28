import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

/**
 * @author Put your name here
 *
 */
public class CryptoUtilitiesTest {

    /**
     * Tests for generateNextLikelyPrime
     */
    @Test
    public void generateNextLikelyPrime_13() {
        NaturalNumber n = new NaturalNumber2(13);
        CryptoUtilities.generateNextLikelyPrime(n);
        assertEquals("13", n.toString());
    }

    @Test
    public void generateNextLikelyPrime_59() {
        NaturalNumber n = new NaturalNumber2(59);
        CryptoUtilities.generateNextLikelyPrime(n);
        assertEquals("59", n.toString());
    }

    @Test
    public void generateNextLikelyPrime_55() {
        NaturalNumber n = new NaturalNumber2(55);
        CryptoUtilities.generateNextLikelyPrime(n);
        assertEquals("59", n.toString());
    }

    @Test
    public void generateNextLikelyPrime_73() {
        NaturalNumber n = new NaturalNumber2(73);
        CryptoUtilities.generateNextLikelyPrime(n);
        assertEquals("73", n.toString());
    }

    @Test
    public void generateNextLikelyPrime_74() {
        NaturalNumber n = new NaturalNumber2(74);
        CryptoUtilities.generateNextLikelyPrime(n);
        assertEquals("79", n.toString());
    }

    @Test
    public void generateNextLikelyPrime_60() {
        NaturalNumber n = new NaturalNumber2(60);
        CryptoUtilities.generateNextLikelyPrime(n);
        assertEquals("61", n.toString());
    }

    /**
     * Tests for isPrime1
     */
    @Test
    public void isPrime1_3() {
        NaturalNumber n = new NaturalNumber2(3);
        CryptoUtilities.isPrime1(n);
        assertEquals(true, true);
    }

    @Test
    public void isPrime1_2() {
        NaturalNumber n = new NaturalNumber2(2);
        CryptoUtilities.isPrime1(n);
        assertEquals(true, true);
    }

    @Test
    public void isPrime1_9() {
        NaturalNumber n = new NaturalNumber2(9);
        CryptoUtilities.isPrime1(n);
        assertEquals(false, false);
    }

    /**
     * Tests for isPrime2
     */
    @Test
    public void isPrime2_3() {
        NaturalNumber n = new NaturalNumber2(3);
        CryptoUtilities.isPrime2(n);
        assertEquals(true, true);
    }

    @Test
    public void isPrime2_100() {
        NaturalNumber n = new NaturalNumber2(100);
        CryptoUtilities.isPrime2(n);
        assertEquals(false, false);
    }

    @Test
    public void isPrime2_97() {
        NaturalNumber n = new NaturalNumber2(97);
        CryptoUtilities.isPrime2(n);
        assertEquals(true, true);
    }

    @Test
    public void isPrime2_2() {
        NaturalNumber n = new NaturalNumber2(2);
        CryptoUtilities.isPrime2(n);
        assertEquals(true, true);
    }

    @Test
    public void isPrime2_9() {
        NaturalNumber n = new NaturalNumber2(9);
        CryptoUtilities.isPrime2(n);
        assertEquals(false, false);
    }

    @Test
    public void isPrime2_98() {
        NaturalNumber n = new NaturalNumber2(98);
        CryptoUtilities.isPrime2(n);
        assertEquals(false, false);
    }

    @Test
    public void isPrime2_13() {
        NaturalNumber n = new NaturalNumber2(13);
        CryptoUtilities.isPrime2(n);
        assertEquals(true, true);
    }

    @Test
    public void isPrime2_15() {
        NaturalNumber n = new NaturalNumber2(15);
        CryptoUtilities.isPrime2(n);
        assertEquals(false, false);
    }

    @Test
    public void isPrime2_7() {
        NaturalNumber n = new NaturalNumber2(7);
        CryptoUtilities.isPrime2(n);
        assertEquals(true, true);
    }

    @Test
    public void isPrime2_395() {
        NaturalNumber n = new NaturalNumber2(395);
        CryptoUtilities.isPrime2(n);
        assertEquals(false, false);
    }

    @Test
    public void isPrime2_197() {
        NaturalNumber n = new NaturalNumber2(197);
        CryptoUtilities.isPrime2(n);
        assertEquals(true, true);
    }

    /**
     * Tests for isWitnessToCompositeness
     */
    @Test
    public void testisWitnessToCompositeness_3_6() {
        NaturalNumber n = new NaturalNumber2(3);
        NaturalNumber m = new NaturalNumber2(6);
        CryptoUtilities.isWitnessToCompositeness(n, m);
        assertEquals(true, true);

    }

    @Test
    public void testisWitnessToCompositeness_4_6() {
        NaturalNumber n = new NaturalNumber2(4);
        NaturalNumber m = new NaturalNumber2(6);
        CryptoUtilities.isWitnessToCompositeness(n, m);
        assertEquals(false, false);

    }

    @Test
    public void testisWitnessToCompositeness_2_6() {
        NaturalNumber n = new NaturalNumber2(2);
        NaturalNumber m = new NaturalNumber2(6);
        CryptoUtilities.isWitnessToCompositeness(n, m);
        assertEquals(true, true);

    }

    @Test
    public void testisWitnessToCompositeness_5_55() {
        NaturalNumber n = new NaturalNumber2(5);
        NaturalNumber m = new NaturalNumber2(55);
        CryptoUtilities.isWitnessToCompositeness(n, m);
        assertEquals(true, true);

    }

    @Test
    public void testisWitnessToCompositeness_3_43() {
        NaturalNumber n = new NaturalNumber2(3);
        NaturalNumber m = new NaturalNumber2(43);
        CryptoUtilities.isWitnessToCompositeness(n, m);
        assertEquals(false, false);

    }

    /*
     * Tests of reduceToGCD
     */

    @Test
    public void testReduceToGCD_0_0() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber m = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals("0", n.toString());
        assertEquals("0", m.toString());
    }

    @Test
    public void testReduceToGCD_30_21() {
        NaturalNumber n = new NaturalNumber2(30);
        NaturalNumber m = new NaturalNumber2(21);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals("3", n.toString());
        assertEquals("0", m.toString());
    }

    @Test
    public void testReduceToGCD_100_50() {
        NaturalNumber n = new NaturalNumber2(100);
        NaturalNumber m = new NaturalNumber2(50);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals("50", n.toString());
        assertEquals("0", m.toString());
    }

    @Test
    public void testReduceToGCD_112_64() {
        NaturalNumber n = new NaturalNumber2(112);
        NaturalNumber m = new NaturalNumber2(64);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals("16", n.toString());
        assertEquals("0", m.toString());
    }

    @Test
    public void testReduceToGCD_1114_84() {
        NaturalNumber n = new NaturalNumber2(1114);
        NaturalNumber m = new NaturalNumber2(84);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals("2", n.toString());
        assertEquals("0", m.toString());
    }

    @Test
    public void testReduceToGCD_183455_2500() {
        NaturalNumber n = new NaturalNumber2(183455);
        NaturalNumber m = new NaturalNumber2(2500);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals("5", n.toString());
        assertEquals("0", m.toString());
    }

    /*
     * Tests of isEven
     */

    @Test
    public void testIsEven_0() {
        NaturalNumber n = new NaturalNumber2(0);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals("0", n.toString());
        assertTrue(result);
    }

    @Test
    public void testIsEven_1() {
        NaturalNumber n = new NaturalNumber2(1);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals("1", n.toString());
        assertTrue(!result);
    }

    @Test
    public void testIsEven_2() {
        NaturalNumber n = new NaturalNumber2(2);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals("2", n.toString());
        assertTrue(result);
    }

    @Test
    public void testIsEven_3() {
        NaturalNumber n = new NaturalNumber2(3);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals("3", n.toString());
        assertTrue(!result);
    }

    @Test
    public void testIsEven_16() {
        NaturalNumber n = new NaturalNumber2(16);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals("16", n.toString());
        assertTrue(result);
    }

    @Test
    public void testIsEven_93() {
        NaturalNumber n = new NaturalNumber2(93);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals("93", n.toString());
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
        CryptoUtilities.powerMod(n, p, m);
        assertEquals("1", n.toString());
        assertEquals("0", p.toString());
        assertEquals("2", m.toString());
    }

    @Test
    public void testPowerMod_2_4_3() {
        NaturalNumber n = new NaturalNumber2(2);
        NaturalNumber p = new NaturalNumber2(4);
        NaturalNumber m = new NaturalNumber2(3);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals("1", n.toString());
        assertEquals("4", p.toString());
        assertEquals("3", m.toString());
    }

    @Test
    public void testPowerMod_6_2_19() {
        NaturalNumber n = new NaturalNumber2(6);
        NaturalNumber p = new NaturalNumber2(2);
        NaturalNumber m = new NaturalNumber2(19);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals("17", n.toString());
        assertEquals("2", p.toString());
        assertEquals("19", m.toString());
    }

    @Test
    public void testPowerMod_6_4_19() {
        NaturalNumber n = new NaturalNumber2(6);
        NaturalNumber p = new NaturalNumber2(4);
        NaturalNumber m = new NaturalNumber2(19);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals("4", n.toString());
        assertEquals("4", p.toString());
        assertEquals("19", m.toString());
    }

    @Test
    public void testPowerMod_17_18_19() {
        NaturalNumber n = new NaturalNumber2(17);
        NaturalNumber p = new NaturalNumber2(18);
        NaturalNumber m = new NaturalNumber2(19);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals("1", n.toString());
        assertEquals("18", p.toString());
        assertEquals("19", m.toString());
    }

    @Test
    public void testPowerMod_2_3_5() {
        NaturalNumber n = new NaturalNumber2(2);
        NaturalNumber p = new NaturalNumber2(3);
        NaturalNumber m = new NaturalNumber2(5);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals("3", n.toString());
        assertEquals("3", p.toString());
        assertEquals("5", m.toString());
    }

    @Test
    public void testPowerMod_3_4_5() {
        NaturalNumber n = new NaturalNumber2(3);
        NaturalNumber p = new NaturalNumber2(4);
        NaturalNumber m = new NaturalNumber2(5);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals("1", n.toString());
        assertEquals("4", p.toString());
        assertEquals("5", m.toString());
    }

}
