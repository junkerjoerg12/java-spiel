package de.junkerjoerg12.tools;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import de.junkerjoerg12.Game;
import javafx.scene.input.KeyEvent;

public class Mapelementselect extends JFrame{

private Game game;


public Mapelementselect(Game game) {
    this.game = game;
    setTitle("Mapelement Selection Screen");
    setSize(500, 500);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setVisible(true);
  }
    
}
