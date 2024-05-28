package de.junkerjoerg12.tools;

import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import de.junkerjoerg12.Game;
import de.junkerjoerg12.Exceptions.NoSuchCommandException;

public class Console extends JFrame {

    // modes:
    private boolean build;
    private boolean settings;
    /*
     * .
     * .
     * .
     */

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

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        add(scrollPane, BorderLayout.CENTER);

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
                    // automatisch rot umrandet o.ä.
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
        print(input);
        input = input.toLowerCase();
        if (settings) {
            /* settigns hier einfügen */
            if (true) {
                print("keine Einstellugen vorhanden");
            } else {
                error(input);
            }
        } else if (build) {
            if (game.getMap() == null) {
                print("erst ein Level Starten");
            } else if (input.matches("^-n [a-zA-Z0-9,; ]*$")) { // neues Element hinzufügen
                game.getMap().getMapwriter().addMapElement(input.replaceAll("-n ", ""));
            } else if (input.contains("-m")) { // schon bestehendes Element bewegen
                try {
                    game.getMap().getMapwriter().changeMapelementPosition(input.replaceAll("\\s*-m\\s*", ""));
                } catch (NoSuchCommandException e) {
                    error(e.getMessage());
                }
            } else if (input.contains("-cd")) {  //größe eine schon bestehenden elements verändern
                try {
                    game.getMap().getMapwriter().changeMapelementDimension(input.replaceAll("\\s*-cd\\s*", ""));
                } catch (NoSuchCommandException e) {
                    error(e.getMessage());
                }
            }
            /* alle weiteren befehle, die zum bauen benötigt werden hier einfügen */
            else {
                error(input);
            }
        } else if (input.equals("highlight player")) {
            game.getMap().getPlayer().highlight();
        } else if (input.matches("highlight mapelement \\d+$")) {
            game.getMap().getAllObjects().get(Integer.parseInt(input.replaceAll("[a-z]", "").trim())).highlight();
        } else if (input.equals("hide")) {
            setVisible(false);
        } else if (input.equals("build")) { // auswahl der Verschiedenen Modi
            build = true;
            game.buildMode = true;
            settings = false;
        } else if (input.equals("settings")) {
            settings = true;
            build = false;
        } else {
            error(input);
        }
    }

    private void error(String input) {
        print("Der Befehl " + input + " ist entweder falsch geschrieben oder konnte nicht gefunden werden.");
    }
}
