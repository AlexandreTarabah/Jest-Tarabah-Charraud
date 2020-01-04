package  vue ;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class FenetreSaisie extends JOptionPane{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private static String pseudo;
	public static void main(String[] args) {
		FenetreSaisie f = new FenetreSaisie();
		//On crée une nouvelle instance de notre FenetreTexte
		setPseudo(JOptionPane.showInputDialog("rentrer le pseudo"));
	}
	public static String getPseudo() {
		return pseudo;
	}
	public static void setPseudo(String pseudo) {
		FenetreSaisie.pseudo = pseudo;
	}
	
}