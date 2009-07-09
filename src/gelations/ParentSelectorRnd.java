/**
 * Alexander Conrad
 * gelations.ParentSelectorRnd.java
 */
package gelations;

import java.util.ArrayList;

/**
 * @author conrada
 *
 */
public class ParentSelectorRnd extends ParentSelector {

    public ParentSelectorRnd(long seed) {
		
	super(seed);
		
    }
	
    public ArrayList<Individual> chooseParents
	(Population population, double ratio) {
		
	int numParents 
	    = (int)(ratio * population.getIndividuals().size());
	int curParents = 0;
		
	ArrayList<Individual> individuals 
	    = population.getIndividuals();
	
	ArrayList<Individual> parents 
	    = new ArrayList<Individual>(numParents);
	
	Individual prospectiveParent;
		
	while (curParents < numParents) {
			
	    prospectiveParent 
		= individuals.get
		(rng.nextInt(individuals.size()));
			
	    if (prospectiveParent.getFitness() 
		> rng.nextDouble()) {
				
		parents.add(prospectiveParent);
		curParents++;
				
	    }
			
	}
		
	return parents;
		
    }

}
