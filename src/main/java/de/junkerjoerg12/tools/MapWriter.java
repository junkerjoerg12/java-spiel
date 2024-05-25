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

    public StringBuffer readMap() { // reads inn the Map file for further processign
        String line = "";
        StringBuffer content = new StringBuffer("");
        try {
            reader = new BufferedReader(new FileReader(filepath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            while ((line = reader.readLine()) != null) {
                content.append(line);
                content.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    private void writeMap(String map) { // writes the whole map String to the File
        try {
            writer = new BufferedWriter(new FileWriter(filepath));
            writer.write(map.toString());
            writer.close();
            System.out.println("Writes: " + map);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addMapElement(String s) {
        MapElement m = stringToElement(s);
        StringBuffer content = readMap();
        content.append(s.replace("new mapelement ", content));
        writeMap(content.toString());
        game.getMap().add(m);
    }

    public MapElement stringToElement(String s) {
        return game.getMap().getMapreader().process(s);
    }

    public void moveMapElements(String replaceAll) {
        throw new UnsupportedOperationException("Unimplemented method 'moveMapElements'");
    }

    public void changeMapelementDimension(String s) {
        /* s sollte die Form "{nummer des Elements}, {width}, {height}" haben */
        String[] newDimension = s.split(", ");
        int element = Integer.parseInt(newDimension[0]);
        int width = Integer.parseInt(newDimension[1]);
        int height = Integer.parseInt(newDimension[2]);
        StringBuffer mapString = readMap();
        String[] lines = mapString.toString().split("\n");

        String[] targetLine = lines[element].split("; ");
        lines[element] = targetLine[0] + "; " + targetLine[1] + "; " + width + ", " + height;

        mapString.setLength(0);
        for (String string : lines) {
            mapString.append(string);
            mapString.append("\n");
        }
        writeMap(mapString.toString());
        game.getMap().repalceMapelement(element, stringToElement(lines[element]));
    }

    public void changeMapelementPosition(String s) {
        /*
         * s sollte die Form "{nummer des Elements}, {x- Koordinate}, {y-Koordinate}"
         * haben
         */
        String[] newDimension = s.split(", ");
        int element = Integer.parseInt(newDimension[0]);
        int x = Integer.parseInt(newDimension[1]);
        int y = Integer.parseInt(newDimension[2]);
        StringBuffer mapString = readMap();
        String[] lines = mapString.toString().split("\n");

        String[] targetLine = lines[element].split("; ");
        lines[element] = targetLine[0] + "; " + x + ", " + y + "; " + targetLine[2];

        mapString.setLength(0);
        for (String string : lines) {
            mapString.append(string);
            mapString.append("\n");
        }
        writeMap(mapString.toString());
        game.getMap().repalceMapelement(element, stringToElement(lines[element]));
    }
    /*
     * private String mapElementToString(MapElement m) {
     * StringBuffer b = new StringBuffer(
     * m.getClass() + "; " + m.getX() + ", " + m.getY() + "; " + m.getHeight() +
     * ", " + m.getHeight());
     * 
     * for (String filepath : m.getImageFilepath()) {
     * b.append("; " + filepath);
     * }
     * return b.toString();
     * }
     */
}
