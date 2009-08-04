/**
 * Alexander Conrad
 * gelations.Population.java
 */
package gelations;

import java.util.ArrayList;
import java.util.Random;

/**
 * Instance class that represents a single population of 
 * Individuals for evolution by the genetic algorithm.
 * 
 * @author conrada
 *
 */
public class Population {

    /**
     * All of the Individuals in this Population.
     */
    private ArrayList<Individual> individuals;
	
    /**
     * Stores the maximum size of the population.
     */
    private int maxPopSize;
	
    /**
     * Create a new Population composed of a given list of 
     * Individuals.
     * 
     * @param _individuals - Individuals contained within this 
     *  Population
     */
    public Population(ArrayList<Individual> _individuals) {
		
	individuals = new ArrayList<Individual>(_individuals);
	maxPopSize = individuals.size();
		
    }
		
    /**
     * Create an new empty Population object.
     *
     *@param _popSize - maximum size of this Population
     */
    public Population(int _maxPopSize) {
		
	individuals = new ArrayList<Individual>();
	maxPopSize = _maxPopSize; 
		
    }
	
    /**
     * 
     * @return all Individuals in the Population in list format
     */
    public ArrayList<Individual> getIndividuals() {
	return new ArrayList<Individual>(individuals);
    }
	
    /**
     * 
     * @param individual - Individual to be added to the 
     *  Population
     */
    public void addIndividual(Individual individual) {
	individuals.add(individual);
    }
	
    /**
     * Returns the maximum fitness out of all of the individuals 
     * in a population, assuming
     * that the fitness values have already been calculated.
     * 
     * @return the maximum fitness value of all Individuals in 
     *  the Population
     */
    public double getMaxFitness() {
		
	return getBestIndividual().getFitness();
		
    }
	
    /**
     * Returns the minimum fitness out of all of the individuals 
     * in a population, assuming
     * that the fitness values have already been calculated.
     * 
     * @return the minimum fitness value of all Individuals in 
     *  the Population
     */
    public double getMinFitness() {
		
	return getWorstIndividual().getFitness();
		
    }
	
    /**
     * 
     * @return current size of the Population
     */
    public int getPopSize() {
	return individuals.size();
    }
	
    /**
     * 
     * @return maximum size of the Population
     */
    public int getMaxPopSize() {
	return maxPopSize;
    }
	
    /**
     * Remove and return an Individual at a given index from the 
     * Population.
     * 
     * @param indi - index of Individual to be removed
     * @return removed Individual
     */
    public Individual removeIndividual(int indi) {
	return individuals.remove(indi);
    }
	
    /**
     * Find and return the most-fit Individual within this 
     * Population. 
     * 
     * @return the fittest Individual
     */
    public Individual getBestIndividual() {
		
	int bestIndividual = 0;
	double bestFitness = individuals.get(0).getFitness();
	double nextFitness;
		
	for (int i=1; i<individuals.size(); i++) {
			
	    nextFitness = individuals.get(i).getFitness();
	    if (nextFitness > bestFitness) {
				
		bestFitness = nextFitness;
		bestIndividual = i;
				
	    }
			
	}
		
	return individuals.get(bestIndividual);
		
    }
	
    /**
     * Find and return the least-fit Individual within this 
     * Population. 
     * 
     * @return the least-fit Individual
     */
    public Individual getWorstIndividual() {

	int worstIndividual = 0;
	double worstFitness = individuals.get(0).getFitness();
	double nextFitness;
		
	for (int i=1; i<individuals.size(); i++) {
			
	    nextFitness = individuals.get(i).getFitness();
	    if (nextFitness < worstFitness) {
				
		worstFitness = nextFitness;
		worstIndividual = i;
				
	    }
			
	}
		
	return individuals.get(worstIndividual);
	
    }
	
    /**
     * Creates and returns a copy of a particular Population, 
     * such that changes to the 
     * original Population do not affect the copy, and vice 
     * versa. Each Individual within the Population is copied.
     * 
     * @param population - Population to be copied
     * @return a copy of the original Population
     */
    public static Population copy(Population population) {
		
	ArrayList<Individual> newIndividuals 
	    = new ArrayList<Individual>();
	
	for (Individual individual: population.getIndividuals()) {
			
	    newIndividuals.add(Individual.copy(individual));
			
	}
		
	return new Population(newIndividuals);
		
    }
	
    /**
     * Reduces a population down to a specific number of elements
     * by randomly selecting 
     * individuals to be included in a new Population. Note: if 
     * size > population.size(), 
     * then a copy of the unmodified population will be returned.
     * Individuals will not be
     * chosen for the reduced population more than once. Warning:
     * the population passed in 
     * as an argument may be destroyed!
     * 
     * @param population - original population to be reduced
     * @param size - number of elements to be contained by the 
     *  new population
     * @param rng - the random number generator to be used
     * @return the reduced population
     */
    public static Population randomlyReducePopulation
	(Population population, int size, Random rng) {
		
	int popSize = population.getPopSize();
		
	if (size>=popSize) {
	    return Population.copy(population);
	}
		
	Population reducedPop = new Population(size);
	
	ArrayList<Individual> individuals = 
	    new ArrayList<Individual>
	    (population.getIndividuals());
	
	int nextIndi;
		
	for (int i=0; i< size; i++) {
			
	    nextIndi = rng.nextInt(individuals.size());
	    reducedPop.addIndividual
		(individuals.remove(nextIndi));
						
	}
		
	return reducedPop;
		
    }
	
    /**
     * Combines two seperate Populations into a single 
     * Population. The Individuals from the 
     * two Populations are combined in random order into the new 
     * Population. Warning: the 
     * population passed in as an argument may be destroyed!
     * 
     * @param pop1 - first population to be merged
     * @param pop2 - second population to be merged
     * @param rng - the random number generator to be used
     * @return the combined populations
     */
    public static Population randomlyCombinePopulations
	(Population pop1, Population pop2, Random rng) {
		
	int pop1Size = pop1.getPopSize();
	int pop2Size = pop2.getPopSize();
		
	int newSize = pop1Size + pop2Size;
	Population newPop = new Population(newSize);
	
	ArrayList<Individual> indi1 
	    = new ArrayList<Individual>(pop1.getIndividuals());
	
	ArrayList<Individual> indi2 
	    = new ArrayList<Individual>(pop2.getIndividuals());
	
	int pop, nextIndi, pop1Counter, pop2Counter;
	
	pop1Counter = 0;
	pop2Counter = 0;
	
	for (int i=0; i<newSize; i++) {
	    
	    // pick an initial population
	    do {
		
		pop = rng.nextInt(2) + 1;
		
	    } while (pop==1 ? pop1Counter>=pop1Size 
		     : pop2Counter >= pop2Size);
	    
	    // pick an element that has not yet been used out of 
	    //  that initial population
	    //	to be added to the reduced population
	    if (pop==1) {
		
		nextIndi = rng.nextInt(indi1.size());
		newPop.addIndividual(indi1.remove(nextIndi));
		pop1Counter++;
		
	    } else {
		
		nextIndi = rng.nextInt(indi2.size());
		newPop.addIndividual(indi2.remove(nextIndi));
		pop2Counter++;
		
	    }
	    
	    
	}
	
	return newPop;		
	
    }
    
}
