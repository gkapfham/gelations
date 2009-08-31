GELATIONS 1.0 (GEnetic aLgorithm bAsed Test suIte prIOritizatioN System)
========================================

The GELATIONS system consists of a genetic algorithm, implemented in Java, for the prioritization of regression test suites. This document assumes the reader's familiarity with the basics of a genetic algorithm. If the reader does not possess this familiarity, I suggest reading the very helpful Wikipedia article at http://en.wikipedia.org/wiki/Genetic_algorithm to get up to speed on the terminology. 

GELATIONS is a highly configurable research prototype, consisting of numerous different operators for selection, crossover, and mutation, and accepting parameters controlling population size, ratio of new children to old individuals in each population, and mutation rate. The algorithm can be configured to terminate after a set execution time, after an individual of a specific fitness value has been generated, or after the algorithm has stagnated for a specified number of generations. The stagnation value refers to the number of generations that have been evolved in which no new individual has evolved that beats the best fitness score thus far encountered.


RUNNING GELATIONS
========================================

By default, this system prioritizes test suites with respect to the Coverage Effectiveness (CE) metric. Although 

To run the prioritizer using an empirically-determined "best" configuration, execute the RunBestPrioritizer class, passing the locations of the datafiles containing coverage and timing information for the test suite to be prioritized, as well as the name of the datafile to be created to store the results. An example call would look like:

   java gelations.RunBestPrioritizer example/RMCoverage.dat example/RMTime.dat example/RMResults.dat

This would prioritize the test suite whose coverage data is contained in example/RMCoverage.dat and timing data is contained in example/RMTime.dat, and would create and print the results of the prioritization to the file example/RMResults.dat. WARNING: if the destination file already exists, this class will replace the contents of the file with the results of the prioritization, not append! RunBestPrioritizer uses the 40% truncation operator, order based crossover operator, insertion mutation operator, a population size of 75, a child percentage of 50% of each generation, a 0.33 mutation rate, and a maximum stagnancy of 20 as a termination condition.

Coverage and timing datafiles are plaintext and tab-delimited. Provided in this distribution are two example files, example/RMCoverage.dat and example/RMTime.dat, respectively containing coverage and timing information. The coverage file must begin with a line reading "NameCD	Requirements", and the timing file must begin with "NameTTD	Time". Each subsequent line in the coverage file represents an instance of a requirement being covered by a test case; the first number in each line identifies the test case, while the second number identifies the requirement. In the timing file, each subsequent line contains the execution time, in milliseconds, for one of the test cases.


USING DIFFERENT CONFIGURATIONS
========================================

If you want to perform an empirical study using different configurations of the genetic algorithm, use the InitSingleDatafile and RunPrioritizer classes located in the gelations package. InitSingleDatafile requires a path to the location where the results of the experiment are to be stored. This class simply erases any pre-existing file at the path, creates a new enpty file, and writes the appropriate header to the top of the file. RunPrioritizer executes the prioritizer for a given configuration of the genetic algorithm. The argument list for RunPrioritizer is: 

[int] dataformat (should always be 1, when using the plaintext data format described above), 
[int] mutation operator, 
[int] crossover operator, 
[int] selection operators, 
[int] fitness transform (this option is deprecated, should always be set to 1; transformations are now incorporated into the selection operators themselves), 
[double] mutation rate (0,1), 
[double] child representation (0,1), 
[int] population size, 
[int] max execution time (in ms; algorithm will terminate and return best solution after this time is reached), 
[double] target fitness (0,1) (algorithm will terminate and return best solution after a solution is produced whose fitness value meets or exceeds this parameter), 
[int] max stagnancy (number of successive generations that no new individual is produced whose fitness exceeds that of the most fit individual thus far encountered; if this value is exceeded, the algorithm will terminate and return the best solution), 
[int] metric (this should always be set to 0, unless using custom metrics not included in the package by default), 
[String] path to datafile containing coverage information, 
[String] path to datafile containing timing information, 
[String] path to datafile containing a list of seeds, 
[String] path to file into which output should be written, 
[int] number of repetitions (each repetition will use the next seed from the seed datafile)

All arguments should be supplies on the command line as space-separated entities. For the mutation, crossover, and selection operators, each operator is represented by an integer. A mapping of the integers to operators follows:

-------------------
Crossover Operators
-------------------
0 = cycle crossover (CX)
1 = maximal preservative crossover (MPX)
2 = order crossover (OX1)
3 = order based crossover (OX2)
4 = partially-mapped crossover (PMX)
5 = position based crossover (POS)
6 = voting recombination (VR

------------------
Mutation Operators
------------------
0 = displacement mutation (DM)
1 = exchange mutation (EM)
2 = insertion mutation (ISM)
3 = inversion mutation (IVM)
4 = simple inversion mutation (SIM)
5 = scramble mutation (SM)

-------------------
Selection Operators
-------------------
0 = roulette selection (ROU)
1 = 50% truncation selection (TRU50)
2 = binary tournament selection (TOU2)
3 = 3-tournament selection (TOU3)
4 = 4-tournament selection (TOU4)
5 = 5-tournament selection (TOU5)
6 = 40% truncation selection (TRU40)
7 = 60% truncation selection (TRU60)
8 = roulette selection with linear ranking fitness transformation (ROUL)
9 = roulette selection with exponential fitness transformation (ROUE)

The crossover and mutation operators are described in detail in "Genetic Algorithms for the Travelling Salesman Problem: A Review of Representations and Operators" by P. LARRANAGA, C.M.H. KUIJPERS, R.H. MURGA, I. INZA and S. DIZDAREVIC. For more information about the selection operators, consult "Threshold selecting: best possible probability distribution for crossover selection in genetic algorithms" by Jorg Lassig, Karl Heinz Hoffmann, and Mihaela Enachescu, "From mating pool distributions to model overfitting" by Claudio F. Lima, Fernando G. Lobo, and Martin Pelikan, and "Comparison of performance between different selection strategies on simple genetic algorithms" by Jinghui Zhong, Xiaomin Hu, Jun Zhang, and Min Gu.


EXTENDING GELATIONS AND COMPILING FROM SOURCE
========================================

This program was developed using version 1.6 of the Java compiler. Use earlier versions at your own risk. To build the program, simply execute the command "javac src/gelations/*.java -d bin/" from the root directory of the svn repository.

To add your own operators, you will need to modify the source of two class, depending on the type of operator that you are adding. First, you will need to add a new case in the switch statement of the initialization class mapping an integer, passed on the command line, to your new operator class. The initialization class for selection operators is InitializeParentSelector.java, for crossover operators is InitializeCrossoverOperators.java, and for mutation operators is InitializeMutationOperators.java. Your new operator class must extend the abstract class ParentSelector.java, CrossoverOperator.java, or MutationOperator.java, depending on the operator being implemented. New metrics can similarly be added by modifying MetricSelector.java and extending MetricCalculator.

In addition to modifying the initialization class, the class Configuration.java must also be modified with the abbreviation for the new operator. The String arrays SELECTION_OPS, CROSSOVER_OPS, MUTATION_OPS, and METRIC contain the abbreviations for each of these operators or metrics. The integer used to represent the operator is also used to select the correct abbreviations from these arrays, so when a new operator is added, these arrays must be updated with the desired abbreviation at the appropriate index.


COPYRIGHT AND ACKNOWLEDGEMENTS
========================================

This software is released under the GNU General Public License v3. See LICENSE.txt for the full conditions under which this software is distributed. Feel free to modify the code to suit your needs.


