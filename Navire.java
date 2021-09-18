package batailleNavale;

public class Navire {
	private Coordonnee debut;
	private Coordonnee fin;
	private Coordonnee[] partiesTouchees;
	private int nbTouchees;

	public Navire(Coordonnee debut, int longueur, boolean estVertical) {
		this.debut = debut;
		int ligne = 0;
		int colonne = 0;
		
		if (estVertical) {
			ligne = this.debut.getLigne() + longueur - 1;
			this.fin = new Coordonnee(ligne, this.debut.getColonne());
		} else {
			colonne = this.debut.getColonne() + longueur - 1;
			this.fin = new Coordonnee(this.debut.getLigne(), colonne);
		}
		partiesTouchees = new Coordonnee[longueur];
		nbTouchees = 0 ;
	}

	public String toString() {
		int dgl = this.debut.getLigne();
		int fgl = this.fin.getLigne();
		int dgc = this.debut.getColonne();
		int fgc = this.fin.getColonne();
		String ch = "";
		if (dgl == fgl) {
			ch = "Navire" + "(" + this.debut + ", " + (1 + fgc - dgc) + "," + " horizontal" + ")";
			return ch;
		}
		ch = "Navire" + "(" + this.debut + ", " + (1 + fgl - dgl) + "," + " horizontal" + ")";
		return ch;
	}

	public Coordonnee getDebut() {
		return this.debut;
	}

	public Coordonnee getFin() {
		return this.fin;
	}

	public boolean contient(Coordonnee c) {
		int dl = this.debut.getLigne();
		int fl = this.fin.getLigne();
		int dc = this.debut.getColonne();
		int fc = this.fin.getColonne();
		int cl = c.getLigne();
		int cc = c.getColonne();
		
		if ((dc <= cc && cc <= fc) && (dl <= cl && cl <= fl )){
			return true;
		}
		return false;
	}

		int cd = this.debut.getColonne();
		int ld = this.debut.getLigne();
		int cf = this.fin.getColonne();
		int lf = this.fin.getLigne();
		int cdn = n.debut.getColonne();
		int ldn = n.debut.getLigne();
		int cfn = n.fin.getColonne();
		int lfn = n.fin.getLigne();
		
		if (!this.chevauche(n)) {
			if (((!(cf < cdn || cfn < cd)) && (lf + 1 == ldn || lfn + 1 == ld)) || (!(lf < ldn || lfn < ld) && (cf + 1 == cdn || cfn + 1 == cd))) {
				return true;
			}
			return false;
		}
		return this.chevauche(n);
	}

	public boolean chevauche(Navire n) {
		int cd = this.debut.getColonne();
		int ld = this.debut.getLigne();
		int cf = this.fin.getColonne();
		int lf = this.fin.getLigne();
		int cdn = n.debut.getColonne();
		int ldn = n.debut.getLigne();
		int cfn = n.fin.getColonne();
		int lfn = n.fin.getLigne();

		if (!((cf < cdn || cfn < cd) || (lf < ldn || lfn < ld))) {
			return true;
		}

		return false;
	}
	
	public boolean recoitTir(Coordonnee c) {
		if (this.contient(c) && !this.estTouche(c)) {
			this.partiesTouchees[nbTouchees] = c;
			nbTouchees += 1;
			return true;
		}
		return false;
	}

	public boolean estTouche(Coordonnee c) {
		for (int i = 0 ; i < nbTouchees; i++) {
			if (this.partiesTouchees[i].equals(c))
				return true;
		}
		return false;
	}

	public boolean estTouche() {
		if (nbTouchees > 0)
			return true;
		return false;
	}

	public boolean estCoule() {
		if (nbTouchees == this.partiesTouchees.length)
			return true;
		return false;
	}

}
