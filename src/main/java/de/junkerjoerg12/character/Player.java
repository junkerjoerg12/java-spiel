package de.junkerjoerg12.character;

import java.awt.Color;

import de.junkerjoerg12.map.Map;

public class Player extends Character {

    // Keycodes für bewegungen:
    private long lastTick;

    public Player(double acceleration, Map map) {
        super(acceleration, map);
        this.setBackground(Color.PINK);

        this.setBounds(300, 300, 50, 50);

        this.setFocusable(true);
        this.requestFocus();
    }

    public Player(Map map) {
        this(10, map);
    }

    @Override
    public void calculatePosition() {
        calculateVerticalVelocity();
        this.setLocation(Math.round((this.getX() + velocityHorizontally * (System.currentTimeMillis() - lastTick) / 1000)),
                 Math.round((this.getY() + velocityVertically * (System.currentTimeMillis() - lastTick) / 1000)));
        this.lastTick = System.currentTimeMillis();
        revalidate();
        repaint();

    }

    public void setVelocityHorizontally(int velocity) {
        velocityHorizontally = velocity;
    }

    public void setVelocityVertically(int velocity) {
        velocityVertically = velocity;
    }

    public void printVelocity() {
        System.out.println("HOrizontally: " + velocityHorizontally);
        System.out.println("Vertically: " + velocityVertically);
    }
}
