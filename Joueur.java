package batailleNavale;

import java.awt.HeadlessException;
import java.io.FileNotFoundException;
import java.io.IOException;

public abstract class Joueur {
	
	public final static int TOUCHE = 1;
	public final static int COULE = 2;
	public final static int A_L_EAU = 3;
	public final static int GAMEOVER = 4;
	
	private Joueur adversaire;
	private int tailleGrille;
	private String nom;
	
	
	
	public Joueur(int tailleGrille, String nom) {
		this.nom = nom;
		this.tailleGrille = tailleGrille;
		
				
	}
	
	public Joueur(int tailleGrille) {
		this.tailleGrille = tailleGrille;
		 this.nom="joueur";
	
	}
	
	public int getTailleGrille() {
		return this.tailleGrille;
	}
	
	public String getNom() {
		return this.nom;
		}
	
	public void jouerAvec(Joueur j) {
		    this.adversaire = j;
			j.adversaire = this;
	
		try {
			deroulementJeu (this,j);
		} catch (HeadlessException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private static void deroulementJeu(Joueur attaquant, Joueur defenseur) throws HeadlessException, FileNotFoundException, IOException {
		int res = 0;
		while (res != GAMEOVER) {
		Coordonnee c = attaquant.choisirAttaque();
		res = defenseur.defendre(c);
		attaquant.retourAttaque(c, res);
		defenseur.retourDefense(c, res);
		Joueur x = attaquant;
		attaquant = defenseur;
		defenseur = x;
		}
		}


}
