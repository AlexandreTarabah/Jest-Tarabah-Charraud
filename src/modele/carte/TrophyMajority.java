package modele.carte;

import java.util.Iterator;

import modele.tas.Jest;

/**
 * TrophyMajority d�finit visitJest afin de trouver le nombre de cartes de la valeur sp�cifi�e dans le jest du joueur. 
 */
public class TrophyMajority extends Trophy 
{
	/**
	 * Constructeur de TrophyMajority
	 * @param value
	 */
	public TrophyMajority(Value value)
	{
		super(value) ;
	} 

	/**
	 * Red�finition de visitJest @see {@link Trophy#visitJest(Jest)}
	 * Attribue le nombre de cartes de la valeur sp�cifi�e dans le jest du joueur � l'attribut (int) majority @see Trophy
	 */
	public void visitJest(Jest jest, Value value) 
	{
		int majority = 0 ;

		Iterator<Card> itJC = jest.jestCards.iterator(); 

		while(itJC.hasNext())
		{
			Card card = (Card) itJC.next();

			if(card.getColor() != Color.joker)
			{
				if(card.getValue() == value)
				{
					majority ++ ;
				} 
			}
		}


		setMajCandidate(majority) ;
	}

}
