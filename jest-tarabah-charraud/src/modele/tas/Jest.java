package modele.tas;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import modele.game.Count;
import modele.game.Game;
import modele.joueur.Player;
import modele.carte.TrophyJoker;
import modele.carte.TrophyMajority;
import modele.carte.TrophyHighest;
import modele.carte.TrophyLowest;
import modele.carte.TrophyBestJest;
import modele.carte.TrophyBestJestNoJoke;
import modele.carte.Card;
import modele.carte.Trophy;

import java.util.Map.Entry;

/**
 * Cette classe objectualise le Jest d'un joueur.
 * Les cartes gagnées au cours des manches s'y accumulent dans une liste jestCards.
 * @author Alex, Yosh
 * @version 4.0*/


public class Jest {

	int bestJest;

	public List<Card> jestCards = new ArrayList<Card>();
	
	public Jest() {
	}

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
		if(t.getTrophy().getMajCandidate() == 0)
		{
			String loose = ("Vous n'avez aucune carte de la même valeur " + p.getPseudo() + " !\n") ;

			System.out.println(loose) ;
		}

		else
		{
			System.out.println(p.getPseudo() + " voici votre nombre de " + t.getValue() 
			+ " : " + t.getTrophy().getMajCandidate() + "\n") ;
			/**
			 * coeff le plus élevé et nombre de cartes mis dans une liste
			 * */
			majCandidates.put(t.getTrophy().bigCoeff, t.getTrophy().getMajCandidate());

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

		String win = ("Bravo Joueur " + ((Player) majPlayer.keySet().toArray()[0]).getPseudo() + 
				" vous avez le plus grand nombre de " + t.getTrophy().getValue() 
				+ ". Vous remportez le Trophée ! Les cartes de votre Jest sont : " 
				+ ((Player) majPlayer.keySet().toArray()[0]).getJest().jestCards + "\n" );

		return win ;
	}

	public String winHighest(Player p, Card t, Map<Player,Integer> highCandidates, Comparator<Integer> valueComparator,
			Map<Player, Integer> sortedHighCandidates)
	{
		if(t.getTrophy().getHighCandidate().getColor()
				.equals(t.getTrophy().getColor()) == false)
		{
			String loose = ("Vous n'avez aucune carte de la même couleur " + p.getPseudo() + " !\n") ;

			System.out.println(loose) ;

			highCandidates.put(p, 0) ;
		}

		else
		{
			{
				System.out.println("Voici votre carte de " + t.getTrophy().getColor() 
						+ " de plus grande valeur " + p.getPseudo() + " : "
						+ t.getTrophy().getHighCandidate().getValue() + " de " 
						+ t.getTrophy().getHighCandidate().getColor() + "\n") ;

				highCandidates.put(p, t.getTrophy().getHighCandidate().getValue().ordinal()) ;

			}
		}


		sortedHighCandidates.putAll(highCandidates);

		String win = ("Bravo Joueur " + ((TreeMap<Player, Integer>) sortedHighCandidates).lastKey().getPseudo() + 
				" vous avez la plus forte carte de " + t.getTrophy().getColor() 
				+ " vous remportez le Trophée ! Les cartes de votre Jest sont : " 
				+ ((TreeMap<Player, Integer>) sortedHighCandidates).lastKey().getJest().jestCards + "\n" ) ;

		return win ;

	}

	public String winLowest(Player p, Card t, Map<Player,Integer> lowCandidates, Comparator<Integer> valueComparator,
			Map<Player, Integer> sortedLowCandidates)
	{
		if(t.getTrophy().getLowCandidate().getColor()
				.equals(t.getTrophy().getColor()) == false)
		{
			String loose = ("Vous n'avez aucune carte de la même couleur " + p.getPseudo() + " !\n") ;

			System.out.println(loose) ;

			lowCandidates.put(p, 10) ;
		}

		else
		{
			{
				System.out.println("Voici votre carte de " + t.getTrophy().getColor() 
						+ " de plus faible valeur " + p.getPseudo() + " : "
						+ t.getTrophy().getLowCandidate().getValue() + " de " 
						+ t.getTrophy().getLowCandidate().getColor() + "\n") ;
				lowCandidates.put(p, t.getTrophy().getLowCandidate().getValue().ordinal()) ;

			}
		}

		sortedLowCandidates.putAll(lowCandidates);

		String win = ("Bravo Joueur " + ((TreeMap<Player, Integer>) sortedLowCandidates).firstKey().getPseudo() + 
				" vous avez la plus faible carte de " + t.getTrophy().getColor() 
				+ " vous remportez le Trophée ! Les cartes de votre Jest sont : " 
				+ ((TreeMap<Player, Integer>) sortedLowCandidates).firstKey().getJest().jestCards + "\n" ) ;

		return win ;

	}

