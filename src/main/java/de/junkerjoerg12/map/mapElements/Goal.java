package de.junkerjoerg12.map.mapElements;

import java.util.ArrayList;

import javax.imageio.ImageIO;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import de.junkerjoerg12.Game;
import de.junkerjoerg12.PhysicsObject;
import de.junkerjoerg12.character.Player;
import de.junkerjoerg12.map.MapElement;

public class Goal extends MapElement {

    public static ArrayList<BufferedImage> images = new ArrayList<>();
    public static BufferedImage imageToDisplay;
    public static int imageIndex = 0;

    private static Player player;

    private ArrayList<PhysicsObject> playerlist = new ArrayList<>();

    public Goal(Game game) {
        super(game);
        if (images.size() == 0) {
            try {
                Goal.images.add(ImageIO
                        .read(new File(Paths.get("src", "main", "resources", "assets", "fire1.png").toString())));
                Goal.images.add(ImageIO
                        .read(new File(Paths.get("src", "main", "resources", "assets", "fire2.png").toString())));
                Goal.images.add(ImageIO
                        .read(new File(Paths.get("src", "main", "resources", "assets", "fire3.png").toString())));
                Goal.images.add(ImageIO
                        .read(new File(Paths.get("src", "main", "resources", "assets", "fire2.png").toString())));
                imageToDisplay = images.get(0);

                Goal.player = game.getMap().getPlayer();
                playerlist.add(player);
                this.width = imageToDisplay.getWidth();
                this.height = imageToDisplay.getHeight();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void switchImages() {
        if (imageIndex != images.size()) {
            imageToDisplay = images.get(imageIndex++);
        } else {
            imageToDisplay = images.get(imageIndex = 0);
        }
    }

    @Override
    public void update() {
        if (intersects(player) || collisionBottom(playerlist) || collisionLeft(playerlist) || collisionRight(playerlist)
                || collisionTop(playerlist)) {
            System.out.println("zu ende");
            game.setEndscreen();
        }
    }

    @Override
    public void draw(Graphics2D g) {
        super.draw(g, this.getClass());
    }

    @Override
    public void setSize(int width, int height) {
    }

}
