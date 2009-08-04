/**
 * Alexander Conrad
 * gelations.ParentSelectorTou4.java
 */
package gelations;

import java.util.ArrayList;

/**
 * This class performs the parent selection by implementing a 
 * tournament selection algorithm.
 * 
 * @author conrada
 *
 */
public class ParentSelectorTou4 extends ParentSelector {
	
    /**
     * Number of individuals to compete in each tournament.
     */
    private int touSize;
	
    public ParentSelectorTou4(long seed) {
		
	super(seed);
	touSize = 4;
		
    }
	
    public ParentSelectorTou4(long seed, int _touSize) {
		
	super(seed);
	touSize = _touSize;
		
    }
	
    public ParentSelectorTou4() {
		
	super();
	touSize = 4;
		
    }
	
	
    public ArrayList<Individual> chooseParents
	(Population population, double ratio) {
		
	//int numParents = (int)((ratio*2) 
	// * population.getPopSize());
	int numParents = (int)(ratio * population.getPopSize());
		
	ArrayList<Individual> individuals 
	    = population.getIndividuals();
	
	ArrayList<Individual> tournament 
	    = new ArrayList<Individual>(touSize);
	
	ArrayList<Individual> parents 
	    = new ArrayList<Individual>(numParents);
	
	Individual mostFit, tempIndi;
	double maxFit;
		
	//for numparents:
	for (int i=0; i<numParents; i++) {
			
	    //randomly choose touSize individuals from population
	    for (int j=0; j<touSize; j++) {
				
		tournament.add(individuals.get
			       (rng.nextInt(individuals.size())));
				
	    }
			
	    //get most fit individual from tournament
	    mostFit = tournament.get(0);
	    maxFit = mostFit.getFitness();			
	    for (int j=1; j<touSize; j++) {
				
		tempIndi = tournament.get(j);
		if (tempIndi.getFitness() > maxFit) {
					
		    maxFit = tempIndi.getFitness();
		    mostFit = tempIndi;
					
		}
				
	    }
			
	    //add most fit individual to parents
	    parents.add(mostFit);
			
	}
		
	return parents;
		
    }
	
}