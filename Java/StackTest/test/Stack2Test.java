import components.stack.Stack;
import components.stack.Stack2;
import components.stack.Stack3;

/**
 * Customized JUnit test fixture for {@code Stack1L}.
 */
public class Stack2Test extends StackTest {

    @Override
    protected final Stack<String> constructorTest() {

        // TODO - fill in body

        Stack<String> stack1 = new Stack2<>();
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
