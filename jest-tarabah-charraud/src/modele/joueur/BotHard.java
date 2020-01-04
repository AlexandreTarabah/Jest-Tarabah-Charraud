package modele.joueur;

import java.util.Scanner;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

import modele.carte.Card;
import modele.game.Game;
import modele.tas.Jest;


/**
 * @version 2 : petite modif sur indice de hand remis à 0 et 1 dans stealCard
 * Ce bot choisit la carte de plus haute couleur de la victime ! */

public class BotHard extends Player implements Difficulty {



	public BotHard(String pseudo, Game g) {
		super(pseudo, g);
		// TODO Auto-generated constructor stub
	}







	@Override
	public void upsideDown(Player p, Scanner input) {

		{		
			System.out.println("voici vos cartes joueur : " + this.getPseudo()+"\n");
			for(int i=0; i<2;i++) {
				System.out.println(getHand()[i].getValue() +" de "+ getHand()[i].getColor()); // on affiche les cartes du joueur
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



			((Map<String, Card>) getOffer()).put("down", getHand()[numC-1]); // -1 car le tableau commence à l'indice 0, je caste l'offer  
			((Map<String, Card>) getOffer()).put("up", getHand()[numC%2]); // avec le modulo 2 on obtient la case manquante, je caste l'offer
			System.out.println(this.getPseudo()  + " a caché " + ((Map<String, Card>) getOffer()).get("down").getValue() + " de " + ((Map<String, Card>) getOffer()).get("down").getColor()+"\n");
			/* et la on affiche le pseudo du player en paramètre, avec get(Down) et la value de la carte, et la couleur
			 */

			Player.listOffer.put(this.getPseudo(), this.getOffer()); // on ajoute l'offre du player a la listOffer.

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
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int i=0;

		if(Game.getNbPlayers()==3) {

			if(nbCardOffer>4) {
				while(Game.players.get(i)==this || Game.players.get(i).getOffer().size()!=2)  {
					i++;
				}setVictime(Game.players.get(i).getPseudo());
			}

			else if(nbCardOffer==4 && this.getOffer().size()==2)
			{setVictime(this.getPseudo());
			}
			else if(nbCardOffer==4 && this.getOffer().size()!=2)
			{
				while( Game.players.get(i)==this ||  Game.players.get(i).getOffer().size()!=2)  {
					i++;				
				}
				setVictime(Game.players.get(i).getPseudo());
			}

		}



		if(Game.getNbPlayers()==4) {
			if(nbCardOffer>5) {
				while( Game.players.get(i)==this || Game.players.get(i).getOffer().size()!=2)  {
					i++;
				}setVictime(Game.players.get(i).getPseudo());	
			}

			else if(nbCardOffer==5 && this.getOffer().size()==2)
			{setVictime(this.getPseudo());}
			else {
				while( Game.players.get(i)==this  || Game.players.get(i).getOffer().size()!=2)  {
					i++;
				}setVictime(Game.players.get(i).getPseudo());	
			}
		}

		System.out.println(getVictime());





		System.out.println("Quelle carte voulez-vous lui dérober ?\n ");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String stolenCard =null;
		int highestC = Game.getForMainPlay().get(getVictime()).getHand()[0].getColor().getColorValue();
		int highestV = Game.getForMainPlay().get(getVictime()).getHand()[0].getValue().getCardValue();
		if(highestC < Game.getForMainPlay().get(getVictime()).getHand()[1].getColor().getColorValue())
		{
			stolenCard = "up";

		}
		else if (highestC == Game.getForMainPlay().get(getVictime()).getHand()[1].getColor().getColorValue())
		{
			if(highestV < Game.getForMainPlay().get(getVictime()).getHand()[1].getValue().getCardValue())
			{
				stolenCard = "up";

			}
			else
			{
				stolenCard = "down";
			}
		}
		else if(highestC > Game.getForMainPlay().get(getVictime()).getHand()[1].getColor().getColorValue())
		{
			stolenCard = "down";
		}

		System.out.println(stolenCard);
		this.jest.jestCards.add(Player.listOffer.get(getVictime()).get(stolenCard));
		Player.listOffer.get(getVictime()).remove(stolenCard);// méthode AddJest() implementé dans Jest.

		this.setHasStolen(true); 

		if(Game.getForMainPlay().get(getVictime()).isHasStolen()==true) { // Dans le cas ou le joueur vole le voleur précédent, on fixe la prochaine victime au joueur qui a l'offre complete. 


			if(Game.getNbPlayers()==3) {
				for (HashMap.Entry<String,Player> mapentry : Game.getForMainPlay().entrySet()) {
					if (mapentry.getValue().getOffer().size()==2) {

						setVictime(mapentry.getKey());

					}
				}
			}else
				if(Game.getNbPlayers()==4) {

					int highestCardValue = 0;
					int highestColorValue = 0;

					for (HashMap.Entry<String,Player> mapentry2 : Game.getForMainPlay().entrySet()) {
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
		Iterator<?> it = this.jest.jestCards.iterator();
		while(it.hasNext())
		{
			System.out.println("Monsieur Bot, vous avez ajouté à votre Jest " + it.next()+" "+"\n");
		}


		if(Game.getNbPlayers() == 3 && nbCardOffer>3)
		{System.out.println(Game.getForMainPlay().get(victime).getPseudo() + " à vous de jouer\n ");
		}else
			if(Game.getNbPlayers()==4 && nbCardOffer>4)
			{System.out.println(Game.getForMainPlay().get(victime).getPseudo() + " à vous de jouer\n ");}




	}


}


