/**
 * Alexander Conrad
 * gelations.Util.java
 */
package gelations;

//import java.util.Random;
/**
 * @author conrada
 *
 */
public class Util {

    //int numCutpoints = 0;
	
    /**
     * Selects a number of unique cutpoints within a genome of a 
     * certain size. Note: this 
     * method is currently not needed by this operator.
     * 
     * @param size - the size of the genome to be cut
     * @param cutpoints - the number of cutpoints to be selected
     * @param rng - the random number generator to be used
     * @return locations of cuts to be inserted
     */
    /*
      private int[] setCutPoints(int size, int cutpoints, Random 
      rng) {
		
      size--;
      int[] cuts = new int[size];
		
      int maxSubset = size;
      int minSubset = 0;
      int curSize = size;
		
      for (int i=0; i<cutpoints; i++) {
			
      cuts[i] = rng.nextInt(maxSubset-minSubset) + minSubset;
      if (cuts[i] < curSize/2) {
      minSubset = cuts[i]+1;
      } else {
      maxSubset = cuts[i];
      }
			
      }
		
      return cuts;
		
      }
    */
    /**
     * Selects a number of unique cutpoints within a genome of a 
     * certain size. Note: this 
     * implementation is very slow and should not be used.
     * 
     * @param size - the size of the genome to be cut
     * @param cutpoints - the number of cutpoints to be selected
     * @param rng - the random number generator to be used
     * @return locations of cuts to be inserted
     * @see setCutPoints
     */
    /*
      private int[] setCutPoints_slow
      (int parentSize, Random rng) {
		
      int[] cuts = new int[numCutpoints];
      boolean isUnique;
		
      for (int i=0; i<numCutpoints; i++) {
			
      do {
      isUnique = true;
      cuts[i] = rng.nextInt(parentSize-2);
      for (int j=0; j<i; j++) {
      if (cuts[j] == cuts[i]) {
      isUnique = false;
      }
      } 
			
      } while (!isUnique);
						
      }
		
      return cuts;
		
      }
    */
	
}
