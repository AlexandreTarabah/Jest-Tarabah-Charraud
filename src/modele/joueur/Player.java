package modele.joueur;
import modele.carte.Card;
import modele.game.Game;
import modele.tas.Jest;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;

/**
 * <b>Player est la classe représentant un joueur de la partie de JEST.</b>
 * <p>
 * Un joueur est caractérisé par les informations suivantes :
 * <ul>
 * <li>L'objet joueur qui dis qui joue</li>
 * <li>Un pseudo, qui est demandé en début de partie.</li>
 * <li>un boolean isAThief;</li>
 * * <li>un String stolenCard qui détermine la carte qui va etre volée</li>
 * * <li> boolean HasStolen qui détermine si il a déja volé</li>
 * * <li>un objet jest qui est son jest</li>
 * <li>Son nombre de point nbPoint</li>
 * <li>Son nombre de carte dans l'offre nbCardOffer</li>
 * <li> le pseudo de sa victime choiceVictime</li>
 </ul>
 * </p>
 * <p>
 * De plus, un Joueur a une offre (list) et une main(list).
 * </p>
 */

public class Player 
{
	private Player isPlaying;

	private String pseudo;

	public boolean isAThief;

	protected HashMap<String, Card> offer;

	protected String stolenCard;

	public boolean HasStolen=false;

	protected LinkedList<Card> hand = new LinkedList<Card>();

	protected Jest jest ;

	boolean firstPlayer = false;

	private int nbPoint;

	public int nbCardOffer;

	private String choiceVictime;

	protected String choiceVictimeBot;

	/**
	 * Dans le constructeur du joueur on met a jour : 
	 * <ul>
	 * <li> son pseudo </li>
	 * <li> son offre </li>
	 * <li> on instancie son jest</li>
	 * <li>on ajoute le joueur a la liste de joueur @see {@link Game#players} </li>
	 * <li>On ajoute le joueur a la hashmap ForMainPlay (pseudo, player) @see {@link Game#ForMainPlay} </li>
	 * </ul>
	 * @param pseudo
	 * @param g
	 */

	public Player (String pseudo, Game g) 
	{
		this.pseudo=pseudo;
		this.setOffer(new HashMap<String, Card>());
		this.jest = new Jest();
		g.players.add(this);
		g.ForMainPlay.put(this.getPseudo(), this);

	}

/**
 * La méthode stealCard qui permet au joueur de volé une carte au joueur rentré en paramètre 
 * On prend la carte dans l'offre du joueur Victime et on l'ajoute au jest du joueur qui joue
 * On détermine ensuite la prochaine victime selon l'algorithme de recherche qui détermine le prochain joueur selon les règles du jeu et le nombre de joueur 
 * @param choiceVictime
 * @param choiceCardVictime
 * @param g
 */

