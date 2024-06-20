package de.junkerjoerg12.map;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.io.File;

import javax.swing.JPanel;

import de.junkerjoerg12.Game;
import de.junkerjoerg12.PhysicsObject;
import de.junkerjoerg12.character.Enemy;
import de.junkerjoerg12.character.Player;
import de.junkerjoerg12.tools.Mapreader;
import de.junkerjoerg12.tools.Mapwriter;

public class Map extends JPanel {
  private ArrayList<PhysicsObject> allObjects = new ArrayList<>();
  private ArrayList<Enemy> enemies = new ArrayList<>();

  private Mapreader mapreader;
  private Mapwriter mapwriter;

  private Player player;
  private Game game;

  private File mapfile;

  private Font timerFont = new Font("TimesRoman", Font.PLAIN, 20);

  public Map(Game game, File mapfile) {
    this.mapfile = mapfile;
    this.game = game;
    this.setBackground(Color.CYAN);
    this.setLayout(null);
    this.setDoubleBuffered(true);
    mapreader = new Mapreader(game);
    mapwriter = new Mapwriter(game);
  }

  public void build() {
    player = new Player(game);
    this.add(player);

    mapreader.setFile(mapfile);
    mapwriter.setFile(mapfile);

    ArrayList<MapElement> list = mapreader.read();

    int size = list.size();
    for (int i = 0; i < size; i++) {
      allObjects.add(list.get(i));
    }
  }

  public void draw() {
    repaint();
  }

  public void update() {
    int sizeAllObjects = allObjects.size();
    for (int i = 0; i < sizeAllObjects; i++) {
      allObjects.get(i).update();
    }
    int sizeEnemies = enemies.size();
    for (int i = 0; i < sizeEnemies; i++) {
      enemies.get(i).update();
    }
    player.update();
    game.updates++;
  }

  // Die beiden Methoden vielleicht zusammenlegen, für einen loopdurchgang weniger
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2D = (Graphics2D) g;
    int sizeAllObjects = allObjects.size();
    for (int i = 0; i < sizeAllObjects; i++) {
      allObjects.get(i).draw(g2D);
    }
    int sizeEnemies = enemies.size();
    for (int i = 0; i < sizeEnemies; i++) {
      enemies.get(i).draw(g2D);
    }
    player.draw(g2D);
    g.setColor(Color.BLACK);

    String strLongs = Long.toString(game.getcurrents());
    String strLongms = Long.toString(game.getcurrentms());
    String strLongmin = Long.toString(game.getcurrentmin());

    g.setFont(timerFont);

    g.drawString("Time: " + strLongmin + ":" + strLongs + "," + strLongms, 1690, 20);// timer anzeigen

  }

  public ArrayList<PhysicsObject> getAllObjects() {
    return allObjects;
  }

  public Player getPlayer() {
    return player;
  }

  public Game getGame() {
    return game;
  }

  public void add(Player p) {
    this.player = p;
  }

  public void add(MapElement m) {
    allObjects.add(m);
  }

  public void add(Enemy e) {
    enemies.add(e);
  }

  public void addNew(Enemy e) {
    enemies.add(e);
    // mapwriter.addMApElement(e);
  }

  public void removeMapelement(int index) {
    allObjects.remove(index);
  }

  public void addNew(String m) {
    mapwriter.addMapElement(m);
  }

  public Mapreader getMapreader() {
    return mapreader;
  }

  public Mapwriter getMapwriter() {
    return mapwriter;
  }

  public void repalceMapelement(int index, MapElement newElement) {
    allObjects.set(index, newElement);
  }

  public File getMapfile() {
    return mapfile;
  }

}
