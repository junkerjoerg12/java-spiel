package de.junkerjoerg12.scenes;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import de.junkerjoerg12.Game;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;

public class Mapelementscene extends JPanel implements ActionListener {

    private BufferedImage buttonIcon = ImageIO.read(new File(Paths.get("src", "main", "resources", "assets", "stone.png").toString()));
    private JButton button = new JButton(new ImageIcon(buttonIcon));
    //public ArrayList<BufferedImage> images = new ArrayList<>();
    //protected BufferedImage image;

    private Image backgroundImage;
    private Game game;

    public Mapelementscene(){
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setContentAreaFilled(false);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == button){
            System.out.println("button");
        }
    }

    

   
}

