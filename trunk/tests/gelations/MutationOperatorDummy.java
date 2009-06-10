/**
 * 
 */
package gelations;

/**
 * Dummy class to aid in the testing of MutationOperator.
 * 
 * @author conrada
 *
 */
public class MutationOperatorDummy extends MutationOperator {
	
	public MutationOperatorDummy(long seed) {
		
		super(seed);
		
	}
	
	/* (non-Javadoc)
	 * @see gelations.MutationOperator#mutateIndividual(gelations.Individual)
	 */
	@Override
	public Individual mutateIndividual(Individual individual) {
		
		Individual indi = new Individual();
		indi.setFitness(individual.getFitness()+0.001);
		return indi;
		
	}

}
