import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel("com.formdev.flatlaf.FlatDarkLaf");
        } catch (Exception ignored) {}

        JFrame frame = new JFrame();
        frame.setTitle("Pacman Game");

        frame.setIconImage(Toolkit.getDefaultToolkit().createImage("./src/images/pacman1.png"));
        Board board = new Board(frame);
        Menu menu = new Menu(board);
        frame.setSize(696, 832);

        JPanel container = new JPanel();
        container.setLayout(null);
        container.add(board.boardUI);
        container.add(menu);
        frame.add(container);

        frame.addKeyListener(board);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);

    }

}
