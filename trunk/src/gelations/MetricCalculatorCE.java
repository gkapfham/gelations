/**
 * Alexander Conrad
 * gelations.MetricCalculatorCE.java
 */
package gelations;

import java.util.HashMap;
import java.util.ArrayList;

/**
 * Instance class for computing the coverage effectiveness 
 * metric for given Individuals.
 * 
 * @author conrada
 *
 */
public class MetricCalculatorCE extends MetricCalculator {
	
    public MetricCalculatorCE() {
	super();
    }
	
    public double computeFitness(Individual individual) {
		
	// first, check cache to see if element is already in 
	//  cache
	Double fitness = super.lookup(individual);
		
	if (fitness != null) {
			
	    //debug
	    //System.out.println("was in cache:"+fitness);
			
	    individual.setFitness(fitness);
	    return fitness;
			
			
	} else {
			
	    // if the fitness for this permutation has not 
	    //  already been computed:
			
	    // use a HashMap to store the coverage status of 
	    //  individual requirements:
	    //	if a requirement's unique int identifier is 
	    //  present, then it has 
	    //	already been covered by another test case
	    HashMap<Integer,Boolean> reqsCovered 
		= new HashMap<Integer,Boolean>
		((int)(individual.size()*1.25),(float)1.0);
	    ArrayList<Chromosome> chromosomes 
		= individual.getChromosomes();
			
	    int[] reqs;				
	    // array of requirements to be reused for each 
	    //  chromosome
	    
	    int nextReqs;			
	    // number of reqs covereby by this chromosome
	    
	    int totReqs = 0;		
	    // counter for total requirements covered
	    
	    double curArea;			
	    // area under current chromosome's part of graph 
	    
	    double curTime;			
	    // runtime for current chromosome 
	    
	    double totArea = 0;		
	    // accumulator for total area under the curve
	    
	    double totTime = 0;		
	    // counter for total execution time
			
	    totTime += chromosomes.get(0).getTime();
			
	    for (int chrom=1; chrom<chromosomes.size(); chrom++) {
				
		// get requirements for previous chromosome
		reqs = chromosomes.get(chrom-1).getRequirements();
		nextReqs = 0;
		curArea = 0;
		// get time for this chromosome
		curTime = chromosomes.get(chrom).getTime();
		totTime += curTime;
				
		for (int req=0; req<reqs.length; req++) {
					
		    if (!reqsCovered.containsKey(reqs[req])) {
			nextReqs++;
			reqsCovered.put(reqs[req],true);
		    }
					
		}
								
		totReqs += nextReqs;

		//debug
		//System.out.println("next box area: reqs:"
		// +totReqs+", time:"+curTime);
				
		curArea = totReqs * curTime;
		totArea += curArea;
								
	    }
			
	    // add the requirements covered by the last 
	    // chromosome to the total counter
	    reqs = chromosomes.get
		(chromosomes.size()-1).getRequirements();
	    
	    for (int req=0; req<reqs.length; req++) {
				
		if (!reqsCovered.containsKey(reqs[req])) {
		    totReqs++;
		    reqsCovered.put(reqs[req],true);
		}
				
	    }
			
	    fitness = totArea / (totTime*totReqs);
	    individual.setFitness(fitness);
	    //individual.setInitFitness(fitness);
	    cache.addFitness
		(individual.getStringRepresentation(), fitness);
	    return fitness;
	    
	}
	
    }
    
}
