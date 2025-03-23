package de.junkerjoerg12.scenes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import de.junkerjoerg12.Game;
import de.junkerjoerg12.tools.ImageLoader;
import de.junkerjoerg12.tools.Mapelementselect;

public class Mapelementscene extends JPanel implements ActionListener {

    private BufferedImage buttonIcon;

    private JButton button;
    // public ArrayList<BufferedImage> images = new ArrayList<>();
    // protected BufferedImage image;

    private Game game;
    private ArrayList<JButton> buttons = new ArrayList<>();
    private Mapelementselect mapelementselect;
    private static ArrayList<BufferedImage> elements;
    private static ArrayList<String> imageNames = new ArrayList<>();

    public Mapelementscene(Mapelementselect mapelementselect) {
        this.mapelementselect = mapelementselect;

        elements = ImageLoader.loadImagesFromFolder("src/main/resources/easyaccessassets");
        imageNames = ImageLoader.imageNames;

        try {
            for (int i = 0; i < imageNames.size(); i++) {
                buttons.add(new JButton(new ImageIcon(ImageIO
                        .read(new File(Paths.get("src", "main", "resources", "easyaccessassets", imageNames.get(i)) // !!!!!!!!!!!!!!!!!!!!!
                                .toString())))));
                buttons.get(i).setName(imageNames.get(i).substring(0, imageNames.get(i).lastIndexOf('.')));
                buttons.get(i).setBorder(BorderFactory.createEmptyBorder());
                buttons.get(i).setContentAreaFilled(false);
                buttons.get(i).addActionListener(this);
                this.add(buttons.get(i));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) { // 1 = stone, 2 = dirt,
        for (int i = 0; i < imageNames.size(); i++) {
            if (e.getSource() == buttons.get(i)) {
                System.out.println(buttons.get(i).getName());
                mapelementselect.selected = buttons.get(i).getName();
            }
        }

    }

}
