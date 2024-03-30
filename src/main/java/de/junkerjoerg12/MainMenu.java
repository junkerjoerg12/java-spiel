package de.junkerjoerg12;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainMenu extends JPanel implements ActionListener {

    JButton start;
    JButton settings;
    JButton quit;

    public MainMenu() {

        this.setLayout(new GridBagLayout());
        this.setVisible(true);
        this.setBackground(Color.GRAY);

        //ginge, aber die Knöpfe wären an unerreichbaren stellen
        /*
        ImageIcon icon = new ImageIcon("C:\\Users\\andre\\Documents\\Informatik_Projekte\\java-spiel\\src\\main\\resources\\MainMenu-Background.png");
        JLabel idk = new JLabel();
        idk.setIcon(icon);
        this.add(idk);
        */

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 1;

        start = new JButton("Start Game");
        this.add(start, constraints);
        start.addActionListener(this);

        constraints.gridy = 2;
        settings = new JButton("Settings");
        this.add(settings, constraints);
        settings.addActionListener(this);

        quit = new JButton("Quit");
        constraints.gridy = 3;
        this.add(quit, constraints);
        quit.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == quit) {
            System.exit(0);
        } else if (e.getSource() == start) {
            System.out.println("Spiel starten");
        } else if (e.getSource() == settings) {
            System.out.println("Einstellungen öffnen");
        }
    }
}
