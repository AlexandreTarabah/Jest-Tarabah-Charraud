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
import java.util.Set;


/**
 * <p>
 * La classe Jest repr�sente comme son nom l'indique le Jest des joueurs qui se concr�tise
<<<<<<< HEAD
 * en une agr�gation de cartes cod�es par une ArrayList.
=======
 * en une agr�gation de cartes cod�es par une ArrayList(Card)
>>>>>>> branch 'master' of https://github.com/AlexandreTarabah/Jest-Tarabah-Charraud
 * </p> 
 * <p>
 * La classe fait �galement partie du  design pattern visiteur avec la classe Trophy @see Trophy. Elle joue le r�le
 * du visit� et poss�de donc une m�thode acceptTrophy ad�quate.
 * @see Jest#acceptTrophy(Trophy) 
 * Par souci d'"objectualisation" de la m�thode main, la classe poss�de une nu�e de m�thodes string qui font �cho 
 * � la visite des jests par un troph�e et comparent le retour de la visite aux visites pr�c�dentes.
 * Si la comparaison s'av�re gagnante pour le jest pour le troph�e en jeu elle renvoie des
 * f�licitations (string) au joueur, lui pr�cisant pourquoi il gagne et son jest mis � jour avec le troph�e remport�.
 * @see Jest#winBestJest(Player, Card, Map, Map, Map, Map, Map, Entry)
 * </p>
 * <p>
 * Enfin, la m�thode acceptCount lui permet de faire le d�compte des points d'un joueur en question donc
 * de son jest.
 * @see Jest#acceptCount(Count, Player, Game, int)
 * </p>
 * 
 *
 */
public class Jest {

	public List<Card> jestCards = new ArrayList<Card>();

	/**
	 * Constructeur du Jest.
	 */
	public Jest() {
	}

	/**
	 * M�thode acceptTrophy
	 * <p>
	 * "Accepte la visite d'un Trophy", c'est-�-dire qu'elle appelle la m�thode visitJest de
	 * l'instance de Trophy fille en argument. 
	 * Cette derni�re effectuera le calcul attendu d�terminant la valeur du Jest du joueur 
	 * pour un Trophy particulier.
	 * </p>
	 * <p>
	 * Dans certains cas (TrophyMajority, TrophyBestJestNoJoke, TrophyBestJest), il est �galement 
	 * appel� les m�thodes BigColor et BigValue de Trophy @see {@link Trophy#bigColor(Jest)}, 
	 * {@link Trophy#bigColor(Jest, modele.carte.Value)}, {@link Trophy#bigValue(Jest)}.
	 * Ces m�thodes permettent de justifier l'attribution des troph�es
	 * lors des situations d'�galit� en premi�re approche. (cf r�gles du Jest)
	 * </p> 
	 * @param trophy
	 * 
	 */
	public void acceptTrophy(Trophy trophy) 
	{
		if (trophy instanceof TrophyHighest) 
		{
			trophy.visitJest(this, trophy.getColor());
			

		}

		else if (trophy instanceof TrophyLowest) 
		{
			trophy.visitJest(this, trophy.getColor());
			

		}

		else if (trophy instanceof TrophyMajority) 
		{
			trophy.visitJest(this, trophy.getValue());
			trophy.bigColor(this, trophy.getValue());
		
		}

		else if (trophy instanceof TrophyBestJest) 
		{
			trophy.visitJest(this);
			trophy.bigColor(this);
			trophy.bigValue(this);
			
		}

		else if (trophy instanceof TrophyBestJestNoJoke) 
		{
			trophy.visitJest(this);
			trophy.bigColor(this);
			trophy.bigValue(this);
		}

		else if (trophy instanceof TrophyJoker) 
		{
			trophy.visitJest(this);
			
		}

	}

