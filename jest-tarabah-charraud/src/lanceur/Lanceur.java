package lanceur;
import javax.swing.UIManager;

import controleur.Controleur;
import modele.game.Game;
import vue.Home;
import vue.Parametres;
import vue.Plateau;
import vue.Regles;
<<<<<<< HEAD
import controleur.Controleur;
import modele.game.Game;
import vue.*;

public class Lanceur {
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(
            UIManager.getCrossPlatformLookAndFeelClassName());
		} 
		catch (Exception e) {

		}
		Controleur controleur = new Controleur();
		Game partie = new Game();
		Home accueil = new Home();
		Regles regles = new Regles();
		Parametres parametres = new Parametres();
		Plateau plateau = new Plateau(partie, controleur);

		controleur.setModele(partie);
		controleur.setVue(accueil, parametres, regles, plateau);
		accueil.setListener(controleur);

=======

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

		controleur.setModele(game);
		controleur.setVue(home, parametres, regles, plateau);
		home.setListener(controleur);
>>>>>>> branch 'master' of https://github.com/AlexandreTarabah/Jest-Tarabah-Charraud
		regles.setListener(controleur);
		parametres.setListener(controleur);
	}
}
