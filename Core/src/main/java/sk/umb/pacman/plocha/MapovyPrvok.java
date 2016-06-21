package sk.umb.pacman.plocha;

public abstract class MapovyPrvok {

	protected int xSur;
	protected int ySur;
	protected PopisPrvkov popis;

	protected MapovyPrvok(int x, int y){
		xSur = x;
		ySur = y;
	}

	public int getxSur() {
		return xSur;
	}

	public int getySur() {
		return ySur;
	}

	public void setySur(int y){
		ySur = y;
	}

	public void setxsur(int x){
		xSur = x;
	}

	public char getPopis() {
		return popis.znak();
	}

	public void setPopis(PopisPrvkov popis) {
		this.popis = popis;
	}

}

