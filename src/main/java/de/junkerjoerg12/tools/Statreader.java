package de.junkerjoerg12.tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.io.File;

import java.util.ArrayList;

public class Statreader {

  private BufferedReader reader;
  private ArrayList<String> time = new ArrayList<>();

  // private File stats1;

  public Statreader(File stats1) {
    // this.stats1 = stats1;
    stats1 = new File(Paths.get(stats1.getParent().toString(), "stats.txt").toString());
    System.out.println(stats1.toString());
    if (!stats1.exists()) {
      try {
        if (!stats1.createNewFile()) {
          System.out.println("An error  occured while creating the stats file");
        } else {
          BufferedWriter writer = new BufferedWriter(new FileWriter(stats1));
          writer.write("0\n0\n0");
          writer.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }

    }
    try {
      reader = new BufferedReader(new FileReader(stats1));
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
