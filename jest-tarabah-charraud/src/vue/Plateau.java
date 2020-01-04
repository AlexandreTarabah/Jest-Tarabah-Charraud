package vue;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import controleur.Controleur;
import modele.carte.*;
import modele.joueur.*;
import modele.game.Game;
import vue.*;

public class Plateau extends JPanel implements Observer{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Game partie;
	private Controleur controleur;

	private JFrame frame;
	private PlayerPanel pppoue;
	private DrawDeckPanel deck;
	private ArrayList<PlayerPanel> pp = new ArrayList<PlayerPanel>();

	public Plateau(Game p, Controleur c){
		super();

		this.partie = p;
		this.partie.addObserver(this);
		this.controleur = c;

		this.frame = new JFrame();
		this.frame.setTitle("JEST");
		this.frame.setSize(1500, 1000);
		this.frame.setLocationRelativeTo(null);               
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setLayout(null);

		this.frame.setContentPane(this);
	}

	public void afficherJeu(PlayerPanel pp){
		pp.setCartesVisibles(true);
	}

	public void afficherPiles(){
		this.deck = new DrawDeckPanel();
		deck.setBounds(600, 320, 300, 200);
		this.add(deck);
		this.frame.setContentPane(this);
	}

	public void afficherJoueurs(int nbrJoueurs){
		ListIterator<Player> iJoueurs = partie.players.listIterator();
		while (iJoueurs.hasNext()){
			pp.add(new PlayerPanel(iJoueurs.next()));
		}
		if (nbrJoueurs == 3){
			pp.get(0).setBounds(0, 0, 500, 200);
			this.add(pp.get(0));
			pp.get(1).setBounds(700, 0, 500, 200);
			this.add(pp.get(1));
			pp.get(2).setBounds(500, 550, 500, 200);
			this.add(pp.get(2));
		}
		if (nbrJoueurs == 4){
			pp.get(0).setBounds(0, 300, 500, 200);
			this.add(pp.get(0));
			pp.get(1).setBounds(500, 0, 500, 200);
			this.add(pp.get(1));
			pp.get(2).setBounds(1000, 300, 500, 200);
			this.add(pp.get(2));
			pp.get(3).setBounds(500, 550, 500, 200);
			this.add(pp.get(3));
		}
		this.frame.setContentPane(this);
	}

	public Image verifierCarte(Card c){
		Image carte = null;
		switch(c.getValue().getCardValue()){
		case 1:
			switch(c.getColor().getColorValue()){
			case 1:
				carte = deck.getCartes().get(1);
				break;
			case 2:
				carte = deck.getCartes().get(5);
				break;
			case 3:
				carte = deck.getCartes().get(9);
				break;
			case 4:
				carte = deck.getCartes().get(16);
				break;
			}
			break;
		case 2:
			switch(c.getColor().getColorValue()){
			case 1:
				carte = deck.getCartes().get(1);
				break;
			case 2:
				carte = deck.getCartes().get(5);
				break;
			case 3:
				carte = deck.getCartes().get(9);
				break;
			case 4:
				carte = deck.getCartes().get(16);
				break;
			}
			break;
		case 3:
			switch(c.getColor().getColorValue()){
			case 1:
				carte = deck.getCartes().get(1);
				break;
			case 2:
				carte = deck.getCartes().get(5);
				break;
			case 3:
				carte = deck.getCartes().get(9);
				break;
			case 4:
				carte = deck.getCartes().get(16);
				break;
			}
			break;
		case 4:
			switch(c.getColor().getColorValue()){
			case 1:
				carte = deck.getCartes().get(1);
				break;
			case 2:
				carte = deck.getCartes().get(5);
				break;
			case 3:
				carte = deck.getCartes().get(9);
				break;
			case 4:
				carte = deck.getCartes().get(16);
				break;
			}
			break;

		}

		return carte;


	}





	public void afficherCartes(Player joueur){
		ListIterator<PlayerPanel> ipp = this.pp.listIterator();
		while (ipp.hasNext()){
			PlayerPanel j = ipp.next();
			if (j.getNomJoueur() == joueur.getPseudo()){
				/*afficher carte comme dans le programme console*/
			}
		}
	}


