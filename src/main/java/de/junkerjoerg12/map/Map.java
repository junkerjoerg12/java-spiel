package de.junkerjoerg12.map;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import de.junkerjoerg12.Game;
import de.junkerjoerg12.PhysicsObject;
import de.junkerjoerg12.character.Enemy;
import de.junkerjoerg12.character.Player;
import de.junkerjoerg12.tools.Mapreader;
import de.junkerjoerg12.tools.Mapwriter;

public class Map extends JPanel {
    /*
    * wenn irgendetwas keien Kollision haben soll einfach noch iene andere Liste
    * machen, die auf kollision überprüft wird und die hier nicht mehr überprüfen
    * oder noch nen boolean "kollision" machen, und wenn der false ist wird das Stück in der Schlife übergangen
    */
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
        this.setBackground(Color.CYAN);
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
        
        ArrayList<MapElement> list = mapreader.read();

        int size = list.size();
        for (int i = 0; i < size; i++) {
            allObjects.add(list.get(i));
        }
    }

    

    public void draw() {
        repaint();
        // repaint(player.getX(), player.getY(), player.getWidth(), player.getHeight());    macht die perfromace auch nicht besser
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
//Die beiden Methoden vielleicht zusammenlegen, fpr einen loopdurchgang weniger
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
        // g2D.drawRect(player.getX(), player.getY(), player.getWidth(), player.getHeight());
        g.setColor(Color.BLACK);
        g.drawString("timer", 1800, 100);//timer anzeigen
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

    
}
