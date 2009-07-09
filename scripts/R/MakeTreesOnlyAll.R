# file:		MakeTreesOnlyAll.R
# author:	Alexander Conrad
# date: 	20 June 2009

## This script generates all desired regression trees.

#source("AnalyzePrioritizations.R")

AllRealData <- rbind(RealData, RealDataTou, RealDataTru)

RealConfigsMod <- c("DS","GB","JD","RM","RP","SK","TM")

for(i in 1:length(RealConfigsMod)) {
  
  print(paste("plotting for: ",RealConfigsMod[i]))
  
  MakeFitnessRegressionTree(SubsetDataset(AllRealData,RealConfigsMod[i]), title=paste(RealConfigsMod[i]," Coverage Effectiveness",sep=""), filesuffix=paste("merge_",RealConfigsMod[i],sep=""))
  MakeTimeRegressionTree(SubsetDataset(AllRealData,RealConfigsMod[i]), title=paste(RealConfigsMod[i]," Runtime",sep=""), filesuffix=paste("merge_",RealConfigsMod[i],sep=""))
  
  graphics.off()
  
}

print("DONE!")
