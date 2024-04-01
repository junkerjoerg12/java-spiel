package de.junkerjoerg12.map.mapElements;

import de.junkerjoerg12.PhysicsObject;

public abstract class MapElement extends PhysicsObject{

    public MapElement() {
        super(0);
        this.setVisible(true);
    }

    public void calculatePosition(){

    }
}
