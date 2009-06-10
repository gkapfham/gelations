/**
 * Alexander Conrad
 * gelations.Configuration.java
 */
package gelations;

import java.util.ArrayList;
import java.io.Serializable;

/**
 * This class instantiates and stores all of the configuration, 
 * seed, and test case
 * information necessary for the execution of the prioritizer. 
 * Objects of this class can be 
 * serialized to allow for easy execution of the experiments.
 * 
 * @author conrada
 *
 */
public class Configuration implements Serializable {
    
    static final long serialVersionUID = 3L;
    
    private int type, muOp, crossOp, selOp, metric, repetitions, 
	popSize, fitTrans, maxStag;
    private double muRate, targetFitness, maxTime, childRep;
    
    private String dataFileCoverage, dataFileTime, dataFileSeeds, 
	dataFileOutput, config, serializedObjectName;
    private transient ArrayList<CaseTest> caseTests;
    private transient ArrayList<Integer> seeds;
    private ConfigurationReader reader;
    
    public static final String[] MUTATION_OPS 
	= {"DM","EM","ISM","IVM","SIM","SM"};
    public static final String[] CROSSOVER_OPS 
	= {"CX","MPX","OX1","OX2","PMX","POS","VR"};
    public static final String[] SELECTION_OPS 
	= {"ROU","TRU","TOU","RND"}; 
    public static final String[] DATATYPE = {"SYN","REAL"};
    public static final String[] METRIC = {"CE"};
    public static final String[] TRANSFORM 
	= {"EXP","UNT","LIN","LIN2","LIT"};
    
    private int argNum;
    
    public Configuration(String[] args) {
	
	argNum = 0;
	type = Integer.parseInt(args[argNum]);
	argNum++;
	
	if (type == 0) {
	    reader = new ConfigurationReaderSynthetic();
	}
	else {
	    reader = new ConfigurationReaderReal();
	}
	
	muOp = Integer.parseInt(args[argNum]);
	argNum++;
	
	crossOp = Integer.parseInt(args[argNum]);
	argNum++;
	
	selOp = Integer.parseInt(args[argNum]);
	argNum++;
	
	fitTrans = Integer.parseInt(args[argNum]);
	argNum++;
	
	muRate = Double.parseDouble(args[argNum]);
	argNum++;
	
	childRep = Double.parseDouble(args[argNum]);
	argNum++;
	
	popSize = Integer.parseInt(args[argNum]);
	argNum++;
	
	maxTime = Double.parseDouble(args[argNum]);
	argNum++;
	
	targetFitness = Double.parseDouble(args[argNum]);
	argNum++;
	
	maxStag = Integer.parseInt(args[argNum]);
	argNum++;
	
	metric = Integer.parseInt(args[argNum]);
	argNum++;
	
	dataFileCoverage = args[argNum];
	argNum++;
	
	dataFileTime = args[argNum];
	argNum++;
	
	dataFileSeeds = args[argNum];
	argNum++;
	
	dataFileOutput = args[argNum];
	argNum++;
	
	repetitions = Integer.parseInt(args[argNum]);
	argNum++;
	
	
	config = reader.getSetConfig(dataFileCoverage);
	
	//caseTests = reader.readCaseTests(dataFileCoverage, 
	// dataFileTime);
	
	//seeds = reader.readSeeds(dataFileSeeds);
	
	serializedObjectName = makeObjectName();
	
    }
    
    public void readData() {
	
	caseTests = reader.readCaseTests(dataFileCoverage, 
					 dataFileTime);
	seeds = reader.readSeeds(dataFileSeeds);
	
    }	
    
    /**
     * Constructs the name for this serialized object based on 
     * the configuration that it represents.
     * 
     * @return
     */
    public String makeObjectName() {
	
	String title = "";
	if (type == 1) 
	    title += "real_";
	else
	    title += "synthetic_";
	
	title += config + "_";
	
	title += MUTATION_OPS[muOp] + "_";
	
	title += CROSSOVER_OPS[crossOp] + "_";
	
	title += SELECTION_OPS[selOp] + "_";
	
	title += TRANSFORM[fitTrans] + "_";
	
	title += muRate + "_";
	
	title += childRep + "_";
	
	title += popSize + "_";
	
	title += maxTime + "_";
	
	title += targetFitness + "_";
	
	title += maxStag + "_";
	
	title += METRIC[metric] + "_";
	
	return title;
	
    }
    
    public String getObjectName() {
	return serializedObjectName;
    }
    
    public ArrayList<CaseTest> getCaseTests() {
	return caseTests;
    }
    
    public ArrayList<Integer> getSeeds() {
	return seeds;
    }
    
    public String getOutputFileName() {
	return dataFileOutput;
    }
    
    public int getMutationOperator() {
	return muOp;
    }
    
    public int getCrossoverOperator() {
	return crossOp;
    }
    
    public int getSelectionOperator() {
	return selOp;
    }
    
    public int getMetric() {
	return metric;
    }
    
    public double getMutationRate() {
	return muRate;
    }
    
    public double getTargetFitness() {
	return targetFitness;
    }
    
    public double getMaxTime() {
	return maxTime;
    }
    
    public int getRepetitions() {
	return repetitions;
    }
    
    public int getPopSize() {
	return popSize;
    }
    
    public double getChildRepresentation() {
	return childRep;
    }
    
    public String getConfig() {
	return config;
    }
    
    public int getDatatype() {
	return type;
    }
    
    public int getFitnessTransform() {
	return fitTrans;
    }
    
    public int getMaxStagnancy() {
	return maxStag;
    }
    
}
