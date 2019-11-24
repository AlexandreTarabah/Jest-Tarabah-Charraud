package jest_tarabah_charraud_2019_2020;
import java.util.Scanner;
import java.util.Set;
import java.util.ArrayList;
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
	
	 boolean GotStolen=false;
	

	private Card[] hand = new Card[2] ;

	private Jest jest ;
	
	boolean firstPlayer = false;


	//j'instancie l'objet offre, qui est aussi une collection de carte, dans le constructeur player ici 

	public Player () 
	{
		offer = new HashMap<String, Card>();
		

		
		this.jest = new Jest() ;
	}

	
	

	public void stealCard(Player playerSteal, Player playerStolen, Scanner input) {
		System.out.println("Qui sera votre victime ? ");
		String victime = input.next(); 
		 victime = playerStolen.pseudo;
		 while(playerStolen.GotStolen=true) {
			 System.out.println("le joueur a déja été volé");
		 }
		System.out.println("Quelle carte voulez-vous lui dérober ? ");
		String stolenCard = input.next();
		if(stolenCard == "down") {
			this.jest.jestCards.add(Player.listOffer.get(playerStolen.pseudo).get("down")); /* Player.listOffer car c'esT static, et
																					je vais get player.pseudo dans la listOffer, avec la clé down.*/
		}
		else if (stolenCard =="up") {
			this.jest.jestCards.add(Player.listOffer.get(playerStolen.pseudo).get("up")); 
		}
			playerStolen.GotStolen=true;
		
	}

	
	public void DeterminateFirstPlayer(Player p1, Player p2, Player p3) {
		
		Set<Entry<String, HashMap<String, Card>>> listOfferHM = listOffer.entrySet();
	      Iterator<Entry<String, HashMap<String, Card>>> it1 = listOfferHM.iterator();
	      Set<Entry<String, Card>> OfferHM = offer.entrySet();
	      Iterator<Entry<String, Card>> it2 = OfferHM.iterator();
	      while(it1.hasNext()){
		      while(it2.hasNext()) {
		    	int HighestValue = 0;
		    	String PlayerHighestValue;
		    	
		    	if(HighestValue > it2.next().getValue().getValue().ordinal())
		    		{
		    		HighestValue=it2.next().getValue().getValue().ordinal();
		    		PlayerHighestValue = it1.next().getKey();
		    		}
		    	}
	      }
		    		
		    		
		    		if(it1.next().getKey()==p1.pseudo) {
		    			p1.firstPlayer = true;
		    			System.out.println("Joueur p1 commence !");
		    		}else 
		    			if(it1.next().getKey()==p2.pseudo) {
		    				System.out.println("Joueur p2 commence ! ");
		    				p1.firstPlayer=true;
		    			}else if(it1.next().getKey()==p3.pseudo) {
		    				System.out.println("Joueur p3 commence ! ");
		    				p3.firstPlayer=true;
		    			}
		    				
		    		
		    	};
		    	  
		      
	      
	

	
	
	

	public void setPseudo(Player player, Scanner input) 
	{
		System.out.println("Entrez le nom du joueur : ");
		String pseudo = input.nextLine() ;
		 player.pseudo = pseudo;
		
	}

	
	
	public  void setHand(int i, Card card)
	{
		this.hand[i] = card;
				
	}

	
	
	// la c'est la méthode pour 
	public void upsideDown(Player player, Scanner input) 
	{		
		System.out.println("voici vos cartes : ");
		for(int i=0; i<2;i++) {
			System.out.println(hand[i].getValue() +" de "+ hand[i].getColor()); // on affiche les cartes du joueur
		}

		System.out.println("Quelle carte voulez-vous garder cachée?");

		int numC = input.nextInt() ; // demande au joueur de rentrer un numéro entre 1 et 2
		
		((Map<String, Card>) offer).put("Down", hand[numC-1]); // -1 car le tableau commence à l'indice 0, je caste l'offer  
		((Map<String, Card>) offer).put("Up", hand[numC%2]); // avec le modulo 2 on obtient la case manquante, je caste l'offer
		System.out.println(player.pseudo  + " a caché " + ((Map<String, Card>) offer).get("Down").getValue() + " de " + ((Map<String, Card>) offer).get("Down").getColor());
		/* et la on affiche le pseudo du player en paramètre, avec get(Down) et la value de la carte, et la couleur
		*/
		
		Player.listOffer.put(player.pseudo, player.offer); // on ajoute l'offre du player a la listOffer.
		
	}
	
	
	 
	
	public Jest getJest()
	{
		return jest ;
	}


}
