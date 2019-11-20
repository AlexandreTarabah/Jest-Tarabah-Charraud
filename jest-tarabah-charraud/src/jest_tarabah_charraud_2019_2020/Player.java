package jest_tarabah_charraud_2019_2020;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Player {
    private String pseudo;

    private int nump;

    public boolean isAThief;

    public boolean hasHighestFup;
   
    private ArrayList<Card> offer;
    
    private ArrayList<Player> listGamer;
    
    
    //j'instancie l'objet offre, qui est aussi une collection de carte, dans le constructeur player ici 
    
    public Player (String pseudo) {
    	this.pseudo=pseudo;
    	offer = new ArrayList<Card>();
    }

    
    public void stealCard(Game p1) {
    }
    
    
    
 public void setPseudo() {
 }
    	
 
 // la c'est la méthode pour 
    public void PlayerTakeCards(Card card) {
    	offer.add(card);
    	System.out.println(card.tostring());
    	offer.add(card);
    	System.out.println(card.tostring());
    }
    	

}
