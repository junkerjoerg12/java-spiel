package de.junkerjoerg12.character;

import java.util.ArrayList;

import de.junkerjoerg12.PhysicsObject;
import de.junkerjoerg12.map.Map;

public abstract class Character extends PhysicsObject {

    private long lastTick;

    public Character(double acceleration, Map map) {
        super(acceleration, map);
    }

    @Override
    public void calculatePosition() {
        // berechnet anhand der Geschindigkeiten und der Vergangenen Zeit die Positin
        // des Objekts

        velocityVertically = calculateVerticalVelocity();

        long now = System.currentTimeMillis();

        int distanceHorizontal = (int) (velocityHorizontally * (now - lastTick) / 1000);
        int distanceVertical = (int) (velocityVertically * (now - lastTick) / 1000);
        
        ArrayList<PhysicsObject> list = map.getAllObjects();

        this.setLocation(
                (this.getX() + distanceHorizontal),
                (this.getY() + distanceVertical));

        // kann und sollte warscheinlich auch noch mal überarbeitet werden
        if (collision(list)){

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
                    if (distanceHorizontal > 0) {//nach rechts
                        if (!this.collisionRight(list)){
                            this.setLocation(this.getX() + 1, this.getY());
                            movedHorizontal++;
                        }
                    } else {//nach links
                        if (!this.collisionLeft(list)) {
                            this.setLocation(this.getX() - 1, this.getY());
                            movedHorizontal--;
                        }
                    }
                }

                // vertikal
                if (movedVertical != distanceVertical) {
                    if (distanceVertical > 0) {//nach unten
                        if (!this.collisionBottom(list)) {
                            this.setLocation(this.getX(), this.getY() + 1);
                            movedVertical++;
                        }
                    } else {//nach oben
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
        this.lastTick = System.currentTimeMillis();
    }

    public void walk(int velocity) {
        velocityHorizontally = velocity;
    }

    public void jump() {
        jump = true;
        velocityVertically = -100;
        lastTimeInTouchWithFloor = System.currentTimeMillis();
    }
}
