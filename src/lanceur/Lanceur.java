package lanceur;
import javax.swing.UIManager;

import controleur.Controleur;
import modele.game.Game;
import vue.Home;
import vue.Parametres;
import vue.Plateau;
import vue.Regles;


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
		regles.setListener(controleur);
		parametres.setListener(controleur);
	}
}
//push