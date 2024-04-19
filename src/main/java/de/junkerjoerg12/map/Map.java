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

    public Map(Player player) {
        this.setBackground(Color.GRAY);
        this.setLayout(null);

        this.player = player;
        this.add(player);

        build();
    }

    private void build() {

        MapElement temp = new Floor();
        temp.setBounds(0, 1000, 1920, 80);
        this.add(temp);
        allObjects.add(temp);

    }

    public void tick() {
        //wird in jedem game tick einmal aufgerufen um alles zu berechnen
        player.printVelocity();
        player.calculatePosition();


    }
}
