package de.junkerjoerg12.map.mapElements;

import java.awt.Color;
import java.awt.Graphics2D;

import de.junkerjoerg12.Game;

public class Wall extends MapElement {

    public Wall(Game game) {
        super(game);
    }

    public void draw(Graphics2D g){
        super.draw(g);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, width, height);
    }
}
