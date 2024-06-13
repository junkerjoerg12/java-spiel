package de.junkerjoerg12.tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Statwriter {
    private BufferedReader reader;
    private BufferedWriter writer;

    public Statwriter(String newpb) {
        try {
            writer = new BufferedWriter(new FileWriter("src//main//resources//stats.txt"));
            writer.write(newpb);
            writer.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
