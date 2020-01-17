package modele.carte;

import java.util.Iterator;

import modele.tas.Jest;

/**
 * <p>
 * La classe Trophy est la classe m�re des diff�rents troph�es attribuables en fin de partie.
 * Elle constitue �galement le visiteur des Jest et de ce fait d�finit la m�thode visitJest.
 * On y trouve �galement les m�thodes bigColor et bigValue d�terminant respectivement la carte de plus forte 
 * couleur (pour une certaine valeur avec TrophyMajority) et la carte de plus forte valeur du jest du joueur.
 * </p>
 * Trophy est caract�ris� par : 
 * <ul>
 * <li> une Value value pour les Trophy s�lectifs sur les valeurs des cartes @see {@link Card#Card()} </li>
 * <li> une Color color instanci�e pour les Trophy s�lectifs sur les couleurs des cartes @see {@link Card#Card()} </li>
 * <li> un attribut highCandidate associ� � TrophyHighest @see {@link TrophHighest} </li>
 * <li> un attribut lowCandidate associ� � TrophyLowest @see {@link TrophyLowest} </li>
 * <li> un attribut majCandidate associ� � TrophyMajority @see {@link TrophyMajority} </li>
 * <li> un attribut jokerCandidate associ� � TrophyJoker @see {@link TrophyJoker} </li>
 * <li> un attribut bestJestCandidate associ� � TrophyBestJest @see {@link TrophyBestJest} {@link TrophyBestJestNoJoke} </li>
 * <li> un attribut bigCoeff retourn� par la m�thode bigColor @see {@link Trophy#bigColor(Jest)} {@link Trophy#bigColor(Jest, Value)} </li>
 * <li> un attribut bigValue retourn� par la m�thode bigValue @see {@link Trophy#bigValue(Jest)} </li>
 * </ul>
 *
 */
public class Trophy 
{
	Value value ;
	Color color ;

	private Card highCandidate = new Card(value, color);

	private Card lowCandidate = new Card(value, color);

	private int majCandidate ;

	private int bestJestCandidate;

	public int jokerCandidate ; 

	public int bigCoeff ;
	public int bigValue ;



	/**
	 * Constructeur de Trophy
	 */
	public Trophy() 
	{
	}
	// TODO Auto-generated constructor stub

	/**
	 * Constructeur de Trophy (sp�cifique � TrophyHighest/Lowest)
	 * @param color
	 */
	public Trophy(Color color) 
	{
		this.color = color ;
	}

	/**
	 * Constructeur de Trophy (sp�cifique � TrophyMajority)
	 * @param value
	 */
	public Trophy(Value value) 
	{
		this.value = value ;
	}

	/**M�thode visitJest
	 * d�finit dans les classes filles TrophyBestJest, TrophyBestJestNoJoke, TrophyJoker
	 * @see {@link TrophyBestJest} {@link TrophyBestJestNoJoke} {@link TrophyJoker} 
	 * @param jest
	 */
	public void visitJest(Jest jest) {
		// TODO Auto-generated method stub

	}


	/**M�thode visitJest
	 * d�finit dans les classes filles TrophyHighest/Lowest
	 * @see {@link TrophyHighest} {@link TrophyLowest}
	 * @param jest
	 * @param color
	 */
	public void visitJest(Jest jest, Color color) 
	{
		// TODO Auto-generated method stub
	}

	/**M�thode visitJest
	 * d�finit dans la classes filles TrophyMajority
	 * @see {@link TrophyMajority}
	 * @param jest
	 * @param value
	 */
	public void visitJest(Jest jest, Value value) 
	{
		// TODO Auto-generated method stub
	}

	/**M�thode bigColor
	 * permet de trouver la carte d'une valeur sp�cifique de plus forte couleur dans le jest d'un joueur
	 * (utile lors d'une �galit� sur la valeur pour l'attribution d'un TrophyMajority o� il faut alors 
	 * remettre le Trophy au joueur avec la carte de valeur X de plus forte couleur)
	 * @param jest
	 * @param value
	 */
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

	/**
	 * M�thode bigColor
	 * permet de trouver la carte de plus forte couleur parmi les cartes de plus fortes valeurs du jest d'un joueur.
	 * Elle est utile lors d'une �galit� sur la valeur pour l'attribution d'un TrophyBestJest (l'attribut bigValue s'est vu alors assign� la valeur de la plus 
	 * forte carte du jest du joueur) il faut alors remettre le Trophy au joueur avec le Jest ayant la carte de plus forte couleur.
	 * @param jest
	 */
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

	/**
	 * M�thode bigValue
	 * permet de trouver la carte de plus forte valeur du jest d'un joueur
	 * (utile lors d'une �galit� sur le total des points pour l'attribution d'un TrophyBestJest 
	 * o� il faut alors remettre le Trophy au joueur avec le Jest ayant la carte de plus forte valeur)
	 * @param jest
	 */
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
