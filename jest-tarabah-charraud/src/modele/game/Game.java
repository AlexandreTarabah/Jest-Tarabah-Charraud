package modele.game;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

import modele.carte.Card;
import modele.carte.Joker;
import modele.carte.MapValueComparator;
import modele.carte.TrophyBestJest;
import modele.carte.TrophyBestJestNoJoke;
import modele.carte.TrophyHighest;
import modele.carte.TrophyJoker;
import modele.carte.TrophyLowest;
import modele.carte.TrophyMajority;
import modele.joueur.BotDown;
import modele.joueur.BotHard;
import modele.joueur.Player;
import modele.game.Count;
import modele.game.CountClassique;
import modele.game.CountInversion;
import modele.tas.DrawDeck;
import modele.tas.Jest;
import vue.FenetreSaisie;
import vue.PlayerPanel;

import java.util.Map.Entry;

import java.util.NavigableMap;
import java.util.Observable;
import java.util.Observer;




/*			 COMMENTAIRE 
 * ----------------------------------------------------------------------------------------------------------------
 Premiere version du jeu avec Bots + extension (Famille de 6)
 si extension=true {
 Si(nbPlayers=3)
 trophyCards=null;
 Si(nbPlayers=4)
 trophyCards=1;
 }

 création d'une méthode createTrophies() pour instancier les bons nombres de trophées selon les conditions préalables.

 le main ressemble maintenant a : 
 initialize Game(extension ou non)  
 chooseGamePlay(3 ou 4 joueurs/bots) 
 createTrophies; 
 et la boucle while pour faire tourner 

 Tu remarqueras un petit (t!=null) avant toute la partie trophée, puisque si on joue a 3 avec extension on a pas de trophées, ca évite
 les pointeurs null exceptions 

 Création d'une méthode readInt qui te fait tester jusqu'a temps que tu rentres un Int, ensuite vérification selon la demande des parametres réntrés.
 ------------------------------------------------------------------------------------------------------------------------------
 */
public class Game extends Observable implements Runnable {


	public  int nbPlayers;
	protected  int nbBots;
	protected  int nbRealPlayers;
	protected int difficulty;

	Player isPlaying;


	Card[] trophyCards = new Card[2] ;

	public HashMap<String,Player> ForMainPlay = new HashMap<String,Player>() ;

	public ArrayList<Player> players = new ArrayList<Player>();

	public  HashMap<String, HashMap<String, Card>> listOffer= new HashMap<>();

	private DrawDeck drawdeck;

	boolean currentPlay;

	public boolean extension = false;


	public HashMap<String,Integer> winner = new HashMap<String,Integer>();

	boolean variante = false;


	public static ArrayList<Integer> choiceVar= new ArrayList<Integer>();


	ArrayList<Integer> choicePlayers= new ArrayList<Integer>();

	public ArrayList<String> upsideChoice = new ArrayList<String>() ; 


	private  String victime;

	public Object [][] scores;

	private ArrayList<Observer> listObserver = new ArrayList<Observer>();

	// La c'est la distribution des cartes, ou finalement j'invoque la méthode takecards et donc le joueur prend 2 cartes, et créé son offer

	public Game() {

	}

	public void distribute() {

		this.currentPlay=true;

		if (drawdeck.isEmpty() != true)
		{		


			// Supposons qu'on distribue les cartes une à une
			{
				for (Iterator<Player> it = players.iterator(); it.hasNext();) 
				{
					Player p = (Player) it.next();
					for(Iterator<Card> it2 = p.getHand().iterator();it2.hasNext();) {
						p.getHand().add(drawdeck.takeCards());
					}
					; // place une carte en position i dans la
					// main du joueur (qui est un tableau)


				}
			}
		}

		else

		{
			// insérer méthode de fin de jeu
		}



	}


	public HashMap<String, Player> getForMainPlay() {
		return ForMainPlay;
	}

