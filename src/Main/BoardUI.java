package Contenitore.Main;

import javax.swing.*;
import java.awt.*;
import java.util.Hashtable;

public class BoardUI extends JPanel {

    private final Hashtable<String, Image> images = new Hashtable<>();
//    private final String[] pathImages = {"board", "pacman1", "pacman2", "pacman3", "pacman4", "redGhost", "blueGhost", "orangeGhost", "pacmanFull", "gameover", "pinkGhost", "scaredGhost", "eyes"};
    private final Image background = Toolkit.getDefaultToolkit().createImage("board");
    private final Pacman pacman;
    private final Ghost pinkGhost, orangeGhost, blueGhost, redGhost;
    private final Cookies balls;

    public BoardUI(Pacman pacman, Ghost redGhost, Ghost pinkGhost, Ghost orangeGhost, Ghost blueGhost, Cookies balls){
//        Toolkit toolkit = Toolkit.getDefaultToolkit();
//        for (String pathImage : pathImages) {
//            String path = "./src/images/" + pathImage + ".png";
//            images.put(pathImage, toolkit.createImage(path));
//        }

        this.pacman = pacman;
        this.redGhost = redGhost;
        this.blueGhost = blueGhost;
        this.orangeGhost = orangeGhost;
        this.pinkGhost = pinkGhost;
        this.balls = balls;

    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        setOpaque(false);
        g.drawImage(background, 0, 0, null);

        pacman.paintComponents(g);
        redGhost.paintComponents(g);
        blueGhost.paintComponents(g);
        orangeGhost.paintComponents(g);
        pinkGhost.paintComponents(g);
        balls.paintComponents(g);

    }



}
