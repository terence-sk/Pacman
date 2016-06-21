package sk.umb.pacman.aktivneprvky;

import sk.umb.pacman.plocha.MapovyPrvok;

public abstract class Postava extends MapovyPrvok {
	
	
	protected Postava(int x, int y) {
		super(x, y);
	}

	protected boolean canMove(int kamX, int kamY){
		
		return false;
	}
	
}
