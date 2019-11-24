package jest_tarabah_charraud_2019_2020;

public enum Value {
	un("un"),
	deux("deux"),
	trois("trois"),
	quatre("quatre");

	private String str =" ";
	
	//constructeur 
	Value(String str){
		this.str=str;
	}
	public String toString() {
		return str;
	}
}
