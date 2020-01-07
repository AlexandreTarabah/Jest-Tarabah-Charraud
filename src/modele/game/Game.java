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


public class Game extends Observable implements Runnable {


	public  int nbPlayers;
	protected  int nbBots;
	protected  int nbRealPlayers;
	protected int difficulty;
	public int nbCardOffer;
	boolean currentPlay;
	public boolean extension = false;
	boolean variante = false;


	private String[] tabPseudo;
	private  String victime;


	private Player isPlaying;
	Card[] trophyCards = new Card[2] ;
	private DrawDeck drawdeck;
	public Object [][] scores ;


	public ArrayList<Player> players = new ArrayList<Player>();

	public ArrayList<String> upsideChoice = new ArrayList<String>() ; 

	public ArrayList<String> scoresTransition = new ArrayList<String>();

	private ArrayList<Observer> listObserver = new ArrayList<Observer>();


	public  HashMap<String, HashMap<String, Card>> listOffer= new HashMap<>();

	public HashMap<String,Integer> winner = new HashMap<String,Integer>();

	public HashMap<String,Player> ForMainPlay = new HashMap<String,Player>() ;

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
					for(int i=0; i<2;i++) {
						p.getHand().add(i, drawdeck.takeCards());
					}
					isPlaying=p;
					; // place une carte en position i dans la
					// main du joueur (qui est un tableau)

				}
			}
		}
	}


	public void createTrophies(Game g) { // On instancie les trophées a partir du DrawDeckn en fonction des parametres 

		if(extension==false) 
		{
			if(g.nbPlayers==3)
			{
				for(int i=0; i<2;i++) 
				{
					g.trophyCards[i]= g.drawdeck.takeCards();
				}
			}
			else if(g.nbPlayers==4)
			{
				trophyCards[0]=g.drawdeck.takeCards();
			}	
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



	public void reglerParametres(int d, int nb, int nrp, boolean v, boolean e){
		this.difficulty = d;
		this.nbBots = nb;
		this.nbRealPlayers = nrp;
		this.variante = v ;
		this.extension = e ;

		this.nbPlayers = nb + nrp ;

		this.determinerNombreJoueurs();
	}


	public void determinerNombreJoueurs(){
		if (this.difficulty==1) {
			for (int i=0;i<this.nbBots;i++){

				Player joueur = new BotDown(JOptionPane.showInputDialog("rentrer le pseudo du bot"), this);


			}
		}else 
		{for(int i=0;i<this.nbBots;i++) {
			Player joueur = new BotHard(JOptionPane.showInputDialog("rentrer le pseudo du bot"), this);

		}
		}

		for (int i=0;i<this.nbRealPlayers;i++){
			Player joueur = new Player(JOptionPane.showInputDialog("rentrer le pseudo d'un joueur"), this);


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





	public void playRounds() 
	{
		this.notifyObservers("piles");



		int choice=0;
		String choiceVictime="";
		String choiceStolenCard="";




		while(this.drawdeck.getSize() != 0) // On repète le processus jusqu'a temps qu'on ait plu de carte
		{
			this.distribute(); // distribuer les cartes 
			// UPSIDE DOWN DE CHAQUE JOUEUR		
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
				if(this.ForMainPlay.get(victime) instanceof BotDown || this.ForMainPlay.get(victime) instanceof BotHard) {// le reste suit selon la méthode stealCard(input)
					this.ForMainPlay.get(victime).stealCard(choiceVictime,choiceStolenCard, this);
					this.notifyObservers("actualiserStealCards");// Les manip de chaque joueur pendant le tour 
				}else
					this.notifyObservers("stealCards");
			}



			for(int i=0; i<this.nbPlayers;i++) {
				players.get(i).HasStolen=false;
			}



			this.mainCollectCards();

			// On ramasse les cartes et on les rebalance dans le jeu pour recommencer 
			for(int i=0;i<this.nbPlayers;i++) {
				this.players.get(i).getHand().clear();
				this.players.get(i).getOffer().clear();
			}
		}
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
		// TODO Auto-generated method stub
		return nbPlayers;
	}


	public boolean getExtension() {
		// TODO Auto-generated method stub
		return extension;
	}


	public ArrayList<String> getUpsideChoice() {
		// TODO Auto-generated method stub
		return upsideChoice;
	}
	public int getNbCardOffer() {
		return nbCardOffer;
	}



	public  HashMap<String, Integer> getWinner() {
		// TODO Auto-generated method stub
		return winner;
	}

	public  Object[][] getScores() {
		// TODO Auto-generated method stub
		return scores;
	}


	public String getVictime() {
		// TODO Auto-generated method stub
		return victime;
	}



	public Player getIsPlaying() {
		return isPlaying;
	}



	public void setVictime(String choiceVictime) {
		this.victime=choiceVictime;

	}

	public Object[] getTabPseudo() {
		return tabPseudo;
	}

	public void setTabPseudo(String[] tabPseudo) {
		this.tabPseudo = tabPseudo;
	}

	public HashMap<String, Player> getForMainPlay() {
		return ForMainPlay;
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