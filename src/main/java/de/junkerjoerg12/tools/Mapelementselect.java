package de.junkerjoerg12.tools;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.BorderLayout;

import de.junkerjoerg12.Game;
import javafx.scene.input.KeyEvent;

public class Mapelementselect extends JFrame{

private Game game;
private JPanel selectscreen;

public Mapelementselect(Game game) {
    this.game = game;
    setTitle("Mapelement Selection Screen");
    setSize(500, 500);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    this.setLayout(new BorderLayout());
   
    
 



    setVisible(true);
  }
    
}
