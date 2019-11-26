package jest_tarabah_charraud_2019_2020;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;



public class Jest {
	
	HashMap<String,Integer> winner = new HashMap();
	
	private int nbCardJest;

	public List<Card> jestCards = new ArrayList<Card>();
	

	public void acceptVisitor(Trophy trophy) 
	{
		trophy.visitJest(this);
	}

	public void calculateScore() {
	}




	public void countJest(Player player) {


		for(int i=0;i<player.getJest().jestCards.size(); i++) {
			player.nbPoint = player.nbPoint + player.getJest().jestCards.get(i).value.ordinal();
			
		}
		winner.put(player.pseudo, player.nbPoint);
	}
		
	public void winnerDetermination() {
		
		int maxValueInMap=(Collections.max(winner.values()));  // This will return max value in the Hashmap
        for (Entry<String, Integer> entry : winner.entrySet()) {  // Itrate through hashmap
            if (entry.getValue()==maxValueInMap) {
                System.out.println(entry.getKey() + "� gagner !" );
            }
	
		}
		
	}

}
	
	


