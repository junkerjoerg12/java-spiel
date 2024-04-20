package de.junkerjoerg12.character;

import java.awt.Color;

public class Player extends Character {

    // Keycodes für bewegungen:
    private long lastTick;

    public Player(double acceleration) {
        super(acceleration);
        this.setBackground(Color.PINK);

        this.setBounds(300, 300, 50, 50);

        this.setFocusable(true);
        this.requestFocus();
    }

    public Player() {
        this(10);
    }

    @Override
    public void calculatePosition() {
        calculateVerticalVelocity();
        this.setLocation(Math.round( (this.getX() + velocityHorizontally * (System.currentTimeMillis() - lastTick) / 1000)),
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
