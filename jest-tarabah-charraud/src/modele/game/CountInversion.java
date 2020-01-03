package modele.game;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

import modele.carte.Card;
import modele.carte.Color;
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
			Card card = it.next() ;
			CV.put(card.getColor(), card.getValue()) ;

			// Spades and Clubs always increase the value of your Jest by their face value.
			if(card.getColor() == Color.spade ||
					card.getColor() == Color.club)
			{
				jestValue += card.getValue().getCardValue() ;
			}

			// Diamonds always reduce the value of your Jest by their face value
			if(card.getColor() == Color.diamond)
			{
				jestValue -= card.getValue().getCardValue() ;
			}

			// Hearts are worth nothing unless you have the Joker (cf ci-après)
			if(card.getColor() == Color.heart || card.getColor() == Color.joker)
			{
				jestValue += 0 ;
			}
		}

		// Joker & Hearts 
		int occurrencesHeart = Collections.frequency(CV.keySet(), Color.heart) ;
		int occurrencesJoker = Collections.frequency(CV.keySet(), Color.joker) ;

		/*If you have the Joker and all 4 Hearts, the Joker
		is worth nothing but every Heart increases the value
		of your Jest by its face value. (In a 4-player game,
		this score is only possible if the trophy is either the
		Joker or a Heart) */
		if(occurrencesHeart == 4 && occurrencesJoker == 1)
		{
			for (Iterator<Card> it = jest.jestCards.iterator() ; it.hasNext();)
			{
				Card card = it.next() ;

				if(card.getColor() == Color.heart)
				{
					jestValue += card.getValue().getCardValue()  ;
				}

			}
		}

		/* If you have the Joker and no Hearts, the Joker is
		worth a bonus 4 points.*/
		else if(occurrencesHeart == 0 && occurrencesJoker == 1)
		{

			jestValue += 4  ;
		}

		/*If you have the Joker and 1, 2 or 3 Hearts, the
		Joker is worth nothing and every Heart reduces the
		value of your Jest by its face value.*/
		else if(occurrencesHeart > 0 && occurrencesHeart < 4 && occurrencesJoker == 1)
		{
			for (Iterator<Card> it = jest.jestCards.iterator() ; it.hasNext();)
			{
				Card card = it.next() ;

				if(card.getColor() == Color.heart)
				{
					jestValue -= card.getValue().getCardValue()  ;
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

				if(card.getColor().equals(Color.heart))
				{
					jestValue += 5 ; // 5 - 0 = 5 (0 est la valeur usuelle des coeurs)

					if(card.getColor().equals(Color.spade) || card.getColor().equals(Color.club))
					{
						jestValue += 4 ; // 5 - 1 = 4
					}

					if(card.getColor().equals(Color.diamond))
					{
						jestValue += 6 ; // 5 - (-1) = 6
					}
				}
			}



		}

		/*If you have a Spade and a Club with the same
		face value, the pair is worth a bonus 2 points in
		addition to the face values of the cards.*/
		if(CV.keySet().contains(Color.spade) && CV.keySet().contains(Color.club))
		{
			for(Iterator<Card> it = jest.jestCards.iterator(); it.hasNext();)
			{
				Card card1 = it.next() ;

				if (card1.getColor() == Color.spade || card1.getColor() == Color.club)
				{
					for(Iterator<Card> itg = jest.jestCards.iterator(); itg.hasNext();)
					{
						Card card2  = itg.next() ;

						if(card1.getValue() == card2.getValue())
						{
							jestValue += 2 ;
						}
					}
				}
			}
		}
		
		return jestValue ;

	}

}
 