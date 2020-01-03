package jest_tarabah_charraud_2019_2020;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class JEST_INTERFACE {

	private JFrame frame;
	private JTextField textField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JEST_INTERFACE window = new JEST_INTERFACE();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public JEST_INTERFACE() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	private void initialize() {
		frame = new JFrame();
<<<<<<< HEAD
		frame.setBounds(100, 100, 1228, 698);
=======
		frame.setBounds(100, 100, 848, 553);
>>>>>>> branch 'master' of https://github.com/AlexandreTarabah/Jest-Tarabah-Charraud
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton button1 = new JButton("1/up");
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		button1.setBounds(12, 402, 137, 51);
		frame.getContentPane().add(button1);
		
		JButton button2 = new JButton("2/down");
		button2.setBounds(175, 402, 137, 51);
		frame.getContentPane().add(button2);
		
		textField = new JTextField();
		textField.setBounds(342, 402, 137, 51);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("     carte 1");
		lblNewLabel.setBounds(273, 146, 92, 147);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblCarte = new JLabel("     carte 2");
		lblCarte.setBounds(475, 146, 92, 147);
		frame.getContentPane().add(lblCarte);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(541, 402, 277, 91);
		frame.getContentPane().add(textArea);
		
		JLabel lblNewLabel_1 = new JLabel("Affichage du d\u00E9roulement de la partie");
		lblNewLabel_1.setBounds(541, 373, 236, 16);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblZoneDeSaisie = new JLabel("zone de saisie");
		lblZoneDeSaisie.setBounds(342, 373, 128, 16);
		frame.getContentPane().add(lblZoneDeSaisie);
		
		
		
		 String[] numberplayers = {"0","1","2","3","4"};
		 String[] var = {"1","2"};
		    JOptionPane jop = new JOptionPane(), 
		    			jop2 = new JOptionPane(),
		    			jop3 = new JOptionPane(),
		    			jop4 = new JOptionPane(),
		    			jop5 = new JOptionPane(),
		    			jop6 = new JOptionPane();
		    
		    
		    String choice = (String) jop.showInputDialog(null,  "Veuillez Choisir le nombre de joueur","ChoixNbJoueurs",JOptionPane.QUESTION_MESSAGE,
		      null,numberplayers,numberplayers[0]);
		    Game.nbRealPlayers=Integer.parseInt(choice);
		    jop2.showMessageDialog(null, "Votre choix est " + choice ,  "resultat", JOptionPane.INFORMATION_MESSAGE);
		   
		    
		    
		    
		    
		    choice = (String) jop3.showInputDialog(null,"Veuillez choisir un nombre de bot","choix nombre de bot",
		    		JOptionPane.QUESTION_MESSAGE,null,numberplayers,numberplayers[0]);
		    		
		    int result = Integer.parseInt(choice)+ Game.nbRealPlayers;
		    while(result < 3 || result > 4 )
		    {
		    	jop4 = new JOptionPane();
		    	jop4.showMessageDialog(null, "Veuillez Completer pour avoir 3 ou 4 joueurs", "Erreur", JOptionPane.INFORMATION_MESSAGE);
		    	choice = (String) jop.showInputDialog(null,"Veuillez Choisir le nombre de joueur","ChoixNbJoueurs",JOptionPane.QUESTION_MESSAGE,
		  		      null, numberplayers,numberplayers[0]);
		
		    	result = Integer.parseInt(choice)+ Game.nbRealPlayers;
		    }
		    
		    Game.nbBots=Integer.parseInt(choice);
		
		    
		    
		
		    choice =(String) jop5.showInputDialog(null,"quelle variante voulez vous ?","choix de la Variante",JOptionPane.QUESTION_MESSAGE,null,var,var[0]);
		    result = Integer.parseInt(choice);
			
		    
		    
		    
		    
		    
		    choice =(String) jop6.showInputDialog(null,"difficulté de Bot voulez vous ?","choix difficulté Bot",JOptionPane.QUESTION_MESSAGE,null,var,var[0]);
		    result = Integer.parseInt(choice);
		    
		    
		    
	}
<<<<<<< HEAD
	
}
=======
}
>>>>>>> branch 'master' of https://github.com/AlexandreTarabah/Jest-Tarabah-Charraud
