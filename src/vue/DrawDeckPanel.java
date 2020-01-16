package vue;

import java.awt.BorderLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class DrawDeckPanel extends JPanel{

	/**Correspond au drawdeck sous forme graphique 
	 * il est définit par une liste d'image : les cartes du drawdeck 
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private LinkedList<Image> cartes;
	
	/**
	 * on créé les cartes (liste d'image) et on génere les cartes @see DrawdeckPanel#genererCartes()
	 */
	public DrawDeckPanel(){
		super();
		this.setLayout(new BorderLayout());
		this.setOpaque(false);
		this.cartes = new LinkedList<Image>();
		this.genererCartes();
	}
	
	/**
	 * cette méthode permet de d'assigner a la liste d'image la bonne image,
	  pour qu'elle être dessinée ensuite dans la méthode drawComponent @see CardPanel#drawComponent()
	  * Les fichiers correspondant à chacunes des cartes sont dans un dossier img 
	 */
	
	public void genererCartes(){
		try {
			Image joker = ImageIO.read(new File("img/Joker.png"));
			cartes.add(joker);
			
			
			Image unCoeur = ImageIO.read(new File("img/AsCoeur.png"));
			cartes.add(unCoeur);
			Image deuxCoeur = ImageIO.read(new File("img/DeuxCoeur.png"));
			cartes.add(deuxCoeur);
			Image troisCoeur = ImageIO.read(new File("img/TroisCoeur.png"));
			cartes.add(troisCoeur);
			Image quatreCoeur = ImageIO.read(new File("img/QuatreCoeur.png"));
			cartes.add(quatreCoeur);
			
			
			Image unCarreau = ImageIO.read(new File("img/AsCarreau.png"));
			cartes.add(unCarreau);
			Image deuxCarreau = ImageIO.read(new File("img/DeuxCarreau.png"));
			cartes.add(deuxCarreau);
			Image troisCarreau = ImageIO.read(new File("img/TroisCarreau.png"));
			cartes.add(troisCarreau);
			Image quatreCarreau = ImageIO.read(new File("img/QuatreCarreau.png"));
			cartes.add(quatreCarreau);
			
			
			Image unTrefle = ImageIO.read(new File("img/AsTrefle.png"));
			cartes.add(unTrefle);
			Image deuxTrefle = ImageIO.read(new File("img/DeuxTrefle.png"));
			cartes.add(deuxTrefle);
			Image troisTrefle = ImageIO.read(new File("img/TroisTrefle.png"));
			cartes.add(troisTrefle);
			Image quatreTrefle = ImageIO.read(new File("img/QuatreTrefle.png"));
			cartes.add(quatreTrefle);
			
			
			Image unPique = ImageIO.read(new File("img/AsPique.png"));
			cartes.add(unPique);
			Image deuxPique = ImageIO.read(new File("img/DeuxPique.png"));
			cartes.add(deuxPique);
			Image troisPique = ImageIO.read(new File("img/TroisPique.png"));
			cartes.add(troisPique);
			Image quatrePique = ImageIO.read(new File("img/QuatrePique.png"));
			cartes.add(quatrePique);
			
			
			Image sixCoeur = ImageIO.read(new File("img/SixCoeur.png"));
			cartes.add(sixCoeur);
			Image sixCarreau = ImageIO.read(new File("img/SixCarreau.png"));
			cartes.add(sixCarreau);
			Image sixTrefle = ImageIO.read(new File("img/SixTrefle.png"));
			cartes.add(sixTrefle);
			Image sixPique = ImageIO.read(new File("img/SixPique.png"));
			cartes.add(sixPique);
			
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @return la liste d'image : les cartes 
	 */
	public LinkedList<Image> getCartes() {
		return cartes;
	}

	/**
	 * permet de modifier la liste de cartes 
	 * @param cartes
	 */
	public void setCartes(LinkedList<Image> cartes) {
		this.cartes = cartes;
	}

	
}
