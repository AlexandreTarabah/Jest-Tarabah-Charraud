package musique ; 
import java.net.*;
import java.applet.AudioClip;

/**
 * Cette classe joue un fichier wav donnant au jeu une musique de fond.
 * SonWav se caractérise par  :
 * <ul>
 * <li> AD, un AudioClip </li>
 * <li> fichierSon, le classpath du fichier wav </li>
 * </ul>
 */
public class SonWav extends java.applet.Applet implements Runnable {

	private static final long serialVersionUID = 1L;
	AudioClip AD;
	String fichierSon ;

	/**
	 * Constructeur de SonWav
	 * @param fichierSon
	 */
	public SonWav(final String fichierSon) {


		this.fichierSon = fichierSon ; 

	}

	/**
	 * Joue le fichier wav placé dans le package musique
	 */
	public void run() {


		AD = newAudioClip(getClass().getResource(fichierSon));
		AD.play(); 


	}

}