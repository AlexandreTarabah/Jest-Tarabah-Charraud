package controleur;

import java.awt.event.*;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.JOptionPane;

import modele.carte.Card;
import modele.game.Game;
import modele.joueur.Player;
import vue.Home;
import vue.Parametres;
import vue.Plateau;
import vue.Regles;
import musique.SonWav ;

/**
 * Classe controleur qui fait du pattern MVC (modele-vue-controleur)
 * Cette classe contient : 
 * <ul>
 * <li> Sa partie game </li>
 * <li> son menu home </li>
 * <li> ses parametres </li>
 * <li> ses regles </li>
 * <li> son plateau </li>
 * </ul>
 */

 public class Controleur implements ActionListener {

	 private Game game;
	 private Home home;
	 private Parametres parametres;
	 private Regles regles;
	 private Plateau plateau;

	 /**
	  * Le constructeur qui met a null tous les composants 
	  */
	 public Controleur() {
		 game = null;
		 home = null;
		 parametres = null;
		 regles = null;
		 plateau = null;
	 }

	
	 public void setModele(Game game) {
		 this.game = game ;
	 }

	 /**
	  * Met � jour la vue en fonction des param�tres (home, parametres, regles et plateau) 
	  * @param home
	  * @param p
	  * @param r
	  * @param pl
	  */
	 public void setVue(Home home, Parametres p, Regles r, Plateau pl) {
		 this.home = home ;
		 this.parametres = p;
		 this.regles = r;
		 this.plateau = pl;
	 }
	 
	 /**
	  * C'est ici qu'on traite l'action sur les composants de la fenetre : 
	  * en fonction de ActionCommand, on active ou d�sactive des fenetres sur l'�cran
	  * Quand l'action commande est "jouer" on ouvre des boites de dialogues si les valeurs rentr�es sont pas correctes
	  * Si tout est valid�, on lance le thread 
	  */
	 public void actionPerformed(ActionEvent e) {
		 if (e.getActionCommand() == "nouvellegame"){
			 parametres.setVisible(true);
			 home.setVisible(false);
		 }
		 if (e.getActionCommand() == "reglesdujeu"){
			 regles.getFrame().setVisible(true);
			 home.setVisible(false);
		 }

		 if (e.getActionCommand() == "retour"){
			 home.setVisible(true);
			 regles.setVisible(false);
		 }
		 if (e.getActionCommand() == "quittergame"){
			 System.exit(0);
		 }
		 if (e.getActionCommand() == "jouer"){
			 if (parametres.getDifficulte() == 0){
				 JOptionPane.showMessageDialog(null, "Vous n'avez pas choisi la difficult� !", "Erreur", JOptionPane.ERROR_MESSAGE);
			 }
			 else if ((parametres.getNbrVirtuels() + parametres.getNbrReels()) > 4 || (parametres.getNbrVirtuels() + parametres.getNbrReels()) < 3){
				 JOptionPane.showMessageDialog(null, "Le nombre de joueurs choisi n'est pas correct !", "Erreur", JOptionPane.ERROR_MESSAGE);
			 }
			 else if ((parametres.getClassique().isSelected() == false && parametres.getInversion().isSelected() == false)){
				 JOptionPane.showMessageDialog(null, "Vous n'avez pas choisi votre mode de jeu !", "Erreur", JOptionPane.ERROR_MESSAGE);
			 }
			 else if ((parametres.getActive().isSelected() == false && parametres.getInactive().isSelected() == false)){
				 JOptionPane.showMessageDialog(null, "Pr�cisez si vous voulez jouer avec l'extension !", "Erreur", JOptionPane.ERROR_MESSAGE);
			 }
			 else{
				 parametres.setVisible(false);
				 plateau.getFrame().setVisible(true);
				 game.reglerParametres(parametres.getDifficulte(), parametres.getNbrVirtuels(), parametres.getNbrReels(),  parametres.getVariante(),  parametres.getExtension());
				 Thread manche = new Thread(game);
				 new SonWav("file/Mozart_12 Variations_In_C_K.265.wav");
				 manche.start();
			 }
		 }
		 
	 }

	
	/**
	 * Cette m�thode permet d'appeler la m�thode upsideDown du joueur si les valeurs sont v�rifi�es 
	 * @param reponseUD
	 * @param player
	 */

	public void methodecontrolupsideDown( int reponseUD,  Player player) {
		player.upsideDown(reponseUD, game);
		
	}



/**
 * Cette m�thode effectue toute la v�rification des valeurs rentr�es pour stealCards.
 * Si les choix ne sont pas corrects (Main incompl�te, ou victime = null, ou la victime n'existe pas) 
 * On appelle ensuite la m�thode stealCards pour du joueur avec les param�tres v�rifi�s 
 * @param choiceVictime correspond au choix de la victime
 * @param choiceCardVictime correspond au choix de la carte a vol� ("up", "down")
 * @param p le joueur
 * @param g la partie 
 */

	public void methodeStealCard(String choiceVictime, String choiceCardVictime,Player p,Game g) {
	
		int nbCardOffer=0;
		for(Entry<String, HashMap<String, Card>> map : g.listOffer.entrySet()) {


			{nbCardOffer = nbCardOffer+map.getValue().size();}
		}
		
		
		if(game.nbPlayers==3) {
			if(nbCardOffer>4) {
				while(game.listOffer.containsKey(choiceVictime)==false || game.listOffer.get(choiceVictime).size()<2 || choiceVictime==null || game.getIsPlaying().getPseudo().equals(choiceVictime)) {
					
					choiceVictime = JOptionPane.showInputDialog(null, "Ce joueur s'est d�j� fait d�rob� ! ", "Choix incorrect ", JOptionPane.QUESTION_MESSAGE);
									
				}
			}
				else {
					
					if(nbCardOffer==4) {
							if(game.getIsPlaying().getOffer().size()==2) {
							while(game.listOffer.containsKey(choiceVictime)==false || game.listOffer.get(choiceVictime).size()<2 || choiceVictime==null) {
								choiceVictime = JOptionPane.showInputDialog(null, "Ce joueur s'est d�j� fait d�rob� ! ", "Choix incorrect ", JOptionPane.QUESTION_MESSAGE);
								
							}
							}else
								{
								if(game.getIsPlaying().getOffer().size()<2) {
								
									while(game.listOffer.containsKey(choiceVictime)==false || game.listOffer.get(choiceVictime).size()<2 || choiceVictime==null || game.getIsPlaying().getPseudo().equals(choiceVictime)) {
										choiceVictime = JOptionPane.showInputDialog(null, "Ce joueur s'est d�j� fait d�rob� ! ", "Choix incorrect ", JOptionPane.QUESTION_MESSAGE);
									
									}
								}
							} 
						}
					}
		}else 

			{ 
			if(game.nbPlayers==4) {
					if(nbCardOffer>5) {
					while(game.listOffer.containsKey(choiceVictime)==false || game.listOffer.get(choiceVictime).size()<2 || choiceVictime==null || game.getIsPlaying().getPseudo().equals(choiceVictime)) {
		
						choiceVictime = JOptionPane.showInputDialog(null, "Choix incorrect ", "erreur", JOptionPane.QUESTION_MESSAGE);
										
					}
				}else
					{ if(nbCardOffer==5) {
						if(game.getIsPlaying().getOffer().size()==2) {
							while(game.listOffer.containsKey(choiceVictime)==false || game.listOffer.get(choiceVictime).size()<2 || choiceVictime==null) {
								choiceVictime = JOptionPane.showInputDialog(null, "Choix incorrect", "erreur", JOptionPane.QUESTION_MESSAGE);
								
							}
							}else {
								if(game.getIsPlaying().getOffer().size()<2) {
									while(game.listOffer.containsKey(choiceVictime)==false || game.listOffer.get(choiceVictime).size()<2 || choiceVictime==null || game.getIsPlaying().getPseudo().equals(choiceVictime)) {
										choiceVictime = JOptionPane.showInputDialog(null, "Choix incorrect ", "erreur", JOptionPane.QUESTION_MESSAGE);
									
												}	
											}				
										}
									}
								}
							}
						}
		game.setVictime(choiceVictime);
		p.stealCard(choiceVictime, choiceCardVictime, game);
	
		
	}
	
}