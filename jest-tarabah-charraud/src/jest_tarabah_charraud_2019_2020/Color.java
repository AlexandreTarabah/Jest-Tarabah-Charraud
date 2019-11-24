package jest_tarabah_charraud_2019_2020;

public enum Color {

	spade("pique"),
	club("trèfle"),
	diamond("carreau"),
	heart("coeur");
	
	
	private String strColor =" ";
	
	Color(String strColor){
		this.strColor=strColor;
	}
	
	public String toString() {
		return strColor;
	}
}
