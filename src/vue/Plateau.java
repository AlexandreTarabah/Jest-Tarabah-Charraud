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

/**
 * Cette classe représente le plateau : C'est la que se déroule une partie.
 * Le plateau implemente Observer : C'est lui qui observe le déroulement du jeu 
 * Le plateau contient : 
 * <ul>
 * <li> sa partie game </li>
 * <li> Son controleur </li>
 * <li> Sa frame </li>
 * <li> son DrawDeck</li>
 * <li> Une arrayList de PlayerPanel </li>
 *</ul>
 * 
 *
 */
public class Plateau extends JPanel implements Observer{
	private static final long serialVersionUID = 1L;

	private Game game;
	private Controleur controleur;
	private JFrame frame;
	private PlayerPanel plp;
	private DrawDeckPanel deck;
	private ArrayList<PlayerPanel> pp = new ArrayList<PlayerPanel>();

/**
 * Le constructeur du Plateau qui initialise la fenetre du plateau 
 * @param p
 * @param c
 */
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
	
	/**
	 * Méthode qui permet d'afficher les joueurs sur le plateau 
	 * En fonction du nombre du joueur, on place les playerPanel sur l'écran 
	 *
	 * @param nbrJoueurs
	 */

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

/**
 * Cette Méthode permet d'afficher les Cartes du joueur : 
 * On fait correspondre le playerPanel avec le bon joueur et on affiche les cartes qu'ils possèdent dans sa main 
 * Pour cela, on utilise la méthode prendreCarte @see {@link PlayerPanel#prendreCarte(Image)} et verifierCarte @see {@link Plateau#verifierCarte(Card)}
 * @param joueur
 */
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

/**
 * Cette méthode permet d'associer une carte de la main du joueur a une image du drawdeck, selon les valeurs de la carte
 * On retourne ensuite l'image correspondante à la carte rentrée en paramètre 
 * Cette méthode est utilisée pour @see {@link Plateau#afficherCartes(Player)}
 * @param c
 * @return
 */
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


/**
 * Cette méthode permet d'actualiser la main du joueur qui se fait volé
 * En fonction des paramètres rentrés, on retire du jeu (Cartes affichés a l'écran) la carte correspondant au choix du joueur qui vole la carte.
 * Cette méthode est séparée en deux, selon si le @param joueur est un joueur réél ou un bot, car la variable choice est différente selon les objets.
 * @param joueur
 * @param choiceCardVictime
 * @param choiceVictime
 * @param g
 */

	public void actualiserStealCards(Player joueur, String choiceCardVictime,String choiceVictime,Game g) {
		
	if(joueur instanceof BotDown || joueur instanceof BotHard ) {
		ListIterator<PlayerPanel> iPj = this.pp.listIterator();
		while (iPj.hasNext()){
			PlayerPanel j = iPj.next();
			if (j.getNomJoueur().equals(joueur.getChoiceVictimeBot())){
				if(joueur.getStolenCard().equals("down")){
					j.getJeu().remove(1);
				}
				else {
					j.getJeu().remove(0);
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
			if (j.getNomJoueur().equals(joueur.getChoiceVictime())){
				if(choiceCardVictime.equals("down")) {
					j.getJeu().remove(1);
				}
				else {
					j.getJeu().remove(0);
				}
				this.revalidate();
				this.repaint();

				}
			}
		}
	}

/**
 * Cette méthode permet de retourner sur l'écran la carte choisie par le joueur : 
 * Pour cela, en fonction du paramètre @param reponseUD, on remplace la carte choisie par une image de Dos de carte. 
 * Si le @param joueur est un bot, alors on retourne la premiere carte car le bot choisie toujours la première carte à retourner
 * @param joueur
 * @param reponseUD
 */
	public void actualiserUpsideDown(Player joueur, int reponseUD) {
		if(joueur instanceof BotDown || joueur instanceof BotHard ) {
			ListIterator<PlayerPanel> iPj = this.pp.listIterator();
			while (iPj.hasNext()){
				PlayerPanel j = iPj.next();
				if (j.getNomJoueur().equals(joueur.getPseudo())){
					try {
						j.getJeu().remove(0);
						this.revalidate();
						this.repaint();
						j.getJeu().add(ImageIO.read(new File("img/Dos.jpg")));
					} catch (IOException e) {
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
				if (j.getNomJoueur().equals(joueur.getPseudo())){
					try {
						j.getJeu().remove(reponseUD);
						this.revalidate();
						this.repaint();
						j.getJeu().add(ImageIO.read(new File("img/Dos.jpg")));
					} catch (IOException e) {
						e.printStackTrace();
					}
					this.revalidate();
					this.repaint();
				}
			}
		}
	}




/**
 * Cette méthode permet de réinitialiser l'affichage des cartes sur le plateau 
 * On itère sur les PlayerPanel, et on vide les liste "jeu" pour qu'elles soient vides
 * @param joueur
 */


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
	/**
	 * Cette méthode permet de mettre à jour le plateau :
	 * en itérant sur la liste de joueur, on supprime le jeu via la méthode supprimerJeu @see {@link Plateau#supprimerJeu(Player)}
	 * ainsi que la méthode afficherCartes @see {@link Plateau#afficherCartes(Player)}
	 */

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


/**
 * Cette méthode permet à la fin de la partie de créé la fenêtre des scores et de l'afficher sur l'écran 
 * 
 */
	public void afficherScores()
	{
		this.frame.setVisible(false);

		Scores scores = new Scores(this.game);
		scores.setVisible(true);



	}

/**
 * permet d'initialiser le fond vert de l'écran 
 */

	public void paintComponent(Graphics g){
		try {
			Image img = ImageIO.read(new File("img/fond-grunge-vert.jpg"));
			g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
/**
 * Cette méthode permet d'ouvrir les boites de dialogue pour communiquer avec le joueur : 
 * On demande de rentrer le nom de la victime puis la carte a volé 
 * On appelle ensuite le contrôleur pour vérifier les valeurs et la méthode actualiser stealCards pour mettre à jour le plateau 
 * @param g
 * @param p
 */
	public void stealCards(Game g,Player p) {
		String choiceVictime = JOptionPane.showInputDialog(null,game.getIsPlaying().getPseudo() + " rentrez le nom de votre victime : ", "Input",JOptionPane.INFORMATION_MESSAGE);
		Object[] choixList = { "down", "up" };
		Object choixFait = JOptionPane.showInputDialog(null, "Quelle carte voulez-vous voler à " + choiceVictime + " ? " , "Input",JOptionPane.INFORMATION_MESSAGE, null,choixList, choixList[0]);
			String choiceCardVictime = choixFait.toString();
			controleur.methodeStealCard(choiceVictime,choiceCardVictime, game.getIsPlaying(),game);
			this.actualiserStealCards(game.getIsPlaying(),choiceCardVictime,game.getIsPlaying().getChoiceVictime(),game);
		

	}
	
	/**
	 * méthode update implémentée dans le pattern Observable/Observer
	 * en fonction de l'argument rentrée, on appelle la bonne méthode du plateau 
	 * Si l'argument est "upsideDown", alors on ouvre la boite de dialogue correspondant pour le choix des cartes 
	 */

	public void update(Observable o, Object arg) {

		if (arg == "joueurs")
		{
			this.afficherJoueurs(game.players.size());
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

		}

		if(arg=="stealCards") {

			this.stealCards(game,game.getIsPlaying());
		}
		
		if(arg=="actualiserStealCards") {
			this.actualiserStealCards(game.getIsPlaying(),null,game.getIsPlaying().getChoiceVictime(),game);
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