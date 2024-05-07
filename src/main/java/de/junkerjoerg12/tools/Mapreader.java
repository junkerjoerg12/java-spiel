package de.junkerjoerg12.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import de.junkerjoerg12.Game;
import de.junkerjoerg12.map.Map;
import de.junkerjoerg12.map.mapElements.Floor;
import de.junkerjoerg12.map.mapElements.MapElement;
import de.junkerjoerg12.map.mapElements.Wall;

public class Mapreader {

    private BufferedReader reader;
    Map map;
    Game game;

    public Mapreader(Game game) {
        this.game = game;
        this.map = game.getMap();

    }
    public void setFilepath(String filepath) throws FileNotFoundException {
        if (new File(filepath).exists()) {
            reader = new BufferedReader(new FileReader(filepath));
        } else {
            throw new FileNotFoundException();
        }
    }

    public ArrayList<MapElement> read() {
        String line;
        ArrayList<MapElement> elements = new ArrayList<>();
        try {
            while ((line = reader.readLine()) != null) {
                elements.add(process(line));
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return elements;
    }

    private MapElement process(String line) {
        String [] objectSomething = line.split(";");
        
        MapElement mapelement;


        if (objectSomething[0].equals("Floor")) {
            mapelement = new Floor(game);
        } else {
            mapelement = new Wall(game);
        }   

        String[] coordinates = objectSomething[1].split(",");
        String[] dimesnsions = objectSomething[2].split(",");
        mapelement.setBounds(Integer.parseInt(coordinates[0].strip()), Integer.parseInt(coordinates[1].strip()), Integer.parseInt(dimesnsions[0].strip()), Integer.parseInt(dimesnsions[1].strip()));

        return mapelement;
    }
}
