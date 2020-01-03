package modele.carte;


//carotte

public class Card 
{

	private boolean ace;

	private boolean faceUp;

	Color color ;

	Value value;
	
	

	public Trophy trophy ;
	
	public Card()
	{
		
	}


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
			this.trophy = new TrophyJoker() ;
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



