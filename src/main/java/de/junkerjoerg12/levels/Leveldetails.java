package de.junkerjoerg12.levels;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import de.junkerjoerg12.Game;
import de.junkerjoerg12.tools.Statreader;

public class Leveldetails extends JPanel implements ActionListener {

    private Game game;
    private JButton back = new JButton("back");
    private String source;
    private JButton start = new JButton("start");
    private Image backgroundImage;
    private Font timeFont = new Font("TimesRoman", Font.PLAIN, 30);

    public Leveldetails(Game game, String source) {
        this.game = game;
        this.source = source;
        this.setLayout(null);
        this.setVisible(true);
        this.setBackground(Color.CYAN);
        back.addActionListener(this);
        start.addActionListener(this);

        back.setBounds(50, 1000, 150, 40);
        this.add(back);
        start.setBounds(1700, 1000, 150, 40);
        this.add(start);

        try {
            backgroundImage = ImageIO.read(new File(
                    "src\\main\\resources\\1000x1000.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back) {
            game.remove(this);
            game.start();
        } else if (e.getSource() == start) {
            game.remove(this);
            game.addmap(source);
            game.revalidate();
            game.repaint();
            game.requestFocus();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, this); // am besten in die Mitte des Bildschirmes(links ist auch möglich dann
                                                  // halt die Buttons + time rechts)

        g.setFont(timeFont);

        g.drawString("Best time: " + getbestmin() + " min " + getbests() + " sec " + getbestms() + " ms", 770, 1000);// timer
        // anzeigen
    }

    public ArrayList<String> getstats() {
        return new Statreader().getbest();
    }

    public String getbestmin() {
        ArrayList<String> time = getstats();
        return time.get(0);
    }

    public String getbests() {
        ArrayList<String> time = getstats();
        return time.get(1);
    }

    public String getbestms() {
        ArrayList<String> time = getstats();
        return time.get(2);
    }

}