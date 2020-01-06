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
