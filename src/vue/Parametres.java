package vue;

import java.awt.*;

import javax.swing.*;

import controleur.Controleur;
import modele.game.Game;
/**
 * 
 * Classe représentant la fenètre des paramètres
 * Cette classe contient : 
 * <ul>
 * <li>un bouton de validation</li>
 * <li> des radiosButton pour les choix a effectué</li>
 * <li>des ButtonsGroup pour regrouper les boutons sur l'interface </li>
 * <li>Des panels liés au reste des composants</li>
 * <li> son panel parametrePanel</li>
 *</ul>
 */

public class Parametres extends JFrame{

	private static final long serialVersionUID = 1L;

	private JButton validation;
	private JRadioButton classique;
	private JRadioButton inversion;
	private JRadioButton active;
	private JRadioButton inactive;
	private JRadioButton botDown;
	private JRadioButton botHard;
	private ButtonGroup extensionGroup;
	private ButtonGroup varianteGroup;
	private ButtonGroup difficultyGroup;
	private JComboBox<Integer> nbrReal;
	private JComboBox<Integer> nbrVirtual;
	private JPanel difficulty;
	private JPanel variante;
	private JPanel extension;
	private JPanel virtual;
	private JPanel real;
	private JLabel lDifficulty;
	private JLabel lExtension;
	private JLabel lVariante;
	private JLabel lNbrVirtual;
	private JLabel lNbrReal;
	private ParametresPanel parp;
/**
 * le constructeur, où on met a jour les composants de la fenetre dont : 
 * <ul>
 * <li> titre de la fenetre</li>
 * <li> la taille de la fenetre </li>
 * <li> les panels associés au choix dans la fenetre </li>
 * <li> les groupes de boutons et de choix qui sont regroupés pour faciliter l'affichage au sein d'une grille puis réparti sur la fenetre
 */
	public Parametres(){
		super();
		this.setTitle("Paramètres de jeu");
		this.setSize(1000, 800);
		this.setLocationRelativeTo(null);               
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.parp = new ParametresPanel();

		difficulty = new JPanel();
		extension = new JPanel();
		variante = new JPanel();
		virtual = new JPanel();
		real = new JPanel();
		Font font = new Font("Courier", Font.BOLD, 15);
		validation = new JButton("Jouer");
		validation.setFont(font);
		validation.setActionCommand("jouer");

		extensionGroup = new ButtonGroup();

		active = new JRadioButton("Active");
		getActive().setFont(font);
		getActive().setOpaque(false);

		inactive = new JRadioButton("Inactive");
		getInactive().setFont(font);
		getInactive().setOpaque(false);

		varianteGroup = new ButtonGroup();

		classique = new JRadioButton("Classique");
		getClassique().setFont(font);
		getClassique().setOpaque(false);

		inversion = new JRadioButton("Red Power !");
		getInversion().setFont(font);
		getInversion().setForeground(Color.RED) ;
		getInversion().setOpaque(false);

		difficultyGroup = new ButtonGroup();

		botDown = new JRadioButton("Facile");
		botDown.setFont(font);
		botDown.setOpaque(false);

		botHard = new JRadioButton("Difficile");
		botHard.setFont(font);
		botHard.setOpaque(false);

		nbrReal = new JComboBox<Integer>();
		nbrVirtual = new JComboBox<Integer>();
		
		lExtension = new JLabel("Extension : ");
		lExtension.setFont(font);
		lExtension.setForeground(Color.WHITE) ;

		lVariante = new JLabel("Mode de Jeu : ");
		lVariante.setFont(font);
		lVariante.setForeground(Color.WHITE) ;

		lDifficulty = new JLabel("Difficulté : ");
		lDifficulty.setFont(font);
		lDifficulty.setForeground(Color.WHITE) ;

		lNbrVirtual = new JLabel("Joueurs Virtuels : ");
		lNbrVirtual.setFont(font);
		lNbrVirtual.setForeground(Color.WHITE) ;

		lNbrReal = new JLabel("Joueurs Réels : ");
		lNbrReal.setFont(font);
		lNbrReal.setForeground(Color.WHITE) ;

		for (int i=0;i<5;i++){nbrReal.addItem(i);}
		for (int i=0;i<5;i++){nbrVirtual.addItem(i);}
		
		extensionGroup.add(active);
		extensionGroup.add(inactive);

		difficultyGroup.add(botDown);
		difficultyGroup.add(botHard);

		varianteGroup.add(classique);
		varianteGroup.add(inversion);

		extension.add(lExtension);
		extension.add(active);
		extension.add(inactive);
		extension.setOpaque(false);
		
		variante.add(lVariante) ;
		variante.add(classique) ;
		variante.add(inversion) ;
		variante.setOpaque(false) ;

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
		parp.add(variante, gbc);

		gbc.gridy = 3;
		parp.add(difficulty, gbc);
		
		gbc.gridy = 4;
		parp.add(extension, gbc);

		gbc.ipadx = 100;
		gbc.ipady = 10;
		gbc.gridy = 5;
		parp.add(validation, gbc);

		this.setContentPane(parp);
	}
	/**
	 * Méthode qui permet de retourner la difficulté en fonction du choix 
	 * @return difficulty
	 */

	public int getDifficulte(){
		int difficulty = 0;
		if (botDown.isSelected()){difficulty = 1;}
		if (botHard.isSelected()){difficulty = 2;}
		return difficulty;
	}

	public boolean getVariante(){
		boolean variante = false ;
		if (getClassique().isSelected()){variante = false;}
		if (getInversion().isSelected()){variante = true;}
		return variante;
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

	public JRadioButton getClassique() {
		return classique;
	}

	public JRadioButton getInversion() {
		return inversion;
	}

	public JRadioButton getActive() {
		
		return active;
	}
	
	public JRadioButton getInactive() {
		
		return inactive;
	}
	/** 
	 * @return le boolean extension 
	 */

	public boolean getExtension(){
		boolean extension = false ;
		if (getInactive().isSelected()){extension = false;}
		if (getActive().isSelected()){extension = true;}
		return extension;
	}
}
