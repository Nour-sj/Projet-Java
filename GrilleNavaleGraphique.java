package batailleNavale;

import java.awt.Color;

public class GrilleNavaleGraphique extends GrilleNavale {
	private GrilleGraphique grille;

	public GrilleNavaleGraphique(int taille) {
		super(taille, 10);
		this.grille = new GrilleGraphique(taille);
	}

	public GrilleGraphique getGrilleGraphique() {
		return this.grille;
	}

	public boolean ajouteNavire(Navire n) {
		if (super.ajouteNavire(n)) {
			grille.colorie(n.getDebut(), n.getFin(), Color.green);
			return true;
		}
		return false;
	}

	public boolean recoitTir(Coordonnee c) {
		if (super.recoitTir(c)) {
			grille.colorie(c, Color.RED);
			return true;
		} else {
			grille.colorie(c, Color.BLUE);
			return false;
		}

	}
}
