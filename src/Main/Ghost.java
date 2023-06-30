package Contenitore.Main;

import java.awt.*;

import javax.swing.Timer;
import java.util.*;

public abstract class Ghost extends ComponentUI {

    private static final Random randomGenerator = new Random();
    private final String ghostName;
    private final Point start;
    public boolean scared = false;
    protected Pacman pacman;
    protected Ghost rosso;
    protected Point scatteredPoint;
    boolean eaten = false;
    private long scaredTime = 0;
    // used for orange ghost
    protected boolean activeGhost;


    public Ghost(Pacman pacman, int x, int y, Direction dir, String name) {

        super(name, new Point(x, y));
        ghostName = name;

        this.pacman = pacman;
        start = new Point(x, y);

        direction = dir;
    }


    public Ghost(Pacman pacman, int x, int y, Direction dir, Ghost rosso, String name) {

        this(pacman, x, y, dir, name);
        this.rosso = rosso;
    }

    public void setActive(boolean active){
        activeGhost = active;
    }

    private static boolean canMoveTo(int row, int col) {
        return row >= 0 && row < Board.board.length && col >= 0 && col < Board.board[0].length && !Board.board[row][col];
    }

    private static double calculateDistance(int x1, int x2, int y1, int y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    public abstract void behaviouralMove(ArrayList<Direction> availableDirections);

    /**
     * @return true if the ghost is on teleporting point
     */
    public boolean updatePosition() {

        // when an eaten ghost return to the spawn point
        if (eaten && Board.numCell(position.x) == 9 && Board.numCell(position.y) == 7)
            setEaten(false);

        // handle teleporting
        if (Board.numCell(position.y) == 9 && (Board.numCell(position.x) == 17 || Board.numCell(position.x) == 18 || Board.numCell(position.x) == 19) && direction == Direction.right) {
            position.x += step;
            if (position.x > 700) position.x = -30;
            return true;
        }

        if (Board.numCell(position.y) == 9 && ((Board.numCell(position.x)) == -1 || Board.numCell(position.x) == 0) && direction == Direction.left) {
            position.x -= step;
            if (position.x <= -30) position.x = (36 * 19) - 2;
            return true;
        }

        if (direction == Direction.right && canMoveTo(Board.numCell(position.y + 2), Board.numCell(position.x) + 1))
            position.x += step;

        if (direction == Direction.left && canMoveTo(Board.numCell(position.y + 2), Board.numCell(position.x)))
            position.x -= step;

        if (direction == Direction.up && (canMoveTo(Board.numCell(position.y), Board.numCell(position.x + 2)) || (Board.numCell(position.y) == 8 && Board.numCell(position.x + 2) == 9)))
            position.y -= step;

        if (direction == Direction.down && canMoveTo(Board.numCell(position.y) + 1, Board.numCell(position.x + 2)))
            position.y += step;

        return false;
    }


    public void moveGhost(int milliSecond, Timer timer){

        if(!activeGhost)
            return;

        if(scared && System.currentTimeMillis() - scaredTime > 8000)
            notScared();

        move(milliSecond < 5000);

        if(eaten)
            move(false);

        Rectangle pacmanR = new Rectangle(pacman.position, new Dimension(10,10));
        Rectangle ghostR = new Rectangle(position, new Dimension(10,10));

        if (!scared && pacmanR.intersects(ghostR) && !eaten )
            timer.setActionCommand("game ended");
        else
            if (scared && pacmanR.intersects(ghostR))
                setEaten(true);

    }

    private void move(boolean scatter) {

        if (updatePosition())
            return;

        ArrayList<Direction> availableMoves = getAvailableDirections();

        if (availableMoves.isEmpty())
            return;

        if (availableMoves.size() == 1 || scared) {
            direction = availableMoves.get(randomGenerator.nextInt(availableMoves.size()));
            return;
        }

        if (eaten) {
            updateDirection(start, availableMoves);
            return;
        }

        if (scatter) {
            updateDirection(scatteredPoint, availableMoves);
            return;
        }

        behaviouralMove(availableMoves);

    }

    private ArrayList<Direction> getAvailableDirections() {

        ArrayList<Direction> availableMoves = new ArrayList<>();

        int newRow = Board.numCell(position.y + 2);
        int newCol = Board.numCell(position.x + 2);

        if (newCol + 1 < Board.board[0].length && !Board.board[newRow][newCol + 1] && Board.checkAngle(position.y) && direction != Direction.left)
            availableMoves.add(Direction.right);

        if (newCol >= 1 && !Board.board[newRow][newCol - 1] && Board.checkAngle(position.y) && direction != Direction.right)
            availableMoves.add(Direction.left);

        if (((!Board.board[Board.numCell(position.y) - 1][newCol] && direction != Direction.down) || (Board.numCell(position.y) == 9 && newCol == 9)) && Board.checkAngle(position.x))
            availableMoves.add(Direction.up);

        if (!Board.board[Board.numCell(position.y) + 1][newCol] && Board.checkAngle(position.x) && direction != Direction.up)
            availableMoves.add(Direction.down);

        return availableMoves;
    }


    public void updateDirection(Point target, ArrayList<Direction> availableDirection) {

        ArrayList<Double> distances = new ArrayList<>(Arrays.asList(1000.0, 1000.0, 1000.0, 1000.0));

        if (availableDirection.contains(Direction.right))
            distances.set(0, calculateDistance(Board.numCell(position.x) + 1, Board.numCell(target.x), Board.numCell(position.y + 2), Board.numCell(target.y + 2)));

        if (availableDirection.contains(Direction.down))
            distances.set(1, calculateDistance(Board.numCell(position.x + 2), Board.numCell(target.x + 2), Board.numCell(position.y) + 1, Board.numCell(target.y)));

        if (availableDirection.contains(Direction.left))
            distances.set(2, calculateDistance(Board.numCell(position.x) - 1, Board.numCell(target.x), Board.numCell(position.y + 2), Board.numCell(target.y + 2)));

        if (availableDirection.contains(Direction.up))
            distances.set(3, calculateDistance(Board.numCell(position.x + 2), Board.numCell(target.x + 2), Board.numCell(position.y) - 1, Board.numCell(target.y)));

        double minDistance = Collections.min(distances);
        int direction = distances.indexOf(minDistance);
//        right, down, left, up,
        this.direction = Direction.values()[direction];

    }

    public void scared() {

        if(!activeGhost)
            return;

        scared = true;
        scaredTime = System.currentTimeMillis();

//      invert the direction: right, down, left, up,
        direction = Direction.values()[(direction.ordinal() + 2) % 4];
        step = 1;

    }

    public void notScared() {
        scared = false;

        // solve the allignment problem that can be caused by the previous step = 1
        if (position.x % 2 != 0)
            position.x--;

        if (position.y % 2 != 0)
            position.y--;

        step = 2;
    }

    public void setEaten(boolean eaten) {

        notScared();
        this.eaten = eaten;

    }

    public void draw(Graphics g) {

        if (scared)
            setImage("scaredGhost");
        else if (eaten)
            setImage("eyes");
        else
            setImage(ghostName);

        super.draw(g);

    }


}

