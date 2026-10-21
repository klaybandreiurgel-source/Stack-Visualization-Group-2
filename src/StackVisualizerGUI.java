/*
Description: This program uses Java Swing to create a graphical
interface that visually represents stack operations. It displays
stack elements and updates the visualization when PUSH, POP,
PEEK, CHECK STACK, RANDOM, and CLEAR operations are performed.

Programmed by: Dela Torre, Torres, Urgel
Last Modified: October 21, 2026
Version: 1.0
Acknowledgements: OpenAI ChatGPT (GPT-6) assisted with drafting and explaining
the Java Swing graphical interface.
Prompt used: "Assist me create a Java Swing GUI that visually represents a stack and supports PUSH, POP, PEEK, CHECK STACK,
RANDOM, and CLEAR operations."
*/

import java.awt.*;
import java.util.Random;
import javax.swing.*;
import javax.swing.border.TitledBorder;

/**
 * StackGUI - Graphical user interface for MyStack.
 * The GUI never touches the stack's internal array. It only calls
 * push(), pop(), peek(), clear(), isEmpty(), isFull(), size() and toArray().
 */
public class StackGUI extends JFrame {

    private final MyStack stack = new MyStack();
    private final Random random = new Random();

    private final JTextField inputField = new JTextField(10);
    private final JPanel stackPanel = new JPanel();
    private final JLabel statusLabel = new JLabel("Ready.");

    // Colors
    private static final Color NORMAL_COLOR = new Color(200, 220, 245);
    private static final Color TOP_COLOR = new Color(120, 200, 120);
    private static final Color PEEK_COLOR = new Color(255, 200, 80);

    // true only right after PEEK is clicked, to highlight the top element
    private boolean peekHighlight = false;

    public StackGUI() {
        setTitle("Stack Visualization");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // ---------- Title + input ----------
        JPanel north = new JPanel(new GridLayout(2, 1, 0, 5));
        JLabel title = new JLabel("STACK VISUALIZATION", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        north.add(title);

        JPanel inputRow = new JPanel(new FlowLayout(FlowLayout.CENTER));
        inputRow.add(new JLabel("Enter value:"));
        inputRow.add(inputField);
        north.add(inputRow);
        add(north, BorderLayout.NORTH);

        // ---------- Stack display ----------
        stackPanel.setLayout(new BoxLayout(stackPanel, BoxLayout.Y_AXIS));
        stackPanel.setBackground(Color.WHITE);
        JScrollPane scroll = new JScrollPane(stackPanel);
        scroll.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY), "Stack: Top to Bottom",
                TitledBorder.LEFT, TitledBorder.TOP));
        scroll.setPreferredSize(new Dimension(400, 330));
        add(scroll, BorderLayout.CENTER);

        // ---------- Buttons + status ----------
        JPanel south = new JPanel(new BorderLayout(5, 5));

        JPanel buttons = new JPanel(new GridLayout(3, 2, 8, 8));
        JButton pushBtn = new JButton("PUSH");
        JButton popBtn = new JButton("POP");
        JButton peekBtn = new JButton("PEEK");
        JButton clearBtn = new JButton("CLEAR");
        JButton checkBtn = new JButton("CHECK STACK");
        JButton randomBtn = new JButton("RANDOM");
        buttons.add(pushBtn);
        buttons.add(popBtn);
        buttons.add(peekBtn);
        buttons.add(clearBtn);
        buttons.add(checkBtn);
        buttons.add(randomBtn);
        south.add(buttons, BorderLayout.CENTER);

        JPanel statusPanel = new JPanel(new BorderLayout());
        statusPanel.setBorder(BorderFactory.createTitledBorder("Status Message"));
        statusLabel.setFont(new Font("Arial", Font.BOLD, 12));
        statusPanel.add(statusLabel, BorderLayout.CENTER);
        south.add(statusPanel, BorderLayout.SOUTH);

        add(south, BorderLayout.SOUTH);

        // ---------- Button actions ----------
        pushBtn.addActionListener(e -> doPush());
        popBtn.addActionListener(e -> doPop());
        peekBtn.addActionListener(e -> doPeek());
        clearBtn.addActionListener(e -> doClear());
        checkBtn.addActionListener(e -> doCheck());
        randomBtn.addActionListener(e -> doRandom());
        inputField.addActionListener(e -> doPush()); // Enter key = PUSH

        refreshDisplay();
        pack();
        setMinimumSize(getSize());
        setLocationRelativeTo(null);
    }

    // =========================================================
    // Operations: each one calls the stack method, then refreshes
    // =========================================================
 private void doPush() {
        peekHighlight = false;
        String text = inputField.getText().trim();
        if (text.isEmpty()) {
            setStatus("INPUT ERROR: Please enter a value first", true);
            refreshDisplay();
            return;
        }
        try {
            int value = Integer.parseInt(text);
            stack.push(value); // stack method
            setStatus("PUSH: " + value + " was added to the top", false);
            inputField.setText("");
        } catch (NumberFormatException ex) {
            setStatus("INPUT ERROR: \"" + text + "\" is not a valid integer", true);
        } catch (IllegalStateException ex) { // overflow
            setStatus(ex.getMessage(), true);
        }
        inputField.requestFocusInWindow();
        refreshDisplay();
    }

    private void doPop() {
        peekHighlight = false;
        try {
            int value = stack.pop(); // stack method
            setStatus("POP: " + value + " was removed from the top", false);
        } catch (IllegalStateException ex) { // underflow
            setStatus(ex.getMessage(), true);
        }
        refreshDisplay();
    }

    private void doPeek() {
        try {
            int value = stack.peek(); // stack method (read-only)
            peekHighlight = true;
            setStatus("PEEK: The top element is " + value + " (not removed)", false);
        } catch (IllegalStateException ex) {
            peekHighlight = false;
            setStatus(ex.getMessage(), true);
        }
        refreshDisplay();
    }

    private void doClear() {
        peekHighlight = false;
        stack.clear(); // stack method
        setStatus("CLEAR: The stack is now empty", false);
        refreshDisplay();
    }

    private void doCheck() {
        peekHighlight = false;
        if (stack.isEmpty()) { // stack method
            setStatus("CHECK STACK: The stack is EMPTY (0/" + stack.capacity() + ")", false);
        } else if (stack.isFull()) { // stack method
            setStatus("CHECK STACK: The stack is NOT empty, it is FULL ("
                    + stack.size() + "/" + stack.capacity() + ")", false);
        } else {
            setStatus("CHECK STACK: The stack is NOT empty ("
                    + stack.size() + "/" + stack.capacity() + " elements)", false);
        }
        refreshDisplay();
    }

    private void doRandom() {
        peekHighlight = false;
        stack.clear(); // clear current stack first
        int count = random.nextInt(stack.capacity()) + 1; // 1 to capacity
        for (int i = 0; i < count; i++) {
            stack.push(random.nextInt(100) + 1); // 1 to 100
        }
        setStatus("RANDOM: Stack cleared and filled with " + count + " random element(s)", false);
        refreshDisplay();
    }

    // =========================================================
    // Display: always redrawn by reading the stack itself
    // =========================================================

 private void refreshDisplay() {
        stackPanel.removeAll();

        int[] items = stack.toArray(); // bottom -> top (a copy)

        if (items.length == 0) {
            JLabel empty = new JLabel("(Empty Stack)", SwingConstants.CENTER);
            empty.setAlignmentX(Component.CENTER_ALIGNMENT);
            empty.setFont(new Font("Arial", Font.BOLD, 12));
            stackPanel.add(Box.createVerticalStrut(20));
            stackPanel.add(empty);
        } else {
            stackPanel.add(Box.createVerticalStrut(5));
            // display from TOP to BOTTOM
            for (int i = items.length - 1; i >= 0; i--) {
                boolean isTop = (i == items.length - 1);
                stackPanel.add(createCell(items[i], i, isTop));
                stackPanel.add(Box.createVerticalStrut(3));
            }
        }

        stackPanel.revalidate();
        stackPanel.repaint();
    }

    private JPanel createCell(int value, int index, boolean isTop) {
        JPanel cell = new JPanel(new BorderLayout());
        cell.setMaximumSize(new Dimension(300, 28));
        cell.setPreferredSize(new Dimension(300, 28));
        cell.setAlignmentX(Component.CENTER_ALIGNMENT);

        Color bg = NORMAL_COLOR;
        if (isTop) {
            bg = peekHighlight ? PEEK_COLOR : TOP_COLOR;
        }
        cell.setBackground(bg);
        cell.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, isTop ? 2 : 1));

        JLabel idx = new JLabel(" [" + index + "]");
        JLabel val = new JLabel(String.valueOf(value), SwingConstants.CENTER);
        val.setFont(new Font("Arial", Font.BOLD, 14));
        JLabel tag = new JLabel(isTop ? "<- TOP " : " ");
        tag.setFont(new Font("Arial", Font.BOLD, 12));

        cell.add(idx, BorderLayout.WEST);
        cell.add(val, BorderLayout.CENTER);
        cell.add(tag, BorderLayout.EAST);
        return cell;
    }

    private void setStatus(String message, boolean error) {
        statusLabel.setText(message + " | Size: " + stack.size() + "/" + stack.capacity());
        statusLabel.setForeground(error ? Color.RED : Color.BLACK);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StackGUI().setVisible(true));
    }
}


