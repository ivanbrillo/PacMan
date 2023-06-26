package Contenitore.Main;

import java.awt.*;

public class BlueGhost extends Ghost{

    public BlueGhost(Pacman pacman, int x, int y, Direction dir, Ghost rosso){
        super (pacman, x, y, dir, rosso, "blueGhost");
        scatteredPoint = new Point(680, 754);
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
            p.x = Math.max(pacman.position.x - 72, 0);
            p.y = Math.max(pacman.position.y - 72, 0);
        } else if (pacman.direction == Direction.down) {
            p.x = pacman.position.x;
            p.y = pacman.position.y < 684 ? pacman.position.y - 72 : pacman.position.y - 754;
        } else if (pacman.direction == Direction.right) {
            p.x = pacman.position.x < 612 ? pacman.position.x + 72 : 682;
            p.y = pacman.position.y;
        } else if (pacman.direction == Direction.left) {
            p.x = Math.max(pacman.position.x - 72, 0);
            p.y = pacman.position.y;
        }

        p.x = p.x + (p.x - rosso.position.x);
        p.y = p.y + (p.y - rosso.position.x);

        p.x = Math.min(Math.max(p.x, 0), 754);
        p.y = Math.min(Math.max(p.y, 0), 684);

        updateDirection(p);
    }

}
