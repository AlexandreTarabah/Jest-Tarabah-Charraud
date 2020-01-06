package vue;

import java.awt.*;
import java.util.LinkedList;

import javax.swing.*;

import modele.joueur.BotDown;
import modele.joueur.BotHard;
import modele.joueur.Player;

public class PlayerPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String nomJoueur;
	private Boolean virtuel = false;
	private JLabel nomJoueurLabel;
	private CardPanel cp;
	private JButton uno;
	private Font font = new Font("Courier", Font.BOLD, 20);
	
	private LinkedList<Image> jeu;
	
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
		this.nomJoueurLabel.setForeground(Color.RED);
		this.add(nomJoueurLabel, BorderLayout.NORTH);
		this.cp= new CardPanel(jeu);
		this.add(cp);
	}

	public JButton getUno() {
		return uno;
	}

	public void setUno(JButton uno) {
		this.uno = uno;
	}

	public void	prendreCarte(Image carte){
		this.jeu.add(carte);
		
	}
	
	public void retirer(Image carte){
		this.jeu.remove(carte);
	}
	
	public void retirerTout(){
		this.jeu.removeAll(jeu);
	}

	public void setCartesVisibles(Boolean visibles){
		this.cp.setCartesVisibles(visibles);
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