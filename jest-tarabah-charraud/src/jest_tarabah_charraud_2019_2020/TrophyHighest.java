package jest_tarabah_charraud_2019_2020;

import java.util.Iterator;

public class TrophyHighest extends Trophy {
	public TrophyHighest(Value value, Color color) {
		super(value, color);
		// TODO Auto-generated constructor stub
	}

	public Card highest(Game newGame, Color color) // préciser color permet d'écarter les cartes de couleur diff
	{

		Card highest = null ;
		int size = newGame.players.size() ;
		for(int i = 0 ; i < newGame.players.size() ; i ++)
		{
			Iterator itJC = newGame.players.get(i).getJest().jestCards.iterator() ; /* Entrance dans le
																					   jest du joueur
			 																		*/
			while(itJC.hasNext())
			{
				Card card = (Card) itJC.next();
				int highestOrdinal = 0 ;
				if(card.getColor().equals(color))
				{
					if(card.getValue().ordinal() > highestOrdinal)
					{
						highest = card ; 
					}
				}
			}
			
		}
		return highest;
	}

	public void visitJest(Jest p1) {
	}

}
