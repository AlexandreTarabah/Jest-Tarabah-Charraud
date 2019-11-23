package jest_tarabah_charraud_2019_2020;

import java.util.Iterator;

public class TrophyLowest extends Trophy implements Visitor {
    public TrophyLowest(Value value, Color color) {
		super(value, color);
		// TODO Auto-generated constructor stub
	}


    public void visitJest(Jest jest, Color color) {
    	
    	{
    		Card lowest = null ;
    		int lowestOrdinal = 5 ;

    		Iterator<Card> itJC = jest.jestCards.iterator() ; /* Entrance dans le
    													   jest du joueur
    		 */
    		while(itJC.hasNext())
    		{
    			Card card = (Card) itJC.next();
    			if(card.getColor().equals(color))
    			{
    				if(card.getValue().ordinal() < lowestOrdinal)
    				{
    					lowest = card ; 
    				}
    			}


    		}
    	}
    }
    

}
