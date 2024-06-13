package de.junkerjoerg12.levels;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

import de.junkerjoerg12.Game;
import de.junkerjoerg12.tools.Statreader;
import de.junkerjoerg12.tools.Statwriter;

public class Endscreen extends JPanel implements ActionListener {
    private Game game;
    private Font timerFont = new Font("TimesRoman", Font.PLAIN, 20);
    private JButton back = new JButton("back");
    private String time;

    private long min;
    private long sec;
    private long ms;

    public Endscreen(Game game, Long min, long seconds, Long ms) {
        this.game = game;
        this.min = min;
        this.sec = seconds;
        this.ms = ms;
        this.setLayout(new GridBagLayout());
        this.setBackground(Color.ORANGE);
        back.addActionListener(this);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 1;
        this.add(back, constraints);

        time = "Your Time: " + min + ":" + seconds + "," + ms;
        repaint();

        compareToBest();
    }

    private void compareToBest() {
        ArrayList<String> best = new Statreader().getbest();

        long bestMin = Long.parseLong(best.get(0));
        long bestSec = Long.parseLong(best.get(1));
        long bestMs = Long.parseLong(best.get(2));

        if (min < bestMin) {
            new Statwriter(min + "\n" + sec + "\n" + ms);
        } else if (min == bestMin && sec < bestSec) {
            new Statwriter(min + "\n" + sec + "\n" + ms);
        }
        else if (min == bestMin & sec == bestSec && ms < bestMs) {
            new Statwriter(min + "\n" + sec + "\n" + ms);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back) {
            game.remove(this);
            game.levelauswahl();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(timerFont);
        g.drawString(time, 885, 600);
    }
}
