package de.junkerjoerg12.map;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import javax.swing.JPanel;

import de.junkerjoerg12.PhysicsObject;
import de.junkerjoerg12.character.Enemy;
import de.junkerjoerg12.character.Player;
import de.junkerjoerg12.map.mapElements.Floor;
import de.junkerjoerg12.map.mapElements.MapElement;
import de.junkerjoerg12.map.mapElements.Wall;
import de.junkerjoerg12.tools.Mapreader;

public class Map extends JPanel {
    // wenn irgendetwas keien Kollision haben soll einfach noch iene andere Liste
    // machen, die auf kollision überprüft wird und die hier nicht mehr überprüfen
    private ArrayList<PhysicsObject> allObjects = new ArrayList<>();
    private ArrayList<Enemy> enemies = new ArrayList<>();
    private BufferedReader reader;
    private Mapreader = new Mapreader();

    private Player player;

    public Map() {
        this.setBackground(Color.GRAY);
        this.setLayout(null);
        try {
            reader = new BufferedReader(new FileReader("maps\\progress"));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        build();
    }

    private void build() {
        player = new Player(this);
        this.add(player);

        // MapElement temp = new Floor(this);
        // temp.setBounds(0, 1000, 1920, 80);
        // this.add(temp);
        // allObjects.add(temp);

        // temp = new Floor(this);
        // temp.setBounds(0, 0, 1920, 20);
        // this.add(temp);
        // allObjects.add(temp);

        // temp = new Wall(this);
        // temp.setBounds(0, 0, 10, 1080);
        // this.add(temp);
        // allObjects.add(temp);

        // temp = new Wall(this);
        // temp.setBounds(1910, 0, 10, 1080);
        // this.add(temp);
        // allObjects.add(temp);

        // temp = new Floor(this);
        // temp.setBounds(0, 900, 200, 10);
        // this.add(temp);
        // allObjects.add(temp);

    }

    public ArrayList<PhysicsObject> getAllObjects() {
        return allObjects;
    }

    public Player getPlayer() {
        return player;
    }
}
