package de.junkerjoerg12.tools;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

public class ImageLoader {

    public static ArrayList<String> imageNames = new ArrayList<>();

    public static ArrayList<BufferedImage> loadImagesFromFolder(String folderPath) {
        ArrayList<BufferedImage> images = new ArrayList<>();
        File folder = new File(folderPath);

        // Ensure folder exists and is a directory
        if (folder.exists() && folder.isDirectory()) {
            File[] files = folder.listFiles(); // List all files in the folder

            // Iterate through the files and load images
            for (File file : files) {
                try {
                    // Check if the file is an image (extension filter)
                    if (file.isFile() && (file.getName().endsWith(".png") || file.getName().endsWith(".jpg")
                            || file.getName().endsWith(".jpeg") || file.getName().endsWith(".gif"))) {
                        BufferedImage image = ImageIO.read(file);
                        images.add(image); // Add to the list
                        imageNames.add(file.getName());
                    }
                } catch (Exception e) {
                    System.err.println("Failed to load image: " + file.getName());
                    e.printStackTrace();
                }
            }
        } else {
            System.err.println("Folder does not exist or is not a directory.");
        }

        return images;
    }

    public static void main(String[] args) {
        // Replace with the path to your folder
        String folderPath = "src/main/resources/assets";
        List<BufferedImage> images = loadImagesFromFolder(folderPath);

        System.out.println("Loaded " + images.size() + " images.");
    }
}