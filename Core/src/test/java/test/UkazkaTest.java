package test;

import org.junit.Test;
import sk.umb.pacman.aktivneprvky.Hra;


public class UkazkaTest {

    @Test
    public void testNacitaniaLevelu() {

        Hra h = new Hra();

        h.zobrazMapu();
        h.pohybHore();
        h.zobrazMapu();

    }
}
