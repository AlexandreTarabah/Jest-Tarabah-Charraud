package jest_tarabah_charraud_2019_2020;


public class Trophy implements Visitor 
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

	@Override
	public void visitJest(Jest jest) {
		// TODO Auto-generated method stub
		
	}

<<<<<<< HEAD
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



=======
	
	
>>>>>>> branch 'master' of https://github.com/AlexandreTarabah/Jest-Tarabah-Charraud
}
