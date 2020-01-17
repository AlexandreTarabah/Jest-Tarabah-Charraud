package vue;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import modele.game.Game;

/**
 * Cette classe permet d'afficher les scores à la fin de la partie 
 * 
 *
 */
public class Scores extends JFrame {

	
	private static final long serialVersionUID = 1L;

	/**
	 * Constructeur qui permet de créé la fenetre des scores d'afficher une nouvelle table des scores 
	 * @param g
	 */
	public Scores(Game g) {
		super();

		setTitle("TABLEAU DES SCORES");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null); 
		this.setSize(500, 500);

		String[] entetes = {"Pseudo des Joueurs", "Score"};

		JTable tableau = new JTable(g.scores, entetes);
		
		this.setBackground(Color.YELLOW); 

		getContentPane().add(new JScrollPane(tableau), BorderLayout.CENTER);
		
		

	}


}