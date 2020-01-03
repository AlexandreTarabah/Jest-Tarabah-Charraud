package modele.carte;

public class Joker extends Card{
	
	public Joker() {

		super();
		this.trophy = new TrophyBestJest() ;
		super.color = Color.joker;
		super.value = Value.un; 
	}

}