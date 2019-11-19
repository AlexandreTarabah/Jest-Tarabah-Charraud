package jest_tarabah_charraud_2019_2020_class_diagram;

public enum Color {

	spade("spade"),
	club("club"),
	diamond("diamond"),
	heart("heart");
	orange("OrangeIsTheNewBlack") ;
	blue("ta mere la tchoin de cité")
	
	private String strColor ="";
	
	Color(String strColor){
		this.strColor=strColor;
	}
	
	public String toString() {
		return strColor;
	}
}
