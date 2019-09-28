import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * JUnit test fixture for {@code ExpressionEvaluator}'s {@code valueOfExpr}
 * static method.
 *
 * @author Put your name here
 *
 */
public final class ExpressionEvaluatorTest {

    @Test
    public void testExample() {
        StringBuilder exp = new StringBuilder(
                "281/7/2-1-5*(15-(14-1))+((1))+20=30!");
        int value = ExpressionEvaluator.valueOfExpr(exp);
        assertEquals(30, value);
        assertEquals("=30!", exp.toString());
    }

    // TODO - add other (simpler) test cases to help with debugging

    @Test
    public void test1() {
        StringBuilder exp = new StringBuilder("((9*9)+9)/10=9!");
        int value = ExpressionEvaluator.valueOfExpr(exp);
        assertEquals(9, value);
        assertEquals("=9!", exp.toString());
    }

    @Test
    public void test2() {
        StringBuilder exp = new StringBuilder("((9*9)+9)*100=9000!");
        int value = ExpressionEvaluator.valueOfExpr(exp);
        assertEquals(9000, value);
        assertEquals("=9000!", exp.toString());
    }
}
