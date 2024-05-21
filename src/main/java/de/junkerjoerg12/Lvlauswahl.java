package de.junkerjoerg12;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import java.awt.GridBagConstraints;

import javax.swing.JButton;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;

public class Lvlauswahl extends JPanel implements ActionListener{

    private JButton button = new JButton("start lvl1");
    private Game game;

    public Lvlauswahl(Game game){
        this.game = game;
        this.setLayout(new GridBagLayout());
        this.setVisible(true);
       
        this.setBackground(Color.GRAY);

        button.addActionListener(this);
        
        GridBagConstraints constraints = new GridBagConstraints();     
        constraints.gridx = 1;
        constraints.gridy = 1;  
        this.add(button, constraints); 
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       game.addmap("maps\\level1\\map1.txt");
    }



}
