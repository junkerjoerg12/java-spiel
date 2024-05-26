package de.junkerjoerg12.tools;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import de.junkerjoerg12.Game;
import de.junkerjoerg12.map.mapElements.Floor;
import de.junkerjoerg12.map.mapElements.MapElement;
import de.junkerjoerg12.map.mapElements.Wall;

public class Mapreader {

    private BufferedReader reader;
    private Game game;

    public Mapreader(Game game) {
        this.game = game;
    }

    public void setFilepath(String filepath) {
        try {
            reader = new BufferedReader(new FileReader(filepath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
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
            e.printStackTrace();
        }
        return elements;
    }

    public MapElement process(String line) {
        String[] objectSomething = line.split(";");

        MapElement mapelement;
        if (objectSomething[0].equals("floor")) {
            mapelement = new Floor(game);
        } else {
            mapelement = new Wall(game);
        }

        String[] coordinates = objectSomething[1].split(",");
        String[] dimesnsions = objectSomething[2].split(",");
        mapelement.setBounds(Integer.parseInt(coordinates[0].strip()), Integer.parseInt(coordinates[1].strip()),
                Integer.parseInt(dimesnsions[0].strip()), Integer.parseInt(dimesnsions[1].strip()));

        return mapelement;
    }
}
