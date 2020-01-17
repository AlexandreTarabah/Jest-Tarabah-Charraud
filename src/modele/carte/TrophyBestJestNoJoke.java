package modele.carte;

import java.util.Iterator;

import modele.tas.Jest;

/**
 * TrophyBestJestNoJoke d�finit visitJest afin de calculer le total des points du Jest d'un joueur ne poss�dant pas le Joker 
 * en se basant sur la valeur de ses cartes. @see {@link modele.game.Game#giveTrophy} 
 *
 */
public class TrophyBestJestNoJoke extends Trophy {
	
	/**
	 * Constructeur de TrophyBestJestNoJoke
	 */
	public TrophyBestJestNoJoke() {}

	/**
	 * Red�finition de visitJest @see {@link Trophy#visitJest(Jest)}
	 * Attribue la somme des valeurs des cartes dans le jest � l'attribut bestJest @see Trophy 
	 */
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


		setBestJestCandidate(bestJest) ;
	}

}
