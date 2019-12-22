package jest_tarabah_charraud_2019_2020;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

/**
 * Cette classe objectualise le Jest d'un joueur.
 * Les cartes gagnées au cours des manches s'y accumulent dans une liste jestCards.
 * @author Alex, Yosh
 * @version 4.0*/


public class Jest {
	
	HashMap<String,Integer> winner = new HashMap();
	
	int bestJest;
	
	private int nbCardJest;

	public List<Card> jestCards = new ArrayList<Card>();
	
	/**
	 * Le Jest invite le Trophy d'une trophyCards à déterminer son éligibilité
	 * 
	 * @param trophy 
	 * 				Le trophy d'une trophyCard
	 */

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
		
		else if (trophy instanceof TrophyMajority) 
		{
			trophy.visitJest(this, trophy.getValue());
			trophy.bigColor(this, trophy.getValue());
			// TODO Auto-generated catch block
		}
		
		else if (trophy instanceof TrophyBestJest) 
		{
			trophy.visitJest(this);
			trophy.bigColor(this);
			trophy.bigValue(this);
			// TODO Auto-generated catch block
		}
		
		else if (trophy instanceof TrophyBestJestNoJoke) 
		{
			trophy.visitJest(this);
			trophy.bigColor(this);
			trophy.bigValue(this);
			// TODO Auto-generated catch block
		}
		
		else if (trophy instanceof TrophyJoker) 
		{
			trophy.visitJest(this);
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
	
	/**
	 *@return bestJest qui est le total des points du Jest d'un joueur 
	 *
	 *@deprecated depuis Jest 4.0, à repenser
	 */
	public int bestJest() { // Calcul juste les points le trophée bestJest. 
		for(int i=0; i<jestCards.size(); i++) {
			bestJest =+ jestCards.get(i).value.ordinal();
		}
		
		return bestJest ;
	}

}
	
	


