package Vue;

import java.awt.BorderLayout;
import java.awt.Font;
import java.io.*;

import javax.swing.*;
import javax.swing.text.rtf.RTFEditorKit;

import Controleur.Controleur;

public class Regles extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JButton back;
	private JFrame frame;
	private Font font = new Font("Courier", Font.BOLD, 15);
	
	public Regles(){
		super();
		this.frame = new JFrame();
		this.frame.setTitle("Regles du jeu");
		this.frame.setSize(1000, 800);
		this.frame.setLocationRelativeTo(null);               
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	    this.setLayout(new BorderLayout());
	    
	    this.back = new JButton("Retour au menu principal");
	    this.back.setActionCommand("retour");
	    this.back.setFont(font);
	    
	    RTFEditorKit rtf = new RTFEditorKit();  
	    JTextPane regles = new JTextPane();  
	    regles.setEditorKit(rtf);  
	    FileInputStream fichier = null;
		try {
			fichier = new FileInputStream("JEST_Rules");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}  
	    try {
			rtf.read(fichier, regles.getDocument(), 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	    JScrollPane sRegles = new JScrollPane(regles);
	    this.add(back, BorderLayout.SOUTH);
	    this.add(sRegles);
	    this.frame.setContentPane(this);
	}
	
	public void setListener(Controleur controleur) {
		back.addActionListener(controleur);
	}
	
	public JFrame getFrame() {
		return frame;
	}
}
