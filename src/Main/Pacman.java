package Contenitore.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;



public class Pacman extends ComponentUI {

//    int colonna = 36;
//    int riga = 36;
//    public int position.x = (colonna) * 9;
//    public int position.y = riga * 15;
//    public String dir = "";
    private Direction dirTrans = Direction.not_moving;
//    Image pacman1 = Toolkit.getDefaultToolkit().createImage("./src/images" + "\\pacman1.png");


    public Pacman() {

        super("pacman1", new Point(324,540));

    }

    
    public void muovi() {


        if (Board.nRiga(position.y) == 9 && (Board.nColonna(position.x) == 18 || Board.nColonna(position.x) == 19) && dirTrans == Direction.right) {
            position.x += spostamento;
            if (position.x > 700) {
                position.x = -30;
            }

            return;
        } else if (Board.nRiga(position.y) == 9 && (Board.nColonna(position.x)) == -1 && dirTrans == Direction.left) {
            position.x = 36 * 19 - 2;
        }


        if (dirTrans == Direction.right && !Board.board[Board.nRiga(position.y + 2)][Board.nColonna(position.x) + 1]) {
            position.x += spostamento;
            if (direction == Direction.up && Board.checkV(position.x) && !Board.board[Board.nRiga(position.y) - 1][Board.nColonna(position.x)]) {
                dirTrans = Direction.up;
            } else if (direction == Direction.down && Board.checkV(position.x) && !Board.board[Board.nRiga(position.y) + 1][Board.nColonna(position.x)]) {
                dirTrans = Direction.down;
            } else if (direction == Direction.left) {
                dirTrans = Direction.left;
            }
        } else if (dirTrans == Direction.left && !Board.board[Board.nRiga(position.y + 2)][Board.nColonna(position.x)]) {
            position.x -= spostamento;
            if (direction == Direction.up && Board.checkV(position.x) && !Board.board[Board.nRiga(position.y) - 1][Board.nColonna(position.x)]) {
                dirTrans = Direction.up;
            } else if (direction == Direction.down && Board.checkV(position.x) && !Board.board[Board.nRiga(position.y) + 1][Board.nColonna(position.x)]) {
                dirTrans = Direction.down;
            } else if (direction == Direction.right) {
                dirTrans = Direction.right;
            }
        } else if (dirTrans == Direction.up && !Board.board[Board.nRiga(position.y)][Board.nColonna(position.x + 2)]) {
            position.y -= spostamento;
            if (direction == Direction.right && Board.checkO(position.y) && !Board.board[Board.nRiga(position.y)][Board.nColonna(position.x) + 1]) {
                dirTrans = Direction.right;
            } else if (direction == Direction.left && Board.checkO(position.y) && !Board.board[Board.nRiga(position.y)][Board.nColonna(position.x) - 1]) {
                dirTrans = Direction.left;
            } else if (direction == Direction.down) {
                dirTrans = Direction.down;
            }
        } else if (dirTrans == Direction.down && !Board.board[Board.nRiga(position.y) + 1][Board.nColonna(position.x + 2)]) {
            position.y += spostamento;
            if (direction == Direction.right && Board.checkO(position.y) && !Board.board[Board.nRiga(position.y)][Board.nColonna(position.x) + 1]) {
                dirTrans = Direction.right;
            } else if (direction == Direction.left && Board.checkO(position.y) && !Board.board[Board.nRiga(position.y)][Board.nColonna(position.x) - 1]) {
                dirTrans = Direction.left;
            } else if (direction == Direction.up) {
                dirTrans = Direction.up;
            }
        } else {
            dirTrans = direction;
        }

        long currentTimeMillis = System.currentTimeMillis();

        if(dirTrans == null)
            setImage("pacman1");
        else if (currentTimeMillis  % 300 > 140)
            setImage("pacman"+(dirTrans.ordinal()+1));      //because enum ints start from 0
        else setImage("pacmanFull");

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