/**
 * Alexander Conrad
 * gelations.ResetDatafile.java
 */
package gelations;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.IOException;

/**
 * @author conrada
 *
 */
public class ResetDatafile {

    /**
     * @param args
     */
    public static void main(String[] args) {

	if (args.length<1) {
	    System.out.println("Please specify the filename for "
			       +"a serialized Configuration "
			       +"object!");
	    System.exit(1);
	}
		
	Configuration configuration = readConfiguration(args[0]);
	WriteResults.writeHeader(configuration);
	WriteResults.writeHeaderStag(configuration);
		
    }
	
    /**
     * Reads in a serialized Configuration object from the file 
     * system back into memory.
     * 
     * @param filename - location in the file system of the 
     *  Configuration object
     * @return the active Configuration object in memory
     */
    public static Configuration readConfiguration
	(String filename) {

	Configuration configuration;
	FileInputStream fis;
	ObjectInputStream in;
		
	try {
			
	    fis = new FileInputStream(filename);
	    in = new ObjectInputStream(fis);
	    configuration = (Configuration)in.readObject();
	    in.close();			
			
	}
	catch(IOException ex) {
			
	    ex.printStackTrace();
	    System.out.println("Unable to read Configuration "
			       +"object "+filename+"!");
	    configuration = null;
	    System.exit(1);		
			
	}
	catch(ClassNotFoundException ex) {
			
	    ex.printStackTrace();
	    System.out.println("Unable to read Configuration "
			       +"object "+filename+"!");
	    configuration = null;
	    System.exit(1);
			
	}
		
	return configuration;
		
    }

}
