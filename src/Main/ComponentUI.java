package Contenitore.Main;
import javax.swing.*;
import java.awt.*;

enum Direction {right, down, left, up, not_moving};

public abstract class ComponentUI extends JPanel {

    private Image image;
    private String lastPath = "";

    protected final Point position;
    protected Direction direction;
    protected int step = 2;


    public ComponentUI(String imgName, Point position){

        setImage(imgName);
        this.position = position;

    }

    public void setImage(String imgName){

        if(lastPath.equals(imgName))
            return;

        lastPath = imgName;
        image = Toolkit.getDefaultToolkit().createImage("./src/images/"+ imgName +".png");
    }

    public void draw(Graphics g){
        g.drawImage(image, position.x + 2, position.y + 1, null);
    }


}
