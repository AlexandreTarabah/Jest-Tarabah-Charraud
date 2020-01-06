package vue;

import java.awt.*;

import javax.swing.JButton;
import javax.swing.JFrame;

import controleur.Controleur;


public class Home extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private HomePanel hp;
	private JButton nouvellegame;
	private JButton regles;
	private JButton quitter;
	
	public Home(){
		this.setTitle("JEU DU JEST");
		this.setSize(1000, 800);
	    this.setLocationRelativeTo(null);               
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    this.hp = new HomePanel();
	    
	    Font font = new Font("Courier", Font.BOLD, 15);
	    
		this.nouvellegame = new JButton("Nouvelle game");
		this.nouvellegame.setFont(font);
		this.nouvellegame.setActionCommand("nouvellegame");
		
		this.regles = new JButton("Regles du jeu");
		this.regles.setFont(font);
		this.regles.setActionCommand("reglesdujeu");
		
		this.quitter = new JButton("Quitter");
		this.quitter.setFont(font);
		this.quitter.setActionCommand("quittergame");
		
	    this.hp.setLayout(new GridBagLayout());
	    GridBagConstraints gbc = new GridBagConstraints();
	    gbc.insets = new Insets(20,20,20,20);
	    gbc.ipadx = 75;
	    gbc.ipady = 10;
	    
	    gbc.gridx = 0;
	    gbc.gridy = 1;
	    this.hp.add(nouvellegame, gbc);
	    
	    gbc.gridy = 2;
	    this.hp.add(regles, gbc);
	    
	    gbc.gridy = 3;
	    this.hp.add(quitter, gbc);
	    
	    this.setContentPane(hp);
	    this.setVisible(true);
	}

	public void setListener(Controleur controleur) {
		nouvellegame.addActionListener(controleur) ;
		regles.addActionListener(controleur) ;
		quitter.addActionListener(controleur) ;
	}
}
