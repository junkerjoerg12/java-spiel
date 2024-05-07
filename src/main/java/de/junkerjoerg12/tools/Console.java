package de.junkerjoerg12.tools;

import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import de.junkerjoerg12.Game;
import de.junkerjoerg12.map.Map;

public class Console extends JFrame {

    private JTextArea outputArea;
    private JTextField inputField;
    private String userInput = "";

    private Game game;

    public Console(Game game) {
        this.game = game;
        setTitle("Console");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create the output area
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        add(scrollPane, BorderLayout.CENTER);

        // Create the input field
        inputField = new JTextField();
        inputField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    userInput = inputField.getText();
                    processInput(userInput);
                    inputField.setText("");
                } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    // evtl irgendwas um die arbeit zu erleichtern, z.B. Ausgewähltes Objekt wird
                    // automatisch rot umrandet
                }
            }
        });
        add(inputField, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void print(String text) {
        outputArea.append(text + "\n");
    }

    public void setVisible(boolean visible) {
        super.setVisible(visible);
        inputField.requestFocus();
    }

    private void processInput(String input) {
        // Process the user input here
        print(input);
        input = input.toLowerCase();
        if (input.equals("highlight player")) {
            game.getMap().getPlayer().highlight();
        } else if (input.matches("^highlight mapelement \\d+$")) {
            game.getMap().getAllObjects().get(Integer.parseInt(input.replaceAll("[a-z]", "").trim())).highlight();
        } else if (input.equals("hide")) {
            setVisible(false);
        } else if (input.matches("set fps \\d+$")) {
            game.getMap().getGame().setFPSTarget(Integer.parseInt(input.replaceAll("[a-z]", "").trim()));
        } else {
            print("Der Befehl " + input + " ist entweder falsch geschrieben oder konnte nicht gefunden werden.");
        }
    }
}

