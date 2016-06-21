package sk.umb.pacman.aktivneprvky;

import sk.umb.pacman.plocha.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Logger;

public class Hra {
    static Logger logger = Logger.getLogger("sk.umb.pacman.aktivneprvky");
    private MapovyPrvok[][] mapa;
    private Pacman pacman;
    private static int numOfDots;
    private static int numOfMons;
    private ArrayList<Prisera> prisery;
    private static boolean showWalls;

    public static int getPocetZivotov() {
        return pocetZivotov;
    }

    private static int pocetZivotov ;
    public Hra() {
        try {
            pocetZivotov = 3;
            prisery = new ArrayList<Prisera>();
            nacitajMapu();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int getPocetPriser(){
        return numOfMons;
    }

    public MapovyPrvok[][] getMapa() {
        return this.mapa;
    }

    public boolean gameHasEnded() {

        if(pocetZivotov<=0)
            return true;

        if (numOfDots <= 0)
            return true;

        for (Prisera p : prisery) {
            if (p.getxSur() == pacman.getxSur() && p.getySur() == pacman.getySur()) {
                pocetZivotov--;
                mapa[pacman.getxSur()][pacman.getySur()] = new PrazdneMiesto(pacman.getxSur(),pacman.getySur());
                pacman.setxsur(12);
                pacman.setySur(14);
                mapa[pacman.getxSur()][pacman.getySur()] = pacman;

            }
        }



        return false;
    }

    private void nacitajMapu() throws IOException {
        Scanner s = null;
        int x, y;

        String str;
        try {
            URL url = ClassLoader.getSystemResource("level_big.dat");
            s = new Scanner(url.openStream());
            // velkost hracej plochy
            x = s.nextInt();
            y = s.nextInt();
            mapa = new MapovyPrvok[x][y];
            int i = -1;
            while (s.hasNext()) {
                i++;
                str = s.next();

                for (int j = 0; j < str.length(); j++) {

                    if (PopisPrvkov.BODKA.znak() == str.charAt(j)) {
                        mapa[i][j] = new Bodka(i, j);
                    } else if (PopisPrvkov.STENA.znak() == str.charAt(j)) {
                        mapa[i][j] = new Stena(i, j);
                        mapa[i][j].setPopis(PopisPrvkov.STENA);
                    } else if (PopisPrvkov.PACMAN.znak() == str.charAt(j)) {
                        this.pacman = new Pacman(i, j);
                        mapa[i][j] = this.pacman;
                    } else if (PopisPrvkov.PRISERA.znak() == str.charAt(j)) {
                        mapa[i][j] = new Prisera(i, j);
                        prisery.add((Prisera) mapa[i][j]);
                        numOfMons++;
                    }
                }

            }
        } finally {
            if (s != null)
                s.close();
        }
    }

    public void zobrazMapu() {
        for (int i = 0; i < mapa.length; i++) {
            for (int j = 0; j < mapa[0].length; j++) {
                System.out.print(mapa[i][j].getPopis());
            }
            System.out.println();
        }
    }

    public void pohybHore()  {
        pacman.pohybHore(mapa);
    }

    public void pohybDole() {
        pacman.pohybDole(mapa);
    }

    public void pohybVlavo() {
        pacman.pohybVlavo(mapa);
    }

    public void pohybVpravo() {
        pacman.pohybVpravo(mapa);
    }

    public Pacman getPacman() {
        return pacman;
    }

    public void setPacman(Pacman pacman) {
        this.pacman = pacman;
    }

    public void moveMonsters() {
        int rand = new Random().nextInt(4);

        for (Prisera p : prisery) {

            switch (rand) {
                case 0:
                    p.pohybDole(mapa);
                    break;
                case 1:
                    p.pohybHore(mapa);
                    break;
                case 2:
                    p.pohybVpravo(mapa);
                    break;
                case 3:
                    p.pohybVlavo(mapa);
                    break;
                default:
                    break;
            }

        }

    }

    // toto nie je najkrajsie riesenie ale najjednoduchsie
    public static void dotEaten() {
        numOfDots--;
    }

    public static int getNumOfDots() {
        return numOfDots;
    }

    public static void setNumOfDots(int i) {
        numOfDots = i;
    }

}


