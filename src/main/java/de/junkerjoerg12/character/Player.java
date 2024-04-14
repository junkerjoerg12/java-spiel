package de.junkerjoerg12.character;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player extends Character implements KeyListener {

    // Keycodes für bewegungen:
    final int keyRight = 68;
    final int keyLeft = 65;
    final int keyJump = 32;

    private float lastTimeInTouchWithFloor;
    private float lastTick;

    public Player(double acceleration) {
        super(acceleration);
        this.setBackground(Color.PINK);

        this.setBounds(300, 300, 50, 50);
    }

    public Player() {
        this(10);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // soweit ich weiß brauchen wir diehier

        System.out.println("Achtung Achtung!!!");

        switch (e.getKeyCode()) {
            case keyRight:
                this.velocityHorizontally = 100;
            case keyLeft:
                this.velocityHorizontally = 100;
            case keyJump:
                System.out.println("Jump");
                this.velocityVertically = -100;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // alle geschindigkeiten wieder null setzen
    }

    @Override
    public void calculatePosition() {
        System.out.println("jasklsdjf");
        System.out.println(velocityHorizontally);

        this.setLocation(
                (int) (this.getX() + velocityHorizontally * ((1 / 1000) * System.currentTimeMillis() - lastTick)),
                this.getY());
        this.lastTick = System.currentTimeMillis();

    }
}
