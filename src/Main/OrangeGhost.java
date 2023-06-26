package Contenitore.Main;

import java.awt.*;

public class OrangeGhost extends Ghost{

    public OrangeGhost(Pacman pacman, int x, int y, Direction dir, Ghost rosso){
        super (pacman, x, y, dir, rosso, "orangeGhost");
        scatteredPoint = new Point(50, 754);
    }

    public void movement(boolean scattered){

        boolean decision = move(scattered);

        if( decision && !scattered ){
            behaviouralMove();
        }

        if( decision && scattered ) {
            updateDirection(scatteredPoint);
        }

    }

    public void behaviouralMove(){

        double dist = Math.sqrt((position.x - pacman.position.x) * (position.x - pacman.position.x) + (position.y - pacman.position.y) * (position.y - pacman.position.y));
        int target = 8;
        if (dist > 36 * target) {
            updateDirection(pacman.position);
        } else {
            updateDirection(scatteredPoint);
        }
    }
}


