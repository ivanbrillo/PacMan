package Contenitore.Main;

import java.awt.*;
import java.util.Random;

public class Ghost extends ComponentUI {
//    public int position.x, position.y;
    private int startX, startY;
    private String dir;
    String iniziodir;
    private Pacman pacman;
    private Ghost rosso;
    private int target;
    public boolean scared = false;
    boolean mangiato = false;

    public Ghost(Pacman pacman, int x, int y, String dir, int target) {

        super("pinkGhost", new Point(x, y));

        this.pacman = pacman;
        this.startX = x;
        this.startY = y;
//        this.position.x = position.x; //326;
//        position.y = position.y; //252;
        this.dir = dir;
        this.iniziodir = dir;
        this.target = target;
        rosso = this;
    }


    public Ghost(Pacman pacman, int x, int y, String dir, int target, Ghost rosso) {

        this(pacman, x, y, dir, target);
        this.rosso = rosso;
    }

    public void muovi(boolean scatter) {

        if (mangiato && Board.nColonna(position.x) == 9 && Board.nColonna(position.y) == 7) {
            mangiato = false;
        }


        if (Board.nRiga(position.y) == 9 && (Board.nColonna(position.x) == 17 || Board.nColonna(position.x) == 18 || Board.nColonna(position.x) == 19) && dir.equals("right")) {
            position.x += spostamento;
            if (position.x > 700) {
                position.x = -30;
            }

            return;
        } else if (Board.nRiga(position.y) == 9 && ((Board.nColonna(position.x)) == -1 || Board.nColonna(position.x) == 0) && dir.equals("left")) {
            position.x -= spostamento;

            if (position.x <= -30) {
                position.x = 36 * 19 - 2;
            }

            return;
        }


        if (dir.equals("right") && Board.nColonna(position.x) + 1 < Board.board[0].length && !Board.board[Board.nRiga(position.y + 2)][Board.nColonna(position.x) + 1]) {
            position.x += spostamento;
        } else if (dir.equals("left") && Board.nColonna(position.x) >= 0 && !Board.board[Board.nRiga(position.y + 2)][Board.nColonna(position.x)]) {
            this.position.x -= spostamento;
        } else if (dir.equals("up") && ((!Board.board[Board.nRiga(position.y)][Board.nColonna(position.x + 2)]) || (Board.nRiga(position.y) == 8 && Board.nColonna(position.x + 2) == 9))) {
            position.y -= spostamento;
        } else if (dir.equals("down") && !Board.board[Board.nRiga(position.y) + 1][Board.nColonna(position.x + 2)]) {
            position.y += spostamento;
        }

        int nDir = 0;
        String consentite[] = new String[4];
        String dir2 = dir;
        if (Board.nColonna(position.x) + 1 < Board.board[0].length && !Board.board[Board.nRiga(position.y + 2)][Board.nColonna(this.position.x) + 1] && Board.checkV(position.y) && !dir.equals("left")) {
            consentite[nDir] = "right";
            dir2 = "right";
            nDir++;
            //	System.out.println(1);

        }// CAMBIATO ORA  34
        if (Board.nColonna(position.x) >= 1 && !Board.board[Board.nRiga(position.y + 2)][Board.nColonna(position.x) - 1] && Board.checkV(position.y) && !dir.equals("right")) {
            consentite[nDir] = "left";

            nDir++;
            dir2 = "left";
            //	System.out.println(2);

        } // CAMBIATO ORA
        if ((!Board.board[Board.nRiga(position.y) - 1][Board.nColonna(position.x + 2)] && Board.checkV(position.x) && !dir.equals("down")) || (Board.nRiga(position.y) == 9 && Board.nColonna(position.x + 2) == 9 && Board.checkV(position.x))) {
            consentite[nDir] = "up";
            nDir++;
            dir2 = "up";
            //			System.out.println(Board.nRiga(position.y-2)+" "+Board.nColonna(this.position.x+2));
            //			System.out.println(3);
        }
        if (!Board.board[Board.nRiga(position.y) + 1][Board.nColonna(position.x + 2)] && Board.checkV(position.x) && !dir.equals("up")) {
            consentite[nDir] = "down";
            nDir++;
            dir2 = "down";

        }


        if (nDir <= 1) {
            dir = dir2;
        } else {
            if (!mangiato) {

                if (scatter && !scared) {
                    if (target == 4) {
                        dir = scegli(80, 0);
                        return;
                    } else if (target == 0) {
                        dir = scegli(604, 0);
                        return;
                    } else if (target == 2) {
                        dir = scegli(680, 754);
                        return;
                    } else if (target == 8) {
                        dir = scegli(50, 754);
                    }
                }
                if (!(Board.nRiga(position.y + 2) == 9 && Board.nColonna(position.x) > 16) && !scared && !scatter) {
                    if (target == 0) {
                        dir = scegli(pacman.position.x, pacman.position.y);
                    } else if (target == 4) {
                        rosa();
                    } else if (target == 2) {
                        blue();
                    } else if (target == 8) {
                        arancione();
                    }
                }

                if (!(Board.nRiga(position.y + 2) == 9 && Board.nColonna(position.x) > 16) && scared && !scatter) {
                    Random generatore = new Random();
                    dir = consentite[generatore.nextInt(nDir)];
                }

            } else {

                dir = scegli(startX, startY);

            }
        }
    }
    public String scegli(int posx, int posy) {
        double dist1 = 10000, dist2 = 10000, dist3 = 10000, dist4 = 10000;
        String dir2 = "right";
        if (!Board.board[Board.nRiga(position.y + 2)][Board.nColonna(position.x) + 1] && !dir.equals("left")) {
            dist1 = Math.sqrt(Math.abs(((Board.nColonna(position.x) + 1) - Board.nColonna(posx)) * (((Board.nColonna(position.x) + 1) - Board.nColonna(posx)))) + Math.abs(((Board.nColonna(position.y + 2)) - Board.nColonna(posy + 2)) * (((Board.nColonna(position.y + 2)) - Board.nColonna(posy + 2)))));
        }
        if (Board.nColonna(position.x) >= 1 && !Board.board[Board.nRiga(position.y + 2)][Board.nColonna(position.x) - 1] && !dir.equals("right")) {
            dist2 = Math.sqrt(Math.abs(((Board.nColonna(position.x) - 1) - Board.nColonna(posx)) * (((Board.nColonna(position.x) - 1) - Board.nColonna(posx)))) + Math.abs(((Board.nColonna(position.y + 2)) - Board.nColonna(posy + 2)) * (((Board.nColonna(position.y + 2)) - Board.nColonna(posy + 2)))));

        }

        if (!Board.board[Board.nRiga(position.y) - 1][Board.nColonna(position.x + 2)] && !dir.equals("down")) {
            dist3 = Math.sqrt(Math.abs(((Board.nColonna(position.x + 2)) - Board.nColonna(posx + 2)) * (((Board.nColonna(position.x + 2)) - Board.nColonna(posx + 2)))) + Math.abs(((Board.nColonna(position.y) - 1) - Board.nColonna(posy)) * (((Board.nColonna(position.y) - 1) - Board.nColonna(posy)))));

        }
        if (!Board.board[Board.nRiga(position.y) + 1][Board.nColonna(position.x + 2)] && !dir.equals("up")) {
            dist4 = Math.sqrt(Math.abs(((Board.nColonna(position.x + 2)) - Board.nColonna(posx + 2)) * (((Board.nColonna(position.x + 2)) - Board.nColonna(posx + 2)))) + Math.abs(((Board.nColonna(position.y) + 1) - Board.nColonna(posy)) * (((Board.nColonna(position.y) + 1) - Board.nColonna(posy)))));

            //		System.out.println(Board.nRiga(this.posy)+1);
        }
        // System.out.println(dir + " " + dist1 + " " + dist3);
        if (dist1 <= dist2 && dist1 <= dist3 && dist1 <= dist4) {
            dir2 = "right";
        } else {
            if (dist2 <= dist1 && dist2 <= dist3 && dist2 <= dist4) {
                dir2 = "left";
            } else {
                if (dist3 <= dist2 && dist3 <= dist1 && dist3 <= dist4) {
                    dir2 = "up";
                } else if (dist4 <= dist2 && dist4 <= dist3 && dist4 <= dist1 && dist4 != 10000) {
                    dir2 = "down";
                }
            }
        }
        //	System.out.println(dir2);
        return dir2;

    }

