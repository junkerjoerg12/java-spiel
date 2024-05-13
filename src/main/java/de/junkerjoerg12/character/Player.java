package de.junkerjoerg12.character;

import java.awt.Color;

import de.junkerjoerg12.Game;

public class Player extends Character {

    public Player(double acceleration, Game game) {
        super(acceleration, game);
        this.setBackground(Color.PINK);

        this.setBounds(300, 300, 50, 50);

        this.setFocusable(true);
        this.requestFocus();
    }

    public Player(Game game) {
        this(2, game);
    }

}
