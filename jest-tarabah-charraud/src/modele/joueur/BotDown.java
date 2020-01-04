package modele.joueur;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;
import modele.carte.Card;
import modele.game.Game;

public class BotDown extends Player implements Difficulty {

	public BotDown(String pseudo, Game g) {
		super(pseudo, g);
		// TODO Auto-generated constructor stub
	}


	@Override
	public void upsideDown(int choice,Game g) {
		int numC = 1 ; // demande au joueur de rentrer un numéro entre 1 et 2

		try {
			Thread.sleep(100);
			System.out.println(numC);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



		((Map<String, Card>) getOffer()).put("down", getHand()[numC-1]); // -1 car le tableau commence à l'indice 0, je caste l'offer  
		((Map<String, Card>) getOffer()).put("up", getHand()[numC%2]); // avec le modulo 2 on obtient la case manquante, je caste l'offer
		System.out.println(this.getPseudo()  + " a caché " + ((Map<String, Card>) getOffer()).get("down").getValue() + " de " + ((Map<String, Card>) getOffer()).get("down").getColor()+"\n");
		/* et la on affiche le pseudo du player en paramètre, avec get(Down) et la value de la carte, et la couleur
		 */

		g.listOffer.put(this.getPseudo(), this.getOffer()); // on ajoute l'offre du player a la listOffer.

	}




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
				}setVictime(g.players.get(i).getPseudo());
			}

			else if(nbCardOffer==4 && this.getOffer().size()==2)
			{setVictime(this.getPseudo());
			}
			else if(nbCardOffer==4 && this.getOffer().size()!=2)
			{
				while( g.players.get(i)==this ||  g.players.get(i).getOffer().size()!=2)  {
					i++;				
				}
				setVictime(g.players.get(i).getPseudo());
			}

		}



		if(g.getNbPlayers()==4) {
			if(nbCardOffer>5) {
				while( g.players.get(i)==this || g.players.get(i).getOffer().size()!=2)  {
					i++;
				}setVictime(g.players.get(i).getPseudo());	
			}

			else if(nbCardOffer==5 && this.getOffer().size()==2)
			{setVictime(this.getPseudo());}
			else {
				while( g.players.get(i)==this  || g.players.get(i).getOffer().size()!=2)  {
					i++;
				}setVictime(g.players.get(i).getPseudo());	
			}
		}

		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String stolenCard ="down";
		this.jest.jestCards.add(g.listOffer.get(getVictime()).get(stolenCard));
		g.listOffer.get(getVictime()).remove(stolenCard);// méthode AddJest() implementé dans Jest.

		this.setHasStolen(true); 

		if(g.getForMainPlay().get(getVictime()).isHasStolen()==true) { // Dans le cas ou le joueur vole le voleur précédent, on fixe la prochaine victime au joueur qui a l'offre complete. 


			if(g.getNbPlayers()==3) {
				for (HashMap.Entry<String,Player> mapentry : g.getForMainPlay().entrySet()) {
					if (mapentry.getValue().getOffer().size()==2) {

						setVictime(mapentry.getKey());

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
								setVictime(mapentry2.getKey());
							}

							if(highestCardValue == mapentry2.getValue().getOffer().get("up").getValue().getCardValue() && 
									highestColorValue < mapentry2.getValue().getOffer().get("up").getColor().getColorValue()) {

								setVictime(mapentry2.getKey());

							}		
						}
					}
				}
		}
		nbCardOffer-=1;
	}

}








