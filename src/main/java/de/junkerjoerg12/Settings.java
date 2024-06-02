package de.junkerjoerg12;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.awt.GridBagConstraints;

public class Settings extends JPanel implements ActionListener, KeyListener {

    private JButton changejumpbutton;
    private JButton button2;
    private JButton button3;

    private Image backgroundImage;
    private Game game;

    public Settings(Game game) {
        this.game = game;

        this.setLayout(new GridBagLayout());
        this.setVisible(true);
        this.setBackground(Color.GRAY);

        try {
            backgroundImage = ImageIO.read(new File(
                    "src\\main\\resources\\MainMenu-Background.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 1;

        changejumpbutton = new JButton("bind jump:");
        this.add(changejumpbutton, constraints);
        changejumpbutton.addActionListener(this);

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == changejumpbutton) {

        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'keyPressed'");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'keyReleased'");
    }

}
