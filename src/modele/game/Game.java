package modele.game;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import modele.carte.Card;
import modele.carte.*;
import modele.joueur.BotDown;
import modele.joueur.BotHard;
import modele.joueur.Player;
import modele.game.Count;
import modele.game.CountClassique;
import modele.game.CountInversion;
import modele.tas.DrawDeck;
import modele.tas.Jest;
import java.util.Map.Entry;
import javax.swing.JOptionPane;
import java.util.Observable;
import java.util.Observer;


/**
 * <p>
 * La classe Game représente la partie. En cela, elle est composée de joueurs (réels ou virtuels), de leurs 
 * cartes respectives, d'un drawDeck et de trophées. Ses méthodes lui permettent de séquencier le jeu correctement.
 * @see {@link Game#run()}
 * </p>
 * Elle est caractérisée par : 
 * <ul>
 * <li> nbPlayers, le nombre de joueurs réels et virtuels dans cette partie </li>
 * <li> nbBots, le nombre de joueurs virtuels dans cette partie </li>
 * <li> nbRealPlayers, le nombre de joueurs réels dans cette partie </li>
 * <li> difficulty, égale à 1 si "facile" 2 si "difficile </li>
 * <li> nbCardOffer, comptabilisant le nombre de cartes total faisant partie d'une offre </li>
 * <li> currentPlay, booléen vrai lorsque la partie a été initialisée, autorisant la distribution des cartes </li>
 * <li> extension, booléen vrai lorsque l'on veut jouer avec l'extension </li>
 * <li> variante, booléen vrai lorsque l'on veut jouer avec la variante </li>
 * <li> victime, un string prenant le pseudo de la "victime" ou le joueur dérobé</li>
 * <li> isPlaying, un attrbut de type Player instancié comme le joueur étant en train de jouer </li>
 * <li> trophyCards, un tableau de cartees contenant les cartes désignées comme Trophy pour la partie en cours </li>
 * <li> drawDeck, instancié comme un attribut DrawDeck représentant le drawDeck de la partie </li>
 * <li> scores, un tableau de deux dimensions prenant le pseudo (en colonne 1) 
 * et le nombre de points (en colonne 2) du joueur </li>
 * <li> players, une ArrayList des joueurs de la partie </li>
 * <li> scoresTransition, ArrayList contenant succesivement un pseudo de joueur et son nombre de 
 * points (casté en string) </li>
 * <li> lisOffer, une HashMap contenant le pseudo du joueur et son offre associée </li>
 * <li> winner, une HashMap contenant les pseudos et les nombres de points des Joueurs en fin de partie </li>
 * <li> ForMainPlay, une HashMap contenant les pseudos des joueurs et les joueurs </li>
 * </ul>
 */
public class Game extends Observable implements Runnable {


	public  int nbPlayers;
	protected  int nbBots;
	protected  int nbRealPlayers;
	protected int difficulty;
	public int nbCardOffer;
	boolean currentPlay;
	public boolean extension = false;
	boolean variante = false;

	private  String victime;


	private Player isPlaying;
	Card[] trophyCards = new Card[2] ;
	private DrawDeck drawdeck;
	public Object [][] scores;


	public ArrayList<Player> players = new ArrayList<Player>();

	public ArrayList<String> scoresTransition = new ArrayList<String>();

	private ArrayList<Observer> listObserver = new ArrayList<Observer>();

	public  HashMap<String, HashMap<String, Card>> listOffer= new HashMap<>();

	public HashMap<String,Integer> winner = new HashMap<String,Integer>();

	public HashMap<String,Player> ForMainPlay = new HashMap<String,Player>() ;

	/**
	 * Constructeur de Game
	 */
	public Game() {

	}

	/**
	 * permet de distribuer les cartes aux joueurs (2 par joueurs)
	 */
	public void distribute() {

		this.currentPlay=true;

		if (drawdeck.isEmpty() != true)
		{		


			
			{
				for (Iterator<Player> it = players.iterator(); it.hasNext();) 
				{
					Player p = (Player) it.next();
					for(int i=0; i<2;i++) {
						p.getHand().add(i, drawdeck.takeCards());
					}
					isPlaying=p;
					; 

				}
			}
		}
	}


