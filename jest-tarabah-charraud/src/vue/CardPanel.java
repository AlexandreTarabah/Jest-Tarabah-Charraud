package vue;

import java.awt.Graphics;
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
		this.setOpaque(false);
		this.setLayout(null);
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

		super.paintComponent(g);
		for(int i=0; i<2;i++) {
			g.drawImage(jeu.get(i), i*30, 0, 80, 140, this);
		}


		/*ListIterator<Image> iJeu = jeu.listIterator();
		while (iJeu.hasNext()){
			g.drawImage(iJeu.next(), (iJeu.previousIndex()*30), 0, 80, 140, this);
		}*/
<<<<<<< HEAD

=======
>>>>>>> branch 'master' of https://github.com/AlexandreTarabah/Jest-Tarabah-Charraud

	}

}

