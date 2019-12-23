package jest_tarabah_charraud_2019_2020;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
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
 
 cr�ation d'une m�thode createTrophies() pour instancier les bons nombres de troph�es selon les conditions pr�alables.
 
 le main ressemble maintenant a : 
 initialize Game(extension ou non)  
 chooseGamePlay(3 ou 4 joueurs/bots) 
 createTrophies; 
 et la boucle while pour faire tourner 
 
 Tu remarqueras un petit (t!=null) avant toute la partie troph�e, puisque si on joue a 3 avec extension on a pas de troph�es, ca �vite
 les pointeurs null exceptions 
 
 TEST AVEC TOUS LES PARAMETRES 
 ------------------------------------------------------------------------------------------------------------------------------
 */

public class Game {

	protected static int nbPlayers;
	protected static int nbBots;
	protected static int nbRealPlayers;
	Card[] trophyCards = new Card[2] ;

	static HashMap<String,Player> ForMainPlay = new HashMap<String,Player>() ;

	static ArrayList<Player> players = new ArrayList<Player>();

	static HashMap<String, HashMap<String, Card>> listOffer= new HashMap<>();

	private DrawDeck drawdeck;

	boolean currentPlay;
	
	 boolean extension=true;



	// La c'est la distribution des cartes, ou finalement j'invoque la m�thode takecards et donc le joueur prend 2 cartes, et cr�� son offer

	public void distribute() {

		this.currentPlay=true;

		if (drawdeck.isEmpty() != true)
		{		


			for (int i=0 ; i < 2 ; i++) // Supposons qu'on distribue les cartes une � une
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
			// ins�rer m�thode de fin de jeu
		}



	}


	public static HashMap<String, Player> getForMainPlay() {
		return ForMainPlay;
	}




	public void initializeGame(Game g,Scanner input) {
System.out.println("Bonjour jeunes gens ! Voulez-faire une partie avec ou sans extension ? \n"
		+ "1 - Avec\n"
		+ "2 - Sans");
int choix=input.nextInt();
if(choix==2) // On choisit si on joue avec ou sans extension, ce qui va impacter new DrawDeck(g)
	g.extension=false;

		players = new ArrayList<Player>();
		listOffer = new HashMap<>();
		drawdeck = new DrawDeck(g);
		drawdeck.shuffle();
	}


public void createTrophies(Game g) { // On instancie les troph�es a partir du DrawDeckn en fonction des parametres 
	if(extension=false) {
		if(nbPlayers==3)
		{if(extension=false)
			for(int i=0; i<2;i++) {
				g.trophyCards[i]=g.drawdeck.takeCards();
			}else 
				g.trophyCards=null;
		}else 
			if(nbPlayers==4)
			 {
				trophyCards[1]=g.drawdeck.takeCards();
			}		
	}
}


	public void addPlayer(Player p, Scanner input) {
		if(currentPlay==false) {

			p.setPseudo(input);
			players.add(p);
			ForMainPlay.put(p.pseudo, p);


		}
	}


	public void mainCollectCards()
	{
		for(int i=0; i <players.size();i++)
		{
			drawdeck.collectCards(players.get(i)); // on rebalance les cartes restantes dans le drawdeck.
		}
	}


