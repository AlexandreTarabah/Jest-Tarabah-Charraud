package modele.carte;
/**
 * Cette énumération correspond aux valeurs possibles des cartes : 
 * A chaque valeur, on associe un string (sa valeur en chaine de caractère) et sa valeur en int 
 * 
 *
 */
public enum Value {
	
	un("un",1),
	deux("deux",2),
	trois("trois",3),
	quatre("quatre",4),
	six("six",6);
	
	
	

	private String str =" ";
	private int cardValue;
	
	/**
	 * constructeur qui associe la bonne valeur et le string correspondant
	 * @param str
	 * @param value
	 */
	Value(String str, int value){
		this.str=str;
		this.cardValue=value;
	}
	
	public String toString() {
		return str;
	}
	
	public int getCardValue() {
		return cardValue;
	}
}
