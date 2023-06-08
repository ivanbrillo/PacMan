package Contenitore.Main;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {

        JFrame frame = new JFrame();
        Board board = new Board();
        frame.add(board.boardUI);
        int larghezza = 696;
        int altezza = 792;
        frame.setSize(larghezza, altezza);

        frame.addKeyListener(board);
        frame.getContentPane().add(board.boardUI);

        frame.setVisible(true);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);

    }


}
