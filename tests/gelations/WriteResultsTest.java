/**
 *
 */
package gelations;

//import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList; 

/**
 * @author conrada
 *
 */
public class WriteResultsTest {
	
	String[] config1 = new String[]{"0","0","0","0","1","0.05","0.5","100","100000","0.95","8","0","/home/conrada/Catterwampus/tests/sample_data/matrix-rsss1","/home/conrada/Catterwampus/tests/sample_data/time-rsss1","/home/conrada/Catterwampus/tests/sample_data/seed-rsss1","/home/conrada/Catterwampus/tests/sample_data/results-rsss1","10"}; 
	Configuration configuration1;
	Individual individual1;
	long time1;
	int repetitions1, generations1;
	
	
	CaseTest ct1, ct2, ct3;
	Chromosome chrom1, chrom2, chrom3;
	Individual indi1, indi2, indi3, indi4;
	ArrayList<Chromosome> chroms1, chroms2, chroms3;
	ArrayList<Individual> individuals;
	
	double ct1Time, ct2Time, ct3Time;
	int ct1Id, ct2Id, ct3Id;
	int[] ct1Reqs, ct2Reqs, ct3Reqs;
	
	double indi1Fitness, indi2Fitness, indi3Fitness;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		configuration1 = new Configuration(config1);
		configuration1.readData();
		
		time1 = 123456789;
		repetitions1 = 3;
		generations1 = 20;
		
		ct1Time = 1.2345;
		ct2Time = 9.8765;
		ct3Time = 3.1415;
		ct1Id = 0;
		ct2Id = 1;
		ct3Id = 2;
		ct1Reqs = new int[]{1,2,3};
		ct2Reqs = new int[]{4,5,6};
		ct3Reqs = new int[]{1,3,5,7};
		indi1Fitness = 0.9876;
		indi2Fitness = 0.5;
		indi3Fitness = 0.4321;
		
		ct1 = new CaseTest(ct1Time, ct1Reqs, ct1Id);
		ct2 = new CaseTest(ct2Time, ct2Reqs, ct2Id);
		ct3 = new CaseTest(ct3Time, ct3Reqs, ct3Id);
		
		chrom1 = new Chromosome(ct1);
		chrom2 = new Chromosome(ct2);
		chrom3 = new Chromosome(ct3);
		
		chroms1 = new ArrayList<Chromosome>();
		chroms1.add(chrom1);
		chroms1.add(chrom2);
		chroms1.add(chrom3);
		indi1 = new Individual(chroms1, indi1Fitness);
		
		chroms2 = new ArrayList<Chromosome>();
		chroms2.add(chrom3);
		chroms2.add(chrom2);
		chroms2.add(chrom1);
		indi2 = new Individual(chroms2, indi2Fitness);
		
		chroms3 = new ArrayList<Chromosome>();
		chroms3.add(chrom2);
		chroms3.add(chrom1);
		indi3 = new Individual(chroms3, indi3Fitness);
		
		individuals = new ArrayList<Individual>();
		individuals.add(indi1);
		individuals.add(indi2);
		individuals.add(indi3);
		
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link gelations.WriteResults#writeHeader(gelations.Configuration)}.
	 */
	@Test
	public void testWriteHeader() {
		
		WriteResults.writeHeader(configuration1);
		
	}

	/**
	 * Test method for {@link gelations.WriteResults#toFile(gelations.Configuration, gelations.Individual, long, int)}.
	 */
	@Test
	public void testToFile() {
		
		WriteResults.toFile(configuration1, indi1, time1, repetitions1, generations1);
		
	}

}
