package jest_tarabah_charraud_2019_2020;

public enum Color {

	spade("spade"),
	club("club"),
	diamond("diamond"),
	heart("heart");
	
	
	private String strColor ="";
	
	Color(String strColor){
		this.strColor=strColor;
	}
	
	public String toString() {
		return strColor;
	}
}