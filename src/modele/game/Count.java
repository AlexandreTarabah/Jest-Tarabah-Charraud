package modele.game;

import java.util.HashMap;
import java.util.Iterator;

import modele.tas.Jest;

/**
 * L'interface Count utilise également le design pattern visiteur et constitue une deuxième classe de visiteurs pour 
 * la classe Jest. Elle est implémentée par deux classes filles différentes :
 * <ul>
 * <li> CountClassique représentant le comptage des points dans la version "classique" du Jeu </li>
 * <li> CountInversion représentant le comptage des points dans la variant "Red power !" du Jeu </li>
 * </ul>
 * {@link CountClassique} {@link CountInversion}
 */
public interface Count {

	/**
	 * Méthode visitJest déclarée
	 * @param jest
	 */
	public int visitJest(Jest jest) ;
	
}
