package Contenitore.Main;

import java.awt.*;

public class PinkGhost extends Ghost{

    public PinkGhost(Pacman pacman, int x, int y, Direction dir){
        super(pacman, x, y, dir, "pinkGhost");
        scatteredPoint = new Point(80, 0);

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

    public void behaviouralMove() {
        Point p = new Point(pacman.position);

        if (pacman.direction == Direction.up) {
            p.x = Math.max(pacman.position.x - 144, 0);
            p.y = Math.max(pacman.position.y - 144, 0);
        } else if (pacman.direction == Direction.down) {
            p.x = pacman.position.x;
            p.y = pacman.position.y < 612 ? pacman.position.y + 144 : 754;
        } else if (pacman.direction == Direction.right) {
            p.x = pacman.position.x < 540 ? pacman.position.x + 144 : 682;
            p.y = pacman.position.y;
        } else if (pacman.direction == Direction.left) {
            p.x = Math.max(pacman.position.x - 144, 0);
            p.y = pacman.position.y;
        }

        updateDirection(p);
    }


}
