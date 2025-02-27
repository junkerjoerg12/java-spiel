package de.junkerjoerg12.scenes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

import de.junkerjoerg12.Game;
import de.junkerjoerg12.tools.Statreader;

import java.nio.file.Paths;
import java.util.ArrayList;

public class MainMenu extends JPanel implements ActionListener {

  private JButton start;
  private JButton settings;
  private JButton characterselect;
  private JButton quit;

  private Image backgroundImage;

  private Game game;

  public MainMenu(Game game) {

    this.setLayout(new GridBagLayout());
    this.setVisible(true);
    this.setBackground(Color.GRAY);

    this.game = game;

    try {
      backgroundImage = ImageIO
          .read(new File(
              Paths.get("src", "main", "resources", "assets", "trying.png").toString()));
    } catch (Exception e) {
      e.printStackTrace();
    }
    GridBagConstraints constraints = new GridBagConstraints();
    constraints.gridx = 1;
    constraints.gridy = 1;

    start = new JButton("Start Game");
    start.setFocusable(false);
    this.add(start, constraints);
    start.addActionListener(this);

    constraints.gridy = 2;
    characterselect = new JButton("Character");
    characterselect.setFocusable(false);
    this.add(characterselect, constraints);
    characterselect.addActionListener(this);

    constraints.gridy = 3;
    settings = new JButton("Settings");
    settings.setFocusable(false);
    this.add(settings, constraints);
    settings.addActionListener(this);

    constraints.gridy = 4;
    quit = new JButton("Quit");
    quit.setFocusable(false);
    this.add(quit, constraints);
    quit.addActionListener(this);

  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.drawImage(backgroundImage, 0, 0, this);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == quit) {
      System.exit(0);
    } else if (e.getSource() == start) {
      game.levelauswahl();
    } else if (e.getSource() == settings) {
      Settings settings = new Settings(game);
      game.switchScene(this, settings);
    } else if (e.getSource() == characterselect) {
      game.setCharacterselect();
    }
  }
}
