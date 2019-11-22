package jest_tarabah_charraud_2019_2020;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Player 
{

	private String pseudo;

	private int nump;

	public boolean isAThief;

	public boolean hasHighestFup;

	private static HashMap<String, Card> offer;

	private ArrayList<Player> listPlayer;

	private Card[] hand = new Card[2] ;

	private Jest jest ;


	//j'instancie l'objet offre, qui est aussi une collection de carte, dans le constructeur player ici 

	public Player () 
	{
		offer = new HashMap<String, Card>();
		this.jest = jest ;
	}


	public void stealCard(Player player, Scanner input)
	{
		System.out.println("Qui sera votre victime ? ");
		String victime = input.nextLine() ;
		System.out.println("Quelle carte voulez-vous lui dérober ? ");
		String stolenCard = input.nextLine();
		jest.jestCards.add(((Map<String, Card>) offer).get(stolenCard)) ; // Map est juste l'interface de HashMap

	}



	public void setPseudo(Scanner input) 
	{
		System.out.println("Entrez le nom du joueur : ");
		String name = input.nextLine() ;
		this.pseudo = name ;
	}

	public void setHand(int i, Card card)
	{
		this.hand[i] = card ;
	}

	// la c'est la méthode pour 
	public void upsideDown(Scanner input) 
	{

		System.out.println("Quelle carte voulez-vous garder cachée?");

		int numC = input.nextInt() ; // demande au joueur de rentrer un numéro entre 1 et 2
		offer.put("Down", hand[numC-1]); // -1 car le tableau commence à l'indice 0
//		System.out.println(hand[numC-1].getValue().name() + " " + hand[numC-1].getColor().name()); 
		offer.put("Up", hand[numC%2]); // avec le modulo 2 on obtient la case manquante
//		System.out.println(hand[numC%2].getValue().name() + " " + hand[numC-1].getColor().name());
	}


}
