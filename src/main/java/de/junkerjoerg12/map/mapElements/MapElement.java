package de.junkerjoerg12.map.mapElements;

import de.junkerjoerg12.Game;
import de.junkerjoerg12.PhysicsObject;

public abstract class MapElement extends PhysicsObject {

    public MapElement(Game game) {
        super(0, game);
        this.setVisible(true);
    }

    public void calculatePosition() {

    }
}
