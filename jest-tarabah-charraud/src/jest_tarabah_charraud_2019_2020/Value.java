package jest_tarabah_charraud_2019_2020;

public enum Value {
	un("un",1),
	deux("deux",2),
	trois("trois",3),
	quatre("quatre",4);

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
