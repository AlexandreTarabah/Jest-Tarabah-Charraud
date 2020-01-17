package modele.tas;
import java.util.ArrayList;
import java.util.Collections;
import modele.joueur.Player;
import modele.carte.Card;
import modele.carte.Joker;
import modele.carte.Value;
import modele.carte.Color;
import modele.game.Game;
/**
 * Cette classe repr�sente le drawdeck de la partie 
 * il est caract�ris� par : 
 * <ul>
 * <li> son nombre de carte interne (sa taille) </li>
 * </ul>
 * il comporte une liste de carte drawdeck @see Drawdeck#drawdeck
 * @author zorro
 *
 */

public class DrawDeck {

	public static int nbCardDD;


	private ArrayList<Card> drawdeck = new ArrayList<Card>();


 /**
  * La m�thode shuffle() permet de m�langer les cartes gr�ce � Collections.shuffle(o)
  */

	public void shuffle() {

		Collections.shuffle(drawdeck);
	}


	/**
	 * Constructeur du Drawdeck 
	 * On instancie le drawdeck selon les valeurs et les couleurs des �num�rations de carte @see Card
	 * on instancie aussi le joker 
	 * Si l'extension n'est pas activ�e, on retire la famille de 6 pour avoir un jeu sans extension
	 * @param g
	 */


	public DrawDeck(Game g) {

		drawdeck= new ArrayList<Card>();

		for (Color c : Color.values()) {

			if(c == Color.joker)
			{
				Card card = new Joker() ;
				drawdeck.add(card) ;
			}
			else
			{
				for(Value v : Value.values()) {

					Card card = new Card(v,c);
					drawdeck.add(card);
				}

			}
		}
		if(g.getExtension()==false) { // Si on joue sans extension, on retire les 6 du jeu
			int verif6=6;
			for(int i=0; i<drawdeck.size();i++) {
				if(verif6==drawdeck.get(i).getValue().getCardValue())
				{drawdeck.remove(i);}
			}
		}

	} 

	/**
	 * M�thode qui permet de prendre une carte � une position al�atoire 
	 * @return la carte choisie 
	 */
	public Card takeCards() {
		nbCardDD=drawdeck.size();
		int position = (int) ((int) Math.round(DrawDeck.nbCardDD-1)*Math.random());
		return drawdeck.remove(position);
	}


/**
 * // ici on collecte les cartes pour les remettre dans le Drawdeck, sauf si DDsize=0, alors on place les cartes dans le jest des joueurs respectifs 
 * Si l'offre est null pour la cl� up alors on add au drawdeck la cl� down et vice-versa 
 * @param player
 */
	public void collectCards(Player player) { 

		if(drawdeck.size()!=0) {

			if(player.getOffer().get("up")==null) {
				drawdeck.add(player.getOffer().get("down"));
			}else
				drawdeck.add(player.getOffer().get("up"));
			player.getOffer().clear();
		}
		else 
		{

			if(player.getOffer().get("up")==null) {
				player.getJest().jestCards.add(player.getOffer().get("down"));
			}else
				player.getJest().jestCards.add(player.getOffer().get("up"));
		}
	}



	/**
	 * 
	 * @return si le drawdeck est vide 
	 */


	public boolean isEmpty()
	{
		return drawdeck.isEmpty(); 
	}

/**
 * 
 * @return la taille du drawdeck
 */

	public int getSize() {
		// TODO Auto-generated method stub
		return drawdeck.size();
	}



	

}
