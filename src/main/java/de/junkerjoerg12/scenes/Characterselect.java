package de.junkerjoerg12.scenes;

import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import java.awt.GridBagConstraints;

import javax.swing.JPanel;

import de.junkerjoerg12.Game;

import java.awt.Graphics;

public class Characterselect extends JPanel implements ActionListener {

    private JButton back;

    private ArrayList<JButton> buttons = new ArrayList<>();
    public ArrayList<BufferedImage> images = new ArrayList<>();
    private int characters = 5;
    public int x;
    public int y;

    private Image backgroundImage;
    private Game game;

    public Characterselect(Game game) {
        this.game = game;
        this.setLayout(new GridBagLayout());
        this.setVisible(true);

        try {
            backgroundImage = ImageIO
                    .read(new File(Paths.get("src", "main", "resources", "trying.png").toString()));

            for (int i = 0; i < characters; i++) {
                images.add(ImageIO.read(new File(Paths
                        .get("src", "main", "resources", "assets", "characterRight" + i).toString())));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.LAST_LINE_START;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.fill = GridBagConstraints.BOTH;

        for (int i = 0; i < characters; i++) {
            buttons.add(new JButton("Character " + (i + 1)));
            buttons.get(i).addActionListener(this);
            this.add(buttons.get(i), constraints);
            constraints.gridx += 1;
        }

        back = new JButton("back to main menu");

        back.addActionListener(this);

        constraints.gridx = 2;
        constraints.gridy = 2;
        this.add(back, constraints);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back) {
            game.remove(this);
            game.mainMenu();
        } else if (e.getSource() == buttons.get(0)) {
            game.setCharacter(0);
        } else if (e.getSource() == buttons.get(1)) {
            game.setCharacter(1);
        } else if (e.getSource() == buttons.get(2)) {
            game.setCharacter(2);
        } else if (e.getSource() == buttons.get(3)) {
            game.setCharacter(3);
        } else if (e.getSource() == buttons.get(4)) {
            game.setCharacter(4);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, this);
        g.drawImage(images.get(0), 0, 0, null);

    }

}
