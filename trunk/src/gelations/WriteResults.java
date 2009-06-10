/**
 * Alexander Conrad
 * gelations.WriteResults.java
 */
package gelations;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

//import java.io.FileNotFoundException;

/**
 * Static class for writing to the output datafiles.
 * 
 * @author conrada
 *
 */
public class WriteResults {
	
    public static final int RS_FACTOR = 100;
    private static Individual randomIndividual;
    private static long randomTime;
    
    
    /**
     * Writes the first line to the datafile, specifying names 
     * for each of the columns.
     * 
     * @param config - Configuration object containing necessary 
     *  filenames.
     */
    public static void writeHeader(Configuration config) {
		
	String filename = config.getOutputFileName();
	//try {
	(new File(filename)).delete();
	//} catch (FileNotFoundException ex) {}
		
	String header = "datatype\tdataset\tcrossover_operator\t"
	    +"mutation_operator\tselection_method\t"
	    +"fitness_transform\tmutation_rate\tchild_density\t"
	    +"pop_size\tmax_time\ttarget_fitness\tmax_stagnancy\t"
	    +"metric\trep\torder\tfitness\truntime\tgenerations\t"
	    +"init_order\tinit_fitness\tinit_reverse_order\t"
	    +"init_reverse_fitness\trandom_search_order\t"
	    +"random_search_fitness\trandom_search_runtime\n";
	BufferedWriter out;
		
	try {
			
	    out = new BufferedWriter(new FileWriter(filename));
	    out.write(header);
	    out.close();
			
	}
	catch (IOException ex) {
			
	    ex.printStackTrace();
			
	}
		
    }
	
    public static void writeHeaderStag(Configuration config) {
		
	String filename = config.getOutputFileName()+".stag";
	//try {
	(new File(filename)).delete();
	//} catch (FileNotFoundException ex) {}
		
	String header = "datatype\tdataset\tcrossover_operator\t"
	    +"mutation_operator\tselection_method\t"
	    +"fitness_transform\tmutation_rate\tchild_density\t"
	    +"pop_size\tmax_time\ttarget_fitness\tmax_stagnancy\t"
	    +"metric\trep\torder\tfitness\truntime\tgenerations\t"
	    +"current_stagnancy\tcurrent_fitness\t"
	    +"current_max_fitness\tcurrent_best_ordering\n";
	BufferedWriter out;
		
	try {
			
	    out = new BufferedWriter(new FileWriter(filename));
	    out.write(header);
	    out.close();
			
	}
	catch (IOException ex) {
			
	    ex.printStackTrace();
			
	}
		
    }
	
    /**
     * Writes a line to the given datafile, logging the results 
     * of the Prioritizer, its
     * runtime, and the configuration that provided these results.
     * 
     * @param config - object containing all of the config info 
     *  for this prioritization run 
     * @param bestIndividual - solution of the evolutionary 
     *  prioritizer
     * @param elapsedTime - execution time of the evolutionary 
     *  prioritizer
     * @param rep - repetition count for the execution of this 
     *  configuration
     * @param generations - the number of generations created to 
     *  arrive at this solution
     */
    public static void toFile
	(Configuration config, Individual bestIndividual, 
	 long elapsedTime, int rep, int generations) {
		
	MetricCalculator fitnessCalculator;
	Individual initialIndividual, reverseIndividual;
	double initialFitness, reverseFitness, randomFitness;
	ArrayList<Chromosome> initialChromosomes, 
	    reverseChromosomes;
		
	initialChromosomes = CreateInitialChromosomes
	    .makeChromosomes(config.getCaseTests());
	
	initialIndividual = new Individual(initialChromosomes);
		
	reverseChromosomes = new ArrayList<Chromosome>
	    (initialChromosomes.size());
	
	for (int i=0; i<initialChromosomes.size(); i++) {
	    reverseChromosomes.add(0,initialChromosomes.get(i));
	}
	
	reverseIndividual = new Individual(reverseChromosomes);
		
	fitnessCalculator = MetricSelector.getFitnessCalculator
	    (config.getMetric());
		
	initialFitness 
	    = fitnessCalculator.computeFitness(initialIndividual);
	
	reverseFitness 
	    = fitnessCalculator.computeFitness(reverseIndividual);
		
	// do the random search as a control
		
	randomSearch(config, initialChromosomes, rep, 
		     fitnessCalculator);
	
	randomFitness = fitnessCalculator.computeFitness
	    (randomIndividual);
		
	BufferedWriter out;
	String toFile = Configuration.DATATYPE
	    [config.getDatatype()]+"\t"+config.getConfig()
	    +"\t"+Configuration.CROSSOVER_OPS
	    [config.getCrossoverOperator()]+"\t"
	    +Configuration.MUTATION_OPS
	    [config.getMutationOperator()]+"\t"
	    +Configuration.SELECTION_OPS
	    [config.getSelectionOperator()]+"\t"
	    +Configuration.TRANSFORM
	    [config.getFitnessTransform()]+"\t"
	    +config.getMutationRate()+"\t"
	    +config.getChildRepresentation()+"\t"
	    +config.getPopSize()+"\t"+config.getMaxTime()+"\t"
	    +config.getTargetFitness()+"\t"
	    +config.getMaxStagnancy()+"\t"
	    +Configuration.METRIC[config.getMetric()]+"\t"
	    +rep+"\t"
	    +bestIndividual.getStringRepresentation()+"\t"
	    +bestIndividual.getFitness()+"\t"
	    +elapsedTime+"\t"+generations+"\t"
	    +initialIndividual.getStringRepresentation()+"\t"
	    +initialFitness+"\t"
	    +reverseIndividual.getStringRepresentation()+"\t"
	    +reverseFitness+"\t"
	    +randomIndividual.getStringRepresentation()+"\t"
	    +randomFitness+"\t"+randomTime+"\n";
		
	try {
			
	    out = new BufferedWriter
		(new FileWriter(config.getOutputFileName(),true));
	    out.write(toFile);
	    out.close();
			
	}
	catch (IOException ex) {
			
	    ex.printStackTrace();
			
	}
		
		
    }
	
