package lanceur;
import javax.swing.UIManager;

import controleur.Controleur;
import modele.game.Game;
import vue.Home;
import vue.Parametres;
import vue.Plateau;
import vue.Regles;
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

		regles.setListener(controleur);
		parametres.setListener(controleur);
	}
}
