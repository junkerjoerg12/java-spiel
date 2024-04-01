package de.junkerjoerg12;

import de.junkerjoerg12.character.Player;
import de.junkerjoerg12.map.Map;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class Game extends JFrame {

    MainMenu mainMenu;
    private Map map;
    private int targetFPS = 60;

    private double delayBetewenFrames; // in Millisekunden

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
        loop();
    }

    public void loop() {
        while (true) {
            long timeSAtart = System.currentTimeMillis();

            if (System.currentTimeMillis() - timeSAtart > delayBetewenFrames) {

                // frame anzeigen
                map.repaint();
                // i guesss thats it
            } else { // Wartet, bis der es an der >Zeit ist den Frame anzuzeigen
                try {
                    Thread.sleep((long) (delayBetewenFrames - (System.currentTimeMillis() - timeSAtart)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                // frame anzeigen
                map.repaint();
                // i guesss thats it
            }

            // an irgendeine Logische bedingung knüpfen
            stop();
            break;
        }
    }

    public void pause() {
        // pausiert das Spiel
    }

    public void stop() {

        // kehrt in hauptmenue zurrück
    }
}
