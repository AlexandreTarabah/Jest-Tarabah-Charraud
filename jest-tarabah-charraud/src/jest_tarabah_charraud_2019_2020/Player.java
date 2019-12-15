package jest_tarabah_charraud_2019_2020;
import java.util.Scanner;

import java.util.Set;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class Player 
{

	String  pseudo;

	private int nump;

	public boolean isAThief;

	public boolean hasHighestFup;

	HashMap<String, Card> offer;

	static HashMap<String,HashMap<String,Card>> listOffer = new HashMap();

	boolean HasStolen=false;


	private Card[] hand = new Card[2] ;

	private Jest jest ;

	boolean firstPlayer = false;

	static private String victime;

	static private String starter;
	
	int nbPoint;

	//j'instancie l'objet offre, qui est aussi une collection de carte, dans le constructeur player ici 

	public Player (Scanner input) 
	{
		this.offer = new HashMap<String, Card>();
		System.out.println("Entrez le nom du joueur : ");
		String pseudo = input.next();
		this.pseudo=pseudo;
		this.jest = new Jest();
		Game.players.add(this);
		Game.ForMainPlay.put(this.pseudo, this);
	}



	
	

	public void stealCard(Scanner input) {
		int nbCardOffer=0;
		for(Entry<String, HashMap<String, Card>> map : listOffer.entrySet()) {
			 
				
				{nbCardOffer = nbCardOffer+map.getValue().size();}
			}
		
				
				System.out.println("Qui sera votre victime ? Rentrer le pseudo d'un joueur\n ");
				victime = input.next();
				
				
				
		while(listOffer.containsKey(victime)==false) {
			System.out.println("Veuillez rentrer un joueur existant\n");
			victime=input.next();
		}
		
		
		while(Player.listOffer.get(victime).size()<2) {
			System.out.println("Offre de la victime incomplète, veuillez saisir une offre complete\n"); // vérification que l'offre est bien complète
			victime=input.next();}
			
		if(nbCardOffer>4) {
		
		
		while( this.pseudo.equals(victime) || victime==starter)  {
			System.out.println(this.pseudo);
			System.out.println(" n'oubliez pas que vous pouvez vous volez uniquement si vous êtes le dernier joueur\n Rentrer un pseudo\n");
				victime=input.next();					
						}
				}
		
			
		
		

		System.out.println("Quelle carte voulez-vous lui dérober ?\n ");
		
		String stolenCard = input.next();
		
		this.jest.jestCards.add(Player.listOffer.get(victime).get(stolenCard));
		Player.listOffer.get(victime).remove(stolenCard);// méthode AddJest() implementé dans Jest.

		this.HasStolen=true; 

		if(Game.getForMainPlay().get(victime).HasStolen==true) { // Dans le cas ou le joueur vole le voleur précédent, on fixe la prochaine victime au joueur qui a l'offre complete. 

			for (HashMap.Entry<String,Player> mapentry : Game.getForMainPlay().entrySet()) {
				if (mapentry.getValue().offer.size()==2) {

					victime=mapentry.getKey();

				}
			}
		}
		starter="";
		Iterator it = this.jest.jestCards.iterator() ;
		while(it.hasNext())
		{
			System.out.println("vous avez ajouté à votre Jest " + it.next()+" "+"\n");
		}
	}


	
	

	
	
	
	
	
	

	public String setPseudo( Scanner input) 
	{
		System.out.println("Entrez le nom du joueur : ");
		String pseudo = input.nextLine() ;
		return pseudo;

	}

	
	
	
	


	public  void setHand(int i, Card card)
	{
		this.hand[i] = card;

	}



	
	
	
	



	public void determinateFirstPlayer(Player p1, Player p2, Player p3) {


		int HighestValue = 0;
		int HighestColor =0;
		String PlayerHighestValue = null;
		for(Entry<String, HashMap<String, Card>> mapentry : listOffer.entrySet()) {
				for(Entry<String, Card> mapentry2 : offer.entrySet()) {

					if(HighestValue < mapentry.getValue().get(mapentry2.getKey()).getValue().getCardValue())
					{
					HighestValue=mapentry.getValue().get(mapentry2.getKey()).getValue().getCardValue();
					PlayerHighestValue = mapentry.getKey();
				
						if(HighestValue == mapentry.getValue().get(mapentry2.getKey()).getValue().getCardValue() && PlayerHighestValue != mapentry.getKey() ){

								if( mapentry.getValue().get(PlayerHighestValue).getColor().getColorValue()<mapentry.getValue().get(mapentry2.getKey()).getColor().getColorValue() ) {

									PlayerHighestValue=mapentry.getKey();
										};
								}
						}
				}
		}



		if(PlayerHighestValue==p1.pseudo) {
			p1.firstPlayer = true;
			starter=p1.pseudo;
			System.out.println("Joueur " + p1.pseudo + " commence\n");
		}else 
			if(PlayerHighestValue==p2.pseudo) {
				System.out.println("Joueur " + p2.pseudo + " commence\n");
				p2.firstPlayer=true;
				starter=p2.pseudo;
			}else 
				if(PlayerHighestValue==p3.pseudo) {
					System.out.println("Joueur " + p3.pseudo + " commence\n");
					starter=p3.pseudo;
					p3.firstPlayer=true;
				}
					
					


	}


	
	
	
	
	
	public static String getStarter() {
		return starter;
	}

	
	
	
	
	
	public static String getVictime() {
		return victime;
	}





	
	

	// la c'est la méthode pour 
	public void upsideDown(Player player, Scanner input) 
	{		
		System.out.println("voici vos cartes joueur : " + player.pseudo+"\n");
		for(int i=0; i<2;i++) {
			System.out.println(hand[i].getValue() +" de "+ hand[i].getColor()); // on affiche les cartes du joueur
		}

		System.out.println("Quelle carte voulez-vous garder cachée?\n");

		int numC = input.nextInt() ; // demande au joueur de rentrer un numéro entre 1 et 2

		((Map<String, Card>) offer).put("down", hand[numC-1]); // -1 car le tableau commence à l'indice 0, je caste l'offer  
		((Map<String, Card>) offer).put("up", hand[numC%2]); // avec le modulo 2 on obtient la case manquante, je caste l'offer
		System.out.println(player.pseudo  + " a caché " + ((Map<String, Card>) offer).get("down").getValue() + " de " + ((Map<String, Card>) offer).get("down").getColor()+"\n");
		/* et la on affiche le pseudo du player en paramètre, avec get(Down) et la value de la carte, et la couleur
		 */

		Player.listOffer.put(player.pseudo, player.offer); // on ajoute l'offre du player a la listOffer.

	}


	
	


	public Jest getJest()
	{
		return jest ;
	}


}
