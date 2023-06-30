package Contenitore.Main;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Cookies extends JPanel {
    private final boolean[][] cookies = new boolean[21][19];
    private final int totalCookies;
    private final Map<int[], Boolean> cookiesMap = new HashMap<>();   // position x,y + boolean not eaten
    private int eatenCookies = 0;
    private final OrangeGhost orangeGhost;

    public Cookies(OrangeGhost orangeGhost) {

        for (int i = 0; i < Board.board.length; i++) {
            cookies[i] = new boolean[Board.board[0].length];
            System.arraycopy(Board.board[i], 0, cookies[i], 0, Board.board[i].length);
        }

        cookies[9][9] = true;   // ghost home, without cookie
        cookiesGenerator();
        totalCookies = cookiesMap.size();
        this.orangeGhost = orangeGhost;

    }

    private void cookiesGenerator() {
        for (int i = 0; i < 21; i++)
            for (int j = 0; j < 19; j++)
                if (!cookies[i][j]) addCookies(i, j);

    }


    public void addCookies(int i, int j) {

        // avoid adding the border cookies on both iterations of continuous free blocks
        cookies[i][j] = true;

        //center
        cookiesMap.put(new int[]{36 * j + 12, 36 * i + 12}, true);

        // above
        if (!cookies[i - 1][j]) cookiesMap.put(new int[]{36 * j + 12, 36 * i}, true);

        // under
        if (!cookies[i + 1][j]) cookiesMap.put(new int[]{36 * j + 12, 36 * i + 30}, true);

        // right
        if ((j + 1) <= 18 && !cookies[i][j + 1]) cookiesMap.put(new int[]{36 * j + 30, 36 * i + 12}, true);

        // left
        if (j - 1 >= 0 && !cookies[i][j - 1]) cookiesMap.put(new int[]{36 * j, 36 * i + 12}, true);

    }

    /**
     * @return true, if the player wins the game (eat all the cookies)
     */
    public boolean eatCookies(Point pacmanPosition) {

        for (Map.Entry<int[], Boolean> entry : cookiesMap.entrySet()) {

            int[] position = entry.getKey();
            boolean notEaten = entry.getValue();

            if (notEaten && ((pacmanPosition.x + 8) <= position[0] && (pacmanPosition.x + 20) >= position[0]) && ((pacmanPosition.y + 5) <= position[1] && (pacmanPosition.y + 20) >= position[1])) {
                cookiesMap.replace(position, false);
                eatenCookies++;
            }
        }

        if (eatenCookies == totalCookies / 3)
            orangeGhost.setActive();

        return eatenCookies == totalCookies;

    }


    public void draw(Graphics g) {

        g.setColor(Color.YELLOW);

        for (Map.Entry<int[], Boolean> entry : cookiesMap.entrySet()) {

            int[] position = entry.getKey();
            boolean notEaten = entry.getValue();

            if (notEaten) g.fillOval((position[0] + 1), (position[1] + 1), 8, 8);

        }
    }

}
