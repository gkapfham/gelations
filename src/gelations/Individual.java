/**
 * Alexander Conrad
 * gelations.Individual.java
 */
package gelations;

import java.util.ArrayList;

/**
 * Instance class whose objects represent a single ordering of 
 * all of the test cases, in 
 * the form of a genetic individual made up of test 
 * case-representing Chromosomes. The 
 * fitness value stored within Individual must be computed by an 
 * external module. Individuals
 * never have repeating Chromosomes; each Chromosome occurs 
 * exactly once within each Individual.
 * 
 * @author conrada
 *
 */
public class Individual implements Comparable<Individual> {

    /**
     * Stores the specific ordering of Chromosomes associated 
     * with this Individual.
     */
    private ArrayList<Chromosome> chromosomes;
	
    /**
     * Stores the (externally-computed) fitness value of this 
     * Individual. The fitness value
     * must fall between 0 and 1, exclusive.
     */
    private double fitness;
	
    /**
     * Stores the original, untransformed fitness value for the 
     * individual.
     */
    private double initFitness;
	
    /**
     * Stores the externally-computed accumulated fitness of 
     * this individual. This field is 
     * used only with roulette wheel-type selection methods.
     */
    private double accumulatedFitness;
		
    /**
     * Creates a new Individual composed of the ordering of 
     * Chromosomes in _chromosomes and
     * with a pre-computed fitness value _fitness.
     * 
     * @param _chromosomes
     * @param _fitness
     */
    public Individual(ArrayList<Chromosome> _chromosomes, 
		      double _fitness) {
		
	chromosomes = _chromosomes;
	fitness = _fitness;
	initFitness = fitness;
		
    }
	
    /**
     * Creates a new Individual composed of the ordering of 
     * Chromosomes in _chromosomes and
     * a uncomputed fitness value; fitness is set to -1 to 
     * signify that it has not yet been
     * computed.
     * 
     * @param _chromosomes
     */
    public Individual(ArrayList<Chromosome> _chromosomes) {
		
	chromosomes = _chromosomes;
	fitness = -1;
	initFitness = fitness;
		
    }
	
    /**
     * Creates a new empty Individual.
     *
     */
    public Individual() {
		
	chromosomes = new ArrayList<Chromosome>();
	fitness = -1;
	initFitness = fitness;
		
    }
	
    /**
     * Creates a new empty Individual with an initial genome 
     * capacity.
     * 
     * @param size - initial size of chromosome ArrayList
     */
    public Individual(int size) {
		
	chromosomes = new ArrayList<Chromosome>(size);
	fitness = -1;
	initFitness = fitness;
		
    }
	
    /**
     * 
     * @return a copy of the ArrayList<Chromosome> within this 
     * individual
     */
    public ArrayList<Chromosome> getChromosomes() {
	return new ArrayList<Chromosome>(chromosomes);
    }
	
    /**
     * 
     * @param _fitness - the fitness value to be associated with 
     * this individual
     */
    public void setFitness(double _fitness) {
	fitness = _fitness;
    }
	
    /**
     * Note: this method should only be called by the fitness 
     * calculator;
     * fitness transformation operators should never call this 
     * method.
     * 
     * @param _fitness - the fitness value to be associated with 
     *  this individual
     */
    public void setInitFitness(double _fitness) {
	initFitness = _fitness;
    }
	
    /**
     * 
     * @return the fitness value assigned to the ordering in this
     *  individual
     */
    public double getFitness() {
	return fitness;
    }
	
    /**
     * 
     * @return the untransformed fitness value assigned to this 
     *  individual
     */
    public double getInitFitness() {
	return initFitness;
    }
	
    /**
     * Adds a Chromosome to the end of the list of Chromosomes 
     * currently held by this Individual. 
     * 
     * @param chromosome - the Chromosome to be added to the 
     *  individual's Chromosome list
     */
    public void addChromosome(Chromosome chromosome) {
	chromosomes.add(chromosome);
    }
	
    /**
     * Remove and return a Chromosome at a given index.
     * 
     * @param i - index of desired Chromosome
     * @return
     */
    public Chromosome removeChromosome(int i) {
	return chromosomes.remove(i);
    }
	
    /**
     * Return a Chromosome at a given index. 
     * 
     * @param i - index of desired Chromosome
     * @return
     */
    public Chromosome getChromosome(int i) {
	return chromosomes.get(i);
    }
	
    /**
     * Add a Chromosome at a particular index in this Individual.
     * 
     * @param index
     * @param chromosome
     */
    public void addChromosome(int index, Chromosome chromosome) {
	chromosomes.add(index,chromosome);
    }
	
    /**
     * Replace the Chromosome at a specific index without 
     * disturbing the indexing of the 
     * other Chromosomes.
     * 
     * @param index - slot in ArrayList to be replaced
     * @param chromosome - to replace whatever is currently in 
     *  that slot
     * @return the Chromosome that previously held the slot
     */
    public Chromosome replaceChromosome(int index, 
					Chromosome chromosome) {
	return chromosomes.set(index, chromosome);
    }
	
    /**
     * 
     * @return the number of chromosomes in this individual
     */
    public int size() {
	return chromosomes.size();
    }
	
    /**
     * Note: do not use this class in the memoizer! Arrays will 
     * not be matched, even
     * if their elements are identical!
     * 
     * @return an int[] representation of this individual for 
     *  use in the Memoizer
     */
    public int[] getIntArrayRepresentation() {
		
	int[] ids = new int[chromosomes.size()];
		
	for (int i=0; i<ids.length; i++) {
			
	    ids[i] = chromosomes.get(i).getId();
			
	}
		
	return ids;
		
    }
	
    /**
     * 
     * @return a String representation of this Individual for 
     *  use in the Memoizer and datafile
     */
    public String getStringRepresentation() {
		
	String ids = Integer.toString(chromosomes.get(0).getId());
		
	for (int i=1; i<chromosomes.size(); i++) {
			
	    ids = ids + "Q" + chromosomes.get(i).getId();
			
	}
		
	return ids;
		
    }
	
    public void setAccumulatedFitness(double fitness) {
	accumulatedFitness = fitness;
    }
	
    public double getAccumulatedFitness() {
	return accumulatedFitness;
    }
	
    /**
     * Creates and returns a defensive copy of individual, so 
     * that modification of one of the
     * two identical individuals does not affect the other. Both 
     * the ArrayList storing the 
     * Chromosomes as well as each individual Chromosome is 
     * defensively copied.
     * 
     * @return a defensive copy of individual
     */
    public static Individual copy(Individual individual) {
		
	ArrayList<Chromosome> newChromosomes 
	    = new ArrayList<Chromosome>();
	for (Chromosome chromosome: individual.getChromosomes()) {
			
	    newChromosomes.add(Chromosome.copy(chromosome));
			
	}
		
	return new Individual(newChromosomes, 
			      individual.getFitness());
		
    }
	
    /**
     * Compares two objects based upon their fitnesses.
     */
    public int compareTo(Individual individual) {
		
	double fitness2 = individual.getFitness();
		
	if (fitness < fitness2)
	    return -1;
	else if (fitness == fitness2)
	    return 0;
	else
	    return 1;
		
    }
	
}
