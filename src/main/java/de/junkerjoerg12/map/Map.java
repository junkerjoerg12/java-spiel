package de.junkerjoerg12.map;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

import de.junkerjoerg12.Game;
import de.junkerjoerg12.PhysicsObject;
import de.junkerjoerg12.character.Enemy;
import de.junkerjoerg12.character.Player;
import de.junkerjoerg12.scenes.Endscreen;
import de.junkerjoerg12.scenes.Failscreen;
import de.junkerjoerg12.scenes.Leveldetails;
import de.junkerjoerg12.tools.Mapelementselect;
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
  private Image backgroundImage;
  private static File mapsDir;
  private static String[] dirsAndFiles;
  private static ArrayList<String> maps;

  private Point startPoint = null;
  private Point endPoint = null;
  private int x1;
  private int y1;
  private int width1;
  private int height1;
  private boolean mousereleased = true;

  private Font timerFont = new Font("TimesRoman", Font.PLAIN, 20);

  public Map(Game game, File mapfile) {
    this.mapfile = mapfile;
    this.game = game;
    this.setBackground(Color.orange);
    this.setLayout(null);
    this.setDoubleBuffered(true);
    mapreader = new Mapreader(game);
    mapwriter = new Mapwriter(game);
    this.addMouseListener(mouseAdapter);
    this.addMouseMotionListener(mouseAdapter);

  }

  MouseAdapter mouseAdapter = new MouseAdapter() {

    @Override
    public void mousePressed(MouseEvent e) {
      mousereleased = false;
      if (game.getmapelementselect() != null && game.getmapelementselect().selected != "") {
        startPoint = e.getPoint();
        endPoint = null;
        repaint();
      }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
      if (game.getmapelementselect() != null && game.getmapelementselect().selected != "") {
        endPoint = e.getPoint();
        repaint();
      }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
      if (game.getmapelementselect() != null && game.getmapelementselect().selected != "") {
        mousereleased = true;
        endPoint = e.getPoint();
        int x = Math.min(startPoint.x, endPoint.x);
        int y = Math.min(startPoint.y, endPoint.y);
        int width = Math.abs(startPoint.x - endPoint.x);
        int height = Math.abs(startPoint.y - endPoint.y);
        game.getMap().getMapwriter()
            .addMapElement(game.getmapelementselect().selected + ";" + x + ", " + y + "; " + width + ", " + height);
        repaint();
      }
    }
  };

  public void build() {
    try {
      backgroundImage = ImageIO
          .read(new File(
              Paths.get("src", "main", "resources", "assets", "trying.png").toString()));

    } catch (Exception e) {
      e.printStackTrace();
    }

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
    player.update();
    int sizeAllObjects = allObjects.size();
    for (int i = 0; i < sizeAllObjects; i++) {
      allObjects.get(i).update();
    }
    int sizeEnemies = enemies.size();
    for (int i = 0; i < sizeEnemies; i++) {
      enemies.get(i).update();
    }
    game.updates++;
  }

  // Die beiden Methoden vielleicht zusammenlegen, für einen loopdurchgang weniger
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.drawImage(backgroundImage, 0, 0, this);
    Graphics2D g2D = (Graphics2D) g;
    int sizeAllObjects = allObjects.size();
    for (int i = 0; i < sizeAllObjects; i++) {
      allObjects.get(i).draw(g2D);
    }
    int sizeEnemies = enemies.size();
    for (int i = 0; i < sizeEnemies; i++) {
      enemies.get(i).draw(g2D);
    }

    g.setColor(Color.BLACK);

    String strLongs = Long.toString(game.getcurrents());
    String strLongms = Long.toString(game.getcurrentms());
    String strLongmin = Long.toString(game.getcurrentmin());

    g.setFont(timerFont);
    g.setColor(Color.WHITE);

    if (game.getmapelementselect() != null) {
      if (startPoint != null && endPoint != null && mousereleased == false) {
        g.setColor(Color.BLACK); // Farbe des Rechtecks beim ziehen

        x1 = Math.min(startPoint.x, endPoint.x);
        y1 = Math.min(startPoint.y, endPoint.y);
        width1 = Math.abs(startPoint.x - endPoint.x);
        height1 = Math.abs(startPoint.y - endPoint.y);

        g.drawRect(x1, y1, width1, height1);
      }
    }

    if (game.easybuildmodeON == false) { // wird nicht gemalt wenn man im easy build mode ist
      player.draw(g2D);
      g.drawString("Time: " + strLongmin + ":" + strLongs + "," + strLongms, 1690, 20);// timer anzeigen
    }

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
    // mapwriter.addMapElement(e);
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

  public void replaceMapelement(int index, MapElement newElement) {
    allObjects.set(index, newElement);
  }

  public File getMapfile() {
    return mapfile;
  }

  public static void setbackroundmapForFail(Failscreen panel) throws IOException {
    maps = new ArrayList<>();
    try {
      mapsDir = new File(Paths.get("maps").toString());
    } catch (Exception e) {
      e.printStackTrace();
    }

    dirsAndFiles = mapsDir.list();
    ArrayList<String> dirs = new ArrayList<>();

    for (String string : dirsAndFiles) {
      if (!string.contains(".")) {
        dirs.add(string);
      }
    }
    for (String dirName : dirs/* level ordner im maps ordner */) {
      maps.add(dirName);
    }

    String nameofmap = panel.getMapfile().toString();
    for (int i = 1; i <= maps.size(); i++) {
      if (nameofmap.contains("level" + i)) {
        panel.setBackground(
            ImageIO.read(new File(Paths.get("src", "main", "resources", "map" + i + ".png").toString())));
      }
    }
  }

  public static void setbackroundmapForEnd(Endscreen panel) throws IOException {
    maps = new ArrayList<>();
    try {
      mapsDir = new File(Paths.get("maps").toString());
    } catch (Exception e) {
      e.printStackTrace();
    }

    dirsAndFiles = mapsDir.list();
    ArrayList<String> dirs = new ArrayList<>();

    for (String string : dirsAndFiles) {
      if (!string.contains(".")) {
        dirs.add(string);
      }
    }
    for (String dirName : dirs/* level ordner im maps ordner */) {
      maps.add(dirName);
    }

    String nameofmap = panel.getMapfile().toString();
    for (int i = 1; i <= maps.size(); i++) {
      if (nameofmap.contains("level" + i)) {
        panel.setBackground(
            ImageIO.read(new File(Paths.get("src", "main", "resources", "map" + i + ".png").toString())));
      }
    }
  }

  public static void setbackroundmapForlvl(Leveldetails panel) throws IOException {
    maps = new ArrayList<>();
    try {
      mapsDir = new File(Paths.get("maps").toString());
    } catch (Exception e) {
      e.printStackTrace();
    }

    dirsAndFiles = mapsDir.list();
    ArrayList<String> dirs = new ArrayList<>();

    for (String string : dirsAndFiles) {
      if (!string.contains(".")) {
        dirs.add(string);
      }
    }
    for (String dirName : dirs/* level ordner im maps ordner */) {
      maps.add(dirName);
    }

    String nameofmap = panel.getMapfile().toString();

    for (int i = 1; i <= maps.size(); i++) {
      if (nameofmap.contains("level" + i)) {
        panel.setBackground(
            ImageIO.read(new File(Paths.get("src", "main", "resources", "map" + i + ".png").toString())));
      }
    }

  }

}
