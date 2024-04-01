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

    private Player player;

    public Map() {
        this.setBackground(Color.GRAY);
        this.setLayout(null);

        build();
    }

    private void build() {

        this.player = new Player();

        MapElement temp = new Floor();
        temp.setBounds(0, 1000, 1920, 80);
        this.add(temp);
        allObjects.add(temp);

        spawnPlayer();
    }

    private void spawnPlayer() {
        player = new Player();
        this.add(player);
    }
}