    public static void toFileStag(Configuration config, 
				  int generations, 
				  double elapsedTime, int rep, 
				  int curStag, double curFitness,
				  double maxFitness, 
				  Individual bestIndividual) {
	
	BufferedWriter out;
	String toFile = Configuration.DATATYPE
	    [config.getDatatype()]+"\t"+config.getConfig()+"\t"
	    +Configuration.CROSSOVER_OPS
	    [config.getCrossoverOperator()]+"\t"
	    +Configuration.MUTATION_OPS
	    [config.getMutationOperator()]+"\t"
	    +Configuration.SELECTION_OPS
	    [config.getSelectionOperator()]+"\t"
	    +Configuration.TRANSFORM
	    [config.getFitnessTransform()]+"\t"
	    +config.getMutationRate()+"\t"
	    +config.getChildRepresentation()+"\t"
	    +config.getPopSize()+"\t"
	    +config.getMaxTime()+"\t"
	    +config.getTargetFitness()+"\t"
	    +config.getMaxStagnancy()+"\t"
	    +Configuration.METRIC[config.getMetric()]+"\t"
	    +rep+"\t"
	    +bestIndividual.getStringRepresentation()+"\t"
	    +bestIndividual.getFitness()+"\t"
	    +elapsedTime+"\t"+generations+"\t"+curStag+"\t"
	    +curFitness+"\t"+maxFitness+"\t"
	    +bestIndividual.getStringRepresentation()+"\n";
	
	try {
	    
	    out = new BufferedWriter
		(new FileWriter
		 (config.getOutputFileName()+".stag",true));
	    
	    out.write(toFile);
	    out.close();
	    
	}
	catch (IOException ex) {
	    
	    ex.printStackTrace();
	    
	}
	
    }
    
    public static void randomSearch(Configuration config, 
				    ArrayList<Chromosome> 
				    chromosomes, int rep, 
				    MetricCalculator 
				    fitnessCalculator) {
	
	long startTime = System.currentTimeMillis();
	Random RSrng = new Random();
	
	Population population 
	    = CreateRandomInitialPopulation.makePopulation
	    (chromosomes, config.getPopSize()*RS_FACTOR, 
	     RSrng.nextLong());
	
	fitnessCalculator.computeFitness(population);
	//debug
	/*
	  for (int i=0; i<population.getIndividuals().size(); 
	  i++) {
	  
	  System.out.println(population.getIndividuals().get(i)
	  .getStringRepresentation());
	  
	  }
	*/
	
	randomTime = System.currentTimeMillis() - startTime;	
	randomIndividual = population.getBestIndividual();
	
    }
    
}
