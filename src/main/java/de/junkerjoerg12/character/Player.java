package de.junkerjoerg12.character;

import java.awt.Color;
import java.awt.Graphics2D;

import de.junkerjoerg12.Game;

public class Player extends Entity {

    public Player(double acceleration, Game game) {
        super(acceleration, game);
        this.setBounds(300, 300, 50, 50);

    }

    public Player(Game game) {
        this(2, game);
    }

    @Override
    public void draw(Graphics2D g) {
        super.draw(g);
        g.setColor(Color.PINK);
        g.fillRect(x, y, width, height);
    }

}
