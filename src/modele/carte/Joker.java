package modele.carte;
/**
 * Voici une classe particuli�re pour le joker, comme il poss�de des attributs particuliers.
 * Dans le constructeur, on met � jour son troph�e, sa couleur et sa valeur 
 * 
 *
 */
public class Joker extends Card{
	
	public Joker() {

		super();
		this.trophy = new TrophyBestJest() ;
		super.color = Color.joker;
		super.value = Value.un; 
	}

}