package jest_tarabah_charraud_2019_2020;



public class Card {

	private boolean ace;

	private boolean faceUp;

	private Color color ;

	private Value value;

	private Trophy trophy ;


	public Card (Value value, Color color) {
		this.color=color;
		this.value=value;
		if (this.color == Color.spade)
		{
			if(this.value == Value.un)
			{
				this.trophy = new TrophyHighest() ;
			}
			if(this.value == Value.deux)
			{
				this.trophy = new TrophyMajority() ;
			}
			if(this.value == Value.trois)
			{
				this.trophy = new TrophyMajority() ;
			}
			if(this.value == Value.quatre)
			{
				this.trophy = new TrophyLowest() ;
			}
		}
		else if (this.color == Color.club)
		{
			if(this.value == Value.un)
			{
				this.trophy = new TrophyHighest() ;
			}
			if(this.value == Value.deux)
			{
				this.trophy = new TrophyLowest() ;
			}
			if(this.value == Value.trois)
			{
				this.trophy = new TrophyHighest() ;
			}
			if(this.value == Value.quatre)
			{
				this.trophy = new TrophyLowest() ;
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
				this.trophy = new TrophyMajority() ;
			}
			if(this.value == Value.deux)
			{
				this.trophy = new TrophyHighest() ;
			}
			if(this.value == Value.trois)
			{
				this.trophy = new TrophyLowest() ;
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


	public String toString() {
		return "value :"+ value.toString() + "color :"+ color.toString();

	}

}
;


