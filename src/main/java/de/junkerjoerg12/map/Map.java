package de.junkerjoerg12.map;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JPanel;

import de.junkerjoerg12.PhysicsObject;

public class Map extends JPanel{
  protected ArrayList<PhysicsObject> allObjectsonMap = new ArrayList<PhysicsObject>();

  public Map() {
    this.setBackground(Color.GRAY);
    this.setLayout(null);
    
  }
}
