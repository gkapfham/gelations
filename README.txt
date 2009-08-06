note: this document is unfinished!

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

If you want to perform your own empirical study, 

Implemented as a part of GELATIONS are the partially-mapped crossover (PMX), cycle crossover (CX), order crossover (OX1), order based crossover (OX2), position based crossover (POS), maximal preservative crossover (MPX), and voting recombination (VR) crossover operators. For mutation, the displacement mutation (DM), exchange mutation (EM), insertion mutation (ISM), simple inversion mutation (SIM), inversion mutation (IVM), and scramble mutation (SM) operators have been implemented. These operators are described in detail in "Genetic Algorithms for the Travelling Salesman Problem: A Review of Representations and Operators" by P. LARRANAGA, C.M.H. KUIJPERS, R.H. MURGA, I. INZA and S. DIZDAREVIC. 


EXTENDING GELATIONS AND COMPILING FROM SOURCE
========================================




COPYRIGHT AND ACKNOWLEDGEMENTS
========================================

This software is released under the GNU General Public License v3. See LICENSE.txt for the full conditions under which this software is distributed. Feel free to modify the code to suit your needs.


