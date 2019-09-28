import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Test class for NaturalNumber calculator GUI (view in MVC).
 *
 * @author Andrew Schneider
 */
public final class NNCalcViewLab extends JFrame implements ActionListener {

    /**
     * Text areas.
     */
    private final JTextArea tTop, tBottom;

    /**
     * Operator and related buttons.
     */
    private final JButton bClear, bSwap, bEnter, bAdd, bSubtract, bMultiply,
            bDivide, bPower, bRoot;

    /**
     * Digit entry buttons.
     */
    private final JButton[] bDigits;

    /**
     * Useful constants.
     */
    private static final int TEXT_AREA_HEIGHT = 5, TEXT_AREA_WIDTH = 20,
            DIGIT_BUTTONS = 10, MAIN_BUTTON_PANEL_GRID_ROWS = 4,
            MAIN_BUTTON_PANEL_GRID_COLUMNS = 4, SIDE_BUTTON_PANEL_GRID_ROWS = 3,
            SIDE_BUTTON_PANEL_GRID_COLUMNS = 1, CALC_GRID_ROWS = 3,
            CALC_GRID_COLUMNS = 1;

    /**
     * No-argument constructor.
     */
    public NNCalcViewLab() {

        // Create the JFrame being extended

        /*
         * Call the JFrame (superclass) constructor with a String parameter to
         * name the window in its title bar
         */
        super("Natural Number Calculator");

        // Set up the GUI widgets --------------------------------------------

        // TODO: fill in rest of body, following outline in comments

        /*
         * Create widgets
         */
        this.tTop = new JTextArea("", TEXT_AREA_HEIGHT, TEXT_AREA_WIDTH);
        this.tBottom = new JTextArea("", TEXT_AREA_HEIGHT, TEXT_AREA_WIDTH);
        this.bDigits = new JButton[DIGIT_BUTTONS];

        this.bClear = new JButton("Clear");
        this.bSwap = new JButton("Swap");
        this.bEnter = new JButton("Enter");
        this.bAdd = new JButton("+");
        this.bSubtract = new JButton("-");
        this.bDivide = new JButton("/");
        this.bMultiply = new JButton("*");
        this.bPower = new JButton("^");
        this.bRoot = new JButton("Root");

        // Set up the GUI widgets --------------------------------------------

        /*
         * Text areas should wrap lines, and should be read-only; they cannot be
         * edited because allowing keyboard entry would require checking whether
         * entries are digits, which we don't want to have to do
         */

        this.tTop.setEditable(false);
        this.tTop.setLineWrap(true);
        this.tTop.setWrapStyleWord(true);
        this.tBottom.setEditable(false);
        this.tBottom.setLineWrap(true);
        this.tBottom.setWrapStyleWord(true);

        /*
         * Initially, the following buttons should be disabled: divide (divisor
         * must not be 0) and root (root must be at least 2) -- hint: see the
         * JButton method setEnabled
         */
        this.bRoot.setEnabled(false);
        this.bDivide.setEnabled(false);

        /*
         * Create scroll panes for the text areas in case number is long enough
         * to require scrolling
         */

        JScrollPane inputTextScrollPane = new JScrollPane(this.tTop);
        JScrollPane bottomTextScrollPane = new JScrollPane(this.tBottom);

        /*
         * Create main button panel
         */
        JPanel buttonPanel = new JPanel(
                new GridLayout((MAIN_BUTTON_PANEL_GRID_ROWS),
                        (MAIN_BUTTON_PANEL_GRID_COLUMNS)));
        /*
         * Add the buttons to the main button panel, from left to right and top
         * to bottom
         */
        JButton seven = new JButton("7");
        JButton eight = new JButton("8");
        JButton nine = new JButton("9");
        buttonPanel.add(seven);
        buttonPanel.add(eight);
        buttonPanel.add(nine);
        buttonPanel.add(this.bRoot);

        JButton four = new JButton("4");
        JButton five = new JButton("5");
        JButton six = new JButton("6");
        buttonPanel.add(four);
        buttonPanel.add(five);
        buttonPanel.add(six);
        buttonPanel.add(this.bPower);

        JButton one = new JButton("1");
        JButton two = new JButton("2");
        JButton three = new JButton("3");

        buttonPanel.add(one);
        buttonPanel.add(two);
        buttonPanel.add(three);
        buttonPanel.add(this.bDivide);

        JButton zero = new JButton("0");
        buttonPanel.add(zero);
        buttonPanel.add(this.bMultiply);
        buttonPanel.add(this.bAdd);
        buttonPanel.add(this.bSubtract);

        this.bDigits[0] = zero;
        this.bDigits[1] = one;
        this.bDigits[2] = two;
        this.bDigits[3] = three;
        this.bDigits[4] = four;
        this.bDigits[5] = five;
        this.bDigits[6] = six;
        this.bDigits[7] = seven;
        this.bDigits[8] = eight;
        this.bDigits[9] = nine;

        /*
         * Create side button panel
         */
        JPanel sideButtonPanel = new JPanel(
                new GridLayout((SIDE_BUTTON_PANEL_GRID_ROWS),
                        (SIDE_BUTTON_PANEL_GRID_COLUMNS)));
        /*
         * Add the buttons to the side button panel, from left to right and top
         * to bottom
         */

        sideButtonPanel.add(this.bSwap);
        sideButtonPanel.add(this.bClear);
        sideButtonPanel.add(this.bEnter);

        /*
         * Create combined button panel organized using flow layout, which is
         * simple and does the right thing: sizes of nested panels are natural,
         * not necessarily equal as with grid layout
         */
        JPanel combined = new JPanel();
        combined.add(buttonPanel);
        combined.add(sideButtonPanel);
        /*
         * Add the other two button panels to the combined button panel
         */
        JPanel end = new JPanel();
        end.add(combined);
        this.setLayout(new GridLayout(3, 1));

        /*
         * Organize main window
         */

        /*
         * Add scroll panes and button panel to main window, from left to right
         * and top to bottom
         */

        this.add(inputTextScrollPane);
        this.add(bottomTextScrollPane);

        this.add(end);
        // Set up the observers ----------------------------------------------

        this.bClear.addActionListener(this);
        this.bSwap.addActionListener(this);
        this.bEnter.addActionListener(this);
        this.bAdd.addActionListener(this);
        this.bSubtract.addActionListener(this);
        this.bMultiply.addActionListener(this);
        this.bPower.addActionListener(this);
        this.bRoot.addActionListener(this);
        this.bDigits[0].addActionListener(this);
        this.bDigits[1].addActionListener(this);
        this.bDigits[2].addActionListener(this);
        this.bDigits[3].addActionListener(this);
        this.bDigits[4].addActionListener(this);
        this.bDigits[5].addActionListener(this);
        this.bDigits[6].addActionListener(this);
        this.bDigits[7].addActionListener(this);
        this.bDigits[8].addActionListener(this);
        this.bDigits[9].addActionListener(this);

        /*
         * Register this object as the observer for all GUI events
         */
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        // Set up the main application window --------------------------------

        /*
         * Make sure the main window is appropriately sized, exits this program
         * on close, and becomes visible to the user
         */

    }

    @Override
    public void actionPerformed(ActionEvent event) {
        JOptionPane.showMessageDialog(this,
                "You pressed: " + event.getActionCommand());
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        NNCalcViewLab view = new NNCalcViewLab();
    }

}