	public void determinateFirstPlayer() { // Code  pour comparer dans countJest, dans cette m�thode, et dans stealCards
		int highestCardValue = 0;
		int highestColorValue = 0;
		for (Iterator<Player> it = Game.players.iterator(); it.hasNext();) 
		{
			Player p = (Player) it.next();
			for(int i=0;i<2;i++) {
				if(highestCardValue < p.hand[i].getValue().getCardValue())
				{
					highestCardValue = p.hand[i].getValue().getCardValue();
					highestColorValue = p.hand[i].getColor().getColorValue();
					Player.victime = p.pseudo;

				}

				if((highestCardValue == p.hand[i].getValue().getCardValue()) && (highestColorValue <  p.hand[i].getColor().getColorValue()))
				{
					highestColorValue =  p.hand[i].getColor().getColorValue();
					Player.victime=p.pseudo;
				}

			}



		}
		System.out.println(Player.victime +" commence la partie");
	}

	
	public void configureGameplay(Scanner input) {
		System.out.println(" Combien voulez-vous de joueur r��ls ?\n"
				+ "Vous avez le choix entre 0 - 1 - 2 - 3 - 4 joueurs r��ls");
		nbRealPlayers = input.nextInt();
		int k = 0;
		while(k<nbRealPlayers) // instanciation des joueurs r��ls
		{ 
			new Player(input);
			k++;
		}
		
		
		System.out.println("Combien voulez-vous de bot ?\n"
				+ "Vous pouvez choisir de jouer jusqu'a "+ (4-nbRealPlayers)+"Bots"); // CHoix nombre de bots
		nbBots=input.nextInt();
		System.out.println("quelle difficult� de Bot ? \n"
				+ "Vous pouvez Choisir entre \n \n"
				+ " 1 - BotDown : bot Facile qui fait des choix randoms\n"
				+ "2 -  BotHard : bot assez difficile qui fera toujours le bon choix");
		int choixBot = input.nextInt();
		for(k=0;k<nbBots;k++) // instanciation des bots 
		{
			if(choixBot==1) {
			new BotDown(input);}else
				new BotHard(input);
		}
		
		nbPlayers = nbBots + nbRealPlayers;
		
	}
	//j'ai mis le main ici, je me suis dis que ca pourrait �tre bien de mettre le d�roulement de la partie dans Game 


	




