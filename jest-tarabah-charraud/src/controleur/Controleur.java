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
			 else{
				 parametres.setVisible(false);
				 plateau.getFrame().setVisible(true);
				 game.reglerParametres(parametres.getDifficulte(), parametres.getNbrVirtuels(), parametres.getNbrReels());
				 Thread manche = new Thread(game);
				 manche.start();
			 }
		 }
		 if (e.getActionCommand() == "affichercartes"){
			 plateau.afficherJeu(plateau.getPjJoue());
		 }
		 
	 }

	public void controleCarte(Carte carte) {
		if (carte.determinerCarteJouable(game.getTalon())){
			game.getJoueurJoue().poserCarte(carte, game.getTalon(), game.getPioche(), game);
		}
		else {
			JOptionPane.showMessageDialog(null, "La carte que vous avez choisi n'est pas jouable !", "Mauvaise carte", JOptionPane.ERROR_MESSAGE);
			plateau.choisirCarte();
		}
	}
	
	public void controlePioche(){
		game.getPioche().verifieTalon(game.getTalon(), 1);
		game.getPioche().distribuerCarte(game.getJoueurJoue(), 1);
	}

	public void controleCouleur(String couleur) {
		if (couleur.equals(null)){
			plateau.choisirCouleur();
		}
		else{
			if (couleur.equals("Noir")){
				plateau.choisirCouleur();
			}
			else if (couleur.equals("Bleu")){
				game.getTalon().setCouleurTalon(Carte.BLEU);
			}
			else if (couleur.equals("Rouge")){
				game.getTalon().setCouleurTalon(Carte.ROUGE);
			}
			else if (couleur.equals("Jaune")){
				game.getTalon().setCouleurTalon(Carte.JAUNE);
			}
			else{
				game.getTalon().setCouleurTalon(Carte.VERT);
			}
		}
	}

	public void controleUNO() {
		if (game.getJoueurJoue().getCartesEnMain().size()==1){
			 int reponseUNO = JOptionPane.showConfirmDialog(null, 
				        "Voulez-vous dire Uno ?", 
				        "UNO", 
				        JOptionPane.YES_NO_OPTION, 
				        JOptionPane.QUESTION_MESSAGE);
				if(reponseUNO == JOptionPane.OK_OPTION){
					game.getJoueurJoue().direUno();
				}
		 }	
	}

	
	
	public void nouvelleManche(int nm) {
		if (nm == JOptionPane.OK_OPTION){
			Thread manche = new Thread(game);
			manche.start();
		}
		else if (nm == JOptionPane.NO_OPTION){
			System.exit(0);
		}
		else{
			plateau.afficherNouvelleManche();
		}
	}

	public void methodecontrolupsideDown(int reponseUD, Player player) {
		player.upsideDown(reponseUD);
		
	}

	public void methodeStealCard(String choiceVictime, String choiceCardVictime) {

	public void methodeStealCard(String choiceVictime, String choiceCardVictime,Player p) {
		while(game.listOffer.containsKey(choiceVictime)==false) {
			
			choiceVictime = JOptionPane.showInputDialog(null, "Veuillez entrer un joueur existant", "erreur", JOptionPane.QUESTION_MESSAGE);
		}


		while(game.listOffer.get(choiceVictime).size()<2) {
			
			choiceVictime = JOptionPane.showInputDialog(null, "Veuillez entrer une offre compl�te", "erreur", JOptionPane.QUESTION_MESSAGE);
			
		}

		if(game.nbPlayers==3) {
			if(game.listOffer.size()>4) {
				while( game.getIsPlaying().equals(choiceVictime)) {
					
					choiceVictime = JOptionPane.showInputDialog(null, "Vous ne pouvez pas vous choisir\n choisissez un joueur ", "erreur", JOptionPane.QUESTION_MESSAGE);
									
				}
			}
		}else 

			if(game.nbPlayers==4) {
				if(game.listOffer.size()>5) {
					while( game.getIsPlaying().equals(choiceVictime)) {
		
						choiceVictime = JOptionPane.showInputDialog(null, "Vous ne pouvez pas vous choisir\n choisissez un joueur ", "erreur", JOptionPane.QUESTION_MESSAGE);
										
					}
				}
			}
		p.stealCard(choiceVictime, choiceCardVictime, game);
		choiceVictime=game.getVictime();
		
	}
	
}