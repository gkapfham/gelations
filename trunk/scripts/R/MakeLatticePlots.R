# file:		MakeLatticePlots.R
# author:	Alexander Conrad
# date:		20 June 2009

## This script generates desired lattice plots incorporating new truncation experiments

#source("AnalyzePrioritizations.R")

AllRealData <- rbind(RealData, RealDataTou, RealDataTru)

crossops <- c("CX","MPX","OX2","PMX","POS")
mutrates <- c(0.33,0.67)
mutops <- c("DM","EM","ISM","IVM","SIM","SM")
childreps <- c(0.5,0.75,1)
maxstags <- c(20,30,40)
popsizes <- c(75,150,225)

for (co in 1:length(crossops)) {

  for (mr in 1:length(mutrates)) {
    
    for (cr in 1:length(childreps)) {
      
      for (ms in 1:length(maxstags)) {
        
        for (ps in 1:length(popsizes)) {
            
Dataset <- SubsetAll(AllRealData,crossop=crossops[co],mutrate=mutrates[mr],childrep=childreps[cr],maxstag=maxstags[ms],popsize=popsizes[ps])

MakeLatticePlotSelectionMethodRS(Dataset, 
    title=paste("co:",crossops[co]," mr:",mutrates[mr]," cr:",childreps[cr]," ms:",maxstags[ms]," ps:",popsizes[ps],sep=""), 
    filesuffix=paste("_co:",crossops[co],"_mr:",mutrates[mr],"_cr:",childreps[cr],"_ms:",maxstags[ms],"_ps:",popsizes[ps],sep=""),
    datatype=1, color=FALSE)

graphics.off()

}}}}}

print("DONE!")
