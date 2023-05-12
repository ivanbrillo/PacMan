package Contenitore;

import java.util.Random;

public class Fantasmino {
    public int posx, posy;
    int iniziox, inizioy;
    public String dir = "right";
    String iniziodir;
    public Pacman pacman;
    public int spostamento = 2;
    public Fantasmino rosso;
    int target;
    public boolean scared = false;
    boolean mangiato = false;

    public Fantasmino(Pacman pacman, int posx, int posy, String dir, int target) {
        this.pacman = pacman;
        this.iniziox = posx;
        this.inizioy = posy;
        this.posx = posx; //326;
        this.posy = posy; //252;
        this.dir = dir;
        this.iniziodir = dir;
        this.target = target;
        rosso = this;
    }

    public Fantasmino(Pacman pacman, int posx, int posy, String dir, int target, Fantasmino rosso) {

        this(pacman, posx, posy, dir, target);
        this.rosso = rosso;
    }

    public void muovi(boolean scatter) {

        if (mangiato && board1.nColonna(posx) == 9 && board1.nColonna(posy) == 7) {
            mangiato = false;
        }


        if (board1.nRiga(posy) == 9 && (board1.nColonna(posx) == 17 || board1.nColonna(posx) == 18 || board1.nColonna(posx) == 19) && dir.equals("right")) {
            posx += spostamento;
            if (posx > 700) {
                posx = -30;
            }

            return;
        } else if (board1.nRiga(posy) == 9 && ((board1.nColonna(posx)) == -1 || board1.nColonna(posx) == 0) && dir.equals("left")) {
            posx -= spostamento;

            if (posx <= -30) {
                posx = 36 * 19 - 2;
            }

            return;
        }


        if (dir.equals("right") && board1.nColonna(this.posx) + 1 < board1.m[0].length && !board1.m[board1.nRiga(this.posy + 2)][board1.nColonna(this.posx) + 1]) {
            this.posx += spostamento;
        } else if (dir.equals("left") && board1.nColonna(this.posx) >= 0 && !board1.m[board1.nRiga(this.posy + 2)][board1.nColonna(this.posx)]) {
            this.posx -= spostamento;
        } else if (dir.equals("up") && ((!board1.m[board1.nRiga(this.posy)][board1.nColonna(this.posx + 2)]) || (board1.nRiga(this.posy) == 8 && board1.nColonna(this.posx + 2) == 9))) {
            this.posy -= spostamento;
        } else if (dir.equals("down") && !board1.m[board1.nRiga(this.posy) + 1][board1.nColonna(this.posx + 2)]) {
            this.posy += spostamento;
        }

        int nDir = 0;
        String consentite[] = new String[4];
        String dir2 = dir;
        if (board1.nColonna(this.posx) + 1 < board1.m[0].length && !board1.m[board1.nRiga(this.posy + 2)][board1.nColonna(this.posx) + 1] && board1.checkV(this.posy) && !dir.equals("left")) {
            consentite[nDir] = "right";
            dir2 = "right";
            nDir++;
            //	System.out.println(1);

        }// CAMBIATO ORA  34
        if (board1.nColonna(this.posx) >= 1 && !board1.m[board1.nRiga(this.posy + 2)][board1.nColonna(this.posx) - 1] && board1.checkV(this.posy) && !dir.equals("right")) {
            consentite[nDir] = "left";

            nDir++;
            dir2 = "left";
            //	System.out.println(2);

        } // CAMBIATO ORA
        if ((!board1.m[board1.nRiga(this.posy) - 1][board1.nColonna(this.posx + 2)] && board1.checkV(this.posx) && !dir.equals("down")) || (board1.nRiga(this.posy) == 9 && board1.nColonna(this.posx + 2) == 9 && board1.checkV(this.posx))) {
            consentite[nDir] = "up";
            nDir++;
            dir2 = "up";
            //			System.out.println(board1.nRiga(this.posy-2)+" "+board1.nColonna(this.posx+2));
            //			System.out.println(3);
        }
        if (!board1.m[board1.nRiga(this.posy) + 1][board1.nColonna(this.posx + 2)] && board1.checkV(this.posx) && !dir.equals("up")) {
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
                if (!(board1.nRiga(this.posy + 2) == 9 && board1.nColonna(this.posx) > 16) && !scared && !scatter) {
                    if (target == 0) {
                        dir = scegli(pacman.posx, pacman.posy);
                    } else if (target == 4) {
                        rosa();
                    } else if (target == 2) {
                        blue();
                    } else if (target == 8) {
                        arancione();
                    }
                }

                if (!(board1.nRiga(this.posy + 2) == 9 && board1.nColonna(this.posx) > 16) && scared && !scatter) {
                    Random generatore = new Random();
                    dir = consentite[generatore.nextInt(nDir)];
                }

            } else {

                dir = scegli(iniziox, inizioy);

            }
        }
    }

    public String scegli(int posx, int posy) {
        double dist1 = 10000, dist2 = 10000, dist3 = 10000, dist4 = 10000;
        String dir2 = "right";
        if (!board1.m[board1.nRiga(this.posy + 2)][board1.nColonna(this.posx) + 1] && !dir.equals("left")) {
            dist1 = Math.sqrt(Math.abs(((board1.nColonna(this.posx) + 1) - board1.nColonna(posx)) * (((board1.nColonna(this.posx) + 1) - board1.nColonna(posx)))) + Math.abs(((board1.nColonna(this.posy + 2)) - board1.nColonna(posy + 2)) * (((board1.nColonna(this.posy + 2)) - board1.nColonna(posy + 2)))));
        }
        if (board1.nColonna(this.posx) >= 1 && !board1.m[board1.nRiga(this.posy + 2)][board1.nColonna(this.posx) - 1] && !dir.equals("right")) {
            dist2 = Math.sqrt(Math.abs(((board1.nColonna(this.posx) - 1) - board1.nColonna(posx)) * (((board1.nColonna(this.posx) - 1) - board1.nColonna(posx)))) + Math.abs(((board1.nColonna(this.posy + 2)) - board1.nColonna(posy + 2)) * (((board1.nColonna(this.posy + 2)) - board1.nColonna(posy + 2)))));

        }

        if (!board1.m[board1.nRiga(this.posy) - 1][board1.nColonna(this.posx + 2)] && !dir.equals("down")) {
            dist3 = Math.sqrt(Math.abs(((board1.nColonna(this.posx + 2)) - board1.nColonna(posx + 2)) * (((board1.nColonna(this.posx + 2)) - board1.nColonna(posx + 2)))) + Math.abs(((board1.nColonna(this.posy) - 1) - board1.nColonna(posy)) * (((board1.nColonna(this.posy) - 1) - board1.nColonna(posy)))));

        }
        if (!board1.m[board1.nRiga(this.posy) + 1][board1.nColonna(this.posx + 2)] && !dir.equals("up")) {
            dist4 = Math.sqrt(Math.abs(((board1.nColonna(this.posx + 2)) - board1.nColonna(posx + 2)) * (((board1.nColonna(this.posx + 2)) - board1.nColonna(posx + 2)))) + Math.abs(((board1.nColonna(this.posy) + 1) - board1.nColonna(posy)) * (((board1.nColonna(this.posy) + 1) - board1.nColonna(posy)))));

            //		System.out.println(board1.nRiga(this.posy)+1);
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

        if (pacman.dir.equals("up")) {

            if (pacman.posy >= 144 && pacman.posx >= 144) {
                dir = scegli(pacman.posx - 144, pacman.posy - 144);
            } else if (pacman.posy >= 144 && pacman.posx < 144) {
                dir = scegli(0, pacman.posy - 144);
            } else if (pacman.posy < 144 && pacman.posx < 144) {
                dir = scegli(0, 0);
            } else if (pacman.posy < 144 && pacman.posx >= 144) {
                dir = scegli(pacman.posx - 144, 0);
            }
        } else if (pacman.dir.equals("down")) {

            if (pacman.posy < 612) {
                dir = scegli(pacman.posx, pacman.posy + 144);
            } else {
                dir = scegli(pacman.posx, 754);
            }
        } else if (pacman.dir.equals("right")) {

            if (pacman.posx < 540) {
                dir = scegli(pacman.posx + 144, pacman.posy);
            } else {
                dir = scegli(682, pacman.posy);
            }
        } else if (pacman.dir.equals("left")) {

            if (pacman.posx >= 144) {
                dir = scegli(pacman.posx - 144, pacman.posy);
            } else {
                dir = scegli(0, pacman.posy);
            }
        } else {
            dir = scegli(pacman.posx, pacman.posy);
        }


    }


    public void blue() {

        int x = 0, y = 0;

        if (pacman.dir.equals("up")) {

            if (pacman.posy >= 72 && pacman.posx >= 72) {
                y = pacman.posx - 72;
                x = pacman.posx - 72;
            } else if (pacman.posy >= 72 && pacman.posx < 72) {
                x = 0;
                y = pacman.posy - 72;
            } else if (pacman.posy < 72 && pacman.posx < 72) {
                x = 0;
                y = 0;
            } else if (pacman.posy < 72 && pacman.posx >= 72) {
                x = pacman.posx - 72;
                y = 0;
            }
        } else if (pacman.dir.equals("down")) {

            if (pacman.posy < 684) {
                x = pacman.posx;
                y = pacman.posy - 72;
            } else {
                x = pacman.posx;
                y = pacman.posy - 754;
            }

        } else if (pacman.dir.equals("right")) {

            if (pacman.posx < 612) {
                x = pacman.posx + 72;
                y = pacman.posy;
            } else {
                x = 682;
                y = pacman.posy;
            }
        } else if (pacman.dir.equals("left")) {

            if (pacman.posx >= 72) {
                y = pacman.posx - 72;
                y = pacman.posy;
            } else {
                x = 0;
                y = pacman.posy;
            }
        } else {
            x = pacman.posx;
            y = pacman.posy;
        }

        x = x + (x - rosso.posx);
        y = y + (y - rosso.posy);

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

        double dist = Math.sqrt((posx - pacman.posx) * (posx - pacman.posx) + (posy - pacman.posy) * (posy - pacman.posy));
        if (dist > 36 * target) {
            dir = scegli(pacman.posx, pacman.posy);
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
        if (posx % 2 != 0) {
            posx++;
        }
        if (posy % 2 != 0) {
            posy++;
        }

        spostamento = 2;
    }


    public void mangiato() {

        norm();
        this.posx = iniziox;
        this.posy = inizioy;
        this.dir = iniziodir;
        this.mangiato = false;


    }

}

