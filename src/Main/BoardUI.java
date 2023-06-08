package Contenitore.Main;

import javax.swing.*;
import java.awt.*;
import java.util.Hashtable;

public class BoardUI extends JPanel {

    private final Hashtable<String, Image> images = new Hashtable<>();
    private final String[] pathImages = {"board", "pacman1", "pacman2", "pacman3", "pacman4", "redGhost", "blueGhost", "orangeGhost", "pacmanFull", "gameover", "pinkGhost", "scaredGhost", "eyes"};

    public BoardUI(){
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        for (String pathImage : pathImages) {
            String path = "./src/images/" + pathImage + ".png";
            images.put(pathImage, toolkit.createImage(path));
        }
    }


    public void update(Point pacman, Point redGhost, Point pinkGhost, Point orangeGhost, Point blueGhost){


        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {





    }



}