	/**Methode winMajority
	 * appel�e dans la m�thode giveTrophy @see Game#giveTrophy lors de l'attribution d'un TrophyMajority.
	 * @param p
	 * @param t
	 * @param majCandidates
	 * @param majPlayer
	 * @param myEntry
	 * @return renvoie des f�licitations (string) au joueur, lui pr�cisant pourquoi il gagne et son jest mis � jour avec le troph�e remport�.
	 */
	public String winMajority(Player p, Card t, Map<Integer,Integer> majCandidates, Map<Player,Entry<Integer, Integer>> majPlayer,
			Map.Entry<Integer,Integer> myEntry)
	{
		if(t.getTrophy().getMajCandidate() == 0)
		{
			String loose = ("Vous n'avez aucune carte de la m�me valeur " + p.getPseudo() + " !\n") ;

			System.out.println(loose) ;
		}

		else
		{
			System.out.println(p.getPseudo() + " voici votre nombre de " + t.getValue() 
			+ " : " + t.getTrophy().getMajCandidate() + "\n") ;
			/**
			 * coeff le plus �lev� et nombre de cartes mis dans une liste
			 * */
			majCandidates.put(t.getTrophy().bigCoeff, t.getTrophy().getMajCandidate());

			Iterator<Entry<Integer, Integer>> itr = majCandidates.entrySet().iterator(); 

			while(itr.hasNext()) 
			{ 
				Entry<Integer, Integer> entry = itr.next();
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
				+ ". Vous remportez le Troph�e ! Les cartes de votre Jest sont : " 
				+ ((Player) majPlayer.keySet().toArray()[0]).getJest().jestCards + "\n" );

		return win ;
	}

	/**M�thode winHighest
	 * appel�e dans la m�thode giveTrophy @see Game#giveTrophy lors de l'attribution d'un TrophyHighest.
	 * @param p
	 * @param t
	 * @param highCandidates
	 * @param valueComparator
	 * @param sortedHighCandidates
	 * @return renvoie des f�licitations (string) au joueur, lui pr�cisant pourquoi il gagne et son jest mis � jour avec le troph�e remport�.
	 */
	public String winHighest(Player p, Card t, Map<Player,Integer> highCandidates, Comparator<Integer> valueComparator,
			Map<Player, Integer> sortedHighCandidates)
	{
		if(t.getTrophy().getHighCandidate().getColor()
				.equals(t.getTrophy().getColor()) == false)
		{
			String loose = ("Vous n'avez aucune carte de la m�me couleur " + p.getPseudo() + " !\n") ;

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
				+ " vous remportez le Troph�e ! Les cartes de votre Jest sont : " 
				+ ((TreeMap<Player, Integer>) sortedHighCandidates).lastKey().getJest().jestCards + "\n" ) ;

		return win ;

	}

	/** M�thode winLowest
	 * appel�e dans la m�thode giveTrophy @see Game#giveTrophy lors de l'attribution d'un TrophyLowest.
	 * @param p
	 * @param t
	 * @param lowCandidates
	 * @param valueComparator
	 * @param sortedLowCandidates
	 * @return renvoie des f�licitations (string) au joueur, lui pr�cisant pourquoi il gagne et son jest mis � jour avec le troph�e remport�.
	 */
	public String winLowest(Player p, Card t, Map<Player,Integer> lowCandidates, Comparator<Integer> valueComparator,
			Map<Player, Integer> sortedLowCandidates)
	{
		if(t.getTrophy().getLowCandidate().getColor()
				.equals(t.getTrophy().getColor()) == false)
		{
			String loose = ("Vous n'avez aucune carte de la m�me couleur " + p.getPseudo() + " !\n") ;

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
				+ " vous remportez le Troph�e ! Les cartes de votre Jest sont : " 
				+ ((TreeMap<Player, Integer>) sortedLowCandidates).firstKey().getJest().jestCards + "\n" ) ;

		return win ;

	}

	/**M�thode winBestJest
	 * appel�e dans la m�thode giveTrophy @see Game#giveTrophy lors de l'attribution d'un TrophyBestJest ou TrophyBestJestNoJoke
	 * (le joueur poss�dant le Joker est d�termin� en amont dans la m�thode giveTrophy @see Game#giveTrophy).
	 * @param p
	 * @param t
	 * @param bestJestCandidates
	 * @param bestJestCandidates1
	 * @param bestJestValue
	 * @param bestJestColor
	 * @param bestJestPlayer
	 * @param myEntry
	 * @return renvoie des f�licitations (string) au joueur, lui pr�cisant pourquoi il gagne et son jest mis � jour avec le troph�e remport�.
	 */
	public String winBestJest(Player p, Card t, Map<Player,Integer> bestJestCandidates, Map<Player, Integer> bestJestCandidates1, 
			Map<Player,Entry<Player, Integer>> bestJestValue, Map<Player, Entry<Player, Integer>> bestJestColor, Map<Player, Integer> bestJestPlayer, 
			Entry<Player, Integer> myEntry)
	{

		System.out.println(p.getPseudo() + " voici la valeur de votre jest : " +
				t.getTrophy().getBestJestCandidate() + "\n") ;
		/**
		 * coeff le plus �lev� et nombre de cartes mis dans une liste
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
			Iterator<Entry<Player, Entry<Player, Integer>>> itrV = bestJestValue.entrySet().iterator();
			Iterator<Entry<Player, Entry<Player, Integer>>> itrC = bestJestColor.entrySet().iterator();

			while(itrV.hasNext() && itrC.hasNext())
			{
				Entry<Player, Entry<Player, Integer>> entryV = itrV.next();
				Entry<Player, Entry<Player, Integer>> entryC = itrC.next();

				int jestValueOnList = bestJestPlayer.get(((Player) bestJestPlayer.keySet().toArray()[0])) ;
				Player playerOnList = ((Player) bestJestPlayer.keySet().toArray()[0]) ;

				if(jestValueOnList < jestValue) 
				{
					bestJestValue.clear();
					bestJestValue.put(p, entry) ;

					bestJestColor.clear();
					bestJestColor.put(p, entry1) ;

					bestJestPlayer.clear();
					bestJestPlayer.put(p, jestValue); 
				}

				else if(entry.getValue() == jestValue && playerOnList != entry.getKey())
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

					else if(entryV.getValue().getValue() == entry.getValue() && playerOnList != entry.getKey())
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
				+ ". Vous remportez le Troph�e ! Les cartes de votre Jest sont : " 
				+ ((Player) bestJestValue.keySet().toArray()[0]).getJest().jestCards + "\n" ) ;

		return win ;

	}

	/**M�thode winJoker
	 * appel�e dans la m�thode giveTrophy @see Game#giveTrophy lors de l'attribution d'un TrophyJoker.
	 * @param p
	 * @param t
	 * @return renvoie des f�licitations (string) au joueur, lui pr�cisant pourquoi il gagne et son jest mis � jour avec le troph�e remport�.
	 */
	public String winJoker(Player p, Card t)
	{
		if(t.getTrophy().jokerCandidate == 1)
		{
			p.getJest().jestCards.add(t) ; 

			String win = ("Bravo Joueur " + p.getPseudo() + 
					" vous avez le Joker ! "
					+ "Vous remportez le Troph�e ! Les cartes de votre Jest sont : " 
					+ p.getJest().jestCards + "\n" ) ;
			return win ;

		}

		else
		{
			String loose = ("Vous n'avez pas le Joker Joueur " + p.getPseudo()) ;
			return loose ;
		}
	}


	/**M�thode acceptCount 
	 * appel�e dans la m�thode countPoints @see Game#countpoints lors de l'attribution du d�compte
	 * des points en fin de jeu. Ajoute successivement � l'ArrayList scoresTransition le pseudo du joueur puis son
	 * nombre de points.
	 * @param count
	 * @param p
	 * @param g
	 * @param i
	 */
	public void acceptCount(Count count, Player p, Game g, int i) 

	{ 

		System.out.println("\nJoueur "+ p.getPseudo() + " effectuons le d�compte de vos points ! \n"
				+ "Rappelons les cartes de votre jest : " + p.getJest().jestCards);
		p.setNbPoint(count.visitJest(this));
		System.out.println("Joueur "+ p.getPseudo() + " votre jest vaut " + p.getNbPoint() + " pts") ;
		g.getWinner().put(p.getPseudo(), p.getNbPoint());

		g.scoresTransition.add(p.getPseudo()) ;
		g.scoresTransition.add(Integer.toString(p.getNbPoint())) ;

	}


}






