package modele.joueur;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

import modele.game.Game;

import java.util.Map.Entry;

public interface Difficulty {
	public void upsideDown(int choice,Game g);
	public void stealCard(String choiceVictime,String choiceCardVictime, Game g);

}
