import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;

public class Board extends KeyAdapter implements ActionListener {

    public static final boolean[][] board = {{true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true},
            {true, false, false, false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, true},
            {true, false, true, true, false, true, true, true, false, true, false, true, true, true, false, true, true, false, true},
            {true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true},
            {true, false, true, true, false, true, false, true, true, true, true, true, false, true, false, true, true, false, true},
            {true, false, false, false, false, true, false, false, false, true, false, false, false, true, false, false, false, false, true},
            {true, true, true, true, false, true, true, true, false, true, false, true, true, true, false, true, true, true, true},
            {true, true, true, true, false, true, false, false, false, false, false, false, false, true, false, true, true, true, true},
            {true, true, true, true, false, true, false, true, true, true, true, true, false, true, false, true, true, true, true},
            {false, false, false, false, false, false, false, true, true, false, true, true, false, false, false, false, false, false, false},
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
            {true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true}};

    public final BoardUI boardUI;
    private final Timer timer = new Timer(30, this);
    private Apple apple = new Apple();
    private final Pacman pacman = new Pacman();

    private final Ghost redGhost = new RedGhost(pacman, 326, 252, Direction.right);
    private final OrangeGhost orangeGhost = new OrangeGhost(pacman, 358, 324, Direction.left, redGhost);
    private final ArrayList<Ghost> ghosts = new ArrayList<>(Arrays.asList(redGhost, orangeGhost, new BlueGhost(pacman, 290, 324, Direction.right, redGhost), new PinkGhost(pacman, 326, 324, Direction.up)));

    private Cookies cookies = new Cookies(orangeGhost);
    boolean game = false;
    private int milliSeconds = 0;
    private final JFrame frame;


    public Board(JFrame frame) {

        ArrayList<ComponentUI> component = new ArrayList<>(ghosts);
        component.add(pacman);

        boardUI = new BoardUI(component, cookies, apple);
        timer.setActionCommand("game in progress");
        this.frame = frame;

    }

    public static int numCell(int position) {
        return position / 36;
    }

    public static boolean checkAngle(int position) {
        return position % 36 == 0;
    }

    public int getCookiesEaten(){
        return cookies.eatenCookies;
    }

    public void resetBoard(String text){

        for (Ghost ghost : ghosts) {
            ghost.resetComponent();
            ghost.setActive(true);
        }
        orangeGhost.setActive(false);
        pacman.resetComponent();

        cookies = new Cookies(orangeGhost);
        apple = new Apple();

        boardUI.reset(cookies, apple);
        timer.setActionCommand("game in progress");

        // restart the board but with everything not moving
        timer.stop();
        game = false;

        boardUI.setText(text);
        milliSeconds = 0;

        boardUI.repaint();


    }

    @Override
    public void keyPressed(KeyEvent e) {


        if (!game) {
            String text = boardUI.text.contains("restart") ? "Press any key to start the Game" : "";
            resetBoard(text);

            if(text.equals("")) {
                timer.start();
                game = true;
            }
        }

        pacman.setDirection(e.getKeyCode());

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        milliSeconds += 30;
        pacman.move();

        if (cookies.eatCookies(pacman.position)) {
            timer.stop();
            game = false;
            boardUI.setText("You won! press any key to restart");
        }

        if (apple.checkMela(pacman.position))
            for (Ghost ghost : ghosts)
                ghost.scared();

        for (Ghost ghost : ghosts)
            ghost.moveGhost(milliSeconds, timer);

        if (timer.getActionCommand().equals("game ended")) {
            timer.stop();
            game = false;
            boardUI.setText("You lose! press any key to restart");
        }

//        boardUI.repaint();
        frame.repaint();

    }
}
