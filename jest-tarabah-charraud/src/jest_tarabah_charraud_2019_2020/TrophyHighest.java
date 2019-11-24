package jest_tarabah_charraud_2019_2020;

import java.util.Iterator;

public class TrophyHighest extends Trophy implements Visitor 
{

	public TrophyHighest(Value value, Color color) {
		super(value, color);
		// TODO Auto-generated constructor stub
	}

	public Card visitJest(Jest jest, Color color) 
	{
		Card highest = new Card(Value.un, Color.heart);
		int highestOrdinal = 0 ;

		Iterator<Card> itJC = jest.jestCards.iterator() ; /* Entrance dans le
													   jest du joueur
		 */
		while(itJC.hasNext())
		{
			Card card = (Card) itJC.next();
			if(card.getColor().equals(color))
			{
				if(card.getValue().ordinal() > highestOrdinal)
				{
					highest = card ; 
				}
			}


		}

		return highest ;

	}


}
