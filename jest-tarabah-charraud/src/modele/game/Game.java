package modele.game;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import modele.joueur.BotDown;
import modele.joueur.BotHard;
import modele.tas.DrawDeck;
import modele.tas.Jest;
import modele.joueur.Player;
import modele.carte.Card;
import modele.carte.Joker;
import modele.carte.MapValueComparator;
import modele.carte.TrophyBestJest;
import modele.carte.TrophyBestJestNoJoke;
import modele.carte.TrophyHighest;
import modele.carte.TrophyJoker;
import modele.carte.TrophyLowest;
import modele.carte.TrophyMajority;

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
 }//

 cr�ation d'une m�thode createTrophies() pour instancier les bons nombres de troph�es selon les conditions pr�alables.

 le main ressemble maintenant a : 
 initialize Game(extension ou non)  
 chooseGamePlay(3 ou 4 joueurs/bots) 
 createTrophies; 
 et la boucle while pour faire tourner 

 Tu remarqueras un petit (t!=null) avant toute la partie troph�e, puisque si on joue a 3 avec extension on a pas de troph�es, ca �vite
 les pointeurs null exceptions 

 Cr�ation d'une m�thode readInt qui te fait tester jusqu'a temps que tu rentres un Int, ensuite v�rification selon la demande des parametres r�ntr�s.
 ------------------------------------------------------------------------------------------------------------------------------
 */

public class Game {
	
	private static int nbPlayers;
	protected static int nbBots;
	protected static int nbRealPlayers;
	Card[] trophyCards = new Card[2] ;

	static HashMap<String,Player> ForMainPlay = new HashMap<String,Player>() ;

	private static ArrayList<Player> players = new ArrayList<Player>();

	static HashMap<String, HashMap<String, Card>> listOffer= new HashMap<>();

	private DrawDeck drawdeck;

	boolean currentPlay;

	private boolean extension = true;

	private static HashMap<String,Integer> winner = new HashMap<String,Integer>();

	boolean variante = false;
	
// Liste de v�rif pour les choix proposer a l'utilisateur : 
	
	private static ArrayList<Integer> choiceVar= new ArrayList<Integer>();

	ArrayList<Integer> choicePlayers= new ArrayList<Integer>();
	
	private static ArrayList<String> upsideChoice = new ArrayList<String>(); 


	// La c'est la distribution des cartes, ou finalement j'invoque la m�thode takecards et donc le joueur prend 2 cartes, et cr�� son offer

	public void distribute() {

		this.currentPlay=true;

		if (drawdeck.isEmpty() != true)
		{		


			for (int i=0 ; i < 2 ; i++) // Supposons qu'on distribue les cartes une � une
			{
				for (Iterator<Player> it = getPlayers().iterator(); it.hasNext();) 
				{
					Player p = (Player) it.next();
					p.setHand(i, drawdeck.takeCards()) ; // place une carte en position i dans la
					// main du joueur (qui est un tableau)


				}
			}
		}

		else

		{
			// ins�rer m�thode de fin de jeu
		}



	}


	public static HashMap<String, Player> getForMainPlay() {
		return ForMainPlay;
	}

	public static int readInt(Scanner scanner, String prompt, String promptOnError) { // Methode qui permet de v�rifier qu'on rentre bien un entier

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

		getChoiceVar().add(1);
		getChoiceVar().add(2);

		for(int i=0; i<5;i++) {
			choicePlayers.add(i);
		}
		getUpsideChoice().add("down");
		getUpsideChoice().add("up");


		System.out.println("Bonjour jeunes gens ! Voulez-faire une partie avec ou sans extension ? \n"
				+ "1 - Avec\n"
				+ "2 - Sans");
		int choice=0;
		while(getChoiceVar().contains(choice)==false) {
			choice = readInt(input,"Entrez un nombre compris entre 1 et 2 : ", "Non, Recommencez : ");
		}
		if(choice==2)
			{g.setExtension(false);} // if(choice==2) // On choisit si on joue avec ou sans extension, ce qui va impacter new DrawDeck(g)
		setPlayers(new ArrayList<Player>());
		listOffer = new HashMap<>();
		drawdeck = new DrawDeck(g);
		drawdeck.shuffle();
	}



	public void createTrophies(Game g) { // On instancie les troph�es a partir du DrawDeckn en fonction des parametres 
		if(isExtension()==false) 
		{
			if(getNbPlayers()==3)
			{
				for(int i=0; i<2;i++) 
				{
					g.trophyCards[i]= g.drawdeck.takeCards() ;
				}
			}
			else if(getNbPlayers()==4)
			{
				trophyCards[0]=g.drawdeck.takeCards() ;
			}	
		}
	}


