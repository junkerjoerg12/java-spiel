package de.junkerjoerg12.scenes;

import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import java.awt.GridBagConstraints;

import javax.swing.JPanel;

import de.junkerjoerg12.Game;

import java.awt.Graphics;

public class Lvlauswahl extends JPanel implements ActionListener {

  private ArrayList<JButton> buttonlvl = new ArrayList<>();
  private JButton back;
  // wenn noch andere buttons oder sachen, die ein ActionListener brauchen
  // hinzugefügt werden dringend Action Performed methode überarbeiten

  private Image backgroundImage;
  private Game game;
  private File mapsDir;

  private String currentMap = "map1.txt";

  public Lvlauswahl(Game game) {
    this.game = game;
    this.setLayout(new GridBagLayout());
    this.setVisible(true);

    try {
      backgroundImage = ImageIO
          .read(new File(Paths.get("src", "main", "resources", "MainMenu-Background.png").toString()));
      mapsDir = new File(Paths.get("maps").toString());
    } catch (Exception e) {
      e.printStackTrace();
    }
    String[] dirsAndFiles = mapsDir.list();
    ArrayList<String> dirs = new ArrayList<>();

    for (String string : dirsAndFiles) {
      if (!string.contains(".")) {
        dirs.add(string);
      }
    }

    GridBagConstraints constraints = new GridBagConstraints();
    constraints.anchor = GridBagConstraints.LAST_LINE_START;
    constraints.gridx = 0;
    constraints.gridy = 0;
    constraints.fill = GridBagConstraints.BOTH;

    System.out.println("hallo");
    for (String string : dirs) {
      System.out.println(string);
    }
    System.out.println("hallo");

    for (String dirName : dirs/* level ordner im maps ordner */) {
      System.out.println("hallo");
      JButton button = new JButton(dirName);
      button.addActionListener(this);
      buttonlvl.add(button);
      constraints.gridy += 1;
      this.add(button, constraints);
    }
    // buttonlvl1 = new JButton("lvl1");
    back = new JButton("back to main menu");

    back.addActionListener(this);

    constraints.gridx = 0;
    constraints.gridy += 1;
    this.add(back, constraints);

  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == back) {
      game.remove(this);
      game.mainMenu();
    } else {
      Leveldetails lvldetails = new Leveldetails(game,
          Paths.get("maps", ((JButton) e.getSource()).getText(), currentMap).toString());
      game.switchScene(this, lvldetails);
    }
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.drawImage(backgroundImage, 0, 0, this);
  }

}
