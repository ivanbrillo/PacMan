import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

enum Direction {right, down, left, up, not_moving}

public abstract class ComponentUI extends JPanel {

    protected Point position;
    private final Point initialPosition;
    private final Map<String, Image> images = new HashMap<>();
    protected Direction direction;
    protected Direction initialDirection;
    protected int step = 2;
    private Image image;
    private String lastPath = "";

    public ComponentUI(String imgName, Point position) {

        setImage(imgName);
        this.position = position;
        initialPosition = new Point(position);

    }

    public void resetComponent(){
        position = new Point(initialPosition);
        direction = initialDirection;
        setImage("pacmanFull");
    }

    public void setImage(String imgName) {

        if (lastPath.equals(imgName))
            return;

        if (!images.containsKey(imgName))
            images.put(imgName, Toolkit.getDefaultToolkit().createImage("./src/images/" + imgName + ".png"));

        image = images.get(imgName);
        lastPath = imgName;

    }

    public void draw(Graphics g) {
        g.drawImage(image, position.x + 2, position.y + 1, null);
    }


}
