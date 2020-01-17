package modele.joueur;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;
import modele.carte.Card;
import modele.game.Game;
/**
 * BotDown représente un Bot, qui fait que des choix Down 
 * Le BotDown a les mêmes caractéristiques que Player 
 * 
 * Le Bot est caractérisé par : 
 * <ul>
 * <li> un boolean isBot</li>
 * <li> Un String stolenCard qui peut-etre Up ou Down </li>
 * </ul>
 * 
 * De plus, BotDown extends la classe Player puisque le Bot est un Joueur avec ses propres méthodes. 
 * 
 * 
 */
public class BotDown extends Player implements Difficulty {
	
	/** 
	 * la caractéristique du Bot : c'est un bot donc isBot = true; Cette caractéristique ne se modifie pas 
	 */
	
	boolean isBot=true;
	/**
	 * Indique quelle carte est selectionnée par le Bot
	 * Ce String prend 2 valeurs : up ou down 
	 * @see BotDown#upsideDown(int, Game)
	 */
	private String stolenCard;

	/** 
	 * Constructeur du Bot hérité de Player
	 * @param pseudo
	 * @param g
	 */
	public BotDown(String pseudo, Game g) {
		super(pseudo, g);
	}

	/**
	 * Méthode upsideDown 
	 * @param choice
	 * @param g 
	 * 
	 * Cette méthode permet de retourner une carte dans la main du Bot 
	 * Ainsi, on définit son offre 
	 */
	
	public void upsideDown(int choice,Game g) {
		int numC = 1 ;

		try {
			Thread.sleep(1000);
			System.out.println(numC);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}



		((Map<String, Card>) offer).put("down", this.hand.get(numC-1));   
		((Map<String, Card>) offer).put("up", this.hand.get(numC%2)); 
		System.out.println(this.getPseudo()  + " a caché " + ((Map<String, Card>) getOffer()).get("down").getValue() + " de " + ((Map<String, Card>) getOffer()).get("down").getColor()+"\n");
		g.listOffer.put(this.getPseudo(), this.offer); 

	}


/**
 * Méthode stealCard qui permet au Bot de choisir une Carte 
 * @param choiceVictime
 * @param choiceCardVictime
 * @param g
 * 
 * En fonction du nombre de joueur dans le jeu, on fait tourner un algorithme qui détermine le bon joueur à volé selon les règles.
 * On change l'attribut victime @see Game#setVictime() selon le choix déterminé 
 * On définit ensuite le pseudo de la prochaine victime en fonction du nombre de joueur :
 * 
 */

	public void stealCard(String choiceVictime,String choiceCardVictime, Game g)
	{
		int nbCardOffer=0;
		for(Entry<String, HashMap<String, Card>> map : g.listOffer.entrySet()) {


			{nbCardOffer = nbCardOffer+map.getValue().size();}
			
		}


		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int i=0;

		if(g.getNbPlayers()==3) {

			if(nbCardOffer>4) {
				while(g.players.get(i)==this || g.players.get(i).getOffer().size()!=2)  {
					i++;
				}g.setVictime(g.players.get(i).getPseudo());
			}

			else if(nbCardOffer==4 && this.getOffer().size()==2)
			{g.setVictime(this.getPseudo());
			}
			else if(nbCardOffer==4 && this.getOffer().size()!=2)
			{
				while( g.players.get(i)==this ||  g.players.get(i).getOffer().size()!=2)  {
					i++;				
				}
				g.setVictime(g.players.get(i).getPseudo());
			}

		}



		if(g.getNbPlayers()==4) {
			if(nbCardOffer>5) {
				while( g.players.get(i)==this || g.players.get(i).getOffer().size()!=2)  {
					i++;
				}g.setVictime(g.players.get(i).getPseudo());	
			}

			else if(nbCardOffer==5 && this.getOffer().size()==2)
			{g.setVictime(this.getPseudo());}
			else {
				while( g.players.get(i)==this  || g.players.get(i).getOffer().size()!=2)  {
					i++;
				}g.setVictime(g.players.get(i).getPseudo());	
			}
		}

		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}

	 stolenCard ="down";
		this.jest.jestCards.add(g.listOffer.get(g.getVictime()).get(stolenCard));
		g.listOffer.get(g.getVictime()).remove(stolenCard);

		this.setHasStolen(true); 
		this.choiceVictimeBot = g.getVictime();
		if(g.getForMainPlay().get(g.getVictime()).isHasStolen()==true) { 


			if(g.getNbPlayers()==3) {
				for (HashMap.Entry<String,Player> mapentry : g.getForMainPlay().entrySet()) {
					if (mapentry.getValue().getOffer().size()==2) {

						g.setVictime(mapentry.getKey());

					}
				}
			}else
				if(g.getNbPlayers()==4) {

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
	
	public String getStolenCard() {
		return stolenCard;
	}

}








