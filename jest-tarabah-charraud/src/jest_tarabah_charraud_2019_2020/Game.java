package jest_tarabah_charraud_2019_2020;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;




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

	private Card[] trophies = new Card[2] ;

	private String gameplay;

	private ArrayList<Player> players = new ArrayList<Player>() ;

	private DrawDeck drawdeck;

	boolean currentPlay;





	public void determinateWinner() {
	}


	// La c'est la distribution des cartes, ou finalement j'invoque la méthode takecards et donc le joueur prend 2 cartes, et créé son offer

	public void distribute() {

		this.currentPlay=true;

		if (drawdeck.isEmpty() != false)
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





	public void initializeGame() {

		players = new ArrayList<Player>();
		drawdeck = new DrawDeck();
		drawdeck.shuffle();
		for(int i = 0 ; i<2 ; i++)
		{
			this.trophies[i] = drawdeck.takeCards() ;
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
			players.add(p);
			p.setPseudo(input);
		}
	}




	//j'ai mis le main ici, je me suis dis que ca pourrait être bien de mettre le déroulement de la partie dans Game 



	public static void main(String[] args) {

		Game newGame = new Game();

		newGame.initializeGame(); 

		Scanner input = new Scanner(System.in) ;

		Player p1 = new Player() ;
		Player p2 = new Player() ;
		Player p3 = new Player() ;

		newGame.addPlayer(p1, input) ;
		newGame.addPlayer(p2, input) ;
		newGame.addPlayer(p3, input) ; 

		newGame.distribute();

		p1.upsideDown(input) ; 
		p2.upsideDown(input) ;
		p3.upsideDown(input) ;
		
		p1.stealCard(p1, input) ;
		p2.stealCard(p2, input) ;
		p3.stealCard(p3, input) ;
		

	}
}