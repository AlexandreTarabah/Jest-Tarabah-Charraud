package fr.utt.lo02.uno.vue;

import java.awt.*;

import javax.swing.JButton;
import javax.swing.JFrame;

import Controleur.Controleur;
import pannel.PanelAccueil;

public class Home extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private PanelHome ph;
	private JButton newGame;
	private JButton rules;
	private JButton quit;
	
	public Accueil(){
		this.setTitle("JEST");
		this.setSize(1000, 800);
	    this.setLocationRelativeTo(null);               
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    this.ph = new PanelHome();
	    
	    Font font = new Font("Courier", Font.BOLD, 15);
	    
		this.newGame = new JButton("newGame");
		this.newGame.setFont(font);
		this.newGame.setActionCommand("newGame");
		
		this.rules = new JButton("Rules");
		this.rules.setFont(font);
		this.rules.setActionCommand("rules");
		
		this.quit = new JButton("quit");
		this.quit.setFont(font);
		this.quit.setActionCommand("quitGame");
		
	    this.ph.setLayout(new GridBagLayout());
	    GridBagConstraints gbc = new GridBagConstraints();
	    gbc.insets = new Insets(20,20,20,20);
	    gbc.ipadx = 75;
	    gbc.ipady = 10;
	    
	    gbc.gridx = 0;
	    gbc.gridy = 1;
	    this.ph.add(newGame, gbc);
	    
	    gbc.gridy = 2;
	    this.ph.add(rules, gbc);
	    
	    gbc.gridy = 3;
	    this.ph.add(quit, gbc);
	    
	    this.setContentPane(ph);
	    this.setVisible(true);
	}

	public void setListener(Controleur controleur) {
		newGame.addActionListener(controleur);
		rules.addActionListener(controleur);
		quit.addActionListener(controleur);
	}
}
