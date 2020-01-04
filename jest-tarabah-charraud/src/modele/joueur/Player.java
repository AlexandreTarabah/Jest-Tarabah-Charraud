package modele.joueur;
import java.util.Scanner;

import java.util.Set;

import modele.carte.Card;
import modele.game.Game;
import modele.tas.Jest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class Player 
{

	private String pseudo;

	public boolean isAThief;

	private HashMap<String, Card> offer;

	private HashMap<String,HashMap<String,Card>> listOffer = new HashMap<String, HashMap<String, Card>>();

	public boolean HasStolen=false;
	
	private Card[] hand = new Card[2] ;

	protected Jest jest ;

	boolean firstPlayer = false;

	protected  String victime;

	private int nbPoint;

	//j'instancie l'objet offre, qui est aussi une collection de carte, dans le constructeur player ici 

	public Player (String pseudo, Game g) 
	{
		this.pseudo=pseudo;
		this.setOffer(new HashMap<String, Card>());
		System.out.println("Entrez le nom du joueur : ");
		this.jest = new Jest();
		g.players.add(this);
		g.ForMainPlay.put(this.getPseudo(), this);
	}






	public void stealCard(Scanner input, Game g) {
		int nbCardOffer=0;
		for(Entry<String, HashMap<String, Card>> map : listOffer.entrySet()) {


			{nbCardOffer = nbCardOffer+map.getValue().size();}
		}


		System.out.println("Qui sera votre victime ? Rentrer le pseudo d'un joueur\n ");
		setVictime(input.next());



		while(listOffer.containsKey(getVictime())==false) {
			System.out.println("Veuillez rentrer un joueur existant\n");
			setVictime(input.next());
		}


		while(this.listOffer.get(getVictime()).size()<2) {
			System.out.println("Offre de la victime incompl�te, veuillez saisir une offre complete\n"); // v�rification que l'offre est bien compl�te
			setVictime(input.next());
		}


		if(g.getNbPlayers()==3) {
			if(nbCardOffer>4) {
				while( this.getPseudo().equals(getVictime()))  {
					System.out.println(this.getPseudo());
					System.out.println(" n'oubliez pas que vous pouvez vous volez uniquement si vous �tes le dernier joueur\n Rentrer un pseudo\n");
					setVictime(input.next());					
				}
			}
		}else 

			if(g.getNbPlayers()==4) {
				if(nbCardOffer>5) {
					while( this.getPseudo().equals(getVictime()))  {
						System.out.println(this.getPseudo());
						System.out.println(" n'oubliez pas que vous pouvez vous volez uniquement si vous �tes le dernier joueur\n Rentrer un pseudo\n");
						setVictime(input.next());					
					}
				}
			}





		System.out.println("Quelle carte voulez-vous lui d�rober ?\n ");

		String stolenCard = input.next();
			while(g.getUpsideChoice().contains(stolenCard)==false)
			{
				System.out.println("Veuillez rentrer down ou up");
				stolenCard=input.next();
			}	

		this.jest.jestCards.add(this.listOffer.get(getVictime()).get(stolenCard));
		this.listOffer.get(getVictime()).remove(stolenCard);// m�thode AddJest() implement� dans Jest.

		this.setHasStolen(true); 

		if(g.getForMainPlay().get(getVictime()).isHasStolen()==true) { // Dans le cas ou le joueur vole le voleur pr�c�dent, on fixe la prochaine victime au joueur qui a l'offre complete. 


			if(g.getNbPlayers()==3) {
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
		Iterator it = this.jest.jestCards.iterator();
		while(it.hasNext())
		{
			System.out.println("Vous avez ajout� � votre Jest " + it.next()+" "+"\n");
		}


		if(Game.getNbPlayers() == 3 && nbCardOffer>3)
		{System.out.println(Game.getForMainPlay().get(victime).pseudo + " � vous de jouer\n ");
		}else
			if(Game.getNbPlayers()==4 && nbCardOffer>4)
			{System.out.println(Game.getForMainPlay().get(victime).pseudo + " � vous de jouer\n ");}




	}












	public String setPseudo(Scanner input) 
	{
		System.out.println("Entrez le nom du joueur : ");
		String pseudo = input.nextLine() ;
		this.pseudo = pseudo ;
		return pseudo;

	}







	public  void setHand(int i, Card card)
	{
		this.hand[i] = card;

	}



















	public  String getVictime() {
		return victime;
	}








	// la c'est la m�thode pour 
	public void upsideDown(Player this, int choice) 
	{		

		int numC = choice; // demande au joueur de rentrer un num�ro entre 1 et 2
	

		((Map<String, Card>) getOffer()).put("down", getHand()[numC-1]); // -1 car le tableau commence � l'indice 0, je caste l'offer  
		((Map<String, Card>) getOffer()).put("up", getHand()[numC%2]); // avec le modulo 2 on obtient la case manquante, je caste l'offer
		/* et la on affiche le pseudo du player en param�tre, avec get(Down) et la value de la carte, et la couleur
		 */

		this.listOffer.put(this.getPseudo(), this.getOffer()); // on ajoute l'offre du player a la listOffer.

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






	public Card[] getHand() {
		return hand;
	}






	public void setHand(Card[] hand) {
		this.hand = hand;
	}






	public static void setVictime(String victime) {
		victime = victime;
	}






	public boolean isHasStolen() {
		return HasStolen;
	}






	public void setHasStolen(boolean hasStolen) {
		HasStolen = hasStolen;
	}


}