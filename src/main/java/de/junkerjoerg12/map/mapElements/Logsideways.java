package de.junkerjoerg12.map.mapElements;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import de.junkerjoerg12.Game;
import de.junkerjoerg12.map.MapElement;

public class Logsideways extends MapElement {

    public static BufferedImage imageToDisplay;

    public Logsideways(Game game) {
        super(game);
        if (Logsideways.imageToDisplay == null) {
            try {
                Logsideways.imageToDisplay = ImageIO
                        .read(new File(Paths.get("src", "main", "resources", "assets", "logsideways.png").toString()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void draw(Graphics2D g) {
        super.draw(g, this.getClass());
    }

}