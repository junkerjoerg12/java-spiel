package de.junkerjoerg12.map;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JPanel;

import de.junkerjoerg12.PhysicsObject;
import de.junkerjoerg12.character.Enemy;
import de.junkerjoerg12.character.Player;
import de.junkerjoerg12.map.mapElements.Floor;
import de.junkerjoerg12.map.mapElements.MapElement;

public class Map extends JPanel {
    private ArrayList<PhysicsObject> allObjects = new ArrayList<>();
    private ArrayList<Enemy> enemies = new ArrayList<>();

    public Player player; //irgandwann mal private machen + getter Methode

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

    }

    public ArrayList<PhysicsObject> getAllObjects() {
        return allObjects;
    }

    public Player getPLayer() {
        return player;
    }
}
