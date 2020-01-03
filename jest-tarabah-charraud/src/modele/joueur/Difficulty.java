package modele.joueur;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Map.Entry;

public interface Difficulty {
	public void stealCard(Scanner input);
	public void upsideDown(Player p, Scanner input);

}
