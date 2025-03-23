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
import de.junkerjoerg12.scenes.Mapelementscene;
import de.junkerjoerg12.scenes.RectanglePanel;

public class Mapelementselect extends JFrame {

  private Game game;
  private JPanel selectscreen;
  public String selected = "";

  public Mapelementselect(Game game) {
    this.game = game;
    setTitle("Mapelement Selection Screen");
    setSize(500, 250);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    this.setLayout(new BorderLayout());
    this.add(new Mapelementscene(this));
    setVisible(true);
  }

}
