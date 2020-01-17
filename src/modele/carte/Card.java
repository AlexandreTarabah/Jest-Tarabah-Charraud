package modele.carte;

/**
 * Cette classe correspond à une carte, elle permet de générer une carte en fonction de sa Value et de sa Couleur.
 * De plus, Elle instancie pour chaque carte son trophée 
 * 
 * Elle est liée au enum Color et Value
 */

public class Card 
{

	

	Color color ;

	Value value;
	
	

	public Trophy trophy ;
	
	/**
	 * Le constructeur sans paramètres 
	 */
	
	public Card()
	{
		
	}

	/**
	 * Le constructeur avec paramètres : 
	 * @param value qui correspond à la valeur de la carte 
	 * @param color qui correspond à la couleur de la carte 7
	 * En fonction des paramètres on instancie la bonne carte et son trophée
	 */

	public Card (Value value, Color color) {
		this.color=color;
		this.value=value;
		if (this.color == Color.spade)
		{
			if(this.value == Value.un)
			{
				this.trophy = new TrophyHighest(Color.club) ;
			}
			if(this.value == Value.deux)
			{
				this.trophy = new TrophyMajority(Value.trois) ;
			}
			if(this.value == Value.trois)
			{
				this.trophy = new TrophyMajority(Value.deux) ;
			}
			if(this.value == Value.quatre)
			{
				this.trophy = new TrophyLowest(Color.club) ;
			}
		}
		else if (this.color == Color.club)
		{
			if(this.value == Value.un)
			{
				this.trophy = new TrophyHighest(Color.spade) ;
			}
			if(this.value == Value.deux)
			{
				this.trophy = new TrophyLowest(Color.heart) ;
			}
			if(this.value == Value.trois)
			{
				this.trophy = new TrophyHighest(Color.heart) ;
			}
			if(this.value == Value.quatre)
			{
				this.trophy = new TrophyLowest(Color.spade) ;
			}
		}
		else if (this.color == Color.heart)
		{
			this.trophy = new TrophyJoker();
		}
		else if (this.color == Color.diamond)
		{
			if(this.value == Value.un)
			{
				this.trophy = new TrophyMajority(Value.quatre) ;
			}
			if(this.value == Value.deux)
			{
				this.trophy = new TrophyHighest(Color.diamond) ;
			}
			if(this.value == Value.trois)
			{
				this.trophy = new TrophyLowest(Color.diamond) ;
			}
			if(this.value == Value.quatre)
			{
				this.trophy = new TrophyBestJestNoJoke() ;
			}
		}

	}


	
	
	public Color getColor() {
		return color;
	}


	
	public void setColor(Color color) {
		this.color=color;

	}

	

	public Value getValue() {
		return value;
	}


	
	public void setValue(Value value) {
		this.value=value;
	}

	
	public Trophy getTrophy()
	{
		return trophy ;
	}

	

	public String toString() 
	{
		return "value : " + this.value.toString() + "color : " + this.color.toString() ;
	}


}



