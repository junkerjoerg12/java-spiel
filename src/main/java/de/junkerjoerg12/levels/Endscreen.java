package de.junkerjoerg12.levels;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import de.junkerjoerg12.Game;

public class Endscreen extends JPanel implements ActionListener {
    private Game game;
    private String time;
    private JButton back = new JButton("back");

    public Endscreen(Game game, String time) {
        this.game = game;
        this.time = time;
        this.setLayout(new GridBagLayout());
        this.setVisible(true);
        this.setBackground(Color.ORANGE);
        back.addActionListener(this);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 1;
        this.add(back, constraints);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back) {
            game.remove(this);
            //
        }
    }
}
