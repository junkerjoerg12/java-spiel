package de.junkerjoerg12.scenes;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

import de.junkerjoerg12.Game;
import de.junkerjoerg12.map.Map;
import de.junkerjoerg12.tools.Statreader;
import de.junkerjoerg12.tools.Statwriter;

public class Failscreen extends JPanel implements ActionListener {
    private Game game;
    private Font timerFont = new Font("TimesRoman", Font.PLAIN, 40);
    private JButton main = new JButton("Main Menu");
    private File mapfile;
    private JButton retry = new JButton("Try again");

    private BufferedImage background;

    public Failscreen(Game game, File mapfile) {
        this.game = game;
        this.mapfile = mapfile;
        this.setLayout(new GridBagLayout());
        main.addActionListener(this);
        retry.addActionListener(this);

        try {
            Map.setbackroundmapForFail(this);
        } catch (IOException e) {
            e.printStackTrace();
        }

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 1;
        this.add(main, constraints);
        constraints.gridx = 1;
        constraints.gridy = 2;
        this.add(retry, constraints);

        repaint();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == main) {
            game.remove(this);
            game.mainMenu();
        } else if (e.getSource() == retry) {
            game.addmap(mapfile);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, null);
        g.setFont(timerFont);
        FontMetrics metrics = g.getFontMetrics();
        // g.setColor(Color.WHITE);
        int x = (game.getMap().getWidth() - metrics.stringWidth("You died!")) / 2;
        g.drawString("You died!", x, 300 + metrics.getHeight());

    }

    public File getMapfile() {
        return mapfile;
    }

    public void setBackground(BufferedImage image) {
        background = image;
    }
}
