package jest_tarabah_charraud_2019_2020;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Player {
    private String pseudo;

    private int nump;

    public boolean isAThief;

    public boolean hasHighestFup;
   
    private static HashMap<String, Card> offer;
    
    private ArrayList<Player> listPlayer;
    
    
    //j'instancie l'objet offre, qui est aussi une collection de carte, dans le constructeur player ici 
    
    public Player (String pseudo) {
    	this.pseudo=pseudo;
    	offer = new HashMap<String, Card>();
    }

    
    public void stealCard(Player player, Player offer) {
    	
    	
    	
    }
    
    
    
 public void setPseudo() {
 }
    	
 
 // la c'est la méthode pour 
    public void upsideDown(Card cardfaceup , Card cardfacedown) {
    	offer.put(pseudo, cardfaceup);
    	System.out.println(cardfaceup.tostring());
    	offer.put(pseudo, cardfacedown);
    	System.out.println(cardfacedown.tostring());
    }
    	
    public HashMap<String, Card> getOffer (String pseudo)
    {
		return Player.offer;
    	
    }

}
