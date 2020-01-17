package modele.carte;

import java.util.Iterator;

import modele.tas.Jest;

/**
 * TrophyMajority définit visitJest afin de vérifier si le joker se trouve dans le jest du joueur. 
 */
public class TrophyJoker extends Trophy {

	/**
	 * Constructeur de TrophyJoker
	 */
	public TrophyJoker() {
	}

	/**
	 * Redéfinition de visitJest @see {@link Trophy#visitJest(Jest)}
	 * jokeDetecter passe à 1 si le joker se trouve dans le jest du joueur @see Trophy
	 */
	public void visitJest(Jest jest) 
	{

		int jokeDetecter = 0 ;

		for (Iterator<Card> it = jest.jestCards.iterator() ; it.hasNext();)
		{
			Card itg = it.next();

			if(itg instanceof Joker)
			{
				jokeDetecter += 1 ;
				break ;
			}
		}

		super.jokerCandidate = jokeDetecter ;
	}

}
