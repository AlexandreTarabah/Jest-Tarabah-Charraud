package jest_tarabah_charraud_2019_2020;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

public class CountClassique implements Count {

	public CountClassique()
	{
	}

	public int visitJest(Jest jest)
	{
		Integer jestValue = 0 ; 

		HashMap<Color, Value> CV = new HashMap<Color, Value>() ;

		for (Iterator<Card> it = jest.jestCards.iterator() ; it.hasNext();)
		{
			Card card = it.next();
			CV.put(card.color, card.value);

			// Spades and Clubs always increase the value of your Jest by their face value.
			if(card.color == Color.spade ||
					card.color == Color.club)
			{
				System.out.println("+ " + card.value.getCardValue() + " pt avec votre " + card.value.toString() + " de " + card.color.toString()) ;
				jestValue += card.value.getCardValue() ;
			}

			// Diamonds always reduce the value of your Jest by their face value
			if(card.color == Color.diamond)
			{
				System.out.println("- " + card.value.getCardValue() + " pt avec votre " + card.value.toString() + " de " + card.color.toString()) ;
				jestValue -= card.value.getCardValue() ;
			}

			// Hearts are worth nothing unless you have the Joker (cf ci-après)
			if(card.color == Color.heart)
			{
				System.out.println("+ 0 pt avec votre " + card.value.toString() + " de " + card.color.toString()) ;
				jestValue += 0 ;
			}

			if(card.color == Color.joker)
			{
				System.out.println("+ 0 pt avec votre " + card.color.toString()) ;
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
			System.out.println("Vous avez le Joker et les 4 cartes de coeur ! Vous remportez de ce fait : ") ;
			for (Iterator<Card> it = jest.jestCards.iterator() ; it.hasNext();)
			{
				Card card = it.next() ;

				if(card.color == Color.heart)
				{
					System.out.println("+ " + card.value.getCardValue() + " avec votre " + card.color.toString() + " de " + card.value.toString()) ;
					jestValue += card.value.getCardValue()  ;
				}

			}
		}

		/* If you have the Joker and no Hearts, the Joker is
		worth a bonus 4 points.*/
		else if(occurrencesHeart == 0 && occurrencesJoker == 1)
		{
			System.out.println("Vous avez le Joker et pas de cartes de coeur ! Vous remportez 4 pts bonus. ") ;
			jestValue += 4  ;
		}

		/*If you have the Joker and 1, 2 or 3 Hearts, the
		Joker is worth nothing and every Heart reduces the
		value of your Jest by its face value.*/
		else if(occurrencesHeart > 0 && occurrencesHeart < 4 && occurrencesJoker == 1)
		{
			System.out.println("Vous n'avez pas le Joker et seulement " + occurrencesHeart + " cartes de coeur ! Vous perdez de ce fait : ") ;
			for (Iterator<Card> it = jest.jestCards.iterator() ; it.hasNext();)
			{
				Card card = it.next() ;

				if(card.color == Color.heart)
				{
					System.out.println("- " + card.value.getCardValue() + " avec votre " + card.value.toString() + " de " + card.color.toString()) ;
					jestValue -= card.value.getCardValue();
				}

			}
		}

		/*If you have an Ace which is the only card of that suit in
		your Jest, the card becomes a 5, with a face value of 5.
		Otherwise it remains an Ace, with a face value of 1.*/
		for (Iterator<Card> it = jest.jestCards.iterator() ; it.hasNext();)
		{
			Card card = it.next() ;

			if(card.value.getCardValue() == 1 && 
					Collections.frequency(jest.jestCards, card.color) == 1)

			{
				System.out.println("Vous avez un as de " + card.color.toString() + ", c'est votre seule carte de " + card.color.toString()) ;

				if(card.color.equals(Color.heart))
				{
					if(occurrencesHeart == 1 && occurrencesJoker == 1)
					{
						System.out.println("Quelle malchance, cette carte vous ne fais pas perdre 1 pt mais 5 ! ");
						jestValue -= 4 ; // -5 + 1 = -4

					}
					else
					{
						System.out.println("Cela n'influe pas sur le décompte de vos pts.");
					}
				}

				if(card.color.equals(Color.spade) || card.color.equals(Color.club))
				{
					System.out.println("Quelle chance, cette carte vous ne fais pas gagner 1 pt mais 5 !");
					jestValue += 4 ; // 5 - 1 = 4
				}

				if(card.color.equals(Color.diamond))
				{
					System.out.println("Quelle malchance, cette carte vous ne fais pas perdre 1 pt mais 5 ! ");
					jestValue -= 4 ; // -5 + 1 = -4
				}
			}
		}


		/*If you have a Spade and a Club with the same
		face value, the pair is worth a bonus 2 points in
		addition to the face values of the cards.*/
		if(CV.keySet().contains(Color.spade) && CV.keySet().contains(Color.club))
		{
			ArrayList<Value> pairsDone = new ArrayList<Value>();
			for(Iterator<Card> it = jest.jestCards.iterator(); it.hasNext();)
			{
				Card card1 = it.next() ;


				if (card1.color == Color.spade && pairsDone.contains(card1.value) == false)
				{
					for(Iterator<Card> itg = jest.jestCards.iterator(); itg.hasNext();)
					{
						Card card2  = itg.next() ;

						if (card2.color == Color.club)
						{
							if(card1.value == card2.value)
							{
								System.out.println("Quelle chance, vous avez une paire noire, vous remportez un bonus de 2 pts ! ");
								jestValue += 2 ;
								pairsDone.add(card1.value);
							}
						}
					}
				}

				if (card1.color == Color.club && pairsDone.contains(card1.value) == false)
				{
					for(Iterator<Card> itg = jest.jestCards.iterator(); itg.hasNext();)
					{
						Card card2  = itg.next() ;

						if (card2.color == Color.spade)
						{
							if(card1.value == card2.value)
							{
								System.out.println("Quelle chance, vous avez une paire noire, vous remportez un bonus de 2 pts ! ");
								jestValue += 2 ;
								pairsDone.add(card1.value);
							}
						}
					}
				}
			}
		}

		return jestValue ;

	}

}
