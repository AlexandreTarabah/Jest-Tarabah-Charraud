package jest_tarabah_charraud_2019_2020;

import java.util.ArrayList;

import java.util.List;

public class Jest {
    private int nbCardJest;

    public List<Card> jestCards = new ArrayList<Card> ();

    public void acceptVisitor(Trophy trophy) 
    {
    	trophy.visitJest(this); 
    }

    public void calculateScore() {
    }


    public static Card AddJest(String victime, String stolenCard) {
    	Card card = new Card(null, null);
    	card = Player.listOffer.get(victime).get(stolenCard);
    	Player.listOffer.get(victime).remove(stolenCard);
    	return (Card) card;
    	
    }
}
//bonjour madame mons