// ===== Stack component (array-based, capacity 10) =====
/**
 * MyStack - Array-based bounded stack (Stack ADT).
 * Maximum capacity: 10 elements. Follows LIFO behavior.
 *
 * This class is the ONLY place where the array and the top index are touched.
 * The GUI communicates with it only through the public methods below.
 */
class MyStack {

    public static final int MAX_CAPACITY = 10;

    private final int[] data; // internal array storage
    private int top; // index of the top element (-1 when empty)

    public MyStack() {
        data = new int[MAX_CAPACITY];
        top = -1;
    }

    /** Adds a value on top of the stack. */
    public void push(int value) {
        if (isFull()) {
            throw new IllegalStateException("STACK OVERFLOW: Cannot push, stack is full");
        }
        top++;
        data[top] = value;
    }

    /** Removes and returns the top element. */
    public int pop() {
        if (isEmpty()) {
            throw new IllegalStateException("STACK UNDERFLOW: Cannot pop, stack is empty");
        }
        int value = data[top];
        top--;
        return value;
    }

    /** Returns the top element WITHOUT removing or modifying it. */
    public int peek() {
        if (isEmpty()) {
            throw new IllegalStateException("STACK EMPTY: Cannot peek, stack is empty");
        }
        return data[top];
    }

    /** Removes all elements from the stack. */
    public void clear() {
        top = -1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == MAX_CAPACITY - 1;
    }

    public int size() {
        return top + 1;
    }

    public int capacity() {
        return MAX_CAPACITY;
    }

    /**
     * Returns a COPY of the elements ordered from bottom (index 0) to top (last index).
     * It is a copy, so the GUI can display the stack without being able to
     * modify the stack's internal array.
     */
    public int[] toArray() {
        int[] copy = new int[size()];
        for (int i = 0; i < copy.length; i++) {
            copy[i] = data[i];
        }
        return copy;
    }
}
