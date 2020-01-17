package vue;


import java.awt.Graphics;


import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.ListIterator;

import javax.imageio.ImageIO;

import javax.swing.JPanel;

/**
 * Cette classe correspond à l'interface graphique des cartes du joueur 
 * L'interface des cartes est caractérisée par : 
 * <ul>
 * <li> une liste d'image "jeu"</li>
 * <li> un boolean qui renseigne sur l'état de visibilité de la carte </li>
 * </ul>
 *
 */

public class CardPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private LinkedList<Image> jeu;
	private boolean cartesVisibles;
/**
 * LE constructeur du Panel de carte : 
 * On met à jour : 
 * <ul>
 * <li> le jeu qui correspond à la liste rentrée en paramètre </li>
 * <li> l'état de visibilité des cartes a true </li>
 * <li> setOpaque puis setLayout pour un affichage optimal </li>
 * </ul>
 * @param j
 */
	public CardPanel(LinkedList<Image> j){
		super();
		this.jeu = j;
		this.cartesVisibles = true;
		this.setOpaque(true);
		this.setLayout(null);
	}
	
	/**
	 * 
	 * @return la liste d'image "jeu"
	 */

	public LinkedList<Image> getJeu() {
		return jeu;
	}
	
	/**
	 * modifie la liste d'image "jeu"
	 * @param jeu
	 */

	public void setJeu(LinkedList<Image> jeu) {
		this.jeu = jeu;
	}


	/**
	 * La méthode la plus importante du cardPanel qui permet de dessiner les cartes sur le panel 
	 * Tant qu'il y a des cartes dans le jeu, on dessine à l'endroit indiqué la carte correspondante
	 */
	public void paintComponent(Graphics g){

		Image dos = null;
		try {
			dos = ImageIO.read(new File("img/Joker.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		ListIterator<Image> iJeu = jeu.listIterator();
		while (iJeu.hasNext()){


			g.drawImage(iJeu.next(), (iJeu.previousIndex()*80), 0, 80, 140, this);

		}

	}

}








