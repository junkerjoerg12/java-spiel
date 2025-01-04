package de.junkerjoerg12.tools;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import de.junkerjoerg12.Game;
import de.junkerjoerg12.map.MapElement;
import de.junkerjoerg12.map.mapElements.*;

public class Mapreader {

  private BufferedReader reader;
  private Game game;

  public Mapreader(Game game) {
    this.game = game;
  }

  public void setFile(File file) {
    try {
      reader = new BufferedReader(new FileReader(file));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  public ArrayList<MapElement> read() {
    String line;
    ArrayList<MapElement> elements = new ArrayList<>();
    try {
      while ((line = reader.readLine()) != null) {
        elements.add(process(line.trim()));
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return elements;
  }

  public MapElement process(String line) {
    String[] objectSomething = line.split(";");

    MapElement mapelement = null;
    String name = objectSomething[0];
    if (name.equals("water")) {
      mapelement = new Water(game);
    } else if (name.equals("dirt")) {
      mapelement = new Dirt(game);
    } else if (name.equals("stone")) {
      mapelement = new Stone(game);
    } else if (name.equals("grass")) {
      mapelement = new Grass(game);
    } else if (name.equals("goal")) {
      mapelement = new Goal(game);
    } else if (name.equals("Floor")) {
      mapelement = new Floor(game);
    } else if (name.equals("trap")) {
      mapelement = new Trap(game);
    }

    String[] coordinates = objectSomething[1].split(",");
    String[] dimesnsions = objectSomething[2].split(",");
    if (mapelement != null) {

      mapelement.setBounds(Integer.parseInt(coordinates[0].trim()), Integer.parseInt(coordinates[1].trim()),
          Integer.parseInt(dimesnsions[0].trim()), Integer.parseInt(dimesnsions[1].trim()));
    }

    return mapelement;
  }
}
