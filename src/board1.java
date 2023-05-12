package Contenitore;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class board1 extends JPanel implements ActionListener, KeyListener {
    Timer timer = new Timer(15, this);
    private Image sfondo, pacman1, pacman2, pacman3, pacman4, fantasmarosso1, fantasmarosa1, fantasmablue1, fantasmaarancione, fantasmascared, chiuso, gameover, eyes, vinto;
    boolean start = false;
    boolean trans = false;
    double secondi = 0;
    int tempo_mela = 0;
    String path = "./foto";
    int colonna = 36;
    int riga = 36;
    boolean game = true;
    Palline palline;
    Pacman pacman;
    public Fantasmino rosso, rosa, blue, arancione;
    int index = 0;
    static final int R = 21;
    static final int C = 19;
    static int numero = 1;
    static boolean m[][] = {
            {true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true},
            {true, false, false, false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, true},
            {true, false, true, true, false, true, true, true, false, true, false, true, true, true, false, true, true, false, true},
            {true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true},
            {true, false, true, true, false, true, false, true, true, true, true, true, false, true, false, true, true, false, true},
            {true, false, false, false, false, true, false, false, false, true, false, false, false, true, false, false, false, false, true},
            {true, true, true, true, false, true, true, true, false, true, false, true, true, true, false, true, true, true, true},
            {false, false, false, true, false, true, false, false, false, false, false, false, false, true, false, true, false, false, false},
            {true, true, true, true, false, true, false, true, true, true, true, true, false, true, false, true, true, true, true},
            {false, false, false, false, false, false, false, true, true, false, true, true, false, false, false, false, false, false, false},
            {true, true, true, true, false, true, false, true, true, true, true, true, false, true, false, true, true, true, true},
            {false, false, false, true, false, true, false, false, false, false, false, false, false, true, false, true, false, false, false},
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

    public static int posMele[][] = {    // nriga - ncolonna
            {2, 1},
            {15, 1},
            {15, 17},
            {2, 17},
    };

    public board1() {
        sfondo = Toolkit.getDefaultToolkit().createImage(path + "\\board.png");
        loadImage(sfondo);
        pacman1 = Toolkit.getDefaultToolkit().createImage(path + "\\pacman1.png");
        loadImage(pacman1);
        pacman2 = Toolkit.getDefaultToolkit().createImage(path + "\\pacman2.png");
        loadImage(pacman2);
        pacman3 = Toolkit.getDefaultToolkit().createImage(path + "\\pacman3.png");
        loadImage(pacman3);
        pacman4 = Toolkit.getDefaultToolkit().createImage(path + "\\pacman4.png");
        loadImage(pacman4);
        fantasmarosso1 = Toolkit.getDefaultToolkit().createImage(path + "\\rosso.png");
        loadImage(fantasmarosso1);
        fantasmablue1 = Toolkit.getDefaultToolkit().createImage(path + "\\blue.png");
        loadImage(fantasmablue1);
        fantasmaarancione = Toolkit.getDefaultToolkit().createImage(path + "\\arancione.png");
        loadImage(fantasmaarancione);
        chiuso = Toolkit.getDefaultToolkit().createImage(path + "\\chiuso.png");
        loadImage(chiuso);
        gameover = Toolkit.getDefaultToolkit().createImage(path + "\\gameover2.png");
        loadImage(gameover);
        vinto = Toolkit.getDefaultToolkit().createImage(path + "\\vinto.png");
        loadImage(vinto);
        fantasmarosa1 = Toolkit.getDefaultToolkit().createImage(path + "\\rosa.png");
        loadImage(fantasmarosa1);
        fantasmascared = Toolkit.getDefaultToolkit().createImage(path + "\\scared.png");
        loadImage(fantasmascared);
        eyes = Toolkit.getDefaultToolkit().createImage(path + "\\eyes.png");
        loadImage(eyes);


        pacman = new Pacman();
        palline = new Palline();
        rosso = new Fantasmino(pacman, 326, 252, "right", 0);
        rosa = new Fantasmino(pacman, 326, 324, "up", 4);
        blue = new Fantasmino(pacman, 290, 324, "right", 2, rosso);
        arancione = new Fantasmino(pacman, 358, 324, "left", 8, rosso);

//        System.out.println(board1.nColonna(362));


        timer.start();


    }

    public static int nColonna(int posx) {
        return (int) posx / 36;
    }

    public static int nRiga(int posy) {
        return (int) posy / 36;
    }

    public static boolean checkV(int posx) {
        boolean angolo = false;
        if (posx % 36 == 0) {
            angolo = true;
        }
        return angolo;
    }

    public static boolean checkO(int posy) {
        boolean angolo = false;
        if (posy % 36 == 0) {
            angolo = true;
        }
        return angolo;
    }

    public void paintComponent(Graphics g) {
        setOpaque(false);
        if (game) {
            g.drawImage(sfondo, 0, 0, null);
            if (secondi % 180 > 60) {
                if (numero == 1) {
                    g.drawImage(pacman1, pacman.posx + 2, pacman.posy + 1, null);
                } else if (numero == 2) {
                    g.drawImage(pacman2, pacman.posx + 2, pacman.posy + 1, null);
                } else if (numero == 3) {
                    g.drawImage(pacman3, pacman.posx + 2, pacman.posy + 1, null);
                } else if (numero == 4) {
                    g.drawImage(pacman4, pacman.posx + 2, pacman.posy + 1, null);
                }
            } else {
                g.drawImage(chiuso, pacman.posx + 2, pacman.posy + 1, null);
            }


            //		for(int i = 0; i<21; i++) {
            //			g.drawLine(colonna*i, 0, colonna*i, 800);
            //			g.drawLine(0, riga*i, 700, riga*i);
            //		}

            g.setColor(Color.YELLOW);
            for (int i = 0; i < (19 * 21 * 5); i++) {
                if (palline.p[i]) {
                    g.fillOval((palline.mPos[i][0] + 1), (palline.mPos[i][1] + 1), 8, 8);
                }
            }

            // palline
            for (int i = 0; i < posMele.length; i++) {
                g.fillOval(posMele[i][1] * 36 + 8, posMele[i][0] * 36 + 8, 16, 16);
            }

            if (!rosso.mangiato) {
                if (!rosso.scared) {
                    g.drawImage(fantasmarosso1, rosso.posx + 2, rosso.posy + 1, null);
                } else {
                    if (tempo_mela <= 6000 || (tempo_mela > 6000 && tempo_mela < 8000 && tempo_mela % 180 > 30)) {
                        g.drawImage(fantasmascared, rosso.posx + 2, rosso.posy + 1, null);
                    } else {
                        g.drawImage(fantasmarosso1, rosso.posx + 2, rosso.posy + 1, null);
                    }
                }
            } else {
                g.drawImage(eyes, rosso.posx + 2, rosso.posy + 1, null);
            }

            if (!rosa.mangiato) {
                if (!rosa.scared) {
                    g.drawImage(fantasmarosa1, rosa.posx + 2, rosa.posy + 1, null);
                } else {
                    if (tempo_mela <= 6000 || (tempo_mela > 6000 && tempo_mela < 8000 && tempo_mela % 180 > 30)) {
                        g.drawImage(fantasmascared, rosa.posx + 2, rosa.posy + 1, null);
                    } else {
                        g.drawImage(fantasmarosa1, rosa.posx + 2, rosa.posy + 1, null);
                    }
                }
            } else {
                g.drawImage(eyes, rosa.posx + 2, rosa.posy + 1, null);
            }

            if (!arancione.mangiato) {
                if (!arancione.scared) {
                    g.drawImage(fantasmaarancione, arancione.posx + 2, arancione.posy + 1, null);
                } else {
                    if (tempo_mela <= 6000 && palline.startArancione || (tempo_mela > 6000 && tempo_mela < 8000 && tempo_mela % 180 > 30 && palline.startArancione)) {
                        g.drawImage(fantasmascared, arancione.posx + 2, arancione.posy + 1, null);
                    } else {
                        g.drawImage(fantasmaarancione, arancione.posx + 2, arancione.posy + 1, null);
                    }
                }
            } else {
                g.drawImage(eyes, arancione.posx + 2, arancione.posy + 1, null);
            }

            if (!blue.mangiato) {

                if (!blue.scared) {
                    g.drawImage(fantasmablue1, blue.posx + 2, blue.posy + 1, null);
                } else {
                    if (tempo_mela <= 6000 || (tempo_mela > 6000 && tempo_mela < 8000 && tempo_mela % 180 > 30)) {
                        g.drawImage(fantasmascared, blue.posx + 2, blue.posy + 1, null);
                    } else {
                        g.drawImage(fantasmablue1, blue.posx + 2, blue.posy + 1, null);
                    }
                }
            } else {
                g.drawImage(eyes, blue.posx + 2, blue.posy + 1, null);
            }

        } else {
            if (!palline.vinto) {
                g.drawImage(gameover, 0, 0, null);
            } else {
                g.drawImage(vinto, 0, 0, null);
            }

        }

        super.paintComponent(g);
    }

    private void loadImage(Image img) {

        try {
            MediaTracker track = new MediaTracker(this);
            track.addImage(img, 0);
            track.waitForID(0);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub

        if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP) {
            pacman.dir = "up";
        } else if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN) {
            pacman.dir = "down";
        } else if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) {
            pacman.dir = "left";
        } else if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) {
            pacman.dir = "right";
        }

        //	if (e.getKeyCode() == KeyEvent.VK_C) {
        //		setCpuMode = true;
        //	}
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (game == false) {
                game = true;
                rosso.mangiato();
                rosa.mangiato();
                arancione.mangiato();
                blue.mangiato();
                pacman = new Pacman();
                palline = new Palline();
                tempo_mela = 0;
                posMele = new int[][]{    // nriga - ncolonna
                        {2, 1},
                        {15, 1},
                        {15, 17},
                        {2, 17},
                };

                secondi = 0;
                timer.start();
            }
        }

    }

    public static void main(String[] args) {
        GraphicsEnvironment graphics = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device = graphics.getDefaultScreenDevice();
        JFrame f = new JFrame();
        board1 b = new board1();
        f.add(b);
        int larghezza = 696;
        int altezza = 792;
        f.setSize(larghezza, altezza);
        //		f.addMouseMotionListener(b);
        //		f.addMouseListener(b);
        f.addKeyListener(b);
        f.getContentPane().add(b);

        f.setVisible(true);
        f.setResizable(true);
        f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);

    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        secondi += 15;
        if (secondi % 30 == 0) {
            tempo_mela += 30;
            pacman.muovi();
            if (tempo_mela >= 8000) {
                rosso.norm();
                rosa.norm();
                blue.norm();
                arancione.norm();

            }

            checkMela();
            palline.mangia(pacman.posx, pacman.posy);

            if (secondi <= 5000) {
                rosso.muovi(true);
                rosa.muovi(true);
                blue.muovi(true);
//              arancione.muovi(true);
            } else {
                rosso.muovi(false);
                rosa.muovi(false);
                blue.muovi(false);

                if (palline.startArancione) {
                    arancione.muovi(false);
                }

            }


            Rectangle pacmanR = new Rectangle(pacman.posx + 10, pacman.posy + 10, 10, 10);
            Rectangle rossoR = new Rectangle(rosso.posx + 10, rosso.posy + 10, 10, 10);
            Rectangle rosaR = new Rectangle(rosa.posx + 10, rosa.posy + 10, 10, 10);
            Rectangle blueR = new Rectangle(blue.posx + 10, blue.posy + 10, 10, 10);
            Rectangle arancioneR = new Rectangle(arancione.posx + 10, arancione.posy + 10, 10, 10);

            if ((!rosso.scared && pacmanR.intersects(rossoR) && !rosso.mangiato) || (!rosa.scared && pacmanR.intersects(rosaR) && !rosa.mangiato) || (!blue.scared && pacmanR.intersects(blueR) && !blue.mangiato) || (!arancione.scared && pacmanR.intersects(arancioneR) && arancione.mangiato)) {
                timer.stop();
                game = false;
            } else {

                if (rosso.scared && pacmanR.intersects(rossoR)) {
//                rosso.mangiato();
                    rosso.mangiato = true;
                    rosso.norm();
                }

                if (arancione.scared && pacmanR.intersects(arancioneR)) {
                    arancione.mangiato = true;
                    arancione.norm();

//                arancione.mangiato();
                }

                if (blue.scared && pacmanR.intersects(blueR)) {
//                blue.mangiato();
                    blue.mangiato = true;
                    blue.norm();

                }


                if (rosa.scared && pacmanR.intersects(rosaR)) {
//                rosa.mangiato();
                    rosa.mangiato = true;
                    rosa.norm();

                }

            }
        } else {
            if (rosso.mangiato) {
                rosso.muovi(false);
            }
            if (blue.mangiato) {
                blue.muovi(false);
            }
            if (arancione.mangiato) {
                arancione.muovi(false);
            }
            if (rosa.mangiato) {
                rosa.muovi(false);
            }
        }

        if (palline.vinto) {
            timer.stop();
            game = false;
        }


        repaint();


    }

    public void checkMela() {
        for (int i = 0; i < board1.posMele.length; i++)
            if (Math.abs(board1.posMele[i][1] * 36 - pacman.posx) < 5 && Math.abs(board1.posMele[i][0] * 36 - pacman.posy) < 5) {
                posMele[i][0] = -1;
                posMele[i][1] = -1;

                rosso.scared(true);
                rosa.scared(true);
                blue.scared(true);
                arancione.scared(true);

                tempo_mela = 0;
            }


    }
}


