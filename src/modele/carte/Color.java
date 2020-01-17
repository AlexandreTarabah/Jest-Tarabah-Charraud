package modele.carte;
/**
 * Cette �num�ration correspond aux Color possibles pour les cartes : 
 * A chaque valeur, on associe un string strColor qui correspond � la couleur et la valeur de la couleur (selon les r�gles du jeu)
 * 
 */
public enum Color {
	
	joker("joker",0),
	heart("coeur",1),
	diamond("carreau",2),
	club("tr�fle",3),
	spade("pique",4);
	
	private String strColor =" ";
	private int colorValue;
	
	/**
	 * Le constructeur qui associe les bonnes couleurs et le bon nom de carte 
	 * @param strColor
	 * @param colorValue
	 */
	Color(String strColor, int colorValue){
		this.strColor=strColor;
		this.colorValue = colorValue;
	}
	
	public String toString() {
		return strColor;
	}
	
	public int getColorValue() {
		return colorValue;
		}
}
