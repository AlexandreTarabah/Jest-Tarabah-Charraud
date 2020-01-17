package vue ;

import java.awt.* ;
import java.io.File ;
import java.io.IOException ;

import javax.imageio.ImageIO ;
import javax.swing.JPanel ;
/**
 *Cette classe représente le contenu du menu d'acceuil 
 *Cette classe permet principalement de définir le contenu du menu d'acceuil 
 * 
 *
 *
 */
public class HomePanel extends JPanel{
	private static final long serialVersionUID = 1L;
/**
 * le constructeur du menu qui hérite de JPanel 
 */
	public HomePanel(){
		super();
	}
	
	
	/**
	 * Méthode paintComponent qui permet de peindre le panel, mettre a jour les images, les textes etc.. 
	 * @param g 
	 */
	public void paintComponent(Graphics g){
	    try {
	      Image img = ImageIO.read(new File("img/JEST_Home.png"));
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
