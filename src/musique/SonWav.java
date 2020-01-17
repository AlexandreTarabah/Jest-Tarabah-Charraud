package musique ; 
import java.net.*;
import java.applet.AudioClip;


public class SonWav extends java.applet.Applet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public boolean play=true;
	AudioClip AD;



	/**************************************************************************************************
	 * joue un son fichierSon 
	 */
	public SonWav(final String fichierSon) {

		try{
			new Thread(new Runnable() {

				public void run() {


					URL adrFichierSon = getClass().getResource(fichierSon);

					AD = newAudioClip(adrFichierSon);
					AD.play(); 


				}

			}).start();



			Thread.sleep(5000); //pause pour lire entierement le wav 5 secondes
		}

		catch(Exception ex) {   
			System.out.println("Impossible de lire le fichier son !");
		}
	}



}