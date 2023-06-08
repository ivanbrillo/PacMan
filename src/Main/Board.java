package Contenitore.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Hashtable;

public class Board extends KeyAdapter implements ActionListener  {

    private final Timer timer = new Timer(15, this);

    private Palline palline;
    private Pacman pacman;
    private Fantasmino rosso, rosa, blue, arancione;

    private final boolean[][] board = {
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
        palline = new Palline();
        // TODO: 08/06/2023 ghosts behavioural patter
        rosso = new Fantasmino(pacman, 326, 252, "right", 0);
        rosa = new Fantasmino(pacman, 326, 324, "up", 4);
        blue = new Fantasmino(pacman, 290, 324, "right", 2, rosso);
        arancione = new Fantasmino(pacman, 358, 324, "left", 8, rosso);

        timer.start();

    }







    @Override
    public void keyPressed(KeyEvent e) {

    }



    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
