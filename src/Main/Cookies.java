package Contenitore.Main;

import javax.swing.*;

public class Cookies extends JPanel {
    public int mPos[][];
    public boolean p[];
    int numero = 0;
    public boolean startArancione = false;
    public boolean vinto = false;
    boolean m[][] = {

            {true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true},
            {true, false, false, false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, true},
            {true, false, true, true, false, true, true, true, false, true, false, true, true, true, false, true, true, false, true},
            {true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true},
            {true, false, true, true, false, true, false, true, true, true, true, true, false, true, false, true, true, false, true},
            {true, false, false, false, false, true, false, false, false, true, false, false, false, true, false, false, false, false, true},
            {true, true, true, true, false, true, true, true, false, true, false, true, true, true, false, true, true, true, true},
            {true, true, true, true, false, true, false, false, false, false, false, false, false, true, false, true, true, true, true},
            {true, true, true, true, false, true, false, true, true, true, true, true, false, true, false, true, true, true, true},
            {false, false, false, false, false, false, false, true, true, true, true, true, false, false, false, false, false, false, false},
            {true, true, true, true, false, true, false, true, true, true, true, true, false, true, false, true, true, true, true},
            {true, true, true, true, false, true, false, false, false, false, false, false, false, true, false, true, true, true, true},
            {true, true, true, true, false, true, false, true, true, true, true, true, false, true, false, true, true, true, true},
            {true, false, false, false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, true},
            {true, false, true, true, false, true, true, true, false, true, false, true, true, true, false, true, true, false, true},
            {true, false, false, true, false, false, false, false, false, false, false, false, false, false, false, true, false, false, true},
            {true, true, false, true, false, true, false, true, true, true, true, true, false, true, false, true, false, true, true},
            {true, false, false, false, false, true, false, false, false, true, false, false, false, true, false, false, false, false, true},
            {true, false, true, true, true, true, true, true, false, true, false, true, true, true, true, true, true, false, true},
            {true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true},
            {true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true}

    };
    public int index = 0;

    public Cookies() {
        mPos = new int[(19 * 21 * 5)][2];
        p = new boolean[(19 * 21 * 5)];
        mappaPalline();
    }

    public void mappaPalline() {
        for (int i = 0; i < board1.R; i++) {
            for (int j = 0; j < board1.C; j++) {
                if (!m[i][j]) {
                    mPos[index][0] = 36 * j + 12;
                    mPos[index][1] = 36 * i + 12;
                    p[index] = true;
                    m[i][j] = true;
                    index++;
                    check(i, j);

                }
            }
        }

    }

    public void check(int i, int j) {
        // check sopra
        if (m[i - 1][j] == false) {
            mPos[index][0] = 36 * j + 12;
            mPos[index][1] = 36 * i;
            p[index] = true;
            index++;
        }
        // check sotto
        if (m[i + 1][j] == false) {
            mPos[index][0] = 36 * j + 12;
            mPos[index][1] = 36 * i + 30;
            p[index] = true;
            index++;
        }
        // check dx
        if ((j + 1) <= 18 && m[i][j + 1] == false) {
            mPos[index][0] = 36 * j + 30;
            mPos[index][1] = 36 * i + 12;
            p[index] = true;
            index++;
        }
        if (j - 1 >= 0 && m[i][j - 1] == false) {
            mPos[index][0] = 36 * j;
            mPos[index][1] = 36 * i + 12;
            p[index] = true;
            index++;
        }

    }

    public void mangia(int posx, int posy) {
        for (int i = 0; i <= index; i++) {
            if (p[i] && ((posx + 8) <= (mPos[i][0]) && (posx + 20) >= (mPos[i][0])) && ((posy + 5) <= (mPos[i][1]) && (posy + 20) >= (mPos[i][1]))) {
                p[i] = false;
                numero++;
            }
        }
        if (numero == (int) index / 3) {
            startArancione = true;
        }

        if (numero >= index) {
            vinto = true;
        }

    }

}
