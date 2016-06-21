package sk.umb.pacman.gui;

import sk.umb.pacman.aktivneprvky.Hra;
import sk.umb.pacman.plocha.PopisPrvkov;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Logger;

public class HerneOkno extends JPanel
        implements Runnable, KeyListener {

    private static final long serialVersionUID = 1L;
    Logger logger = Logger.getLogger("sk.umb.pacman.gui");

    private JTextField txt_score;
    private JTextField txt_monsters;
    private JTextField txt_zivoty;
    private Hra h;
    private Thread thread;
    private boolean running;
    private Direction direction;
    private TileMap tileMap;
    private boolean showWalls;

    public HerneOkno() {
        super();
        this.setLayout(null);
        showWalls = true;
        txt_score = new JTextField();
        txt_score.setSize(200, 50);
        txt_score.setLocation(500, 400);
        txt_score.setFont(new Font("SansSerif", Font.BOLD, 20));
        txt_score.setHorizontalAlignment(JTextField.CENTER);

        txt_zivoty = new JTextField();
        txt_zivoty.setSize(200, 50);
        txt_zivoty.setLocation(200, 400);
        txt_zivoty.setFont(new Font("SansSerif", Font.BOLD, 20));
        txt_zivoty.setHorizontalAlignment(JTextField.CENTER);

        txt_monsters = new JTextField();
        txt_monsters.setSize(200, 50);
        txt_monsters.setLocation(800, 400);
        txt_monsters.setFont(new Font("SansSerif", Font.BOLD, 20));
        txt_monsters.setHorizontalAlignment(JTextField.CENTER);

        this.add(txt_monsters);
        this.add(txt_zivoty);
        this.add(txt_score);
        this.h = new Hra();
        setPreferredSize(
                new Dimension(1200, 450));

        logger.info("Vytvaram: Herne okno s rozmermi: " + getSize());

        setFocusable(true);
        requestFocus();

        System.out.println("Inicializujem mapu");
        tileMap = new TileMap(h.getMapa());

    }

    public void keyTyped(KeyEvent key) {
    }

    public void keyReleased(KeyEvent key) {
    }

    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        switch (keyCode) {
            case KeyEvent.VK_UP:
                direction = Direction.UP;
                break;
            case KeyEvent.VK_DOWN:
                direction = Direction.DOWN;
                break;
            case KeyEvent.VK_LEFT:
                direction = Direction.LEFT;
                break;
            case KeyEvent.VK_RIGHT:
                direction = Direction.RIGHT;
                break;
            case KeyEvent.VK_SPACE:
                if(showWalls)
                    showWalls = false;
                else
                    showWalls = true;
                break;
        }

    }

    public void addNotify() {
        super.addNotify();
        if (thread == null) {
            thread = new Thread(this);
            addKeyListener(this);
            thread.start();
            logger.info("thread spustam");
        }
    }

    public void run() {

        running = true;
        direction = Direction.DOWN;

        while (running) {

            if (h.gameHasEnded()) {
                running = false;

                if (Hra.getNumOfDots() == 0) {
                    txt_score.setBackground(Color.green);
                    txt_score.setText("WINNER!");
                } else {
                    txt_score.setBackground(Color.red);
                    txt_score.setText("LOOSER!");
                }
                // aby sa dalej nekreslil pohyb po prehre
                return;
            } else {

                txt_score.setText("Dots left: " + Hra.getNumOfDots());
                txt_monsters.setText("Monsters: " + Hra.getPocetPriser());
                txt_zivoty.setText("Zivoty: " + Hra.getPocetZivotov());
                try {
                    switch (direction) {
                        case UP:
                            h.pohybHore();
                            break;
                        case DOWN:
                            h.pohybDole();
                            break;
                        case LEFT:
                            h.pohybVlavo();
                            break;
                        case RIGHT:
                            h.pohybVpravo();
                            break;
                    }
                    tileMap.updatePacmanDirection(direction);
                    h.moveMonsters();
                    repaint();
                    Thread.sleep(350);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        tileMap.update(h.getMapa());
        Tile[][] mapka = tileMap.getImgMap();

        for (int i = 0; i < mapka.length; i++)
            for (int j = 0; j < mapka[0].length; j++)
                if(mapka[i][j].getPopis() == PopisPrvkov.STENA){
                    if(!showWalls)
                        g.drawImage(mapka[i][j].getImage(), j * 40, i * 20, null);
                } else {
                    g.drawImage(mapka[i][j].getImage(), j * 40, i * 20, null);
                }
    }

}



