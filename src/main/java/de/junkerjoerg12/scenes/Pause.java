package de.junkerjoerg12.scenes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import de.junkerjoerg12.Game;
import de.junkerjoerg12.map.Map;

public class Pause extends JPanel implements ActionListener {

    private JButton backToMainMenu = new JButton("back to main menue");
    private JButton continueButton = new JButton("continue");
    
    private Game game;
    private Map map;

    public Pause(Game game) {
        this.game = game;
        this.map = game.getMap();
        backToMainMenu.addActionListener(this);
        continueButton.addActionListener(this);

        add(backToMainMenu);
        add(continueButton);

        game.remove(map);
        game.add(this);
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backToMainMenu) {
            game.removeAll();
            game.mainMenu();    
        } else if (e.getSource() == continueButton) {//funktioniert noch nicht
            game.remove(this);
            game.pause();
        }
    }

}
