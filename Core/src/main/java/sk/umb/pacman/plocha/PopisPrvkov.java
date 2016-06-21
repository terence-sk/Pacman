package sk.umb.pacman.plocha;

public enum PopisPrvkov {
	STENA('#'), PACMAN('P'), BODKA('.'), PRISERA('M'), PRAZDNE(' ');
	
	private final char znak;
	
	public char znak(){
		return znak;
	}
	
	private PopisPrvkov(char c) {
		this.znak = c;
	}
}

