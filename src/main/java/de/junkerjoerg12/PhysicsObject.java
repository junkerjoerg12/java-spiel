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

    public long lastTimeInTouchWithFloor;

    protected boolean jump;
    //vielleicht noch was besseres überlegen

    protected Map map;

    public PhysicsObject(double acceleration, Map map) {
        this.acceleration = -acceleration;// Minus, weil die Y-Achse bei Komputergraphik quasi gespiegelt ist
        this.map = map;

        this.setBackground(Color.CYAN);

        lastTimeInTouchWithFloor = System.currentTimeMillis();
    }

    public boolean checkCollision(ArrayList<PhysicsObject> list) {
        // viellcit später zahlen als code für Collision links, rechts, oben oder unten
        // zurückgeben

        //Problem: bei schneller Bewegung kann man sich in gegenstände reinbewegen, da nicht nach jedem bewegten Pixel überprüft wird, ob kollision vorhanden ist, sondern nur nach jedem Frame, also nachdem man sich mehrere pisel weit verschpben hat 

        for (PhysicsObject p : list) {
            if (this.getBounds().intersects(p.getBounds())) {
                return true;
            }
        }
        return false;
    }

    protected int calculateVerticalVelocity() {
        // bin mir nicht sicher, ob das realistisch ist, es sieht aber ganz gut aus

        if (jump) {//ist nicht schön, funktioniert aber, also vielleicht mal noch was anderes überlegen
            jump = false;
            return velocityVertically;
        } else if (!checkCollision(map.getAllObjects())) {
            return velocityVertically + (int) (acceleration
                    * ((lastTimeInTouchWithFloor - System.currentTimeMillis()) / 1000));
        }
        return 0;
        // problem: wird auch falls man springen will gleich wieder auf null gesetzt
    }

    protected abstract void calculatePosition();
}
