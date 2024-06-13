package de.junkerjoerg12.tools;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

import de.junkerjoerg12.Game;
import de.junkerjoerg12.map.MapElement;

public class Statreader {

    private BufferedReader reader;
    private ArrayList<String> time = new ArrayList<>();

    public Statreader() {
        try {
            reader = new BufferedReader(new FileReader("src//main//resources//stats.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void process() {
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                time.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getbest() {
        process();
        return time;
    }

}
