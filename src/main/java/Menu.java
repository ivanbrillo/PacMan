import javax.swing.*;
import java.awt.*;


public class Menu extends JPanel {

    private final JLabel points;
    private final Board board;

    public Menu(Board board) {

        this.board = board;
        setLayout(null);
        JButton button = new JButton("Restart");
        button.addActionListener(e -> board.resetBoard("Press any key to start the Game"));

        button.setBounds(20,0,100,30);
        button.setFont(new Font("Agency FB", Font.BOLD, 20));
        button.setForeground(Color.white);
        button.setFocusable(false);
        add(button);

        points = new JLabel("Points: "+ board.getCookiesEaten());
        points.setForeground(Color.white);
        points.setFont(new Font("Agency FB", Font.BOLD, 20));
        points.setBounds(600,0,100,30);
        add(points);

        setBounds(0, 760, 696, 30);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        points.setText("Points: "+ board.getCookiesEaten());
    }


}
