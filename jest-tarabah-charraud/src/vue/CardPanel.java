package vue;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.ListIterator;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class CardPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private LinkedList<Image> jeu;
	private boolean cartesVisibles;

	public CardPanel(LinkedList<Image> j){
		super();
		this.jeu = j;
		this.cartesVisibles = true;
		this.setOpaque(true);
		this.setLayout(null);
	}

	public CardPanel() {
		// TODO Auto-generated constructor stub
	}

	public LinkedList<Image> getJeu() {
		return jeu;
	}

	public void setJeu(LinkedList<Image> jeu) {
		this.jeu = jeu;
	}

	public boolean isCartesVisibles() {
		return cartesVisibles;
	}

	public void setCartesVisibles(boolean visible) {
		this.cartesVisibles = visible;
	}

	public void paintComponent(Graphics g){

		Image dos = null;
		try {
			dos = ImageIO.read(new File("img/Joker.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		ListIterator<Image> iJeu = jeu.listIterator();
		while (iJeu.hasNext()){
			if (cartesVisibles)
				g.drawImage(iJeu.next(), (iJeu.previousIndex()*200 + 525), 300, 120, 180, this);
			else{
				iJeu.next();
				g.drawImage(dos, (iJeu.previousIndex()*200 + 525), 300, 120, 180, this);
			}
			
		}
		
		/* super.paintComponent(g);
		for(int i=0; i<2;i++) {
			g.drawImage(jeu.get(i), i*30, 0, 80, 140, this);
		} */ 

	}

}

