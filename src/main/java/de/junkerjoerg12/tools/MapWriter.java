package de.junkerjoerg12.tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import de.junkerjoerg12.Game;
import de.junkerjoerg12.map.mapElements.MapElement;

public class Mapwriter extends Thread {

    private Game game;
    private String filepath;
    private BufferedReader reader;
    private BufferedWriter writer;

    public Mapwriter(Game game) {
        this.game = game;
    }

    public void run() {
    }

    public void setFilepath(String fielpath) {
        this.filepath = fielpath;
    }

    public void addMapElement(String s) {
        MapElement m = stringToElement(s);
        String line = "";
        StringBuffer content = new StringBuffer("");
        try {
            reader = new BufferedReader(new FileReader(filepath));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            while ((line = reader.readLine()) != null) {
                System.out.println("Was schon im Dokument steht: " + line);
                content.append(line);
                content.append("\n");
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        content.append(s.replace("new mapelement ", content));
        try {
            writer = new BufferedWriter(new FileWriter(filepath));
            writer.write(content.toString());
            writer.close();
            System.out.println("Writes: "+ content);
        } catch (IOException e) {
            e.printStackTrace();
        }
        game.getMap().add(m);
    }

    private String mapElementToString(MapElement m) {
        StringBuffer b = new StringBuffer(
                m.getClass() + "; " + m.getX() + ", " + m.getY() + "; " + m.getHeight() + ", " + m.getHeight());

        for (String filepath : m.getImageFilepath()) {
            b.append("; " + filepath);
        }
        return b.toString();
    }

    public MapElement stringToElement(String s) {
        MapElement m = game.getMap().getMapreader().process(s);
        return m;
    }
}
