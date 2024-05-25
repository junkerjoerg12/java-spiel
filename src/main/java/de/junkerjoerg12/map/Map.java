package de.junkerjoerg12.map;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.JPanel;

import de.junkerjoerg12.Game;
import de.junkerjoerg12.PhysicsObject;
import de.junkerjoerg12.character.Enemy;
import de.junkerjoerg12.character.Player;
import de.junkerjoerg12.map.mapElements.MapElement;
import de.junkerjoerg12.tools.Mapreader;
import de.junkerjoerg12.tools.Mapwriter;

public class Map extends JPanel {
    // wenn irgendetwas keien Kollision haben soll einfach noch iene andere Liste
    // machen, die auf kollision überprüft wird und die hier nicht mehr überprüfen
    private ArrayList<PhysicsObject> allObjects = new ArrayList<>();
    private ArrayList<Enemy> enemies = new ArrayList<>();

    private Mapreader mapreader;
    private Mapwriter mapwriter;

    private Player player;
    private Game game;
    private String filepath;

    public Map(Game game, String filepath) {
        this.game = game;
        this.filepath = filepath;
        this.setBackground(Color.YELLOW);
        this.setLayout(null);
        this.setDoubleBuffered(true);
        mapreader = new Mapreader(game);
        mapwriter = new Mapwriter(game);
        build();
    }

    private void build() {
        player = new Player(game);
        this.add(player);

        mapreader.setFilepath(filepath); // "maps\\level1\\map1.txt"
        mapwriter.setFilepath(filepath);

        for (MapElement m : mapreader.read()) {
            allObjects.add(m);
        }
    }

    public void update() {
        for (PhysicsObject p : allObjects) {
            p.update();
        }
        for (Enemy e : enemies) {
            e.update();
        }
        player.update();
    }

    public void draw() {
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        for (PhysicsObject p : allObjects) {
            p.draw(g2D);
        }
        for (Enemy e : enemies) {
            e.draw(g2D);
        }
        player.draw(g2D);
        ;

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
}
