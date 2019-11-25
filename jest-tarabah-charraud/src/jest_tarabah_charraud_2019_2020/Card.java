package jest_tarabah_charraud_2019_2020;

//carotte

public class Card implements Cloneable {

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
				this.trophy = new TrophyHighest(Color.club) ;
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
				this.trophy = new TrophyHighest(Color.spade) ;
			}
			if(this.value == Value.deux)
			{
				this.trophy = new TrophyLowest() ;
			}
			if(this.value == Value.trois)
			{
				this.trophy = new TrophyHighest(Color.heart) ;
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
				this.trophy = new TrophyHighest(Color.diamond) ;
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

//yep
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

	
	public Card clone() {
		Object c = null;
		try {
			// On r�cup�re l'instance � renvoyer par l'appel de la 
			// m�thode super.clone()
			c = super.clone();
		} catch(CloneNotSupportedException cnse) {
			// Ne devrait jamais arriver car nous impl�mentons 
			// l'interface Cloneable
			cnse.printStackTrace(System.err);
		}
		// on renvoie le clone
		
		
		return (Card) c;
	}
}
;


