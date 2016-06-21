package sk.umb.pacman.aktivneprvky;

import sk.umb.pacman.plocha.MapovyPrvok;
import sk.umb.pacman.plocha.PopisPrvkov;
import sk.umb.pacman.plocha.PrazdneMiesto;

public class Pacman extends Postava{

	public Pacman(int x, int y) {
		super(x, y);
		popis = PopisPrvkov.PACMAN;
	}

	public void pohybHore(MapovyPrvok[][] mapa) {
		if(xSur == 0 || xSur > mapa.length)
			return;
		if(ySur == 0 || ySur > mapa[1].length)
			return;

		if(mapa[xSur-1][ySur].getPopis() == PopisPrvkov.STENA.znak())
			return;
		else{
			mapa[xSur-1][ySur] = mapa[xSur][ySur];
			mapa[xSur][ySur] = new PrazdneMiesto(xSur, ySur);
			Hra.dotEaten();
			xSur -= 1;
		}
			
	}
	
	public void pohybDole(MapovyPrvok[][] mapa) {
		if(xSur == mapa.length-1)
			return;
		if(mapa[xSur+1][ySur].getPopis() == PopisPrvkov.STENA.znak())
			return;
		else{
			mapa[xSur+1][ySur] = mapa[xSur][ySur];
			mapa[xSur][ySur] = new PrazdneMiesto(xSur, ySur);
			Hra.dotEaten();

			xSur += 1;
		}
			
	}
	
	public void pohybVlavo(MapovyPrvok[][] mapa) {
		if(ySur == 0)
			return;
		if(mapa[xSur][ySur-1].getPopis() == PopisPrvkov.STENA.znak())
			return;
		else{
			mapa[xSur][ySur-1] = mapa[xSur][ySur];
			mapa[xSur][ySur] = new PrazdneMiesto(xSur, ySur);
			Hra.dotEaten();

			ySur -= 1;
		}
			
	}
	
	public void pohybVpravo(MapovyPrvok[][] mapa) {
		if(ySur == mapa[0].length-1)
			return;
		if(mapa[xSur][ySur+1].getPopis() == PopisPrvkov.STENA.znak())
			return;
		else{
			mapa[xSur][ySur+1] = mapa[xSur][ySur];
			mapa[xSur][ySur] = new PrazdneMiesto(xSur, ySur);
			Hra.dotEaten();

			ySur += 1;
		}
			
	}

}


