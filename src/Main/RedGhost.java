package Contenitore.Main;

import java.awt.*;

public class RedGhost extends Ghost {

    public RedGhost(Pacman pacman, int x, int y, Direction dir){
        super(pacman, x, y, dir, "redGhost");
        scatteredPoint = new Point(604, 0);
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
        updateDirection(pacman.position);
    }
}
