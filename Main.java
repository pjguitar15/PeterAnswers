import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * @author Philcob Suzuki Josol
 */
public class Main extends JFrame {
    JLabel title;
    JLabel instruction;
    JTextField petitionField;
    JTextField questionField;
    JButton submit;
    JButton reset;
    JSeparator line;
    JLabel response;

    Font h1 = new Font("Open Sans", Font.BOLD, 24);
    Font paragraph = new Font("Open Sans", Font.PLAIN, 14);

    String petitionStatement = "Peter, please answer the following question";
    String typedStatement = "";

    public static void main(String[] args) {
        new Main();
    }

    Main() {
        setLayout(new GridBagLayout());
        GridBagConstraints gcon = new GridBagConstraints();
        gcon.insets = new Insets(5, 5, 5, 5);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(700, 500));
        setTitle("Peter, please answer");
        setResizable(false);
        // JLabel for Title
        title = new JLabel("Peter, please answer");
        gcon.gridx = 0;
        gcon.gridy = 0;
        title.setFont(h1);
        title.setForeground(Color.BLACK);
        add(title, gcon);

        // JLabel for instructions
        instruction = new JLabel("Type \"Peter, please answer\" on the petition first, then ask your question.");
        gcon.gridx = 0;
        gcon.gridy = 1;
        instruction.setFont(paragraph);
        add(instruction, gcon);

        // Textfield for peter please answer
        petitionField = new JTextField("Type your petition here");
        gcon.gridx = 0;
        gcon.gridy = 2;
        petitionField.setPreferredSize(new Dimension(250, 32));
        petitionField.setBorder(BorderFactory.createCompoundBorder(
                petitionField.getBorder(),
                BorderFactory.createEmptyBorder(5, 7, 5, 5)));
        petitionField.setForeground(Color.decode("#6C757D"));
        petitionField.setFont(new Font("Open Sans", Font.PLAIN, 13));
        petitionField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (petitionField.getText().equals("Type your petition here")) {
                    petitionField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (petitionField.getText().equals("")) {
                    petitionField.setText("Type your petition here");
                }
            }
        });
        petitionField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                // if first char is ".", display petition statement
                if ((e.getKeyChar() == '.' && typedStatement.length() == 0) || (typedStatement.length() > 0 && typedStatement.charAt(0) == '.')) {
                    typedStatement += e.getKeyChar();
                    petitionField.setText(petitionStatement.substring(0, typedStatement.length() - 1));
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        add(petitionField, gcon);

        // Textfield for question
        questionField = new JTextField("Type your question here");
        gcon.gridx = 0;
        gcon.gridy = 3;
        questionField.setPreferredSize(new Dimension(250, 32));
        questionField.setBorder(BorderFactory.createCompoundBorder(
                questionField.getBorder(),
                BorderFactory.createEmptyBorder(5, 7, 5, 5)));
        questionField.setForeground(Color.decode("#6C757D"));
        questionField.setFont(new Font("Open Sans", Font.PLAIN, 13));
        questionField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (questionField.getText().equals("Type your question here")) {
                    questionField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (questionField.getText().equals("")) {
                    questionField.setText("Type your question here");
                }
            }
        });
        add(questionField, gcon);

        // Button
        submit = new JButton("Submit");
        gcon.gridx = 0;
        gcon.gridy = 4;
        submit.setPreferredSize(new Dimension(100, 28));
        submit.setCursor(new Cursor(Cursor.HAND_CURSOR));
        submit.setFont(new Font("Helvetica Neue", Font.BOLD, 12));
        submit.setBackground(Color.decode("#007BFF"));
        submit.setForeground(Color.WHITE);
        submit.setBorder(null);
        submit.setFocusPainted(false);
        submit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                submit.setBackground(Color.decode("#0069D9"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                submit.setBackground(Color.decode("#007BFF"));
            }
        });
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (typedStatement.isEmpty()) {
                    response.setText("I am not in a mood to answer that right now.");
                } else {
                    response.setText(typedStatement.substring(1).trim());
                }
                submit.setVisible(false);
                reset.setVisible(true);
            }
        });
        add(submit, gcon);

        // Reset Button
        reset = new JButton("Reset");
        gcon.gridx = 0;
        gcon.gridy = 4;
        reset.setPreferredSize(new Dimension(100, 28));
        reset.setCursor(new Cursor(Cursor.HAND_CURSOR));
        reset.setFont(new Font("Helvetica Neue", Font.BOLD, 12));
        reset.setBackground(Color.decode("#28A745"));
        reset.setForeground(Color.WHITE);
        reset.setBorder(null);
        reset.setFocusPainted(false);
        reset.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                reset.setBackground(Color.decode("#218838"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                reset.setBackground(Color.decode("#28A745"));
            }
        });
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reset.setVisible(false);
                submit.setVisible(true);
                petitionField.setText("Type your petition here");
                questionField.setText("Type your question here");
                response.setText("");
                typedStatement = "";
            }
        });
        add(reset, gcon);
        reset.setVisible(false);
        // Hr
        line = new JSeparator();
        line.setOrientation(SwingConstants.HORIZONTAL);
        gcon.gridx = 0;
        gcon.gridy = 5;
        add(line, gcon);
        // JLabel for response
        response = new JLabel("");
        gcon.gridx = 0;
        gcon.gridy = 6;
        response.setFont(h1);
        add(response, gcon);

        pack();
        setLocationRelativeTo(null);
        getContentPane().requestFocusInWindow();
        setVisible(true);
    }

}
