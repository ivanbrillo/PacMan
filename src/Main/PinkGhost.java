package Contenitore.Main;

import java.awt.*;

public class PinkGhost extends Ghost{

    public PinkGhost(Pacman pacman, int x, int y, String dir){
        super(pacman, x, y, dir, "pinkGhost");
        scatteredPoint = new Point(680, 754);

    }

    public void movement(boolean scattered){

        boolean decision = move(scattered);

        if( decision && !scattered ){
            behaviouralMove();
        }

        if( decision && scattered ) {
            dir = updateDirection(80, 0);
        }

    }

    public void behaviouralMove(){
        if (pacman.direction == Direction.up) {

            if (pacman.position.y >= 144 && pacman.position.x >= 144) {
                dir = updateDirection(pacman.position.x - 144, pacman.position.y - 144);
            } else if (pacman.position.y >= 144 && pacman.position.x < 144) {
                dir = updateDirection(0, pacman.position.y - 144);
            } else if (pacman.position.y < 144 && pacman.position.x < 144) {
                dir = updateDirection(0, 0);
            } else if (pacman.position.y < 144 && pacman.position.x >= 144) {
                dir = updateDirection(pacman.position.x - 144, 0);
            }
        } else if (pacman.direction == Direction.down) {

            if (pacman.position.y < 612) {
                dir = updateDirection(pacman.position.x, pacman.position.y + 144);
            } else {
                dir = updateDirection(pacman.position.x, 754);
            }
        } else if (pacman.direction == Direction.right) {

            if (pacman.position.x < 540) {
                dir = updateDirection(pacman.position.x + 144, pacman.position.y);
            } else {
                dir = updateDirection(682, pacman.position.y);
            }
        } else if (pacman.direction == Direction.left) {

            if (pacman.position.x >= 144) {
                dir = updateDirection(pacman.position.x - 144, pacman.position.y);
            } else {
                dir = updateDirection(0, pacman.position.y);
            }
        } else {
            dir = updateDirection(pacman.position.x, pacman.position.y);
        }

    }


}
