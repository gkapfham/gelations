/**
 * Alexander Conrad
 * gelations.ParentSelectorTru60.java
 */
package gelations;

import java.util.ArrayList;

/**
 * @author conrada
 *
 */
public class ParentSelectorTru60 extends ParentSelector {

    /**
     * Ratio of individuals to be included after truncation
     */
    private double proportion;
	
    public ParentSelectorTru60(long seed) {
		
	super(seed);
	proportion = 0.6;
		
    }
	
    public ParentSelectorTru60(long seed, double _proportion) {
		
	super(seed);
	proportion = _proportion;
		
    }
	
    public ParentSelectorTru60(double _proportion) {
		
	super();
	proportion = _proportion;
		
    }
	
    public ParentSelectorTru60() {
		
	super();
	proportion = 0.6;
		
    }
	
    public ArrayList<Individual> chooseParents
	(Population population, double ratio) {
		
	//int numParents = (int)((ratio*2) 
	// * population.getPopSize());
	int numParents = (int)(ratio * population.getPopSize());
	int numTruncated;
		
	ArrayList<Individual> parents 
	    = new ArrayList<Individual>(numParents);
	
	ArrayList<Individual> individuals 
	    = sortIndividuals(population.getIndividuals());
		
	numTruncated = (int)(individuals.size() * proportion);
		
	for (int i=0; i<numParents; i++) {
			
	    parents.add(individuals.get(i%numTruncated));
			
	}
		
	return parents;
		
    }
	
	
    public ArrayList<Individual> sortIndividuals
	(ArrayList<Individual> individuals) {
		
	//debug
	//System.out.println(individuals.size());
		
	if (individuals.size() < 2) {
	    return individuals;
	}
		
	int pivotIndi = rng.nextInt(individuals.size());
	boolean allSame = true;
		
	double pivotFitness 
	    = individuals.get(pivotIndi).getFitness();
		
	ArrayList<Individual> parents 
	    = new ArrayList<Individual>();
	
	ArrayList<Individual> lesserIndividuals 
	    = new ArrayList<Individual>();
	
	ArrayList<Individual> greaterIndividuals 
	    = new ArrayList<Individual>();
		
	for (Individual individual: individuals) {
			
	    if (individual.getFitness() > pivotFitness) {
				
		greaterIndividuals.add(individual);
		allSame = false;
				
	    } else if (individual.getFitness() < pivotFitness) {
				
		lesserIndividuals.add(individual);
		allSame = false;
				
	    } else {
				
		lesserIndividuals.add(individual);
				
	    }
			
	}
		
	if (allSame) {
			
	    parents.addAll(lesserIndividuals);
			
	} else {
			
	    lesserIndividuals 
		= sortIndividuals(lesserIndividuals);
	    
	    greaterIndividuals 
		= sortIndividuals(greaterIndividuals);
	    
	    parents.addAll(greaterIndividuals);
	    parents.addAll(lesserIndividuals);
	    
	}
	
	return parents;
	
    }
    
}
