package fr.utt.lo02.uno.vue.panneaux;

import java.awt.*;
import java.util.LinkedList;

import javax.swing.*;

import fr.utt.lo02.uno.modele.joueur.Joueur;
import modele.joueur.Player;

public class PlayerPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String nomJoueur;
	private Boolean virtuel = true;
	private JLabel nomJoueurLabel;
	private PlayerPanel pp;
	private JButton uno;
	private Font font = new Font("Courier", Font.BOLD, 20);
	
	private Player jeu;
	
	public PlayerPanel(Player joueur){
		super();
		this.setOpaque(false);
		this.setLayout(new BorderLayout());
		this.setJeu(new LinkedList<Image>());
		
		this.pp = new PlayerPanel(jeu);
		this.add(pp);
		
		if (!joueur.isBot()){
			this.virtuel = false;
		}
		
		this.nomJoueur = joueur.getPseudo();
		this.nomJoueurLabel = new JLabel(nomJoueur);
		this.nomJoueurLabel.setFont(font);
		this.nomJoueurLabel.setForeground(Color.RED);
		this.add(nomJoueurLabel, BorderLayout.NORTH);
	}

	public JButton getUno() {
		return uno;
	}

	public void setUno(JButton uno) {
		this.uno = uno;
	}

	public void piocher(Image carte){
		this.jeu.add(carte);
	}
	
	public void retirer(Image carte){
		this.jeu.remove(carte);
	}
	
	public void retirerTout(){
		this.jeu.removeAll(jeu);
	}

	public void setCartesVisibles(Boolean visibles){
		this.pp.setCartesVisibles(visibles);
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

	public PlayerPanel getpp() {
		return pp;
	}

	public void setpp(PlayerPanel pp) {
		this.pp = pp;
	}
	
	public Boolean isVirtuel() {
		return virtuel;
	}

	public void setVirtuel(Boolean virtuel) {
		this.virtuel = virtuel;
	}
}

