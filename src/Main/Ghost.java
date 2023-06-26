package Contenitore.Main;

import java.awt.*;
import java.util.*;

public abstract class Ghost extends ComponentUI {

    private Point start;
    protected Direction dir;
    private Direction startDir;
    protected Pacman pacman;
    protected Ghost rosso;
    public boolean scared = false;
    boolean eaten = false;
    private final String ghostName;
    protected Point scatteredPoint;

    public Ghost(Pacman pacman, int x, int y, Direction dir, String name) {

        super(name, new Point(x, y));
        ghostName = name;

        this.pacman = pacman;
        start = new Point(x, y);

        this.dir = dir;
        this.startDir = dir;
        rosso = this;
    }


    public Ghost(Pacman pacman, int x, int y, Direction dir, Ghost rosso, String name) {

        this(pacman, x, y, dir, name);
        this.rosso = rosso;
    }


    public abstract void movement(boolean scattered);


        /**
         * @return true if there is a decision to take on which direction (means more direction available)
         */
    public boolean move(boolean scatter) {

        if (eaten && Board.nColonna(position.x) == 9 && Board.nColonna(position.y) == 7) {
            eaten = false;
//            setImage(ghostName);
        }


        if (Board.nRiga(position.y) == 9 && (Board.nColonna(position.x) == 17 || Board.nColonna(position.x) == 18 || Board.nColonna(position.x) == 19) && dir == Direction.right) {
            position.x += step;
            if (position.x > 700) {
                position.x = -30;
            }

            return false;
        } else if (Board.nRiga(position.y) == 9 && ((Board.nColonna(position.x)) == -1 || Board.nColonna(position.x) == 0) && dir == Direction.left) {
            position.x -= step;

            if (position.x <= -30) {
                position.x = 36 * 19 - 2;
            }
            return false;
        }


        if (dir == Direction.right && Board.nColonna(position.x) + 1 < Board.board[0].length && !Board.board[Board.nRiga(position.y + 2)][Board.nColonna(position.x) + 1]) {
            position.x += step;
        } else if (dir == Direction.left && Board.nColonna(position.x) >= 0 && !Board.board[Board.nRiga(position.y + 2)][Board.nColonna(position.x)]) {
            this.position.x -= step;
        } else if (dir == Direction.up && ((!Board.board[Board.nRiga(position.y)][Board.nColonna(position.x + 2)]) || (Board.nRiga(position.y) == 8 && Board.nColonna(position.x + 2) == 9))) {
            position.y -= step;
        } else if (dir == Direction.down && !Board.board[Board.nRiga(position.y) + 1][Board.nColonna(position.x + 2)]) {
            position.y += step;
        }

        int nDir = 0;
        String[] consentite = new String[4];
        Direction dir2 = dir;
        if (Board.nColonna(position.x) + 1 < Board.board[0].length && !Board.board[Board.nRiga(position.y + 2)][Board.nColonna(this.position.x) + 1] && Board.checkV(position.y) && dir != Direction.left) {
            consentite[nDir] = "right";
            dir2 = Direction.right;
            nDir++;
            //	System.out.println(1);

        }// CAMBIATO ORA 34
        if (Board.nColonna(position.x) >= 1 && !Board.board[Board.nRiga(position.y + 2)][Board.nColonna(position.x) - 1] && Board.checkV(position.y) && dir != Direction.right) {
            consentite[nDir] = "left";

            nDir++;
            dir2 = Direction.left;
            //	System.out.println(2);

        } // CAMBIATO ORA
        if ((!Board.board[Board.nRiga(position.y) - 1][Board.nColonna(position.x + 2)] && Board.checkV(position.x) && dir != Direction.down) || (Board.nRiga(position.y) == 9 && Board.nColonna(position.x + 2) == 9 && Board.checkV(position.x))) {
            consentite[nDir] = "up";
            nDir++;
            dir2 = Direction.up;
            //			System.out.println(Board.nRiga(position.y-2)+" "+Board.nColonna(this.position.x+2));
            //			System.out.println(3);
        }
        if (!Board.board[Board.nRiga(position.y) + 1][Board.nColonna(position.x + 2)] && Board.checkV(position.x) && dir != Direction.up) {
            consentite[nDir] = "down";
            nDir++;
            dir2 = Direction.down;

        }


        if (nDir <= 1) {
            dir = dir2;
        } else {
            if (!eaten) {

                if (scatter && !scared) {

                        updateDirection(scatteredPoint);
                        return true;

                }
                if (!(Board.nRiga(position.y + 2) == 9 && Board.nColonna(position.x) > 16) && !scared && !scatter) {
                   return true;
                }

                if (!(Board.nRiga(position.y + 2) == 9 && Board.nColonna(position.x) > 16) && scared && !scatter) {
                    Random generatore = new Random();
                    dir = Direction.valueOf(consentite[generatore.nextInt(nDir)]);
//                    System.out.println(dir);
                }

            } else {
                updateDirection(start);
            }

        }
        return false;
    }

    public void updateDirection(Point target) {

        ArrayList<Double> distances = new ArrayList<>(Arrays.asList(1000.0, 1000.0, 1000.0, 1000.0));

        if (!Board.board[Board.nRiga(position.y + 2)][Board.nColonna(position.x) + 1] && dir != Direction.up)
            distances.set(0, calculateDistance(Board.nColonna(position.x) + 1, Board.nColonna(target.x), Board.nColonna(position.y + 2), Board.nColonna(target.y + 2)));

        if (!Board.board[Board.nRiga(position.y) - 1][Board.nColonna(position.x + 2)] && dir != Direction.down)
            distances.set(3, calculateDistance(Board.nColonna(position.x + 2), Board.nColonna(target.x + 2), Board.nColonna(position.y) - 1, Board.nColonna(target.y)));

        if (Board.nColonna(position.x) >= 1 && !Board.board[Board.nRiga(position.y + 2)][Board.nColonna(position.x) - 1] && dir != Direction.right)
            distances.set(2, calculateDistance(Board.nColonna(position.x) - 1, Board.nColonna(target.x), Board.nColonna(position.y + 2), Board.nColonna(target.y + 2)));

        if (!Board.board[Board.nRiga(position.y) + 1][Board.nColonna(position.x + 2)] && dir != Direction.up)
            distances.set(1, calculateDistance(Board.nColonna(position.x + 2), Board.nColonna(target.x + 2), Board.nColonna(position.y) + 1, Board.nColonna(target.y)));

        double minDistance = Collections.min(distances);
        int direction = distances.indexOf(minDistance);
//        right, down, left, up,
        dir = Direction.values()[direction];

    }

    private static double calculateDistance(int x1, int x2, int y1, int y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }


    public void scared(boolean scared) {
        this.scared = scared;

//        invert the direction: right, down, left, up,
        dir = Direction.values()[(dir.ordinal()+2)%4];



//        if (dir.equals("right")) {
//            dir = "left";
//        } else if (dir.equals("left")) {
//            dir = "right";
//        } else if (dir.equals("up")) {
//            dir = "down";
//        } else if (dir.equals("down")) {
//            dir = "up";
//        }

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


//    public void mangiato() {
//
//        norm();
//        position.x = startX;
//        position.y = startY;
//        this.dir = startDir;
//        this.eaten = false;
//
//    }


    public void draw(Graphics g){

        if(scared)
            setImage("scaredGhost");
        else if(eaten)
            setImage("eyes");
        else
            setImage(ghostName);

        super.draw(g);

    }


}

