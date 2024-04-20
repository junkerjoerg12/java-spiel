package de.junkerjoerg12;

import de.junkerjoerg12.character.Player;
import de.junkerjoerg12.map.Map;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.Timer;

public class Game extends JFrame implements ActionListener, KeyListener {

    final int keyRight = 68;
    final int keyLeft = 65;
    final int keyJump = 32;

    //auf welchem Monitor das Spiel angezeigt werden soll
    //nur während entwicklung wichtig
    byte monitor = 2;


    private MainMenu mainMenu;
    private Map map;
    private Player player;


    private int targetFPS = 1;

    private double delayBetewenFrames; // in Millisekunden

    private Timer timer;

    public Game() {
        delayBetewenFrames = 1.0 / targetFPS * 1000;

        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        // this.setUndecorated(true);
        this.setLayout(new BorderLayout());


        //öffnet spiel auf gewnschtem monitor 
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

        player = new Player();
        map = new Map(player);
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

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // soweit ich weiß brauchen wir diehiero

        switch (e.getKeyCode()) {
            case keyRight:
                System.out.println("right");
                player.setVelocityHorizontally(100.0);
                break;
            case keyLeft:
                System.out.println("left");
                player.setVelocityHorizontally(-100.0);
                break;
            case keyJump:
                System.out.println("Jump");
                player.setVelocityVertically(-100.0);
                break;
            default:
                break;
        }
    }


    @Override
    public void keyReleased(KeyEvent e) {
        // alle geschindigkeiten wieder null setzen
    }
}