	public static void main(String[] args) {

		Game newGame = new Game();
		Scanner input = new Scanner(System.in) ;
		newGame.initializeGame(newGame, input); 
		newGame.configureGameplay(input);
		newGame.createTrophies(newGame);
		
	
		
		
		while(newGame.drawdeck.getSize()!=0) // On repete le processus jusqu'a temps qu'on ait plu de carte
		{

			newGame.distribute(); // distribuer les cartes 




			// UPSIDE DOWN DE CHAQUE JOUEUR		
			Iterator<Player> it = players.iterator();
			while(it.hasNext()) {
				Player p = it.next();
				p.upsideDown(p, input);
			}


			newGame.determinateFirstPlayer(); // on d�termine le premier Joueur





			for(int j =0; j<nbPlayers;j++) {  // le reste suit selon la m�thode stealCard(input)
				ForMainPlay.get(Player.getVictime()).stealCard(input);	 // Les manip de chaque joueur pendant le tour 
			}

			 for(int i=0; i<Game.nbPlayers;i++) {
				 Game.players.get(i).HasStolen=false;
			 }

			newGame.mainCollectCards(); // On ramasse les cartes et on les rebalance dans le jeu pour recommencer 

		}

		
		
		

		System.out.println(Arrays.deepToString(newGame.trophyCards) + "\n") ;

		ArrayList<Player> p = Game.players ;
		Card[] t = newGame.trophyCards;
		
if(t!=null) { // Si y'a l'extension et 3 joueurs y'a pas de troph�es ducoup on passe.
	
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

				for(int i = 0 ; i < p.size() ; i ++) // parcourt les joueurs
				{
					Jest jest = p.get(i).getJest() ;
					jest.acceptVisitor(t[j].getTrophy()) ;

					if(t[j].getTrophy().highCandidate.getColor()
							.equals(t[j].getTrophy().getColor()) == false)
					{
						System.out.println("Vous n'avez aucune carte de la m�me couleur " + p.get(i).pseudo + " !\n") ;
					}

					else
					{
						{
							System.out.println("Voici votre carte de " + t[j].getTrophy().getColor() 
									+ " de plus grande valeur " + p.get(i).pseudo + " : "
									+ t[j].getTrophy().highCandidate.getValue() + " de " 
									+ t[j].getTrophy().highCandidate.getColor() + "\n") ;
							highCandidates.put(p.get(i), t[j].getTrophy().highCandidate.getValue().ordinal()) ;

						}
					}
				}

				MapValueComparator<Player, Integer> mapComparator = new MapValueComparator<Player,Integer>(highCandidates, valueComparator);
				Map<Player, Integer> sortedHighCandidates = new TreeMap<Player, Integer>(mapComparator);
				sortedHighCandidates.putAll(highCandidates);

				if(sortedHighCandidates != null)
				{
					(((TreeMap<Player, Integer>) sortedHighCandidates).lastKey()).getJest().jestCards.
					add(t[j]);



					System.out.println("Bravo Joueur " + ((TreeMap<Player, Integer>) sortedHighCandidates).lastKey().pseudo + 
							" vous avez la plus forte carte de " + t[j].getTrophy().getColor() 
							+ " vous remportez le Troph�e ! Les cartes de votre Jest sont : " 
							+ ((TreeMap<Player, Integer>) sortedHighCandidates).lastKey().getJest().jestCards + "\n" ) ;

				}
			} 


			else if(t[j].getTrophy() instanceof TrophyLowest) // si c'est des trophyHighest
			{

				Map<Player,Integer> lowCandidates = new HashMap<Player, Integer>();

				for(int i = 0 ; i < p.size() ; i ++) // parcourt les joueurs
				{
					Jest jest = p.get(i).getJest() ;
					jest.acceptVisitor(t[j].getTrophy()) ;

					if(t[j].getTrophy().lowCandidate.getColor()
							.equals(t[j].getTrophy().getColor()) == false)
					{
						System.out.println("Vous n'avez aucune carte de la m�me couleur " + p.get(i).pseudo + " !\n") ;
					}

					else
					{
						System.out.println("Voici votre carte de " + t[j].getTrophy().getColor() 
								+ " de plus faible valeur " + p.get(i).pseudo + " : "
								+ t[j].getTrophy().lowCandidate.getValue() + " de " 
								+ t[j].getTrophy().lowCandidate.getColor() + "\n") ;
						lowCandidates.put(p.get(i), t[j].getTrophy().lowCandidate.getValue().ordinal()) ;

					}
				}


				MapValueComparator<Player, Integer> mapComparator = new MapValueComparator<Player,Integer>(lowCandidates, valueComparator);
				Map<Player, Integer> sortedLowCandidates = new TreeMap<Player, Integer>(mapComparator);
				sortedLowCandidates.putAll(lowCandidates) ;


				(((TreeMap<Player, Integer>) sortedLowCandidates).firstKey()).getJest().jestCards.
				add(t[j]) ;



				System.out.println("Bravo Joueur " + ((TreeMap<Player, Integer>) sortedLowCandidates).firstKey().pseudo + 
						" vous avez la plus faible carte de " + t[j].getTrophy().getColor() 
						+ " vous remportez le Troph�e ! Les cartes de votre Jest sont : " 
						+ ((TreeMap<Player, Integer>) sortedLowCandidates).firstKey().getJest().jestCards + "\n" ) ;



			}

			else if(t[j].getTrophy() instanceof TrophyMajority) // si c'est des trophyHighest
			{

				Map<Integer,Integer> majCandidates = new HashMap<Integer, Integer>();
				Map<Player,Entry<Integer, Integer>> majPlayer = new HashMap<Player, Entry<Integer, Integer>>();
				Map.Entry<Integer,Integer> myEntry = new AbstractMap.SimpleEntry<Integer, Integer>(0, 0);
				majPlayer.put(players.get(1), myEntry) ;

				for(int i = 0 ; i < p.size() ; i ++) // parcourt les joueurs
				{
					Jest jest = p.get(i).getJest() ;
					jest.acceptVisitor(t[j].getTrophy()) ;

					if(t[j].getTrophy().majCandidate == 0)
					{
						System.out.println("Vous n'avez aucune carte de la m�me valeur " + p.get(i).pseudo + " !\n") ;
					}

					else
					{
						System.out.println(p.get(i).pseudo + " voici votre nombre de " + t[j].getTrophy().getValue() 
								+ " : " + t[j].getTrophy().majCandidate + "\n") ;
						/**
						 * coeff le plus �lev� et nombre de cartes mis dans une liste
						 * */
						majCandidates.put(t[j].getTrophy().bigCoeff, t[j].getTrophy().majCandidate);

						Iterator<Entry<Integer, Integer>> itr = majCandidates.entrySet().iterator(); 

						while(itr.hasNext()) 
						{ 
							Entry<Integer, Integer> entry = itr.next();
							// parcourrons la map (contient un joueur) qui est candidat au trophy
							Iterator<Entry<Player, Entry<Integer, Integer>>> itrP = majPlayer.entrySet().iterator();

							while(itrP.hasNext())
							{
								Entry<Player, Entry<Integer, Integer>> entryP = itrP.next();

								if(entryP.getValue().getValue() < entry.getValue()) 
								{
									
									majPlayer.clear();
									majPlayer.put(p.get(i), entry) ;
									
								}
								else if(entryP.getValue().getValue() == entry.getValue())
								{
									if(entryP.getValue().getKey() < entry.getKey())
									{
										majPlayer.clear();
										majPlayer.put(p.get(i), entry) ;
									}
								}
							}

						}
					}
				}

				// Instruction ci dessous marche car on a qu un seul �l�ment On est sur par la conversion en array de le retouver
				((Player) majPlayer.keySet().toArray()[0]).getJest().jestCards.add(t[j]); 


				System.out.println("Bravo Joueur " + ((Player) majPlayer.keySet().toArray()[0]).pseudo + 
						" vous avez le plus grand nombre de " + t[j].getTrophy().getValue() 
						+ ". Vous remportez le Troph�e ! Les cartes de votre Jest sont : " 
						+ ((Player) majPlayer.keySet().toArray()[0]).getJest().jestCards + "\n" );


			}

			else if(t[j].getTrophy() instanceof TrophyBestJest) // si c'est des trophyHighest
			{
				Map<Integer,Integer> bestJestCandidates = new HashMap<Integer, Integer>();
				Map<Integer,Integer> bestJestCandidates1 = new HashMap<Integer, Integer>();
				Map<Player,Entry<Integer, Integer>> bestJestPlayer = new HashMap<Player, Entry<Integer, Integer>>();
				Map<Player,Entry<Integer, Integer>> bestJestColor = new HashMap<Player, Entry<Integer, Integer>>();
				Map.Entry<Integer,Integer> myEntry = new AbstractMap.SimpleEntry<Integer, Integer>(0, 0);
				bestJestPlayer.put(players.get(1), myEntry) ;
				bestJestColor.put(players.get(1), myEntry) ; ;

				for(int i = 0 ; i < p.size() ; i ++) // parcourt les joueurs
				{
					Jest jest = p.get(i).getJest() ;
					jest.acceptVisitor(t[j].getTrophy()) ;

					System.out.println(p.get(i).pseudo + " voici la valeur de votre jest : " +
							t[j].getTrophy().bestJestCandidate + "\n") ;
					/**
					 * coeff le plus �lev� et nombre de cartes mis dans une liste
					 * */

					bestJestCandidates.put(t[j].getTrophy().bigValue, t[j].getTrophy().bestJestCandidate) ;
					bestJestCandidates1.put(t[j].getTrophy().bigCoeff, t[j].getTrophy().bestJestCandidate) ;

					Iterator<Entry<Integer, Integer>> itr = bestJestCandidates.entrySet().iterator();
					Iterator<Entry<Integer, Integer>> itr1 = bestJestCandidates1.entrySet().iterator();

					while(itr.hasNext() && itr1.hasNext()) 
					{ 
						Entry<Integer, Integer> entry = itr.next();
						Entry<Integer, Integer> entry1 = itr1.next();
						// parcourrons la map (contient un joueur) qui est candidat au trophy
						Iterator<Entry<Player, Entry<Integer, Integer>>> itrP = bestJestPlayer.entrySet().iterator();
						Iterator<Entry<Player, Entry<Integer, Integer>>> itrC = bestJestColor.entrySet().iterator();

						while(itrP.hasNext() && itrC.hasNext())
						{
							Entry<Player, Entry<Integer, Integer>> entryP = itrP.next();
							Entry<Player, Entry<Integer, Integer>> entryC = itrC.next();

							if(entryP.getValue().getValue() < entry.getValue()) 
							{
								bestJestPlayer.clear();
								bestJestPlayer.put(p.get(i), entry) ;

								bestJestColor.clear();
								bestJestColor.put(p.get(i), entry1) ;
							}

							else if(entryP.getValue().getValue() == entry.getValue())
							{
								if(entryP.getValue().getKey() < entry.getValue())
								{
									bestJestPlayer.clear();
									bestJestPlayer.put(p.get(i), entry) ;

									bestJestColor.clear();
									bestJestColor.put(p.get(i), entry1) ;
								}

								else if(entryP.getValue().getKey() == entry.getValue())
								{
									if(entryC.getValue().getKey() < entry1.getValue())
									{
										bestJestPlayer.clear();
										bestJestPlayer.put(p.get(i), entry) ;

										bestJestColor.clear();
										bestJestColor.put(p.get(i), entry1) ;
									}
								}
							}
						}

					}
				}

				((Player) bestJestPlayer.keySet().toArray()[0]).getJest().jestCards.add(t[j]) ; 


				System.out.println("Bravo Joueur " + ((Player) bestJestPlayer.keySet().toArray()[0]).pseudo + 
						" vous avez le meilleur jest sans joker dont le total de points est " 
						+ ((Card) bestJestPlayer.values()).getValue()  
						+ ". Vous remportez le Troph�e ! Les cartes de votre Jest sont : " 
						+ ((Player) bestJestPlayer.keySet().toArray()[0]).getJest().jestCards + "\n" ) ;
			}

			else if(t[j].getTrophy() instanceof TrophyBestJestNoJoke) // si c'est des trophyHighest
			{
				Map<Integer,Integer> bestJestCandidates = new HashMap<Integer, Integer>();
				Map<Integer,Integer> bestJestCandidates1 = new HashMap<Integer, Integer>();
				Map<Player,Entry<Integer, Integer>> bestJestPlayer = new HashMap<Player, Entry<Integer, Integer>>();
				Map<Player,Entry<Integer, Integer>> bestJestColor = new HashMap<Player, Entry<Integer, Integer>>();
				Map.Entry<Integer,Integer> myEntry = new AbstractMap.SimpleEntry<Integer, Integer>(0, 0);
				bestJestPlayer.put(players.get(1), myEntry) ;
				bestJestColor.put(players.get(1), myEntry) ;

				int jokeDetecter = 0;
				

				Player bp = null ;

				for(int i = 0 ; i < p.size() ; i ++) // parcourt les joueurs
				{
					Jest jest = p.get(i).getJest() ;

					bp = p.get(i) ; // for bestPlayer

					for (Iterator<Card> it = jest.jestCards.iterator() ; it.hasNext(); )
					{
						Card itg = it.next();

						if(itg instanceof Joker)
						{
							jokeDetecter=1;
							break ;
						}
					}

					if(jokeDetecter == 1)
					{
						System.out.println(p.get(i).pseudo + " vous avez le Joker vous n'�tes pas �ligible !") ;
					}

					else
					{
						jest.acceptVisitor(t[j].getTrophy()) ;

						System.out.println(p.get(i).pseudo + " voici la valeur de votre jest : " +
								t[j].getTrophy().bestJestCandidate + "\n") ;
						/**
						 * coeff le plus �lev� et nombre de cartes mis dans une liste
						 * */

						bestJestCandidates.put(t[j].getTrophy().bigValue, t[j].getTrophy().bestJestCandidate) ;
						bestJestCandidates1.put(t[j].getTrophy().bigCoeff, t[j].getTrophy().bestJestCandidate) ;

						Iterator<Entry<Integer, Integer>> itr = bestJestCandidates.entrySet().iterator();
						Iterator<Entry<Integer, Integer>> itr1 = bestJestCandidates1.entrySet().iterator();

						while(itr.hasNext() && itr1.hasNext()) 
						{ 
							Entry<Integer, Integer> entry = itr.next();
							Entry<Integer, Integer> entry1 = itr1.next();
							// parcourrons la map (contient un joueur) qui est candidat au trophy
							Iterator<Entry<Player, Entry<Integer, Integer>>> itrP = bestJestPlayer.entrySet().iterator();
							Iterator<Entry<Player, Entry<Integer, Integer>>> itrC = bestJestColor.entrySet().iterator();

							while(itrP.hasNext() && itrC.hasNext())
							{
								Entry<Player, Entry<Integer, Integer>> entryP = itrP.next();
								Entry<Player, Entry<Integer, Integer>> entryC = itrC.next();

								if(entryP.getValue().getValue() < entry.getValue()) 
								{
									bestJestPlayer.clear();
									bestJestPlayer.put(p.get(i), entry) ;

									bestJestColor.clear();
									bestJestColor.put(p.get(i), entry1) ;
								}

								else if(entryP.getValue().getValue() == entry.getValue())
								{
									if(entryP.getValue().getKey() < entry.getKey())
									{
										bestJestPlayer.clear();
										bestJestPlayer.put(p.get(i), entry) ;

										bestJestColor.clear();
										bestJestColor.put(p.get(i), entry1) ;
									}

									else if(entryP.getValue().getKey() == entry.getKey())
									{
										if(entryC.getValue().getKey() < entry1.getKey())
										{
											bestJestPlayer.clear();
											bestJestPlayer.put(p.get(i), entry) ;

											bestJestColor.clear();
											bestJestColor.put(p.get(i), entry1) ;

										}
									}
								}
							}

						}


					}

				}

				((Player) bestJestPlayer.keySet().toArray()[0]).getJest().jestCards.add(t[j]) ; 


				System.out.println("Bravo Joueur " + ((Player) bestJestPlayer.keySet().toArray()[0]).pseudo + 
						" vous avez le meilleur jest dont le total de points est " 
						+ bestJestPlayer.get(bp).getValue()
						+ ". Vous remportez le Troph�e ! Les cartes de votre Jest sont : " 
						+ ((Player) bestJestPlayer.keySet().toArray()[0]).getJest().jestCards + "\n" ) ;
			}

			else if(t[j].getTrophy() instanceof TrophyJoker) // si c'est des trophyHighest
			{

				for(int i = 0 ; i < p.size() ; i ++)
				{
					Jest jest = p.get(i).getJest() ;
					jest.acceptVisitor(t[j].getTrophy()) ;

					if(t[j].getTrophy().jokerCandidate == 1)
					{
						p.get(i).getJest().jestCards.add(t[j]) ; 

						System.out.println("Bravo Joueur " + p.get(i).pseudo + 
								" vous avez le Joker ! "
								+ "Vous remportez le Troph�e ! Les cartes de votre Jest sont : " 
								+ p.get(i).getJest().jestCards + "\n" ) ;

						break ;
					}

					else
					{
						System.out.println("Personne n'a le Joker !") ;
						}

					}
				}

			}

		}
	}
} // ARMAGEDDON DE SES MORTS
