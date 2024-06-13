package de.junkerjoerg12;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
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

    protected boolean collisionActive = true;

    private boolean highlighted;

    private Font font1 = new Font("Serif", Font.PLAIN, 20);
    private Font font2 = new Font("Serif", Font.PLAIN, 10);

    public PhysicsObject(double acceleration, Game game) {
        this.acceleration = acceleration;
        this.game = game;

    }

    // intersection detectionalgorithm copied from the java.awt.Rectangle class
    public boolean intersects(PhysicsObject r) {
        int tw = this.width;
        int th = this.height;
        int rw = r.width;
        int rh = r.height;
        if (rw <= 0 || rh <= 0 || tw <= 0 || th <= 0) {
            return false;
        }
        int tx = this.x;
        int ty = this.y;
        int rx = r.x;
        int ry = r.y;
        rw += rx;
        rh += ry;
        tw += tx;
        th += ty;
        // overflow || intersect
        return ((rw < rx || rw > tx) &&
                (rh < ry || rh > ty) &&
                (tw < tx || tw > rx) &&
                (th < ty || th > ry));
    }

    public boolean collision(ArrayList<PhysicsObject> list) {

        int size = list.size();
        for (int i = 0; i < size; i++) {
            PhysicsObject p = list.get(i);
            if (intersects(p) && p.collisionActive) {
                return true;
            }
        }
        return false;
    }

    public boolean collisionLeft(ArrayList<PhysicsObject> list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            PhysicsObject p = list.get(i);
            if (this.getX() == p.getX() + p.getWidth() && this.getY() < p.getY() + p.getHeight()
                    && this.getY() + this.getHeight() > p.getY() && p.collisionActive) {
                return true;
            }
        }
        return false;
    }

    public boolean collisionRight(ArrayList<PhysicsObject> list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            PhysicsObject p = list.get(i);
            if (this.getX() + this.getWidth() == p.getX() && this.getY() < p.getY() + p.getHeight()
                    && this.getY() + this.getHeight() > p.getY() && p.collisionActive) {
                return true;
            }
        }
        return false;
    }

    public boolean collisionTop(ArrayList<PhysicsObject> list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            PhysicsObject p = list.get(i);
            if (this.getY() == p.getY() + p.getHeight() && this.getX() < p.getX() + p.getWidth()
                    && this.getX() + this.getWidth() > p.getX() && p.collisionActive) {
                return true;
            }
        }
        return false;
    }

    public boolean collisionBottom(ArrayList<PhysicsObject> list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            PhysicsObject p = list.get(i);
            if (this.getY() + this.getHeight() == p.getY() && this.getX() < p.getX() + p.getWidth()
                    && this.getX() + this.getWidth() > p.getX() && p.collisionActive) {
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
        ArrayList<PhysicsObject> list = game.getMap().getAllObjects();// enthighlightet alle anderen Objekte
        int size = list.size();
        for (int i = 0; i < size; i++) {
            list.get(i).highlighted = false;
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
            g.setFont(font1);
            g.drawString(game.getMap().getAllObjects().indexOf(this) + "", x + width / 2, y + height / 2);
            g.drawRect(x, y, width, height);
            g.setFont(font2);
            g.drawString((x + " | " + y), x, y + 10);
            g.drawString("w: " + width, x + width / 2, y + 10);
            g.drawString("h: " + height, x + 10, y + height / 2);
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

    protected abstract void calculatePosition();

    public abstract void update();
}
