package de.junkerjoerg12.map.mapElements;

import java.awt.Color;

import de.junkerjoerg12.Game;

public class Wall extends MapElement {

    public Wall(Game game) {
        super(game);
        this.setBackground(Color.BLACK);
    }
}