	public static int readInt(Scanner scanner, String prompt, String promptOnError) { // Methode qui permet de vérifier qu'on rentre bien un entier

		System.out.print(prompt);

		while ( !scanner.hasNextInt() ) {
			System.out.print(promptOnError);
			scanner.nextLine(); // vidage saisie incorrect
		}

		final int input = scanner.nextInt();
		scanner.nextLine(); // vidage buffer
		return input;

	}






	public void createTrophies(Game g) { // On instancie les trophées a partir du DrawDeckn en fonction des parametres 

		if(extension==false) 
		{
			if(g.nbPlayers==3)
			{
				for(int i=0; i<2;i++) 
				{
					g.trophyCards[i]= g.drawdeck.takeCards() ;
				}
			}
			else if(g.nbPlayers==4)
			{
				trophyCards[0]=g.drawdeck.takeCards();
			}	
		}
	}


	public void addPlayer(Player p, Scanner input) {
		if(currentPlay==false) {

			p.setPseudo(new FenetreSaisie());
			players.add(p);
			ForMainPlay.put(p.getPseudo(), p);


		}
	}


	public void mainCollectCards()
	{
		for(int i=0; i <players.size();i++)
		{
			drawdeck.collectCards(players.get(i)); // on rebalance les cartes restantes dans le drawdeck.
		}
	}


