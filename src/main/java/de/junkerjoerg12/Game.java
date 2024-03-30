package de.junkerjoerg12;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;

public class Game extends JFrame {

    MainMenu mainMenu;

    public Game() {
        System.out.println("game konmstruktor");
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setSize(200, 200);
        this.setUndecorated(true);
        this.setLayout(new BorderLayout());
        mainMenu();
        this.setVisible(true);
        System.out.println("game konstroktor beendet");
    }

    private void mainMenu() {
        this.mainMenu = new MainMenu();
        this.add(mainMenu, BorderLayout.CENTER);
    }

}
