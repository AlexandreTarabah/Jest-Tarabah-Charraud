package Vue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import Modele.Game;

public class Scores extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JTable tableau;
	private String titres[] = {"Nom du Joueur", "Score"};
	private Object donnees[][];
	
	public Scores(Game p){
		this.setLocationRelativeTo(null);
		this.setSize(400, 300);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setTitle("Scores");

	    this.donnees = new Object[p.getPlayers().size()][p.getPlayers().size()];
	    for (int i=0;i<p.getPlayers().size();i++){
	    	this.donnees[i][0] = p.getPlayers().get(i).getPseudo();
	    }
	    for (int j=0;j<p.getPlayers().size();j++){
	    	this.donnees[j][1] = p.getPlayers().get(j).getScore();
    	}
	    this.tableau = new JTable(donnees, titres);
	    this.setContentPane(new JScrollPane(tableau));
	}

}

