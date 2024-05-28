package de.junkerjoerg12.levels;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import java.awt.GridBagConstraints;

import javax.swing.JPanel;

import de.junkerjoerg12.Game;

import java.awt.Color;

public class Lvlauswahl extends JPanel implements ActionListener{

    private JButton buttonlvl1 = new JButton("lvl1");
    private Game game;

    public Lvlauswahl(Game game){
        this.game = game;
        this.setLayout(new GridBagLayout());
        this.setVisible(true);

        this.setBackground(Color.GRAY);

        buttonlvl1.addActionListener(this);
        
        GridBagConstraints constraints = new GridBagConstraints();     
        constraints.gridx = 1;
        constraints.gridy = 1;  
        this.add(buttonlvl1, constraints); 
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == buttonlvl1)
       game.remove(this);
       game.addLeveldetails("maps\\level1\\map1.txt");
    }
    }



