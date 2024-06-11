package de.junkerjoerg12;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.awt.GridBagConstraints;

public class Settings extends JPanel implements ActionListener, KeyListener {

    private JButton back;
    private JButton changejumpbutton;
    private JButton changerightbutton;
    private JButton changeleftbutton;
    private JButton changeconsolebutton;
    private JLabel showscurrentjumpkey;
    private JLabel showscurrentrightkey;
    private JLabel showscurrentleftkey;
    private JLabel showscurrentconsolekey;

    private boolean jump = false;
    private boolean right = false;
    private boolean left = false;
    private boolean console = false;

    private Image backgroundImage;
    private Game game;

    public Settings(Game game) {
        this.game = game;

        this.setLayout(new GridBagLayout());
        this.setVisible(true);
        this.setBackground(Color.GRAY);
        this.setFocusable(true); // Make the panel focusable
        this.addKeyListener(this);
        try {
            backgroundImage = ImageIO.read(new File(
                    "src\\main\\resources\\MainMenu-Background.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;

        constraints.fill = GridBagConstraints.BOTH;

        changejumpbutton = new JButton("bind jump:");
        changejumpbutton.addActionListener(this);
        this.add(changejumpbutton, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;

        showscurrentjumpkey = new JLabel("", SwingConstants.CENTER);
        showscurrentjumpkey.setText(getthekey(game.getjumpkey()));
        showscurrentjumpkey.setVisible(true);
        this.add(showscurrentjumpkey, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;

        changerightbutton = new JButton("bind move right:");
        changerightbutton.addActionListener(this);
        this.add(changerightbutton, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;

        showscurrentrightkey = new JLabel("", SwingConstants.CENTER);
        showscurrentrightkey.setText(getthekey(game.getrightkey()));
        showscurrentrightkey.setVisible(true);
        this.add(showscurrentrightkey, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;

        changeleftbutton = new JButton("bind move left:");
        changeleftbutton.addActionListener(this);
        this.add(changeleftbutton, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;

        showscurrentleftkey = new JLabel("", SwingConstants.CENTER);
        showscurrentleftkey.setText(getthekey(game.getleftkey()));
        showscurrentleftkey.setVisible(true);
        this.add(showscurrentleftkey, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;

        changeconsolebutton = new JButton("bind console key:");
        changeconsolebutton.addActionListener(this);
        this.add(changeconsolebutton, constraints);

        constraints.gridx = 1;
        constraints.gridy = 3;

        showscurrentconsolekey = new JLabel("", SwingConstants.CENTER);
        showscurrentconsolekey.setText(getthekey(game.getconsolekey()));
        showscurrentconsolekey.setVisible(true);
        this.add(showscurrentconsolekey, constraints);

        constraints.gridx = 0;
        constraints.gridy = 5;

        back = new JButton("return to main menu");
        this.add(back, constraints);
        back.addActionListener(this);
    }

    @Override
    public void addNotify() {
        super.addNotify();
        requestFocusInWindow(); // Request focus when the panel is added to its parent container
    }

    private String getthekey(int key) {
        return java.awt.event.KeyEvent.getKeyText(key);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back) {
            game.remove(this);
            game.mainMenu();
        } else if (e.getSource() == changejumpbutton) {
            showscurrentjumpkey.setText("please press any key");
            jump = true;
            requestFocusInWindow(); // Ensure the panel has focus when waiting for key input
        } else if (e.getSource() == changerightbutton) {
            showscurrentrightkey.setText("please press any key");
            right = true;
            requestFocusInWindow();
        } else if (e.getSource() == changeleftbutton) {
            showscurrentleftkey.setText("please press any key");
            left = true;
            requestFocusInWindow();
        } else if (e.getSource() == changeconsolebutton) {
            showscurrentconsolekey.setText("please press any key");
            console = true;
            requestFocusInWindow();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (jump) {
            if (game.alreadybound(e.getKeyCode()) == false) {
                game.setjumpkey(e.getKeyCode());
                showscurrentjumpkey.setText(getthekey(game.getjumpkey()));
                jump = false;
            } else {
                game.setjumpkey(32);
                showscurrentjumpkey.setText("this key is already bound to another action(reverted to space)");
                jump = false;
            }

        } else if (right) {
            if (game.alreadybound(e.getKeyCode()) == false) {
                game.setrightkey(e.getKeyCode());
                showscurrentrightkey.setText(getthekey(game.getrightkey()));
                right = false;
            } else {
                game.setrightkey(68);
                showscurrentrightkey.setText("this key is already bound to another action(reverted to d)");
                jump = false;
            }

        } else if (left) {
            if (game.alreadybound(e.getKeyCode()) == false) {
                game.setleftkey(e.getKeyCode());
                showscurrentleftkey.setText(getthekey(game.getleftkey()));
                left = false;
            } else {
                game.setleftkey(65);
                showscurrentleftkey.setText("this key is already bound to another action(reverted to a)");
                jump = false;
            }

        } else if (console) {
            if (game.alreadybound(e.getKeyCode()) == false) {
                game.setconsolekey(e.getKeyCode());
                showscurrentconsolekey.setText(getthekey(game.getconsolekey()));
                console = false;
            } else {
                game.setleftkey(130);
                showscurrentconsolekey
                        .setText("this key is already bound to another action(reverted to Dead Circumflex)");
                jump = false;
            }

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
