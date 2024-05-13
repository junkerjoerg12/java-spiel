package de.junkerjoerg12.map.mapElements;

import java.awt.Color;

import de.junkerjoerg12.Game;
import de.junkerjoerg12.map.Map;

public class Wall extends MapElement{

    public Wall(Game game) {
        super(game);
        this.setBackground(Color.BLACK);
    }
}
