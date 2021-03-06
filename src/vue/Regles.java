package vue;

import java.awt.BorderLayout;
import java.awt.Font;
import java.io.*;

import javax.swing.*;
import javax.swing.text.rtf.RTFEditorKit;

import controleur.Controleur;
/**
 * Cette classe repr�sente les regles, elle contient principalement le document rtf des r�gles du JEST 
 * Elle contient : 
 * <ul>
 * <li>Un bouton retour </li>
 * <li> la frame </li>
 *</ul>
 *
 */
public class Regles extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	private JButton retour;
	private JFrame frame;
	private Font font = new Font("Courier", Font.BOLD, 15);
	
	/**
	 * Constructeur qui permet de mettre � jour le titre de la frame, la taille, les boutons et le document RTF qui s'affiche 
	 */
	public Regles(){
		super();
		this.frame = new JFrame();
		this.frame.setTitle("R�GLES DU JEST");
		this.frame.setSize(1000, 800);
		this.frame.setLocationRelativeTo(null);               
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	    this.setLayout(new BorderLayout());
	    
	    this.retour = new JButton("Menu principal");
	    this.retour.setActionCommand("retour");
	    this.retour.setFont(font);
	    
	    RTFEditorKit rtf = new RTFEditorKit();  
	    JTextPane regles = new JTextPane();  
	    regles.setEditorKit(rtf);  
	    FileInputStream fichier = null;
		try {
			fichier = new FileInputStream("file/Jest-Rules-v1.0.rtf");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}  
	    try {
			rtf.read(fichier, regles.getDocument(), 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	    JScrollPane sRegles = new JScrollPane(regles);
	    this.add(retour, BorderLayout.SOUTH);
	    this.add(sRegles);
	    this.frame.setContentPane(this);
	}
	
	public void setListener(Controleur controleur) {
		retour.addActionListener(controleur);
	}
	
	public JFrame getFrame() {
		return frame;
	}
}
