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

	private Game game;
	private Controleur controleur;

	private JFrame frame;
	private PlayerPanel plp;
	private DrawDeckPanel deck;
	private ArrayList<PlayerPanel> pp = new ArrayList<PlayerPanel>();

	public Plateau(Game p, Controleur c){
		super();

		this.game = p;
		this.game.addObserver(this);
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
		ListIterator<Player> iJoueurs = game.players.listIterator();
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


	public void afficherCartes(Player joueur){
		ListIterator<PlayerPanel> iPj = this.pp.listIterator();
		while (iPj.hasNext()){
			PlayerPanel j = iPj.next(); 
			if (j.getNomJoueur() == joueur.getPseudo()){
				ListIterator<Card> iCartes = joueur.getHand().listIterator();
				while (iCartes.hasNext()){
					j.prendreCarte(this.verifierCarte(iCartes.next()));

				}
				j.setCp(j.getJeu()); // ci-dessous code pour cartes dans fenetre externe
				/*JFrame votreMain = new JFrame("Votre Main joueur " + joueur.getPseudo());
				votreMain.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);*/
				this.frame.setContentPane(j.getCp()) ;
				this.frame.setVisible(true);
				/* votreMain.setSize(300, 200);
				votreMain.setVisible(true); */
			}
		}
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



	public void actualiserMain(int reponseUD) {
		ListIterator<PlayerPanel> ipp = pp.listIterator();
		while (ipp.hasNext()){
			ipp.next().getJeu().get(reponseUD).getGraphics().drawRect(getX(), getY(), getWidth(), getHeight());
		}

	}




	public void afficherJoueurCommence(){
		ListIterator<PlayerPanel> ipp = pp.listIterator();
		while (ipp.hasNext()){
			ipp.next().setCartesVisibles(true);
		}
		ListIterator<Player> iJoueurs = game.players.listIterator();
		while (iJoueurs.hasNext()){
			this.afficherCartes(iJoueurs.next());
		}
		JOptionPane.showMessageDialog(null, game.getVictime() + " commence cette manche !", "Qui Commence ?", JOptionPane.INFORMATION_MESSAGE);
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
		ListIterator<Player> iJoueurs = game.players.listIterator();
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
		ListIterator<Player> iJoueur = game.players.listIterator();
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
			if (pp.getNomJoueur() == game.getIsPlaying().getPseudo()){
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
			Image img = ImageIO.read(new File("img/fond-grunge-vert.jpg"));
			g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void stealCards(Game g,Player p) {
		String choiceVictime = JOptionPane.showInputDialog(null, "choisissez votre victime"+game.getIsPlaying().getPseudo(), "Input",JOptionPane.INFORMATION_MESSAGE);
		Object[] choixList = { "down", "up" };
		Object choixFait = JOptionPane.showInputDialog(null, "Choisissez la carte a volé", "Input",JOptionPane.INFORMATION_MESSAGE, null,choixList, choixList[0]);
		String choiceCardVictime = choixFait.toString();


		controleur.methodeStealCard(choiceVictime,choiceCardVictime, game.getIsPlaying());

	}

	public void update(Observable o, Object arg) {

		if (arg == "joueurs")
		{
			this.afficherJoueurs(game.players.size());
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
					"C'est le tour de "+ game.getIsPlaying().getPseudo()+ "\nQuelle carte retourner ?",
					"Action",
					JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE,
					null,
					action,
					action[1]);

			controleur.methodecontrolupsideDown(reponseUD,game.getIsPlaying());

			// this.actualiserMain(reponseUD);

		}

		if(arg=="stealCards") {

			this.stealCards(game,game.getIsPlaying());


		}

		if (arg == "determinateFirstPlayer"){
			this.afficherJoueurCommence();
		}


		if (arg == "actualiserPlateau"){
			this.actualiserPlateau();
		}

		if(arg=="afficherCartes") {
			this.afficherCartes(game.getIsPlaying());
		}



	}



	public JFrame getFrame() {
		return frame;
	}

	public PlayerPanel getplp() {
		return plp;
	}

	public void setplp(PlayerPanel plp) {
		this.plp = plp;
	}
}
