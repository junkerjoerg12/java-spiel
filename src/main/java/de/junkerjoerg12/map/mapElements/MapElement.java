package de.junkerjoerg12.map.mapElements;

import de.junkerjoerg12.Game;
import de.junkerjoerg12.PhysicsObject;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
import javax.swing.Timer;

public abstract class MapElement extends PhysicsObject implements ActionListener {

    private Timer imageswitcher;
    private static int imageIndex = 0;// the index of the Image currently displayed

    public MapElement(Game game) {
        super(0, game);
        try {
            images.add(ImageIO
                    .read(new File(Paths.get("src", "main", "resources", "assets", "wasser1.png").toString())));
            images.add(ImageIO
                    .read(new File(Paths.get("src", "main", "resources", "assets", "wasser2.png").toString())));
            images.add(ImageIO
                    .read(new File(Paths.get("src", "main", "resources", "assets", "wasser3.png").toString())));
            imageToDisplay = images.get(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        imageswitcher = new Timer(333, this);
        imageswitcher.start();
    }

    public void calculatePosition() {
    }

    public void update() {
        calculatePosition();
    }

    @Override
    public void draw(Graphics2D g) {

        int width = imageToDisplay.getWidth();
        int height = imageToDisplay.getHeight();
        // für den Teil,d er nur teilweite gezeichnet werden muss: BufferedImage
        // subImage = imageToDisplay.getSubimage(height, height, width, height);
        int iteratinWidth = this.width / width;
        int iteratorHeight = this.height / height;
        for (int i = 0; i < iteratinWidth; i++) {
            for (int j = 0; j < iteratorHeight; j++) {
                g.drawImage(imageToDisplay, x + width * i, y + height * j, null);
            }
        }
        super.draw(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == imageswitcher) {
            if (images.size() > imageIndex) {
                imageToDisplay = images.get(imageIndex++);
            } else {
                imageIndex = 0;
                imageToDisplay = images.get(imageIndex);
            }
        }

    }
}
