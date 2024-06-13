package de.junkerjoerg12.levels;

import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import java.awt.GridBagConstraints;

import javax.swing.JPanel;

import de.junkerjoerg12.Game;

import java.awt.Color;
import java.awt.Graphics;

public class Lvlauswahl extends JPanel implements ActionListener {

    private JButton buttonlvl1;
    private JButton back;
    private Image backgroundImage;
    private Game game;

    public Lvlauswahl(Game game) {
        this.game = game;
        this.setLayout(new GridBagLayout());
        this.setVisible(true);

        this.setBackground(Color.ORANGE);
        try {
            backgroundImage = ImageIO.read(new File(
                    "src\\main\\resources\\MainMenu-Background.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        buttonlvl1 = new JButton("lvl1");
        back = new JButton("back to main menu");

        buttonlvl1.addActionListener(this);
        back.addActionListener(this);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.LAST_LINE_START;
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.fill = GridBagConstraints.BOTH;

        this.add(buttonlvl1, constraints);
        constraints.gridx = 0;
        constraints.gridy = 2;
        this.add(back, constraints);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonlvl1) {
            Leveldetails lvldetails = new Leveldetails(game, Paths.get("maps", "level1", "map1.txt").toString());
            game.switchScene(this, lvldetails);
        } else if (e.getSource() == back) {
            game.remove(this);
            game.mainMenu();
        }

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, this);
    }

}
