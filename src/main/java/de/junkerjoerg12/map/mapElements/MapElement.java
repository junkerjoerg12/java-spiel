package de.junkerjoerg12.map.mapElements;

import de.junkerjoerg12.PhysicsObject;
import de.junkerjoerg12.map.Map;

public abstract class MapElement extends PhysicsObject{

    public MapElement(Map map) {
        super(0,map);
        this.setVisible(true);
    }

    public void calculatePosition(){

    }
}