	public void addPlayer(Player p, Scanner input) {
		if(currentPlay==false) {

			p.setPseudo(input);
			getPlayers().add(p);
			ForMainPlay.put(p.getPseudo(), p);


		}
	}


	public void mainCollectCards()
	{
		for(int i=0; i <getPlayers().size();i++)
		{
			drawdeck.collectCards(getPlayers().get(i)); // on rebalance les cartes restantes dans le drawdeck.
		}
	}


	public void determinateFirstPlayer() { // Code  pour comparer dans countJest, dans cette m�thode, et dans stealCards
		int highestCardValue = 0;
		int highestColorValue = 0;
		for (Iterator<Player> it = Game.getPlayers().iterator(); it.hasNext();) 
		{
			Player p = (Player) it.next();
			for(int i=0;i<2;i++) {
				if(highestCardValue <= p.getHand()[i].getValue().getCardValue())
				{
					highestCardValue = p.getHand()[i].getValue().getCardValue();
					highestColorValue = p.getHand()[i].getColor().getColorValue();
					Player.setVictime(p.getPseudo());

				}

				if((highestCardValue == p.getHand()[i].getValue().getCardValue()) && (highestColorValue <  p.getHand()[i].getColor().getColorValue()))
				{
					highestColorValue =  p.getHand()[i].getColor().getColorValue();
					Player.setVictime(p.getPseudo());
				}

			}



		}
		System.out.println(Player.getVictime() +" commence la partie");
	}


	public void configureGameplay(Scanner input) {

		System.out.println(" Combien voulez-vous de joueur r��ls ?\n"
				+ "Vous avez le choix entre 0 - 1 - 2 - 3 - 4 joueurs r��ls");
		int choiceNbPlayers=10;
		while(choicePlayers.contains(choiceNbPlayers)==false) {
			choiceNbPlayers = readInt(input,"Entrez un nombre compris entre 0 et 4 : ", "Non, Recommencez : ");
		}
		int k = 0;
		while(k<choiceNbPlayers) // instanciation des joueurs r��ls
		{ 
			new Player(input);
			k++;
		}



		System.out.println("Combien voulez-vous de bot ?\n"
				+ "Vous pouvez choisir de jouer jusqu'a "+ (4-choiceNbPlayers)+" Bots"); // CHoix nombre de bots
		int choiceNbBot=10;
		while((choiceNbBot<0 ||choiceNbBot>(4-choiceNbPlayers))) {
			choiceNbBot = readInt(input,"Veuillez choisir un nombre pour compl�ter � 3 ou 4 joueurs", "Non, Recommencez : ");
		}



		System.out.println("Quelle difficult� de Bot ? \n"
				+ "Vous pouvez Choisir entre \n \n"
				+ " 1 - BotDown : bot Facile qui fait des choix randoms\n"
				+ " 2 - BotHard : bot assez difficile qui fera toujours le bon choix");
		int choiceDifficulty=0;
		while(getChoiceVar().contains(choiceDifficulty)==false) {
			choiceDifficulty = readInt(input,"Entrez un nombre compris entre 1 et 2 : ", "Non, Recommencez : ");
		}
		for(k=0;k<choiceNbBot;k++) // instanciation des bots 
		{
			if(choiceDifficulty==1) {
				new BotDown(input);}
			else
				new BotHard(input);
		}

		setNbPlayers(choiceNbBot + choiceNbPlayers);




		System.out.println("Avec quelle variante voulez-vous jouer ?\n"
				+ "1 - Variante Classique \n"
				+ "2 - Variante inversion\n");
		int choicevar=0;
		while(getChoiceVar().contains(choicevar)==false) {
			choicevar = readInt(input,"Entrez un nombre compris entre 1 et 2 : ", "Non, Recommencez : ");
		}
		if(choicevar==2)
		{variante=true;}
	}




	public void winnerDetermination() {

		int maxValueInMap=(Collections.max(getWinner().values()));  // retourne la valeur max de la hashmap winner
		for (Entry<String, Integer> entry : getWinner().entrySet()) {  
			if (entry.getValue()==maxValueInMap) {
				System.out.println(entry.getKey() + " a gagn� !" ); // d�termine a quelle cl� cela appartient pour afficher le gagnant 
			}

		}


	}



