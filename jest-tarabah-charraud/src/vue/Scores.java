package vue;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import modele.game.Game;


public class Scores extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Scores(Game g) {
		super();

		setTitle("SCORES");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null); 
		this.setSize(500, 500);

		String[] entetes = {"Pseudo", "Scores"};

		JTable tableau = new JTable(g.scores, entetes);

		getContentPane().add(new JScrollPane(tableau), BorderLayout.CENTER);

	}


}