package de.junkerjoerg12.character;

import de.junkerjoerg12.PhysicsObject;
import de.junkerjoerg12.map.Map;

public abstract class Character extends PhysicsObject {

    

    public Character(double acceleration, Map map) {
        super(acceleration, map);
    }
    
}
