package de.junkerjoerg12.levels;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import java.awt.GridBagConstraints;

import javax.swing.JPanel;

import de.junkerjoerg12.Game;

import java.awt.Color;

public class Lvlauswahl extends JPanel implements ActionListener {

    private JButton buttonlvl1;
    private JButton back;
    private Game game;

    public Lvlauswahl(Game game) {
        this.game = game;
        this.setLayout(new GridBagLayout());
        this.setVisible(true);

        this.setBackground(Color.GRAY);
        buttonlvl1 = new JButton("lvl1");
        back = new JButton("back to main menu");

        buttonlvl1.addActionListener(this);
        back.addActionListener(this);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 9;
        this.add(buttonlvl1, constraints);
        this.add(back);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonlvl1) {
            Leveldetails lvldetails = new Leveldetails(game, "maps\\level1\\map1.txt");
            game.switchwindow(this, lvldetails);
        } else if (e.getSource() == back) {
            game.remove(this);
            game.mainMenu();
        }

    }
}
