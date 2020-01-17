package vue;
import java.awt.*;
import java.util.LinkedList;
import javax.swing.*;
import modele.joueur.Player;
/**
 * Cette classe correspond au Panel du Joueur.
 * Cette classe contient les attributs suivants : 
 * <ul>
 * <li>Le nom du joueur </li>
 * <li>Un boolean qui détermine si le joueur est réél ou un bot </li>
 * <li>Un label pour le nom du joueur </li>
 * <li> Son card panel pour afficher ses cartes </li>
 * <li> Son jeu, une arrayList d'image </li>
 * </ul>
 */
public class PlayerPanel extends JPanel{

	
	private static final long serialVersionUID = 1L;

	private String nomJoueur;
	private Boolean virtuel = false;
	private JLabel nomJoueurLabel;
	private CardPanel cp;
	private Font font = new Font("Times", Font.BOLD, 25);
	
	private LinkedList<Image> jeu;
	
	/**
	 * Le constructeur du PlayerPanel : 
	 * Affiche les composants du PlayerPanel tel que le nom, et son cardPanel
	 * @param joueur
	 */
	
	public PlayerPanel(Player joueur){
		super();
		this.setOpaque(false);
		this.setLayout(new BorderLayout());
		this.setJeu(new LinkedList<Image>());
	
		if (joueur instanceof Player){
			this.virtuel = false;
		}
		
		this.nomJoueur = joueur.getPseudo();
		this.nomJoueurLabel = new JLabel(nomJoueur);
		this.nomJoueurLabel.setFont(font);
		this.nomJoueurLabel.setForeground(Color.WHITE);
		this.add(nomJoueurLabel, BorderLayout.NORTH);
		this.cp= new CardPanel(jeu);
		this.add(cp);
	}

/**
 * Méthode qui permet d'ajouter au jeu la carte rentrée en paramètre 
 * @param carte
 */
	public void	prendreCarte(Image carte){
		this.jeu.add(carte);
		
	}
	/**
	 * Méthode qui permet de retirer au jeu la carte rentrée en paramètre
	 * @param carte
	 */
	public void retirer(Image carte){
		this.jeu.remove(carte);
	}
	/**
	 * méthode qui permet de tout retirer du jeu
	 */
	public void retirerTout(){
		this.jeu.removeAll(jeu);
	}


	public LinkedList<Image> getJeu() {
		return jeu;
	}

	public void setJeu(LinkedList<Image> jeu) {
		this.jeu = jeu;
	}

	public String getNomJoueur() {
		return nomJoueur;
	}

	public CardPanel getCp() {

		return cp;
	}

	public void setCp(LinkedList<Image> jeu) {
		this.cp= new CardPanel(jeu);
	}
	
	public Boolean isVirtuel() {
		return virtuel;
	}

	public void setVirtuel(Boolean virtuel) {
		this.virtuel = virtuel;
	}

	
}