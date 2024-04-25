package de.junkerjoerg12.character;

import de.junkerjoerg12.PhysicsObject;
import de.junkerjoerg12.map.Map;

public abstract class Character extends PhysicsObject {

    private long lastTick;

    public Character(double acceleration, Map map) {
        super(acceleration, map);
    }

    @Override
    public void calculatePosition() {
        velocityVertically = calculateVerticalVelocity();
        this.setLocation(
                Math.round((this.getX() + velocityHorizontally * (System.currentTimeMillis() - lastTick) / 1000)),
                Math.round((this.getY() + velocityVertically * (System.currentTimeMillis() - lastTick) / 1000)));
        this.lastTick = System.currentTimeMillis();
    }

    public void walk(int velocity) {
        velocityHorizontally = velocity;
    }

    public void jump() {
        jump = true;
        velocityVertically = -100;
        lastTimeInTouchWithFloor = System.currentTimeMillis();
    }
}
