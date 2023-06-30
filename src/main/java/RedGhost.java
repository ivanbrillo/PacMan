import java.awt.*;
import java.util.ArrayList;

public class RedGhost extends Ghost {

    public RedGhost(Pacman pacman, int x, int y, Direction dir){
        super(pacman, x, y, dir, "redGhost");
        scatteredPoint = new Point(604, 0);
    }

    public void behaviouralMove(ArrayList<Direction> availableDirection){
        updateDirection(pacman.position, availableDirection);
    }
}