	public void stealCard(String choiceVictime,String choiceCardVictime, Game g) {
		nbCardOffer=0;
		for(Entry<String, HashMap<String, Card>> map : g.listOffer.entrySet()) {


			{nbCardOffer = nbCardOffer+map.getValue().size();}
			g.nbCardOffer=nbCardOffer;
		}


		g.setVictime(choiceVictime);
		this.choiceVictime=choiceVictime; // set la victime à mettre à jour visuellement et non dans la séquenciation

		String stolenCard = choiceCardVictime;


		this.jest.jestCards.add(g.listOffer.get(g.getVictime()).get(stolenCard));
		g.listOffer.get(g.getVictime()).remove(stolenCard);// méthode AddJest() implementé dans Jest.

		this.HasStolen=true; 

		if(g.getForMainPlay().get(g.getVictime()).HasStolen==true) { // Dans le cas ou le joueur vole le voleur précédent, on fixe la prochaine victime au joueur qui a l'offre complete. 


			if(g.nbPlayers==3) {
				for (HashMap.Entry<String,Player> mapentry : g.getForMainPlay().entrySet()) {
					if (mapentry.getValue().getOffer().size()==2) {

						g.setVictime(mapentry.getKey());

					}
				}
			}else
				if(g.nbPlayers==4) {

					int highestCardValue = 0;
					int highestColorValue = 0;

					for (HashMap.Entry<String,Player> mapentry2 : g.getForMainPlay().entrySet()) {
						if (mapentry2.getValue().getOffer().size()==2) {
							if(highestCardValue <  mapentry2.getValue().getOffer().get("up").getValue().getCardValue())
							{
								highestCardValue = mapentry2.getValue().getOffer().get("up").getValue().getCardValue();
								highestColorValue = mapentry2.getValue().getOffer().get("up").getColor().getColorValue();
								g.setVictime(mapentry2.getKey());
							}

							if(highestCardValue == mapentry2.getValue().getOffer().get("up").getValue().getCardValue() && 
									highestColorValue < mapentry2.getValue().getOffer().get("up").getColor().getColorValue()) {

								g.setVictime(mapentry2.getKey());

							}		
						}
					}
				}
		}

		nbCardOffer-=1;
	}









/**
 *retourne la main du joueur 
 * @return hand
 */


	public LinkedList<Card> getHand() {
		return hand;
	}








	/**
	 * Méthode qui permet au joueur de retourné la carte de son choix 
	 * Son choix est matérialisé par numC qui représente le choix du joueur  
	 * En fonction de son choix, place dans l'offre la carte choisi avec la clé down et l'autre avec up 
	 * @param choice 
	 * @param g
	 */
	public void upsideDown(int choice, Game g) 
	{		this.isPlaying=this;

	int numC =0;
	numC =choice + 1; 


	((Map<String, Card>) offer).put("down", this.hand.get(numC-1));   
	((Map<String, Card>) offer).put("up", this.hand.get(numC%2)); 

	
	g.listOffer.put(this.getPseudo(), this.getOffer());





	}



/**
 * 
 * @return le jest
 */


	public Jest getJest()
	{
		return jest ;
	}



/**
 * @return le pseudo du joueur
 */


	public String getPseudo() {
		return pseudo;
	}



/**
 * met à jour le pseudo du joueur
 * @param pseudo
 */


	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}



/**
 * 
 * @return le nombre de point 
 */


	public int getNbPoint() {
		return nbPoint;
	}
	
	public void setNbPoint(int nbPoint) {
		this.nbPoint=  nbPoint;
	}



/**
 * 
 * @return l'offre du joueur 
 */

	public HashMap<String, Card> getOffer() {
		return offer;
	}


/**
 * modifie l'offre du joueur 
 * @param offer
 */



	public void setOffer(HashMap<String, Card> offer) {
		this.offer = offer;
	}

	/**
	 * 
	 * @return la carte choisit pour être volée 
	 */

	public String getStolenCard() {
		return stolenCard;
	}

/**
 * 
 * @return si le joueur a déja volé 
 */

	public boolean isHasStolen() {
		return HasStolen;
	}

/**
 * 
 * @return le joueur entrain de jouer
 */
	public Player getIsPlaying() {
		return isPlaying;
	}

/**
 * Met a jour le statut "a volé" du joueur
 * @param hasStolen
 */

	public void setHasStolen(boolean hasStolen) {
		HasStolen = hasStolen;
	}
	
	/**
	 * 
	 * @return le nombre de carte dans l'offre du joueur
	 */

	public int getNbCardOffer() {
		return nbCardOffer;
	}

/**
 * 
 * @return le choix de victime
 */

	public String getChoiceVictime() {
		// TODO Auto-generated method stub
		return choiceVictime;
	}

	/**
	 * 
	 * @return le choix de la victime, choix du bot 
	 */


	public String getChoiceVictimeBot() {
		// TODO Auto-generated method stub
		return choiceVictimeBot;
	}


}