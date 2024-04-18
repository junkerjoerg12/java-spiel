package de.junkerjoerg12;

import de.junkerjoerg12.map.Map;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;

public class Game extends JFrame implements ActionListener {

    MainMenu mainMenu;
    private Map map;
    private int targetFPS = 1;

    private double delayBetewenFrames; // in Millisekunden

    private Timer timer;

    public Game() {
        delayBetewenFrames = 1.0 / targetFPS * 1000;
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setSize(200, 200);
        this.setUndecorated(true);
        this.setLayout(new BorderLayout());
        mainMenu();
        this.setVisible(true);

        timer = new Timer((int) delayBetewenFrames, this);
        timer.setRepeats(false);
        // sodass ich nicht immer irgendwelche knöpfe drücken muss
        start();
    }

    private void mainMenu() {
        this.mainMenu = new MainMenu(this);
        this.add(mainMenu, BorderLayout.CENTER);
    }

    public void start() {
        remove(mainMenu);

        map = new Map();
        map.setVisible(true);
        this.add(map, BorderLayout.CENTER);
        revalidate();
        repaint();
        timer.start();
    }

    public void pause() {
        // pausiert das Spiel
    }

    public void stop() {

        // kehrt in hauptmenue zurrück
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == timer) {
            long timeSAtart = System.currentTimeMillis();

            map.tick();

            // wartet falls nötig darauf, dass die zeit zwichen frames abgelaufen ist
            if ((System.currentTimeMillis() - timeSAtart - delayBetewenFrames) <= 0) {
                    timer.setDelay((int) (delayBetewenFrames - (System.currentTimeMillis() - timeSAtart)));
                    timer.start();
            } else {
                actionPerformed(e);
            }

        }

    }
}
