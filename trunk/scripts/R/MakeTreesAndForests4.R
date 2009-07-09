# file:		MakeTreesAndForests3.R
# author:	Alexander Conrad
# date: 	11 August 2008

## This script generates regression trees and random forests for TM.
## Only 100 trees are generated for each random forest, rather than the 
## default of 500.

source("AnalyzePrioritizations.R")

i=9
  
  print(paste("plotting for: ",RealConfigs[i]))
  
  MakeFitnessRegressionTreeRF(SubsetDataset(RealData,RealConfigs[i]), title=paste(RealConfigs[i]," Coverage Effectiveness",sep=""), filesuffix=paste("Fitness_Summary_",RealConfigs[i],sep=""), trees=100)
  MakeTimeRegressionTreeRF(SubsetDataset(RealData,RealConfigs[i]), title=paste(RealConfigs[i]," Execution Time (in ms)",sep=""), filesuffix=paste("Runtime_Summary_",RealConfigs[i],sep=""), trees=100)
  
  graphics.off()
  


print("DONE!")