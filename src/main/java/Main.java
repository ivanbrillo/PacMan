import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel("com.formdev.flatlaf.FlatDarkLaf");
        } catch (Exception ignored) {}

        JFrame frame = new JFrame();
        frame.setTitle("Pacman Game");
        frame.setIconImage(Toolkit.getDefaultToolkit().createImage("./src/images/redGhost.png"));
        Board board = new Board();
        frame.add(board.boardUI);
        frame.setSize(696, 792);

        frame.addKeyListener(board);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);

    }

}
