package de.junkerjoerg12;

import de.junkerjoerg12.map.Map;
import de.junkerjoerg12.tools.Console;

import java.awt.BorderLayout;
import java.awt.GraphicsEnvironment;
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
    byte monitor = 1;

    private MainMenu mainMenu;
    private Map map;
    private Console console;

    private final int targetFPS = 60;

    private double delayBetweenFrames; // in Millisekunden

    private Timer timer;

    // misst die Zeit, die das Spiel Läuft
    private double upTime;

    public Game() {
        delayBetweenFrames = Math.round(1.0 / targetFPS * 1000);

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

        timer = new Timer((int) delayBetweenFrames, this);
        timer.setRepeats(true);

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
        if (e.getSource() == timer) {
            // raus am besten
            tick();

        }

    }

    private void tick() {
        upTime += delayBetweenFrames;
        map.getPlayer().calculatePosition();
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

    /*!!!
        fragen, warum ich die breaks nicht weglassen kann, bzw warum sonst die Konsole erscheint
    !!!*/
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
                    map.getPlayer().jump();
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
