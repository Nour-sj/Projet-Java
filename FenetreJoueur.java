package batailleNavale;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class FenetreJoueur extends JFrame {

	private JPanel contentPane;

	private GrilleGraphique grilleTirs;
	private GrilleNavaleGraphique grilleDefense;

	/**
	 * @param nom
	 * @param taille
	 */
	public FenetreJoueur(String nom, int taille) {
				this.grilleTirs= new GrilleGraphique(taille);
				this.grilleDefense= new GrilleNavaleGraphique(taille);
				
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				setBounds(100, 100, 450, 300);
				contentPane = new JPanel();
				contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
				contentPane.setLayout(new BorderLayout(0, 0));
				setContentPane(contentPane);

				JPanel fond = new JPanel();
				this.setContentPane(fond);

				fond.setLayout(new GridLayout(1, 2));
				fond.add(grilleTirs);
				fond.add(grilleDefense.getGrilleGraphique());
			
	}

	public GrilleGraphique getGrilleTirs() {
		return grilleTirs;
	}

	public GrilleNavaleGraphique getGrilleDefense() {
		return grilleDefense;

	}

	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FenetreJoueur frame = new FenetreJoueur();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FenetreJoueur() {
		this("Joueur",10);
		

		
	}

}
