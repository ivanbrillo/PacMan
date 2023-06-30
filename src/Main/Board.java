package Contenitore.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;

public class Board extends KeyAdapter implements ActionListener {

    public static final boolean[][] board = {{true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true}, {true, false, false, false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, true}, {true, false, true, true, false, true, true, true, false, true, false, true, true, true, false, true, true, false, true}, {true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true}, {true, false, true, true, false, true, false, true, true, true, true, true, false, true, false, true, true, false, true}, {true, false, false, false, false, true, false, false, false, true, false, false, false, true, false, false, false, false, true}, {true, true, true, true, false, true, true, true, false, true, false, true, true, true, false, true, true, true, true}, {true, true, true, true, false, true, false, false, false, false, false, false, false, true, false, true, true, true, true}, {true, true, true, true, false, true, false, true, true, true, true, true, false, true, false, true, true, true, true}, {false, false, false, false, false, false, false, true, true, false, true, true, false, false, false, false, false, false, false}, {true, true, true, true, false, true, false, true, true, true, true, true, false, true, false, true, true, true, true}, {true, true, true, true, false, true, false, false, false, false, false, false, false, true, false, true, true, true, true}, {true, true, true, true, false, true, false, true, true, true, true, true, false, true, false, true, true, true, true}, {true, false, false, false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, true}, {true, false, true, true, false, true, true, true, false, true, false, true, true, true, false, true, true, false, true}, {true, false, false, true, false, false, false, false, false, false, false, false, false, false, false, true, false, false, true}, {true, true, false, true, false, true, false, true, true, true, true, true, false, true, false, true, false, true, true}, {true, false, false, false, false, true, false, false, false, true, false, false, false, true, false, false, false, false, true}, {true, false, true, true, true, true, true, true, false, true, false, true, true, true, true, true, true, false, true}, {true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true}, {true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true}};

    public final BoardUI boardUI;
    private final Timer timer = new Timer(30, this);
    private final Cookies cookies = new Cookies();
    private final Apple apple = new Apple();
    private final Pacman pacman = new Pacman();

    private final Ghost redGhost = new RedGhost(pacman, 326, 252, Direction.right);
    ArrayList<Ghost> ghosts = new ArrayList<>(Arrays.asList(redGhost, new OrangeGhost(pacman, 358, 324, Direction.left, redGhost), new BlueGhost(pacman, 290, 324, Direction.right, redGhost), new PinkGhost(pacman, 326, 324, Direction.up)));

//    private final Ghost orangeGhost = new OrangeGhost(pacman, 358, 324, Direction.left, redGhost);
//    private final Ghost blueGhost = new BlueGhost(pacman, 290, 324, Direction.right, redGhost);
//    private final Ghost pinkGhost = new PinkGhost(pacman, 326, 324, Direction.up);
    boolean game = true;
    private int milliSeconds = 0;
    private int millisecondsApple = 0;
    private boolean win = false;

    public Board() {

        ArrayList<ComponentUI> component = new ArrayList<>(ghosts);
        component.add(pacman);

        boardUI = new BoardUI(component, cookies, apple);
        timer.start();
        timer.setActionCommand("game in progress");

    }

    public static int numCell(int position) {
        return position / 36;
    }

    public static boolean checkAngle(int position) {
        return position % 36 == 0;
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

        milliSeconds += 30;
        millisecondsApple += 30;

        pacman.move();
        // TODO: 28/06/2023 porta la logica dei ghost timer inside the class

        if (millisecondsApple >= 8000) {

            for(Ghost ghost : ghosts)
                ghost.notScared();

//
//            redGhost.notScared();
//            pinkGhost.notScared();
//            blueGhost.notScared();
//            orangeGhost.notScared();

        }
        win = cookies.eatCookies(pacman.position);

//        if (milliSeconds <= 5000) {
//
//
//
//            redGhost.moveGhost(true, timer);
//            pinkGhost.moveGhost(true, timer);
//            blueGhost.moveGhost(true, timer);
////              arancione.muovi(true);
//        } else {
//            redGhost.moveGhost(false, timer);
//            pinkGhost.moveGhost(false, timer);
//            blueGhost.moveGhost(false, timer);
//
//            if (cookies.startArancione) {
//                orangeGhost.moveGhost(false, timer);
//            }
//
//        }

        if (timer.getActionCommand().equals("game ended")) {
            timer.stop();
            game = false;
        }

        if (apple.checkMela(pacman.position)) {


            for(Ghost ghost : ghosts)
                ghost.scared();


//            redGhost.scared();
//            pinkGhost.scared();
//            blueGhost.scared();
//            orangeGhost.scared();

            millisecondsApple = 0;
        }


//        Rectangle pacmanR = new Rectangle(pacman.position.x, pacman.position.y, 10, 10);
//        Rectangle rossoR = new Rectangle(redGhost.position.x, redGhost.position.y, 10, 10);
//        Rectangle rosaR = new Rectangle(pinkGhost.position.x, pinkGhost.position.y, 10, 10);
//        Rectangle blueR = new Rectangle(blueGhost.position.x, blueGhost.position.y , 10, 10);
//        Rectangle arancioneR = new Rectangle(orangeGhost.position.x, orangeGhost.position.y, 10, 10);
//
//        if ((!redGhost.scared && pacmanR.intersects(rossoR) && !redGhost.eaten) || (!pinkGhost.scared && pacmanR.intersects(rosaR) && !pinkGhost.eaten) || (!blueGhost.scared && pacmanR.intersects(blueR) && !blueGhost.eaten) || (!orangeGhost.scared && pacmanR.intersects(arancioneR) && orangeGhost.eaten)) {
//            timer.stop();
//            game = false;
//        } else {
//
//            if (redGhost.scared && pacmanR.intersects(rossoR))
//                redGhost.setEaten(true);
//
//            if (orangeGhost.scared && pacmanR.intersects(arancioneR))
//                orangeGhost.setEaten(true);
//
//
//            if (blueGhost.scared && pacmanR.intersects(blueR))
//                blueGhost.setEaten(true);
//
//
//            if (pinkGhost.scared && pacmanR.intersects(rosaR))
//                pinkGhost.setEaten(true);
//
//        }


//        if (win) {
//            timer.stop();
//            game = false;
//        }


        boardUI.repaint();


    }

}