	public String determinateFirstPlayer() { // Code  pour comparer dans countJest, dans cette méthode, et dans stealCards
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


	public void reglerParametres(int d, int nb, int nrp){
		this.difficulty = d;
		this.nbBots = nb;
		this.nbRealPlayers = nrp;

		this.nbPlayers = nb + nrp ;

		this.determinerNombreJoueurs();
	}

	/**
	 * Cette méthode va créer les joueurs en conséquent des nombres de joueurs réels et virtuels voulu.
	 */
	public void determinerNombreJoueurs(){
		if (this.difficulty==1) {
			for (int i=0;i<this.nbBots;i++){

				Player joueur = new BotDown(Integer.toString(i), this);
				joueur.setPseudo(new FenetreSaisie()) ;

			}
		}else 
		{for(int i=0;i<this.nbBots;i++) {
			Player joueur = new BotHard(Integer.toString(i), this);
			joueur.setPseudo(new FenetreSaisie()) ;

		}
		}

		for (int i=0;i<this.nbRealPlayers;i++){
			Player joueur = new Player(Integer.toString(i), this);
			joueur.setPseudo(new FenetreSaisie()) ;

		}
		this.notifyObservers("joueurs");
	}




	public Integer winnerDetermination() {

		int maxValueInMap=(Collections.max(winner.values()));  // retourne la valeur max de la hashmap winner
		for (Entry<String, Integer> entry : winner.entrySet()) {  
			if (entry.getValue()==maxValueInMap) {
				maxValueInMap=entry.getValue(); // détermine a quelle clé cela appartient pour afficher le gagnant 
			}

		}
		return maxValueInMap;
	}

	public void playRounds() {

		int choice=0;


		String choiceVictime="";
		String choiceStolenCard="";


		while(this.drawdeck.getSize() != 0) // On repète le processus jusqu'a temps qu'on ait plu de carte
		{
			this.distribute(); // distribuer les cartes 

			// UPSIDE DOWN DE CHAQUE JOUEUR		
			Iterator<Player> it = players.iterator();
			while(it.hasNext()) {
				Player p = it.next();
				isPlaying=p;

				if(p instanceof BotDown || p instanceof BotHard) {
					p.upsideDown(choice);
				}
				else
				{
					this.notifyObservers("upsideDown");
				}
				this.notifyObservers("ActualiserMain");
			}
			this.notifyObservers("upsideDown");

		}this.notifyObservers("ActualiserMain");




		this.determinateFirstPlayer();
		this.notifyObservers("PremierJoueurCommence");// on détermine le premier Joueur



		this.determinateFirstPlayer(); // on détermine le premier Joueur


		for(int j =0; j<nbPlayers;j++) {
			isPlaying=this.ForMainPlay.get(victime);
			if(this.ForMainPlay.get(victime) instanceof BotDown || this.ForMainPlay.get(victime) instanceof BotHard) {// le reste suit selon la méthode stealCard(input)
				this.ForMainPlay.get(victime).stealCard(choiceVictime,choiceStolenCard, this);	 // Les manip de chaque joueur pendant le tour 
			}else
				this.notifyObservers("stealCards");
		}

		for(int i=0; i<this.nbPlayers;i++) {
			players.get(i).HasStolen=false;
		}

		this.mainCollectCards();
		this.notifyObservers("collectCards");// On ramasse les cartes et on les rebalance dans le jeu pour recommencer 

	}




	public void giveTrophy() {
		ArrayList<Player> p = this.players ;
		Card[] t = this.trophyCards;

		if(t[0] != null && t[1] != null) { // Si y'a l'extension et 3 joueurs y'a pas de trophées ducoup on passe.

			Comparator<Integer> valueComparator = new Comparator<Integer>() {
				@Override
				public int compare(Integer int1, Integer int2) {
					return int1.compareTo(int2);
				}
			};

			for(int j = 0 ; j < t.length ; j ++) // parcourt les trophies
			{
				if(t[j].getTrophy() instanceof TrophyHighest) // si c'est des trophyHighest
				{

					Map<Player,Integer> highCandidates = new HashMap<Player, Integer>();
					MapValueComparator<Player, Integer> mapComparator = new MapValueComparator<Player,Integer>(highCandidates, valueComparator);
					Map<Player, Integer> sortedHighCandidates = new TreeMap<Player, Integer>(mapComparator);
					String result = "" ;

					for(int i = 0 ; i < p.size() ; i ++) // parcourt les joueurs
					{
						Jest jest = p.get(i).getJest();
						jest.acceptTrophy(t[j].getTrophy());

						result = jest.winHighest(p.get(i), t[j], highCandidates, valueComparator, sortedHighCandidates) ;

					}

					(((TreeMap<Player, Integer>) sortedHighCandidates).lastKey()).getJest().jestCards.

					add(t[j]) ;

					System.out.println(result+ " et " + t[j]);

				}


				else if(t[j].getTrophy() instanceof TrophyLowest) // si c'est des trophyHighest
				{

					Map<Player,Integer> lowCandidates = new HashMap<Player, Integer>();
					MapValueComparator<Player, Integer> mapComparator = new MapValueComparator<Player,Integer>(lowCandidates, valueComparator);
					Map<Player, Integer> sortedLowCandidates = new TreeMap<Player, Integer>(mapComparator);
					String result = "" ;

					for(int i = 0 ; i < p.size() ; i ++) // parcourt les joueurs
					{
						Jest jest = p.get(i).getJest() ;
						jest.acceptTrophy(t[j].getTrophy()) ;

						result = jest.winLowest(p.get(i), t[j], lowCandidates, valueComparator, sortedLowCandidates) ;

					}

					(((TreeMap<Player, Integer>) sortedLowCandidates).firstKey()).getJest().jestCards.

					add(t[j]) ;

					System.out.println(result+ " et " + t[j]) ;

				}

				else if(t[j].getTrophy() instanceof TrophyMajority) // si c'est des trophyHighest
				{

					Map<Integer,Integer> majCandidates = new HashMap<Integer, Integer>();
					Map<Player,Entry<Integer, Integer>> majPlayer = new HashMap<Player, Entry<Integer, Integer>>();
					Map.Entry<Integer,Integer> myEntry = new AbstractMap.SimpleEntry<Integer, Integer>(0, 0);
					majPlayer.put(players.get(1), myEntry) ;
					String result = "" ;

					for(int i = 0 ; i < p.size() ; i ++) // parcourt les joueurs
					{
						Jest jest = p.get(i).getJest() ;
						jest.acceptTrophy(t[j].getTrophy()) ;

						result = jest.winMajority(p.get(i), t[j], majCandidates, majPlayer, myEntry) ;	

					}
					// Instruction ci dessous marche car on a qu un seul élément On est sur par la conversion en array de le retouver
					((Player) majPlayer.keySet().toArray()[0]).getJest().jestCards.add(t[j]);

					System.out.println(result+ " et " + t[j]) ;

				}

				else if(t[j].getTrophy() instanceof TrophyBestJest) // si c'est des trophyHighest
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

					for(int i = 0 ; i < p.size() ; i ++) // parcourt les joueurs
					{
						Jest jest = p.get(i).getJest() ;
						jest.acceptTrophy(t[j].getTrophy()) ;
						result = jest.winBestJest(p.get(i), t[j], bestJestCandidates, bestJestCandidates1, bestJestValue, bestJestColor, bestJestPlayer, myEntry) ;
					}

					((Player) bestJestValue.keySet().toArray()[0]).getJest().jestCards.add(t[j]) ;
					System.out.println(result+ " et " + t[j]) ;
				}

				else if(t[j].getTrophy() instanceof TrophyBestJestNoJoke) // si c'est des trophyHighest
				{
					Map<Player,Integer> bestJestCandidates = new HashMap<Player, Integer>();
					Map<Player,Integer> bestJestCandidates1 = new HashMap<Player, Integer>();
					Map<Player,Entry<Player, Integer>> bestJestValue = new HashMap<Player, Entry<Player, Integer>>();
					Map<Player,Entry<Player, Integer>> bestJestColor = new HashMap<Player, Entry<Player, Integer>>();
					Map<Player, Integer> bestJestPlayer = new HashMap<Player, Integer>();
					Map.Entry<Player,Integer> myEntry = new AbstractMap.SimpleEntry<Player, Integer>(players.get(1), 0);
					bestJestValue.put(players.get(1), myEntry) ;
					bestJestColor.put(players.get(1), myEntry) ; 
					String result = "" ; 

					int jokeDetecter = 0 ;

					for(int i = 0 ; i < p.size() ; i ++) // parcourt les joueurs
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
									bestJestCandidates1, bestJestValue, bestJestColor, bestJestCandidates1, myEntry) ;

						}

						jokeDetecter = 0 ;

					}

					((Player) bestJestValue.keySet().toArray()[0]).getJest().jestCards.add(t[j]) ;
					System.out.println(result + " et " + t[j]) ;

				}