    public void rosa() {

        if (pacman.direction == Direction.up) {

            if (pacman.position.y >= 144 && pacman.position.x >= 144) {
                dir = scegli(pacman.position.x - 144, pacman.position.y - 144);
            } else if (pacman.position.y >= 144 && pacman.position.x < 144) {
                dir = scegli(0, pacman.position.y - 144);
            } else if (pacman.position.y < 144 && pacman.position.x < 144) {
                dir = scegli(0, 0);
            } else if (pacman.position.y < 144 && pacman.position.x >= 144) {
                dir = scegli(pacman.position.x - 144, 0);
            }
        } else if (pacman.direction == Direction.down) {

            if (pacman.position.y < 612) {
                dir = scegli(pacman.position.x, pacman.position.y + 144);
            } else {
                dir = scegli(pacman.position.x, 754);
            }
        } else if (pacman.direction == Direction.right) {

            if (pacman.position.x < 540) {
                dir = scegli(pacman.position.x + 144, pacman.position.y);
            } else {
                dir = scegli(682, pacman.position.y);
            }
        } else if (pacman.direction == Direction.left) {

            if (pacman.position.x >= 144) {
                dir = scegli(pacman.position.x - 144, pacman.position.y);
            } else {
                dir = scegli(0, pacman.position.y);
            }
        } else {
            dir = scegli(pacman.position.x, pacman.position.y);
        }


    }


