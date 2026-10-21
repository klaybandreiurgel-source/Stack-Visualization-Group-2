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
