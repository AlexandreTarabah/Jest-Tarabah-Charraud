package vue;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.NoSuchElementException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class DrawDeckPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private LinkedList<Image> cartes;
	private LinkedList<Image> talon;
	
	public DrawDeckPanel(){
		super();
		this.setLayout(new BorderLayout());
		this.setOpaque(false);
		this.cartes = new LinkedList<Image>();
		this.genererCartes();
	}
	
	
	
	public void genererCartes(){
		try {
			Image joker = ImageIO.read(new File("img/Joker.jpg"));
			cartes.add(joker);
			
			
			Image unCoeur = ImageIO.read(new File("img/AsCoeur.jpg"));
			cartes.add(unCoeur);
			Image deuxCoeur = ImageIO.read(new File("img/DeuxCoeur.jpg"));
			cartes.add(deuxCoeur);
			Image troisCoeur = ImageIO.read(new File("img/TroisCoeur.jpg"));
			cartes.add(troisCoeur);
			Image quatreCoeur = ImageIO.read(new File("img/QuatreCoeur.jpg"));
			cartes.add(quatreCoeur);
			
			
			
			Image unTrefle = ImageIO.read(new File("img/UnTrefle.jpg"));
			cartes.add(unTrefle);
			Image deuxTrefle = ImageIO.read(new File("img/DeuxTrefle.jpg"));
			cartes.add(deuxTrefle);
			Image troisTrefle = ImageIO.read(new File("img/TroisTrefle.jpg"));
			cartes.add(troisTrefle);
			Image quatreTrefle = ImageIO.read(new File("img/QuatreTrefle.jpg"));
			cartes.add(quatreTrefle);
			
			
			
			

			Image unCarreau = ImageIO.read(new File("img/AsCarreau.jpg"));
			cartes.add(unCarreau);
			Image deuxCarreau = ImageIO.read(new File("img/DeuxCarreau.jpg"));
			cartes.add(deuxCarreau);
			Image troisCarreau = ImageIO.read(new File("img/TroisCarreau.jpg"));
			cartes.add(troisCarreau);
			Image quatreCarreau = ImageIO.read(new File("img/QuatreCarreau.jpg"));
			cartes.add(quatreCarreau);
			
			
			
			
			
			Image unPique = ImageIO.read(new File("img/AsPique.jpg"));
			cartes.add(unPique);
			Image deuxPique = ImageIO.read(new File("img/DeuxPique.jpg"));
			cartes.add(deuxPique);
			Image troisPique = ImageIO.read(new File("img/TroisPique.jpg"));
			cartes.add(troisPique);
			Image quatrePique = ImageIO.read(new File("img/QuatrePique.jpg"));
			cartes.add(quatrePique);
			
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public LinkedList<Image> getCartes() {
		return cartes;
	}

	public void setCartes(LinkedList<Image> cartes) {
		this.cartes = cartes;
	}

	
}
