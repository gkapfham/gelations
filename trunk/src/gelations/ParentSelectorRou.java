/**
 * Alexander Conrad
 * gelations.ParentSelectorRou.java
 */
package gelations;

import java.util.ArrayList;

/**
 * This class performs the parent selection by implementing a 
 * roulette-wheel fitness proportionate selection algorithm.
 * 
 * @author conrada
 *
 */
public class ParentSelectorRou extends ParentSelector {
	
    public ParentSelectorRou(long seed) {
		
	super(seed);
		
    }
	
    public ParentSelectorRou() {
		
	super();
		
    }
	
    public ArrayList<Individual> chooseParents
	(Population population, double ratio) {
		
	//int numParents = (int)((ratio*2) * 
	// population.getPopSize());
	int numParents = (int)(ratio * population.getPopSize());
	
	ArrayList<Individual> individuals 
	    = population.getIndividuals();
	
	ArrayList<Individual> parents 
	    = new ArrayList<Individual>(numParents);
	
	double rouletteWheelPosition;
		
	double totalFitness 
	    = computeAccumulatedFitness(individuals);
	
	double currentFitness;
	int currentIndividual;
		
	// for each parent to be added
	for (int i=0; i<numParents; i++) {
			
	    // select a position on the "roulette wheel"
	    rouletteWheelPosition 
		= rng.nextDouble() * totalFitness;
			
	    currentIndividual = 0;
	    currentFitness = 0;
	    
	    // locate the individual at that position and add it 
	    //  to the parents
	    while (currentFitness < rouletteWheelPosition) {
				
		currentFitness = individuals.get
		    (currentIndividual).getAccumulatedFitness();
				
		if (currentFitness > rouletteWheelPosition) {
					
		    // if the current individual ends at a 
		    //  fitness geq the 
		    //	rouletteWheelPosition, check the previous
		    //  element as well, 
		    //	since it was originally skipped.
		    try {
			if (individuals.get(currentIndividual-1)
			    .getAccumulatedFitness() 
			    > rouletteWheelPosition) {
							
			    currentIndividual--;
			    currentFitness 
				= individuals.get
				(currentIndividual)
				.getAccumulatedFitness();
							
			}
		    } catch (IndexOutOfBoundsException ex) {}
					
		}
				
		// increment by 2 instead of 1 to get logarithmic
		//  time
		if (currentIndividual+2 < individuals.size()) {
		    currentIndividual += 2;
		} else {
		    currentIndividual++;
		}
				
	    }
			
	    if (currentIndividual >= 2)
		currentIndividual -= 2;
	    parents.add(individuals.get(currentIndividual));
			
	}
		
	return parents;
		
    }
	
    public double computeAccumulatedFitness
	(ArrayList<Individual> individuals) {
		
	double totalFitness = 0.0;
	double currentFitness;
		
	for (Individual individual: individuals) {
			
	    currentFitness = individual.getFitness();
	    totalFitness += currentFitness;
	    individual.setAccumulatedFitness(totalFitness);
			
	}
		
	return totalFitness;
		
    }
	
}
