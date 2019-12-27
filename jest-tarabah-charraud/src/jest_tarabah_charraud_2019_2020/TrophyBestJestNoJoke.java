package jest_tarabah_charraud_2019_2020;

import java.util.Iterator;

public class TrophyBestJestNoJoke extends Trophy {
	public TrophyBestJestNoJoke() {

		// TODO Auto-generated constructor stub
	}

	public void bestJestNoJoke() {
	}

	public void visitJest(Jest jest) 
	{
		int bestJest = 0 ;

		Iterator<Card> itJC = jest.jestCards.iterator() ; // Entrance dans le
		// jest du joueur

		while(itJC.hasNext())
		{
			Card card = (Card) itJC.next();

			if(card.getColor() != Color.joker)
			{

				bestJest += card.value.getCardValue();

			}
		}


		super.bestJestCandidate = bestJest ;
	}

}
