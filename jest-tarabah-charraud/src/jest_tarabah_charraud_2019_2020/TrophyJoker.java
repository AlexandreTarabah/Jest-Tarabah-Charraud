package jest_tarabah_charraud_2019_2020;

import java.util.Iterator;

public class TrophyJoker extends Trophy {

	public TrophyJoker() {
		// TODO Auto-generated constructor stub
	}

	public void joker() {
	}

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
