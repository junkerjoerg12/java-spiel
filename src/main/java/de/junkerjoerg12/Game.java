package de.junkerjoerg12;

import de.junkerjoerg12.map.Map;
import de.junkerjoerg12.map.mapElements.Goal;
import de.junkerjoerg12.map.mapElements.Water;
import de.junkerjoerg12.scenes.Endscreen;
import de.junkerjoerg12.scenes.Lvlauswahl;
import de.junkerjoerg12.scenes.MainMenu;
import de.junkerjoerg12.scenes.Pause;
import de.junkerjoerg12.tools.Console;
import de.junkerjoerg12.tools.Gameloop;
import de.junkerjoerg12.tools.TimerForMap;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Game extends JFrame implements ActionListener, KeyListener {

  // keybinds
  private int keyRight = 68;
  private int keyLeft = 65;
  private int keyJump = 32;
  private int keyConsole = KeyEvent.VK_F1;

  // auf welchem Monitor das Spiel angezeigt werden soll
  // nur während entwicklung wichtig
  private byte monitor = 1;

  private MainMenu mainMenu;
  private Map map;
  public Console console;
  private Lvlauswahl lvlauswahl;
  private Endscreen endscreen;
  // private Pause pause;

  private final int targetFPS = 60;

  private double delayBetweenFrames; // in Millisekunden

  private Timer timer;
  private TimerForMap timerformap;
  private Gameloop gameloop;
  private Timer imageSwitcher;

  // misst die Zeit, die das Spiel Läuft
  private double upTime;

  private boolean autostart = false;// ob sich das Spiel gleich startet oder man erst ins Main Menue kommt

  public boolean buildMode;

  private boolean paused = false;

  // Sachen zum testen von performance
  Timer timerm;
  int calls = 0;
  public int updates = 0;
  public int draws = 0;
  long start = 0;
  long afterUpdate = 0;
  long fertig;

  public Game() {
    delayBetweenFrames = Math.floor(1.0 / targetFPS * 1000);

    timerm = new Timer(1000, this);
    imageSwitcher = new Timer(700, this);

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

    this.addKeyListener(this);

    if (autostart) {
      levelauswahl();
    }
  }

  public void mainMenu() {
    if (map != null) {
      remove(map);
      gameloop.pause();
    }
    this.mainMenu = new MainMenu(this);
    this.add(mainMenu, BorderLayout.CENTER);
    mainMenu.setVisible(true);
    revalidate();
    repaint();
    requestFocus();
  }

  public void levelauswahl() {
    lvlauswahl = new Lvlauswahl(this);
    remove(mainMenu);
    this.add(lvlauswahl, BorderLayout.CENTER);
    revalidate();
    repaint();
    this.requestFocus();
  }

  public void addmap(File mapfile) {
    remove(lvlauswahl);
    map = new Map(this, mapfile);
    map.build();
    map.setVisible(true);
    this.add(map, BorderLayout.CENTER);
    timerformap = new TimerForMap();
    revalidate();
    repaint();

    this.requestFocus();
    gameloop = new Gameloop((long) delayBetweenFrames, this);
    gameloop.start();
    imageSwitcher.start();
    timerm.start();
  }

  public void switchScene(JPanel oldpanel, JPanel newpanel) { // sollte bspw Settings removen und lvlauswahl adden
    remove(oldpanel);
    add(newpanel, BorderLayout.CENTER);
    revalidate();
    repaint();
    this.requestFocus();
  }

  public void pause() {
    gameloop.pause();
    new Pause(this);

    revalidate();
    repaint();
    paused = !paused;
  }

  public void stoppause(Map map) {
    map.setVisible(true);
    gameloop.go();
    revalidate();
    repaint();
  }

  public void setEndscreen() {
    endscreen = new Endscreen(this, getcurrentmin(), getcurrents(), getcurrentms(), map.getMapfile());
    gameloop.pause();
    remove(map);
    this.add(endscreen, BorderLayout.CENTER);
    endscreen.setVisible(true);
    revalidate();
    repaint();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    // wird immer wieder vom Timer aufgerufen, ist quasi die Gameloop
    if (e.getSource() == timer) {
    } else if (e.getSource() == timerm) {
      // System.out.println("calls: " + calls);
      // System.out.println("updates: " + updates);
      // System.out.println("draws: " + draws);
      // System.out.println("update Time: " + (afterUpdate - start));
      // System.out.println("drawTime: " + (fertig - afterUpdate));
      calls = 0;
      updates = 0;
      draws = 0;
    } else if (e.getSource() == imageSwitcher) {
      // switch image methode von jeder Mapobjekt klasse aufrufen
      Water.switchImage();
      Goal.switchImages();
    }
  }

  public void tick() {
    calls++;
    upTime += delayBetweenFrames;
    start = System.currentTimeMillis();
    map.update();
    afterUpdate = System.currentTimeMillis();
    map.draw();
    fertig = System.currentTimeMillis();
  }

  public long getcurrents() {
    return timerformap.calculateCurrentTimeInS();
  }

  public long getcurrentms() {
    return timerformap.calculateCurrentTimeInMs();
  }

  public long getcurrentmin() {
    return timerformap.calculateCurrentTimeInMin();
  }

  public boolean alreadybound(int key) {
    if (key == keyJump) {
      return true;
    } else if (key == keyRight) {
      return true;
    } else if (key == keyLeft) {
      return true;
    } else if (key == keyConsole) {
      return true;
    }
    return false;
  }

  @Override
  public void keyTyped(KeyEvent e) {
  }

  @Override
  public void keyPressed(KeyEvent e) {
    if (e.getKeyCode() == keyRight) {
      if (!map.getPlayer().collisionRight(map.getAllObjects())) {
        map.getPlayer().walkRight = true;
      }
    } else if (e.getKeyCode() == keyLeft) {
      if (!map.getPlayer().collisionLeft(map.getAllObjects())) {
        map.getPlayer().walkLeft = true;
      }
    } else if (e.getKeyCode() == keyJump) {
      if (map.getPlayer().collisionBottom(map.getAllObjects())) {
        map.getPlayer().jump = true;
      }
    } else if (e.getKeyCode() == keyConsole) {
      if (console == null) {
        console = new Console(this);
      } else {
        console.setVisible(!console.isVisible());
      }
    } else if (e.getKeyCode() == 27) {// esc
      pause();
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
    if (e.getKeyCode() == keyRight) {
      map.getPlayer().walkRight = false;
    } else if (e.getKeyCode() == keyLeft) {
      map.getPlayer().walkLeft = false;
    } else if (e.getKeyCode() == keyJump) {
      map.getPlayer().jump = false;
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

  public int getjumpkey() {
    return keyJump;
  }

  public int getleftkey() {
    return keyLeft;
  }

  public int getrightkey() {
    return keyRight;
  }

  public int getconsolekey() {
    return keyConsole;
  }

  public void setjumpkey(int key) {
    this.keyJump = key;
  }

  public void setleftkey(int key) {
    this.keyLeft = key;
  }

  public void setrightkey(int key) {
    this.keyRight = key;
  }

  public void setconsolekey(int key) {
    this.keyConsole = key;
  }

  public void setpaused(boolean b) {
    paused = b;
  }

  public static void main(String[] args) {
    new Game();
  }
}
