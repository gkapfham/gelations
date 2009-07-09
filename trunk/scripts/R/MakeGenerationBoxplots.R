# file:		MakeGenerationBoxplots.R
# author:	Alexander Conrad
# date: 	23 June 2009

## This script generates all desired box plots for generations

#source("AnalyzePrioritizations.R")

#AllRealData <- rbind(RealData, RealDataTou, RealDataTru)

RealConfigsMod <- c("DS","GB","JD","LF","RM","RP","SK","TM")

for(i in 1:length(RealConfigsMod)) {
  
  #print(paste("plotting for: ",RealConfigsMod[i]))

  MakeGenerationsBoxPlotForSelection(SubsetAll(AllRealData, dataset=RealConfigsMod[i]), title=paste("dataset=",RealConfigsMod[i],sep=""), filesuffix=paste("gen_",RealConfigsMod[i],sep=""))
  
  graphics.off()
  
}

print("DONE!")
