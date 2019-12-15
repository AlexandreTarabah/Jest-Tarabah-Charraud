package jest_tarabah_charraud_2019_2020;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;



public class Jest {
	
	HashMap<String,Integer> winner = new HashMap();
	
	int bestJest;
	
	private int nbCardJest;

	public List<Card> jestCards = new ArrayList<Card>();
	

	public void acceptVisitor(Trophy trophy) 
	{
		if (trophy instanceof TrophyHighest) 
		{
			trophy.visitJest(this, trophy.getColor());
			// TODO Auto-generated catch block

		}
		
		else if (trophy instanceof TrophyLowest) 
		{
			trophy.visitJest(this, trophy.getColor());
			// TODO Auto-generated catch block

		}
		
	}


	public void calculateScore() {
	}




	public void countJest(Player player) { // A revoir avec Strategy ou visitor

		for(int i=0;i<player.getJest().jestCards.size(); i++) {
			player.nbPoint = player.nbPoint + player.getJest().jestCards.get(i).value.ordinal();
			
		}
		winner.put(player.pseudo, player.nbPoint);
	}
		
	
	
	
	
	
	public void winnerDetermination() {
		
		int maxValueInMap=(Collections.max(winner.values()));  // retourne la valeur max de la hashmap winner
        for (Entry<String, Integer> entry : winner.entrySet()) {  
            if (entry.getValue()==maxValueInMap) {
                System.out.println(entry.getKey() + " a gagné !" ); // détermine a quelle clé cela appartient pour afficher le gagnant 
            }
	
		}
		
	}
	public int bestJest() { // Calcul juste les points le trophée bestJest.
		for(int i=0; i<jestCards.size(); i++) {
			bestJest =+ jestCards.get(i).value.ordinal();
		}
		return bestJest;
	}

}
	
	


