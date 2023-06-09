package Contenitore.Main;

public class RedGhost extends Ghost {

    public RedGhost(Pacman pacman, int x, int y, String dir){
        super(pacman, x, y, dir, "redGhost");
    }

    public RedGhost(Pacman pacman, int x, int y, String dir, Ghost rosso){
        super (pacman, x, y, dir, rosso, "redGhost");
    }

    public void movement(boolean scattered){

        boolean decision = move(scattered);

        if( decision && !scattered ){
            behaviouralMove();
        }

        if( decision && scattered ) {
            dir = updateDirection(604, 0);
        }

    }

    public void behaviouralMove(){
        dir = updateDirection(pacman.position.x, pacman.position.y);    }
}
