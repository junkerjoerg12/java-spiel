package de.junkerjoerg12;

import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Settings extends JLabel implements ActionListener {

    private JButton button1;
    private JButton button2;
    private JButton button3;

    private Image backgroundImage;
    private Game game;

    public Settings(Game game) {
        this.game = game;

        this.setLayout(new GridBagLayout());
        this.setVisible(true);
        this.setBackground(Color.GRAY);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }

}
