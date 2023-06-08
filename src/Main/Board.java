package Contenitore.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Board extends KeyAdapter implements ActionListener  {

    private final Timer timer = new Timer(15, this);

    private Cookies cookies;
    private Pacman pacman;
    private Ghost redGhost, pinkGhost, orangeGhost,blueGhost;
    private int milliSeconds = 0;
    private int millisecondsApple = 0;
    boolean game = true;


    public final BoardUI boardUI;

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


    public Board(){

        pacman = new Pacman();
        cookies = new Cookies();
        // TODO: 08/06/2023 ghosts behavioural patter
        redGhost = new Ghost(pacman, 326, 252, "right", 0);
        pinkGhost = new Ghost(pacman, 326, 324, "up", 4);
        blueGhost = new Ghost(pacman, 290, 324, "right", 2, redGhost);
        orangeGhost = new Ghost(pacman, 358, 324, "left", 8, redGhost);
        boardUI = new BoardUI(pacman, redGhost, pinkGhost, orangeGhost, blueGhost, cookies);

        timer.start();

    }







    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_ENTER) {

            // TODO: 08/06/2023 what if win


        }
        else {
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
            cookies.mangia(pacman.position.x, pacman.position.y);

            if (milliSeconds <= 5000) {
                redGhost.muovi(true);
                pinkGhost.muovi(true);
                blueGhost.muovi(true);
//              arancione.muovi(true);
            } else {
                redGhost.muovi(false);
                pinkGhost.muovi(false);
                blueGhost.muovi(false);

                if (cookies.startArancione) {
                    orangeGhost.muovi(false);
                }

            }


            Rectangle pacmanR = new Rectangle(pacman.position.x + 10, pacman.position.y + 10, 10, 10);
            Rectangle rossoR = new Rectangle(redGhost.position.x + 10, redGhost.position.y + 10, 10, 10);
            Rectangle rosaR = new Rectangle(pinkGhost.position.x + 10, pinkGhost.position.y + 10, 10, 10);
            Rectangle blueR = new Rectangle(blueGhost.position.x + 10, blueGhost.position.y + 10, 10, 10);
            Rectangle arancioneR = new Rectangle(orangeGhost.position.x + 10, orangeGhost.position.y + 10, 10, 10);

            if ((!redGhost.scared && pacmanR.intersects(rossoR) && !redGhost.mangiato) || (!pinkGhost.scared && pacmanR.intersects(rosaR) && !pinkGhost.mangiato) || (!blueGhost.scared && pacmanR.intersects(blueR) && !blueGhost.mangiato) || (!orangeGhost.scared && pacmanR.intersects(arancioneR) && orangeGhost.mangiato)) {
                timer.stop();
                game = false;
            } else {

                if (redGhost.scared && pacmanR.intersects(rossoR)) {
//                rosso.mangiato();
                    redGhost.mangiato = true;
                    redGhost.norm();
                }

                if (orangeGhost.scared && pacmanR.intersects(arancioneR)) {
                    orangeGhost.mangiato = true;
                    orangeGhost.norm();

//                arancione.mangiato();
                }

                if (blueGhost.scared && pacmanR.intersects(blueR)) {
//                blue.mangiato();
                    blueGhost.mangiato = true;
                    blueGhost.norm();

                }


                if (pinkGhost.scared && pacmanR.intersects(rosaR)) {
//                rosa.mangiato();
                    pinkGhost.mangiato = true;
                    pinkGhost.norm();

                }

            }
        } else {
            if (redGhost.mangiato) {
                redGhost.muovi(false);
            }
            if (blueGhost.mangiato) {
                blueGhost.muovi(false);
            }
            if (orangeGhost.mangiato) {
                orangeGhost.muovi(false);
            }
            if (pinkGhost.mangiato) {
                pinkGhost.muovi(false);
            }
        }

        if (cookies.vinto) {
            timer.stop();
            game = false;
        }


        boardUI.repaint();


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

}
