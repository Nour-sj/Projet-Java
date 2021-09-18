package batailleNavale;

public class Coordonnee implements Comparable<Coordonnee>{
	
	private int ligne;
	private int colonne;
	
	public Coordonnee(int ligne, int colonne) {
		if (ligne < 0 || ligne > 25 || colonne < 0 || colonne > 25)
			throw new IllegalArgumentException("Les coordonnées " + colonne + ligne + " sont en dehors des indices valides");
		this.ligne = ligne;
		this.colonne = colonne;
		
	}

	public Coordonnee(String s) {
		// TODO Auto-generated constructor stub
		if (s == null)
			throw new IllegalArgumentException("Les coordonnées sont vides");
		if (s.length() < 2) {
			throw new IllegalArgumentException("La coordonnée n'est pas complète");
		}
		if (s.length() < 4) {
			String lettre = s.substring(0, 1);
			this.ligne = Integer.parseInt(s.substring(1)) - 1;
			lettre = lettre.toUpperCase();
			this.colonne = lettre.charAt(0) - 65;
			if (this.colonne < 0 || this.colonne > 25 || this.ligne < 0 || this.ligne > 25)
				throw new IllegalArgumentException("La colonne n'est pas correcte");
		}
		else {
			throw new IllegalArgumentException("Les coordonnées " + this.colonne + this.ligne + " ne sont pas valides");
		}
	}
	
	public String toString() {
		int int_lettre = colonne + 65;
		char lettre = (char) int_lettre;
		return "" + lettre + (ligne + 1);
	}
	
	public int getLigne() {
		return this.ligne;
	}
	
	public int getColonne() {
		return this.colonne;
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof Coordonnee) {
			Coordonnee c = (Coordonnee) obj;
			if(c.getColonne() == this.getColonne() && this.getLigne() == c.getLigne())
				return true;
			return false;
		}
		return false;
	}
	
	public boolean voisine(Coordonnee c) {
		if (c.getColonne() == this.colonne) {
			if (this.ligne + 1 == c.getLigne() || this.ligne - 1 == c.getLigne())
				return true;
		} else if (c.getLigne() == this.ligne) {
			if (this.colonne + 1 == c.getColonne() || this.colonne - 1 == c.getColonne())
				return true;
		}
		return false;
	}
	
	public int compareTo(Coordonnee c) {
		if (this.ligne - c.getLigne() < 0 || (this.ligne - c.getLigne() == 0 && this.colonne - c.getColonne() < 0))
			return -1;
		if (c.getLigne() - this.ligne == 0 && c.getColonne() - this.colonne == 0)
			return 0;
		return 1;
	}

}
