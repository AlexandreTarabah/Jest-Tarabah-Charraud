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

    private String trophy;

    private String gameplay;
    
    private ArrayList<Player> players;
    
    private DrawDeck drawdeck;
    
    boolean currentPlay;
    
    
    
    

    public void determinateWinner() {
    }

    
    // La c'est la distribution des cartes, ou finalement j'invoque la méthode takecards et donc le joueur prend 2 cartes, et créé son offer
    
  public void distribute() {
    	
    	this.currentPlay=true;
    	Iterator<Player> it =players.iterator();
    	while(it.hasNext()) {
    		Player j= (Player) it.next();
    		j.PlayerTakeCards(drawdeck.takeCards());
    	}
    	
  }

  
  
  
    public void InitializeGame() {
    	
    		  players = new ArrayList<Player>();
    		  drawdeck = new DrawDeck();
    		  drawdeck.shuffle();
    		  currentPlay=false;   	
   
    	
    }

    
    
    
    public void chooseGameplay() {
    }

    
    protected void finalize() {
    }

    
    public void highestFUp() {
    }

    
    
    
    public void addPlayer(Player player) {
    	if(currentPlay==false) {
    		players.add(player);
    	}
    }

    
    
    
    //j'ai mis le main ici, je me suis dis que ca pourrait être bien de mettre le déroulement de la partie dans Game 
	
	

public static void main(String[] args) {
	
	Game newGame=new Game();

	Player marcel = new Player("Alex");
	Player sophia = new Player("Pelgro");
	
	newGame.addPlayer(alex);
	newGame.addPlayer(pepe);
	
	newGame.distribute();
	System.out.println(newGame);
	

	
}
}