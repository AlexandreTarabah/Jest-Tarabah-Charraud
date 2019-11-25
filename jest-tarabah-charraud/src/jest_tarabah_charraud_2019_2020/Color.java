package jest_tarabah_charraud_2019_2020;

public enum Color {
	
	heart("coeur"),
	diamond("carreau"),
	club("trèfle"),
	spade("pique");
	
	private String strColor =" ";
	
	Color(String strColor){
		this.strColor=strColor;
	}
	
	public String toString() {
		return strColor;
	}
}
