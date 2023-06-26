package Contenitore.Main;

import java.awt.*;
import java.awt.event.KeyEvent;



public class Pacman extends ComponentUI {

    private Direction dirTrans = Direction.not_moving;
    private String oldImage = "pacman1";

    public Pacman() {

        super("pacman1", new Point(324,540));

    }


    public void muovi() {


        if (Board.numCell(position.y) == 9 && (Board.numCell(position.x) == 18 || Board.numCell(position.x) == 19) && dirTrans == Direction.right) {
            position.x += step;
            if (position.x > 700) {
                position.x = -30;
            }

            return;
        } else if (Board.numCell(position.y) == 9 && (Board.numCell(position.x)) == -1 && dirTrans == Direction.left) {
            position.x = 36 * 19 - 2;
        }


        if (dirTrans == Direction.right && !Board.board[Board.numCell(position.y + 2)][Board.numCell(position.x) + 1]) {
            position.x += step;
            if (direction == Direction.up && Board.checkAngle(position.x) && !Board.board[Board.numCell(position.y) - 1][Board.numCell(position.x)]) {
                dirTrans = Direction.up;
            } else if (direction == Direction.down && Board.checkAngle(position.x) && !Board.board[Board.numCell(position.y) + 1][Board.numCell(position.x)]) {
                dirTrans = Direction.down;
            } else if (direction == Direction.left) {
                dirTrans = Direction.left;
            }
        } else if (dirTrans == Direction.left && !Board.board[Board.numCell(position.y + 2)][Board.numCell(position.x)]) {
            position.x -= step;
            if (direction == Direction.up && Board.checkAngle(position.x) && !Board.board[Board.numCell(position.y) - 1][Board.numCell(position.x)]) {
                dirTrans = Direction.up;
            } else if (direction == Direction.down && Board.checkAngle(position.x) && !Board.board[Board.numCell(position.y) + 1][Board.numCell(position.x)]) {
                dirTrans = Direction.down;
            } else if (direction == Direction.right) {
                dirTrans = Direction.right;
            }
        } else if (dirTrans == Direction.up && !Board.board[Board.numCell(position.y)][Board.numCell(position.x + 2)]) {
            position.y -= step;
            if (direction == Direction.right && Board.checkAngle(position.y) && !Board.board[Board.numCell(position.y)][Board.numCell(position.x) + 1]) {
                dirTrans = Direction.right;
            } else if (direction == Direction.left && Board.checkAngle(position.y) && !Board.board[Board.numCell(position.y)][Board.numCell(position.x) - 1]) {
                dirTrans = Direction.left;
            } else if (direction == Direction.down) {
                dirTrans = Direction.down;
            }
        } else if (dirTrans == Direction.down && !Board.board[Board.numCell(position.y) + 1][Board.numCell(position.x + 2)]) {
            position.y += step;
            if (direction == Direction.right && Board.checkAngle(position.y) && !Board.board[Board.numCell(position.y)][Board.numCell(position.x) + 1]) {
                dirTrans = Direction.right;
            } else if (direction == Direction.left && Board.checkAngle(position.y) && !Board.board[Board.numCell(position.y)][Board.numCell(position.x) - 1]) {
                dirTrans = Direction.left;
            } else if (direction == Direction.up) {
                dirTrans = Direction.up;
            }
        } else {
            dirTrans = direction;
        }

        long currentTimeMillis = System.currentTimeMillis();



        String name;

        if(dirTrans == null)
            name = "pacman1";
        else if (currentTimeMillis  % 400 > 190)
            name = "pacman"+(dirTrans.ordinal()+1);      //because enum ints start from 0
        else
            name = "pacmanFull";

        if(!oldImage.equals(name))
            setImage(name);
        oldImage = name;

    }

    public void setDirection(int key){
        if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
            direction = Direction.up;
        } else if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
            direction = Direction.down;
        } else if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
            direction = Direction.left;
        } else if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
            direction = Direction.right;
        }
    }




}