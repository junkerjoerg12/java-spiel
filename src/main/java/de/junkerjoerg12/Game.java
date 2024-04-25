package de.junkerjoerg12;

import de.junkerjoerg12.map.Map;

import java.awt.BorderLayout;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.Timer;

public class Game extends JFrame implements ActionListener, KeyListener {

    final int keyRight = 68;
    final int keyLeft = 65;
    final int keyJump = 32;

    // auf welchem Monitor das Spiel angezeigt werden soll
    // nur während entwicklung wichtig
    byte monitor = 2;

    private MainMenu mainMenu;
    private Map map;

    private int targetFPS = 30;

    private double delayBetewenFrames; // in Millisekunden

    private Timer timer;

    public Game() {
        delayBetewenFrames = 1.0 / targetFPS * 1000;

        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        // this.setUndecorated(true);
        this.setLayout(new BorderLayout());

        // öffnet spiel auf gewnschtem monitor
        this.setLocation(GraphicsEnvironment
                .getLocalGraphicsEnvironment()
                .getScreenDevices()[monitor - 1]
                .getDefaultConfiguration()
                .getBounds()
                .getLocation());

        mainMenu();
        this.setVisible(true);

        timer = new Timer((int) delayBetewenFrames, this);
        timer.setRepeats(false);

        this.addKeyListener(this);

        this.setFocusable(true);
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
        this.requestFocus();
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

            tick();

            // wartet falls nötig darauf, dass die zeit zwichen frames abgelaufen ist
            if ((System.currentTimeMillis() - timeSAtart - delayBetewenFrames) <= 0) {
                timer.setDelay((int) (delayBetewenFrames - (System.currentTimeMillis() - timeSAtart)));
                timer.start();
            } else {
                actionPerformed(e);
            }
        }

    }

    private void tick() {
        map.player.calculatePosition();
    }


    //alternativ Keybindings
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // soweit ich weiß brauchen wir diehiero

        switch (e.getKeyCode()) {
            case keyRight:
                map.getPLayer().walk(100);
                break;
            case keyLeft:
                map.getPLayer().walk(-100);
                break;
            case keyJump:
                if (map.getPLayer().checkCollision(map.getAllObjects()))
                    map.getPLayer().jump();
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case keyRight:
                map.getPLayer().walk(0);
                break;
            case keyLeft:
                map.getPLayer().walk(0);
                break;
            default:
                break;
        }
    }

    public static void main(String[] args) {
        new Game();
    }
}
