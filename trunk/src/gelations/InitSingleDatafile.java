package gelations;

/**
 * A simple wrapper class for easily calling InitDatafile
 * 
 * @author conrada
 */
public class InitSingleDatafile {
	
	public static final int TOTAL_ARGS = 1;
	public static final String CALL = "String output_data_file";
	
	public static void main(String[] args) {
		
		if (args.length < TOTAL_ARGS) {
		    
		    System.out.println("Incorrect arguments provided. "
				       +"Proper arguments are:");
		    System.out.println(" "+CALL);
		    System.exit(1);
		    
		}
		
		String[] arguments = {"1","0","0","0","0","0","0","0","0","0","0","0","ex/EXCoverage.dat","ex/EXTime.dat","ex/EXSeed.dat",args[0],"0"};
		
		InitDatafile.main(arguments);
		
	}
	
}
