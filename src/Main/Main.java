package Contenitore.Main;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {

        JFrame frame = new JFrame();
        Board board = new Board();
        frame.add(board.boardUI);
        frame.setSize(696, 792);

        frame.addKeyListener(board);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);

    }


}
