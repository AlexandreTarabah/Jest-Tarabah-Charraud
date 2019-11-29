package jest_tarabah_charraud_2019_2020;

import java.util.Iterator;

public class TrophyBestJest extends Trophy {
    public TrophyBestJest(Color color) {

    	super(color);
	}


    public void visitJest(Jest jest) {
    	
    	int bestJest = 0 ;

    	for(int i=0;i<jest.jestCards.size(); i++) {
			 bestJest =+ jest.jestCards.get(i).value.ordinal();
    	}
			if(super.bestJestCandidate<bestJest) {
				
		super.bestJestCandidate = bestJest;
			}
    	
    	
    	
    			
		}
    
    }


