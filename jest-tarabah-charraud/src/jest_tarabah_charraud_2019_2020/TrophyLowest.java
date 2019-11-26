package jest_tarabah_charraud_2019_2020;

import java.util.Iterator;

public class TrophyLowest extends Trophy
{

	public TrophyLowest(Color color) 
	{
		super(color) ;
	}

	public void visitJest(Jest jest, Color color)
	{
		Card lowest = new Card(Value.un, Color.heart) ;
		int lowestOrdinal = 0 ;

		Iterator<Card> itJC = jest.jestCards.iterator() ; // Entrance dans le
		// jest du joueur

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

		super.lowCandidate = lowest ;
	}

}
