package de.junkerjoerg12.map.mapElements;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import de.junkerjoerg12.Game;
import de.junkerjoerg12.PhysicsObject;
import de.junkerjoerg12.character.Player;
import de.junkerjoerg12.map.MapElement;

public class Trap extends MapElement {

    private static Player player;
    public static ArrayList<BufferedImage> images = new ArrayList<>();
    public static BufferedImage imageToDisplay;
    private ArrayList<PhysicsObject> playerlist = new ArrayList<>();
    public static int imageIndex;

    public Trap(Game game) { // needs to be size :
        super(game);
        if (Trap.images.size() == 0) {
            try {
                Trap.images.add(ImageIO
                        .read(new File(Paths.get("src", "main", "resources", "assets", "wasser1.png").toString())));
                Trap.images.add(ImageIO
                        .read(new File(Paths.get("src", "main", "resources", "assets", "wasser2.png").toString())));
                Trap.images.add(ImageIO
                        .read(new File(Paths.get("src", "main", "resources", "assets", "wasser3.png").toString())));
                Trap.images.add(ImageIO
                        .read(new File(Paths.get("src", "main", "resources", "assets", "wasser2.png").toString())));
                Trap.imageToDisplay = images.get(0);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Trap.player = game.getMap().getPlayer();
        playerlist.add(player);
    }

    @Override

    public void draw(Graphics2D g) {
        super.draw(g, this.getClass());
    }

    @Override
    public void update() {
        if (intersects(player) || collisionBottom(playerlist) || collisionLeft(playerlist) || collisionRight(playerlist)
                || collisionTop(playerlist)) {
            game.setFailscreen();
        }
    }

    public static void switchImage() {
        if (imageIndex != images.size()) {
            imageToDisplay = images.get(imageIndex++);
        } else {
            imageToDisplay = images.get(imageIndex = 0);
        }
    }
}
