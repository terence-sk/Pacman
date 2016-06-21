package sk.umb.pacman.gui;
import sk.umb.pacman.aktivneprvky.Hra;
import sk.umb.pacman.plocha.*;

import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;

public class TileMap {

    private MapovyPrvok[][] charMap;
    private Tile[][] imgMap;

    private BufferedImage wall;
    private BufferedImage dot;
    private BufferedImage ghost;
    private BufferedImage pacman;
    private BufferedImage empty;

    // left right up down
    private BufferedImage l_pac;
    private BufferedImage r_pac;
    private BufferedImage u_pac;
    private BufferedImage d_pac;

    public TileMap(MapovyPrvok[][] charMap) {
        this.charMap = charMap;
        imgMap = new Tile[charMap.length][charMap[0].length];
        try {

            dot = ImageIO.read(
                    Thread.currentThread().getContextClassLoader().
                            getResourceAsStream("dot.png"));
            wall = ImageIO.read(
                    Thread.currentThread().getContextClassLoader().
                            getResourceAsStream("wall.png"));
            ghost = ImageIO.read(
                    Thread.currentThread().getContextClassLoader().
                            getResourceAsStream("ghost.png"));
            r_pac = ImageIO.read(
                    Thread.currentThread().getContextClassLoader().
                            getResourceAsStream("pacman_right.png"));
            l_pac = ImageIO.read(
                    Thread.currentThread().getContextClassLoader().
                            getResourceAsStream("pacman_left.png"));
            u_pac = ImageIO.read(
                    Thread.currentThread().getContextClassLoader().
                            getResourceAsStream("pacman_up.png"));
            d_pac = ImageIO.read(
                    Thread.currentThread().getContextClassLoader().
                            getResourceAsStream("pacman_down.png"));
            empty = ImageIO.read(
                    Thread.currentThread().getContextClassLoader().
                            getResourceAsStream("prazdne.png"));
            pacman = r_pac;
            convertToTileMap();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update(MapovyPrvok[][] charMap) {
        this.charMap = charMap;
        try {
            convertToTileMap();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
    Z nacitanej mapy v textovej podobe spravi obrazky
    */
    private void convertToTileMap() throws IOException {
        int numDots = 0;
        for (int i = 0; i < charMap.length; i++) {
            for (int j = 0; j < charMap[0].length; j++) {

                if (PopisPrvkov.BODKA.znak() == charMap[i][j].getPopis()) {
                    imgMap[i][j] = new Tile(dot, Tile.NORMAL);
                    numDots++;
                } else if (PopisPrvkov.STENA.znak() == charMap[i][j].getPopis()) {
                    imgMap[i][j] = new Tile(wall, Tile.BLOCKED);
                    imgMap[i][j].setPopis(PopisPrvkov.STENA);
                }else if (PopisPrvkov.PACMAN.znak() == charMap[i][j].getPopis()) {
                    imgMap[i][j] = new Tile(pacman, Tile.NORMAL);
                } else if (PopisPrvkov.PRISERA.znak() == charMap[i][j].getPopis()) {
                    imgMap[i][j] = new Tile(ghost, Tile.NORMAL);
                } else if (PopisPrvkov.PRAZDNE.znak() == charMap[i][j].getPopis()) {
                    imgMap[i][j] = new Tile(empty, Tile.NORMAL);
                }
            }
        }
        Hra.setNumOfDots(numDots);

    }

    public void updatePacmanDirection(Direction d){
        switch (d) {
            case DOWN:
                pacman = d_pac;
                break;
            case UP:
                pacman = u_pac;
                break;
            case RIGHT:
                pacman = r_pac;
                break;
            case LEFT:
                pacman = l_pac;
                break;
        }
    }

    public Tile[][] getImgMap() {
        return imgMap;
    }

}


















