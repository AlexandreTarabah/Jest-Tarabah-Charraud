package modele.carte;

import java.util.Iterator;

import modele.tas.Jest;


/**
 * TrophyHighest définit visitJest afin de trouver la carte de plus forte valeur de la couleur spécifiée. 
 *
 */
public class TrophyHighest extends Trophy


{


	/**
	 * Constructeur de TrophyHighest
	 * @param color
	 */
	public TrophyHighest(Color color) 
	{
		super(color) ;
	}

	
	/**
	 * Redéfinition de visitJest @see {@link Trophy#visitJest(Jest)}
	 * Attribue la carte de plus forte valeur de la couleur spécifiée à l'attribut (Card) highest @see Trophy 
	 */
	public void visitJest(Jest jest, Color color)
	{

		Card highest = new Card(Value.un, Color.heart) ;


		int highestOrdinal = 0 ;

		Iterator<Card> itJC = jest.jestCards.iterator();

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

		setHighCandidate(highest) ;
	}
}

