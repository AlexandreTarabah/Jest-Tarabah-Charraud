package lanceur;
import javax.swing.UIManager;

import controleur.Controleur;
import modele.game.Game;
import musique.SonWav;
import vue.Home;
import vue.Parametres;
import vue.Plateau;
import vue.Regles;

/**
 *Cette classe regroupe le "lanceur" : c'est ici qu'on lance la partie :  
 *Cette classe permet d'instancier et de mettre sur écoute tous les composants principaux des fenetres graphiques à savoir : 
 *<ul>
 *<li> controleur </li>
 *<li> game </li>
 *<li> home </li>
 *<li> regle </li>
 *<li> parametres </li>
 *<li> plateau </li>
 *</ul>
 *
 *On met ensuite sur écoute tous les composants grâce a la méthode .setListener
 *
 */
public class Lanceur {
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(
            UIManager.getCrossPlatformLookAndFeelClassName());
		} 
		catch (Exception e) {
		}
		Controleur controleur = new Controleur();
		Game game = new Game();
		Home home = new Home();
		Regles regles = new Regles();
		Parametres parametres = new Parametres();
		Plateau plateau = new Plateau(game, controleur);
		SonWav musique = new SonWav("mozart.wav") ; 
		
		controleur.setMusique(musique) ;
		controleur.setModele(game);
		controleur.setVue(home, parametres, regles, plateau);
		home.setListener(controleur);
		regles.setListener(controleur);
		parametres.setListener(controleur);
	}
}