package vue;

import java.awt.*;
import java.util.LinkedList;

import javax.swing.*;

import modele.joueur.Player;

public class PlayerPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String nomJoueur;
	private Boolean virtual = true;
	private JLabel nomJoueurLabel;
	private CardPanel cd;
	private Font font = new Font("Courier", Font.BOLD, 20);
	
	private LinkedList<Image> jeu;
	
	public PlayerPanel(Player player){
		super();
		this.setOpaque(false);
		this.setLayout(new BorderLayout());
		this.setJeu(new LinkedList<Image>());
		
		this.cd = new CardPanel(jeu);
		this.add(cd);
		
		if (!player.isVirtual()){ // a compléter avec boolean isVirtual
			this.virtuel = false;
		}
		
		this.nomJoueur = player.getPseudo();
		this.nomJoueurLabel = new JLabel(nomJoueur);
		this.nomJoueurLabel.setFont(font);
		this.nomJoueurLabel.setForeground(Color.RED);
		this.add(nomJoueurLabel, BorderLayout.NORTH);
	}


	public void choisirCarteVolée(Image card){
		this.jeu.add(card);
	}
	
	public void choisirUpsideDown(Image card) {
		this.jeu.retourner(card); // créer la méthode retourner carte 
	}
	

	public void setCartesVisibles(Boolean visibles){
		this.cd.setCartesVisibles(visibles);
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

	public CardPanel getPc() {
		return cd;
	}

	public void setPc(CardPanel pc) {
		this.cd = pc;
	}
	
	public Boolean isVirtual() {
		return virtual;
	}

	public void setVirtuel(Boolean virtuel) {
		this.virtual = virtual;
	}
}

