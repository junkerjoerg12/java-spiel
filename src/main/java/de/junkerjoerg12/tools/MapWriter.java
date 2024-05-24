package de.junkerjoerg12.tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import de.junkerjoerg12.Game;
import de.junkerjoerg12.map.mapElements.MapElement;

public class MapWriter extends Thread {

    private Game game;
    private String filepath;
    private BufferedReader reader;
    private BufferedWriter writer;

    public MapWriter(Game game) {
        this.game = game;
    }

    public void run() {
    }

    public void setFilepath() {
        try {
            writer = new BufferedWriter(new FileWriter(filepath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addMApElement(MapElement m) {
        BufferedReader reader;
        String line = "";
        StringBuffer content = new StringBuffer("");
        try {
            reader = new BufferedReader(new FileReader(filepath));
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        content.append(mapElementToString(m));
    }

    private String mapElementToString(MapElement m) {
        StringBuffer b = new StringBuffer(
                m.getClass() + "; " + m.getX() + ", " + m.getY() + "; " + m.getHeight() + ", " + m.getHeight());

        for (String filepath : m.getImageFilepath()) {
            b.append("; " + filepath);
        }
        return b.toString();
    }

    public void stringToElement(String s) {
        MapElement m = game.getMap().getMapreader().process(s);
        addMApElement(m);
        game.getMap().add(m);
    }
}
