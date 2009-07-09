# file:		MakeTreesAndForests.R
# author:	Alexander Conrad
# date: 	31 July 2008

## This script generates all desired regression trees and random forests.

source("AnalyzePrioritizations.R")

for(i in 1:length(RealConfigs)) {
  
  print(paste("plotting for: ",RealConfigs[i]))
  
  MakeFitnessRegressionTreeRF(SubsetDataset(RealData,RealConfigs[i]), title=paste(RealConfigs[i]," Coverage Effectiveness",sep=""), filesuffix=paste("Fitness_Summary_",RealConfigs[i],sep=""))
  MakeTimeRegressionTreeRF(SubsetDataset(RealData,RealConfigs[i]), title=paste(RealConfigs[i]," Runtime",sep=""), filesuffix=paste("Runtime_Summary_",RealConfigs[i],sep=""))
  
  graphics.off()
  
}

print("DONE!")