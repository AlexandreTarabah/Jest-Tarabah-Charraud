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


/* On implŽmente ActionListener qui permet de traiter les ŽvŽnements du bouton */
 public class Controleur implements ActionListener {

	 private Game game;
	 private Home home;
	 private Parametres parametres;
	 private Regles regles;
	 private Plateau plateau;

	 public Controleur() {
		 game = null;
		 home = null;
		 parametres = null;
		 regles = null;
		 plateau = null;
	 }

	 /* Permettra de connaitre le modŽle */
	 public void setModele(Game game) {
		 this.game = game ;
	 }

	 /* Permettra de connaitre la vue */
	 public void setVue(Home home, Parametres p, Regles r, Plateau pl) {
		 this.home = home ;
		 this.parametres = p;
		 this.regles = r;
		 this.plateau = pl;
	 }
	 
	 /* C'est ici que l'on traite l'action rŽcupŽrŽ : implŽmentation due ˆ l'interface ActionListener */
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
				 JOptionPane.showMessageDialog(null, "Vous n'avez pas choisi la difficulté !", "Erreur", JOptionPane.ERROR_MESSAGE);
			 }
			 else if ((parametres.getNbrVirtuels() + parametres.getNbrReels()) > 4 || (parametres.getNbrVirtuels() + parametres.getNbrReels()) < 1){
				 JOptionPane.showMessageDialog(null, "Le nombre de joueurs choisi n'est pas correct !", "Erreur", JOptionPane.ERROR_MESSAGE);
			 }
			 else if ((parametres.getClassique().isSelected() == false && parametres.getInversion().isSelected() == false)){
				 JOptionPane.showMessageDialog(null, "Vous n'avez pas choisi votre mode de jeu !", "Erreur", JOptionPane.ERROR_MESSAGE);
			 }
			 else if ((parametres.getActive().isSelected() == false && parametres.getInactive().isSelected() == false)){
				 JOptionPane.showMessageDialog(null, "Précisez si vous voulez jouer avec l'extension !", "Erreur", JOptionPane.ERROR_MESSAGE);
			 }
			 else{
				 parametres.setVisible(false);
				 plateau.getFrame().setVisible(true);
				 game.reglerParametres(parametres.getDifficulte(), parametres.getNbrVirtuels(), parametres.getNbrReels(),  parametres.getVariante(),  parametres.getExtension());
				 Thread manche = new Thread(game);
				 manche.start();
			 }
		 }
		 
	 }

	
	

	public void methodecontrolupsideDown( int reponseUD,  Player player) {
		player.upsideDown(reponseUD, game);
		
	}





	public void methodeStealCard(String choiceVictime, String choiceCardVictime,Player p,Game g) {
	
		int nbCardOffer=0;
		for(Entry<String, HashMap<String, Card>> map : g.listOffer.entrySet()) {


			{nbCardOffer = nbCardOffer+map.getValue().size();}
		}
		
		
		if(game.nbPlayers==3) {
			if(nbCardOffer>4) {
				while(game.listOffer.containsKey(choiceVictime)==false || game.listOffer.get(choiceVictime).size()<2 || choiceVictime==null || game.getIsPlaying().getPseudo().equals(choiceVictime)) {
					
					choiceVictime = JOptionPane.showInputDialog(null, "Choix incorrect ", "erreur", JOptionPane.QUESTION_MESSAGE);
									
				}
			}
		}else 

			if(game.nbPlayers==4) {
				if(nbCardOffer>5) {
					while(game.listOffer.containsKey(choiceVictime)==false || game.listOffer.get(choiceVictime).size()<2 || choiceVictime==null || game.getIsPlaying().getPseudo().equals(choiceVictime)) {
		
						choiceVictime = JOptionPane.showInputDialog(null, "Choix incorrect ", "erreur", JOptionPane.QUESTION_MESSAGE);
										
					}
				}
			}
		p.stealCard(choiceVictime, choiceCardVictime, game);
		game.setVictime(choiceVictime);
		
	}
	
}