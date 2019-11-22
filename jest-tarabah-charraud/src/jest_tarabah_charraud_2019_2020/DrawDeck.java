package jest_tarabah_charraud_2019_2020;

import java.util.ArrayList;
import java.util.Collections;

public class DrawDeck {
    public static int nbCardDD;
    
    private ArrayList<Card> drawdeck;
    
    
// j'ai pu simplifi� le m�lange des cartes avec shuffle directement implement� dans le java, Collections.shuffle et je m�lange les cartes 

    public void shuffle() {
    	
    	Collections.shuffle(drawdeck);
    }



    public void revealTrophies() {
    }

    
    // la je cr�� le drawdeck qui est une liste de cartes.
    
    
    
    public DrawDeck() {
    	drawdeck= new ArrayList<Card>();
    	
    	for (Color c : Color.values()) {
    		for(Value v : Value.values()) {
    			Card card = new Card(v,c);
    			drawdeck.add(card);
    		}
    	}
    }
    
    public Card takeCards() {
		int position = (int) ((int) Math.round(DrawDeck.nbCardDD-1)*Math.random());
		return drawdeck.remove(position);
			
    }
    
    public boolean isEmpty()
    {
    	return drawdeck.isEmpty(); 
    }
    
}
