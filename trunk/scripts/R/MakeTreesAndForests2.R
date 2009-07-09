# file:		MakeTreesAndForests2.R
# author:	Alexander Conrad
# date: 	11 August 2008

## This script generates the first half of regression trees and random forests.
## Only 100 trees are generated for each random forest, rather than the 
## default of 500.

source("AnalyzePrioritizations.R")

RealForestDatafilePath <- paste(RealDatafilePath,"trees/",sep="")

for(i in 1:(length(RealConfigs)/2)) {
  
  print(paste("plotting for: ",RealConfigs[i]))

  
  write(MakeFitnessRegressionTreeRF(SubsetDataset(RealData,RealConfigs[i]), title=paste(RealConfigs[i]," Coverage Effectiveness",sep=""), filesuffix=paste("Fitness_Summary_",RealConfigs[i],sep=""), trees=100), file=paste(RealForestDatafilePath,RealConfigs[i],"FitnessForestData.dat",sep=""))


  write(MakeTimeRegressionTreeRF(SubsetDataset(RealData,RealConfigs[i]), title=paste(RealConfigs[i]," Execution Time (in ms)",sep=""), filesuffix=paste("Runtime_Summary_",RealConfigs[i],sep=""), trees=100), file=paste(RealForestDatafilePath,RealConfigs[i],"RuntimeForestData.dat",sep=""))
  

  graphics.off()
  
}

print("DONE!")