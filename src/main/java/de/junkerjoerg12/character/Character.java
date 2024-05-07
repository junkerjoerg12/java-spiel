package de.junkerjoerg12.character;

import java.util.ArrayList;

import de.junkerjoerg12.Game;
import de.junkerjoerg12.PhysicsObject;
import de.junkerjoerg12.map.Map;

public abstract class Character extends PhysicsObject {


    public Character(double acceleration, Game game) {
        super(acceleration, game);
    }

    @Override
    public void calculatePosition() { // gefühlt gibt es beim Springen minimale Abweichungen
        // berechnet anhand der Geschindigkeiten und der Vergangenen Zeit die Positin
        // des Objekts

        velocityVertically = calculateVerticalVelocity();

        //auch mit berechneten, nicht gemessenene Zeiten berechnen
        int distanceHorizontal = (int) (velocityHorizontally * (game.getNow() - game.getLastTick()) / 1000);
        int distanceVertical = (int) (velocityVertically * (game.getNow() - game.getLastTick()) / 1000);

        ArrayList<PhysicsObject> list = game.getMap().getAllObjects();

        this.setLocation(
                (this.getX() + distanceHorizontal),
                (this.getY() + distanceVertical));

        // kann und sollte warscheinlich auch noch mal überarbeitet werden
        if (collision(list)) {

            // pixel für pixel bewegen, damit die bewegung gestoppt werden kann, sobald die
            // KOlision stattfindet
            // und die Objekte sich nicht mehr überschneiden
            int movedHorizontal = 0;
            int movedVertical = 0;

            // position wird wieder zurückgesetzt
            this.setLocation(
                    (this.getX() - distanceHorizontal),
                    (this.getY() - distanceVertical));

            for (int i = 0; i < Math.max(Math.abs(distanceVertical), Math.abs(distanceHorizontal)); i++) {
                if (movedHorizontal != distanceHorizontal) {
                    if (distanceHorizontal > 0) {// nach rechts
                        if (!this.collisionRight(list)) {
                            this.setLocation(this.getX() + 1, this.getY());
                            movedHorizontal++;
                        }
                    } else {// nach links
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
                            lastTimeInTouchWithFloor = System.currentTimeMillis();
                        }
                    } else {// nach oben
                        if (!this.collisionTop(list)) {
                            this.setLocation(this.getX(), this.getY() - 1);
                            movedVertical--;
                        } else {
                            velocityVertically = 0;
                        }
                    }
                }
            }
        }
    }

    public void walk(int velocity) {
        velocityHorizontally = velocity;
    }

    public void jump() {
        jump = true;
        velocityVertically = -250;
        lastTimeInTouchWithFloor = System.currentTimeMillis();
    }
}
