import components.stack.Stack;
import components.stack.Stack1L;
import components.stack.Stack3;

/**
 * Customized JUnit test fixture for {@code Stack1L}.
 */
public class Stack1LTest extends StackTest {

    @Override
    protected final Stack<String> constructorTest() {

        // TODO - fill in body

        Stack<String> stack1 = new Stack1L<>();
        // This line added just to make the program compilable.
        return stack1;
    }

    @Override
    protected final Stack<String> constructorRef() {

        // TODO - fill in body

        Stack<String> stack2 = new Stack3<>();
        // This line added just to make the program compilable.
        return stack2;
    }

}
