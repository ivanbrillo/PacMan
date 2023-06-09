package Contenitore.Main;

public class BlueGhost extends Ghost{

    public BlueGhost(Pacman pacman, int x, int y, String dir){
        super(pacman, x, y, dir, "blueGhost");
    }

    public BlueGhost(Pacman pacman, int x, int y, String dir, Ghost rosso){
        super (pacman, x, y, dir, rosso, "blueGhost");
    }

    public void movement(boolean scattered){

        boolean decision = move(scattered);

        if( decision && !scattered ){
            behaviouralMove();
        }

        if( decision && scattered ) {
            dir = updateDirection(680, 754);
        }

    }


    public void behaviouralMove(){
        int x = 0, y = 0;

        if (pacman.direction == Direction.up) {

            if (pacman.position.y >= 72 && pacman.position.x >= 72) {
                y = pacman.position.x - 72;
                x = pacman.position.x - 72;
            } else if (pacman.position.y >= 72 && pacman.position.x < 72) {
                x = 0;
                y = pacman.position.y - 72;
            } else if (pacman.position.y < 72 && pacman.position.x < 72) {
                x = 0;
                y = 0;
            } else if (pacman.position.y < 72 && pacman.position.x >= 72) {
                x = pacman.position.x - 72;
                y = 0;
            }
        } else if (pacman.direction == Direction.down) {

            if (pacman.position.y < 684) {
                x = pacman.position.x;
                y = pacman.position.y - 72;
            } else {
                x = pacman.position.x;
                y = pacman.position.y - 754;
            }

        } else if (pacman.direction == Direction.right) {

            if (pacman.position.x < 612) {
                x = pacman.position.x + 72;
                y = pacman.position.y;
            } else {
                x = 682;
                y = pacman.position.y;
            }
        } else if (pacman.direction == Direction.left) {

            if (pacman.position.x >= 72) {
                y = pacman.position.x - 72;
                y = pacman.position.y;
            } else {
                x = 0;
                y = pacman.position.y;
            }
        } else {
            x = pacman.position.x;
            y = pacman.position.y;
        }

        x = x + (x - rosso.position.x);
        y = y + (y - rosso.position.x);

        if (x > 754) {
            x = 754;
        }
        if (x < 0) {
            x = 0;
        }
        if (y > 684) {
            y = 684;
        }
        if (y < 0) {
            y = 0;
        }
        dir = updateDirection(x, y);
    }
}
