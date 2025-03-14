package de.junkerjoerg12.scenes;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

import de.junkerjoerg12.Game;
import de.junkerjoerg12.map.Map;
import de.junkerjoerg12.tools.Statreader;
import de.junkerjoerg12.tools.Statwriter;

public class Endscreen extends JPanel implements ActionListener {
  private Game game;
  private Font timerFont = new Font("TimesRoman", Font.PLAIN, 20);
  private JButton back = new JButton("back to Menu");
  private JButton retry = new JButton("play again");
  private String time = "";
  private File mapfile;

  private long min;
  private long sec;
  private long ms;

  private BufferedImage background;

  public Endscreen(Game game, Long min, long seconds, Long ms, File mapfile) {
    this.game = game;
    this.min = min;
    this.sec = seconds;
    this.ms = ms;
    this.mapfile = mapfile;
    this.setLayout(new GridBagLayout());
    back.addActionListener(this);
    retry.addActionListener(this);

    try {
      Map.setbackroundmapForEnd(this);
    } catch (IOException e) {
      e.printStackTrace();
    }

    GridBagConstraints constraints = new GridBagConstraints();
    constraints.gridy = 1;
    this.add(back, constraints);
    constraints.gridx = 1;

    time = "Your Time: " + min + ":" + seconds + "," + ms;
    compareToBest();
    repaint();

  }

  private void compareToBest() {
    ArrayList<String> best = new Statreader(mapfile).getbest();

    long bestMin = Long.parseLong(best.get(0));
    long bestSec = Long.parseLong(best.get(1));
    long bestMs = Long.parseLong(best.get(2));

    if (bestMin == 0 && bestSec == 0 && bestMs == 0) {// if all 0 no highscore was set yet
      new Statwriter(min + "\n" + sec + "\n" + ms, mapfile);
      time += "\n new best";
    } else if (min < bestMin) {
      new Statwriter(min + "\n" + sec + "\n" + ms, mapfile);
      time += "\n new best";
    } else if (min == bestMin && sec < bestSec) {
      new Statwriter(min + "\n" + sec + "\n" + ms, mapfile);
      time += "\n new best";
    } else if (min == bestMin & sec == bestSec && ms < bestMs) {
      new Statwriter(min + "\n" + sec + "\n" + ms, mapfile);
      time += "\n new best";
    }

  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == back) {
      game.remove(this);
      game.levelauswahl();
    }
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.drawImage(background, 0, 0, null);
    g.setFont(timerFont);
    FontMetrics metrics = g.getFontMetrics();
    String[] texts = time.split("\n");
    g.setColor(Color.white);
    for (int i = 0; i < texts.length; i++) {
      String s = texts[i];
      int x = (game.getMap().getWidth() - metrics.stringWidth(s)) / 2;
      g.drawString(s, x, 600 + metrics.getHeight() * i);

    }
  }

  public File getMapfile() {
    return mapfile;
  }

  public void setBackground(BufferedImage image) {
    background = image;
  }
}
