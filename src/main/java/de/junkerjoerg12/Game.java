package de.junkerjoerg12;

import de.junkerjoerg12.map.Map;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class Game extends JFrame {

    MainMenu mainMenu;
    private Map map;
    private int targetFPS = 1;

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
        int counter = 0;
        while (true) {
            long timeSAtart = System.currentTimeMillis();

            // alles bewegen und neu zeichnen und so
            map.tick();
            System.out.println("Zeichne neu...");

            // wartet falls nötig darauf, dass die zeit zwichen frames abgelaufen ist
            if ((System.currentTimeMillis() - timeSAtart - delayBetewenFrames) <= 0) {
                System.out.println("muss warten");
                try {
                    System.out.println(delayBetewenFrames - (System.currentTimeMillis() - timeSAtart));
                    Thread.sleep((long) (delayBetewenFrames - (System.currentTimeMillis() - timeSAtart)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                System.err.println("muss nicht warten");
            }

            // an irgendeine Logische bedingung knüpfen
            System.out.println(counter);
            if (counter > 100) {
                stop();
                break;
            }
            counter++;
        }
    }

    public void pause() {
        // pausiert das Spiel
    }

    public void stop() {

        // kehrt in hauptmenue zurrück
    }

}