				else if(t[j].getTrophy() instanceof TrophyJoker) // si c'est des trophyHighest
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



	public void countPoints() {
		for (int i = 0 ; i < this.players.size() ; i ++)
		{	
			if (this.variante == false)
			{
				Count count = new CountClassique() ;
				this.players.get(i).getJest().acceptCount(count, this.players.get(i),this);
			}
			else 
			{
				Count count = new CountInversion() ;
				this.players.get(i).getJest().acceptCount(count,this.players.get(i),this);
			}

			this.scores[i][0] = this.players.get(i).getPseudo() ;
			this.scores[i][1] = this.players.get(i).getNbPoint()  ;
		}

		System.out.println("\n") ;

	}


	public void run() {

		this.listOffer = new HashMap<>();
		this.drawdeck = new DrawDeck(this);
		this.drawdeck.shuffle();


		this.createTrophies(this); //METTRE DANS MAIN JESTINTERFACE


		this.playRounds(); 

		this.giveTrophy();

		this.countPoints();


		this.winnerDetermination(); 	



	}


	public  int getNbPlayers() {
		// TODO Auto-generated method stub
		return nbPlayers;
	}


	public boolean isExtension() {
		// TODO Auto-generated method stub
		return extension;
	}


	public ArrayList<String> getUpsideChoice() {
		// TODO Auto-generated method stub
		return upsideChoice;
	}


	public  ArrayList<Integer> getChoiceVar() {
		// TODO Auto-generated method stub
		return choiceVar;
	}



	public  HashMap<String, Integer> getWinner() {
		// TODO Auto-generated method stub
		return winner;
	}



	public String getVictime() {
		// TODO Auto-generated method stub
		return victime;
	}



	public Player getIsPlaying() {
		return isPlaying;
	}

	public void addObserver(Observer obs) {
		this.listObserver.add(obs);
	}

	public void notifyObservers(Object arg) {
		for (Observer obs : listObserver){
			obs.update(this, arg);
		}
	}

	public void deleteObserver(Observer o) {
		listObserver.remove(o);
	}

}
// ARMAGEDDON 
