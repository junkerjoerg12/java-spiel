package de.junkerjoerg12.tools;

import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import de.junkerjoerg12.Exceptions.InvalidIndexException;
import de.junkerjoerg12.Exceptions.NoSuchCommandException;
import de.junkerjoerg12.Game;

public class Console extends JFrame {

  // modes:
  private boolean build;
  private boolean settings;

  private JTextArea outputArea;
  private JTextField inputField;
  private String userInput = "";

  private int lineIndex = 0;
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
          lineIndex = 0;
          if (build) {
            inputField.setText("build ");
            e.consume();
          } else {
            inputField.setText("");
          }
          processInput(userInput);
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
          String[] strings = outputArea.getText().split("\n");
          if (lineIndex + 1 <= strings.length) {
            lineIndex++;
            inputField.setText(strings[strings.length - lineIndex]);
          }
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
          String[] strings = outputArea.getText().split("\n");
          if (lineIndex - 1 >= 0) {
            lineIndex--;
            inputField.setText(strings[strings.length - lineIndex - 1]);
          } else {
            inputField.setText("build");
          }
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
      /* settigns hier einfügen */ /////
      if (true) {
        // hier einstellunge einfügen
        print("keine Einstellugen vorhanden");
      }

    } else if (build) {
      input = input.replaceAll("build\\s*", "");
      if (game.getMap() == null) {
        print("erst ein Level Starten");
      } else if (input.matches("^-n [a-zA-Z0-9,; ]*$")) { // neues Element hinzufügen
        game.getMap().getMapwriter().addMapElement(input.replaceAll("-n ", ""));
      } else if (input.contains("-m")) { // schon bestehendes Element bewegen
        try {
          game.getMap().getMapwriter().changeMapelementPosition(input.replaceAll("\\s*-m\\s*", ""));
        } catch (NoSuchCommandException e) {
          inputError(e.getMessage());
        } catch (InvalidIndexException e) {
          indexError(e.getMessage());
        }
      } else if (input.contains("-cd")) { // größe eine schon bestehenden elements verändern
        try {
          game.getMap().getMapwriter().changeMapelementDimension(input.replaceAll("\\s*-cd\\s*", ""));
        } catch (NoSuchCommandException e) {
          inputError(e.getMessage());
        } catch (InvalidIndexException e) {
          indexError(e.getMessage());
        }
      } else if (input.contains("-rm")) {
        try {
          game.getMap().getMapwriter().removeMapelement(input.replaceAll("\\s*-rm\\s*", ""));
        } catch (NoSuchCommandException e) {
          inputError(e.getMessage());
        } catch (InvalidIndexException e) {
          indexError(e.getMessage());
        } /* alle weiteren befehle, die zum bauen benötigt werden hier einfügen */
      } else if (input.matches("\\s*help\\s*")) { // -n dirt; 400, 500; 50, 50
        print(
            "-n {art des MapObjects}; {x-Koordinate}, {y.Koordinate}; {Breite}, {Hoehe}       Erzeugt ein neues MapObjet an gegebener Stelle\n"
                +
                "-m {index des Objekts}, {x-Koordinate}, {y-Koordinate}                         Bewegt Objekt mit index zu gegebenen Koorinaten\n"
                +
                "-m {index des Objekts}, x{+ oder - zu Verschiebener Weg}                       Bewegt da Objet um übergebenen Wert auf der x_Achse\n"
                +
                "-m {index des Objekts}, y{+ oder - zu verschiebener Weg}                       Bewegt das Objekt um übegebene wert auf der y-Achse\n"
                +
                "-cd {index des Objekts}, {nueue Breite}, {neue Hoehe}                          Setzt Breite und Hoehe auf übergebene werte\n"
                +
                "-cd {indes des Objekts}, w{+ oder - um was die Breite geänder werden soll}     Ändert die Breite es Pbjekts um übergebenen Wert\n"
                +
                "-cd {indes des Objekts}, w{+ oder - um was die Hoehe geänder werden soll}      Ändert die Hoehe es Pbjekts um übergebenen Wert\n"
                +
                "-rm {index des Objekts}                                                        Löscht Objekt");
      } else if (input.matches("^\\s*exit\\s*")) {
        build = false;
        game.buildMode = false;
        inputField.setText("");
      } else {
        inputError(input);
      }
    } else if (input.equals("highlight player")) {
      game.getMap().getPlayer().highlight();
    } else if (input.matches("highlight mapelement \\d+$")) {
      game.getMap().getAllObjects().get(Integer.parseInt(input.replaceAll("[a-z]", "").replaceAll(" ", "")))
          .highlight();
    } else if (input.equals("hide")) {
      setVisible(false);
    } else if (input.equals("build")) { // auswahl der Verschiedenen Modi
      build = true;
      game.buildMode = true;
      game.buildMode = true;
      settings = false;
      inputField.setText("build ");
    } else if (input.equals("settings")) {
      settings = true;
      build = false;

    } else {
      inputError(input);
    }
  }

  private void inputError(String input) {
    print("Der Befehl " + input + " ist entweder falsch geschrieben oder konnte nicht gefunden werden.");
  }

  private void indexError(String input) {
    print("Der Index " + input + " existiert nicht");
  }
}
