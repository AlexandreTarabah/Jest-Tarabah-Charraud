package vue;

import java.awt.*;

import javax.swing.JButton;
import javax.swing.JFrame;

import controleur.Controleur;

/**
 *Cette classe représente l'affichage graphique du menu principal 
 *Cette classe possède : 
 *<ul>
 * <li> un HomePanel hp</li>
 * <li> un bouton nouvellegame </li>
 * <li> un bouton regles </li>
 * <li> un bouton quitter </li>
 * </ul>
 *
 *
 */
public class Home extends JFrame{
	private static final long serialVersionUID = 1L;
	
	private HomePanel hp;
	private JButton nouvellegame;
	private JButton regles;
	private JButton quitter;
	
	/**
	 * Constructeur de l'acceuil : 
	 * on met a jour le titre, la taille de la fenetre, et on instancie sur une grille les boutons 
	 */
	
	public Home(){
		this.setTitle("JEU DU JEST");
		this.setSize(1000, 800);
	    this.setLocationRelativeTo(null);               
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    this.hp = new HomePanel();
	    
	    Font font = new Font("Courier", Font.BOLD, 15);
	    
		this.nouvellegame = new JButton("Nouvelle partie");
		this.nouvellegame.setFont(font);
		this.nouvellegame.setActionCommand("nouvellegame");
		
		this.regles = new JButton("Règles du JEST");
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

	/**
	 * Met en place les listener en lien avec controleur
	 * @param controleur
	 */
	public void setListener(Controleur controleur) {
		nouvellegame.addActionListener(controleur) ;
		regles.addActionListener(controleur) ;
		quitter.addActionListener(controleur) ;
	}
}
