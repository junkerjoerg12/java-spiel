package de.junkerjoerg12.scenes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import de.junkerjoerg12.Game;
import de.junkerjoerg12.map.Map;

public class Pause extends JPanel implements ActionListener {

    private JButton backToMainMenu = new JButton("back to main menu");
    private JButton continueButton = new JButton("continue");
    
    private Game game;
    private Map map;

    public Pause(Game game) {
        this.game = game;
        this.map = game.getMap();
        backToMainMenu.addActionListener(this);
        //continueButton.addActionListener(this);

        add(backToMainMenu);
        //add(continueButton);

        game.getMap().setVisible(false);
        game.add(this);
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backToMainMenu) {
            game.remove(this);
            game.mainMenu();
            game.setpaused(false);
        } else if (e.getSource() == continueButton) {//funktioniert noch nicht
            game.remove(this);
            game.stoppause(map);
            game.setpaused(false);
        }
    }

}
