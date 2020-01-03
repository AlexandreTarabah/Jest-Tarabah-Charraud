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
	
	public void paintComponent(Graphics g){
		try {
		      Image pioche = ImageIO.read(new File("Dos.bmp"));
		      g.drawImage(pioche, 0, 0, 100, 180, this);
		     /* try { 
		    	   Image talon = this.talon.getLast();
		    	     g.drawImage(talon, 120, 0, 100, 180, this);
		      }
		      catch (NoSuchElementException e) {}
		} catch (IOException e) {
		      e.printStackTrace();
		}*/
	}
	
	public void genererCartes(){
		try {
			Image joker = ImageIO.read(new File("Speciales/Joker.bmp"));
			cartes.add(joker);
			
			
			Image unCoeur = ImageIO.read(new File("Vert/Un Vert.bmp"));
			cartes.add(unCoeur);
			Image deuxCoeur = ImageIO.read(new File("Vert/Deux Vert.bmp"));
			cartes.add(deuxCoeur);
			Image troisCoeur = ImageIO.read(new File("Vert/Trois Vert.bmp"));
			cartes.add(troisCoeur);
			Image quatreCoeur = ImageIO.read(new File("Vert/Quatre Vert.bmp"));
			cartes.add(quatreCoeur);
			Image sixCoeur = ImageIO.read(new File("Jaune/Six Jaune.bmp"));
			cartes.add(sixCoeur);
			
			
			Image unTrefle = ImageIO.read(new File("Vert/Un Vert.bmp"));
			cartes.add(unCoeur);
			Image deuxTrefle = ImageIO.read(new File("Vert/Deux Vert.bmp"));
			cartes.add(deuxCoeur);
			Image troisTrefle = ImageIO.read(new File("Vert/Trois Vert.bmp"));
			cartes.add(troisCoeur);
			Image quatreTrefle = ImageIO.read(new File("Vert/Quatre Vert.bmp"));
			cartes.add(quatreCoeur);
			Image sixTrefle = ImageIO.read(new File("Jaune/Six Jaune.bmp"));
			cartes.add(sixCoeur);
			
			
			

			Image unCarreau = ImageIO.read(new File("Vert/Un Vert.bmp"));
			cartes.add(unCoeur);
			Image deuxCarreau = ImageIO.read(new File("Vert/Deux Vert.bmp"));
			cartes.add(deuxCoeur);
			Image troisCarreau = ImageIO.read(new File("Vert/Trois Vert.bmp"));
			cartes.add(troisCoeur);
			Image quatreCarreau = ImageIO.read(new File("Vert/Quatre Vert.bmp"));
			cartes.add(quatreCoeur);
			Image sixCarreau = ImageIO.read(new File("Jaune/Six Jaune.bmp"));
			cartes.add(sixCoeur);
			
			
			
			
			Image unPique = ImageIO.read(new File("Vert/Un Vert.bmp"));
			cartes.add(unCoeur);
			Image deuxPique = ImageIO.read(new File("Vert/Deux Vert.bmp"));
			cartes.add(deuxCoeur);
			Image troisPique = ImageIO.read(new File("Vert/Trois Vert.bmp"));
			cartes.add(troisCoeur);
			Image quatrePique = ImageIO.read(new File("Vert/Quatre Vert.bmp"));
			cartes.add(quatreCoeur);
			Image sixPique = ImageIO.read(new File("Jaune/Six Jaune.bmp"));
			cartes.add(sixCoeur);
			
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
