package de.junkerjoerg12.character;

import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import de.junkerjoerg12.Game;

public class Player extends Entity {

    public Player(double acceleration, Game game) {
        super(acceleration, game);
        try {
            if (game.getCharacter() == 0) {

                images.add(ImageIO
                        .read(new File(
                                Paths.get("src", "main", "resources", "assets", "characterRight0.png").toString())));
                images.add(ImageIO
                        .read(new File(
                                Paths.get("src", "main", "resources", "assets", "characterLeft0.png").toString())));
                imageToDisplay = images.get(0);

            } else if (game.getCharacter() == 1) {
                images.add(ImageIO
                        .read(new File(
                                Paths.get("src", "main", "resources", "assets", "characterRight1.png").toString())));
                images.add(ImageIO
                        .read(new File(
                                Paths.get("src", "main", "resources", "assets", "characterLeft1.png").toString())));
                imageToDisplay = images.get(0);

            } else if (game.getCharacter() == 2) {
                images.add(ImageIO
                        .read(new File(
                                Paths.get("src", "main", "resources", "assets", "characterRight2.png").toString())));
                images.add(ImageIO
                        .read(new File(
                                Paths.get("src", "main", "resources", "assets", "characterLeft2.png").toString())));
                imageToDisplay = images.get(0);

            } else if (game.getCharacter() == 3) {
                images.add(ImageIO
                        .read(new File(
                                Paths.get("src", "main", "resources", "assets", "characterRight3.png").toString())));
                images.add(ImageIO
                        .read(new File(
                                Paths.get("src", "main", "resources", "assets", "characterLeft3.png").toString())));
                imageToDisplay = images.get(0);

            } else if (game.getCharacter() == 4) {
                images.add(ImageIO
                        .read(new File(
                                Paths.get("src", "main", "resources", "assets", "characterRight4.png").toString())));
                images.add(ImageIO
                        .read(new File(
                                Paths.get("src", "main", "resources", "assets", "characterLeft4.png").toString())));
                imageToDisplay = images.get(0);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        this.setBounds(968, 900, images.get(0).getWidth(), images.get(0).getHeight());
    }

    public Player(Game game) {
        this(2, game);
    }

    @Override
    public void update() {

        if (walkRight && !walkLeft) {
            imageToDisplay = images.get(0);
        } else if (walkLeft && !walkRight) {
            imageToDisplay = images.get(1);
        }
        super.update();
    }

    @Override
    public void draw(Graphics2D g) {
        g.drawImage(imageToDisplay, x, y, null);
        game.draws++;
        super.draw(g);
    }

}
