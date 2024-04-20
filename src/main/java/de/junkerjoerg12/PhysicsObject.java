package de.junkerjoerg12;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JPanel;

public abstract class PhysicsObject extends JPanel {

    // in pixeln/sekunde²
    protected double acceleration;
    // in pixeln/sekunde
    protected int velocityHorizontally;
    protected int velocityVertically;

    private long lastTimeInTouchWithFloor;

    public PhysicsObject(double acceleration) {
        this.acceleration = -acceleration;//Minus, weil die Y-Achse bei Komputergraphik quasi gespiegelt ist

        this.setBackground(Color.CYAN);
    }

    public boolean checkCollision(ArrayList<PhysicsObject> list) {
        for (PhysicsObject p : list) {
            // if ()
        }

        return false;
    }

    protected void calculateVerticalVelocity() {
        if (lastTimeInTouchWithFloor == 0) {
            lastTimeInTouchWithFloor = System.currentTimeMillis();
        }
        //bin mir nicht sicher, ob das realistisch ist, es sieht aber ganz gut aus
        velocityVertically +=  (int) (acceleration * ((lastTimeInTouchWithFloor - System.currentTimeMillis()) / 1000));
        // Rest noch mit beachten, sonst wirkt das unsmooth
    }

    protected abstract void calculatePosition();
}
