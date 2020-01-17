package modele.carte;

import java.util.Iterator;

import modele.tas.Jest;

/**
 * TrophyHighest définit visitJest afin de trouver la carte de plus faible valeur de la couleur spécifiée. 
 *
 */
public class TrophyLowest extends Trophy
{

	/**
	 * Constructeur de TrophyLowest
	 * @param color
	 */
	public TrophyLowest(Color color) 
	{
		super(color) ;
	}

	/**
	 * Redéfinition de visitJest @see {@link Trophy#visitJest(Jest)}
	 * Attribue la carte de plus faible valeur de la couleur spécifiée à l'attribut (Card) lowest @see Trophy	 
	 */
	public void visitJest(Jest jest, Color color)
	{
		Card lowest = new Card(Value.un, Color.heart) ;
		
		int lowestOrdinal = 5 ;

		Iterator<Card> itJC = jest.jestCards.iterator();

		while(itJC.hasNext())
		{
			Card card = (Card) itJC.next();

			if(card.getColor().equals(color))
			{
				if(card.getValue().ordinal() < lowestOrdinal)
				{
					lowest = card ;
					lowestOrdinal = card.getValue().ordinal() ; 
				}
			}
		}

		setLowCandidate(lowest) ;
	}

}
