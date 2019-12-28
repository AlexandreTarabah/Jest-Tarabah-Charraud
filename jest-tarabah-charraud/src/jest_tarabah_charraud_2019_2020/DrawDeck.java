package jest_tarabah_charraud_2019_2020;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class DrawDeck {

	public static int nbCardDD;


	private ArrayList<Card> drawdeck = new ArrayList<Card>();


	// j'ai pu simplifié le mélange des cartes avec shuffle directement implementé dans le java, Collections.shuffle et je mélange les cartes 

	public void shuffle() {

		Collections.shuffle(drawdeck);
	}



	public void revealTrophies() {
	}


	// la je créé le drawdeck qui est une liste de cartes.



	public DrawDeck(Game g) {

		drawdeck= new ArrayList<Card>();

		for (Color c : Color.values()) {

			if(c == Color.joker)
			{
				/*Card card = new Joker() ;
				drawdeck.add(card);*/
			}
			else
			{
				for(Value v : Value.values()) {

					Card card = new Card(v,c);
					drawdeck.add(card);
				}

			}
		}
		if(g.extension==false) { // Si on joue sans extension, on retire les 6 du jeu
			int verif6=6;
			for(int i=0; i<drawdeck.size();i++) {
				if(verif6==drawdeck.get(i).value.getCardValue())
				{drawdeck.remove(i);}
			}
		}
		// Si tu ajoutes le Joker ici, soit tu l'ajoutes après les removes, soit tu changes la position des removes pour que ça corresponde

	} 

	public Card takeCards() { // méthode pour prendre une carte.
		nbCardDD=drawdeck.size();
		int position = (int) ((int) Math.round(DrawDeck.nbCardDD-1)*Math.random());
		return drawdeck.remove(position);
	}



	public void collectCards(Player player) {  // ici on collecte les cartes pour les rebalancer dans le DD, sauf si DDsize<3 

		if(drawdeck.size()!=0) {

			if(player.offer.get("up")==null) {
				drawdeck.add(player.offer.get("down"));
			}else
				drawdeck.add(player.offer.get("up"));
		}else 


			if(player.offer.get("up")==null) {
				player.getJest().jestCards.add(player.offer.get("down"));
			}else
				player.getJest().jestCards.add(player.offer.get("up"));

	}





	public boolean isEmpty()
	{
		return drawdeck.isEmpty(); 
	}



	public int getSize() {
		// TODO Auto-generated method stub
		return drawdeck.size();
	}

}
