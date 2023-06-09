package Contenitore.Main;

public class OrangeGhost extends Ghost{

    private final int target = 8;

    public OrangeGhost(Pacman pacman, int x, int y, String dir){
        super(pacman, x, y, dir, "orangeGhost");
    }

    public OrangeGhost(Pacman pacman, int x, int y, String dir, Ghost rosso){
        super (pacman, x, y, dir, rosso, "orangeGhost");
    }

    public void movement(boolean scattered){

        boolean decision = move(scattered);

        if( decision && !scattered ){
            behaviouralMove();
        }

        if( decision && scattered ) {
            dir = updateDirection(50, 754);
        }

    }

    public void behaviouralMove(){

        double dist = Math.sqrt((position.x - pacman.position.x) * (position.x - pacman.position.x) + (position.y - pacman.position.y) * (position.y - pacman.position.y));
        if (dist > 36 * target) {
            dir = updateDirection(pacman.position.x, pacman.position.y);
        } else {
            dir = updateDirection(50, 754);
        }
    }
}


