package Modele;

import java.util.Scanner;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;


/**
 * @version 2 : petite modif sur indice de hand remis à 0 et 1 dans stealCard
 * Ce bot choisit la carte de plus haute couleur de la victime ! */

public class BotHard extends Player implements Difficulty {



	public BotHard(Scanner input) {
		super(input);
		// TODO Auto-generated constructor stub
	}







	@Override
	public void upsideDown(Player p, Scanner input) {

		{		
			System.out.println("voici vos cartes joueur : " + this.pseudo+"\n");
			for(int i=0; i<2;i++) {
				System.out.println(hand[i].getValue() +" de "+ hand[i].getColor()); // on affiche les cartes du joueur
			}

			System.out.println("Quelle carte voulez-vous garder cachée?\n");

			int numC = 2 ; // demande au joueur de rentrer un numéro entre 1 et 2

			try {
				Thread.sleep(100);
				System.out.println(numC);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}



			((Map<String, Card>) offer).put("down", hand[numC-1]); // -1 car le tableau commence à l'indice 0, je caste l'offer  
			((Map<String, Card>) offer).put("up", hand[numC%2]); // avec le modulo 2 on obtient la case manquante, je caste l'offer
			System.out.println(this.pseudo  + " a caché " + ((Map<String, Card>) offer).get("down").getValue() + " de " + ((Map<String, Card>) offer).get("down").getColor()+"\n");
			/* et la on affiche le pseudo du player en paramètre, avec get(Down) et la value de la carte, et la couleur
			 */

			Player.listOffer.put(this.pseudo, this.offer); // on ajoute l'offre du player a la listOffer.

		}


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
				while(Game.getPlayers().get(i)==this || Game.getPlayers().get(i).offer.size()!=2)  {
					i++;
				}victime = Game.getPlayers().get(i).pseudo;
			}

			else if(nbCardOffer==4 && this.offer.size()==2)
			{victime=this.pseudo;
			}
			else if(nbCardOffer==4 && this.offer.size()!=2)
			{
				while( Game.getPlayers().get(i)==this ||  Game.getPlayers().get(i).offer.size()!=2)  {
					i++;				
				}
				victime = Game.getPlayers().get(i).pseudo;
			}

		}



		if(Game.nbPlayers==4) {
			if(nbCardOffer>5) {
				while( Game.getPlayers().get(i)==this || Game.getPlayers().get(i).offer.size()!=2)  {
					i++;
				}victime = Game.getPlayers().get(i).pseudo;	
			}

			else if(nbCardOffer==5 && this.offer.size()==2)
			{victime=this.pseudo;}
			else {
				while( Game.getPlayers().get(i)==this  || Game.getPlayers().get(i).offer.size()!=2)  {
					i++;
				}victime = Game.getPlayers().get(i).pseudo;	
			}
		}

		System.out.println(victime);





		System.out.println("Quelle carte voulez-vous lui dérober ?\n ");
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String stolenCard =null;
		int highestC = Game.ForMainPlay.get(victime).hand[0].getColor().getColorValue();
		int highestV = Game.ForMainPlay.get(victime).hand[0].getValue().getCardValue();
		if(highestC < Game.ForMainPlay.get(victime).hand[1].getColor().getColorValue())
		{
			stolenCard = "up";

		}
		else if (highestC == Game.ForMainPlay.get(victime).hand[1].getColor().getColorValue())
		{
			if(highestV < Game.ForMainPlay.get(victime).hand[1].getValue().getCardValue())
			{
				stolenCard = "up";

			}
			else
			{
				stolenCard = "down";
			}
		}
		else if(highestC > Game.ForMainPlay.get(victime).hand[1].getColor().getColorValue())
		{
			stolenCard = "down";
		}

		System.out.println(stolenCard);
		this.jest.jestCards.add(Player.listOffer.get(victime).get(stolenCard));
		Player.listOffer.get(victime).remove(stolenCard);// méthode AddJest() implementé dans Jest.

		this.HasStolen=true; 

		if(Game.getForMainPlay().get(victime).HasStolen==true) { // Dans le cas ou le joueur vole le voleur précédent, on fixe la prochaine victime au joueur qui a l'offre complete. 


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
							if(highestCardValue <  mapentry2.getValue().offer.get("up").value.getCardValue())
							{
								highestCardValue = mapentry2.getValue().offer.get("up").value.getCardValue();
								highestColorValue = mapentry2.getValue().offer.get("up").getColor().getColorValue();
								victime = mapentry2.getKey();
							}

							if(highestCardValue == mapentry2.getValue().offer.get("up").value.getCardValue() && 
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
			System.out.println("Monsieur Bot, vous avez ajouté à votre Jest " + it.next()+" "+"\n");
		}


		if(Game.nbPlayers == 3 && nbCardOffer>3)
		{System.out.println(Game.ForMainPlay.get(Player.getVictime()).pseudo + " à vous de jouer\n ");
		}else
			if(Game.nbPlayers==4 && nbCardOffer>4)
			{System.out.println(Game.ForMainPlay.get(Player.getVictime()).pseudo + " à vous de jouer\n ");}




	}


}


