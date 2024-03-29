package de.junkerjoerg12;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class Game extends JFrame {

    MainMenu mainMenu;

    public Game() {
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setUndecorated(true);
        this.setLayout(new BorderLayout());
        this.setVisible(true);
        mainMenu();
    }

    private void mainMenu() {
        this.mainMenu = new MainMenu();
        this.add(mainMenu, BorderLayout.CENTER);
        this.revalidate();
        this.repaint();
    }

}