	public String winBestJest(Player p, Card t, Map<Player,Integer> bestJestCandidates, Map<Player, Integer> bestJestCandidates1, 
			Map<Player,Entry<Player, Integer>> bestJestValue, Map<Player, Entry<Player, Integer>> bestJestColor, Map<Player, Integer> bestJestPlayer, 
			Entry<Player, Integer> myEntry)
	{

		System.out.println(p.getPseudo() + " voici la valeur de votre jest : " +
				t.getTrophy().getBestJestCandidate() + "\n") ;
		/**
		 * coeff le plus élevé et nombre de cartes mis dans une liste
		 * */

		int jestValue = t.getTrophy().getBestJestCandidate() ; 

		bestJestCandidates.put(p, t.getTrophy().bigValue) ;
		bestJestCandidates1.put(p, t.getTrophy().bigCoeff) ;

		Iterator<Entry<Player, Integer>> itr = bestJestCandidates.entrySet().iterator();
		Iterator<Entry<Player, Integer>> itr1 = bestJestCandidates1.entrySet().iterator();

		while(itr1.hasNext()) 
		{
			Entry<Player, Integer> entry = itr.next();
			Entry<Player, Integer> entry1 = itr1.next();
			// parcourrons la map (contient un joueur) qui est candidat au trophy
			Iterator<Entry<Player, Entry<Player, Integer>>> itrV = bestJestValue.entrySet().iterator();
			Iterator<Entry<Player, Entry<Player, Integer>>> itrC = bestJestColor.entrySet().iterator();
			Iterator<Entry<Player, Integer>> itrP = bestJestPlayer.entrySet().iterator();

			while(itrV.hasNext() && itrC.hasNext())
			{
				Entry<Player, Entry<Player, Integer>> entryV = itrV.next();
				Entry<Player, Entry<Player, Integer>> entryC = itrC.next();
				Entry<Player, Integer> entryP = itrP.next();

				if(entryP.getValue() < jestValue) 
				{
					bestJestValue.clear();
					bestJestValue.put(p, entry) ;

					bestJestColor.clear();
					bestJestColor.put(p, entry1) ;

					bestJestPlayer.clear();
					bestJestPlayer.put(p, jestValue) ; 
				}

				else if(entry.getValue() == jestValue && entryP.getKey() != entry.getKey())
				{
					if(entryV.getValue().getValue() < entry.getValue())
					{
						bestJestValue.clear();
						bestJestValue.put(p, entry) ;

						bestJestColor.clear();
						bestJestColor.put(p, entry1) ;

						bestJestPlayer.clear();
						bestJestPlayer.put(p, jestValue) ;
					}
//nimp
					else if(entryV.getValue().getValue() == entry.getValue() && entryP.getKey() != entry.getKey())
					{
						if(entryC.getValue().getValue() < entry1.getValue())
						{
							bestJestValue.clear();
							bestJestValue.put(p, entry) ;

							bestJestColor.clear();
							bestJestColor.put(p, entry1) ;

							bestJestPlayer.clear();
							bestJestPlayer.put(p, jestValue) ;
						}
					}
				}
			}
		}


		String win = ("Bravo Joueur " + ((Player) bestJestPlayer.keySet().toArray()[0]).getPseudo() + 
				" vous avez le meilleur jest dont le total de points est " 
				+ bestJestPlayer.get(((Player) bestJestPlayer.keySet().toArray()[0]))
				+ ". Vous remportez le Trophée ! Les cartes de votre Jest sont : " 
				+ ((Player) bestJestValue.keySet().toArray()[0]).getJest().jestCards + "\n" ) ;

		return win ;

	}

	public String winJoker(Player p, Card t)
	{
		if(t.getTrophy().jokerCandidate == 1)
		{
			p.getJest().jestCards.add(t) ; 

			String win = ("Bravo Joueur " + p.getPseudo() + 
					" vous avez le Joker ! "
					+ "Vous remportez le Trophée ! Les cartes de votre Jest sont : " 
					+ p.getJest().jestCards + "\n" ) ;
			return win ;

		}

		else
		{
			String loose = ("Vous n'avez pas le Joker Joueur " + p.getPseudo()) ;
			return loose ;
		}
	}


	public void acceptCount(Count count, Player p, Game g, int i) 

	{ // A revoir avec Strategy ou visitor

		System.out.println("\nJoueur "+ p.getPseudo() + " effectuons le décompte de vos points ! \n"
				+ "Rappelons les cartes de votre jest : " + p.getJest().jestCards);
		p.setNbPoint(count.visitJest(this));
		System.out.println("Joueur "+ p.getPseudo() + " votre jest vaut " + p.getNbPoint() + " pts") ;
		g.getWinner().put(p.getPseudo(), p.getNbPoint());
		
		g.scoresTransition.add(p.getPseudo()) ;
		g.scoresTransition.add(Integer.toString(p.getNbPoint())) ;
		
	}
 

}






