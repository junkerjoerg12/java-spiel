package de.junkerjoerg12.map;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JPanel;

import de.junkerjoerg12.PhysicsObject;
import de.junkerjoerg12.character.Enemy;
import de.junkerjoerg12.character.Player;
import de.junkerjoerg12.map.mapElements.Floor;
import de.junkerjoerg12.map.mapElements.MapElement;
import de.junkerjoerg12.map.mapElements.Wall;

public class Map extends JPanel {
    // wenn irgendetwas keien Kollision haben soll einfach noch iene andere Liste
    // machen, die auf kollision überprüft wird und die hier nicht mehr überprüfen
    private ArrayList<PhysicsObject> allObjects = new ArrayList<>();
    private ArrayList<Enemy> enemies = new ArrayList<>();

    private Player player;

    public Map() {
        this.setBackground(Color.GRAY);
        this.setLayout(null);

        build();
    }

    private void build() {
        player = new Player(this);
        this.add(player);

        MapElement temp = new Floor(this);
        temp.setBounds(0, 1000, 1920, 80);
        this.add(temp);
        allObjects.add(temp);

        temp = new Floor(this);
        temp.setBounds(0, 0, 1920, 20);
        this.add(temp);
        allObjects.add(temp);

        temp = new Wall(this);
        temp.setBounds(0, 0, 10, 1080);
        this.add(temp);
        allObjects.add(temp);

        temp = new Wall(this);
        temp.setBounds(1910, 0, 10, 1080);
        this.add(temp);
        allObjects.add(temp);

    }

    public ArrayList<PhysicsObject> getAllObjects() {
        return allObjects;
    }

    public Player getPlayer() {
        return player;
    }
}
