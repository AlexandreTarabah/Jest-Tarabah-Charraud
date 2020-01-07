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


	public void afficherPiles(){
		this.deck = new DrawDeckPanel();
		this.frame.setContentPane(this);
	}

	public void afficherJoueurs(int nbrJoueurs){
		ListIterator<Player> iJoueurs = game.players.listIterator();
		while (iJoueurs.hasNext()){ 
			pp.add(new PlayerPanel(iJoueurs.next()));
		}
		if (nbrJoueurs == 3){
			pp.get(0).setBounds(100, 0, 500, 200);
			this.add(pp.get(0));
			pp.get(1).setBounds(1100, 0, 500, 200);
			this.add(pp.get(1));
			pp.get(2).setBounds(600, 500, 500, 200);
			this.add(pp.get(2));
		}
		if (nbrJoueurs == 4){
			pp.get(0).setBounds(100, 250, 500, 200);
			this.add(pp.get(0));
			pp.get(1).setBounds(600, 0, 500, 200);
			this.add(pp.get(1));
			pp.get(2).setBounds(1100, 250, 500, 200);
			this.add(pp.get(2));
			pp.get(3).setBounds(600, 500, 500, 200);
			this.add(pp.get(3));
		}
		this.frame.setContentPane(this);
	}


	public void afficherCartes(Player joueur){
		ListIterator<PlayerPanel> iPj = this.pp.listIterator();
		while (iPj.hasNext()){
			PlayerPanel j = iPj.next();
			if (j.getNomJoueur() == joueur.getPseudo()){
				j.getJeu().clear();
				ListIterator<Card> iCartes = joueur.getHand().listIterator();
				while (iCartes.hasNext()){
					j.prendreCarte(this.verifierCarte(iCartes.next()));
				}
			}
		}
	}


	public Image verifierCarte(Card c){

		Image carte = null;

		switch(c.getValue().getCardValue()){
		case 1:
			switch(c.getColor().getColorValue()){
			case 0:
				carte = deck.getCartes().get(0);
				break ;
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
				carte = deck.getCartes().get(13);
				break;
			}
			break;
		case 2:
			switch(c.getColor().getColorValue()){
			case 1:
				carte = deck.getCartes().get(2);
				break;
			case 2:
				carte = deck.getCartes().get(6);
				break;
			case 3:
				carte = deck.getCartes().get(10);
				break;
			case 4:
				carte = deck.getCartes().get(14);
				break;
			}
			break;
		case 3:
			switch(c.getColor().getColorValue()){
			case 1:
				carte = deck.getCartes().get(3);
				break;
			case 2:
				carte = deck.getCartes().get(7);
				break;
			case 3:
				carte = deck.getCartes().get(11);
				break;
			case 4:
				carte = deck.getCartes().get(15);
				break;
			}
			break;
		case 4:
			switch(c.getColor().getColorValue()){
			case 1:
				carte = deck.getCartes().get(4);
				break;
			case 2:
				carte = deck.getCartes().get(8);
				break;
			case 3:
				carte = deck.getCartes().get(12);
				break;
			case 4:
				carte = deck.getCartes().get(16);
				break;
			}
			break;
		case 6:
			switch(c.getColor().getColorValue())
			{
			case 1:
				carte = deck.getCartes().get(17);
				break;
			case 2:
				carte = deck.getCartes().get(18);
				break;
			case 3:
				carte = deck.getCartes().get(19);
				break;
			case 4:
				carte = deck.getCartes().get(20);
				break;
			}
			break ;
		}

		return carte;


	}



	public void actualiserStealCards(Player joueur, String choiceCardVictime) {

		if(joueur instanceof BotDown || joueur instanceof BotHard ) {
			ListIterator<PlayerPanel> iPj = this.pp.listIterator();
			while (iPj.hasNext()){
				PlayerPanel j = iPj.next();
				if (j.getNomJoueur() == joueur.getPseudo()){
					if(joueur.getStolenCard().equals("down")) {
						j.getJeu().remove(0);
					}
					else {
						j.getJeu().remove(1);
					}
					this.revalidate();
					this.repaint();
				}	
			}
		}
		else
		{
			ListIterator<PlayerPanel> iPj = this.pp.listIterator();
			while (iPj.hasNext()){
				PlayerPanel j = iPj.next();
				if (j.getNomJoueur() == joueur.getPseudo()){
					if(choiceCardVictime.equals("down")) {
						j.getJeu().remove(0);
					}
					else {
						j.getJeu().remove(1);
					}
					this.revalidate();
					this.repaint();
				}
			}
		}
	}


	public void actualiserUpsideDown(Player joueur, int reponseUD) {
		if(joueur instanceof BotDown || joueur instanceof BotHard ) {
			ListIterator<PlayerPanel> iPj = this.pp.listIterator();
			while (iPj.hasNext()){
				PlayerPanel j = iPj.next();
				if (j.getNomJoueur() == joueur.getPseudo()){
					try {
						j.getJeu().remove(0);
						this.revalidate();
						this.repaint();
						j.getJeu().add(ImageIO.read(new File("img/Dos.jpg")));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					this.revalidate();
					this.repaint();
				}	
			}
		}
		else
		{
			ListIterator<PlayerPanel> iPj = this.pp.listIterator();
			while (iPj.hasNext()){
				PlayerPanel j = iPj.next();
				if (j.getNomJoueur() == joueur.getPseudo()){
					try {
						j.getJeu().remove(reponseUD);
						this.revalidate();
						this.repaint();
						j.getJeu().add(ImageIO.read(new File("img/Dos.jpg")));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					this.revalidate();
					this.repaint();
				}
			}
		}
	}







	public void supprimerJeu(Player joueur){
		ListIterator<PlayerPanel> ipp = pp.listIterator();
		while (ipp.hasNext()){
			PlayerPanel pp = ipp.next();
			if (pp.getNomJoueur() == joueur.getPseudo()){
				pp.getJeu().removeAll(pp.getJeu());
				pp.revalidate();
			}
		}
	}

	public void actualiserPlateau()
	{
		ListIterator<Player> iJoueur = game.players.listIterator();
		while (iJoueur.hasNext()){
			Player j = iJoueur.next();
			this.supprimerJeu(j);
			this.afficherCartes(j);
			this.revalidate();
			this.repaint();

		}
	}



	public void afficherScores()
	{
		this.frame.setVisible(false);

		Scores scores = new Scores(this.game);
		scores.setVisible(true) ;



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
		String choiceVictime = JOptionPane.showInputDialog(null,game.getIsPlaying().getPseudo() + " rentrez le nom de votre victime : ", "Input",JOptionPane.INFORMATION_MESSAGE);
		Object[] choixList = { "down", "up" };
		Object choixFait = JOptionPane.showInputDialog(null, "Quelle carte voulez-vous voler à " + choiceVictime + " ? " , "Input",JOptionPane.INFORMATION_MESSAGE, null,choixList, choixList[0]);
		try {
			String choiceCardVictime = choixFait.toString();
			controleur.methodeStealCard(choiceVictime,choiceCardVictime, game.getIsPlaying(),game);
			this.actualiserStealCards(game.getIsPlaying(),choiceCardVictime);
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Veuillez rentrer le nom d'un joueur existant : ");
		}

	}

	public void update(Observable o, Object arg) {

		if (arg == "joueurs")
		{
			this.afficherJoueurs(game.players.size());
		}
		if (arg == "piles"){
			this.afficherPiles();
		}


		if(arg=="upsideDown") {
			Object[] action = {1,2};
			int reponseUD=JOptionPane.showOptionDialog(null, 
					"C'est le tour de " + game.getIsPlaying().getPseudo()+ " \n Quelle carte souhaitez-vous retourner ? ",
					"Faites une Offre ! ",
					JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE,
					null,
					action,
					action[1]);

			controleur.methodecontrolupsideDown(reponseUD,game.getIsPlaying());

			this.actualiserUpsideDown(game.getIsPlaying(), reponseUD);

			// this.actualiserMain(reponseUD);

		}

		if(arg=="stealCards") {

			this.stealCards(game,game.getIsPlaying());
		}
		//hm
		if(arg=="actualiserStealCards") {
			this.actualiserStealCards(game.getIsPlaying(),null);
		}

		if(arg == "actualiserUpsideDown") {
			this.actualiserUpsideDown(game.getIsPlaying(), 0);
		}

		if (arg == "actualiserPlateau"){
			this.actualiserPlateau();
		}

		if(arg=="scores") {
			this.afficherScores();
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
