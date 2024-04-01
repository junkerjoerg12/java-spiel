package de.junkerjoerg12.character;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player extends Character implements KeyListener {

    // Keycodes für bewegungen:
    final int keyRight = 68;
    final int keyLeft = 65;
    final int keyJump = 32;

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

        switch (e.getKeyCode()) {
            case keyRight:
                System.out.println("Move forward");
                break;
            case keyLeft:
                System.out.println("Move backwards");
                break;
            case keyJump:
                System.out.println("Jump");

            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //alle geschindigkeiten wieder null setzen
    }

    @Override
    protected void calculatePosition() {

    }
}
