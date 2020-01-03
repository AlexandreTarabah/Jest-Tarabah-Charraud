package modele.carte;

import java.util.Iterator;

import modele.tas.Jest;

public class Trophy 
{
	Value value ;
	Color color ;

	private Card highCandidate = new Card(value, color);

	private Card lowCandidate = new Card(value, color);

	private int majCandidate ;

	private int bestJestCandidate;

	public int bigCoeff ;
	public int bigValue ;

	public int jokerCandidate ; 

	public Trophy() 
	{
	}
	// TODO Auto-generated constructor stub

	public Trophy(Color color) 
	{
		this.color = color ;
	}

	public Trophy(Value value) 
	{
		this.value = value ;
	}

	public void visitJest(Jest jest) {
		// TODO Auto-generated method stub

	}


	public void visitJest(Jest jest, Color color) 
	{
		// TODO Auto-generated method stu
	}

	public void visitJest(Jest jest, Value value) 
	{
		// TODO Auto-generated method stu
	}

	public void bigColor(Jest jest, Value value)
	{
		Iterator<Card> itJC = jest.jestCards.iterator() ; // Entrance dans le
		// jest du joueur

		int bigCoeff = 0 ;

		while(itJC.hasNext())
		{
			Card card = (Card) itJC.next();

			if(card.getValue() == value)
			{	
				if(card.getColor().ordinal() > bigCoeff)
					bigCoeff = card.getColor().ordinal() ;
			} 
		}

		this.bigCoeff = bigCoeff ;
	}

	public void bigColor(Jest jest)
	{
		Iterator<Card> itJC = jest.jestCards.iterator() ; // Entrance dans le
		// jest du joueur

		int bigCoeff = 0 ;

		while(itJC.hasNext())
		{
			Card card = (Card) itJC.next();

			if(card.getValue().getCardValue() == this.bigValue)
			{	

				if(card.getColor().ordinal() > bigCoeff)
					bigCoeff = card.getColor().ordinal() ;

			}
		}


		this.bigCoeff = bigCoeff;
	}	

	public void bigValue(Jest jest)
	{
		Iterator<Card> itJC = jest.jestCards.iterator() ; // Entrance dans le
		// jest du joueur

		int bigValue = 0 ;

		while(itJC.hasNext())
		{
			Card card = (Card) itJC.next();

			if(card.getValue().getCardValue() > bigValue)
			{
				bigValue = card.getValue().getCardValue() ;
			}
		}

		this.bigValue = bigValue;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Value getValue() {
		return value ;
	}

	public Value setValue(Value value) {
		return value ;
	}

	public int getMajCandidate() {
		return majCandidate;
	}

	public void setMajCandidate(int majCandidate) {
		this.majCandidate = majCandidate;
	}

	public Card getHighCandidate() {
		return highCandidate;
	}

	public void setHighCandidate(Card highCandidate) {
		this.highCandidate = highCandidate;
	}

	public Card getLowCandidate() {
		return lowCandidate;
	}

	public void setLowCandidate(Card lowCandidate) {
		this.lowCandidate = lowCandidate;
	}

	public int getBestJestCandidate() {
		return bestJestCandidate;
	}

	public void setBestJestCandidate(int bestJestCandidate) {
		this.bestJestCandidate = bestJestCandidate;
	}
}
