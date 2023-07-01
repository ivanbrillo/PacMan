import javax.swing.*;

public class Menu extends JPanel {

    public Menu() {

        setLayout(null);
        JButton button = new JButton("Restart");
        button.setBounds(20,0,100,30);
        add(button);

        JLabel points = new JLabel("Points: 0");
        points.setBounds(600,0,50,30);
        add(points);


        setBounds(0, 760, 696, 30);
    }


}
