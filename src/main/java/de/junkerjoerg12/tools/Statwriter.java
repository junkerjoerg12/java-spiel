package de.junkerjoerg12.tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

public class Statwriter {
    private BufferedReader reader;
    private BufferedWriter writer;

    public Statwriter(String newpb) {
        System.out.println("statwriter");
        try {
            writer = new BufferedWriter(new FileWriter(Paths.get("maps","level1", "stats.txt").toString()));
            writer.write(newpb);
            writer.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
