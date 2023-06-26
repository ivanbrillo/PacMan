package Contenitore.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Board extends KeyAdapter implements ActionListener {

    public static final boolean[][] board = {
            {true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true},
            {true, false, false, false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, true},
            {true, false, true, true, false, true, true, true, false, true, false, true, true, true, false, true, true, false, true},
            {true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true},
            {true, false, true, true, false, true, false, true, true, true, true, true, false, true, false, true, true, false, true},
            {true, false, false, false, false, true, false, false, false, true, false, false, false, true, false, false, false, false, true},
            {true, true, true, true, false, true, true, true, false, true, false, true, true, true, false, true, true, true, true},
            {false, false, false, true, false, true, false, false, false, false, false, false, false, true, false, true, false, false, false},
            {true, true, true, true, false, true, false, true, true, true, true, true, false, true, false, true, true, true, true},
            {false, false, false, false, false, false, false, true, true, false, true, true, false, false, false, false, false, false, false},
            {true, true, true, true, false, true, false, true, true, true, true, true, false, true, false, true, true, true, true},
            {false, false, false, true, false, true, false, false, false, false, false, false, false, true, false, true, false, false, false},
            {true, true, true, true, false, true, false, true, true, true, true, true, false, true, false, true, true, true, true},
            {true, false, false, false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, true},
            {true, false, true, true, false, true, true, true, false, true, false, true, true, true, false, true, true, false, true},
            {true, false, false, true, false, false, false, false, false, false, false, false, false, false, false, true, false, false, true},
            {true, true, false, true, false, true, false, true, true, true, true, true, false, true, false, true, false, true, true},
            {true, false, false, false, false, true, false, false, false, true, false, false, false, true, false, false, false, false, true},
            {true, false, true, true, true, true, true, true, false, true, false, true, true, true, true, true, true, false, true},
            {true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true},
            {true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true}
    };
    public final BoardUI boardUI;
    private final Timer timer = new Timer(15, this);
    private final Cookies cookies = new Cookies();
    private final Apple apple = new Apple();
    private final Pacman pacman = new Pacman();
    private final Ghost redGhost = new RedGhost(pacman, 326, 252, Direction.right);
    private final Ghost orangeGhost = new OrangeGhost(pacman, 358, 324, Direction.left, redGhost);
    private final Ghost blueGhost = new BlueGhost(pacman, 290, 324, Direction.right, redGhost);
    private final Ghost pinkGhost = new PinkGhost(pacman, 326, 324, Direction.up);
    boolean game = true;
    private int milliSeconds = 0;
    private int millisecondsApple = 0;

    public Board() {

        boardUI = new BoardUI(pacman, redGhost, pinkGhost, orangeGhost, blueGhost, cookies, apple);
        timer.start();

    }

    public static int nColonna(int posx) {
        return (int) posx / 36;
    }

    public static int nRiga(int posy) {
        return (int) posy / 36;
    }

    public static boolean checkV(int posx) {
        boolean angolo = false;
        if (posx % 36 == 0) {
            angolo = true;
        }
        return angolo;
    }

    public static boolean checkO(int posy) {
        boolean angolo = false;
        if (posy % 36 == 0) {
            angolo = true;
        }
        return angolo;
    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_ENTER) {

            // TODO: 08/06/2023 what if win

        } else {
            pacman.setDirection(e.getKeyCode());
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        milliSeconds += 15;
        if (milliSeconds % 30 == 0) {
            millisecondsApple += 30;
            pacman.muovi();
            if (millisecondsApple >= 8000) {
                redGhost.norm();
                pinkGhost.norm();
                blueGhost.norm();
                orangeGhost.norm();

            }
// TODO: 08/06/2023 mela
            // checkMela();
            cookies.eatCookies(pacman.position.x, pacman.position.y);

            if (milliSeconds <= 5000) {
                redGhost.movement(true);
                pinkGhost.movement(true);
                blueGhost.movement(true);
//              arancione.muovi(true);
            } else {
                redGhost.movement(false);
                pinkGhost.movement(false);
                blueGhost.movement(false);

                if (cookies.startArancione) {
                    orangeGhost.movement(false);
                }

            }

            if (apple.checkMela(pacman.position)) {

                redGhost.scared(true);
                pinkGhost.scared(true);
                blueGhost.scared(true);
                orangeGhost.scared(true);

                millisecondsApple = 0;
            }

            Rectangle pacmanR = new Rectangle(pacman.position.x + 10, pacman.position.y + 10, 10, 10);
            Rectangle rossoR = new Rectangle(redGhost.position.x + 10, redGhost.position.y + 10, 10, 10);
            Rectangle rosaR = new Rectangle(pinkGhost.position.x + 10, pinkGhost.position.y + 10, 10, 10);
            Rectangle blueR = new Rectangle(blueGhost.position.x + 10, blueGhost.position.y + 10, 10, 10);
            Rectangle arancioneR = new Rectangle(orangeGhost.position.x + 10, orangeGhost.position.y + 10, 10, 10);

            if ((!redGhost.scared && pacmanR.intersects(rossoR) && !redGhost.eaten) || (!pinkGhost.scared && pacmanR.intersects(rosaR) && !pinkGhost.eaten) || (!blueGhost.scared && pacmanR.intersects(blueR) && !blueGhost.eaten) || (!orangeGhost.scared && pacmanR.intersects(arancioneR) && orangeGhost.eaten)) {
                timer.stop();
                game = false;
            } else {

                if (redGhost.scared && pacmanR.intersects(rossoR)) {
//                    redGhost.setImage("eyes");
                    redGhost.eaten = true;
                    redGhost.norm();

//                    redGhost.mangiato();
                }

                if (orangeGhost.scared && pacmanR.intersects(arancioneR)) {
//                    orangeGhost.mangiato();
//                    orangeGhost.setImage("eyes");
                    orangeGhost.eaten = true;
                    orangeGhost.norm();

                }

                if (blueGhost.scared && pacmanR.intersects(blueR)) {
//                    blueGhost.mangiato();
//                    blueGhost.setImage("eyes");
                    blueGhost.eaten = true;
                    blueGhost.norm();

                }


                if (pinkGhost.scared && pacmanR.intersects(rosaR)) {
//                    pinkGhost.mangiato();
//                    pinkGhost.setImage("eyes");
                    pinkGhost.eaten = true;
                    pinkGhost.norm();

                }

            }
        } else {
            if (redGhost.eaten) {
                redGhost.movement(false);
            }
            if (blueGhost.eaten) {
                blueGhost.movement(false);
            }
            if (orangeGhost.eaten) {
                orangeGhost.movement(false);
            }
            if (pinkGhost.eaten) {
                pinkGhost.movement(false);
            }
        }

        if (cookies.win) {
            timer.stop();
            game = false;
        }


        boardUI.repaint();


    }

}
