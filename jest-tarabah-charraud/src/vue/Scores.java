package vue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import modele.game.Game;


public class Scores extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JTable tableau;
	private String titres[] = {"Pseudo", "Score"};
	
	public Scores(Game g){
		this.setLocationRelativeTo(null);
		this.setSize(400, 300);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setTitle("Scores");

//tableau scores deux dimensions créé dans game qui reçoit pseudo et scores des joueurs
	    this.tableau = new JTable(g.scores, titres);
	    this.setContentPane(new JScrollPane(tableau));
	}

}

