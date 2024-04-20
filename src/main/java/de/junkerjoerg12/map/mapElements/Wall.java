package de.junkerjoerg12.map.mapElements;

import java.awt.Color;

import de.junkerjoerg12.map.Map;

public class Wall extends MapElement{

    public Wall(Map map) {
        super(map);
        this.setBackground(Color.BLACK);
    }
}