	/**
	 * choisit aléatoirement deux cartes de la partie pour trophées (sauf si on choisit de jouer à 3 avec l'extension)
	 * @param g
	 */
	public void createTrophies(Game g) {
		if(extension==false) 
		{
			if(g.nbPlayers==3)
			{
				for(int i=0; i<2;i++) 
				{
					g.trophyCards[i] = g.drawdeck.takeCards();
				}
			}
			else if(g.nbPlayers==4)
			{
				trophyCards[0]=g.drawdeck.takeCards();
			}	
		}
		else if(extension==true) 
		{
			if(g.nbPlayers==4) 
			{
				trophyCards[0]=g.drawdeck.takeCards();
			}
		}

	}


	/**
	 * ramsse les cartes restantes, non volées, et les place dans le drawdeck
	 */
	public void mainCollectCards()
	{
		for(int i=0; i <players.size();i++)
		{
			drawdeck.collectCards(players.get(i));
		}
	}


	/**
	 * Détermine le joueur commencant le tour.
	 * On part du principe que dans le cas général le joueur volé devient victime. Ainsi, le statut de 
	 * "victime" est transmis de joueurs en joueurs se volant les uns les autres.
	 * De ce fait, on initialise le premier joueur en tant que victime.
	 * @return victime qui prend ici le pseudo du joueur commencant le tour 
	 */
	public String determinateFirstPlayer() { 
		int highestCardValue = 0;
		int highestColorValue = 0;
		for (Iterator<Player> it = this.players.iterator(); it.hasNext();) 
		{
			Player p = (Player) it.next();
			for(int i=0;i<2;i++) {
				if(highestCardValue <= p.getHand().get(i).getValue().getCardValue())
				{
					highestCardValue = p.getHand().get(i).getValue().getCardValue();
					highestColorValue = p.getHand().get(i).getColor().getColorValue();
					victime=(p.getPseudo());

				}

				if((highestCardValue == p.getHand().get(i).getValue().getCardValue()) && (highestColorValue <  p.getHand().get(i).getColor().getColorValue()))
				{
					highestColorValue =  p.getHand().get(i).getColor().getColorValue();
					victime=(p.getPseudo());
				}

			}
		}

		return victime;
	}



	/**
	 * comme son nom l'indique la méthode initialise les paramètres essentiels de la partie
	 * @param d
	 * @param nb
	 * @param nrp
	 * @param v
	 * @param e
	 */
	public void reglerParametres(int d, int nb, int nrp, boolean v, boolean e){
		this.difficulty = d;
		this.nbBots = nb;
		this.nbRealPlayers = nrp;
		this.variante = v ;
		this.extension = e ;

		this.nbPlayers = nb + nrp ;

		this.determinerNombreJoueurs();
	}


	/**
	 * comme son nom l'indique la méthode demande au joueur de choisir le nombre de joueurs virtuels et reels et cela
	 * via des boîtes de dialogue (JOptionPane)
	 */
	public void determinerNombreJoueurs(){
		if (this.difficulty==1) {
			for (int i=0;i<this.nbBots;i++){

				Player joueur = new BotDown(JOptionPane.showInputDialog(null, "Rentrez le pseudo du bot", "Nom du bot", JOptionPane.QUESTION_MESSAGE), this);


			}
		}else 
		{for(int i=0;i<this.nbBots;i++) {
			Player joueur = new BotHard(JOptionPane.showInputDialog(null, "Rentrez le pseudo du bot", "Nom du bot", JOptionPane.QUESTION_MESSAGE), this);

		}
		}

		for (int i=0;i<this.nbRealPlayers;i++){
			Player joueur = new Player(JOptionPane.showInputDialog(null, "Rentrez le pseudo du joueur", "Nom du joueur", JOptionPane.QUESTION_MESSAGE), this);


		}
		this.notifyObservers("joueurs");


	}


	/**
	 * détermine le joueur ayant le nombre de points le plus élevé de la partie et son
	 * nombre de points
	 * @return maxValueInMap soit une entrée contenant le joueur ayant le nombre de points le plus élevé de la partie et son
	 * nombre de points
	 */
	public Integer winnerDetermination() {

		int maxValueInMap=(Collections.max(winner.values()));  
		for (Entry<String, Integer> entry : winner.entrySet()) {  
			if (entry.getValue()==maxValueInMap) {
				maxValueInMap=entry.getValue();
			}

		}
		return maxValueInMap;
	}





