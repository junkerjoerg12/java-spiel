package de.junkerjoerg12.levels;

import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.junkerjoerg12.Game;

public class Leveldetails extends JPanel implements ActionListener {

    private Game game;
    private JButton back = new JButton("back");
    private String source;
    private JButton start = new JButton("start") ;

    public Leveldetails(Game game, String source) {
        this.game = game;
        this.source = source;
        this.setLayout(new GridBagLayout());
        this.setVisible(true);
        this.setBackground(Color.ORANGE);
        back.addActionListener(this);
        start.addActionListener(this);
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 1;
        this.add(back, constraints);
        constraints.gridx = 2;
        constraints.gridy = 2;
        this.add(start, constraints);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back) {
            game.remove(this);
            game.start();
             }else if(e.getSource() == start){
                game.addmap(source);
             }
        

    }
}