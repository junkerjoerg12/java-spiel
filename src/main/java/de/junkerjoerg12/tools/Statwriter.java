package de.junkerjoerg12.tools;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

public class Statwriter {

    public Statwriter(String newpb) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(Paths.get("maps","level1", "stats.txt").toString()));
            writer.write(newpb);
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