	/**
	 * séquencie tous les tours de la partie en appelant succesivement : 
	 * <ul>
	 * <li>{@link Game#distribute()}</li>
	 * <li>{@link Game#upsideDown()}</li>
	 * <li>{@link Game#determinateFirstPlayer()}</li>
	 * <li>{@link Game#mainCollectCards()}</li>
	 * </ul>
	 * Régulièrement des notifications sont envoyées au JPanel plateau pour update la view.
	 */
	public void playRounds() 
	{

		int choice=0;
		String choiceVictime="";
		String choiceStolenCard="";

		this.notifyObservers("piles");


		while(this.drawdeck.getSize() != 0)
		{
			this.distribute(); 
			this.notifyObservers("actualiserPlateau");
			Iterator<Player> it = players.iterator();
			while(it.hasNext()) {
				Player p = it.next();
				isPlaying=p;

				if(p instanceof BotDown || p instanceof BotHard) {

					p.upsideDown(choice,this);
					this.notifyObservers("actualiserUpsideDown");

				}
				else
				{

					this.notifyObservers("upsideDown");


				}
			}



			this.determinateFirstPlayer();




			for(int j =0; j<nbPlayers;j++) {
				isPlaying=this.ForMainPlay.get(victime);
				if(this.ForMainPlay.get(victime) instanceof BotDown || this.ForMainPlay.get(victime) instanceof BotHard) {
					this.ForMainPlay.get(victime).stealCard(choiceVictime,choiceStolenCard, this);
					this.notifyObservers("actualiserStealCards");
				}else
					this.notifyObservers("stealCards");
			}



			for(int i=0; i<this.nbPlayers;i++) {
				players.get(i).HasStolen=false;
			}



			this.mainCollectCards();

			for(int i=0;i<this.nbPlayers;i++) {
				this.players.get(i).getHand().clear();
				this.players.get(i).getOffer().clear();
			}
		}
	}



