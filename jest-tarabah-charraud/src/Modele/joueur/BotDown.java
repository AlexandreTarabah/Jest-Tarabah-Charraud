package modele.joueur;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

import modele.carte.Card;
import modele.game.Game;

public class BotDown extends Player implements Difficulty {

	public BotDown(Scanner input) {
		super(input);
		// TODO Auto-generated constructor stub
	}


	@Override
	public void upsideDown(Player p, Scanner input) {

		System.out.println("voici vos cartes joueur : " + this.pseudo+"\n");
		for(int i=0; i<2;i++) {
			System.out.println(getHand()[i].getValue() +" de "+ getHand()[i].getColor()); // on affiche les cartes du joueur
		}

		System.out.println("Quelle carte voulez-vous garder cach�e?\n");

		int numC = 1 ; // demande au joueur de rentrer un num�ro entre 1 et 2

		try {
			Thread.sleep(100);
			System.out.println(numC);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



		((Map<String, Card>) offer).put("down", this.getHand()[numC-1]); // -1 car le tableau commence � l'indice 0, je caste l'offer  
		((Map<String, Card>) offer).put("up", this.getHand()[numC%2]); // avec le modulo 2 on obtient la case manquante, je caste l'offer
		System.out.println(this.pseudo  + " a cach� " + ((Map<String, Card>) offer).get("down").getValue() + " de " + ((Map<String, Card>) offer).get("down").getColor()+"\n");
		/* et la on affiche le pseudo du player en param�tre, avec get(Down) et la value de la carte, et la couleur
		 */

		Player.listOffer.put(this.pseudo, this.offer); // on ajoute l'offre du player a la listOffer.

	}




	public void stealCard(Scanner input)
	{
		int nbCardOffer=0;
		for(Entry<String, HashMap<String, Card>> map : listOffer.entrySet()) {


			{nbCardOffer = nbCardOffer+map.getValue().size();}
		}


		System.out.println("Qui sera votre victime ? Rentrer le pseudo d'un joueur\n ");
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int i=0;

		if(Game.nbPlayers==3) {

			if(nbCardOffer>4) {
				while(Game.players.get(i)==this || Game.players.get(i).offer.size()!=2)  {
					i++;
				}victime = Game.players.get(i).pseudo;
			}

			else if(nbCardOffer==4 && this.offer.size()==2)
			{victime=this.pseudo;
			}
			else if(nbCardOffer==4 && this.offer.size()!=2)
			{
				while( Game.players.get(i)==this ||  Game.players.get(i).offer.size()!=2)  {
					i++;				
				}
				victime = Game.players.get(i).pseudo;
			}

		}



		if(Game.nbPlayers==4) {
			if(nbCardOffer>5) {
				while( Game.players.get(i)==this || Game.players.get(i).offer.size()!=2)  {
					i++;
				}victime = Game.players.get(i).pseudo;	
			}

			else if(nbCardOffer==5 && this.offer.size()==2)
			{victime=this.pseudo;}
			else {
				while( Game.players.get(i)==this  || Game.players.get(i).offer.size()!=2)  {
					i++;
				}victime = Game.players.get(i).pseudo;	
			}
		}

		System.out.println(victime);


		System.out.println("Quelle carte voulez-vous lui d�rober ?\n ");
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String stolenCard ="down";
		System.out.println("down");
		this.jest.jestCards.add(Player.listOffer.get(victime).get(stolenCard));
		Player.listOffer.get(victime).remove(stolenCard);// m�thode AddJest() implement� dans Jest.

		this.HasStolen=true; 

		if(Game.getForMainPlay().get(victime).HasStolen==true) { // Dans le cas ou le joueur vole le voleur pr�c�dent, on fixe la prochaine victime au joueur qui a l'offre complete. 


			if(Game.nbPlayers==3) {
				for (HashMap.Entry<String,Player> mapentry : Game.getForMainPlay().entrySet()) {
					if (mapentry.getValue().offer.size()==2) {

						victime=mapentry.getKey();

					}
				}
			}else
				if(Game.nbPlayers==4) {

					int highestCardValue = 0;
					int highestColorValue = 0;

					for (HashMap.Entry<String,Player> mapentry2 : Game.getForMainPlay().entrySet()) {
						if (mapentry2.getValue().offer.size()==2) {
							if(highestCardValue <  mapentry2.getValue().offer.get("up").getValue().getCardValue())
							{
								highestCardValue = mapentry2.getValue().offer.get("up").getValue().getCardValue();
								highestColorValue = mapentry2.getValue().offer.get("up").getColor().getColorValue();
								victime = mapentry2.getKey();
							}

							if(highestCardValue == mapentry2.getValue().offer.get("up").getValue().getCardValue() && 
									highestColorValue < mapentry2.getValue().offer.get("up").getColor().getColorValue()) {

								victime = mapentry2.getKey();

							}		
						}
					}
				}
		}
		nbCardOffer-=1;
		Iterator<?> it = this.jest.jestCards.iterator();
		while(it.hasNext())
		{
			System.out.println("Monsieur Bot, vous avez ajout� � votre Jest " + it.next()+" "+"\n");
		}


		if(Game.nbPlayers == 3 && nbCardOffer>3)
		{System.out.println(Game.ForMainPlay.get(Player.getVictime()).pseudo + " � vous de jouer\n ");
		}else
			if(Game.nbPlayers==4 && nbCardOffer>4)
			{System.out.println(Game.ForMainPlay.get(Player.getVictime()).pseudo + " � vous de jouer\n ");}




	}

}







