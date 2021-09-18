package batailleNavale;

import java.util.Scanner;
public class JoueurTexte extends JoueurAvecGrille {
	private Scanner sc = new Scanner(System.in);
	
	public JoueurTexte(GrilleNavale g, String nom ) {
		super(g, nom);
		}
	
	public JoueurTexte(GrilleNavale g) {
		super(g);
	}
	
	protected void retourAttaque(Coordonnee c, int etat) {
		if(etat == 1) 
			System.out.println("Vous avez touché un navire en "+ c +'\n');
		 if(etat == 2) 
			System.out.println("Vous avez coulé le navire en " + c +'\n');
		 if(etat == 3) 
			System.out.println("Vous avez tirez dans l'eau en " + c +'\n');
		 if(etat == 4) 
			System.out.println("WINNER : "+ this.getNom());
	}
	
	protected void retourDefense(Coordonnee c, int etat) {
		 if(etat == 1) 
			System.out.println("Vous avez été touché en "+ c +'\n');
		 if(etat == 2) 
			System.out.println("Votre navire a coulé en " + c +'\n');
		 if(etat == 3) 
			System.out.println("Le tir précédent est allé à l'eau en " + c +'\n');
		 if(etat == 4) 
			System.out.println("GAMEOVER : " + this.getNom());
	}
	
	public Coordonnee choixAttaque () {
		System.out.println(this.getGrille());
		System.out.println("Entrez la coordonnée d'attaque");
		String input = sc.next();
		Coordonnee c = new Coordonnee(input);
		
		return c;
	}
}
