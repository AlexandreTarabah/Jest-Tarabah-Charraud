package modele.carte;

import java.util.Iterator;

import modele.tas.Jest;

public class TrophyBestJest extends Trophy {
	public TrophyBestJest() {

		super();
	}


	public void visitJest(Jest jest) 
	{
		int bestJest = 0 ;

		Iterator<Card> itJC = jest.jestCards.iterator(); // Entrance dans le
		// jest du joueur

		while(itJC.hasNext())
		{
			Card card = (Card) itJC.next();
			if(card.getColor() != Color.joker)
			{

				bestJest += card.value.getCardValue();

			}
		}


		setBestJestCandidate(bestJest) ;
	}

}


