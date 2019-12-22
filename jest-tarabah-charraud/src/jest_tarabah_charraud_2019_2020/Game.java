package jest_tarabah_charraud_2019_2020;
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




/*			 COMMENTAIRE 
 * ----------------------------------------------------------------------------------------------------------------
 tu verras dans le débogguage, c'est le add(player) ligne 99 qui cause probleme, j'ai changé aussi 
 le nom de la classe gamer en Player, c'est plus mainipulable. 
 Je me suis permis de changer d'emplacement certaines méthodes, notamment de player et game

 Pour public void distribute j'ai appliqué le td jeu de bataille.
 initializeGame c'est JeudeCartes. 
 Dans player, j'ai mis des System.out.println pour essayer d'afficher les cartes des joueurs.

 Pour les cartes en elle-meme, j'ai bossé avec des enums Value et Color, j'ai donc rajouter les fichiers enum et j'ai 
 implementer des méthodes String toString() pour pouvoir afficher, mais je suis pas sur de devoir faire comme ca.

 Enjoy mon boug.



 			Fin Commentaire 
 ------------------------------------------------------------------------------------------------------------------------------
 */

public class Game {
	
	protected static int nbPlayers;


	Card[] trophyCards = new Card[2] ;

	private String gameplay;

	static HashMap<String,Player> ForMainPlay = new HashMap<String,Player>();

	static ArrayList<Player> players = new ArrayList<Player>();

	static HashMap<String, HashMap<String, Card>> listOffer= new HashMap<>();

	private static DrawDeck drawdeck;

	boolean currentPlay;





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




	public void initializeGame(Game g) {

		players = new ArrayList<Player>();
		listOffer = new HashMap<>();
		drawdeck = new DrawDeck(g);
		drawdeck.shuffle();
		/*	for(int i = 0 ; i<2 ; i++)

			/*	for(int i = 0 ; i<2 ; i++)

		{
			trophyCards[i] = drawdeck.takeCards() ;
		}
		 		currentPlay=false;   

		 */
	}



	public void chooseGameplay() {
	}


	protected void finalize() {
	}


	public void highestFUp() {
	}

	
	
	public void mainCollectCards()
	{
		for(int i=0; i <players.size();i++)
		 {
				drawdeck.collectCards(players.get(i)); // on rebalance les cartes restantes dans le drawdeck.
		 }
	}


	public void determinateFirstPlayer() { // Code  pour comparer dans countJest, dans cette méthode, et dans stealCards
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
					Player.starter = p.pseudo;
					
				}
				
				if((highestCardValue == p.hand[i].getValue().getCardValue()) && (highestColorValue <  p.hand[i].getColor().getColorValue()))
				{
					highestColorValue =  p.hand[i].getColor().getColorValue();
					Player.starter=p.pseudo;
				}
				
			}
			
			
			
	}
		System.out.println(Player.starter +"Commence la partie");
	}
	




	//j'ai mis le main ici, je me suis dis que ca pourrait être bien de mettre le déroulement de la partie dans Game 





	public static void main(String[] args) {

		Game newGame = new Game();

		newGame.initializeGame(newGame); 

		Scanner input = new Scanner(System.in) ;
// DEBUT DES MANOEUVRES PÖUR 3 ou 4 joueurs
		
		System.out.println("Bonjour jeunes gens ! combien voulez-vous de joueur ?");
		nbPlayers = input.nextInt() ;
	      int i = 0;
		while(i<nbPlayers) { 
	        new Player(input);
	         i++;
	    }
		
		
		while(newGame.drawdeck.getSize()>nbPlayers) // On repete le processus jusqu'a temps qu'on ait plu de carte
		{
			
newGame.distribute(); // distribuer les cartes 
		



// UPSIDE DOWN DE CHAQUE JOUEUR		
Iterator<Player> it = players.iterator();
while(it.hasNext()) {
	Player p = it.next();
	p.upsideDown(p, input);
}


newGame.determinateFirstPlayer(); // on détermine le premier Joueur




ForMainPlay.get(Player.getStarter()).stealCard(input); // le premier joueur Joue
		
		for(int j =1; j<nbPlayers;j++) {  // le reste suit selon la méthode stealCard(input)
			ForMainPlay.get(Player.getVictime()).stealCard(input);	 // Les manip de chaque joueur pendant le tour 
		}
		
		
		
newGame.mainCollectCards(); // On ramasse les cartes et on les rebalance dans le jeu pour recommencer 

}
 
/*
		System.out.println(Arrays.deepToString(newGame.trophyCards) + "\n") ;

		ArrayList<Player> p = Game.players ;
		Card[] t = newGame.trophyCards ;

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

				for(int i = 0 ; i < p.size() ; i ++) // parcourt les joueurs
				{
					Jest jest = p.get(i).getJest() ;
					jest.acceptVisitor(t[j].getTrophy()) ;

					if(t[j].getTrophy().highCandidate.getColor()
							.equals(t[j].getTrophy().getColor()) == false)
					{
						System.out.println("Vous n'avez aucune carte de la même couleur " + p.get(i).pseudo + " !\n") ;
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
				sortedHighCandidates.putAll(highCandidates) ;
				
				if(sortedHighCandidates != null)
				{
				(((TreeMap<Player, Integer>) sortedHighCandidates).lastKey()).getJest().jestCards.
				add(t[j]) ;
				


				System.out.println("Bravo Joueur " + ((TreeMap<Player, Integer>) sortedHighCandidates).lastKey().pseudo + 
						" vous avez la plus forte carte de " + t[j].getTrophy().getColor() 
						+ " vous remportez le Trophée ! Les cartes de votre Jest sont : " 
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
						System.out.println("Vous n'avez aucune carte de la même couleur " + p.get(i).pseudo + " !\n") ;
					}

					else
					{
						{
							System.out.println("Voici votre carte de " + t[j].getTrophy().getColor() 
									+ " de plus faible valeur " + p.get(i).pseudo + " : "
									+ t[j].getTrophy().lowCandidate.getValue() + " de " 
									+ t[j].getTrophy().lowCandidate.getColor() + "\n") ;
							lowCandidates.put(p.get(i), t[j].getTrophy().lowCandidate.getValue().ordinal()) ;
						}
					}
				}

				MapValueComparator<Player, Integer> mapComparator = new MapValueComparator<Player,Integer>(lowCandidates, valueComparator);
				Map<Player, Integer> sortedLowCandidates = new TreeMap<Player, Integer>(mapComparator);
				sortedLowCandidates.putAll(lowCandidates) ;

				if(sortedLowCandidates != null)
				{
				(((TreeMap<Player, Integer>) sortedLowCandidates).firstKey()).getJest().jestCards.
				add(t[j]) ;
				


				System.out.println("Bravo Joueur " + ((TreeMap<Player, Integer>) sortedLowCandidates).firstKey().pseudo + 
						" vous avez la plus faible carte de " + t[j].getTrophy().getColor() 
						+ " vous remportez le Trophée ! Les cartes de votre Jest sont : " 
						+ ((TreeMap<Player, Integer>) sortedLowCandidates).firstKey().getJest().jestCards + "\n" ) ;
				
				}


			}
		}

		p1.getJest().countJest(p1);
		p2.getJest().countJest(p2);
		p3.getJest().countJest(p3);


		p1.getJest().winnerDetermination();

*/
	}




}