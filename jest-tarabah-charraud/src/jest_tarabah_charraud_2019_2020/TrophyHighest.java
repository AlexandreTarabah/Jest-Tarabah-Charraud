package jest_tarabah_charraud_2019_2020;

import java.util.Iterator;


/**
 * Trophyhighest est une classe fille de Trophy
 * @see Trophy
 * 
 * @author Yosh
 * @version 4.0
 * 
 */


public class TrophyHighest extends Trophy


{


	public TrophyHighest(Color color) 
	{
		super(color) ;
	}

	/**
	 * Redéfinit la méthode visitJest de Trophy
	 * @see Trophy
	 * 
	 * @since 3.0*/
	
	public void visitJest(Jest jest, Color color)
	{

		Card highest = new Card(Value.un, Color.heart) ;


		int highestOrdinal = 0 ;

		Iterator<Card> itJC = jest.jestCards.iterator() ; // Entrance dans le
		// jest du joueur

		while(itJC.hasNext())
		{
			Card card = (Card) itJC.next();

			if(card.getColor().equals(color))
			{
				if(card.getValue().ordinal() >= highestOrdinal)
				{
					highest = card ;
					highestOrdinal = card.getValue().ordinal() ;
				} 
			}
		}

		super.highCandidate = highest ;
	}
}

