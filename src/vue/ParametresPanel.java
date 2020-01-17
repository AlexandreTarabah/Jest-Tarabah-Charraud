package vue;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * 
 * Classe représentant le panel associé a la classe Parametres 
 *
 */
public class ParametresPanel extends JPanel{

	private static final long serialVersionUID = 1L;
/**
 * Le constructeur de la classe héritant de JPanel
 */
	public ParametresPanel(){
		super();
	}
/**
 * Méthode qui permet de mettre a jour le fond de la fenetre avec fond-vert, et les auteurs
 */
	public void paintComponent(Graphics g){
		try {
			Image img = ImageIO.read(new File("img/fond-grunge-vert.jpg"));
			g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
			Font font = new Font("Courier", Font.BOLD, 18);
			g.setFont(font);
			g.drawString("UTT - LO02 - 2019/2020", 10, 760);
			g.drawString("Auteurs : Alexandre Tarabah et Pelgrim Charraud  ©", 540, 760);
		} catch (IOException e) {
			e.printStackTrace();
		}
	} 
}
