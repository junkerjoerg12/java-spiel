package de.junkerjoerg12.character;

import java.awt.Color;

import de.junkerjoerg12.map.Map;

public class Player extends Character {


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


}
