package de.junkerjoerg12;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

public abstract class PhysicsObject {

    // in pixeln/sekunde²
    protected double acceleration;
    // in pixeln/sekunde
    protected double velocityHorizontally;
    protected double velocityVertically;

    public double deltaTSinceVelicityZero;

    protected Game game;

    protected int x;
    protected int y;
    protected int width;
    protected int height;

    private boolean highlighted;

    protected ArrayList<String> imageFilepath = new ArrayList<>();
    protected ArrayList<Image> images = new ArrayList<>();

    public PhysicsObject(double acceleration, Game game) {
        this.acceleration = acceleration;
        this.game = game;

    }

    public boolean collision(ArrayList<PhysicsObject> list) {
        int rect1BottomRightX = this.x + this.width;
        int rect1BottomRightY = this.y + this.height;

        for (PhysicsObject p : list) {
            int rect2BottomRightX = p.x + p.width;
            int rect2BottomRightY = p.y + p.height;

            // Check if one rectangle is entirely to the left of the other
            if (rect1BottomRightX <= p.x || rect2BottomRightX <= p.x) {
                continue; // Skip to the next rectangle in the list
            }

            // Check if one rectangle is entirely above the other
            if (rect1BottomRightY <= p.y || rect2BottomRightY <= p.y) {
                continue; // Skip to the next rectangle in the list
            }

            // If neither of the above conditions is true, the rectangles overlap
            return true;
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

        if (!collisionBottom(game.getMap().getAllObjects())) {
            deltaTSinceVelicityZero += game.getDelaybetweenFrames();
            double v = velocityVertically + (int) Math.round((acceleration) * game.getDelaybetweenFrames());
            // double v = velocityVertically + (int) Math.round((acceleration
            // * ((deltaTSinceInTouchWithFloor) / 1000.0)));
            return v;
        } else {
            return 0;
        }
    }

    public void highlight() {
        for (PhysicsObject p : game.getMap().getAllObjects()) {
            p.highlighted = false;
        }
        highlighted = !highlighted;
    }

    // hier mus das Objekt gerendert werden
    public void draw(Graphics2D g) {
        if (highlighted) {
            g.setColor(Color.RED);
            g.drawRect(x, y, width - 1, height - 1);
        }
        if (game.buildMode) {
            g.setColor(Color.RED);
            g.drawString(game.getMap().getAllObjects().indexOf(this) + "", x + width / 2, y + height / 2);
        }
    }

    public void setBounds(int x, int y, int width, int height) {
        setLocation(x, y);
        setSize(width, height);

    }

    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public ArrayList<String> getImageFilepath() {
        return imageFilepath;
    }

    protected abstract void calculatePosition();

    public abstract void update();
}
