package Contenitore.Main;

import javax.swing.*;
import java.awt.*;
import java.util.Hashtable;

public class BoardUI extends JPanel {

    private final Hashtable<String, Image> images = new Hashtable<>();
//    private final String[] pathImages = {"board", "pacman1", "pacman2", "pacman3", "pacman4", "redGhost", "blueGhost", "orangeGhost", "pacmanFull", "gameover", "pinkGhost", "scaredGhost", "eyes"};
    private final Image background = Toolkit.getDefaultToolkit().createImage("./src/images/"+"board.png");
    private final Pacman pacman;
    private final Ghost pinkGhost, orangeGhost, blueGhost, redGhost;
    private final Cookies cookies;
    private final Apple apple;


    public BoardUI(Pacman pacman, Ghost redGhost, Ghost pinkGhost, Ghost orangeGhost, Ghost blueGhost, Cookies cookies, Apple apple){

        this.pacman = pacman;
        this.redGhost = redGhost;
        this.blueGhost = blueGhost;
        this.orangeGhost = orangeGhost;
        this.pinkGhost = pinkGhost;
        this.cookies = cookies;
        this.apple = apple;

    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        setOpaque(false);
        g.drawImage(background, 0, 0, null);

        cookies.draw(g);
        apple.draw(g);
        redGhost.draw(g);
        blueGhost.draw(g);
        orangeGhost.draw(g);
        pinkGhost.draw(g);
        pacman.draw(g);

    }



}
