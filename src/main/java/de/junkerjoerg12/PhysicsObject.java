package de.junkerjoerg12;

import java.awt.Color;
import java.awt.Rectangle;
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
    // vielleicht noch was besseres überlegen

    protected Map map;

    public PhysicsObject(double acceleration, Map map) {
        this.acceleration = -acceleration;// Minus, weil die Y-Achse bei Komputergraphik quasi gespiegelt ist
        this.map = map;

        this.setBackground(Color.CYAN);

        lastTimeInTouchWithFloor = System.currentTimeMillis();
    }

    // public boolean[] checkCollision(ArrayList<PhysicsObject> list) {
    // // viellcit später zahlen als code für Collision links, rechts, oben oder
    // unten
    // // zurückgeben

    // // Problem: bei schneller Bewegung kann man sich in gegenstände reinbewegen,
    // da
    // // nicht nach jedem bewegten Pixel überprüft wird, ob kollision vorhanden
    // ist,
    // // sondern nur nach jedem Frame, also nachdem man sich mehrere pisel weit
    // // verschpben hat

    // // gibt 4 byte array zurück: [x, x, x, x]
    // // [kollision oben, kollision rechts, kollision unten, kollision links]

    // // bei fragen mal in goodnotes nachgucken
    // boolean[] result = new boolean[4];
    // // oben
    // for (PhysicsObject p : list) {
    // if (((p.getY() + p.getHeight() < getY() && p.getY() <= getY())
    // && (p.getX() + p.getWidth() >= getX() || p.getX() <= getX() + getWidth()))) {
    // result[0] = true;
    // System.out.println("kollision nach oben");
    // break;
    // }
    // }
    // // rechts
    // for (PhysicsObject p : list) {
    // if (!((p.getX() <= getX() + getWidth())
    // && (p.getY() + p.getHeight() >= getY() || p.getY() <= getY() + getHeight())))
    // {
    // result[1] = true;
    // System.out.println("kollision nach rechts");
    // break;
    // }
    // }
    // // unten
    // for (PhysicsObject p : list) {
    // if (((p.getY() >= getY() + getHeight())
    // && (p.getX() + p.getWidth() >= getX() || p.getX() <= getX() + getWidth()))) {
    // result[2] = true;
    // System.out.println("kollision nach unten");
    // break;
    // }
    // }
    // // links
    // for (PhysicsObject p : list) {
    // if (!((p.getX() + getWidth() <= getX())
    // && (p.getY() + p.getHeight() >= getY() || p.getY() <= getY() + getHeight())))
    // {
    // result[3] = true;
    // System.out.println("kollision nach links");
    // break;
    // }
    // }
    // return result;
    // }

    public boolean checkCollisionDown(ArrayList<PhysicsObject> list) {
        for (PhysicsObject p : list) {
            if (p.getY() + p.getHeight() >= this.getY()
                    && p.getY() <= this.getY() + this.getHeight()
                    && p.getX() <= this.getX() + this.getWidth()
                    && p.getX() + p.getWidth() >= this.getX()) {
                return true;
            }
        }
        return false;
    }

    public boolean checkCollisionRight(ArrayList<PhysicsObject> list) {
        Rectangle bounds1 = this.getBounds();
        for (PhysicsObject p : list) {
            Rectangle bounds2 = p.getBounds();
            if (bounds1.x + bounds1.width >= bounds2.x &&
                    bounds1.x < bounds2.x + bounds2.width &&
                    bounds1.y < bounds2.y + bounds2.height &&
                    bounds1.y + bounds1.height > bounds2.y) {
                return true;
            }
        }
        return false;
    }

    public boolean checkCollisionLeft(ArrayList<PhysicsObject> list) {
        Rectangle bounds1 = this.getBounds();
        for (PhysicsObject p : list) {
            Rectangle bounds2 = p.getBounds();
            if (bounds1.x <= bounds2.x + bounds2.width &&
                    bounds1.x + bounds1.width > bounds2.x &&
                    bounds1.y < bounds2.y + bounds2.height &&
                    bounds1.y + bounds1.height > bounds2.y) {
                return true;
            }
        }
        return false;
    }

    public boolean checkCollisionUp(ArrayList<PhysicsObject> list) {
        for (PhysicsObject p : list) {
            if (true) {
                return true;
            }
        }
        return false;
    }

    protected int calculateVerticalVelocity() {
        // bin mir nicht sicher, ob das realistisch ist, es sieht aber ganz gut aus

        if (jump) {// ist nicht schön, funktioniert aber, also vielleicht mal noch was anderes
                   // überlegen
            jump = false;
            return velocityVertically;
        } else if (!checkCollisionDown(map.getAllObjects())) {
            return velocityVertically + (int) (acceleration
                    * ((lastTimeInTouchWithFloor - System.currentTimeMillis()) / 1000));
        }
        return 0;
        // problem: wird auch falls man springen will gleich wieder auf null gesetzt
    }

    protected abstract void calculatePosition();
}
