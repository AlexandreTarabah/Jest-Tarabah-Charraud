package vue;

import java.awt.*;

import javax.swing.*;

import controleur.Controleur;
import modele.game.Game;

public class Parametres extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JButton validation;
	private JRadioButton botDown;
	private JRadioButton botHard;
	private ButtonGroup difficultyGroup;
	private JComboBox nbrReal;
	private JComboBox nbrVirtual;
	private JPanel difficulty;
	private JPanel virtual;
	private JPanel real;
	private JLabel lDifficulty;
	private JLabel lNbrVirtual;
	private JLabel lNbrReal;
	private ParametresPanel parp;
	
	public Parametres(){
		super();
		this.setTitle("Parametres de jeu");
		this.setSize(1000, 800);
	    this.setLocationRelativeTo(null);               
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    this.parp = new ParametresPanel();
	    
	    difficulty = new JPanel();
	    virtual = new JPanel();
	    real = new JPanel();
		Font font = new Font("Courier", Font.BOLD, 15);
		validation = new JButton("Jouer");
		validation.setFont(font);
		validation.setActionCommand("jouer");
		
		difficultyGroup = new ButtonGroup();
		
		botDown = new JRadioButton("Facile");
		botDown.setFont(font);
		botDown.setOpaque(false);
		
		botHard = new JRadioButton("Moyen");
		botHard.setFont(font);
		botHard.setOpaque(false);
		
		nbrReal = new JComboBox();
		nbrVirtual = new JComboBox();
		
		lDifficulty = new JLabel("Difficulte : ");
		lDifficulty.setFont(font);
		
		lNbrVirtual = new JLabel("Joueurs Virtuels : ");
		lNbrVirtual.setFont(font);
		
		lNbrReal = new JLabel("Joueurs Reels : ");
		lNbrReal.setFont(font);
		
		for (int i=0;i<5;i++){nbrReal.addItem(i+1);}
		for (int i=0;i<5;i++){nbrVirtual.addItem(i+1);}
		
		difficultyGroup.add(botDown);
		difficultyGroup.add(botHard);
		
		
		difficulty.add(lDifficulty);
		difficulty.add(botDown);
		difficulty.add(botHard);
		difficulty.setOpaque(false);
		
		virtual.add(lNbrVirtual);
		virtual.add(nbrVirtual);
		virtual.setOpaque(false);
		
		real.add(lNbrReal);
		real.add(nbrReal);
		real.setOpaque(false);
		
		parp.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
	    gbc.insets = new Insets(10,10,10,10);
	    
	    gbc.gridx = 0;
	    gbc.gridy = 0;
	    parp.add(virtual, gbc);
	    
	    gbc.gridy = 1;
	    parp.add(real, gbc);
	    
	    gbc.gridy = 2;
	    parp.add(difficulty, gbc);
	    
	    gbc.ipadx = 100;
	    gbc.ipady = 10;
	    gbc.gridy = 3;
		parp.add(validation, gbc);
		
	    this.setContentPane(parp);
	}
	
	public int getDifficulte(){
		int difficulty = 0;
		if (botDown.isSelected()){difficulty = 1;}
		if (botHard.isSelected()){difficulty = 2;}
		return difficulty;
	}
	
	public int getNbrVirtuels(){
		return (Integer) nbrVirtual.getSelectedItem();
	}
	
	public int getNbrReels(){
		return (Integer) nbrReal.getSelectedItem();
	}
	
	public void setListener(Controleur controleur) {
		validation.addActionListener(controleur);
	}
	public void reglerParametres(Game g) {
		g.nbPlayers=this.getNbrReels()+this.getNbrVirtuels();
	}
}
