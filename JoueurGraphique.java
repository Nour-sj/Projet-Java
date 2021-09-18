package batailleNavale;

import java.awt.Color;
import java.awt.EventQueue;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.JOptionPane;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class JoueurGraphique extends JoueurAvecGrille {
	private GrilleGraphique grilleTirs;
	
	public JoueurGraphique(GrilleNavaleGraphique grilleDefense, GrilleGraphique grilleTirs, String nom) {
		super(grilleDefense,nom);
		this.grilleTirs=grilleTirs;
	}
	
	public JoueurGraphique(GrilleNavaleGraphique grilleDefense, GrilleGraphique grilleTirs) {
		super(grilleDefense);
		this.grilleTirs=grilleTirs;
	}

	public Coordonnee choisirAttaque() {
		return this.grilleTirs.getCoordonneeSelectionnee();
	}

	protected void retourDefense(Coordonnee c, int etat) {
		
		switch (etat) {
		case TOUCHE:
			JOptionPane.showMessageDialog(grilleTirs, "L'ennemi a touché un navire en " + c);
			break;
		case COULE:
			JOptionPane.showMessageDialog(grilleTirs, "L'ennemi a coulé un navire en " + c);
			break;
		case GAMEOVER:
			JOptionPane.showMessageDialog(grilleTirs, "Vous avez PERDU...");
		}
	}

	protected void retourAttaque(Coordonnee c, int etat) throws IOException {
		Color couleur = etat == A_L_EAU ? Color.BLUE : Color.RED;
		grilleTirs.colorie(c, couleur);
		switch (etat) {
		case TOUCHE:
			///----son----
			InputStream tir = new FileInputStream("C:/Users/ilham/Desktop/SON/touch.wav");
			AudioStream sontir = new AudioStream(tir);		
			AudioPlayer.player.start(sontir);
			//---fin ---
			
			
			JOptionPane.showMessageDialog(grilleTirs, "Vous avez touche un navire en " + c);
			break;
		case COULE:
			///------son ------
			InputStream tirCoule = new FileInputStream("C:/Users/ilham/Desktop/SON/coule.wav");
			AudioStream sontircoule = new AudioStream(tirCoule);		
			AudioPlayer.player.start(sontircoule);
			//------Fin son -----
			
			JOptionPane.showMessageDialog(grilleTirs, "Vous avez coulé un navire en " + c);
			break;
		case GAMEOVER:
			
			//---son---
			///----son----
			InputStream gover = new FileInputStream("C:/Users/ilham/Desktop/SON/gover.wav");
			AudioStream songover = new AudioStream(gover);		
			AudioPlayer.player.start(songover);
			//---fin ---
			
			
			JOptionPane.showMessageDialog(grilleTirs, "Vous avez GAGNE !!!");
		}
	}
}