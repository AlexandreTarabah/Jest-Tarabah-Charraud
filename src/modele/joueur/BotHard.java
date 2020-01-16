package modele.joueur;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import modele.carte.Card;
import modele.game.Game;

/**
 * BotHard repr�sente un Bot, qui choisit la meilleure carte  
 * Le BotHard a les m�mes caract�ristiques que Player 
 * 
 * Le Bot est caract�ris� par : 
 * <ul>
 * <li> un boolean isBot</li>
 * <li> Un String stolenCard qui peut-etre Up ou Down </li>
 * </ul>
 * 
 * De plus, BotDown extends la classe Player puisque le Bot est un Joueur avec ses propres m�thodes. 
 * 
 * 
 */

public class BotHard extends Player implements Difficulty {
	/** 
	 * la caract�ristique du Bot : c'est un bot donc isBot = true; Cette caract�ristique ne se modifie pas 
	 */
	boolean isBot=true;
	/**
	 * Indique quelle carte est selectionn�e par le Bot
	 * Ce String prend 2 valeurs : up ou down 
	 * @see BotHard#upsideDown(int, Game)
	 */
	String stolenCard;

	/** 
	 * Constructeur du Bot h�rit� de Player
	 * @param pseudo
	 * @param g
	 */
	
	public BotHard(String pseudo,Game g) {
		super(pseudo, g);
		
	}
	/**
	 * M�thode upsideDown 
	 * @param choice
	 * @param g 
	 * 
	 * Cette m�thode permet de retourner une carte dans la main du Bot 
	 * Ainsi, on d�finit son offre 
	 */
	
	@Override
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
		System.out.println(this.getPseudo()  + " a cach� " + this.offer.get("down").getValue() + " de " + this.offer.get("down").getColor()+"\n");

		g.listOffer.put(this.getPseudo(), this.getOffer());

	}


	/**
	 * M�thode stealCard qui permet au Bot de choisir une Carte 
	 * @param choiceVictime
	 * @param choiceCardVictime
	 * @param g
	 * 
	 * En fonction du nombre de joueur dans le jeu, on fait tourner un algorithme qui d�termine le bon joueur � vol� selon les r�gles.
	 * On change l'attribut victime @see Game#setVictime() selon le choix d�termin� 
	 * On d�finit ensuite le pseudo de la prochaine victime en fonction du nombre de joueur :
	 * 
	 */

	public void stealCard(String choiceVictime,String choiceCardVictime, Game g)
	{
		int nbCardOffer=0;
		for(Entry<String, HashMap<String, Card>> map : g.listOffer.entrySet()) {


			{nbCardOffer = nbCardOffer+map.getValue().size();}
			g.nbCardOffer=nbCardOffer;
		}


		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
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
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		
			e.printStackTrace();
		}

		 stolenCard =null;
		int highestC = g.ForMainPlay.get(g.getVictime()).hand.get(0).getColor().getColorValue();
		int highestV = g.ForMainPlay.get(g.getVictime()).hand.get(0).getValue().getCardValue();
		if(highestC < g.ForMainPlay.get(g.getVictime()).hand.get(1).getColor().getColorValue())
		{
			stolenCard = "up";

		}
		else if (highestC == g.ForMainPlay.get(g.getVictime()).hand.get(1).getColor().getColorValue())
		{
			if(highestV < g.ForMainPlay.get(g.getVictime()).hand.get(1).getValue().getCardValue())
			{
				stolenCard = "up";

			}
			else
			{
				stolenCard = "down";
			}
		}
		else if(highestC > g.ForMainPlay.get(g.getVictime()).hand.get(1).getColor().getColorValue())
		{
			stolenCard = "down";
		}
		this.jest.jestCards.add(g.listOffer.get(g.getVictime()).get(stolenCard));
		g.listOffer.get(g.getVictime()).remove(stolenCard);

		this.HasStolen=true; 
		this.choiceVictimeBot = g.getVictime();

		if(g.getForMainPlay().get(g.getVictime()).HasStolen==true) {

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
	 * Retourne l'attribut StolenCard
	 * @return stolenCard; 
	 * 
	 */
	public String getStolenCard() {
		return stolenCard;
	}
	}