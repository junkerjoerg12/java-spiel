package de.junkerjoerg12;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

import java.io.File;

public class MainMenu extends JPanel implements ActionListener {

    private JButton start;
    private JButton settings;
    private JButton quit;

    private Image backgroundImage;

    private Game game;

    public MainMenu(Game game) {

        this.setLayout(new GridBagLayout());
        this.setVisible(true);
        this.setBackground(Color.GRAY);

        this.game = game;

        try {
            backgroundImage = ImageIO.read(new File(
                    "src\\main\\resources\\MainMenu-Background.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
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
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == quit) {
            System.exit(0);
        } else if (e.getSource() == start) {
            System.out.println("Spiel starten");
            game.start();
        } else if (e.getSource() == settings) {
            System.out.println("Einstellungen öffnen");
        }
    }
}
