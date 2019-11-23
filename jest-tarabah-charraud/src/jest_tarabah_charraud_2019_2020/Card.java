package jest_tarabah_charraud_2019_2020;



public class Card {

    private boolean ace;

    private boolean faceUp;

     private Color color ;
     
     private Value value;
     
     private Trophy trophy ; 

   
   public Card (Value value, Color color) {
	   this.color=color;
	   this.value=value;
    }
   
   
   public Color getColor() {
	   return color;
   }
   
   
   public void setColor(Color color) {
	   this.color=color;
	   
   }

   
   public Value getValue() {
	   return value;
   }
    
   
public void setValue(Value value) {
	this.value=value;
}

public Trophy getTrophy()
{
	return trophy ;
}



	public String tostring() {
		return value.toString() + color.toString();
 	}
;

}
