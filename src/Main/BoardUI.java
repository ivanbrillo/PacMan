package Contenitore.Main;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BoardUI extends JPanel {

    private final Image background = Toolkit.getDefaultToolkit().createImage("./src/images/" + "board.png");
    private final ArrayList<ComponentUI> components;

    private final Cookies cookies;
    private final Apple apple;

    public BoardUI(ArrayList<ComponentUI> components, Cookies cookies, Apple apple) {

        this.components = components;
        this.cookies = cookies;
        this.apple = apple;

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setOpaque(false);

        g.drawImage(background, 0, 0, null);

        cookies.draw(g);
        apple.draw(g);

        for (ComponentUI component : components)
            component.draw(g);

    }


}
