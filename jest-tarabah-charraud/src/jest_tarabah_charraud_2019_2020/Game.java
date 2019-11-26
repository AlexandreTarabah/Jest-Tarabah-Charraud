package jest_tarabah_charraud_2019_2020;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
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
	protected static int nbPlayer;

	Card[] trophyCards = new Card[2] ;

	private String gameplay;

	static HashMap<String,Player> ForMainPlay = new HashMap<String,Player>() ;

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
				for(int i = 0 ; i<2 ; i++)
		{
			trophyCards[i] = drawdeck.takeCards() ;
		}
		 		currentPlay=false;   


	}



	public void chooseGameplay() {
	}


	protected void finalize() {
	}


	public void highestFUp() {
	}




	public void addPlayer(Player p, Scanner input) {
		if(currentPlay==false) {

			p.setPseudo(p, input);
			players.add(p);
			ForMainPlay.put(p.pseudo, p);


		}
	}




	//j'ai mis le main ici, je me suis dis que ca pourrait être bien de mettre le déroulement de la partie dans Game 



<<<<<<< HEAD
	public static void main(String[] args) 
	{

=======
	public static void main(String[] args) {
		
>>>>>>> branch 'master' of https://github.com/AlexandreTarabah/Jest-Tarabah-Charraud
		Game newGame = new Game();

		newGame.initializeGame(newGame); 

		Scanner input = new Scanner(System.in) ;

		Player p1 = new Player() ;
		Player p2 = new Player() ;
		Player p3 = new Player() ;

		newGame.addPlayer(p1, input) ;
		newGame.addPlayer(p2, input) ;
		newGame.addPlayer(p3, input) ; 


			newGame.distribute();



			p1.upsideDown(p1, input) ; 
			p2.upsideDown(p2, input) ;
			p3.upsideDown(p3, input) ;

			p1.determinateFirstPlayer( p1,p2,p3);

			
			ForMainPlay.get(Player.getStarter()).stealCard(input);

			System.out.println(ForMainPlay.get(Player.getVictime()).pseudo + " à vous de jouer\n ");
			ForMainPlay.get(Player.getVictime()).stealCard(input);

			System.out.println(ForMainPlay.get(Player.getVictime()).pseudo + " à vous de jouer\n ");
			ForMainPlay.get(Player.getVictime()).stealCard(input);

			drawdeck.collectCards(p1);
			drawdeck.collectCards(p2);
			drawdeck.collectCards(p3);
		

			System.out.println(Arrays.deepToString(newGame.trophyCards)) ;

			ArrayList<Player> p = Game.players ;
			Card[] t = newGame.trophyCards ;

			for(int j = 0 ; j < t.length ; j ++) // parcourt les trophies
			{
				if(t[j].getTrophy() instanceof TrophyHighest) // si c'est des trophyHighest
				{
					for(int i = 0 ; i < p.size() ; i ++) // parcourt les joueurs
					{
						Jest jest = p.get(i).getJest() ;
						jest.acceptVisitor(t[j].getTrophy()) ;
						if(t[j].getTrophy().highCandidate.getColor()
						  .equals(t[j].getTrophy().getColor()) == false)
						{
							System.out.println("Vous n'avez aucune carte de la même couleur !\n") ;
						}
						else
						{
							System.out.println("Voici votre carte de " + t[j].getTrophy().getColor() 
												+ " de plus grande valeur : "
												+ t[j].getTrophy().highCandidate.getValue() + " de " 
												+ t[j].getTrophy().highCandidate.getColor() + "\n") ;	
						}
						
					}
				}
				
				else if(t[j].getTrophy() instanceof TrophyLowest) // si c'est des trophyHighest
				{
					for(int i = 0 ; i < p.size() ; i ++) // parcourt les joueurs
					{
						Jest jest = p.get(i).getJest() ;
						jest.acceptVisitor(t[j].getTrophy()) ;
						if(t[j].getTrophy().lowCandidate.getColor()
						  .equals(t[j].getTrophy().getColor()) == false)
						{
							System.out.println("Vous n'avez aucune carte de la même couleur !\n") ;
						}
						else
						{
							System.out.println("Voici votre carte de " + t[j].getTrophy().getColor() 
									+ " de plus faible valeur : "
									+ t[j].getTrophy().lowCandidate.getValue() + " de " 
									+ t[j].getTrophy().lowCandidate.getColor() + "\n") ;	
						}
						
					}
				}
			}
			
			p1.getJest().countJest(p1);
			p2.getJest().countJest(p2);
			p3.getJest().countJest(p3);
			
			p1.getJest().calculateScore();

<<<<<<< HEAD
		}
=======

		

		} 

		 
>>>>>>> branch 'master' of https://github.com/AlexandreTarabah/Jest-Tarabah-Charraud
	}
<<<<<<< HEAD

}
=======
>>>>>>> branch 'master' of https://github.com/AlexandreTarabah/Jest-Tarabah-Charraud
