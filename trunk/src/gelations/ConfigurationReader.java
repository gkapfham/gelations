/**
 * Alexander Conrad
 * gelations.ConfigurationReader.java
 */
package gelations;

import java.util.ArrayList;

/**
 * The purpose of this interface is to allow the prioritizer 
 * program to read data from
 * datafiles of different configurations. Classes implementing 
 * this interface will be able
 * to read different formats of data and convert it into a 
 * format that is usable by the 
 * prioritization program.
 * 
 * @author conrada
 *
 */
public interface ConfigurationReader {
    
    /**
     * Reads a list of seeds from a datafile and returns them, 
     * in order, in a list format.
     * 
     * @param seedFileName - datafile from which to read seed data
     * @return the individual seeds
     */
    public ArrayList<Integer> readSeeds(String seedFileName);
    
    /**
     * Creates an ordered list of CaseTests from a pair of 
     * datafile, one containing coverage 
     * information, and the other containing timing information.
     * 
     * @param coverageFileName - datafile from which to read the 
     *  coverage data
     * @param timeFileName - datafile from which to read the 
     *  timing data
     * @return the list of CaseTests constructed from the 
     *  datafiles
     */
    public ArrayList<CaseTest> readCaseTests
	(String coverageFileName, String timeFileName);
    
    /**
     * Creates a unique string identifier for the data set of 
     * which fileName is a member.
     * 
     * @param fileName - datafile belonging to a particular 
     *  dataset
     * @return unique identifier for the dataset
     */
    public String getSetConfig(String fileName);
    
}
