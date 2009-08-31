/**
 * Alexander Conrad
 * gelations.InitDatafile.java
 */
package gelations;


/**
 * @author conrada
 */
public class InitDatafile {
    
    public static final int TOTAL_ARGS = 16;
    public static final String CALL = "int data_format  "
	+"int mutation_operator  int crossover_operator  "
	+"int selection_method  int fitness_transform  "
	+"double mutation_rate  double child_representation  "
	+"int pop_size  int max_time  double target_fitness  "
	+"int max_stagnancy  int metric  "
	+"String coverage_data_file  String timing_data_file  "
	+"String seed_data_file  String output_data_file  "
	+"int number_of_repetitions";
    
    public static void main(String[] args) {
		
	if (args.length < TOTAL_ARGS) {
	    
	    System.out.println("Incorrect arguments provided. "
			       +"Proper arguments are:");
	    System.out.println(" "+CALL);
	    System.exit(1);
	    
	}		
	
	Configuration config = new Configuration(args);
	
	WriteResults.writeHeader(config);
	//WriteResults.writeHeaderStag(config);
	
    }
        
}