	/**
	 * Attribue les trophées selon le design pattern visiteur entre jest et trophy.
	 * Selon les instances filles de Trophy dans le tableau TrophyCards les Trophées sont distribuées
	 * en respectant les conditions qui sont ^propres à leur type.
	 */
	public void giveTrophy() {
		ArrayList<Player> p = this.players ;
		Card[] t = this.trophyCards;

		if(t[0] != null && t[1] != null) {

			Comparator<Integer> valueComparator = new Comparator<Integer>() {
				@Override
				public int compare(Integer int1, Integer int2) {
					return int1.compareTo(int2);
				}
			};

			for(int j = 0 ; j < t.length ; j ++)
			{
				if(t[j].getTrophy() instanceof TrophyHighest) 
				{

					Map<Player,Integer> highCandidates = new HashMap<Player, Integer>();
					MapValueComparator<Player, Integer> mapComparator = new MapValueComparator<Player,Integer>(highCandidates, valueComparator);
					Map<Player, Integer> sortedHighCandidates = new TreeMap<Player, Integer>(mapComparator);
					String result = "" ;

					for(int i = 0 ; i < p.size() ; i ++)
					{
						Jest jest = p.get(i).getJest();
						jest.acceptTrophy(t[j].getTrophy());

						result = jest.winHighest(p.get(i), t[j], highCandidates, valueComparator, sortedHighCandidates) ;

					}

					(((TreeMap<Player, Integer>) sortedHighCandidates).lastKey()).getJest().jestCards.

					add(t[j]) ;

					System.out.println(result+ " et " + t[j]);

				}

				else if(t[j].getTrophy() instanceof TrophyLowest)
				{

					Map<Player,Integer> lowCandidates = new HashMap<Player, Integer>();
					MapValueComparator<Player, Integer> mapComparator = new MapValueComparator<Player,Integer>(lowCandidates, valueComparator);
					Map<Player, Integer> sortedLowCandidates = new TreeMap<Player, Integer>(mapComparator);
					String result = "" ;

					for(int i = 0 ; i < p.size() ; i ++)
					{
						Jest jest = p.get(i).getJest() ;
						jest.acceptTrophy(t[j].getTrophy()) ;

						result = jest.winLowest(p.get(i), t[j], lowCandidates, valueComparator, sortedLowCandidates) ;

					}

					(((TreeMap<Player, Integer>) sortedLowCandidates).firstKey()).getJest().jestCards.

					add(t[j]) ;

					System.out.println(result+ " et " + t[j]) ;

				}

				else if(t[j].getTrophy() instanceof TrophyMajority)
				{

					Map<Integer,Integer> majCandidates = new HashMap<Integer, Integer>();
					Map<Player,Entry<Integer, Integer>> majPlayer = new HashMap<Player, Entry<Integer, Integer>>();
					Map.Entry<Integer,Integer> myEntry = new AbstractMap.SimpleEntry<Integer, Integer>(0, 0);
					majPlayer.put(players.get(1), myEntry) ;
					String result = "" ;

					for(int i = 0 ; i < p.size() ; i ++)
					{
						Jest jest = p.get(i).getJest() ;
						jest.acceptTrophy(t[j].getTrophy()) ;

						result = jest.winMajority(p.get(i), t[j], majCandidates, majPlayer, myEntry) ;	

					}
					((Player) majPlayer.keySet().toArray()[0]).getJest().jestCards.add(t[j]);

					System.out.println(result+ " et " + t[j]) ;

				}

				else if(t[j].getTrophy() instanceof TrophyBestJest)
				{
					Map<Player,Integer> bestJestCandidates = new HashMap<Player, Integer>();
					Map<Player,Integer> bestJestCandidates1 = new HashMap<Player, Integer>();
					Map<Player,Entry<Player, Integer>> bestJestValue = new HashMap<Player, Entry<Player, Integer>>();
					Map<Player,Entry<Player, Integer>> bestJestColor = new HashMap<Player, Entry<Player, Integer>>();
					Map<Player, Integer> bestJestPlayer = new HashMap<Player, Integer>();
					Map.Entry<Player,Integer> myEntry = new AbstractMap.SimpleEntry<Player, Integer>(players.get(1), 0);
					bestJestValue.put(players.get(1), myEntry);
					bestJestColor.put(players.get(1), myEntry); 
					bestJestPlayer.put(players.get(1), 0);
					String result = "" ; 

					for(int i = 0 ; i < p.size() ; i ++) 
					{
						Jest jest = p.get(i).getJest() ;
						jest.acceptTrophy(t[j].getTrophy()) ;
						result = jest.winBestJest(p.get(i), t[j], bestJestCandidates, bestJestCandidates1, bestJestValue, bestJestColor, bestJestPlayer, myEntry) ;
					}

					((Player) bestJestValue.keySet().toArray()[0]).getJest().jestCards.add(t[j]) ;
					System.out.println(result+ " et " + t[j]) ;
				}

				else if(t[j].getTrophy() instanceof TrophyBestJestNoJoke)
				{
					Map<Player,Integer> bestJestCandidates = new HashMap<Player, Integer>();
					Map<Player,Integer> bestJestCandidates1 = new HashMap<Player, Integer>();
					Map<Player,Entry<Player, Integer>> bestJestValue = new HashMap<Player, Entry<Player, Integer>>();
					Map<Player,Entry<Player, Integer>> bestJestColor = new HashMap<Player, Entry<Player, Integer>>();
					Map<Player, Integer> bestJestPlayer = new HashMap<Player, Integer>();
					Map.Entry<Player,Integer> myEntry = new AbstractMap.SimpleEntry<Player, Integer>(players.get(1), 0);
					bestJestValue.put(players.get(1), myEntry) ;
					bestJestColor.put(players.get(1), myEntry) ; 
					bestJestPlayer.put(players.get(1), 1) ;
					String result = "" ; 

					int jokeDetecter = 0 ;

					for(int i = 0 ; i < p.size() ; i ++)
					{
						Jest jest = p.get(i).getJest() ;

						for (Iterator<Card> it = jest.jestCards.iterator() ; it.hasNext(); )
						{
							Card itg = it.next();

							if(itg instanceof Joker)
							{
								jokeDetecter += 1 ;

								break ;
							}
						}

						if(jokeDetecter == 1)
						{
							System.out.println(p.get(i).getPseudo() + " vous avez le Joker vous n'êtes pas éligible !") ;
						}

						else
						{
							jest.acceptTrophy(t[j].getTrophy()) ;

							result = jest.winBestJest(p.get(i), t[j], bestJestCandidates, 
									bestJestCandidates1, bestJestValue, bestJestColor, bestJestPlayer, myEntry) ;

						}

						jokeDetecter = 0 ;

					}

					((Player) bestJestValue.keySet().toArray()[0]).getJest().jestCards.add(t[j]) ;
					System.out.println(result + " et " + t[j]) ;

				}

				else if(t[j].getTrophy() instanceof TrophyJoker)
				{
					String result = "";

					for(int i = 0 ; i < p.size() ; i ++)
					{
						Jest jest = p.get(i).getJest() ;
						jest.acceptTrophy(t[j].getTrophy()) ;

						result = jest.winJoker(p.get(i), t[j]) ;

						System.out.println(result);

					}
				}

			}

		}

		System.out.println("\n") ;

	}



