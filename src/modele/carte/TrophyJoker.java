package modele.carte;

import java.util.Iterator;

import modele.tas.Jest;

/**
 * TrophyMajority d�finit visitJest afin de v�rifier si le joker se trouve dans le jest du joueur. 
 */
public class TrophyJoker extends Trophy {

	/**
	 * Constructeur de TrophyJoker
	 */
	public TrophyJoker() {
	}

	/**
	 * Red�finition de visitJest @see {@link Trophy#visitJest(Jest)}
	 * jokeDetecter passe � 1 si le joker se trouve dans le jest du joueur @see Trophy
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
