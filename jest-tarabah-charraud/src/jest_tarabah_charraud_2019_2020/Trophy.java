package jest_tarabah_charraud_2019_2020;


public class Trophy 
{
	Value value ;
	Color color ;

	protected Card highCandidate = new Card(value, color);

	protected Card lowCandidate = new Card(value, color);

	public Trophy() 
	{
	}
	// TODO Auto-generated constructor stub

	public Trophy(Color color) 
	{
		this.color = color ;
	}

	public void visitJest(Jest jest) {
		// TODO Auto-generated method stub
		
	}


	public void visitJest(Jest jest, Color color) 
	{
		// TODO Auto-generated method stu
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

}