	/**
	 * Décompte les points des joueurs en respectant les règles (selon version : "Classique" ou "Red power !").
	 * Suite au décompte on instancie le tableau scores qui sera réutilisé par modele.vue.Scores pour créer la
	 * JTable affichant les scores.
	 */
	public void countPoints() {
		for (int i = 0 ; i < this.players.size(); i ++)
		{	
			if (this.variante == false)
			{
				Count count = new CountClassique() ;
				this.players.get(i).getJest().acceptCount(count, this.players.get(i),this, i);

			}
			else 
			{
				Count count = new CountInversion() ;
				this.players.get(i).getJest().acceptCount(count,this.players.get(i),this, i);

			}

		}

		if (nbPlayers == 4)
		{
			this.scores = new Object[][] {
				{scoresTransition.get(0), scoresTransition.get(1)},
				{scoresTransition.get(2), scoresTransition.get(3)},
				{scoresTransition.get(4), scoresTransition.get(5)},
				{scoresTransition.get(6), scoresTransition.get(7)},
			} ;

		}

		if (nbPlayers == 3)
		{
			this.scores = new Object[][] {
				{scoresTransition.get(0), scoresTransition.get(1)},
				{scoresTransition.get(2), scoresTransition.get(3)},
				{scoresTransition.get(4), scoresTransition.get(5)},
			} ;

		}

		this.notifyObservers("scores");

		System.out.println("\n") ;


	}




	/**
	 * <p>
	 * Permet de lancer la partie lorsque le thread est lancé par le {@link modele.controleur.Controleur#actionPerformed}.
	 * </p>
	 * Appelle successivement :
	 * <ul>
	 * <li> {@link Game#createTrophies} </li>
	 * <li> {@link Game#playRounds} </li>
	 * <li> {@link Game#giveTrophy} </li>
	 * <li> {@link Game#countPoints} </li>
	 * <li> {@link Game#winnerDetermination} </li>
	 * </ul>
	 */
	public void run() {

		this.listOffer = new HashMap<>();
		this.drawdeck = new DrawDeck(this);
		this.drawdeck.shuffle();


		this.createTrophies(this); 


		this.playRounds(); 

		this.giveTrophy();

		this.countPoints();


		this.winnerDetermination(); 	

	}


	public  int getNbPlayers() {
		
		return nbPlayers;
	}


	public boolean getExtension() {
		return extension;
	}


	public int getNbCardOffer() {
		return nbCardOffer;
	}

	public  HashMap<String, Integer> getWinner() {
		return winner;
	}

	public  Object[][] getScores() {
		return scores;
	}


	public String getVictime() {
		return victime;
	}



	public Player getIsPlaying() {
		return isPlaying;
	}



	public void setVictime(String choiceVictime) {
		this.victime=choiceVictime;

	}

	public HashMap<String, Player> getForMainPlay() {
		return ForMainPlay;
	}

	/**
	 *	Ajoute un observeur à la liste des observateurs de la partie (généralement des JPanel)
	 */
	public void addObserver(Observer obs) {
		this.listObserver.add(obs);
	}

	/**
	 *	Notifie un observeur / le met à jour
	 */
	
	public void notifyObservers(Object arg) {
		for (Observer obs : listObserver){
			obs.update(this, arg);
		}
	}

	/**
	 *	Supprime un observeur à la liste des observateurs de la partie (généralement des JPanel)
	 */
	
	public void deleteObserver(Observer o) {
		listObserver.remove(o);
	}

}