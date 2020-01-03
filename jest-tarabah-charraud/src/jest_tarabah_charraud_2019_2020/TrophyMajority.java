package jest_tarabah_charraud_2019_2020;

import java.util.Iterator;

public class TrophyMajority extends Trophy 
{
	public TrophyMajority(Value value)
	{
		super(value) ;
	} 

	public void visitJest(Jest jest, Value value) 
	{
		int majority = 0 ;

		Iterator<Card> itJC = jest.jestCards.iterator() ; // Entrance dans le
		// jest du joueur

		while(itJC.hasNext())
		{
			Card card = (Card) itJC.next();

			if(card.getColor() != Color.joker)
			{
				if(card.getValue() == value)
				{
					majority ++ ;
				} 
			}
		}


		super.majCandidate = majority ;
	}

}
