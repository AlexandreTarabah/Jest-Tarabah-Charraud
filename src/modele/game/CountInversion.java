package modele.game;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

import modele.carte.Color;
import modele.carte.Card;
import modele.carte.Value;
import modele.tas.Jest;

public class CountInversion implements Count {

	public CountInversion()
	{
	}

	public int visitJest(Jest jest)
	{
		Integer jestValue = 0 ; 

		HashMap<Color, Value> CV = new HashMap<Color, Value>() ;

		for (Iterator<Card> it = jest.jestCards.iterator() ; it.hasNext();)
		{
			Card card = it.next();
			CV.put(card.getColor(), card.getValue());

			// hearts and diamonds always increase the value of your Jest by their face value.
			if(card.getColor() == Color.heart ||
					card.getColor() == Color.diamond)
			{
				System.out.println("+ " + card.getValue().getCardValue() + " pt avec votre " + card.getValue().toString() + " de " + card.getColor().toString()) ;
				jestValue += card.getValue().getCardValue() ;
			}

			// clubs always reduce the value of your Jest by their face value
			if(card.getColor() == Color.club)
			{
				System.out.println("- " + card.getValue().getCardValue() + " pt avec votre " + card.getValue().toString() + " de " + card.getColor().toString()) ;
				jestValue -= card.getValue().getCardValue() ;
			}

			// spades are worth nothing unless you have the Joker (cf ci-après)
			if(card.getColor() == Color.spade)
			{
				System.out.println("+ 0 pt avec votre " + card.getValue().toString() + " de " + card.getColor().toString()) ;
				jestValue += 0 ;
			}

			if(card.getColor() == Color.joker)
			{
				System.out.println("+ 0 pt avec votre " + card.getColor().toString()) ;
				jestValue += 0 ;
			}
		}

		// Joker & spades 
		int occurrencesSpade = Collections.frequency(CV.keySet(), Color.spade) ;
		int occurrencesJoker = Collections.frequency(CV.keySet(), Color.joker) ;

		/*If you have the Joker and all 4 spades, the Joker
		is worth nothing but every spade increases the value
		of your Jest by its face value. (In a 4-player game,
		this score is only possible if the trophy is either the
		Joker or a spade) */
		if(occurrencesSpade == 4 && occurrencesJoker == 1)
		{
			System.out.println("Vous avez le Joker et les 4 cartes de pique ! Vous remportez de ce fait : ") ;
			for (Iterator<Card> it = jest.jestCards.iterator() ; it.hasNext();)
			{
				Card card = it.next() ;

				if(card.getColor() == Color.spade)
				{
					System.out.println("+ " + card.getValue().getCardValue() + " avec votre " + card.getColor().toString() + " de " + card.getValue().toString()) ;
					jestValue += card.getValue().getCardValue()  ;
				}

			}
		}

		/* If you have the Joker and no spades, the Joker is
		worth a bonus 4 points.*/
		else if(occurrencesSpade == 0 && occurrencesJoker == 1)
		{
			System.out.println("Vous avez le Joker et pas de cartes de pique ! Vous remportez 4 pts bonus. ") ;
			jestValue += 4  ;
		}

		/*If you have the Joker and 1, 2 or 3 spades, the
		Joker is worth nothing and every spade reduces the
		value of your Jest by its face value.*/
		else if(occurrencesSpade > 0 && occurrencesSpade < 4 && occurrencesJoker == 1)
		{
			System.out.println("Vous avez le Joker et seulement " + occurrencesSpade + " cartes de pique ! Vous perdez de ce fait : ") ;
			for (Iterator<Card> it = jest.jestCards.iterator() ; it.hasNext();)
			{
				Card card = it.next() ;

				if(card.getColor() == Color.spade)
				{
					System.out.println("- " + card.getValue().getCardValue() + " avec votre " + card.getValue().toString() + " de " + card.getColor().toString()) ;
					jestValue -= card.getValue().getCardValue();
				}

			}
		}

		/*If you have an Ace which is the only card of that suit in
		your Jest, the card becomes a 5, with a face value of 5.
		Otherwise it remains an Ace, with a face value of 1.*/
		for (Iterator<Card> it = jest.jestCards.iterator() ; it.hasNext();)
		{
			Card card = it.next() ;

			if(card.getValue().getCardValue() == 1 && 
					Collections.frequency(jest.jestCards, card.getColor()) == 1)

			{
				System.out.println("Vous avez un as de " + card.getColor().toString() + ", c'est votre seule carte de " + card.getColor().toString()) ;

				if(card.getColor().equals(Color.spade))
				{
					if(occurrencesSpade == 1 && occurrencesJoker == 1)
					{
						System.out.println("Quelle malchance, cette carte vous ne fais pas perdre 1 pt mais 5 ! ");
						jestValue -= 4 ; // 5 - 0 = 5 (0 est la valeur usuelle des piques)

					}
					else
					{
						System.out.println("Cela n'influe pas sur le décompte de vos pts.");
					}
				}

				if(card.getColor().equals(Color.club) || card.getColor().equals(Color.diamond))
				{
					System.out.println("Quelle chance, cette carte vous ne fais pas gagner 1 pt mais 5 !");
					jestValue += 4 ; // 5 - 1 = 4
				}

				if(card.getColor().equals(Color.club))
				{
					System.out.println("Quelle malchance, cette carte vous ne fais pas perdre 1 pt mais 5 ! ");
					jestValue += 6 ; // 5 - (-1) = 6
				}
			}
		}


		/*If you have a Diamonds and Hearts with the same
		face value, the pair is worth a bonus 2 points in
		addition to the face values of the cards.*/
		if(CV.keySet().contains(Color.diamond) && CV.keySet().contains(Color.heart))
		{ArrayList<Value> pairsDone = new ArrayList<Value>();
			for(Iterator<Card> it = jest.jestCards.iterator(); it.hasNext();)
			{
				Card card1 = it.next() ;
				
				
				if (card1.getColor() == Color.diamond && pairsDone.contains(card1.getValue()) == false)
				{
					for(Iterator<Card> itg = jest.jestCards.iterator(); itg.hasNext();)
					{
						Card card2  = itg.next() ;

						if (card2.getColor() == Color.heart)
						{
							if(card1.getValue() == card2.getValue())
							{
								System.out.println("Quelle chance, vous avez une paire rouge, vous remportez un bonus de 2 pts ! ");
								jestValue += 2 ;
								pairsDone.add(card1.getValue());
							}
						}
					}
				}
				
				if (card1.getColor() == Color.heart && pairsDone.contains(card1.getValue()) == false)
				{
					for(Iterator<Card> itg = jest.jestCards.iterator(); itg.hasNext();)
					{
						Card card2  = itg.next() ;

						if (card2.getColor() == Color.diamond)
						{
							if(card1.getValue() == card2.getValue())
							{
								System.out.println("Quelle chance, vous avez une paire rouge, vous remportez un bonus de 2 pts ! ");
								jestValue += 2 ;
								pairsDone.add(card1.getValue());
							}
						}
					}
				}
			}
		}

		return jestValue ;

	}

} 