	public void afficherJoueurCommence(){
		ListIterator<PlayerPanel> ipp = pp.listIterator();
		while (ipp.hasNext()){
			ipp.next().setCartesVisibles(true);
		}
		ListIterator<Player> iJoueurs = partie.players.listIterator();
		while (iJoueurs.hasNext()){
			this.afficherCartes(iJoueurs.next());
		}
		JOptionPane.showMessageDialog(null, partie.getVictime() + " commence cette manche !", "Qui Commence ?", JOptionPane.INFORMATION_MESSAGE);
		while (ipp.hasPrevious()){
			ipp.previous().retirerTout();
		}
		this.frame.setContentPane(this);
	}

	public void afficherDistribution(){
		ListIterator<PlayerPanel> ipp = pp.listIterator();
		while (ipp.hasNext()){
			ipp.next().setCartesVisibles(false);
		}
		ListIterator<Player> iJoueurs = partie.players.listIterator();
		while (iJoueurs.hasNext()){
			this.afficherCartes(iJoueurs.next());
		}
		this.frame.setContentPane(this);
	}


	public void supprimerJeu(Player joueur){
		ListIterator<PlayerPanel> ipp = pp.listIterator();
		while (ipp.hasNext()){
			PlayerPanel pp = ipp.next();
			if (pp.getNomJoueur() == joueur.getPseudo()){
				pp.getJeu().removeAll(pp.getJeu());
			}
		}
	}

	public void actualiserPlateau(){
		ListIterator<Player> iJoueur = partie.players.listIterator();
		while (iJoueur.hasNext()){
			Player j = iJoueur.next();
			this.supprimerJeu(j);
			this.afficherCartes(j);
		}
	}


	public void changerVisibiliteCartes(Boolean visibles){
		ListIterator<PlayerPanel> ipp = pp.listIterator();
		while (ipp.hasNext()){
			PlayerPanel pp = ipp.next();
			if (pp.getNomJoueur() == partie.getIsPlaying().getPseudo()){
				pp.setCartesVisibles(visibles);
			}
		}
		this.frame.setContentPane(this);
	}

	public void afficherScore(){

	}

	public void afficherNouvelleManche(){
		int nm = JOptionPane.showConfirmDialog(null, "Voulez-vous faire une autre manche ?", "Autre Manche ?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		controleur.nouvelleManche(nm);
	}

	public void paintComponent(Graphics g){
		try {
			Image img = ImageIO.read(new File("UNO_Plateau.jpg"));
			g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void stealCards(Game g) {

		String choiceVictime = JOptionPane.showInputDialog(null, 
				"choisissez votre victime", "le titre", JOptionPane.QUESTION_MESSAGE);

		String choiceCardVictime=JOptionPane.showInputDialog(null, 
				"choisissez la carte à volé", "le titre", JOptionPane.QUESTION_MESSAGE);


		controleur.methodeStealCard(choiceVictime,choiceCardVictime, null);

	}

	public void update(Observable o, Object arg) {
		if (arg == "joueurs"){
			this.afficherJoueurs(partie.players.size());
		}
		if (arg == "piles"){
			this.afficherPiles();
		}
		if (arg == "joueurcommence"){
			this.afficherJoueurCommence();
		}
		if (arg == "distribuer"){
			this.afficherDistribution();
		}
		if(arg=="upsideDown") {
			Object[] action = {1,2};
			int reponseUD=JOptionPane.showOptionDialog(null, 
					"C'est le tour de "+ partie.getIsPlaying().getPseudo()+ "\nQuelle carte retourner ?",
					"Action",
					JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE,
					null,
					action,
					action[1]);

			controleur.methodecontrolupsideDown(reponseUD,partie.getIsPlaying());

		}

		if(arg=="stealCards") {

			this.stealCards(partie);


		}

		if (arg == "déterminateFirstPlayer"){
			this.afficherJoueurCommence();
		}


		if (arg == "actualiserPlateau"){
			this.actualiserPlateau();
		}



	}


	public JFrame getFrame() {
		return frame;
	}

	public PlayerPanel getpppoue() {
		return getpppoue();
	}

	public void setpppoue(PlayerPanel pppoue) {
		this.pppoue = pppoue;
	}
}
