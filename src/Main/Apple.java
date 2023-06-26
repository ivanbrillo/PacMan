package Contenitore.Main;

import java.awt.*;
import java.util.List;
import java.util.Vector;

public class Apple {

    private static final Point[] applePos = {    // row - column
            new Point(1, 2),
            new Point(1, 15),
            new Point(17, 15),
            new Point(17, 2)
    };
    Vector<Point> applePosition = new Vector<>();


    public Apple() {

        applePosition.addAll(List.of(applePos));

    }


    public boolean checkMela(Point pacmanPosition) {

        for (Point p : applePosition)
            if (Math.abs(p.x * 36 - pacmanPosition.x) < 5 && Math.abs(p.y * 36 - pacmanPosition.y) < 5) {

                applePosition.remove(p);
                return true;

            }

        return false;

    }


    public void draw(Graphics g) {

        for (Point p : applePosition)
            g.fillOval(p.x * 36 + 8, p.y * 36 + 8, 16, 16);

    }


}
