package de.junkerjoerg12.tools;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import de.junkerjoerg12.map.mapElements.MapElement;

public class Mapreader {
    private BufferedReader reader;

    public Mapreader(String filepath) throws IOException {
        reader = new BufferedReader(new FileReader(filepath));

    }

    public void setFilepath(String filepath) throws IOException {
        reader = new BufferedReader(new FileReader(filepath));
    }

    public MapElement[] read() {
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

        return (MapElement[]) elements.toArray();
    }

    private MapElement process(String line) {

    }
}
