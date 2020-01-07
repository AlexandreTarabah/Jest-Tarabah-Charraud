package modele.joueur;
import modele.carte.Card;
import modele.game.Game;
import modele.tas.Jest;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;

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

	//j'instancie l'objet offre, qui est aussi une collection de carte, dans le constructeur player ici 

	public Player (String pseudo, Game g) 
	{
		this.pseudo=pseudo;
		this.setOffer(new HashMap<String, Card>());
		this.jest = new Jest();
		g.players.add(this);
		g.ForMainPlay.put(this.getPseudo(), this);

	}



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












	public LinkedList<Card> getHand() {
		return hand;
	}








	// la c'est la méthode pour 
	public void upsideDown(int choice, Game g) 
	{		this.isPlaying=this;

	int numC =0;
	numC =choice + 1; // demande au joueur de rentrer un numéro entre 1 et 2


	((Map<String, Card>) offer).put("down", this.hand.get(numC-1)); // -1 car le tableau commence à l'indice 0, je caste l'offer  
	((Map<String, Card>) offer).put("up", this.hand.get(numC%2)); // avec le modulo 2 on obtient la case manquante, je caste l'offer

	/* et la on affiche le pseudo du player en paramètre, avec get(Down) et la value de la carte, et la couleur
	 */

	g.listOffer.put(this.getPseudo(), this.getOffer()); // on ajoute l'offre du player a la listOffer.


	/* et la on affiche le pseudo du player en paramètre, avec get(Down) et la value de la carte, et la couleur
	 */



	}






	public Jest getJest()
	{
		return jest ;
	}






	public String getPseudo() {
		return pseudo;
	}






	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}






	public int getNbPoint() {
		return nbPoint;
	}






	public void setNbPoint(int nbPoint) {
		this.nbPoint = nbPoint;
	}






	public HashMap<String, Card> getOffer() {
		return offer;
	}






	public void setOffer(HashMap<String, Card> offer) {
		this.offer = offer;
	}


	public String getStolenCard() {
		return stolenCard;
	}



	public boolean isHasStolen() {
		return HasStolen;
	}


	public Player getIsPlaying() {
		return isPlaying;
	}



	public void setHasStolen(boolean hasStolen) {
		HasStolen = hasStolen;
	}

	public int getNbCardOffer() {
		return nbCardOffer;
	}



	public String getChoiceVictime() {
		// TODO Auto-generated method stub
		return choiceVictime;
	}



	public String getChoiceVictimeBot() {
		// TODO Auto-generated method stub
		return choiceVictimeBot;
	}


}