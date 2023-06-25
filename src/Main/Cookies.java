package Contenitore.Main;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Cookies extends JPanel {
    private final boolean[][] cookies = new boolean[21][19];

    public boolean startArancione = false;
    public boolean win = false;
    Map<int[], Boolean> cookiesMap = new HashMap<>();
    private final int totalCookies;
    private int eatenCookies = 0;


    public Cookies() {

        for (int i = 0; i < Board.board.length; i++) {
            cookies[i] = new boolean[Board.board[0].length];
            System.arraycopy(Board.board[i], 0, cookies[i], 0, Board.board[i].length);
        }

        cookies[9][9] = true;   // ghost home, without cookie
        mappaPalline();
        totalCookies = cookiesMap.size();

    }

    private void mappaPalline() {
        for (int i = 0; i < 21; i++)
            for (int j = 0; j < 19; j++)
                if (!cookies[i][j]) {
                    cookies[i][j] = true;
                    addCookies(i, j);
                }
    }


    public void addCookies(int i, int j) {

        //center
        cookiesMap.put(new int[]{36 * j + 12, 36 * i + 12}, true);

        // above
        if (!cookies[i - 1][j])
            cookiesMap.put(new int[]{36 * j + 12, 36 * i}, true);

        // under
        if (!cookies[i + 1][j])
            cookiesMap.put(new int[]{36 * j + 12, 36 * i + 30}, true);

        // right
        if ((j + 1) <= 18 && !cookies[i][j + 1])
            cookiesMap.put(new int[]{36 * j + 30, 36 * i + 12}, true);

        // left
        if (j - 1 >= 0 && !cookies[i][j - 1])
            cookiesMap.put(new int[]{36 * j, 36 * i + 12}, true);

    }

    public void eatCookies(int posx, int posy) {

        for (Map.Entry<int[], Boolean> entry : cookiesMap.entrySet()) {

            int[] position = entry.getKey();
            boolean notEaten = entry.getValue();

            if ( notEaten && ((posx + 8) <= (position[0]) && (posx + 20) >= (position[0])) && ((posy + 5) <= (position[1]) && (posy + 20) >= (position[1]))) {
                cookiesMap.replace(position, false);
                eatenCookies++;
            }
        }


        if (eatenCookies == totalCookies / 3) {
            startArancione = true;
        }

        if (eatenCookies == totalCookies) {
            win = true;
        }

    }


    public void draw(Graphics g) {

        g.setColor(Color.YELLOW);

        for (Map.Entry<int[], Boolean> entry : cookiesMap.entrySet()) {

            int[] position = entry.getKey();
            boolean notEaten = entry.getValue();

            if(notEaten)
                g.fillOval((position[0] + 1), (position[1] + 1), 8, 8);

        }
    }

}
