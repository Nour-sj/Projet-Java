package batailleNavale;

public abstract class JoueurAvecGrille extends Joueur {
	private GrilleNavale grille;
	
	
	public JoueurAvecGrille(GrilleNavale g, String nom) {
		super(g.getTaille(),nom);
		this.grille=g;
	}

	public JoueurAvecGrille (GrilleNavale g) {
		super(g.getTaille());
		this.grille=g;
	}
	
	public GrilleNavale getGrille() {
		return grille;
	}
	
	
	public int defendre(Coordonnee c) {
		grille.recoitTir(c);
		if(grille.estCoule(c)) {
			if (grille.perdu())
				return GAMEOVER;
			return COULE;
		}
		if(grille.estALEau(c))
			return A_L_EAU;
		if (grille.estTouche(c))
			return TOUCHE;
		throw new RuntimeException ("La grille ne reçoit pas la coordonnée du tir");
	}


}
