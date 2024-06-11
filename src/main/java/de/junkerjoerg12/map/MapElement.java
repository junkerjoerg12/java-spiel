package de.junkerjoerg12.map;

import de.junkerjoerg12.Game;
import de.junkerjoerg12.PhysicsObject;
import de.junkerjoerg12.map.mapElements.Water;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.lang.reflect.Field;

public abstract class MapElement extends PhysicsObject {

    public MapElement(Game game) {
        super(0, game);
    }

    public void calculatePosition() {
    }

    public void update() {
        calculatePosition();
    }

    // public abstract void draw(Graphics2D g);

    public void draw(Graphics2D g) {
        super.draw(g);
    }

    public void draw(Graphics2D g, Class<? extends MapElement> myclass) {
        Field img = null;
        BufferedImage imageToDisplay = null;
        try {
            img = myclass.getField("imageToDisplay");
            imageToDisplay = (BufferedImage) img.get(null);
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
        // höhe und breite des enzufügenden Bildes
        int width = imageToDisplay.getWidth();
        int height = imageToDisplay.getHeight();
        // für den Teil, der nur teilweite gezeichnet werden muss: BufferedImage
        // subImage = imageToDisplay.getSubimage(height, height, width, height);
        int iteratorWidth = this.width / width;
        int iteratorHeight = this.height / height;
        // BufferedImage subImageCorner = imageToDisplay.getSubimage(0, 0, this.width -
        // iteratorWidth * width,
        // this.height - iteratorHeight * height);
        for (int i = 0; i < iteratorWidth; i++) {
            for (int j = 0; j < iteratorHeight; j++) {
                g.drawImage(imageToDisplay, x + width * i, y + height * j, null);
            }
        }

        // zeichnet en rechten Rand des Objektes
        int widthSub = this.width - iteratorWidth * width;
        if ((widthSub) > 0) {

            int x = this.x + iteratorWidth * width;
            BufferedImage subImage = imageToDisplay.getSubimage(0, 0, widthSub, height);
            for (int i = 0; i < iteratorHeight; i++) {
                g.drawImage(subImage, x, y + height * i, null);
            }
        }

        // zeichnet den unteren rand des Objektes
        int heightSub = this.height - iteratorHeight * height;
        if (heightSub > 0) {
            int y = this.y + iteratorHeight * height;
            BufferedImage subImage = imageToDisplay.getSubimage(0, 0, width, heightSub);
            for (int i = 0; i < iteratorWidth; i++) {
                g.drawImage(subImage, x + width * i, y, null);
            }

            // zeichent die untere rechte ecke des Objektes
            if (heightSub > 0 && widthSub > 0) {
                subImage = imageToDisplay.getSubimage(0, 0, widthSub, heightSub);
                g.drawImage(subImage, this.x + width * iteratorWidth, this.y + height *iteratorHeight, null);
            }
        }
        super.draw(g);
    }
}
