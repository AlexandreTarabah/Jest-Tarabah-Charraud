package jest_tarabah_charraud_2019_2020;

import java.util.ArrayList;

import java.util.List;

public class Jest {
	private int nbCardJest;

	public List<Card> jestCards = new ArrayList<Card> ();

	public void acceptVisitor(Trophy trophy) 
	{
		trophy.visitJest(this);
	}

	public void calculateScore() {
	}




	public void CountJest(Player player) {




	}
}


