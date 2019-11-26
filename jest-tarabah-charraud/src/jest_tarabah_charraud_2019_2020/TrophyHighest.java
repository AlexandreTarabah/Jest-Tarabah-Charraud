package jest_tarabah_charraud_2019_2020;

import java.util.Iterator;

<<<<<<< HEAD
public class TrophyHighest extends Trophy
{

=======
public class TrophyHighest extends Trophy implements Visitor 
{Card highest = new Card(Value.un, Color.heart);
	
>>>>>>> branch 'master' of https://github.com/AlexandreTarabah/Jest-Tarabah-Charraud
	public TrophyHighest(Color color) 
	{
		super(color) ;
	}

	public void visitJest(Jest jest, Color color)
	{
<<<<<<< HEAD
		Card highest = new Card(Value.un, Color.heart) ;
=======
		
>>>>>>> branch 'master' of https://github.com/AlexandreTarabah/Jest-Tarabah-Charraud
		int highestOrdinal = 0 ;

		Iterator<Card> itJC = jest.jestCards.iterator() ; // Entrance dans le
		// jest du joueur

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

		super.highCandidate = highest ;
	}

}
