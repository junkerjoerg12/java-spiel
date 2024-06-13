package de.junkerjoerg12.levels;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import de.junkerjoerg12.Game;

public class Endscreen extends JPanel implements ActionListener {
    private Game game;
    private String time;
    private JButton back = new JButton("back");
    private JTextField timeField;

    public Endscreen(Game game, String time) {
        this.game = game;
        this.time = time;
        this.setLayout(new GridBagLayout());
        this.setBackground(Color.ORANGE);
        back.addActionListener(this);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 1;
        this.add(back, constraints);

        timeField = new JTextField("Ihre Zeit: " + time);
        constraints.gridy = 2;
        this.add(timeField, constraints);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back) {
            game.remove(this);
            game.mainMenu();
        }
    }
}
