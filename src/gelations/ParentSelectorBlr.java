/**
 * Alexander Conrad
 * gelations.ParentSelectorBlr
 */
package gelations;

import java.util.ArrayList;

/**
 * @author conrada
 *
 */
public class ParentSelectorBlr extends ParentSelector {

    /**
     * Ratio of individuals to be included after truncation
     */
    private double sp;
	
    public ParentSelectorBlr(long seed) {
		
	super(seed);
	sp = 2;
		
    }
	
    public ParentSelectorBlr(long seed, double _sp) {
		
	super(seed);
	sp = _sp;
		
    }
	
    public ParentSelectorBlr(double _sp) {
		
	super();
	sp = _sp;
		
    }
	
    public ParentSelectorBlr() {
		
	super();
	sp = 2;
		
    }
	
    public ArrayList<Individual> chooseParents
	(Population population, double ratio) {
		
	//int numParents = (int)((ratio*2) 
	// * population.getPopSize());
	int numParents = (int)(ratio * population.getPopSize());
		
	ArrayList<Individual> parents 
	    = new ArrayList<Individual>(numParents);
	
	ArrayList<Individual> individuals 
	    = sortIndividuals(population.getIndividuals());
	
	// transform the fitnesses:
	//double bestFitness = individuals.get(0).getFitness();
	//double totalFitnessPreTrans 
	//	= computeAccumulatedFitness(individuals);
	for (int i=0; i<individuals.size(); i++) {
		
		// baker's linear ranking algorithm
		individuals.get(i).setFitness(2-sp+2*(sp-1)*(i/individuals.size()-1));
		
	}
	
	// after transformation, proceed with regular roulette
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
