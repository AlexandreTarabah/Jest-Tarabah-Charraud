package jest_tarabah_charraud_2019_2020;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class DrawDeck {
    public static int nbCardDD;
    
    private ArrayList<Card> drawdeck = new ArrayList<Card>();
    
    
// j'ai pu simplifié le mélange des cartes avec shuffle directement implementé dans le java, Collections.shuffle et je mélange les cartes 

    public void shuffle() {
    	
    	Collections.shuffle(drawdeck);
    }



    public void revealTrophies() {
    }

    
    // la je créé le drawdeck qui est une liste de cartes.
    
    
    
    public DrawDeck(Game g) {
    	drawdeck= new ArrayList<Card>();
    	
    	for (Color c : Color.values()) {
    		for(Value v : Value.values()) {
    			if (v == Value.un && c == Color.club)
    			{
    				Card card = new Card(v,c);
    				g.trophyCards[0] = card ;
    			}
    			else if (v == Value.trois && c == Color.club)
    			{
    				Card card = new Card(v,c);
    				g.trophyCards[1] = card ;
    			}
    			else
    			{
    			Card card = new Card(v,c);
    			drawdeck.add(card);
    			}
    		}
    	}
    }
    
    public Card takeCards() {
    	nbCardDD=drawdeck.size();
		int position = (int) ((int) Math.round(DrawDeck.nbCardDD-1)*Math.random());
		return drawdeck.remove(position);
			
    }

    
    public boolean isEmpty()
    {
    	return drawdeck.isEmpty(); 
    }
    
}
