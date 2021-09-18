package batailleNavale;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.border.EtchedBorder;
import java.awt.Font;

import javax.imageio.IIOException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;

import javax.swing.border.TitledBorder;
import javax.swing.plaf.synth.SynthSpinnerUI;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import javax.swing.UIManager;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.BoxLayout;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BatailleNavale {

	private Joueur joueur1, joueur2;
	private int tailleGrille;

	private JFrame frmBatailleNavale;
	private JTextField txtJoueur;
	private JTextField txtJoueur_1;
	private JTextField txtJoueur_2;
	private final ButtonGroup buttonGroup_Joueur1 = new ButtonGroup();
	private final ButtonGroup buttonGroup_Joueur2 = new ButtonGroup();

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BatailleNavale window = new BatailleNavale();
					window.frmBatailleNavale.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	private void demarrerPartie() {
		new Thread() {
			public void run() {
				joueur1.jouerAvec(joueur2);
			}
		}.start();
	}

	
	public BatailleNavale() {
		initialize();
	}

	
	private void initialize() {
		frmBatailleNavale = new JFrame();
		frmBatailleNavale.setResizable(false);
		frmBatailleNavale.setTitle("Bataille Navale");
		frmBatailleNavale.setBounds(100, 100, 376, 448);
		frmBatailleNavale.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBatailleNavale.getContentPane().setLayout(null);

		JPanel TailleGrille = new JPanel();
		TailleGrille.setToolTipText("");
		TailleGrille.setBounds(0, 1, 370, 33);
		TailleGrille.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		frmBatailleNavale.getContentPane().add(TailleGrille);
		TailleGrille.setLayout(new BoxLayout(TailleGrille, BoxLayout.X_AXIS));

		JLabel lblTailleDeGrille = new JLabel("Taille de grille : ");
		lblTailleDeGrille.setFont(new Font("Dialog", Font.BOLD, 13));
		TailleGrille.add(lblTailleDeGrille);
		
		txtJoueur = new JTextField();
		txtJoueur.setText("10");
		TailleGrille.add(txtJoueur);

		JPanel panel2 = new JPanel();
		panel2.setBounds(0, 41, 370, 320);
		frmBatailleNavale.getContentPane().add(panel2);
		panel2.setLayout(new GridLayout(2, 0, 0, 0));

		JPanel panel_Joueur1 = new JPanel();
		panel_Joueur1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Joueur 1",
				TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		panel2.add(panel_Joueur1);
		panel_Joueur1.setLayout(new GridLayout(4, 0, 0, 0));

		JPanel panel = new JPanel();
		panel_Joueur1.add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

		JLabel lblNom = new JLabel("Nom : ");
		lblNom.setFont(new Font("Dialog", Font.BOLD, 13));
		panel.add(lblNom);

		txtJoueur_1 = new JTextField();
		txtJoueur_1.setText("Joueur 1");
		panel.add(txtJoueur_1);
		txtJoueur_1.setColumns(10);
		
		
		JRadioButton BJgraph1 = new JRadioButton("Joueur graphique");
		BJgraph1.setSelected(true);
		buttonGroup_Joueur1.add(BJgraph1);
		BJgraph1.setFont(new Font("Dialog", Font.BOLD, 13));
		panel_Joueur1.add(BJgraph1);

		JRadioButton BJtext1 = new JRadioButton("Joueur Texte");
		buttonGroup_Joueur1.add(BJtext1);
		BJtext1.setFont(new Font("Dialog", Font.BOLD, 13));
		panel_Joueur1.add(BJtext1);

		JRadioButton BJauto1 = new JRadioButton("Joueur Auto");
		buttonGroup_Joueur1.add(BJauto1);
		BJauto1.setFont(new Font("Dialog", Font.BOLD, 13));
		panel_Joueur1.add(BJauto1);

		JPanel panel_Joueur2 = new JPanel();
		panel_Joueur2.setBorder(BorderFactory.createTitledBorder("Joueur 2"));
		panel2.add(panel_Joueur2);
		panel_Joueur2.setLayout(new GridLayout(4, 0, 0, 0));

		JPanel panel_1 = new JPanel();
		panel_Joueur2.add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));

		JLabel lblNewLabel = new JLabel("Nom : ");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 13));
		panel_1.add(lblNewLabel);

		txtJoueur_2 = new JTextField();
		txtJoueur_2.setText("Joueur 2");
		panel_1.add(txtJoueur_2);
		txtJoueur_2.setColumns(10);

		JRadioButton BJgrap2 = new JRadioButton("Joueur graphique");
		BJgrap2.setSelected(true);
		BJgrap2.setFont(new Font("Dialog", Font.BOLD, 13));
		buttonGroup_Joueur2.add(BJgrap2);
		panel_Joueur2.add(BJgrap2);

		JRadioButton BJText2 = new JRadioButton("Joueur Texte");
		BJText2.setFont(new Font("Dialog", Font.BOLD, 13));
		buttonGroup_Joueur2.add(BJText2);
		panel_Joueur2.add(BJText2);

		JRadioButton BJauto2 = new JRadioButton("Joueur Auto");
		BJauto2.setFont(new Font("Dialog", Font.BOLD, 13));
		buttonGroup_Joueur2.add(BJauto2);
		panel_Joueur2.add(BJauto2);

		JPanel panel3 = new JPanel();
		panel3.setBounds(0, 360, 370, 53);
		frmBatailleNavale.getContentPane().add(panel3);
		panel3.setLayout(null);

		JButton btnLancerLaPartie = new JButton("Lancer la partie");
		btnLancerLaPartie.addActionListener(new ActionListener() {



			public void actionPerformed(ActionEvent e) {
				
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						
						int[] tabNav = { 2, 3, 4, 5, 3 };
						tailleGrille = Integer.parseInt(txtJoueur.getText());
						
						
						

								if(tailleGrille <10 || tailleGrille >25) {
									JOptionPane.showMessageDialog(frmBatailleNavale,"Erreur : veuillez vérifier votre saisie! \n"
											+ " La taille de la grille doit etre comptise entre 10 et 25");
								}
							
					try {
						
						
						InputStream tir = new FileInputStream("C:/Users/ilham/Desktop/SON/Pirates.wav");
						AudioStream sontir = new AudioStream(tir);		
						AudioPlayer.player.start(sontir);
						
						if (BJgraph1.isSelected()) {
							System.out.println("bouton Joueur graphique");
							FenetreJoueur fen = new FenetreJoueur(txtJoueur_1.getText(), tailleGrille);
							fen.getGrilleDefense().placementAuto(tabNav);
							fen.pack();
							fen.setTitle("Jeu Bataille Navale: " + txtJoueur_1.getText());
							fen.setVisible(true);
							joueur1 = new JoueurGraphique(fen.getGrilleDefense(), fen.getGrilleTirs(),
									txtJoueur_1.getText());
						}

						if (BJtext1.isSelected()) {
							System.out.println("bouton Joueur text");
							GrilleNavale grilleJ1 = new GrilleNavale(tailleGrille, tabNav);
							joueur1 = new JoueurTexte(grilleJ1, txtJoueur_1.getText());

						}
						if (BJauto1.isSelected()) {
							System.out.println("bouton Joueur auto");
							FenetreJoueur fen = new FenetreJoueur(txtJoueur_1.getText(), tailleGrille);
							fen.getGrilleDefense().placementAuto(tabNav);
							fen.pack();
							fen.setVisible(true);
							joueur1 = new JoueurAuto(fen.getGrilleDefense(), txtJoueur_1.getText());

						}
						if (BJgrap2.isSelected()) {
							System.out.println("bouton Joueur graphique1");
							FenetreJoueur fen2 = new FenetreJoueur(txtJoueur_2.getText(), tailleGrille);
							fen2.getGrilleDefense().placementAuto(tabNav);
							fen2.pack();
							fen2.setTitle("Jeu Bataille Navale: " + txtJoueur_2.getText());
							fen2.setVisible(true);
							joueur2 = new JoueurGraphique(fen2.getGrilleDefense(), fen2.getGrilleTirs(),
									txtJoueur_2.getText());

						}
						if (BJText2.isSelected()) {
							System.out.println("bouton Joueur text1");
							GrilleNavale grilleJ2 = new GrilleNavale(tailleGrille, tabNav);
							joueur2 = new JoueurTexte(grilleJ2, txtJoueur_2.getText());

						}
						if (BJauto2.isSelected()) {
							System.out.println("bouton Joueur auto1");
							FenetreJoueur fen2 = new FenetreJoueur(txtJoueur_2.getText(), tailleGrille);
							fen2.getGrilleDefense().placementAuto(tabNav);
							fen2.pack();
							fen2.setVisible(true);
							joueur2 = new JoueurAuto(fen2.getGrilleDefense(), txtJoueur_2.getText());
						}
						}catch(IllegalArgumentException e1) {
							System.out.println(e1.getMessage() + "ATTENTION ERREUR" );
							JOptionPane.showMessageDialog(frmBatailleNavale,"Erreur : veuillez vérifier votre saisie!" );
							
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						} catch(IOException e) {
							e.printStackTrace();
						
						}


						demarrerPartie();
					}
				});
			}
		});

		btnLancerLaPartie.setFont(new Font("Dialog", Font.BOLD, 13));
		btnLancerLaPartie.setBounds(94, 12, 195, 27);
		panel3.add(btnLancerLaPartie);
		
	}
}