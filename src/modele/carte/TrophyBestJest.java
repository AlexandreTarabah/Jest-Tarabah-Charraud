package modele.carte;

import java.util.Iterator;

import modele.tas.Jest;

/**
 * TrophyBestJest définit visitJest afin de calculer le total des points du Jest d'un joueur en se basant sur la valeur de ses cartes.
 *
 */
public class TrophyBestJest extends Trophy {
	
	/**
	 * Constructeur de TrophyBestJest
	 */
	public TrophyBestJest() {}


	/**
	 * Redéfinition de visitJest @see {@link Trophy#visitJest(Jest)}
	 * Attribue la somme des valeurs des cartes dans le jest à l'attribut bestJest @see Trophy 
	 *
	 */
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


