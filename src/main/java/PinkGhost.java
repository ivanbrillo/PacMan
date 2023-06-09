import java.awt.*;
import java.util.ArrayList;

public class PinkGhost extends Ghost{

    public PinkGhost(Pacman pacman, int x, int y, Direction dir){
        super(pacman, x, y, dir, "pinkGhost");
        scatteredPoint = new Point(80, 0);

    }


    public void behaviouralMove(ArrayList<Direction> availableDirection) {
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

        updateDirection(p, availableDirection);
    }


}
