package sk.umb.pacman.aktivneprvky;

import sk.umb.pacman.plocha.MapovyPrvok;
import sk.umb.pacman.plocha.PopisPrvkov;

public class Prisera extends Postava {

	public Prisera(int x, int y) {
		super(x, y);
		popis = PopisPrvkov.PRISERA;
	}

	public void pohybHore(MapovyPrvok[][] mapa) {
		if(xSur == 0 || xSur > mapa.length)
			return;
		if(ySur == 0 || ySur > mapa[1].length)
			return;

		if(mapa[xSur-1][ySur].getPopis() == PopisPrvkov.STENA.znak())
			return;
		else if(mapa[xSur-1][ySur].getPopis() == PopisPrvkov.PRISERA.znak()){
			return;
		}
		else {
			MapovyPrvok mp = mapa[xSur - 1][ySur];
			mapa[xSur - 1][ySur] = mapa[xSur][ySur];
			mapa[xSur][ySur] = mp;

			xSur -= 1;
		}

	}

	public void pohybDole(MapovyPrvok[][] mapa) {
		if(xSur == mapa.length-1)
			return;
		if(mapa[xSur+1][ySur].getPopis() == PopisPrvkov.STENA.znak())
			return;
		else if(mapa[xSur+1][ySur].getPopis() == PopisPrvkov.PRISERA.znak()){
			return;
		}
		else{
			MapovyPrvok mp = mapa[xSur + 1][ySur];
			mapa[xSur+1][ySur] = mapa[xSur][ySur];
			mapa[xSur][ySur] = mp;
			xSur += 1;
		}

	}

	public void pohybVlavo(MapovyPrvok[][] mapa) {
		if(ySur == 0)
			return;
		if(mapa[xSur][ySur-1].getPopis() == PopisPrvkov.STENA.znak())
			return;
		else if(mapa[xSur][ySur-1].getPopis() == PopisPrvkov.PRISERA.znak()){
			return;
		}
		else{
			MapovyPrvok mp = mapa[xSur][ySur-1];
			mapa[xSur][ySur-1] = mapa[xSur][ySur];
			mapa[xSur][ySur] = mp;
			ySur -= 1;
		}

	}

	public void pohybVpravo(MapovyPrvok[][] mapa) {
		if(ySur == mapa[0].length-1)
			return;
		if(mapa[xSur][ySur+1].getPopis() == PopisPrvkov.STENA.znak())
			return;
		else if(mapa[xSur][ySur+1].getPopis() == PopisPrvkov.PRISERA.znak()){
			return;
		}
		else{
			MapovyPrvok mp = mapa[xSur][ySur+1];
			mapa[xSur][ySur+1] = mapa[xSur][ySur];
			mapa[xSur][ySur] = mp;

			ySur += 1;
		}

	}

}

