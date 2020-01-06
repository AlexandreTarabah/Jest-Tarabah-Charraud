package controleur;

import java.awt.event.*;

import javax.swing.JOptionPane;

import modele.game.Game;
import modele.joueur.Player;
import vue.Home;
import vue.Parametres;
import vue.Plateau;
import vue.Regles;


/* On impl�mente ActionListener qui permet de traiter les �v�nements du bouton */
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

	 /* Permettra de connaitre le mod�le */
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
	 
	 /* C'est ici que l'on traite l'action r�cup�r� : impl�mentation due � l'interface ActionListener */
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
			 else if ((parametres.getNbrVirtuels() + parametres.getNbrReels()) > 4 || (parametres.getNbrVirtuels() + parametres.getNbrReels()) < 1){
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
				 manche.start();
			 }
		 }
		 
	 }

	
	

	public void methodecontrolupsideDown( int reponseUD,  Player player) {
		player.upsideDown(reponseUD, game);
		
	}





	public void methodeStealCard(String choiceVictime, String choiceCardVictime,Player p) {
		while(game.listOffer.containsKey(choiceVictime)==false || game.listOffer.get(choiceVictime).size()<2 || choiceVictime==null) {
			
			choiceVictime = JOptionPane.showInputDialog(null, "Veuillez entrer un joueur existant", "erreur", JOptionPane.QUESTION_MESSAGE);
			
		}

		
		if(game.nbPlayers==3) {
			if(game.nbCardOffer>4) {
				while( game.getIsPlaying().getPseudo().equals(choiceVictime)) {
					
					choiceVictime = JOptionPane.showInputDialog(null, "Vous ne pouvez pas vous choisir\n choisissez un joueur ", "erreur", JOptionPane.QUESTION_MESSAGE);
									
				}
			}
		}else 

			if(game.nbPlayers==4) {
				if(game.nbCardOffer>5) {
					while( game.getIsPlaying().getPseudo().equals(choiceVictime)) {
		
						choiceVictime = JOptionPane.showInputDialog(null, "Vous ne pouvez pas vous choisir\n choisissez un joueur ", "erreur", JOptionPane.QUESTION_MESSAGE);
										
					}
				}
			}
		p.stealCard(choiceVictime, choiceCardVictime, game);
		game.setVictime(choiceVictime);
		
	}
	
}