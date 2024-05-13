package de.junkerjoerg12;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import de.junkerjoerg12.map.Map;

public abstract class PhysicsObject extends JPanel {

    // in pixeln/sekunde²
    protected double acceleration;
    // in pixeln/sekunde
    protected double velocityHorizontally;
    protected double velocityVertically;

    public double lastTimeInTouchWithFloor;
    public double deltaTSinceInTouchWithFloor;

    protected boolean jump;
    // vielleicht noch was besseres überlegen

    protected Game game;

    private boolean highlighted;

    public PhysicsObject(double acceleration, Game game) {
        this.acceleration = acceleration;
        this.game = game;

        this.setBackground(Color.CYAN);

        lastTimeInTouchWithFloor = 0;// brauche ich

    }

    public boolean collision(ArrayList<PhysicsObject> list) {
        for (PhysicsObject p : list) {
            if (this.getBounds().intersects(p.getBounds())) {
                return true;
            }
        }
        return false;
    }

    public boolean collisionLeft(ArrayList<PhysicsObject> list) {
        for (PhysicsObject p : list) {
            if (this.getX() == p.getX() + p.getWidth() && this.getY() < p.getY() + p.getHeight()
                    && this.getY() + this.getHeight() > p.getY()) {
                return true;
            }
        }
        return false;
    }

    public boolean collisionRight(ArrayList<PhysicsObject> list) {
        for (PhysicsObject p : list) {
            if (this.getX() + this.getWidth() == p.getX() && this.getY() < p.getY() + p.getHeight()
                    && this.getY() + this.getHeight() > p.getY()) {
                return true;
            }
        }
        return false;
    }

    public boolean collisionTop(ArrayList<PhysicsObject> list) {
        for (PhysicsObject p : list) {
            if (this.getY() == p.getY() + p.getHeight() && this.getX() < p.getX() + p.getWidth()
                    && this.getX() + this.getWidth() > p.getX()) {
                return true;
            }
        }
        return false;
    }

    public boolean collisionBottom(ArrayList<PhysicsObject> list) {
        for (PhysicsObject p : list) {
            if (this.getY() + this.getHeight() == p.getY() && this.getX() < p.getX() + p.getWidth()
                    && this.getX() + this.getWidth() > p.getX()) {
                return true;
            }
        }
        return false;
    }

    protected double calculateVerticalVelocity() {
        // bin mir nicht sicher, ob das realistisch ist, es sieht aber ganz gut aus

        if (jump) {// ist nicht schön, funktioniert aber, also vielleicht mal noch was anderes
                   // überlegen
            jump = false;
            deltaTSinceInTouchWithFloor = 0;
            return velocityVertically;
        } else if (!collisionBottom(game.getMap().getAllObjects())) {
            deltaTSinceInTouchWithFloor += game.getDelaybetweenFrames();
            double v = velocityVertically + (int) Math.round((acceleration
                    * ((deltaTSinceInTouchWithFloor) / 1000.0)));
            return v;
        } else {
            return 0;
        }
    }

    public void highlight() {
        for (PhysicsObject p : game.getMap().getAllObjects()) {
            p.highlighted = false;
            p.repaint();
        }
        highlighted = !highlighted;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (highlighted) {
            g.setColor(Color.RED);
            g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
        }
    }

    protected abstract void calculatePosition();
}
