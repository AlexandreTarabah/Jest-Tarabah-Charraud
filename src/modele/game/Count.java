package modele.game;

import java.util.HashMap;
import java.util.Iterator;

import modele.tas.Jest;

/**
 * L'interface Count utilise �galement le design pattern visiteur et constitue une deuxi�me classe de visiteurs pour 
 * la classe Jest. Elle est impl�ment�e par deux classes filles diff�rentes :
 * <ul>
 * <li> CountClassique repr�sentant le comptage des points dans la version "classique" du Jeu </li>
 * <li> CountInversion repr�sentant le comptage des points dans la variant "Red power !" du Jeu </li>
 * </ul>
 * {@link CountClassique} {@link CountInversion}
 */
public interface Count {

	/**
	 * M�thode visitJest d�clar�e
	 * @param jest
	 */
	public int visitJest(Jest jest) ;
	
}
