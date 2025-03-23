package de.junkerjoerg12.scenes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

public class RectanglePanel extends JPanel {

    private Point startPoint = null;
    private Point endPoint = null;

    public RectanglePanel() {
        // Add a mouse listener to capture click-and-drag events
        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // Store the starting point when the mouse is pressed
                startPoint = e.getPoint();
                endPoint = null; // Clear the endpoint
                repaint(); // Trigger a repaint to clear previous rectangles
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                // Update the endpoint as the mouse is dragged
                endPoint = e.getPoint();
                repaint(); // Trigger a repaint to update the rectangle
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // Finalize the endpoint when the mouse is released
                endPoint = e.getPoint();
                repaint(); // Trigger a final repaint to finish the rectangle
            }
        };

        this.addMouseListener(mouseAdapter);
        this.addMouseMotionListener(mouseAdapter);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Clears the panel for new drawings
        if (startPoint != null && endPoint != null) {
            g.setColor(Color.BLUE); // Set the rectangle's color

            // Calculate the rectangle's top-left corner and dimensions
            int x = Math.min(startPoint.x, endPoint.x);
            int y = Math.min(startPoint.y, endPoint.y);
            int width = Math.abs(startPoint.x - endPoint.x);
            int height = Math.abs(startPoint.y - endPoint.y);

            // Draw the rectangle
            g.drawRect(x, y, width, height);
        }
    }
}