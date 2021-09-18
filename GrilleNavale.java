package batailleNavale;

public class GrilleNavale {
	private Navire[] navires;
	private int nbNavires;
	private int taille;
	private Coordonnee[] tirsRecus;
	private int nbTirsRecus;

	public GrilleNavale(int taille, int[] taillesNavires) {
		this (taille,taillesNavires.length);
		this.placementAuto(taillesNavires);
	}

	public GrilleNavale(int taille, int nbNavires) {
		if (taille <= 0)  {
			throw new IllegalArgumentException("La grille doit être au moins de taille 1");	
		}
		if (nbNavires < 0 )  {
			throw new IllegalArgumentException("Le nombre de navires ne peut pas être négatif");	
		}
		if (taille == 1 & nbNavires!=1)  {
			throw new IllegalArgumentException("La grille de taille 1 ne peut pas contenir plus de 1 navire");	
		}
		this.taille = taille;
		this.nbNavires = 0;
		this.navires = new Navire[nbNavires];
		this.nbTirsRecus = 0;
		this.tirsRecus = new Coordonnee[this.taille * this.taille];
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer(taille*taille);
		StringBuffer idxLigne = new StringBuffer(taille);
		char [][] t = new char [taille][taille];
		
		idxLigne.append("   ");
		for (int i = 0 ; i < taille ; i++) {
			idxLigne.append((char) ('A'+ i)+" ");
		}
		
		for (int i = 0 ; i < taille ; i++) {
			if (i+1 < 10)
				sb.append(" " + (Integer.toString(i+1)) + " ");
			else
				sb.append((Integer.toString(i+1)) + " ");
			
			for(int j = 0 ; j < taille ; j++){
				Coordonnee c = new Coordonnee(i,j);
				t[i][j] = '.';
				
				for (int n = 0 ; n < nbNavires; n++) {
					if (navires[n].getDebut().getLigne() == navires[n].getFin().getLigne() && i == navires[n].getDebut().getLigne()) {
						if (j >= navires[n].getDebut().getColonne() && j <= navires[n].getFin().getColonne())
							t[i][j] = '#' ;
						}
					if (navires[n].getDebut().getColonne() == navires[n].getFin().getColonne() && j == navires[n].getDebut().getColonne()) {
						if (i >= navires[n].getDebut().getLigne() && i <= navires[n].getFin().getLigne())
							t[i][j] = '#' ;
						}
					}
				if (this.estTouche(c)) {
					t[i][j] = 'X';
				}
				else if (this.estDansTirsRecus(c)) {
					t[i][j] = 'O';
					}
				sb.append(t[i][j]).append(' ');
			}			
			sb.append('\n');
		}
		return idxLigne.append('\n') + sb.toString();	
	}
	
	public int getTaille() {
		return(taille);
	}
	
	public boolean ajouteNavire(Navire n) {
		int i = 0;
		boolean possible = true;
		while (i < nbNavires && possible) {
				if(navires[i].touche(n) || navires[i].chevauche(n) ) 
					possible = false;
				i++;
		}
		if (possible == true) {
			if(!estDansGrille(n.getFin())) {
				possible = false;
			}
		}
		if(possible) {
			navires[nbNavires] = n;
			this.nbNavires++;
		}
		return possible;
	}
	
	public void placementAuto(int[] taillesNavires) {
		int i = 0;
			while (i < taillesNavires.length) {
				boolean estVertical = Math.random() < 0.5;
				int lig = (int) (Math.random() * taille) ;
				int col = (int) (Math.random() * taille) ;
				Coordonnee deb = new Coordonnee(lig, col);
				Navire nav = new Navire(deb, taillesNavires[i], estVertical);
				if (ajouteNavire(nav)) {
					i++;
				}
		}
	}
	
	private boolean estDansGrille(Coordonnee c) {
		int i = 0;
		int j = 0;
		boolean trouve = false;
		for ( i = 0 ; i < taille ; i++) {
			for(j = 0 ; j < taille ; j++){
				if (i == c.getLigne() && j == c.getColonne())
					trouve=true;
				}
		}
		return (trouve);
	}
	
	private boolean estDansTirsRecus(Coordonnee c) {
		int i=0;
		boolean appartient = false;
		while (i < nbTirsRecus && !appartient) {
			if (c.compareTo(tirsRecus[i]) == 0) {
				appartient = true;
			}
			i++;
		}
		return (appartient);	
	}
	
	private boolean ajouteDansTirsRecus(Coordonnee c) {
		if (estDansGrille(c) && !estDansTirsRecus(c)) {
			int i = 0;
			Coordonnee [] t = new Coordonnee [nbTirsRecus+1];
			for (i = 0 ; i < nbTirsRecus ; i++) {
				t[i] = tirsRecus[i];
			}
			t[i] = c;
			tirsRecus = t;
			nbTirsRecus += 1;
			return true;	
		}
		else return false;	
	}
	
	public boolean recoitTir(Coordonnee c) {
		boolean tir = ajouteDansTirsRecus(c);
		boolean touche = false;
		if (tir) {
			int i = 0;
			while (i < nbNavires && !touche) {
				if (navires[i].recoitTir(c))
					touche = true;
				i++;
			}
		}
			
		return touche;
	}
	
	public boolean estTouche(Coordonnee c) {
		boolean touche = false;
		int i = 0;
		while (i < nbNavires && !touche) {
			if (navires[i].estTouche(c))
				touche = true;
			i++;
		}
		return (touche);
	}
	
	public boolean estALEau(Coordonnee c) {
		return !estTouche(c);
	}
	
	boolean estCoule(Coordonnee c) {
		boolean coule = false;
		int i = 0;
		while (i < nbNavires && !coule) {
			if (navires[i].estTouche(c) && navires[i].estCoule()) {
				coule = true;
			}
			i++;
		}
		return  coule;
		}
	
	public boolean perdu() {
		boolean perdu = true;
		int i = 0;
		while(i < nbNavires && perdu) {
			if(!navires[i].estCoule())
				perdu = false;
			i++;
		}
		return(perdu);
	}
		
}
