package de.junkerjoerg12;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JPanel;

import de.junkerjoerg12.map.Map;

public abstract class PhysicsObject extends JPanel {

    // in pixeln/sekunde²
    protected double acceleration;
    // in pixeln/sekunde
    protected int velocityHorizontally;
    protected int velocityVertically;

    private long lastTimeInTouchWithFloor;

    protected Map map;

    public PhysicsObject(double acceleration, Map map) {
        this.acceleration = -acceleration;// Minus, weil die Y-Achse bei Komputergraphik quasi gespiegelt ist
        this.map = map;

        this.setBackground(Color.CYAN);
    }

    public boolean checkCollision(ArrayList<PhysicsObject> list) {
        // viellcit später zahlen als code für Collision links, rechts, oben oder unten
        // zurückgeben

        for (PhysicsObject p : list) {
            if (this.getBounds().intersects(p.getBounds())) {
                return true;
            }
        }
        return false;
    }

    protected void calculateVerticalVelocity() {
        if (lastTimeInTouchWithFloor == 0) {
            lastTimeInTouchWithFloor = System.currentTimeMillis();
        }
        // bin mir nicht sicher, ob das realistisch ist, es sieht aber ganz gut aus
        if (!checkCollision(map.getAllObjects())) {
            velocityVertically += (int) (acceleration
                    * ((lastTimeInTouchWithFloor - System.currentTimeMillis()) / 1000));
            // Rest noch mit beachten, sonst wirkt das unsmooth
        } else {
            velocityVertically = 0;
        }
    }

    protected abstract void calculatePosition();
}
