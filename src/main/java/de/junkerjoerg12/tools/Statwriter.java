package de.junkerjoerg12.tools;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

public class Statwriter {

  public Statwriter(String newpb, File mapFile) {
    mapFile = new File(Paths.get(mapFile.getParent().toString(), "stats.txt").toString());
    try {
      BufferedWriter writer = new BufferedWriter(new FileWriter(mapFile));
      writer.write(newpb);
      writer.close();

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
