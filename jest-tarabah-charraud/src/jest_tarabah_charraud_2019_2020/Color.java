package jest_tarabah_charraud_2019_2020;

public enum Color {
	
	heart("coeur",1),
	diamond("carreau",2),
	club("trèfle",3),
	spade("pique",4);
	
	private String strColor =" ";
	private int colorValue;
	
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
