package de.junkerjoerg12.tools;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import de.junkerjoerg12.map.Map;

public class Console extends JFrame implements KeyListener{

    private JTextArea textArea;
    private JTextField inputField;
    private Map map;

    public Console(Map map) {
        setTitle("Console");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(textArea);

        inputField = new JTextField();

        this.add(inputField, BorderLayout.SOUTH);

        add(scrollPane, BorderLayout.CENTER);

        this.addKeyListener(this);
    }

    public void print(String message) {
        textArea.append(message + "\n");
    }

    public void processUserInput(String userInput) {
        // Verarbeiten Sie die Benutzereingabe hier
        print("Benutzereingabe: " + userInput);
    }

        public static void main(String[] args) {
        Console consoleFrame = new Console(null);
        consoleFrame.setVisible(true);
        consoleFrame.print("Hallo, Welt!");
    }

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            System.out.println(e.getKeyCode());
            if (e.getKeyCode() == 10) {//enter
                processUserInput(inputField.getText());
                inputField.setText("");
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
}
