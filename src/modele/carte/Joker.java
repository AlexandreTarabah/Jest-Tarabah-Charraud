package modele.carte;
/**
 * Voici une classe particulière pour le joker, comme il possède des attributs particuliers.
 * Dans le constructeur, on met à jour son trophée, sa couleur et sa valeur 
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