/**
 * Alexander Conrad 
 * gelations.CaseTest.java
 */
package gelations;

import java.io.Serializable;

/**
 * Instance class whose objects each correspond to a test case 
 * as read into the program from 
 * the datafiles. Each CaseTest object contains all of the 
 * relevant timing and requirement
 * coverage information about the test case being represented. 
 * CaseTest objects should be
 * unique; there should never be more than one CaseTest object 
 * existing for a single test 
 * case.
 * 
 * @author conrada
 *
 */
public class CaseTest implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * The execution time of the test case.
     */
    private double time;
	
    /**
     * The requirements covered by this test case, 
     * in integer format.
     */
    private int[] requirements;
	
    /**
     * unique identifier for this CaseTest.
     */
    private final int id;
	
    /**
     * Creates a new CaseTest object, representing a test 
     * case with an execution time of 
     * _time and covering the requirements cooresponding to 
     * the integer identifiers in 
     * _requirements.
     * 
     * @param _time - execution time
     * @param _requirements - requirements covered
     * @param _id - unique identifier
     */
    public CaseTest(double _time, int[] _requirements, 
		    int _id) {
	
	time = _time;
	requirements = _requirements;
	id = _id;
	
    }
    
    /**
     * Creates an empty CaseTest object with a fixed 
     * capacity of requirements.
     * 
     * @param numReqs - number of 
     * @param _id
     */
    public CaseTest(int numReqs, int _id) {
	
	time = -1;
	requirements = new int[numReqs];
	id = _id;
	
    }
    
    /**
     * Creates an empty CaseTest object.
     * 
     * @param _id
     */
    public CaseTest(int _id) {
	
	time = -1;
	id = _id;
	
    }
    
    /**
     * 
     * @param _requirements - list of requirements 
     * covered by this CaseTest
     */
    public void setReqs(int[] _requirements) {
	requirements = _requirements;
    }
    
    /**
     * 
     * @return the execution time of the test case 
     */
    public double getTime() {
	return time;
    }
    
    /**
     * 
     * @return the requirements covered by the test case
     */
    public int[] getRequirements() {
	return requirements;
    }
    
    /**
     * 
     * @return the unique identifier for this CaseTest
     */
    public int getId() {
	return id;
    }
    
}
