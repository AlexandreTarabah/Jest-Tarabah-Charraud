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
			Image joker = ImageIO.read(new File("Joker.PNG"));
			cartes.add(joker);
			
			
			Image unCoeur = ImageIO.read(new File("AsCoeur.PNG"));
			cartes.add(unCoeur);
			Image deuxCoeur = ImageIO.read(new File("DeuxCoeur.PNG"));
			cartes.add(deuxCoeur);
			Image troisCoeur = ImageIO.read(new File("TroisCoeur.PNG"));
			cartes.add(troisCoeur);
			Image quatreCoeur = ImageIO.read(new File("QuatreCoeur.PNG"));
			cartes.add(quatreCoeur);
			
			
			
			Image unTrefle = ImageIO.read(new File("UnTrefle.PNG"));
			cartes.add(unTrefle);
			Image deuxTrefle = ImageIO.read(new File("DeuxTrefle.PNG"));
			cartes.add(deuxTrefle);
			Image troisTrefle = ImageIO.read(new File("TroisTrefle.PNG"));
			cartes.add(troisTrefle);
			Image quatreTrefle = ImageIO.read(new File("QuatreTrefle.PNG"));
			cartes.add(quatreTrefle);
			
			
			
			

			Image unCarreau = ImageIO.read(new File("AsCarreau.PNG"));
			cartes.add(unCarreau);
			Image deuxCarreau = ImageIO.read(new File("DeuxCarreau.PNG"));
			cartes.add(deuxCarreau);
			Image troisCarreau = ImageIO.read(new File("TroisCarreau.PNG"));
			cartes.add(troisCarreau);
			Image quatreCarreau = ImageIO.read(new File("QuatreCarreau.PNG"));
			cartes.add(quatreCarreau);
			
			
			
			
			
			Image unPique = ImageIO.read(new File("AsPique.PNG"));
			cartes.add(unPique);
			Image deuxPique = ImageIO.read(new File("DeuxPique.PNG"));
			cartes.add(deuxPique);
			Image troisPique = ImageIO.read(new File("TroisPique.PNG"));
			cartes.add(troisPique);
			Image quatrePique = ImageIO.read(new File("QuatrePique.PNG"));
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