    public void blue() {

        int x = 0, y = 0;

        if (pacman.direction == Direction.up) {

            if (pacman.position.y >= 72 && pacman.position.x >= 72) {
                y = pacman.position.x - 72;
                x = pacman.position.x - 72;
            } else if (pacman.position.y >= 72 && pacman.position.x < 72) {
                x = 0;
                y = pacman.position.y - 72;
            } else if (pacman.position.y < 72 && pacman.position.x < 72) {
                x = 0;
                y = 0;
            } else if (pacman.position.y < 72 && pacman.position.x >= 72) {
                x = pacman.position.x - 72;
                y = 0;
            }
        } else if (pacman.direction == Direction.down) {

            if (pacman.position.y < 684) {
                x = pacman.position.x;
                y = pacman.position.y - 72;
            } else {
                x = pacman.position.x;
                y = pacman.position.y - 754;
            }

        } else if (pacman.direction == Direction.right) {

            if (pacman.position.x < 612) {
                x = pacman.position.x + 72;
                y = pacman.position.y;
            } else {
                x = 682;
                y = pacman.position.y;
            }
        } else if (pacman.direction == Direction.left) {

            if (pacman.position.x >= 72) {
                y = pacman.position.x - 72;
                y = pacman.position.y;
            } else {
                x = 0;
                y = pacman.position.y;
            }
        } else {
            x = pacman.position.x;
            y = pacman.position.y;
        }

        x = x + (x - rosso.position.x);
        y = y + (y - rosso.position.x);

        if (x > 754) {
            x = 754;
        }
        if (x < 0) {
            x = 0;
        }
        if (y > 684) {
            y = 684;
        }
        if (y < 0) {
            y = 0;
        }
        dir = scegli(x, y);

    }

    public void arancione() {

        double dist = Math.sqrt((position.x - pacman.position.x) * (position.x - pacman.position.x) + (position.y - pacman.position.y) * (position.y - pacman.position.y));
        if (dist > 36 * target) {
            dir = scegli(pacman.position.x, pacman.position.y);
        } else {
            dir = scegli(50, 754);
        }

    }


    public void scared(boolean scared) {
        this.scared = scared;
        if (dir.equals("right")) {
            dir = "left";
        } else if (dir.equals("left")) {
            dir = "right";
        } else if (dir.equals("up")) {
            dir = "down";
        } else if (dir.equals("down")) {
            dir = "up";
        }

        spostamento = 1;

    }

    public void norm() {
        this.scared = false;
        if (position.x % 2 != 0) {
            position.x++;
        }
        if (position.y % 2 != 0) {
            position.y++;
        }

        spostamento = 2;
    }


    public void mangiato() {

        norm();
        position.x = startX;
        position.y = startY;
        this.dir = iniziodir;
        this.mangiato = false;


    }

}

