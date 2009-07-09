# file:		MakeTreesOnly.R
# author:	Alexander Conrad
# date: 	8 August 2008

## This script generates all desired regression trees.

source("AnalyzePrioritizations.R")

for(i in 1:length(RealConfigs)) {
  
  print(paste("plotting for: ",RealConfigs[i]))
  
  MakeFitnessRegressionTree(SubsetDataset(RealData,RealConfigs[i]), title=paste(RealConfigs[i]," Coverage Effectiveness",sep=""), filesuffix=paste("Fitness_Summary_",RealConfigs[i],sep=""))
  MakeTimeRegressionTree(SubsetDataset(RealData,RealConfigs[i]), title=paste(RealConfigs[i]," Runtime",sep=""), filesuffix=paste("Runtime_Summary_",RealConfigs[i],sep=""))
  
  graphics.off()
  
}

print("DONE!")