package de.junkerjoerg12;

import de.junkerjoerg12.map.Map;
import de.junkerjoerg12.tools.Console;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.Timer;

public class Game extends JFrame implements ActionListener, KeyListener {

    // keybinds
    private final int keyRight = 68;
    private final int keyLeft = 65;
    private final int keyJump = 32;
    private final int keyConsole = 130;

    // auf welchem Monitor das Spiel angezeigt werden soll
    // nur während entwicklung wichtig
    private byte monitor = 1;

    private MainMenu mainMenu;
    private Map map;
    private Console console;

    private final int targetFPS = 60;

    private double delayBetweenFrames; // in Millisekunden

    private Timer timer;

    // misst die Zeit, die das Spiel Läuft
    private double upTime;

    private boolean autostart = false;// ob sich das Spiel gleich startet oder man erst ins Main Menue kommt

    public Game() {
        delayBetweenFrames = Math.round(1.0 / targetFPS * 1000);

        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        if (Toolkit.getDefaultToolkit().getScreenSize().equals(new Dimension(1920, 1080))) {
            this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        } else {
            this.setSize(1920, 1080);
        }
        this.setUndecorated(true);
        this.setLayout(new BorderLayout());

        // öffnet spiel auf gewünschtem Monitor
        this.setLocation(GraphicsEnvironment
                .getLocalGraphicsEnvironment()
                .getScreenDevices()[monitor - 1]
                .getDefaultConfiguration()
                .getBounds()
                .getLocation());

        mainMenu();
        this.setVisible(true);

        timer = new Timer((int) delayBetweenFrames, this);
        timer.setRepeats(true);

        this.addKeyListener(this);

        if (autostart) {
            start();
        }
    }

    private void mainMenu() {
        this.mainMenu = new MainMenu(this);
        this.add(mainMenu, BorderLayout.CENTER);
    }

    public void start() {
        remove(mainMenu);

        map = new Map(this);
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

    public void quit() {

        // kehrt in hauptmenue zurück
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // wird immer wieder vom Timer aufgerufen, ist quasi die Gameloop
        if (e.getSource() == timer) {
            tick();
        }
    }

    private void tick() {
        upTime += delayBetweenFrames;
        map.update();
        map.draw();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // if (e.getKeyCode() == keyConsole) {
        // if (console == null) {
        // console = new Console(map);
        // } else {
        // console.setVisible(!console.isVisible());
        // }
        // }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case keyRight:
                if (!map.getPlayer().collisionRight(map.getAllObjects())) {
                    map.getPlayer().walkRight = true;
                }
                break;
            case keyLeft:
                if (!map.getPlayer().collisionLeft(map.getAllObjects())) {
                    map.getPlayer().walkLeft = true;
                }
                break;
            case keyJump:
                if (map.getPlayer().collisionBottom(map.getAllObjects())) {
                    map.getPlayer().jump = true;
                }
                break;
            case keyConsole:
                if (console == null) {
                    console = new Console(this);
                } else {
                    console.setVisible(!console.isVisible());
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case keyRight:
                map.getPlayer().walkRight = false;
                break;
            case keyLeft:
                map.getPlayer().walkLeft = false;
                break;
            case keyJump:
                map.getPlayer().jump = false;
            default:
                break;
        }
    }

    public double getUptime() {
        return upTime;
    }

    public Map getMap() {
        return map;
    }

    public double getDelaybetweenFrames() {
        return delayBetweenFrames;
    }

    public static void main(String[] args) {
        new Game();
    }
}
