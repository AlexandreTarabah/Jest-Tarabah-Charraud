package Vue;

import java.awt.*;

import javax.swing.*;

import Controleur.Controleur;
import pannel.PanelAccueil;

public class Configuration extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private PanelAccueil pp;
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
	
	public Parametres(){
		super();
		this.setTitle("Parametres de jeu");
		this.setSize(1000, 800);
	    this.setLocationRelativeTo(null);               
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    pp = new PanelHome();
	    difficulty = new JPanel();
		virtual = new JPanel();
		real = new JPanel();
		Font font = new Font("Courier", Font.BOLD, 15);
		validation = new JButton("Play");
		validation.setFont(font);
		validation.setActionCommand("play");
		
		difficultyGroup = new ButtonGroup();
		botDown = new JRadioButton("Easy / Bot Down");
		botDown.setFont(font);
		botDown.setOpaque(false);
		botHard = new JRadioButton("Hard / Bot Hard");
		botHard.setFont(font);
		botHard.setOpaque(false);
		
		
		nbrReal = new JComboBox();
		nbrVirtual = new JComboBox();
		
		lDifficulty = new JLabel("Difficulty : ");
		lDifficulty.setFont(font);
		
		lNbrVirtual = new JLabel("Joueurs Virtuels : ");
		lNbrVirtual.setFont(font);
		
		lNbrReal = new JLabel("Joueurs Reels : ");
		lNbrReal.setFont(font);
		
		for (int i=0;i<10;i++){nbrReal.addItem(i+1);}
		for (int i=0;i<10;i++){nbrVirtual.addItem(i);}
		
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
		
		pp.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
	    gbc.insets = new Insets(10,10,10,10);
	    
	    gbc.gridx = 0;
	    gbc.gridy = 0;
	    pp.add(virtual, gbc);
	    
	    gbc.gridy = 1;
	    pp.add(real, gbc);
	    
	    gbc.gridy = 2;
	    pp.add(difficulty, gbc);
	    
	    gbc.ipadx = 100;
	    gbc.ipady = 10;
	    gbc.gridy = 3;
		pp.add(validation, gbc);
		
	    this.setContentPane(pp);
	}
	
	public int getDifficulty(){
		int difficulte = 0;
		if (botDown.isSelected()){difficulte = 1;}
		if (botHard.isSelected()){difficulte = 2;}
		return difficulty;
	}
	
	public int getNbrVirtual(){
		return (Integer) nbrVirtual.getSelectedItem();
	}
	
	public int getNbrReal(){
		return (Integer) nbrReal.getSelectedItem();
	}
	
	public void setListener(Controleur controleur) {
		validation.addActionListener(controleur);
	}
}
