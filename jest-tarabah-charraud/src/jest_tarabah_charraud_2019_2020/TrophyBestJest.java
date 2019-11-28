package jest_tarabah_charraud_2019_2020;

import java.util.Iterator;

public class TrophyBestJest extends Trophy {
    public TrophyBestJest(Color color) {

    	super(color);
	}


    public void visitJest(Jest jest) {
    	
    	for(int i=0;i<jest.jestCards.size(); i++) {
			int bestJest =+ jest.jestCards.get(i).value.ordinal();
			System.out.println("Votre jest à un total de "+ bestJest + "points");
			}
    			
		}
    
    }


