package sk.umb.pacman.gui;

import sk.umb.pacman.plocha.PopisPrvkov;

import java.awt.image.BufferedImage;

public class Tile {

    private BufferedImage image;
    private int type;

    public PopisPrvkov getPopis() {
        return popis;
    }

    public void setPopis(PopisPrvkov popis) {
        this.popis = popis;
    }

    private PopisPrvkov popis;
    public static final int NORMAL = 0;
    public static final int BLOCKED = 1;

    public Tile(BufferedImage image, int type) {
        this.image = image;
        this.type = type;
    }

    public BufferedImage getImage() { return image; }
    public void setImage(BufferedImage bi) { this.image = bi; }
    public int getType() { return type; }

}