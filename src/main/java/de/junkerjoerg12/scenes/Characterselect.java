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
import java.awt.Graphics2D;

public class Characterselect extends JPanel implements ActionListener {

    private JButton back;

    private ArrayList<JButton> buttons = new ArrayList<>();
    public ArrayList<BufferedImage> images = new ArrayList<>();
    protected BufferedImage image;
    private int characters = 5;
    public int x = 720; // erster wert bei 5 Charakteren
    public int y = 450;

    private Image backgroundImage;
    private Game game;

    public Characterselect(Game game) {
        this.game = game;
        this.setLayout(new GridBagLayout());

        try {
            backgroundImage = ImageIO
                    .read(new File(Paths.get("src", "main", "resources", "trying.png").toString()));

            for (int i = 0; i < characters; i++) {
                BufferedImage img = ImageIO.read(new File(
                        Paths.get("src", "main", "resources", "assets", "characterRight" + i + ".png").toString()));
                images.add(img);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.LAST_LINE_START;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.fill = GridBagConstraints.BOTH;

        for (int i = 0; i < characters; i++) { // wenn es aus irgendeinem grund weniger character werden überarbeiten
            buttons.add(new JButton("Character " + (i + 1)));
            buttons.get(i).addActionListener(this);
            this.add(buttons.get(i), constraints);
            constraints.gridx += 1;
        }

        back = new JButton("back to main menu");

        back.addActionListener(this);

        constraints.gridx = constraints.gridx / 2;
        constraints.gridy = 2;
        this.add(back, constraints);

        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back) {
            game.remove(this);
            game.mainMenu();
        } else {
            for (int i = 0; i < characters; i++) {
                if (e.getSource() == buttons.get(i)) {
                    int previousCharacterIndex = game.getCharacter();
                    resetimg(previousCharacterIndex);
                    game.setCharacter(i);
                }
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, this);
        Graphics2D g2D = (Graphics2D) g;
        draw(g2D);
    }

    private void resetimg(int index) {
        GridBagConstraints constraints = ((GridBagLayout) this.getLayout()).getConstraints(buttons.get(index));
        int buttonX = buttons.get(index).getX();
        int imageX = buttonX + (buttons.get(index).getWidth() - 41) / 2;
        y = 450;
        // zurücksetzen y Position
        repaint();
    }

    public void draw(Graphics2D g) {

        if (!images.isEmpty() && images.size() == buttons.size()) {
            int selectedCharacterIndex = game.getCharacter();
            for (int i = 0; i < characters; i++) {
                GridBagConstraints constraints = ((GridBagLayout) this.getLayout()).getConstraints(buttons.get(i));
                int buttonX = buttons.get(i).getX();
                int imageX = buttonX + (buttons.get(i).getWidth() - 41) / 2;
                int offsetY = (i == selectedCharacterIndex) ? 10 : 0;
                g.drawImage(images.get(i), imageX, y + offsetY, null);
            }
        } else {
            System.err.println("Images liste ist leer (oder buttons != images)");
        }
    }
}
