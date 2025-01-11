package de.junkerjoerg12.map.mapElements;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import de.junkerjoerg12.Game;
import de.junkerjoerg12.map.MapElement;

public class Leaf extends MapElement {
    public static ArrayList<BufferedImage> images = new ArrayList<>();
    public static BufferedImage imageToDisplay;
    public static int imageIndex;// the index of the Image currently displayed

    public Leaf(Game game) {
        super(game);
        if (Leaf.images.size() == 0) {
            try {
                Leaf.images.add(ImageIO
                        .read(new File(Paths.get("src", "main", "resources", "assets", "leaf1.png").toString())));
                Leaf.images.add(ImageIO
                        .read(new File(Paths.get("src", "main", "resources", "assets", "leaf2.png").toString())));
                Leaf.imageToDisplay = images.get(0);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void switchImages() {
        if (images.size() != 0) {

            if (imageIndex != images.size()) {
                imageToDisplay = images.get(imageIndex++);
            } else {
                imageToDisplay = images.get(imageIndex = 0);
            }
        }
    }

    @Override
    public void draw(Graphics2D g) {

        super.draw(g, this.getClass());
    }

}
