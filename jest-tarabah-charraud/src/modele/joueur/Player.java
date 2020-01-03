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

	static HashMap<String,HashMap<String,Card>> listOffer = new HashMap<String, HashMap<String, Card>>();

	private boolean HasStolen=false;
	
	private Card[] hand = new Card[2] ;

	protected Jest jest ;

	boolean firstPlayer = false;

	private static  String victime;

	private int nbPoint;

	//j'instancie l'objet offre, qui est aussi une collection de carte, dans le constructeur player ici 

	public Player (Scanner input) 
	{
		this.setOffer(new HashMap<String, Card>());
		System.out.println("Entrez le nom du joueur : ");
		String pseudo = input.next();
		this.setPseudo(pseudo);
		this.jest = new Jest();
		Game.getPlayers().add(this) ;
		Game.getForMainPlay().put(this.getPseudo(), this);
	}






	public void stealCard(Scanner input) {
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


		while(Player.listOffer.get(getVictime()).size()<2) {
			System.out.println("Offre de la victime incomplète, veuillez saisir une offre complete\n"); // vérification que l'offre est bien complète
			setVictime(input.next());
		}


		if(Game.getNbPlayers()==3) {
			if(nbCardOffer>4) {
				while( this.getPseudo().equals(getVictime()))  {
					System.out.println(this.getPseudo());
					System.out.println(" n'oubliez pas que vous pouvez vous volez uniquement si vous êtes le dernier joueur\n Rentrer un pseudo\n");
					setVictime(input.next());					
				}
			}
		}else 

			if(Game.getNbPlayers()==4) {
				if(nbCardOffer>5) {
					while( this.getPseudo().equals(getVictime()))  {
						System.out.println(this.getPseudo());
						System.out.println(" n'oubliez pas que vous pouvez vous volez uniquement si vous êtes le dernier joueur\n Rentrer un pseudo\n");
						setVictime(input.next());					
					}
				}
			}





		System.out.println("Quelle carte voulez-vous lui dérober ?\n ");

		String stolenCard = input.next();
			while(Game.getUpsideChoice().contains(stolenCard)==false)
			{
				System.out.println("Veuillez rentrer down ou up");
				stolenCard=input.next();
			}	

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
		Iterator it = this.jest.jestCards.iterator();
		while(it.hasNext())
		{
			System.out.println("Vous avez ajouté à votre Jest " + it.next()+" "+"\n");
		}


		if(Game.getNbPlayers() == 3 && nbCardOffer>3)
		{System.out.println(Game.getForMainPlay().get(Player.getVictime()).pseudo + " à vous de jouer\n ");
		}else
			if(Game.getNbPlayers()==4 && nbCardOffer>4)
			{System.out.println(Game.getForMainPlay().get(Player.getVictime()).pseudo + " à vous de jouer\n ");}




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



















	public static String getVictime() {
		return victime;
	}








	// la c'est la méthode pour 
	public void upsideDown(Player p, Scanner input) 
	{		
		System.out.println("voici vos cartes joueur : " + this.getPseudo()+"\n");
		for(int i=0; i<2;i++) {
			System.out.println(getHand()[i].getValue() +" de "+ getHand()[i].getColor()); // on affiche les cartes du joueur
		}

		System.out.println("Quelle carte voulez-vous garder cachée?\n");

		int numC = 0; // demande au joueur de rentrer un numéro entre 1 et 2
	
		while(Game.getChoiceVar().contains(numC)==false) {
			numC = Game.readInt(input,"Entrez un nombre compris entre 1 et 2 : ", "Non, Recommencez : ");
		}

		((Map<String, Card>) getOffer()).put("down", getHand()[numC-1]); // -1 car le tableau commence à l'indice 0, je caste l'offer  
		((Map<String, Card>) getOffer()).put("up", getHand()[numC%2]); // avec le modulo 2 on obtient la case manquante, je caste l'offer
		System.out.println(this.getPseudo()  + " a caché " + ((Map<String, Card>) getOffer()).get("down").getValue() + " de " + ((Map<String, Card>) getOffer()).get("down").getColor()+"\n");
		/* et la on affiche le pseudo du player en paramètre, avec get(Down) et la value de la carte, et la couleur
		 */

		Player.listOffer.put(this.getPseudo(), this.getOffer()); // on ajoute l'offre du player a la listOffer.

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
		Player.victime = victime;
	}






	public boolean isHasStolen() {
		return HasStolen;
	}






	public void setHasStolen(boolean hasStolen) {
		HasStolen = hasStolen;
	}


}