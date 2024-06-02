package de.junkerjoerg12.map.mapElements;

import java.awt.Color;
import java.awt.Graphics2D;

import de.junkerjoerg12.Game;

public class Floor extends MapElement {

    public Floor(Game game) {
        super(game);
    }

    @Override
    public void draw(Graphics2D g){
        g.setColor(Color.GREEN);
        g.fillRect(x, y, width, height);
        super.draw(g);
    }
}