	public static void main(String[] args) {

		Game newGame = new Game();
		Scanner input = new Scanner(System.in) ;
		newGame.initializeGame(newGame, input); 
		newGame.configureGameplay(input);
		newGame.createTrophies(newGame);

		System.out.println(Arrays.deepToString(newGame.trophyCards) + "\n") ;

		while(newGame.drawdeck.getSize() != 0) // On repete le processus jusqu'a temps qu'on ait plu de carte
		{
			newGame.distribute(); // distribuer les cartes 

			// UPSIDE DOWN DE CHAQUE JOUEUR		
			Iterator<Player> it = getPlayers().iterator();
			while(it.hasNext()) {
				Player p = it.next();
				p.upsideDown(p, input);
			}


			newGame.determinateFirstPlayer(); // on d�termine le premier Joueur

			for(int j =0; j<getNbPlayers();j++) {  // le reste suit selon la m�thode stealCard(input)
				ForMainPlay.get(Player.getVictime()).stealCard(input);	 // Les manip de chaque joueur pendant le tour 
			}

			for(int i=0; i<Game.getNbPlayers();i++) {
				Game.getPlayers().get(i).setHasStolen(false);
			}

			newGame.mainCollectCards(); // On ramasse les cartes et on les rebalance dans le jeu pour recommencer 

		}

		ArrayList<Player> p = Game.getPlayers() ;
		Card[] t = newGame.trophyCards;

		if(t[0] != null && t[1] != null) { // Si y'a l'extension et 3 joueurs y'a pas de troph�es ducoup on passe.

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
						Jest jest = p.get(i).getJest() ;
						jest.acceptTrophy(t[j].getTrophy()) ;

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
					majPlayer.put(getPlayers().get(1), myEntry) ;
					String result = "" ;

					for(int i = 0 ; i < p.size() ; i ++) // parcourt les joueurs
					{
						Jest jest = p.get(i).getJest() ;
						jest.acceptTrophy(t[j].getTrophy()) ;

						result = jest.winMajority(p.get(i), t[j], majCandidates, majPlayer, myEntry) ;	

					}
					// Instruction ci dessous marche car on a qu un seul �l�ment On est sur par la conversion en array de le retouver
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
					Map.Entry<Player,Integer> myEntry = new AbstractMap.SimpleEntry<Player, Integer>(getPlayers().get(1), 0);
					bestJestValue.put(getPlayers().get(1), myEntry) ;
					bestJestColor.put(getPlayers().get(1), myEntry) ; 
					bestJestPlayer.put(getPlayers().get(1), 0) ;
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
					Map.Entry<Player,Integer> myEntry = new AbstractMap.SimpleEntry<Player, Integer>(getPlayers().get(1), 0);
					bestJestValue.put(getPlayers().get(1), myEntry) ;
					bestJestColor.put(getPlayers().get(1), myEntry) ; 
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
							System.out.println(p.get(i).getPseudo() + " vous avez le Joker vous n'�tes pas �ligible !") ;
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

		for (int i = 0 ; i < getPlayers().size() ; i ++)
		{	
			if (newGame.variante == false)
			{
				Count count = new CountClassique() ;
				p.get(i).getJest().acceptCount(count, p.get(i)) ;
			}
		}

		System.out.println("\n") ;
		
		newGame.winnerDetermination() ; 

	}


	public boolean isExtension() {
		return extension;
	}


	public void setExtension(boolean extension) {
		this.extension = extension;
	}


	public static ArrayList<Player> getPlayers() {
		return players;
	}


	public static void setPlayers(ArrayList<Player> players) {
		Game.players = players;
	}


	public static int getNbPlayers() {
		return nbPlayers;
	}


	public static void setNbPlayers(int nbPlayers) {
		Game.nbPlayers = nbPlayers;
	}


	public static ArrayList<String> getUpsideChoice() {
		return upsideChoice;
	}


	public static void setUpsideChoice(ArrayList<String> upsideChoice) {
		Game.upsideChoice = upsideChoice;
	}


	public static ArrayList<Integer> getChoiceVar() {
		return choiceVar;
	}


	public static void setChoiceVar(ArrayList<Integer> choiceVar) {
		Game.choiceVar = choiceVar;
	}


	public static HashMap<String,Integer> getWinner() {
		return winner;
	}


	public static void setWinner(HashMap<String,Integer> winner) {
		Game.winner = winner;
	}
} // ARMAGEDDON 