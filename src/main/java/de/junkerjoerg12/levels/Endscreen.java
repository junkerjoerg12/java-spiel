package de.junkerjoerg12.levels;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
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
    private Long min;
    private Long seconds;
    private Long ms;
    private Font timerFont = new Font("TimesRoman", Font.PLAIN, 20);
    private JButton back = new JButton("back");
    private JTextField timeField;

    public Endscreen(Game game, Long min, long seconds, Long ms) {
        this.game = game;
        this.min = min;
        this.seconds = seconds;
        this.ms = ms;
        this.setLayout(new GridBagLayout());
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
            game.mainMenu();
        }
    }
}
