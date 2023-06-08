package Contenitore.Main;

import javax.swing.*;
import java.awt.*;

public class Pacman extends JPanel {

    int colonna = 36;
    int riga = 36;
    public int posx = (colonna) * 9;
    public int posy = riga * 15;
    public String dir = "";
    public String dirTrans = "";
    Image pacman1 = Toolkit.getDefaultToolkit().createImage("./src/images" + "\\pacman1.png");


    public Pacman() {

    }


    public void draw(Graphics g){
        System.out.println("acua");
        g.drawImage(pacman1, posx + 2, posy + 1, null);

    }


    public void muovi() {


        if (board1.nRiga(posy) == 9 && (board1.nColonna(posx) == 18 || board1.nColonna(posx) == 19) && dirTrans.equals("right")) {
            posx += 2;
            if (posx > 700) {
                posx = -30;
            }

            return;
        } else if (board1.nRiga(posy) == 9 && (board1.nColonna(posx)) == -1 && dirTrans.equals("left")) {
            posx = 36 * 19 - 2;
        }


        if (dirTrans.equals("right") && !board1.board[board1.nRiga(posy + 2)][board1.nColonna(posx) + 1]) {
            posx += 2;
            if (dir.equals("up") && board1.checkV(posx) && !board1.board[board1.nRiga(posy) - 1][board1.nColonna(posx)]) {
                dirTrans = "up";
            } else if (dir.equals("down") && board1.checkV(posx) && !board1.board[board1.nRiga(posy) + 1][board1.nColonna(posx)]) {
                dirTrans = "down";
            } else if (dir.equals("left")) {
                dirTrans = "left";
            }
        } else if (dirTrans.equals("left") && !board1.board[board1.nRiga(posy + 2)][board1.nColonna(posx)]) {
            posx -= 2;
            if (dir.equals("up") && board1.checkV(posx) && !board1.board[board1.nRiga(posy) - 1][board1.nColonna(posx)]) {
                dirTrans = "up";
            } else if (dir.equals("down") && board1.checkV(posx) && !board1.board[board1.nRiga(posy) + 1][board1.nColonna(posx)]) {
                dirTrans = "down";
            } else if (dir.equals("right")) {
                dirTrans = "right";
            }
        } else if (dirTrans.equals("up") && !board1.board[board1.nRiga(posy)][board1.nColonna(posx + 2)]) {
            posy -= 2;
            if (dir.equals("right") && board1.checkO(posy) && !board1.board[board1.nRiga(posy)][board1.nColonna(posx) + 1]) {
                dirTrans = "right";
            } else if (dir.equals("left") && board1.checkO(posy) && !board1.board[board1.nRiga(posy)][board1.nColonna(posx) - 1]) {
                dirTrans = "left";
            } else if (dir.equals("down")) {
                dirTrans = "down";
            }
        } else if (dirTrans.equals("down") && !board1.board[board1.nRiga(posy) + 1][board1.nColonna(posx + 2)]) {
            posy += 2;
            if (dir.equals("right") && board1.checkO(posy) && !board1.board[board1.nRiga(posy)][board1.nColonna(posx) + 1]) {
                dirTrans = "right";
            } else if (dir.equals("left") && board1.checkO(posy) && !board1.board[board1.nRiga(posy)][board1.nColonna(posx) - 1]) {
                dirTrans = "left";
            } else if (dir.equals("up")) {
                dirTrans = "up";
            }
        } else {
            dirTrans = dir + "";
        }

        if (dirTrans.equals("right")) {
            board1.numero = 1;
        } else if (dirTrans.equals("down")) {
            board1.numero = 2;
        } else if (dirTrans.equals("left")) {
            board1.numero = 3;
        } else if (dirTrans.equals("up")) {
            board1.numero = 4;
        }

    }




}