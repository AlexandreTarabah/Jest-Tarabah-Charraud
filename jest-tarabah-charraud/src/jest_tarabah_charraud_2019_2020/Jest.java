package jest_tarabah_charraud_2019_2020;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
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

	public void acceptTrophy(Trophy trophy) 
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

	public String winMajority(Player p, Card t, Map<Integer,Integer> majCandidates, Map<Player,Entry<Integer, Integer>> majPlayer,
			Map.Entry<Integer,Integer> myEntry)
	{
		if(t.getTrophy().majCandidate == 0)
		{
			String loose = ("Vous n'avez aucune carte de la même valeur " + p.pseudo + " !\n") ;

			System.out.println(loose) ;
		}

		else
		{
			System.out.println(p.pseudo + " voici votre nombre de " + t.getValue() 
			+ " : " + t.getTrophy().majCandidate + "\n") ;
			/**
			 * coeff le plus élevé et nombre de cartes mis dans une liste
			 * */
			majCandidates.put(t.getTrophy().bigCoeff, t.getTrophy().majCandidate);

			Iterator<Entry<Integer, Integer>> itr = majCandidates.entrySet().iterator(); 

			while(itr.hasNext()) 
			{ 
				Entry<Integer, Integer> entry = itr.next();
				// parcourrons la map (contient un joueur) qui est candidat au trophy
				Iterator<Entry<Player, Entry<Integer, Integer>>> itrP = majPlayer.entrySet().iterator();

				while(itrP.hasNext())
				{
					Entry<Player, Entry<Integer, Integer>> entryP = itrP.next();

					if(entryP.getValue().getValue() < entry.getValue()) 
					{

						majPlayer.clear();
						majPlayer.put(p, entry) ;

					}

					else if(entryP.getValue().getValue() == entry.getValue())
					{
						if(entryP.getValue().getKey() < entry.getKey())
						{
							majPlayer.clear();
							majPlayer.put(p, entry) ;
						}
					}
				}

			}
		}

		String win = ("Bravo Joueur " + ((Player) majPlayer.keySet().toArray()[0]).pseudo + 
				" vous avez le plus grand nombre de " + t.getTrophy().getValue() 
				+ ". Vous remportez le Trophée ! Les cartes de votre Jest sont : " 
				+ ((Player) majPlayer.keySet().toArray()[0]).getJest().jestCards + "\n" );

		return win ;
	}

	public String winHighest(Player p, Card t, Map<Player,Integer> highCandidates, Comparator<Integer> valueComparator,
			Map<Player, Integer> sortedHighCandidates)
	{
		if(t.getTrophy().highCandidate.getColor()
				.equals(t.getTrophy().getColor()) == false)
		{
			String loose = ("Vous n'avez aucune carte de la même couleur " + p.pseudo + " !\n") ;

			System.out.println(loose) ;
		}

		else
		{
			{
				System.out.println("Voici votre carte de " + t.getTrophy().getColor() 
						+ " de plus grande valeur " + p.pseudo + " : "
						+ t.getTrophy().highCandidate.getValue() + " de " 
						+ t.getTrophy().highCandidate.getColor() + "\n") ;
				highCandidates.put(p, t.getTrophy().highCandidate.getValue().ordinal()) ;

			}
		}


		sortedHighCandidates.putAll(highCandidates);

		String win = ("Bravo Joueur " + ((TreeMap<Player, Integer>) sortedHighCandidates).lastKey().pseudo + 
				" vous avez la plus forte carte de " + t.getTrophy().getColor() 
				+ " vous remportez le Trophée ! Les cartes de votre Jest sont : " 
				+ ((TreeMap<Player, Integer>) sortedHighCandidates).lastKey().getJest().jestCards + "\n" ) ;

		return win ;

	}

	public String winLowest(Player p, Card t, Map<Player,Integer> lowCandidates, Comparator<Integer> valueComparator,
			Map<Player, Integer> sortedHighCandidates)
	{
		if(t.getTrophy().highCandidate.getColor()
				.equals(t.getTrophy().getColor()) == false)
		{
			String loose = ("Vous n'avez aucune carte de la même couleur " + p.pseudo + " !\n") ;

			System.out.println(loose) ;
		}

		else
		{
			{
				System.out.println("Voici votre carte de " + t.getTrophy().getColor() 
						+ " de plus faible valeur " + p.pseudo + " : "
						+ t.getTrophy().highCandidate.getValue() + " de " 
						+ t.getTrophy().highCandidate.getColor() + "\n") ;
				lowCandidates.put(p, t.getTrophy().highCandidate.getValue().ordinal()) ;

			}
		}

		sortedHighCandidates.putAll(lowCandidates);

		String win = ("Bravo Joueur " + ((TreeMap<Player, Integer>) sortedHighCandidates).firstKey().pseudo + 
				" vous avez la plus faible carte de " + t.getTrophy().getColor() 
				+ " vous remportez le Trophée ! Les cartes de votre Jest sont : " 
				+ ((TreeMap<Player, Integer>) sortedHighCandidates).firstKey().getJest().jestCards + "\n" ) ;

		return win ;

	}

	public String winBestJest(Player p, Card t, Map<Integer,Integer> bestJestCandidates, Map<Integer,Integer> bestJestCandidates1, 
			Map<Player,Entry<Integer, Integer>> bestJestPlayer, Map<Player,Entry<Integer, Integer>> bestJestColor,
			Map.Entry<Integer,Integer> myEntry)
	{

		System.out.println(p.pseudo + " voici la valeur de votre jest : " +
				t.getTrophy().bestJestCandidate + "\n") ;
		/**
		 * coeff le plus élevé et nombre de cartes mis dans une liste
		 * */

		bestJestCandidates.put(t.getTrophy().bigValue, t.getTrophy().bestJestCandidate) ;
		bestJestCandidates1.put(t.getTrophy().bigCoeff, t.getTrophy().bestJestCandidate) ;

		Iterator<Entry<Integer, Integer>> itr = bestJestCandidates.entrySet().iterator();
		Iterator<Entry<Integer, Integer>> itr1 = bestJestCandidates1.entrySet().iterator();

		while(itr.hasNext() && itr1.hasNext()) 
		{ 
			Entry<Integer, Integer> entry = itr.next();
			Entry<Integer, Integer> entry1 = itr1.next();
			// parcourrons la map (contient un joueur) qui est candidat au trophy
			Iterator<Entry<Player, Entry<Integer, Integer>>> itrP = bestJestPlayer.entrySet().iterator();
			Iterator<Entry<Player, Entry<Integer, Integer>>> itrC = bestJestColor.entrySet().iterator();

			while(itrP.hasNext() && itrC.hasNext())
			{
				Entry<Player, Entry<Integer, Integer>> entryP = itrP.next();
				Entry<Player, Entry<Integer, Integer>> entryC = itrC.next();

				if(entryP.getValue().getValue() < entry.getValue()) 
				{
					bestJestPlayer.clear();
					bestJestPlayer.put(p, entry) ;

					bestJestColor.clear();
					bestJestColor.put(p, entry1) ;
				}

				else if(entryP.getValue().getValue() == entry.getValue())
				{
					if(entryP.getValue().getKey() < entry.getValue())
					{
						bestJestPlayer.clear();
						bestJestPlayer.put(p, entry) ;

						bestJestColor.clear();
						bestJestColor.put(p, entry1) ;
					}

					else if(entryP.getValue().getKey() == entry.getValue())
					{
						if(entryC.getValue().getKey() < entry1.getValue())
						{
							bestJestPlayer.clear();
							bestJestPlayer.put(p, entry) ;

							bestJestColor.clear();
							bestJestColor.put(p, entry1) ;
						}
					}
				}
			}

		} 


		String win = ("Bravo Joueur " + ((Player) bestJestPlayer.keySet().toArray()[0]).pseudo + 
				" vous avez le meilleur jest sans joker dont le total de points est " 
				+ ((Card) bestJestPlayer.values()).getValue()  
				+ ". Vous remportez le Trophée ! Les cartes de votre Jest sont : " 
				+ ((Player) bestJestPlayer.keySet().toArray()[0]).getJest().jestCards + "\n" ) ;

		return win ;

	}

	public String winJoker(Player p, Card t)
	{
		if(t.getTrophy().jokerCandidate == 1)
		{
			p.getJest().jestCards.add(t) ; 

			String win = ("Bravo Joueur " + p.pseudo + 
					" vous avez le Joker ! "
					+ "Vous remportez le Trophée ! Les cartes de votre Jest sont : " 
					+ p.getJest().jestCards + "\n" ) ;
			return win ;

		}

		else
		{
			String loose = ("Vous n'avez pas le Joker Joueur " + p.pseudo) ;
			return loose ;
		}
	}


	public void acceptCount(Count count, Player p) 
	
	{ // A revoir avec Strategy ou visitor


			count.visitJest(this) ;
			winner.put(p.pseudo, p.nbPoint);

	}


	public void winnerDetermination() {

		int maxValueInMap=(Collections.max(winner.values()));  // retourne la valeur max de la hashmap winner
		for (Entry<String, Integer> entry : winner.entrySet()) {  
			if (entry.getValue()==maxValueInMap) {
				System.out.println(entry.getKey() + " a gagné !" ); // détermine a quelle clé cela appartient pour afficher le gagnant 
			}

		}




	}

}






