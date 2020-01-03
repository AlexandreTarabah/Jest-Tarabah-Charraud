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
import vue.PlayerPanel;

import java.util.Map.Entry;
import java.util.NavigableMap;




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

public class Game {
	
	public static int nbPlayers;
	protected static int nbBots;
	protected static int nbRealPlayers;
	Card[] trophyCards = new Card[2] ;

	public static HashMap<String,Player> ForMainPlay = new HashMap<String,Player>() ;

	public static ArrayList<Player> players = new ArrayList<Player>();

	static HashMap<String, HashMap<String, Card>> listOffer= new HashMap<>();

	private DrawDeck drawdeck;

	boolean currentPlay;

	public boolean extension = true;

	public static HashMap<String,Integer> winner = new HashMap<String,Integer>();

	boolean variante = false;
	
// Liste de vérif pour les choix proposer a l'utilisateur : 
	
	public static ArrayList<Integer> choiceVar= new ArrayList<Integer>();

	ArrayList<Integer> choicePlayers= new ArrayList<Integer>();
	
	public static ArrayList<String> upsideChoice = new ArrayList<String>() ; 
	
	private  String victime;


	// La c'est la distribution des cartes, ou finalement j'invoque la méthode takecards et donc le joueur prend 2 cartes, et créé son offer

	public void distribute() {

		this.currentPlay=true;

		if (drawdeck.isEmpty() != true)
		{		


			for (int i=0 ; i < 2 ; i++) // Supposons qu'on distribue les cartes une à une
			{
				for (Iterator<Player> it = players.iterator(); it.hasNext();) 
				{
					Player p = (Player) it.next();
					p.setHand(i, drawdeck.takeCards()) ; // place une carte en position i dans la
					// main du joueur (qui est un tableau)


				}
			}
		}

		else

		{
			// insérer méthode de fin de jeu
		}



	}


