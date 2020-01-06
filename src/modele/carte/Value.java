package modele.carte;

public enum Value {
	
	un("un",1),
	deux("deux",2),
	trois("trois",3),
	quatre("quatre",4),
	six("six",6);
	
	
	

	private String str =" ";
	private int cardValue;
	
	//constructeur 
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
