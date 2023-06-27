package Contenitore.Main;

import java.awt.*;
import java.util.ArrayList;

public class OrangeGhost extends Ghost{

    public OrangeGhost(Pacman pacman, int x, int y, Direction dir, Ghost rosso){
        super (pacman, x, y, dir, rosso, "orangeGhost");
        scatteredPoint = new Point(50, 754);
    }

    public void behaviouralMove(ArrayList<Direction> availableDirection){

        double dist = Math.sqrt((position.x - pacman.position.x) * (position.x - pacman.position.x) + (position.y - pacman.position.y) * (position.y - pacman.position.y));
        int target = 8;
        if (dist > 36 * target) {
            updateDirection(pacman.position, availableDirection);
        } else {
            updateDirection(scatteredPoint, availableDirection);
        }
    }
}