	public static HashMap<String, Player> getForMainPlay() {
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



	public void initializeGame(Game g,Scanner input) {

		choiceVar.add(1);
		choiceVar.add(2);

		for(int i=0; i<5;i++) {
			choicePlayers.add(i);
		}
		upsideChoice.add("down");
		upsideChoice.add("up");


		System.out.println("Bonjour jeunes gens ! Voulez-faire une partie avec ou sans extension ? \n"
				+ "1 - Avec\n"
				+ "2 - Sans");
		int choice=0;
		while(choiceVar.contains(choice)==false) {
			choice = readInt(input,"Entrez un nombre compris entre 1 et 2 : ", "Non, Recommencez : ");
		}
		if(choice==2)
			{g.extension=false;} // if(choice==2) // On choisit si on joue avec ou sans extension, ce qui va impacter new DrawDeck(g)
		players = new ArrayList<Player>();
		listOffer = new HashMap<>();
		drawdeck = new DrawDeck(g);
		drawdeck.shuffle();
	}



	public void createTrophies(Game g) { // On instancie les trophées a partir du DrawDeckn en fonction des parametres 
		if(extension==false) 
		{
			if(nbPlayers==3)
			{
				for(int i=0; i<2;i++) 
				{
					g.trophyCards[i]= g.drawdeck.takeCards() ;
				}
			}
			else if(nbPlayers==4)
			{
				trophyCards[0]=g.drawdeck.takeCards() ;
			}	
		}
	}


	public void addPlayer(Player p, Scanner input) {
		if(currentPlay==false) {

			p.setPseudo(input);
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
		for (Iterator<Player> it = Game.players.iterator(); it.hasNext();) 
		{
			Player p = (Player) it.next();
			for(int i=0;i<2;i++) {
				if(highestCardValue <= p.getHand()[i].getValue().getCardValue())
				{
					highestCardValue = p.getHand()[i].getValue().getCardValue();
					highestColorValue = p.getHand()[i].getColor().getColorValue();
					victime=(p.getPseudo());

				}

				if((highestCardValue == p.getHand()[i].getValue().getCardValue()) && (highestColorValue <  p.getHand()[i].getColor().getColorValue()))
				{
					highestColorValue =  p.getHand()[i].getColor().getColorValue();
					victime=(p.getPseudo());
				}

			}



		}
		System.out.println(victime +" commence la partie");
		return victime;
	}


	public void configureGameplay(Scanner input) {

		System.out.println(" Combien voulez-vous de joueur rééls ?\n"
				+ "Vous avez le choix entre 0 - 1 - 2 - 3 - 4 joueurs rééls");
		int choiceNbPlayers=10;
		while(choicePlayers.contains(choiceNbPlayers)==false) {
			choiceNbPlayers = readInt(input,"Entrez un nombre compris entre 0 et 4 : ", "Non, Recommencez : ");
		}
		int k = 0;
		while(k<choiceNbPlayers) // instanciation des joueurs rééls
		{ 
			new Player(input);
			k++;
		}



		System.out.println("Combien voulez-vous de bot ?\n"
				+ "Vous pouvez choisir de jouer jusqu'a "+ (4-choiceNbPlayers)+" Bots"); // CHoix nombre de bots
		int choiceNbBot=10;
		while((choiceNbBot<0 ||choiceNbBot>(4-choiceNbPlayers))) {
			choiceNbBot = readInt(input,"Veuillez choisir un nombre pour compléter à 3 ou 4 joueurs", "Non, Recommencez : ");
		}



		System.out.println("Quelle difficulté de Bot ? \n"
				+ "Vous pouvez Choisir entre \n \n"
				+ " 1 - BotDown : bot Facile qui fait des choix randoms\n"
				+ " 2 - BotHard : bot assez difficile qui fera toujours le bon choix");
		int choiceDifficulty=0;
		while(choiceVar.contains(choiceDifficulty)==false) {
			choiceDifficulty = readInt(input,"Entrez un nombre compris entre 1 et 2 : ", "Non, Recommencez : ");
		}
		for(k=0;k<choiceNbBot;k++) // instanciation des bots 
		{
			if(choiceDifficulty==1) {
				new BotDown(input);}
			else
				new BotHard(input);
		}

		nbPlayers = choiceNbBot + choiceNbPlayers;




		System.out.println("Avec quelle variante voulez-vous jouer ?\n"
				+ "1 - Variante Classique \n"
				+ "2 - Variante inversion\n");
		int choicevar=0;
		while(choiceVar.contains(choicevar)==false) {
			choicevar = readInt(input,"Entrez un nombre compris entre 1 et 2 : ", "Non, Recommencez : ");
		}
		if(choicevar==2)
		{variante=true;}
	}




	public void winnerDetermination() {

		int maxValueInMap=(Collections.max(winner.values()));  // retourne la valeur max de la hashmap winner
		for (Entry<String, Integer> entry : winner.entrySet()) {  
			if (entry.getValue()==maxValueInMap) {
				System.out.println(entry.getKey() + " a gagné !" ); // détermine a quelle clé cela appartient pour afficher le gagnant 
			}

		}


	}

	public void playRounds() {
		Scanner input2 = new Scanner(System.in);
		
		while(this.drawdeck.getSize() != 0) // On repète le processus jusqu'a temps qu'on ait plu de carte
		{
			this.distribute(); // distribuer les cartes 

			// UPSIDE DOWN DE CHAQUE JOUEUR		
			Iterator<Player> it = players.iterator();
			while(it.hasNext()) {
				Player p = it.next();
				p.upsideDown(p, input2);
			}
			
			
			this.determinateFirstPlayer(); // on détermine le premier Joueur

			for(int j =0; j<nbPlayers;j++) {  // le reste suit selon la méthode stealCard(input)
				ForMainPlay.get(victime).stealCard(input2);	 // Les manip de chaque joueur pendant le tour 
			}

			for(int i=0; i<Game.nbPlayers;i++) {
				players.get(i).HasStolen=false;
			}

			this.mainCollectCards(); // On ramasse les cartes et on les rebalance dans le jeu pour recommencer 

		}
		
	}

	
	public void giveTrophy() {
		ArrayList<Player> p = Game.players ;
		Card[] t = this.trophyCards;

		if(t[0] != null && t[1] != null) { // Si y'a l'extension et 3 joueurs y'a pas de trophées ducoup on passe.

			Comparator<Integer> valueComparator = new Comparator<Integer>() {
				@Override
				public int compare(Integer int1, Integer int2) {
					return int1.compareTo(int2);
				}
			} ;

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
					bestJestValue.put(players.get(1), myEntry) ;
					bestJestColor.put(players.get(1), myEntry) ; 
					bestJestPlayer.put(players.get(1), 0) ;
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
		for (int i = 0 ; i < players.size() ; i ++)
		{	
			if (this.variante == false)
			{
				Count count = new CountClassique() ;
				Game.players.get(i).getJest().acceptCount(count, Game.players.get(i)) ;
			}
			else 
			{
				Count count = new CountInversion() ;
				Game.players.get(i).getJest().acceptCount(count,Game.players.get(i)) ;
			}
		}

		System.out.println("\n") ;
		
	}
	
	
	public void run() {

		
		Scanner input = new Scanner(System.in);
		
		this.initializeGame(this, input); 
		
		this.configureGameplay(input);
		
		
		this.createTrophies(this); //METTRE DANS MAIN JESTINTERFACE

		System.out.println(Arrays.deepToString(this.trophyCards) + "\n"); // Création 2 labels 

		this.playRounds(); 
		
		this.giveTrophy();
		
		this.countPoints();
		
		this.winnerDetermination() ; 

	}


	public static int getNbPlayers() {
		// TODO Auto-generated method stub
		return nbPlayers;
	}


	public boolean isExtension() {
		// TODO Auto-generated method stub
		return extension;
	}


	public static ArrayList<String> getUpsideChoice() {
		// TODO Auto-generated method stub
		return upsideChoice;
	}


	public static ArrayList<Integer> getChoiceVar() {
		// TODO Auto-generated method stub
		return choiceVar;
	}


	public static HashMap<String, Integer> getWinner() {
		// TODO Auto-generated method stub
		return winner;
	}


	public String getVictime() {
		// TODO Auto-generated method stub
		return victime;
	}


	public String getIsPlaying() {
		// TODO Auto-generated method stub
		return // le joueur entrain de jouer
	}


	
} // ARMAGEDDON 