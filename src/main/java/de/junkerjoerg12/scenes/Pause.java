package de.junkerjoerg12.scenes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

import de.junkerjoerg12.Game;
import de.junkerjoerg12.map.Map;

public class Pause extends JPanel implements ActionListener {

    private JButton backToMainMenu = new JButton("back to main menu");
    private JButton quitGame = new JButton("quit the game");
    private JButton continueButton = new JButton("continue");
    private JButton backtolvls = new JButton("Choose Level");

    private Image backgroundImage;

    private Game game;
    private Map map;

    public Pause(Game game) {

        this.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 1;

        this.game = game;
        this.map = game.getMap();

        backtolvls.setFocusable(false);
        backtolvls.addActionListener(this);
        this.add(backtolvls, constraints);

        constraints.gridy = 2;
        backToMainMenu.addActionListener(this);
        backToMainMenu.setFocusable(false);
        this.add(backToMainMenu, constraints);

        // constraints.gridy = 1;
        // continueButton.addActionListener(this);
        // continueButton.setFocusable(false);
        // this.add(continueButton, constraints);

        constraints.gridy = 3;
        quitGame.addActionListener(this);
        quitGame.setFocusable(false);
        this.add(quitGame, constraints);

        this.setBackground(Color.GRAY);
        game.getMap().setVisible(false);
        game.add(this);
        this.setVisible(true);

        try {
            backgroundImage = ImageIO
                    .read(new File(
                            Paths.get("src", "main", "resources", "assets", "trying.png").toString()));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backToMainMenu) {
            if(game.mapelementselect != null){
                game.leaveEasyBuildMode();
            }
            game.remove(this);
            game.mainMenu();
            game.setpaused(false);
        } else if (e.getSource() == continueButton) {// funktioniert noch nicht
            game.remove(this);
            game.stoppause(map);
            game.setpaused(false);
        } else if (e.getSource() == quitGame) {
            System.exit(0);
        } else if (e.getSource() == backtolvls) {
            game.remove(this);
            game.levelauswahl();
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, this);
    }

}
