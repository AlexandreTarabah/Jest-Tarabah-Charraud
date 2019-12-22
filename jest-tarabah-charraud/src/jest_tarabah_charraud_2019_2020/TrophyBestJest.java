package jest_tarabah_charraud_2019_2020;

import java.util.Iterator;

public class TrophyBestJest extends Trophy {
    public TrophyBestJest(Color color) {

    	super(color);
	}


    public void visitJest(Jest jest) 
	{
		int bestJest = 0 ;

		Iterator<Card> itJC = jest.jestCards.iterator() ; // Entrance dans le
		// jest du joueur

		while(itJC.hasNext())
		{
			Card card = (Card) itJC.next();

					bestJest += card.value.getCardValue();
		}

		
		super.bestJestCandidate = bestJest ;
	}
    
    }


