package de.junkerjoerg12.tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

import de.junkerjoerg12.Game;
import de.junkerjoerg12.Exceptions.InvalidIndexException;
import de.junkerjoerg12.Exceptions.NoSuchCommandException;
import de.junkerjoerg12.map.MapElement;

public class Mapwriter {

  private Game game;
  private File mapfile;
  private BufferedReader reader;
  private BufferedWriter writer;

  public Mapwriter(Game game) {
    this.game = game;
  }

  public void setFile(File file) {
    this.mapfile = file;
  }

  public StringBuffer readMap() { // reads inn the Map file for further processign
    String line = "";
    StringBuffer content = new StringBuffer("");
    try {
      reader = new BufferedReader(new FileReader(mapfile));
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
    map = map.replaceAll("  ", " ");
    try {
      writer = new BufferedWriter(new FileWriter(mapfile));
      writer.write(map.toString());
      writer.close();
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

  public void changeMapelementDimension(String s) throws NoSuchCommandException, InvalidIndexException {
    /* s sollte die Form "{nummer des Elements}, {width}, {height}" haben */
    if (s.matches("^\\s*\\d+\\s*,\\s*\\d+\\s*,\\s*\\d+")) {
      resizeMapelement(s);
    } else if (s.matches("^\\s*\\d+\\s*,\\s*w\\s*[+-]\\s*\\d+")) {
      changeWidth(s);
    } else if (s.matches("^\\s*\\d+\\s*,\\s*h\\s*[+-]\\s*\\d+")) {
      changeHeight(s);
    } else {
      throw new NoSuchCommandException("-cd " + s);
    }
  }

  public void changeMapelementPosition(String s) throws NoSuchCommandException, InvalidIndexException {

    if (s.matches("^\\d+\\s*,\\s*\\d+\\s*,\\s*\\d+")) {
      replaceMapelement(s);
    } else if (s.matches("^\\s*\\d+\\s*,\\s*x\\s*[+-]\\s*\\d+")) {
      changeX(s);
    } else if (s.matches("^\\s*\\d+\\s*,\\s*y\\s*[+-]\\s*\\d+")) {
      changeY(s);
    } else {
      throw new NoSuchCommandException("-m " + s);
    }
  }

  private void changeWidth(String s) throws InvalidIndexException {
    s = s.replaceAll("w", "").replaceAll(" ", "");
    /*
     * Form: {index des Objekts},+/-{Zahl, um die Height geändert werden soll}
     */
    String[] sArr = s.split(",");
    int element = Integer.parseInt(sArr[0]);
    int widthDifference = Integer.parseInt(sArr[1]);
    StringBuffer mapString = readMap();
    String[] lines = mapString.toString().split("\n");

    try {

      String[] targetLine = lines[element].split("; ");
      String[] dimension = targetLine[2].split(", ");
      lines[element] = targetLine[0] + "; " + targetLine[1]
          + "; " + (widthDifference + Integer.parseInt(dimension[0])) + ", " + (dimension[1]);

      mapString.setLength(0);
      for (String string : lines) {
        mapString.append(string);
        mapString.append("\n");
      }
      game.getMap().repalceMapelement(element, stringToElement(lines[element]));

    } catch (IndexOutOfBoundsException e) {
      throw new InvalidIndexException(element);
    }
    writeMap(mapString.toString());
  }

  private void changeHeight(String s) throws InvalidIndexException {
    s = s.replaceAll("h", "").replaceAll(" ", "");
    /*
     * Form: {index des Objekts},+/-{Zahl, um die Height geändert werden soll}
     */
    String[] sArr = s.split(",");
    int element = Integer.parseInt(sArr[0]);
    int heightDifference = Integer.parseInt(sArr[1]);
    StringBuffer mapString = readMap();
    String[] lines = mapString.toString().split("\n");

    try {
      String[] targetLine = lines[element].split("; ");
      String[] dimension = targetLine[2].split(", ");
      lines[element] = targetLine[0] + "; " + targetLine[1]
          + "; " + dimension[0] + ", " + (heightDifference + Integer.parseInt(dimension[1]));

      mapString.setLength(0);
      for (String string : lines) {
        mapString.append(string);
        mapString.append("\n");
      }
      game.getMap().repalceMapelement(element, stringToElement(lines[element]));

    } catch (IndexOutOfBoundsException e) {
      throw new InvalidIndexException(element);
    }
    writeMap(mapString.toString());
  }

  private void changeX(String s) throws InvalidIndexException {
    s = s.replaceAll("x", "").replaceAll(" ", "");
    /*
     * sollte jetzt nur noch
     * "{index des Objekts},+/_{zahl, um die verschoben werden soll}" enthalten
     */
    String[] sArr = s.split(",");
    int element = Integer.parseInt(sArr[0]);
    int xDifference = Integer.parseInt(sArr[1]);
    StringBuffer mapString = readMap();
    String[] lines = mapString.toString().split("\n");

    try {
      String[] targetLine = lines[element].split("; ");
      String[] position = targetLine[1].split(", ");
      lines[element] = targetLine[0] + "; " + (xDifference + Integer.parseInt(position[0])) + ", " + position[1]
          + "; " + targetLine[2];

      mapString.setLength(0);
      for (String string : lines) {
        mapString.append(string);
        mapString.append("\n");
      }
      game.getMap().repalceMapelement(element, stringToElement(lines[element]));

    } catch (IndexOutOfBoundsException e) {
      throw new InvalidIndexException(element);
    }
    writeMap(mapString.toString());
  }

  private void changeY(String s) throws InvalidIndexException {
    s = s.replaceAll("y", "").replaceAll(" ", "");
    /*
     * sollte jetzt nur noch
     * "{index des Objekts},+/_{zahl, um die verschoben werden soll}" enthalten
     */
    String[] sArr = s.split(",");
    int element = Integer.parseInt(sArr[0]);
    int yDifference = Integer.parseInt(sArr[1]);
    StringBuffer mapString = readMap();
    String[] lines = mapString.toString().split("\n");

    try {
      String[] targetLine = lines[element].split("; ");
      String[] position = targetLine[1].split(", ");
      lines[element] = targetLine[0] + "; " + position[0] + ", " + (yDifference + Integer.parseInt(position[1]))
          + ", "
          + "; " + targetLine[2];

      mapString.setLength(0);
      for (String string : lines) {
        mapString.append(string);
        mapString.append("\n");
      }
      game.getMap().repalceMapelement(element, stringToElement(lines[element]));

    } catch (IndexOutOfBoundsException e) {
      throw new InvalidIndexException(element);
    }

    writeMap(mapString.toString());
  }

  private void resizeMapelement(String s) throws InvalidIndexException {
    /*
     * Form: {Nummer des Elements}, {width}, {height}
     */
    String[] newDimension = s.split(", ");
    int element = Integer.parseInt(newDimension[0]);
    int width = Integer.parseInt(newDimension[1]);
    int height = Integer.parseInt(newDimension[2]);
    StringBuffer mapString = readMap();
    String[] lines = mapString.toString().split("\n");

    try {

      String[] targetLine = lines[element].split("; ");
      lines[element] = targetLine[0] + "; " + targetLine[1] + "; " + width + ", " + height;

      mapString.setLength(0);
      for (String string : lines) {
        mapString.append(string);
        mapString.append("\n");
      }
      writeMap(mapString.toString());

    } catch (IndexOutOfBoundsException e) {
      throw new InvalidIndexException(element);
    }
    game.getMap().repalceMapelement(element, stringToElement(lines[element]));
  }

  private void replaceMapelement(String s) throws InvalidIndexException { // platziert ein MapElement an einer neuen
                                                                          // Stelle
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

    try {
      String[] targetLine = lines[element].split("; ");
      lines[element] = targetLine[0] + "; " + x + ", " + y + "; " + targetLine[2];

      mapString.setLength(0);
      for (String string : lines) {
        mapString.append(string);
        mapString.append("\n");
      }
      game.getMap().repalceMapelement(element, stringToElement(lines[element]));

    } catch (IndexOutOfBoundsException e) {
      throw new InvalidIndexException(element);
    }
    writeMap(mapString.toString());
  }

  public void removeMapelement(String string) throws NoSuchCommandException, InvalidIndexException {
    string = string.trim();

    if (string.matches("^\\d+")) {

      StringBuffer mapString = readMap();
      String[] lines = mapString.toString().split("\n");
      int index = Integer.parseInt(string);
      mapString.setLength(0);
      try {
        for (int i = 0; i < lines.length; i++) {
          if (i != index) {
            mapString.append(lines[i]);
            mapString.append("\n");
          }
        }
        game.getMap().removeMapelement(index);
        writeMap(mapString.toString());
      } catch (IndexOutOfBoundsException e) {
        throw new InvalidIndexException(index);
      }

    } else {
      throw new NoSuchCommandException(string);
    }

  }

}
