package de.junkerjoerg12;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainMenu extends JPanel {

    JButton start;
    JButton settings;
    JButton quit;
    ImageIcon icon = new ImageIcon("MainMenu-Background.png");

    public MainMenu(){

        JLabel idk = new JLabel();
        idk.setIcon(icon);
        this.add(idk);
        
        this.setLayout(new GridLayout(3, 1, 300, 0));
        start = new JButton("Start Game");
        settings = new JButton("Settings");
        quit = new JButton("Quit");
        this.setBackground(Color.GREEN);
        this.setOpaque(false);
        this.setVisible(true);
    }
}
