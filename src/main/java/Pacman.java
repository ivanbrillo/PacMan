import java.awt.*;
import java.awt.event.KeyEvent;


public class Pacman extends ComponentUI {

    // Future direction update by key
    private Direction savedDirection;

    public Pacman() {
        super("pacmanFull", new Point(324, 540));
        direction = Direction.not_moving;
        initialDirection = direction;
    }

    public void move() {

        // handle teleporting
        if (Board.numCell(position.y) == 9 && (Board.numCell(position.x) == 18 || Board.numCell(position.x) == 19) && direction == Direction.right) {
            position.x += step;
            if (position.x > 700) position.x = -30;

            return;
        }

        if (Board.numCell(position.y) == 9 && (Board.numCell(position.x)) == -1 && direction == Direction.left)
            position.x = 36 * 19 - 2;

        incrementPosition();
        newDirection();
        setImage();

    }

    private void incrementPosition() {
        if (direction == Direction.right && !Board.board[Board.numCell(position.y + 2)][Board.numCell(position.x) + 1])
            position.x += step;
        else if (direction == Direction.left && !Board.board[Board.numCell(position.y + 2)][Board.numCell(position.x)])
            position.x -= step;
        else if (direction == Direction.up && !Board.board[Board.numCell(position.y)][Board.numCell(position.x + 2)])
            position.y -= step;
        else if (direction == Direction.down && !Board.board[Board.numCell(position.y) + 1][Board.numCell(position.x + 2)])
            position.y += step;
        else direction = savedDirection;   // means I'm stuck, so every direction is good

    }

    private void setImage() {

        String image;

        if (System.currentTimeMillis() % 400 > 190)
            image = direction == null ? "pacman1" : "pacman" + (direction.ordinal() + 1);
        else image = "pacmanFull";

        super.setImage(image);
    }

    /**
     * Set the direction = savedDirection when came at a turning point
     */
    private void newDirection() {

        // no direction changing when teleporting
        if ((Board.numCell(position.x) == 0 || Board.numCell(position.x) == 18) && Board.numCell(position.y) == 9)
            return;

        //handle change of degree 180 of the direction
        if (direction == null || savedDirection == Direction.values()[(direction.ordinal() + 2) % 4])
            direction = savedDirection;

        if (savedDirection == Direction.right && direction != Direction.right && Board.checkAngle(position.y) && !Board.board[Board.numCell(position.y)][Board.numCell(position.x) + 1])
            direction = Direction.right;
        else if (savedDirection == Direction.left && direction != Direction.left &&  Board.checkAngle(position.y) && !Board.board[Board.numCell(position.y)][Board.numCell(position.x) - 1])
            direction = Direction.left;
        else if (savedDirection == Direction.down && direction != Direction.down && Board.checkAngle(position.x) && !Board.board[Board.numCell(position.y) + 1][Board.numCell(position.x)])
            direction = Direction.down;
        else if (savedDirection == Direction.up && direction != Direction.up && Board.checkAngle(position.x) && !Board.board[Board.numCell(position.y) - 1][Board.numCell(position.x)])
            direction = Direction.up;

    }

    /**
     * Set the saved direction when a key is pressed
     */
    public void setDirection(int key) {

        if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) savedDirection = Direction.up;
        else if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) savedDirection = Direction.down;
        else if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) savedDirection = Direction.left;
        else if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) savedDirection = Direction.right;

        if (direction == Direction.not_moving) direction = savedDirection;

    }


}