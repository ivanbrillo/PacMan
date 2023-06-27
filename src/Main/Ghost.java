package Contenitore.Main;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public abstract class Ghost extends ComponentUI {

    private final String ghostName;
    private final Point start;
    private final Direction startDir;
    public boolean scared = false;
    protected Direction dir;
    protected Pacman pacman;
    protected Ghost rosso;
    protected Point scatteredPoint;
    boolean eaten = false;

    public Ghost(Pacman pacman, int x, int y, Direction dir, String name) {

        super(name, new Point(x, y));
        ghostName = name;

        this.pacman = pacman;
        start = new Point(x, y);

        this.dir = dir;
        startDir = dir;
        rosso = this;
    }


    public Ghost(Pacman pacman, int x, int y, Direction dir, Ghost rosso, String name) {

        this(pacman, x, y, dir, name);
        this.rosso = rosso;
    }

    private static boolean canMoveTo(int row, int col) {
        return row >= 0 && row < Board.board.length && col >= 0 && col < Board.board[0].length && !Board.board[row][col];
    }

    private static double calculateDistance(int x1, int x2, int y1, int y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    public abstract void behaviouralMove();


    /**
     * @return true if the ghost is on teleporting point
     */
    public boolean updatePosition() {

        // when an eaten ghost return to the spawn point
        if (eaten && Board.numCell(position.x) == 9 && Board.numCell(position.y) == 7)
            eaten = false;

        // handle teleporting
        if (Board.numCell(position.y) == 9 && (Board.numCell(position.x) == 17 || Board.numCell(position.x) == 18 || Board.numCell(position.x) == 19) && dir == Direction.right) {
            position.x += step;
            if (position.x > 700) position.x = -30;
            return true;
        }

        if (Board.numCell(position.y) == 9 && ((Board.numCell(position.x)) == -1 || Board.numCell(position.x) == 0) && dir == Direction.left) {
            position.x -= step;
            if (position.x <= -30) position.x = (36 * 19) - 2;
            return true;
        }

        if (dir == Direction.right && canMoveTo(Board.numCell(position.y + 2), Board.numCell(position.x) + 1))
            position.x += step;

        if (dir == Direction.left && canMoveTo(Board.numCell(position.y + 2), Board.numCell(position.x)))
            position.x -= step;

        if (dir == Direction.up && (canMoveTo(Board.numCell(position.y), Board.numCell(position.x + 2)) || (Board.numCell(position.y) == 8 && Board.numCell(position.x + 2) == 9)))
            position.y -= step;

        if (dir == Direction.down && canMoveTo(Board.numCell(position.y) + 1, Board.numCell(position.x + 2)))
            position.y += step;

        return false;
    }

    public void move(boolean scatter) {
        if (updatePosition())
            return;

        ArrayList<Direction> availableMoves = new ArrayList<>();

        int newRow = Board.numCell(position.y + 2);
        int newCol = Board.numCell(position.x + 2);

        if (newCol + 1 < Board.board[0].length && !Board.board[newRow][newCol + 1] && Board.checkAngle(position.y) && dir != Direction.left)
            availableMoves.add(Direction.right);

        if (newCol >= 1 && !Board.board[newRow][newCol - 1] && Board.checkAngle(position.y) && dir != Direction.right)
            availableMoves.add(Direction.left);

        if (((!Board.board[Board.numCell(position.y) - 1][newCol] && dir != Direction.down) || (Board.numCell(position.y) == 9 && newCol == 9)) && Board.checkAngle(position.x))
            availableMoves.add(Direction.up);

        if (!Board.board[Board.numCell(position.y) + 1][newCol] && Board.checkAngle(position.x) && dir != Direction.up)
            availableMoves.add(Direction.down);

        if (availableMoves.isEmpty())
            return;

        if (availableMoves.size() == 1) {
            dir = availableMoves.get(0);
            return;
        }

        if (eaten) {
            updateDirection(start);
            return;
        }

        if (scatter) {
            updateDirection(scatteredPoint);
            return;
        }

        if (scared) {
            Random generatore = new Random();
            dir = availableMoves.get(generatore.nextInt(availableMoves.size()));
            return;
        }

        behaviouralMove();

    }


    public void updateDirection(Point target) {

        ArrayList<Double> distances = new ArrayList<>(Arrays.asList(1000.0, 1000.0, 1000.0, 1000.0));

        if (!Board.board[Board.numCell(position.y + 2)][Board.numCell(position.x) + 1] && dir != Direction.left)
            distances.set(0, calculateDistance(Board.numCell(position.x) + 1, Board.numCell(target.x), Board.numCell(position.y + 2), Board.numCell(target.y + 2)));

        if (!Board.board[Board.numCell(position.y) - 1][Board.numCell(position.x + 2)] && dir != Direction.down)
            distances.set(3, calculateDistance(Board.numCell(position.x + 2), Board.numCell(target.x + 2), Board.numCell(position.y) - 1, Board.numCell(target.y)));

        if (Board.numCell(position.x) >= 1 && !Board.board[Board.numCell(position.y + 2)][Board.numCell(position.x) - 1] && dir != Direction.right)
            distances.set(2, calculateDistance(Board.numCell(position.x) - 1, Board.numCell(target.x), Board.numCell(position.y + 2), Board.numCell(target.y + 2)));

        if (!Board.board[Board.numCell(position.y) + 1][Board.numCell(position.x + 2)] && dir != Direction.up)
            distances.set(1, calculateDistance(Board.numCell(position.x + 2), Board.numCell(target.x + 2), Board.numCell(position.y) + 1, Board.numCell(target.y)));

        double minDistance = Collections.min(distances);
        int direction = distances.indexOf(minDistance);
//        right, down, left, up,
        dir = Direction.values()[direction];

    }

    public void scared() {
        scared = true;

//      invert the direction: right, down, left, up,
        dir = Direction.values()[(dir.ordinal() + 2) % 4];
        step = 1;

    }

    public void norm() {
        this.scared = false;
        if (position.x % 2 != 0) {
            position.x++;
        }
        if (position.y % 2 != 0) {
            position.y++;
        }

        step = 2;
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

