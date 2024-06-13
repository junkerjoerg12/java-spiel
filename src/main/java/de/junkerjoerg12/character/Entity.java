package de.junkerjoerg12.character;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import de.junkerjoerg12.Game;
import de.junkerjoerg12.PhysicsObject;

public abstract class Entity extends PhysicsObject {

    protected int maxHorizontalSpeed = 250;
    protected double horizontalAccelleration = 40;
    public boolean walkRight;
    public boolean walkLeft;
    public boolean jump;

    protected ArrayList<BufferedImage> images = new ArrayList<>();
    protected BufferedImage imageToDisplay;

    protected int jumpVelocity = 1000; // glaube nicht 1:1 die Geschwindigkeit, aber mehr ist mehr

    public double lastTimeInTouchWithFloor;

    public Entity(double acceleration, Game game) {
        super(acceleration, game);
        lastTimeInTouchWithFloor = 0;// brauche ich
    }

    public Entity(double acceleration, Game game, double horizontalAccelleration) {
        this(acceleration, game);
        this.horizontalAccelleration = horizontalAccelleration;
    }

    @Override
    public void calculatePosition() {

        velocityVertically = calculateVerticalVelocity();
        velocityHorizontally = calculateHorizontalVelocity();

        // auch mit berechneten, nicht gemessenene Zeiten berechnen
        int distanceHorizontal = (int) (velocityHorizontally * (game.getDelaybetweenFrames()) / 1000);
        int distanceVertical = (int) (velocityVertically * (game.getDelaybetweenFrames()) / 1000);

        ArrayList<PhysicsObject> list = game.getMap().getAllObjects();

        this.setLocation(
                (this.getX() + distanceHorizontal),
                (this.getY() + distanceVertical));

        // kann und sollte warscheinlich auch noch mal überarbeitet werden
        if (collision(list)) {

            // pixel für pixel bewegen, damit die bewegung gestoppt werden kann, sobald die
            // Kollision stattfindet
            // und die Objekte sich nicht mehr überschneiden
            int movedHorizontal = 0;
            int movedVertical = 0;

            // position wird wieder zurückgesetzt
            this.setLocation(
                    (this.getX() - distanceHorizontal),
                    (this.getY() - distanceVertical));

            int condition = Math.max(Math.abs(distanceVertical), Math.abs(distanceHorizontal));
            for (int i = 0; i < condition; i++) {
                if (movedHorizontal != distanceHorizontal) {
                    if (distanceHorizontal > 0) {// nach rechts
                        if (!this.collisionRight(list)) {
                            this.setLocation(this.getX() + 1, this.getY());
                            movedHorizontal++;
                        }
                    } else if (distanceHorizontal < 0) {// nach links
                        if (!this.collisionLeft(list)) {
                            this.setLocation(this.getX() - 1, this.getY());
                            movedHorizontal--;
                        }
                    }
                }

                // vertikal
                if (movedVertical != distanceVertical) {
                    if (distanceVertical > 0) {// nach unten
                        if (!this.collisionBottom(list)) {
                            this.setLocation(this.getX(), this.getY() + 1);
                            movedVertical++;
                        } else {
                            lastTimeInTouchWithFloor = game.getUptime();
                        }
                    } else if (distanceVertical < 0) {// nach oben
                        if (!this.collisionTop(list)) {
                            this.setLocation(this.getX(), this.getY() - 1);
                            movedVertical--;
                        } else {
                            velocityVertically = 0; // rausmachen für ceiling surfing, müsste dann aber noch ein wenig
                                                    // überarbeitet werden
                        }
                    }
                }
            }
        }
    }

    public double calculateHorizontalVelocity() {
        if (walkRight && walkLeft) {
            return 0;
        } else if (walkRight) {
            if (velocityHorizontally + horizontalAccelleration < maxHorizontalSpeed) {
                return velocityHorizontally + horizontalAccelleration;
            } else {
                return maxHorizontalSpeed;
            }
        } else if (walkLeft) {
            if (velocityHorizontally - horizontalAccelleration > -maxHorizontalSpeed) {
                return velocityHorizontally - horizontalAccelleration;
            } else {
                return -maxHorizontalSpeed;
            }
        }
        return 0;
    }

    public double calculateVerticalVelocity() {
        // bin mir nicht sicher, ob das realistisch ist, es sieht aber ganz gut aus

        if (jump && collisionBottom(game.getMap().getAllObjects())) {// ist nicht schön, funktioniert aber, also
                                                                     // vielleicht mal noch was anderes
            // überlegen
            deltaTSinceVelicityZero = 0;
            lastTimeInTouchWithFloor = game.getUptime();
            velocityVertically = -jumpVelocity;
            return velocityVertically;
        } else if (!collisionBottom(game.getMap().getAllObjects())) {
            deltaTSinceVelicityZero += game.getDelaybetweenFrames();
            return velocityVertically + (int) Math.round((acceleration) * game.getDelaybetweenFrames());
        } else {
            return 0;
        }
    }

    public void update() {
        calculatePosition();
    }

    @Override
    public void draw(Graphics2D g) {
        super.draw(g);
    }

}
