package de.junkerjoerg12;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JLabel;

public abstract class PhysicsObject extends JLabel {

  protected double acceleration;
  protected double velocityHorizontally;
  protected double velocityVertically;


  public PhysicsObject(double acceleration) {
    this.acceleration = acceleration;
    this.setBackground(Color.CYAN);
  }

  public boolean checkCollision(ArrayList<PhysicsObject> list ) {
    for (PhysicsObject p : list) {
      // if ()
    }


    return false;
  }
}
