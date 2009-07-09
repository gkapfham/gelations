# file:		AnalyzePrioritizations.R
# author: 	Alexander Conrad (unless otherwise noted)
# date: 	23 July 2008

## This file contains the necessary functions for visualizing the
## data from the prioritization experiments.

# necessary libraries and R files
library(R2HTML)
library(gplots)
library(tree)
library(randomForest)
library(lattice)
library(e1071)
library(ipred)
library(beanplot)
#library(vioplot)

# datafile locations:
SynDatafilePath <- paste(Home,"results/syn/",sep="")
RealDatafilePath <- paste(Home,"results/real/",sep="")
RealDatafilePathTou <- paste(Home, "results/real_tou/",sep="")

# real-world datafile identifiers
#RealConfigs <- c("DS","GB","JD","LF","RM","RP","SK","SR","TM")
RealConfigs <- c("DS","GB","JD","LF","RM","RP","SK","TM")

# vectors for parsing through the configurations
datasets <- c("DS","GB","JD","LF","RM","RP","SK","TM","*")
crossover_operators <- c("CX","MPX","OX1","OX2","PMX","POS","VR",
                         "*")
mutation_operators <- c("DM","EM","ISM","IVM","SIM","SM","*")
selection_methods <- c("ROU","TRU","TOU","*")
fitness_transforms <- c("EXP","UNT","LIN","*")
mutation_rates <- c("0.01","0.33","0.67","*")
child_densitys <- c("0.5","0.75","1","*")
pop_sizes <- c("75","150","225","*")
max_stagnancys <- c("20","30","40","*")

# synthetic datafile identifiers
SynBals <- c("r","c")
SynTests <- c("s","m","l")
SynReqs <- c("s","m","l")
SynPts <- c("s","m","l")
SynReps <- c("1")

# constants for window size
treewidth <- 11.5
treeheight <- 7.5
forestwidth <- 9
forestheight <- 5
latticewidth <- 7
latticeheight <- 4

###########################################################
### datatable creation ###

# read in all data from the real-world-based datafiles
ReadAllRealData <- function() {
  
  MasterData <- read.table(paste(RealDatafilePath,RealConfigs
                                 [length(RealConfigs)],
                                 "Results.dat",sep=""),header=T)
  
  # debug
  print(paste(RealDatafilePath,RealConfigs[length(RealConfigs)],
              "Results.dat",sep=""))

  #print(MasterData[,2])

  for(i in 1:(length(RealConfigs)-1)) {
    
    MasterData <- rbind(MasterData,
                        read.table(paste(RealDatafilePath,
                                         RealConfigs[i],
                                         "Results.dat",sep=""),
                                   header=T))

    # debug
    #print(MasterData[,2])
    print(paste(RealDatafilePath,RealConfigs[i],"Results.dat",
                sep=""));
    
  }
  
  return(MasterData)
  
}

# read in all data from the synthetic-based datafiles
ReadAllSynData <- function() {
  
  
  
}

## stores all real-world data, so that it doesn't have to be
## re-read for each 
## graph. NOTE: This variable, once assigned, should never be
## modified except 
## by ReloadAnalyzePrioritizations() or by re-sourcing the class!
#RealData <- ReadAllRealData()

# stores all synthetic data, so that it doesn't have to be
# re-read for each 
# graph. NOTE: This variable, once assigned, should never be
# modified except 
# by ReloadAnalyzePrioritizations() or by re-sourcing the class!
#SynData <- ReadAllSynData()

###########################################################
### libraries ###

# reloads all libraries and rereads all datafiles
ReloadAnalyzePrioritizations <- function() {
  
  library(R2HTML)
  library(gplots)
  library(tree)
  library(randomForest)
  library(lattice)
  library(e1071)
  library(ipred)
  library(beanplot)
  #library(vioplot)
  #source("../../CoverageEffectiveness.R")
  source("AnalyzePrioritizations.R")
  source("ReadResults.R")
  source("ReadBestResults.R")
  
}

###########################################################
### graphic save functions ###
### from: CoverageEffectiveness.R ###
### authors: GMK and Alexander Conrad ###

# FUNCTION: This function will save the current working graph to
# the
# file that is specified.  Note that the X11 device that is
# currently
# labelled as (ACTIVE) will be saved.
SaveGraphic <- function(fileName)
{
  
  dev.copy(postscript, file=fileName, height=5, width=6.5,
           horizontal=F, onefile=F)
  dev.off()

}

# FUNCTION: This function will save the current working graph to
# the
# file that is specified.  Note that the X11 device that is
# currently
# labelled as (ACTIVE) will be saved.
SaveGraphicPaper <- function(fileName)
{
  
  SaveGraphic(fileName)

}

# FUNCTION: This function will save the current working graph to
# the
# file that is specified.  Note that the X11 device that is
# currently
# labelled as (ACTIVE) will be saved.
#  NOTE: all SaveGraphicWeb functions are currently disabled
SaveGraphicWeb <- function(fileName)
{
  
  #dev.copy(png, file=fileName, horizontal=F, onefile=F)
  #dev.off()

}

# FUNCTION: This function saves tukey graphs as png files. This
# extra function 
# is necessary due to graph sizes.
#  NOTE: all SaveGraphicWeb functions are currently disabled
SaveGraphicTukeyWeb <- function(fileName)
{
  
  #dev.copy(png, file=fileName,
  #	   horizontal=F, onefile=F)
  #dev.off()
  
}

# FUNCTION: This function saves tukey graphs as eps files. This
# extra function 
# is necessary due to graph sizes.
SaveGraphicTukeyPaper <- function(fileName)
{
  
  dev.copy(postscript, file=fileName, height=11, width=5,
           horizontal=F, onefile=F)
  dev.off()
    
}

SaveGraphicBeanWeb <- function(fileName)
{
  
  #dev.copy(png, file=fileName,
  #	   horizontal=F, onefile=F)
  #dev.off()
  
}

SaveGraphicBeanPaper <- function(fileName)
{
  
  dev.copy(postscript, file=fileName, height=6, width=11,
           horizontal=F, onefile=F)
  dev.off()
    
}

# For saving a regression tree at a viewable size.
#  NOTE: all SaveGraphicWeb functions are currently disabled
SaveGraphicTreeWeb <- function(fileName) {
  
  #dev.copy(png, file=fileName,
  #	   horizontal=F, onefile=F)
  #dev.off()
  
}

# For saving a regression tree at a viewable size.
SaveGraphicTreePaper <- function(fileName) {
  
  dev.copy(postscript, file=fileName, height=treeheight,
           width=treewidth, horizontal=F, onefile=F)
  dev.off()
  
}

# For saving a forest analysis at a viewable size.
#  NOTE: all SaveGraphicWeb functions are currently disabled
SaveGraphicForestWeb <- function(fileName) {
  
  #dev.copy(png, file=fileName,
  #	   horizontal=F, onefile=F)
  #dev.off()
  
}

# For saving a forest analysis at a viewable size.
SaveGraphicForestPaper <- function(fileName) {
  
  dev.copy(postscript, file=fileName, height=forestheight,
           width=forestwidth, horizontal=F, onefile=F)
  dev.off()
  
}

# For saving a lattice graph at a viewable size.
#  NOTE: all SaveGraphicWeb functions are currently disabled
SaveGraphicLatticeWeb <- function(fileName) {
  
  #dev.copy(png, file=fileName,
  #	   horizontal=F, onefile=F)
  #dev.off()
  
}

# For saving a lattice graph at a viewable size.
SaveGraphicLatticePaper <- function(fileName) {
  
  dev.copy(postscript, file=fileName, height=latticeheight,
           width=latticewidth, horizontal=F, onefile=F)
  dev.off()
  
}


###########################################################
### subset functions ###

## The following Subset* functions are meant to allow for
## partitioning of the data sets.

RemoveSR <- function(MasterData) {
  
  SubsetData <- subset(MasterData, dataset != noquote("SR"))
  return(SubsetData)
  
}

SubsetDataset <- function(MasterData, dataset_var) {
	      	
  if(dataset_var=="*") {
    return(MasterData) 
  }
  
  SubsetData <- subset(MasterData, dataset
                       == noquote(dataset_var))
  return(SubsetData)

}

SubsetCrossoverOperator <- function(MasterData, subset_var) {
	      	
  if(subset_var=="*") {
    return(MasterData) 
  }
  
  SubsetData <- subset(MasterData, crossover_operator
                       == noquote(subset_var))
  return(SubsetData)

}

SubsetMutationOperator <- function(MasterData, subset_var) {
	      	
  if(subset_var=="*") {
    return(MasterData) 
  }
  
  SubsetData <- subset(MasterData, mutation_operator
                       == noquote(subset_var))
  return(SubsetData)

}

SubsetSelectionMethod <- function(MasterData, subset_var) {
	      	
  if(subset_var=="*") {
    return(MasterData) 
  }

  SubsetData <- subset(MasterData, selection_method
                       == noquote(subset_var))
  return(SubsetData)

}

SubsetFitnessTransform <- function(MasterData, subset_var) {
	      	
  if(subset_var=="*") {
    return(MasterData) 
  }

  SubsetData <- subset(MasterData, fitness_transform
                       == noquote(subset_var))
  return(SubsetData)

}

SubsetMutationRate <- function(MasterData, subset_var) {
	      	
  if(subset_var=="*") {
    return(MasterData) 
  }

  SubsetData <- subset(MasterData, mutation_rate
                       == noquote(subset_var))
  return(SubsetData)

}

SubsetChildDensity <- function(MasterData, subset_var) {
	      	
  if(subset_var=="*") {
    return(MasterData) 
  }

  SubsetData <- subset(MasterData, child_density == subset_var)
  return(SubsetData)

}

SubsetMaxStagnancy <- function(MasterData, subset_var) {
	      	
  if(subset_var=="*") {
    return(MasterData) 
  }

  SubsetData <- subset(MasterData, max_stagnancy
                       == noquote(subset_var))
  return(SubsetData)

}

SubsetGenerations <- function(MasterData, subset_var_min,
                              subset_var_max) {
	      	
  if(subset_var=="*") {
    return(MasterData) 
  }

  SubsetData <- subset(MasterData, generations
                       > noquote(subset_var_min) & generations
                       < noquote(subset_var_max) )
  return(SubsetData)

}

SubsetDatatype <- function(MasterData, subset_var) {
	      	
  if(subset_var=="*") {
    return(MasterData) 
  }

  SubsetData <- subset(MasterData, datatype
                       == noquote(subset_var))
  return(SubsetData)

}

SubsetPopSize <- function(MasterData, subset_var) {
	      	
  if(subset_var=="*") {
    return(MasterData) 
  }

  SubsetData <- subset(MasterData, pop_size
                       == noquote(subset_var))
  return(SubsetData)

}

SubsetAll <- function(MasterData, dataset="*", crossop="*",
                      mutop="*", selop="*", fitop="*",
                      mutrate="*", childrep="*", maxstag="*",
                      datatype="*", popsize="*") {
  
  SubsetData <- MasterData
  
  SubsetData <- SubsetDataset(SubsetData,dataset)
  
  SubsetData <- SubsetCrossoverOperator(SubsetData,crossop)
  
  SubsetData <- SubsetMutationOperator(SubsetData,mutop)

  SubsetData <- SubsetSelectionMethod(SubsetData,selop)
  
  SubsetData <- SubsetFitnessTransform(SubsetData,fitop)
  
  SubsetData <- SubsetMutationRate(SubsetData,mutrate)
  
  SubsetData <- SubsetChildDensity(SubsetData,childrep)
  
  SubsetData <- SubsetMaxStagnancy(SubsetData,maxstag)
  
  SubsetData <- SubsetDatatype(SubsetData,datatype)
  
  SubsetData <- SubsetPopSize(SubsetData,popsize)
  
  return(SubsetData)
  
}

###########################################################
### boxplot functions ###

# make a time boxplot based on a single specified variable
MakeTimeBoxPlot <- function(variable, MasterData, title,
                            filesuffix="", datatype=1) {
  
  if(datatype==1) {
    datapath <- RealDatafilePath
    label <- "REAL"
  }
  if(datatype==0) {
    datapath <- SynDatafilePath
    label <- "SYN"
  }
  
  max=1

  #MasterData <- ReadAllRealData()
  
  #print(MasterData)

  X11(height=6, width=6)
  
  if(variable == "datatype") {
  boxplot(runtime~datatype,
          data=MasterData,
          outline=FALSE,
          xlab=variable,
          ylab="Runtime (in ms)",
          main=title,
	  ylim=c(0,10000),
          col="grey75")
  }
  if(variable == "dataset") {
  boxplot(runtime~dataset,
          data=MasterData,
          outline=FALSE,
          xlab=variable,
          ylab="Runtime (in ms)",
          main=title,
	  ylim=c(0,10000),
          col="grey75")
  }
  if(variable == "crossover_operator") {
  boxplot(runtime~crossover_operator,
          data=MasterData,
          outline=FALSE,
          xlab=variable,
          ylab="Runtime (in ms)",
          main=title,
	  ylim=c(0,10000),
          col="grey75")
  }
  if(variable == "mutation_operator") {
  boxplot(runtime~mutation_operator,
          data=MasterData,
          outline=FALSE,
          xlab=variable,
          ylab="Runtime (in ms)",
          main=title,
	  ylim=c(0,10000),
          col="grey75")
  }
  if(variable == "selection_method") {
  boxplot(runtime~selection_method,
          data=MasterData,
          outline=FALSE,
          xlab=variable,
          ylab="Runtime (in ms)",
          main=title,
	  ylim=c(0,10000),
          col="grey75")
  }
  if(variable == "fitness_transform") {
  boxplot(runtime~fitness_transform,
          data=MasterData,
          outline=FALSE,
          xlab=variable,
          ylab="Runtime (in ms)",
          main=title,
	  ylim=c(0,10000),
          col="grey75")
  }
  if(variable == "mutation_rate") {
  boxplot(runtime~mutation_rate,
          data=MasterData,
          outline=FALSE,
          xlab=variable,
          ylab="Runtime (in ms)",
          main=title,
	  ylim=c(0,10000),
          col="grey75")
  }
  if(variable == "child_density") {
  boxplot(runtime~child_density,
          data=MasterData,
          outline=FALSE,
          xlab=variable,
          ylab="Runtime (in ms)",
          main=title,
	  ylim=c(0,10000),
          col="grey75")
  }
  if(variable == "pop_size") {
  boxplot(runtime~pop_size,
          data=MasterData,
          outline=FALSE,
          xlab=variable,
          ylab="Runtime (in ms)",
          main=title,
	  ylim=c(0,10000),
          col="grey75")
  }
  if(variable == "max_time") {
  boxplot(runtime~max_time,
          data=MasterData,
          outline=FALSE,
          xlab=variable,
          ylab="Runtime (in ms)",
          main=title,
	  ylim=c(0,10000),
          col="grey75")
  }
  if(variable == "target_fitness") {
  boxplot(runtime~target_fitness,
          data=MasterData,
          outline=FALSE,
          xlab=variable,
          ylab="Runtime (in ms)",
          main=title,
	  ylim=c(0,10000),
          col="grey75")
  }
  if(variable == "max_stagnancy") {
  boxplot(runtime~max_stagnancy,
          data=MasterData,
          outline=FALSE,
          xlab=variable,
          ylab="Runtime (in ms)",
          main=title,
	  ylim=c(0,10000),
          col="grey75")
  }
  if(variable == "metric") {
  boxplot(runtime~metric,
          data=MasterData,
          outline=FALSE,
          xlab=variable,
          ylab="Runtime (in ms)",
          main=title,
	  ylim=c(0,10000),
          col="grey75")
  }
  if(variable == "rep") {
  boxplot(runtime~rep,
          data=MasterData,
          outline=FALSE,
          xlab=variable,
          ylab="Runtime (in ms)",
          main=title,
	  ylim=c(0,10000),
          col="grey75")
  }
  if(variable == "generations") {
  boxplot(runtime~generations,
          data=MasterData,
          outline=FALSE,
          xlab=variable,
          ylab="Runtime (in ms)",
          main=title,
	  ylim=c(0,10000),
          col="grey75")
  }
  
  histolegend <- "Runtime (in ms)"
  par(xpd=TRUE)
  par(usr=c(0,1,0,1))
  legend(x=.3, y=1.1, histolegend, bty="n", 
  	 horiz=TRUE, fill="grey75")
  
  SaveGraphicWeb(paste(datapath,"graphs/web/BOXPLOT_RUNTIME_"
                       ,filesuffix,sep=""))
  SaveGraphicPaper(paste(datapath,"graphs/paper/BOXPLOT_RUNTIME_"
                         ,filesuffix,".eps",sep=""))
  
}



MakeCEBoxPlot <- function(variable, MasterData, title,
                          filesuffix="", datatype=1) {

  MakeFitnessBoxPlot(variable, "Coverage Effectiveness",
                     MasterData, title, filesuffix, datatype)

}

# maxe a fitness boxplot based on a single specified variable
MakeFitnessBoxPlot <- function(variable, metric_name, MasterData,
                               title, filesuffix="", datatype=1) {
  
  if(datatype==1) {
    datapath <- RealDatafilePath
    label <- "REAL"
  }
  if(datatype==0) {
    datapath <- SynDatafilePath
    label <- "SYN"
  }
  
  max=1

  #MasterData <- ReadAllRealData()

  #print(MasterData)

  X11(height=6, width=6)
  
  if(variable == "datatype") {
  boxplot(runtime~datatype,
          data=MasterData,
          outline=FALSE,
          xlab=variable,
          ylab=metric_name,
          main=title,
	  ylim=c(0.86,1.0),
          col="grey75")
  }
  if(variable == "dataset") {
  boxplot(fitness~dataset,
          data=MasterData,
          outline=FALSE,
          xlab=variable,
          ylab=metric_name,
          main=title,
	  ylim=c(0.86,1.0),
          col="grey75")
  }
  if(variable == "crossover_operator") {
  boxplot(fitness~crossover_operator,
          data=MasterData,
          outline=FALSE,
          xlab=variable,
          ylab=metric_name,
          main=title,
	  ylim=c(0.86,1.0),
          col="grey75")
  }
  if(variable == "mutation_operator") {
  boxplot(fitness~mutation_operator,
          data=MasterData,
          outline=FALSE,
          xlab=variable,
          ylab="Fitness (in ms)",
          main=title,
	  ylim=c(0.86,1.0),
          col="grey75")
  }
  if(variable == "selection_method") {
  boxplot(fitness~selection_method,
          data=MasterData,
          outline=FALSE,
          xlab=variable,
          ylab=metric_name,
          main=title,
	  ylim=c(0.86,1.0),
          col="grey75")
  }
  if(variable == "fitness_transform") {
  boxplot(fitness~fitness_transform,
          data=MasterData,
          outline=FALSE,
          xlab=variable,
          ylab=metric_name,
          main=title,
	  ylim=c(0.86,1.0),
          col="grey75")
  }
  if(variable == "mutation_rate") {
  boxplot(fitness~mutation_rate,
          data=MasterData,
          outline=FALSE,
          xlab=variable,
          ylab=metric_name,
          main=title,
	  ylim=c(0.86,1.0),
          col="grey75")
  }
  if(variable == "child_density") {
  boxplot(fitness~child_density,
          data=MasterData,
          outline=FALSE,
          xlab=variable,
          ylab=metric_name,
          main=title,
          col="grey75")
  }
  if(variable == "pop_size") {
  boxplot(fitness~pop_size,
          data=MasterData,
          outline=FALSE,
          xlab=variable,
          ylab=metric_name,
          main=title,
	  ylim=c(0.86,1.0),
          col="grey75")
  }
  if(variable == "max_time") {
  boxplot(fitness~max_time,
          data=MasterData,
          outline=FALSE,
          xlab=variable,
          ylab=metric_name,
          main=title,
	  ylim=c(0.86,1.0),
          col="grey75")
  }
  if(variable == "target_fitness") {
  boxplot(fitness~target_fitness,
          data=MasterData,
          outline=FALSE,
          xlab=variable,
          ylab=metric_name,
          main=title,
	  ylim=c(0.86,1.0),
          col="grey75")
  }
  if(variable == "max_stagnancy") {
  boxplot(fitness~max_stagnancy,
          data=MasterData,
          outline=FALSE,
          xlab=variable,
          ylab=metric_name,
          main=title,
	  ylim=c(0.86,1.0),
          col="grey75")
  }
  if(variable == "metric") {
  boxplot(fitness~metric,
          data=MasterData,
          outline=FALSE,
          xlab=variable,
          ylab=metric_name,
          main=title,
	  ylim=c(0.86,1.0),
          col="grey75")
  }
  if(variable == "rep") {
  boxplot(fitness~rep,
          data=MasterData,
          outline=FALSE,
          xlab=variable,
          ylab=metric_name,
          main=title,
	  ylim=c(0.86,1.0),
          col="grey75")
  }
  if(variable == "generations") {
  boxplot(fitness~generations,
          data=MasterData,
          outline=FALSE,
          xlab=variable,
          ylab=metric_name,
          main=title,
	  ylim=c(0.86,1.0),
          col="grey75")
  }
  
  histolegend <- metric_name
  par(xpd=TRUE)
  par(usr=c(0,1,0,1))
  legend(x=.25, y=1.1, histolegend, bty="n", 
  	 horiz=TRUE, fill="grey75")
  
  SaveGraphicWeb(paste(datapath,"graphs/web/BOXPLOT_FITNESS_",
                       filesuffix,sep=""))
  SaveGraphicPaper(paste(datapath,"graphs/paper/BOXPLOT_FITNESS_",
                         filesuffix,".eps",sep=""))
  
}

# make a time beanplot based on a single specified variable
MakeTimeBeanPlot <- function(variable, MasterData, title,
                             filesuffix="", datatype=1) {
  
  if(datatype==1) {
    datapath <- RealDatafilePath
    label <- "REAL"
  }
  if(datatype==0) {
    datapath <- SynDatafilePath
    label <- "SYN"
  }
  
  max=1

  #MasterData <- ReadAllRealData()
  
  #print(MasterData)

  X11(height=6, width=6)
  
  if(variable == "datatype") {
  beanplot(runtime~datatype,
          data=MasterData,
          outline=FALSE,
          xlab=variable,
          ylab="Runtime (in ms)",
          main=title,
	  ylim=c(0,10000),
          col="grey75",
          maxstripline=0.0)
  }
  if(variable == "dataset") {
  beanplot(runtime~dataset,
          data=MasterData,
          outline=FALSE,
          xlab=variable,
          ylab="Runtime (in ms)",
          main=title,
	  ylim=c(0,10000),
          col="grey75",
          maxstripline=0.0)
  }
  if(variable == "crossover_operator") {
  beanplot(runtime~crossover_operator,
          data=MasterData,
          outline=FALSE,
          xlab=variable,
          ylab="Runtime (in ms)",
          main=title,
	  ylim=c(0,10000),
          col="grey75",
          maxstripline=0.0)
  }
  if(variable == "mutation_operator") {
  beanplot(runtime~mutation_operator,
          data=MasterData,
          outline=FALSE,
          xlab=variable,
          ylab="Runtime (in ms)",
          main=title,
	  ylim=c(0,10000),
          col="grey75",
          maxstripline=0.0)
  }
  if(variable == "selection_method") {
  beanplot(runtime~selection_method,
          data=MasterData,
          outline=FALSE,
          xlab=variable,
          ylab="Runtime (in ms)",
          main=title,
	  ylim=c(0,10000),
          col="grey75",
          maxstripline=0.0)
  }
  if(variable == "fitness_transform") {
  beanplot(runtime~fitness_transform,
          data=MasterData,
          outline=FALSE,
          xlab=variable,
          ylab="Runtime (in ms)",
          main=title,
	  ylim=c(0,10000),
          col="grey75",
          maxstripline=0.0)
  }
  if(variable == "mutation_rate") {
  beanplot(runtime~mutation_rate,
          data=MasterData,
          outline=FALSE,
          xlab=variable,
          ylab="Runtime (in ms)",
          main=title,
	  ylim=c(0,10000),
          col="grey75",
          maxstripline=0.0)
  }
  if(variable == "child_density") {
  beanplot(runtime~child_density,
          data=MasterData,
          outline=FALSE,
          xlab=variable,
          ylab="Runtime (in ms)",
          main=title,
	  ylim=c(0,10000),
          col="grey75",
          maxstripline=0.0)
  }
  if(variable == "pop_size") {
  beanplot(runtime~pop_size,
          data=MasterData,
          outline=FALSE,
          xlab=variable,
          ylab="Runtime (in ms)",
          main=title,
	  ylim=c(0,10000),
          col="grey75",
          maxstripline=0.0)
  }
  if(variable == "max_time") {
  beanplot(runtime~max_time,
          data=MasterData,
          outline=FALSE,
          xlab=variable,
          ylab="Runtime (in ms)",
          main=title,
	  ylim=c(0,10000),
          col="grey75",
          maxstripline=0.0)
  }
  if(variable == "target_fitness") {
  beanplot(runtime~target_fitness,
          data=MasterData,
          outline=FALSE,
          xlab=variable,
          ylab="Runtime (in ms)",
          main=title,
	  ylim=c(0,10000),
          col="grey75",
          maxstripline=0.0)
  }
  if(variable == "max_stagnancy") {
  beanplot(runtime~max_stagnancy,
          data=MasterData,
          outline=FALSE,
          xlab=variable,
          ylab="Runtime (in ms)",
          main=title,
	  ylim=c(0,10000),
          col="grey75",
          maxstripline=0.0)
  }
  if(variable == "metric") {
  beanplot(runtime~metric,
          data=MasterData,
          outline=FALSE,
          xlab=variable,
          ylab="Runtime (in ms)",
          main=title,
	  ylim=c(0,10000),
          col="grey75",
          maxstripline=0.0)
  }
  if(variable == "rep") {
  beanplot(runtime~rep,
          data=MasterData,
          outline=FALSE,
          xlab=variable,
          ylab="Runtime (in ms)",
          main=title,
	  ylim=c(0,10000),
          col="grey75",
          maxstripline=0.0)
  }
  if(variable == "generations") {
  beanplot(runtime~generations,
          data=MasterData,
          outline=FALSE,
          xlab=variable,
          ylab="Runtime (in ms)",
          main=title,
	  ylim=c(0,10000),
          col="grey75",
          maxstripline=0.0)
  }
  
  histolegend <- "Runtime (in ms)"
  par(xpd=TRUE)
  par(usr=c(0,1,0,1))
  legend(x=.3, y=1.1, histolegend, bty="n", 
  	 horiz=TRUE, fill="grey75")
  
  SaveGraphicWeb(paste(datapath,"graphs/web/BEANPLOT_RUNTIME_",
                       filesuffix,sep=""))
  SaveGraphicPaper(paste(datapath,
                         "graphs/paper/BEANPLOT_RUNTIME_",
                         filesuffix,".eps",sep=""))
  
}



MakeCEBeanPlot <- function(variable, MasterData, title,
                           filesuffix="", datatype=1) {

  MakeFitnessBeanPlot(variable, "Coverage Effectiveness",
                      MasterData, title, filesuffix, datatype)

}

# maxe a fitness beanplot based on a single specified variable
MakeFitnessBeanPlot <- function(variable, metric_name,
                                MasterData, title, filesuffix="",
                                datatype=1) {
  
  if(datatype==1) {
    datapath <- RealDatafilePath
    label <- "REAL"
  }
  if(datatype==0) {
    datapath <- SynDatafilePath
    label <- "SYN"
  }
  
  max=1

  #MasterData <- ReadAllRealData()

  #print(MasterData)

  X11(height=6, width=6)
  
  if(variable == "datatype") {
  beanplot(fitness~datatype,
          data=MasterData,
          outline=FALSE,
          xlab=variable,
          ylab=metric_name,
          main=title,
	  ylim=c(0.86,1.0),
          col="grey75",
          maxstripline=0.0)
  }
  if(variable == "dataset") {
  beanplot(fitness~dataset,
          data=MasterData,
          outline=FALSE,
          xlab=variable,
          ylab=metric_name,
          main=title,
	  ylim=c(0.86,1.0),
          col="grey75",
          maxstripline=0.0)
  }
  if(variable == "crossover_operator") {
  beanplot(fitness~crossover_operator,
          data=MasterData,
          outline=FALSE,
          xlab=variable,
          ylab=metric_name,
          main=title,
	  ylim=c(0.86,1.0),
          col="grey75",
          maxstripline=0.0)
  }
  if(variable == "mutation_operator") {
  beanplot(fitness~mutation_operator,
          data=MasterData,
          outline=FALSE,
          xlab=variable,
          ylab="Fitness (in ms)",
          main=title,
	  ylim=c(0.86,1.0),
          col="grey75",
          maxstripline=0.0)
  }
  if(variable == "selection_method") {
  beanplot(fitness~selection_method,
          data=MasterData,
          outline=FALSE,
          xlab=variable,
          ylab=metric_name,
          main=title,
	  ylim=c(0.86,1.0),
          col="grey75",
          maxstripline=0.0)
  }
  if(variable == "fitness_transform") {
  beanplot(fitness~fitness_transform,
          data=MasterData,
          outline=FALSE,
          xlab=variable,
          ylab=metric_name,
          main=title,
	  ylim=c(0.86,1.0),
          col="grey75",
          maxstripline=0.0)
  }
  if(variable == "mutation_rate") {
  beanplot(fitness~mutation_rate,
          data=MasterData,
          outline=FALSE,
          xlab=variable,
          ylab=metric_name,
          main=title,
	  ylim=c(0.86,1.0),
          col="grey75",
          maxstripline=0.0)
  }
  if(variable == "child_density") {
  beanplot(fitness~child_density,
          data=MasterData,
          outline=FALSE,
          xlab=variable,
          ylab=metric_name,
          main=title,
          col="grey75",
          maxstripline=0.0)
  }
  if(variable == "pop_size") {
  beanplot(fitness~pop_size,
          data=MasterData,
          outline=FALSE,
          xlab=variable,
          ylab=metric_name,
          main=title,
	  ylim=c(0.86,1.0),
          col="grey75",
          maxstripline=0.0)
  }
  if(variable == "max_time") {
  beanplot(fitness~max_time,
          data=MasterData,
          outline=FALSE,
          xlab=variable,
          ylab=metric_name,
          main=title,
	  ylim=c(0.86,1.0),
          col="grey75",
          maxstripline=0.0)
  }
  if(variable == "target_fitness") {
  beanplot(fitness~target_fitness,
          data=MasterData,
          outline=FALSE,
          xlab=variable,
          ylab=metric_name,
          main=title,
	  ylim=c(0.86,1.0),
          col="grey75",
          maxstripline=0.0)
  }
  if(variable == "max_stagnancy") {
  beanplot(fitness~max_stagnancy,
          data=MasterData,
          outline=FALSE,
          xlab=variable,
          ylab=metric_name,
          main=title,
	  ylim=c(0.86,1.0),
          col="grey75",
          maxstripline=0.0)
  }
  if(variable == "metric") {
  beanplot(fitness~metric,
          data=MasterData,
          outline=FALSE,
          xlab=variable,
          ylab=metric_name,
          main=title,
	  ylim=c(0.86,1.0),
          col="grey75",
          maxstripline=0.0)
  }
  if(variable == "rep") {
  beanplot(fitness~rep,
          data=MasterData,
          outline=FALSE,
          xlab=variable,
          ylab=metric_name,
          main=title,
	  ylim=c(0.86,1.0),
          col="grey75",
          maxstripline=0.0)
  }
  if(variable == "generations") {
  beanplot(fitness~generations,
          data=MasterData,
          outline=FALSE,
          xlab=variable,
          ylab=metric_name,
          main=title,
	  ylim=c(0.86,1.0),
          col="grey75",
          maxstripline=0.0)
  }
  
  histolegend <- metric_name
  par(xpd=TRUE)
  par(usr=c(0,1,0,1))
  legend(x=.25, y=1.1, histolegend, bty="n", 
  	 horiz=TRUE, fill="grey75")
  
  SaveGraphicWeb(paste(datapath,"graphs/web/BEANPLOT_FITNESS_",
                       filesuffix,sep=""))
  SaveGraphicPaper(paste(datapath,
                         "graphs/paper/BEANPLOT_FITNESS_",
                         filesuffix,".eps",sep=""))
  
}

# make a time boxplot based on a single specified variable
MakeTimeBoxPlotRS <- function(variable, MasterData, title,
                              filesuffix="RS", datatype=1) {
  
  if(datatype==1) {
    datapath <- RealDatafilePath
    label <- "REAL"
  }
  if(datatype==0) {
    datapath <- SynDatafilePath
    label <- "SYN"
  }
  
  max=1

  #MasterData <- ReadAllRealData()
  
  #print(MasterData)

  X11(height=6, width=6)
  
  if(variable == "datatype") {
  boxplot(random_search_runtime~datatype,
          data=MasterData,
          outline=FALSE,
          xlab=variable,
          ylab="Runtime (in ms)",
          main=title,
	  ylim=c(0,10000),
          col="grey75")
  }
  if(variable == "dataset") {
  boxplot(random_search_runtime~dataset,
          data=MasterData,
          outline=FALSE,
          xlab=variable,
          ylab="Runtime (in ms)",
          main=title,
	  ylim=c(0,10000),
          col="grey75")
  }
  if(variable == "crossover_operator") {
  boxplot(random_search_runtime~crossover_operator,
          data=MasterData,
          outline=FALSE,
          xlab=variable,
          ylab="Runtime (in ms)",
          main=title,
	  ylim=c(0,10000),
          col="grey75")
  }
  if(variable == "mutation_operator") {
  boxplot(random_search_runtime~mutation_operator,
          data=MasterData,
          outline=FALSE,
          xlab=variable,
          ylab="Runtime (in ms)",
          main=title,
	  ylim=c(0,10000),
          col="grey75")
  }
  if(variable == "selection_method") {
  boxplot(random_search_runtime~selection_method,
          data=MasterData,
          outline=FALSE,
          xlab=variable,
          ylab="Runtime (in ms)",
          main=title,
	  ylim=c(0,10000),
          col="grey75")
  }
  if(variable == "fitness_transform") {
  boxplot(random_search_runtime~fitness_transform,
          data=MasterData,
          outline=FALSE,
          xlab=variable,
          ylab="Runtime (in ms)",
          main=title,
	  ylim=c(0,10000),
          col="grey75")
  }
  if(variable == "mutation_rate") {
  boxplot(random_search_runtime~mutation_rate,
          data=MasterData,
          outline=FALSE,
          xlab=variable,
          ylab="Runtime (in ms)",
          main=title,
	  ylim=c(0,10000),
          col="grey75")
  }
  if(variable == "child_density") {
  boxplot(random_search_runtime~child_density,
          data=MasterData,
          outline=FALSE,
          xlab=variable,
          ylab="Runtime (in ms)",
          main=title,
	  ylim=c(0,10000),
          col="grey75")
  }
  if(variable == "pop_size") {
  boxplot(random_search_runtime~pop_size,
          data=MasterData,
          outline=FALSE,
          xlab=variable,
          ylab="Runtime (in ms)",
          main=title,
	  ylim=c(0,10000),
          col="grey75")
  }
  if(variable == "max_time") {
  boxplot(random_search_runtime~max_time,
          data=MasterData,
          outline=FALSE,
          xlab=variable,
          ylab="Runtime (in ms)",
          main=title,
	  ylim=c(0,10000),
          col="grey75")
  }
  if(variable == "target_fitness") {
  boxplot(random_search_runtime~target_fitness,
          data=MasterData,
          outline=FALSE,
          xlab=variable,
          ylab="Runtime (in ms)",
          main=title,
	  ylim=c(0,10000),
          col="grey75")
  }
  if(variable == "max_stagnancy") {
  boxplot(random_search_runtime~max_stagnancy,
          data=MasterData,
          outline=FALSE,
          xlab=variable,
          ylab="Runtime (in ms)",
          main=title,
	  ylim=c(0,10000),
          col="grey75")
  }
  if(variable == "metric") {
  boxplot(random_search_runtime~metric,
          data=MasterData,
          outline=FALSE,
          xlab=variable,
          ylab="Runtime (in ms)",
          main=title,
	  ylim=c(0,10000),
          col="grey75")
  }
  if(variable == "rep") {
  boxplot(random_search_runtime~rep,
          data=MasterData,
          outline=FALSE,
          xlab=variable,
          ylab="Runtime (in ms)",
          main=title,
	  ylim=c(0,10000),
          col="grey75")
  }
  if(variable == "generations") {
  boxplot(random_search_runtime~generations,
          data=MasterData,
          outline=FALSE,
          xlab=variable,
          ylab="Runtime (in ms)",
          main=title,
	  ylim=c(0,10000),
          col="grey75")
  }
  
  histolegend <- "Runtime (in ms)"
  par(xpd=TRUE)
  par(usr=c(0,1,0,1))
  legend(x=.3, y=1.1, histolegend, bty="n", 
  	 horiz=TRUE, fill="grey75")
  
  SaveGraphicWeb(paste(datapath,"graphs/web/BOXPLOT_RUNTIME_",
                       filesuffix,sep=""))
  SaveGraphicPaper(paste(datapath,"graphs/paper/BOXPLOT_RUNTIME_",
                         filesuffix,".eps",sep=""))
  
}

MakeCEBoxPlotRS <- function(variable, MasterData, title,
                            filesuffix="RS", datatype=1) {

  MakeFitnessBoxPlotRS(variable, "Coverage Effectiveness",
                       MasterData, title, filesuffix, datatype)

}

MakeFitnessBoxPlotRS <- function(variable, metric_name,
                                 MasterData, title, filesuffix="",
                                 datatype=1) {
  
  if(datatype==1) {
    datapath <- RealDatafilePath
    label <- "REAL"
  }
  if(datatype==0) {
    datapath <- SynDatafilePath
    label <- "SYN"
  }
  
  max=1

  #MasterData <- ReadAllRealData()
  
  #print(MasterData)

  X11(height=6, width=6)
  
  if(variable == "datatype") {
  boxplot(random_search_fitness~datatype,
          data=MasterData,
          outline=FALSE,
          xlab=variable,
          ylab=metric_name,
          main=title,
	  ylim=c(0.86,1.0),
          col="grey75")
  }
  if(variable == "dataset") {
  boxplot(random_search_fitness~dataset,
          data=MasterData,
          outline=FALSE,
          xlab=variable,
          ylab=metric_name,
          main=title,
	  ylim=c(0.86,1.0),
          col="grey75")
  }
  if(variable == "crossover_operator") {
  boxplot(random_search_fitness~crossover_operator,
          data=MasterData,
          outline=FALSE,
          xlab=variable,
          ylab=metric_name,
          main=title,
	  ylim=c(0.86,1.0),
          col="grey75")
  }
  if(variable == "mutation_operator") {
  boxplot(random_search_fitness~mutation_operator,
          data=MasterData,
          outline=FALSE,
          xlab=variable,
          ylab=metric_name,
          main=title,
	  ylim=c(0.86,1.0),
          col="grey75")
  }
  if(variable == "selection_method") {
  boxplot(random_search_fitness~selection_method,
          data=MasterData,
          outline=FALSE,
          xlab=variable,
          ylab=metric_name,
          main=title,
	  ylim=c(0.86,1.0),
          col="grey75")
  }
  if(variable == "fitness_transform") {
  boxplot(random_search_fitness~fitness_transform,
          data=MasterData,
          outline=FALSE,
          xlab=variable,
          ylab=metric_name,
          main=title,
	  ylim=c(0.86,1.0),
          col="grey75")
  }
  if(variable == "mutation_rate") {
  boxplot(random_search_fitness~mutation_rate,
          data=MasterData,
          outline=FALSE,
          xlab=variable,
          ylab=metric_name,
          main=title,
	  ylim=c(0.86,1.0),
          col="grey75")
  }
  if(variable == "child_density") {
  boxplot(random_search_fitness~child_density,
          data=MasterData,
          outline=FALSE,
          xlab=variable,
          ylab=metric_name,
          main=title,
	  ylim=c(0.86,1.0),
          col="grey75")
  }
  if(variable == "pop_size") {
  boxplot(random_search_fitness~pop_size,
          data=MasterData,
          outline=FALSE,
          xlab=variable,
          ylab=metric_name,
          main=title,
	  ylim=c(0.86,1.0),
          col="grey75")
  }
  if(variable == "max_time") {
  boxplot(random_search_fitness~max_time,
          data=MasterData,
          outline=FALSE,
          xlab=variable,
          ylab=metric_name,
          main=title,
	  ylim=c(0.86,1.0),
          col="grey75")
  }
  if(variable == "target_fitness") {
  boxplot(random_search_fitness~target_fitness,
          data=MasterData,
          outline=FALSE,
          xlab=variable,
          ylab=metric_name,
          main=title,
	  ylim=c(0.86,1.0),
          col="grey75")
  }
  if(variable == "max_stagnancy") {
  boxplot(random_search_fitness~max_stagnancy,
          data=MasterData,
          outline=FALSE,
          xlab=variable,
          ylab=metric_name,
          main=title,
	  ylim=c(0.86,1.0),
          col="grey75")
  }
  if(variable == "metric") {
  boxplot(random_search_fitness~metric,
          data=MasterData,
          outline=FALSE,
          xlab=variable,
          ylab="Runtime (in ms)",
          main=title,
	  ylim=c(0.86,1.0),
          col="grey75")
  }
  if(variable == "rep") {
  boxplot(random_search_fitness~rep,
          data=MasterData,
          outline=FALSE,
          xlab=variable,
          ylab=metric_name,
          main=title,
	  ylim=c(0.86,1.0),
          col="grey75")
  }
  if(variable == "generations") {
  boxplot(random_search_fitness~generations,
          data=MasterData,
          outline=FALSE,
          xlab=variable,
          ylab=metric_name,
          main=title,
	  ylim=c(0.86,1.0),
          col="grey75")
  }
  
  histolegend <- metric_name
  par(xpd=TRUE)
  par(usr=c(0,1,0,1))
  legend(x=.3, y=1.1, histolegend, bty="n", 
  	 horiz=TRUE, fill="grey75")
  
  SaveGraphicWeb(paste(datapath,"graphs/web/BOXPLOT_FITNESS_",
                       filesuffix,sep=""))
  SaveGraphicPaper(paste(datapath,"graphs/paper/BOXPLOT_FITNESS_",
                         filesuffix,".eps",sep=""))
  
}

###########################################################
### anova/tukey analysis ###

AnovaAnalysisRealCE <- function(variable, MasterData) {
  
  AnovaAnalysisFitness(variable, MasterData,
                       "Coverage Effectiveness", 1)
  
}

# Perform anova and tukey analyses on a given dataset for fitness
# with respect to a specific variable. 
# datatype=1: real-world data. datatype=0: synthetic data.
AnovaAnalysisFitness <- function(variable, masterData,
                                 metric_name, datatype=1) {
  
  if(datatype==1) {
    datapath <- RealDatafilePath
    label <- "REAL"
  }
  if(datatype==0) {
    datapath <- SynDatafilePath
    label <- "SYN"
  }
  
  # do anova analysis for fitness
  if(variable=="dataset") {
    fitnessAovData <- aov(fitness~dataset,data=masterData)
  }
  if(variable=="crossover_operator") {
    fitnessAovData <- aov(fitness~crossover_operator,
                          data=masterData)
  }
  if(variable=="mutation_operator") {
    fitnessAovData <- aov(fitness~mutation_operator,
                          data=masterData)
  }
  if(variable=="selection_method") {
    fitnessAovData <- aov(fitness~selection_method,
                          data=masterData)
  }
  if(variable=="fitness_transform") {
    fitnessAovData <- aov(fitness~fitness_transform,
                          data=masterData)
  }
  if(variable=="mutation_rate") {
    fitnessAovData <- aov(fitness~mutation_rate,data=masterData)
  }
  if(variable=="child_density") {
    fitnessAovData <- aov(fitness~child_density,data=masterData)
  }
  if(variable=="pop_size") {
    fitnessAovData <- aov(fitness~pop_size,data=masterData)
  }
  if(variable=="max_time") {
    fitnessAovData <- aov(fitness~max_time,data=masterData)
  }
  if(variable=="target_fitness") {
    fitnessAovData <- aov(fitness~target_fitness,data=masterData)
  }
  if(variable=="max_stagnancy") {
    fitnessAovData <- aov(fitness~max_stagnancy,data=masterData)
  }
  if(variable=="rep") {
    fitnessAovData <- aov(fitness~rep,data=masterData)
  }
  if(variable=="runtime") {
    fitnessAovData <- aov(fitness~runtime,data=masterData)
  }
  if(variable=="random_search_runtime") {
    fitnessAovData <- aov(random_search_fitness~random_search_runtime,data=masterData)
  }
    

  #print(summary(fitnessAovData))
  fileLoc <- paste(datapath,"stats/fitness/FITNESS_ANOVA_STATS_",
                   label,"_",variable,".html",sep="")
  fitnessAovSummary <- summary(fitnessAovData)
  HTML(fitnessAovSummary, file=fileLoc)
  
  # do tuckey analysis for fitness
  fitnessTukeyData <- TukeyHSD(fitnessAovData)
  #print(fitnessTukeyData)
  fileLoc <- paste(datapath,"stats/fitness/FITNESS_TUKEY_STATS_",
                   label,"_",variable,".html",sep="")
  #HTML(fitnessTukeyData, file=fileLoc)
        
  X11(height=10, width=5)
  plot(fitnessTukeyData,
       sub = paste("TukeyHSD_",label,"_",metric_name,"_",variable,
         sep=""))
       
  SaveGraphicTukeyPaper(paste(datapath,
                              "graphs/paper/FITNESS_TUKEY_GRAPH_",
                              label,"_",variable,".eps",sep=""))
  SaveGraphicTukeyWeb(paste(datapath,
                            "graphs/web/FITNESS_TUKEY_GRAPH_",
                            label,"_",variable,".png",sep=""))
  
  #graphics.off()

}

# Perform anova and tukey analyses on a given dataset for runtime
# with respect to a specific variable. 
# datatype=0: real-world data. datatype=1: synthetic data.
AnovaAnalysisTime <- function(variable, MasterData, datatype=1) {
  
  if(datatype==1) {
    datapath <- RealDatafilePath
    label <- "REAL"
  }
  if(datatype==0) {
    datapath <- SynDatafilePath
    label <- "SYN"
  }
  
  # do anova analysis for runtime
  if(variable=="dataset") {
    runtimeAovData <- aov(runtime~dataset,data=masterData)
  }
  if(variable=="crossover_operator") {
    runtimeAovData <- aov(runtime~crossover_operator,
                          data=masterData)
  }
  if(variable=="mutation_operator") {
    runtimeAovData <- aov(runtime~mutation_operator,
                          data=masterData)
  }
  if(variable=="selection_method") {
    runtimeAovData <- aov(runtime~selection_method,
                          data=masterData)
  }
  if(variable=="fitness_transform") {
    runtimeAovData <- aov(runtime~fitness_transform,
                          data=masterData)
  }
  if(variable=="mutation_rate") {
    runtimeAovData <- aov(runtime~mutation_rate,data=masterData)
  }
  if(variable=="child_density") {
    runtimeAovData <- aov(runtime~child_density,data=masterData)
  }
  if(variable=="pop_size") {
    runtimeAovData <- aov(runtime~pop_size,data=masterData)
  }
  if(variable=="max_time") {
    runtimeAovData <- aov(runtime~max_time,data=masterData)
  }
  if(variable=="target_fitness") {
    runtimeAovData <- aov(runtime~target_fitness,data=masterData)
  }
  if(variable=="max_stagnancy") {
    runtimeAovData <- aov(runtime~max_stagnancy,data=masterData)
  }
  if(variable=="rep") {
    runtimeAovData <- aov(runtime~rep,data=masterData)
  }

  #print(summary(runtimeAovData))
  fileLoc <- paste(datapath,"stats/time/RUNTIME_ANOVA_STATS_",
                   label,"_",variable,".html",sep="")
  runtimeAovSummary <- summary(runtimeAovData)
  HTML(runtimeAovSummary, file=fileLoc)
  
  # do tuckey analysis for runtime
  runtimeTukeyData <- TukeyHSD(runtimeAovData)
  #print(runtimeTukeyData)
  fileLoc <- paste(datapath,"stats/time/RUNTIME_TUKEY_STATS_",
                   label,"_",variable,".html",sep="")
  #HTML(runtimeTukeyData, file=fileLoc)
        
  X11(height=10, width=5)
  plot(runtimeTukeyData,
       sub = paste("TukeyHSD_",label,"_",metric_name,"_",
         variable))
  
  SaveGraphicTukeyPaper(paste(datapath,
                              "graphs/paper/RUNTIME_TUKEY_GRAPH_",
                              label,"_",variable,".eps",sep=""))
  SaveGraphicTukeyWeb(paste(datapath,
                            "graphs/web/RUNTIME_TUKEY_GRAPH_",
                            label,"_",variable,".png",sep=""))
  
  #graphics.off()

  # do anova analysis for time
  timeAovData <- aov(runtime~variable,data=masterData)
  #print(summary(timeAovData))
  fileLoc <- paste(datapath,"stats/time/TIME_ANOVA_STATS_",label,
                   "_",variable,".html",sep="")
  timeAovSummary <- summary(timeAovData)
  HTML(timeAovSummary, file=fileLoc)

  # do tuckey analysis for time
  timeTukeyData <- TukeyHSD(timeAovData)
  #print(timeTukeyData)
  fileLoc <- paste(datapath,"stats/time/TIME_TUKEY_STATS_",label,
                   "_",variable,".html",sep="")
  #HTML(timeTukeyData, file=fileLoc)
    
  X11(height=10, width=5)
  plot(timeTukeyData,
       sub = paste("TukeyHSD_",label,"_Runtime_",variable,sep=""))
  
  SaveGraphicTukeyPaper(paste(datapath,
                              "graphs/paper/TIME_TUKEY_GRAPH_",
                              label,"_",variable,".eps",sep=""))
  SaveGraphicTukeyWeb(paste(datapath,
                            "graphs/web/TIME_TUKEY_GRAPH_",
                            label,"_",variable,".png",sep=""))

  #graphics.off()
    
}

###########################################################
### regression trees ###

MakeFitnessRegressionTree <- function(Dataset,
                                      title=
                                      "Coverage Effectiveness Regression Tree",
                                      filesuffix="Fitness_Summary",
                                      datatype=1) {

  if(datatype==1) {
    datapath <- RealDatafilePath
    label <- "REAL"
  }
  if(datatype==0) {
    datapath <- SynDatafilePath
    label <- "SYN"
  }

  masterData <- SubsetDataForFitnessTree(Dataset)  

  masterDataTree <- tree(fitness~.,data=masterData)

  # plot regression tree
  
  X11(width=treewidth, height=treeheight)
  plot(masterDataTree)
  text(masterDataTree, cex=.8, pretty=0, splits=TRUE)
  
  par(xpd=TRUE)
  par(usr=c(0,1,0,1))
  text(x=.45, y=1.09, title, cex=1.1)
  
  SaveGraphicTreeWeb(paste(datapath,
                           "trees/web/FITNESS_TREE_REGRESSION_",
                           label,"_",filesuffix,".png",sep=""))
  SaveGraphicTreePaper(paste(datapath,
                             "trees/paper/FITNESS_TREE_REGRESSION_",
                             label,"_",filesuffix,".eps",sep=""))
  
  # plot regression summary

  X11()
  plot(prune.tree(masterDataTree))
#, ylab="deviance (D)", xlab="Number of Leaf Nodes")
  
  par(xpd=TRUE)
  par(usr=c(0,1,0,1))
  text(x=.45, y=1.15, paste(title," Deviance Summary",sep=""), cex=1.1)

  SaveGraphicWeb(paste(datapath,
                       "trees/web/FITNESS_TREE_SUMMARY_",
                       label,"_",filesuffix,".png",sep=""))
  SaveGraphicPaper(paste(datapath,
                         "trees/paper/FITNESS_TREE_SUMMARY_",
                         label,"_",filesuffix,".eps",sep=""))
    
}



MakeTimeRegressionTree <- function(Dataset,
                                   title=
                                   "Execution Time Regression Tree",
                                   filesuffix="Runtime_Summary",
                                   datatype=1) {
  
  if(datatype==1) {
    datapath <- RealDatafilePath
    label <- "REAL"
  }
  if(datatype==0) {
    datapath <- SynDatafilePath
    label <- "SYN"
  }
  
  masterData <- SubsetDataForTimeTree(Dataset) 

  masterDataTree <- tree(runtime~.,data=masterData)

  # plot regression tree

  X11(width=treewidth, height=treeheight)
  plot(masterDataTree)
  text(masterDataTree, cex=.8, pretty=0, splits=TRUE)
  
  par(xpd=TRUE)
  par(usr=c(0,1,0,1))
  text(x=.45, y=1.09, title, cex=1.1)
  
  SaveGraphicTreeWeb(paste(datapath,
                           "trees/web/RUNTIME_TREE_REGRESSION_",
                           label,"_",filesuffix,".png",sep=""))
  SaveGraphicTreePaper(paste(datapath,
                             "trees/paper/RUNTIME_TREE_REGRESSION_",
                             label,"_",filesuffix,".eps",sep=""))
  
  # plot regression summary
  
  X11()
  plot(prune.tree(masterDataTree))
#, ylab="deviance (D)", xlab="Number of Leaf Nodes")
  
  par(xpd=TRUE)
  par(usr=c(0,1,0,1))
  text(x=.45, y=1.15, paste(title," Deviance Summary",sep=""),
       cex=1.1)
  
  SaveGraphicWeb(paste(datapath,"trees/web/RUNTIME_TREE_SUMMARY_",
                       label,"_",filesuffix,".png",sep=""))
  SaveGraphicPaper(paste(datapath,"trees/paper/RUNTIME_TREE_SUMMARY_",
                         label,"_",filesuffix,".eps",sep=""))
  
}



MakeFitnessRegressionTreeRF <- function(Dataset,
                                        title=
                                        "Coverage Effectiveness Regression Tree",
                                        filesuffix="Fitness_Summary",
                                        datatype=1, trees=500) {
  
  if(datatype==1) {
    datapath <- RealDatafilePath
    label <- "REAL"
  }
  if(datatype==0) {
    datapath <- SynDatafilePath
    label <- "SYN"
  }

  masterData <- SubsetDataForFitnessTree(Dataset) 

  masterDataTree <- tree(fitness~.,data=masterData)
  
  # plot regression tree
  
  X11(width=treewidth, height=treeheight)
  plot(masterDataTree)
  text(masterDataTree, cex=.8, pretty=0, splits=TRUE)
  
  par(xpd=TRUE)
  par(usr=c(0,1,0,1))
  text(x=.45, y=1.09, title, cex=1.1)
  
  SaveGraphicTreeWeb(paste(datapath,
                           "trees/web/FITNESS_TREE_REGRESSION_",
                           label,"_",filesuffix,".png",sep=""))
  SaveGraphicTreePaper(paste(datapath,
                             "trees/paper/FITNESS_TREE_REGRESSION_",
                             label,"_",filesuffix,".eps",sep=""))
  
  # plot regression summary
  
  X11()
  plot(prune.tree(masterDataTree))
#, ylab="deviance (D)", xlab="Number of Leaf Nodes")

  par(xpd=TRUE)
  par(usr=c(0,1,0,1))
  text(x=.45, y=1.15, paste(title," Deviance Summary",sep=""),
       cex=1.1)
  
  SaveGraphicWeb(paste(datapath,
                       "trees/web/FITNESS_TREE_SUMMARY_",
                       label,"_",filesuffix,".png",sep=""))
  SaveGraphicPaper(paste(datapath,
                         "trees/paper/FITNESS_TREE_SUMMARY_",
                         label,"_",filesuffix,".eps",sep=""))
  
  # plot variable importance
  
  masterDataRandomForest <- randomForest(fitness~.,data=masterData,
    importance=TRUE, 
    ntree=trees)
  
  print(masterDataRandomForest)
  
  print(importance(masterDataRandomForest))
  
  X11(width=forestwidth, height=forestheight)
  varImpPlot(masterDataRandomForest,
             main=paste(title," Variable Importance",sep=""))
  
  SaveGraphicForestWeb(paste(datapath,
                             "trees/web/FITNESS_FOREST_IMPORTANCE_",
                             label,"_",filesuffix,".png",sep=""))
  SaveGraphicForestPaper(paste
                         (datapath,
                          "trees/paper/FITNESS_FOREST_IMPORTANCE_",
                          label,"_",filesuffix,".eps",sep=""))
  
  # plot variable importance summary
  
  X11()
  plot(masterDataRandomForest, main=paste(title,
                                 " Random Forest Error Summary",
                                 sep=""))
  
  #par(xpd=TRUE)
  #par(usr=c(0,1,0,1))
  #text(x=.45, y=1.15, paste(title,"_Variable_Importance_Summary",
  # sep=""), cex=.9)
  
  SaveGraphicWeb(paste(datapath,
                       "trees/web/FITNESS_FOREST_SUMMARY_",
                       label,"_",filesuffix,".png",sep=""))
  SaveGraphicPaper(paste(datapath,
                         "trees/paper/FITNESS_FOREST_SUMMARY_",
                         label,"_",filesuffix,".eps",sep=""))
  
  return(importance(masterDataRandomForest))
  
}



MakeTimeRegressionTreeRF <- function(Dataset,
                                     title="Execution Time Regression Tree",
                                     filesuffix="Runtime_Summary",
                                     datatype=1, trees=500) {
  
  if(datatype==1) {
    datapath <- RealDatafilePath
    label <- "REAL"
  }
  if(datatype==0) {
    datapath <- SynDatafilePath
    label <- "SYN"
  }
  
  masterData <- SubsetDataForTimeTree(Dataset) 

  masterDataTree <- tree(runtime~.,data=masterData)

  # plot regression tree  

  X11(width=treewidth, height=treeheight)
  plot(masterDataTree)
  text(masterDataTree, cex=.8, pretty=0, splits=TRUE)

  par(xpd=TRUE)
  par(usr=c(0,1,0,1))
  text(x=.45, y=1.09, title, cex=1.1)
  
  SaveGraphicTreeWeb(paste(datapath,
                           "trees/web/RUNTIME_TREE_REGRESSION_",
                           label,"_",filesuffix,".png",sep=""))
  SaveGraphicTreePaper(paste(datapath,
                             "trees/paper/RUNTIME_TREE_REGRESSION_",
                             label,"_",filesuffix,".eps",sep=""))
  
  # plot regression summary
  
  X11()
  plot(prune.tree(masterDataTree))
#, ylab="deviance (D)", xlab="Number of Leaf Nodes")
  
  par(xpd=TRUE)
  par(usr=c(0,1,0,1))
  text(x=.45, y=1.15, paste(title," Deviance Summary",sep=""),
       cex=1.1)
  
  SaveGraphicWeb(paste(datapath,"trees/web/RUNTIME_TREE_SUMMARY_",
                       label,"_",filesuffix,".png",sep=""))
  SaveGraphicPaper(paste(datapath,"trees/paper/RUNTIME_TREE_SUMMARY_",
                         label,"_",filesuffix,".eps",sep=""))
  
  # plot variable importance
  
  masterDataRandomForest <- randomForest(runtime~.,data=masterData,
    importance=TRUE, keep.forest=TRUE,
    ntree=trees)
  
  print(masterDataRandomForest)
  
  print(importance(masterDataRandomForest))
  
  X11(width=forestwidth, height=forestheight)
  varImpPlot(masterDataRandomForest,
             main=paste(title," Variable Importance",sep=""))
  
  SaveGraphicForestWeb(paste(datapath,
                             "trees/web/RUNTIME_FOREST_IMPORTANCE_",
                             label,"_",filesuffix,".png",sep=""))
  SaveGraphicForestPaper(paste(datapath,
                               "trees/paper/RUNTIME_FOREST_IMPORTANCE_",
                               label,"_",filesuffix,".eps",sep=""))
  
  # plot variable importance summary
  
  X11()
  plot(masterDataRandomForest,
       main=paste(title," Random Forest Error Summary",sep=""))
  
  #par(xpd=TRUE)
  #par(usr=c(0,1,0,1))
  #text(x=.45, y=1.15, paste(title,"_Variable_Importance_Summary",
  # sep=""), cex=.9)
  
  SaveGraphicWeb(paste(datapath,
                       "trees/web/RUNTIME_FOREST_SUMMARY_",
                       label,"_",filesuffix,".png",sep=""))
  SaveGraphicPaper(paste(datapath,
                         "trees/paper/RUNTIME_FOREST_SUMMARY_",
                         label,"_",filesuffix,".eps",sep=""))
  
  return(masterDataRandomForest)
  
}



# Remove all columns from the dataset that we can't control or
# aren't interested
# in, in order to isolate the parameters that we can control.
# This function 
# prepares the data for fitness regression tree construction.
SubsetDataForFitnessTree <- function(Dataset) {

  masterData <- SubsetDataForTree(Dataset)
  
  masterData$runtime <- NULL
  
  return(masterData)

}

# Remove all columns from the dataset that we can't control or
# aren't interested
# in, in order to isolate the parameters that we can control.
# This function 
# prepares the data for runtime regression tree construction.
SubsetDataForTimeTree <- function(Dataset) {

  masterData <- SubsetDataForTree(Dataset)
  
  masterData$fitness <- NULL

  return(masterData)

}

# Remove all columns from the dataset that we can't control or
# aren't interested
# in, in order to isolate the parameters that we can control.
SubsetDataForTree <- function(Dataset) {

  masterData <- Dataset
  
  masterData$datatype <- NULL
  masterData$order <- NULL
  masterData$init_order <- NULL
  masterData$init_reverse_order <- NULL  
  masterData$random_search_order <- NULL
  masterData$random_search_fitness <- NULL
  masterData$dataset <- NULL
  masterData$init_fitness <- NULL
  masterData$init_reverse_fitness <- NULL
  masterData$generations <- NULL
  masterData$random_search_runtime <- NULL
  masterData$rep <- NULL
  
  # the following are nullified because they are not currently
  # being varied in the experiments
  masterData$max_time <- NULL
  masterData$target_fitness <- NULL
  masterData$metric <- NULL
  
  return(masterData)

}

MakeAllRandomForests <- function() {
  
  for (d in 1:(length(datasets)-1)) {
    
    MakeFitnessRegressionTreeRF(SubsetAll(RealData,
                                          dataset=datasets[d]),
                                title=paste(datasets[d],
                                  " Coverage Effectiveness"),
                                filesuffix=datasets[d],
                                trees=100
                                )
    MakeTimeRegressionTreeRF(SubsetAll(RealData,
                                       dataset=datasets[d]),
                             title=paste(datasets[d],
                               " Execution Time"),
                             filesuffix=datasets[d],
                             trees=100
                             )
    
	graphics.off()
    
  }
  
}

MakeTMRandomForests <- function() {
  
  d = 9
    
    MakeFitnessRegressionTreeRF(SubsetAll(RealData,
                                          dataset=datasets[d]),
                                title=paste(datasets[d],
                                  " Coverage Effectiveness"),
                                filesuffix=datasets[d],
                                trees=100
                                )
    MakeTimeRegressionTreeRF(SubsetAll(RealData,
                                       dataset=datasets[d]),
                             title=paste(datasets[d],
                               " Execution Time"),
                             filesuffix=datasets[d],
                             trees=100
                             )

	graphics.off()
    
  
  
}

###########################################################
### lattice plotting code ###


MakeLatticePlot <- function(Dataset, title="", filesuffix="",
                            datatype=1, color=FALSE) {
  
  if(datatype==1) {
    datapath <- RealDatafilePath
    label <- "REAL"
  }
  if(datatype==0) {
    datapath <- SynDatafilePath
    label <- "SYN"
  }

  if(color==FALSE) {
    lattice.options(default.theme = canonical.theme(color=FALSE))
  }
  
  X11(height=latticeheight, width=latticewidth)
  
  if(color==FALSE) {
    strip.background <- trellis.par.get("strip.background")
    trellis.par.set(strip.background=list(col=grey(7:1/8)))
    plot.symbol <- trellis.par.get("plot.symbol")
    trellis.par.set(plot.symbol=list(col=grey(5/8)))
  
    print(xyplot(fitness ~ runtime | dataset, 
		     data=Dataset, 
		     #groups=crossover_operator,
		     layout=c(4,2),
		     par.settings=(list(superpose.symbol=
                                        list(pch=c(0),cex=0.5,
                                             col=c("grey5")))),
		     #auto.key = list(columns=4, lines=FALSE),

                     par.strip.text = list(cex=.7),
                     scales = list(x=list(cex=.7),
                       ylist=(cex=.7)),
                     
		     xlab="Execution Time (in ms)",
		     ylab="Coverage Effectiveness",
		     main=title	
		      ))
  }

  else {
    
    print(xyplot(fitness ~ runtime | dataset, 
		     data=Dataset, 
		     #groups=crossover_operator,
		     layout=c(4,2),
		     par.settings=(list(superpose.symbol=
                                        list(pch=c(0),cex=0.5,
                                             col=c("black")))),
		     #auto.key = list(columns=4, lines=FALSE),
		     xlab="Execution Time (in ms)",
		     ylab="Coverage Effectiveness",
		     main=title	
		      ))
  }		      
  
  SaveGraphicLatticeWeb(paste(datapath,"lattice/web/LATTICE_",
                              filesuffix,".png",sep=""))
  SaveGraphicLatticePaper(paste(datapath,"lattice/paper/LATTICE_",
                                filesuffix,".eps",sep=""))
  
}


# Note: this plot will be very dense: it is highly recommended that the 
# dataset be reduced before trying to graph as a lattice plot
MakeLatticePlotCrossoverOperator <- function(Dataset, title="",
                                             filesuffix="",
                                             datatype=1,
                                             color=FALSE) {
  
  if(datatype==1) {
    datapath <- RealDatafilePath
    label <- "REAL"
  }
  if(datatype==0) {
    datapath <- SynDatafilePath
    label <- "SYN"
  }

  if(color==FALSE) {
    lattice.options(default.theme = canonical.theme(color=FALSE))
  }
  
  X11(height=latticeheight, width=latticewidth)
  
  if(color==FALSE) {
    strip.background <- trellis.par.get("strip.background")
    trellis.par.set(strip.background=list(col=grey(7:1/8)))
    plot.symbol <- trellis.par.get("plot.symbol")
    trellis.par.set(plot.symbol=list(col=grey(5/8)))
  
    print(xyplot(fitness ~ runtime | dataset, 
		     data=Dataset, 
		     groups=crossover_operator,
		     layout=c(4,2),
		     par.settings=(list(superpose.symbol=
                                        list(pch=c(0,1,2,3,4,5,6,
                                               8),cex=0.5,col=
                                             c("grey5","grey15",
                                               "grey25","grey35",
                                               "grey45","grey55",
                                               "grey65","grey75"))
                                        )),
		     auto.key = list(columns=4, lines=FALSE),

                     par.strip.text = list(cex=.7),
                     scales = list(x=list(cex=.7),
                       ylist=(cex=.7)),
                     
		     xlab="Execution Time (in ms)",
		     ylab="Coverage Effectiveness",
		     main=title	
		      ))
  }

  else {
    
    print(xyplot(fitness ~ runtime | dataset, 
		     data=Dataset, 
		     groups=crossover_operator,
		     layout=c(4,2),
		     par.settings=(list(superpose.symbol=
                                        list(pch=c(0,1,2,3,4,5,6,
                                               8),cex=0.5,col=
                                             c("black","red",
                                               "orange","yellow",
                                               "green","blue",
                                               "purple","grey"))
                                        )),
		     auto.key = list(columns=4, lines=FALSE),
		     xlab="Execution Time (in ms)",
		     ylab="Coverage Effectiveness",
		     main=title	
		      ))
  }		      
  
  SaveGraphicLatticeWeb(paste(datapath,"lattice/web/LATTICE_",
                              filesuffix,".png",sep=""))
  SaveGraphicLatticePaper(paste(datapath,"lattice/paper/LATTICE_",
                                filesuffix,".eps",sep=""))
  
}

MakeLatticePlotSelectionMethod <- function(Dataset, title="",
                                           filesuffix="",
                                           datatype=1,
                                           color=FALSE) {
  
  if(datatype==1) {
    datapath <- RealDatafilePath
    label <- "REAL"
  }
  if(datatype==0) {
    datapath <- SynDatafilePath
    label <- "SYN"
  }

  if(color==FALSE) {
    lattice.options(default.theme = canonical.theme(color=FALSE))
  }
  
  X11(height=latticeheight, width=latticewidth)
  
  if(color==FALSE) {
    strip.background <- trellis.par.get("strip.background")
    trellis.par.set(strip.background=list(col=grey(7:1/8)))
    plot.symbol <- trellis.par.get("plot.symbol")
    trellis.par.set(plot.symbol=list(col=grey(5/8)))
  
    print(xyplot(fitness ~ runtime | dataset, 
		     data=Dataset, 
		     groups=selection_method,
		     layout=c(4,2),
		     par.settings=(list(superpose.symbol=
                                        list(pch=c(0,1,2,3,4,5,6,8,9,10),
                                             cex=0.5,col=
                                             c("grey5","grey15","grey25",
                                               "grey35","grey45","grey55",
                                               "grey65","grey75","grey85",
                                               "grey95"))
                                        )),
		     auto.key = list(columns=5, lines=FALSE),
		     xlab="Execution Time (in ms)",
		     ylab="Coverage Effectiveness",
		     main=title	
		      ))
  }

  else {
    
    print(xyplot(fitness ~ runtime | dataset, 
		     data=Dataset, 
		     groups=selection_method,
		     layout=c(4,2),
		     par.settings=(list(superpose.symbol=
                                        list(pch=c(0,1,2,3,4,5,6,8,9,10),
                                             cex=0.5,col=
                                             c("black","red","orange",
                                               "yellow","green","blue",
                                               "purple","grey","aquamarine",
                                               "pink"))
                                        )),
		     auto.key = list(columns=5, lines=FALSE),
		     xlab="Execution Time (in ms)",
		     ylab="Coverage Effectiveness",
		     main=title	
		      ))
  }		      
  
  SaveGraphicLatticeWeb(paste(datapath,"lattice/web/LATTICE_",
                              filesuffix,".png",sep=""))
  SaveGraphicLatticePaper(paste(datapath,"lattice/paper/LATTICE_",
                                filesuffix,".eps",sep=""))
  
}

MakeLatticePlotMaxStagnancy <- function(Dataset, title="",
                                        filesuffix="", datatype=1,
                                        color=FALSE) {
  
  if(datatype==1) {
    datapath <- RealDatafilePath
    label <- "REAL"
  }
  if(datatype==0) {
    datapath <- SynDatafilePath
    label <- "SYN"
  }

  if(color==FALSE) {
    lattice.options(default.theme = canonical.theme(color=FALSE))
  }
  
  X11(height=latticeheight, width=latticewidth)
  
  if(color==FALSE) {
    strip.background <- trellis.par.get("strip.background")
    trellis.par.set(strip.background=list(col=grey(7:1/8)))
    plot.symbol <- trellis.par.get("plot.symbol")
    trellis.par.set(plot.symbol=list(col=grey(5/8)))
  
    print(xyplot(fitness ~ runtime | dataset, 
		     data=Dataset, 
		     groups=max_stagnancy,
		     layout=c(4,2),
		     par.settings=(list(superpose.symbol=
                                        list(pch=c(0,1,2,3),
                                             cex=0.5,col=
                                             c("grey15","grey35",
                                               "grey55","grey75"))
                                        )),
		     auto.key = list(columns=2, lines=FALSE),
		     xlab="Execution Time (in ms)",
		     ylab="Coverage Effectiveness",
		     main=title	
		      ))
  }

  else {
    
    print(xyplot(fitness ~ runtime | dataset, 
		     data=Dataset, 
		     groups=max_stagnancy,
		     layout=c(4,2),
		     par.settings=(list(superpose.symbol=
                                        list(pch=c(0,1,2,3),
                                             cex=0.5,col=
                                             c("black","red",
                                               "green","blue")))),
		     auto.key = list(columns=2, lines=FALSE),
		     xlab="Execution Time (in ms)",
		     ylab="Coverage Effectiveness",
		     main=title	
		      ))
  }		      
  
  SaveGraphicLatticeWeb(paste(datapath,"lattice/web/LATTICE_",
                              filesuffix,".png",sep=""))
  SaveGraphicLatticePaper(paste(datapath,"lattice/paper/LATTICE_",
                                filesuffix,".eps",sep=""))
  
}

MakeLatticePlotMutationOperator <- function(Dataset, title="",
                                            filesuffix="",
                                            datatype=1,
                                            color=FALSE) {
  
  if(datatype==1) {
    datapath <- RealDatafilePath
    label <- "REAL"
  }
  if(datatype==0) {
    datapath <- SynDatafilePath
    label <- "SYN"
  }

  if(color==FALSE) {
    lattice.options(default.theme = canonical.theme(color=FALSE))
  }
  
  X11(height=latticeheight, width=latticewidth)
  
  if(color==FALSE) {
    strip.background <- trellis.par.get("strip.background")
    trellis.par.set(strip.background=list(col=grey(7:1/8)))
    plot.symbol <- trellis.par.get("plot.symbol")
    trellis.par.set(plot.symbol=list(col=grey(5/8)))
  
    print(xyplot(fitness ~ runtime | dataset, 
		     data=Dataset, 
		     groups=mutation_operator,
		     layout=c(4,2),
		     par.settings=(list(superpose.symbol=
                                        list(pch=c(0,1,2,3,4,5,6,
                                               8),cex=0.5,col=
                                             c("grey5","grey15",
                                               "grey25","grey35",
                                               "grey45","grey55",
                                               "grey65","grey75"))
                                        )),
                 auto.key = list(columns=4, lines=FALSE),
                 
                 par.strip.text = list(cex=.7),
                 scales = list(x=list(cex=.7),
                   ylist=(cex=.7)),
                 
		     xlab="Execution Time (in ms)",
		     ylab="Coverage Effectiveness",
		     main=title	
		      ))
  }

  else {
    
    print(xyplot(fitness ~ runtime | dataset, 
		     data=Dataset, 
		     groups=mutation_operator,
		     layout=c(4,2),
		     par.settings=(list(superpose.symbol=
                                        list(pch=c(0,1,2,3,4,5,6,
                                               8),cex=0.5,col=
                                             c("black","red",
                                               "orange","yellow",
                                               "green","blue",
                                               "purple","grey"))
                                        )),
		     auto.key = list(columns=4, lines=FALSE),
		     xlab="Execution Time (in ms)",
		     ylab="Coverage Effectiveness",
		     main=title	
		      ))
  }		      
  
  SaveGraphicLatticeWeb(paste(datapath,"lattice/web/LATTICE_",
                              filesuffix,".png",sep=""))
  SaveGraphicLatticePaper(paste(datapath,"lattice/paper/LATTICE_",
                                filesuffix,".eps",sep=""))
  
}




# These function modify a dataset by taking the
# random_search_fitness and 
# random_search_runtime columns and copying them onto the fitness
# and 
# runtime columns to prepare for being plotted to a lattice. Be
# warned, this
# will double the size of the datafile.
MakeLatticePlotCrossoverOperatorRS <- function(Dataset,
                                               title=
                                               "Crossover Operators for Each Case Study Application",
                                               filesuffix=
                                               "_CROSSOVER_OPERATOR",
                                               datatype=1,
                                               color=FALSE) {
    
  rndData <- Dataset
  
  # this isn't true, but keeping the same names in the dataset is
  # necessary for rbind
  names(rndData)[16] <- "random_search_fitness"
  names(rndData)[17] <- "random_search_runtime"
  
  names(rndData)[24] <- "fitness"
  names(rndData)[25] <- "runtime"
  
  rndData$crossover_operator <- "Control (RS)"
    
  rndData <- rbind(rndData, Dataset)
  
  MakeLatticePlotCrossoverOperator(rndData, title, filesuffix,
                                   datatype, color)
  
}

MakeLatticePlotMutationOperatorRS <- function(Dataset,
                                              title=
                                              "Mutation Operators for Each Case Study Application",
                                              filesuffix=
                                              "_MUTATION_OPERATOR",
                                              datatype=1,
                                              color=FALSE) {
    
  rndData <- Dataset
  
  # this isn't true, but keeping the same names in the dataset is
  # necessary for rbind
  names(rndData)[16] <- "random_search_fitness"
  names(rndData)[17] <- "random_search_runtime"
  
  names(rndData)[24] <- "fitness"
  names(rndData)[25] <- "runtime"
  
  rndData$mutation_operator <- "Control (RS)"
      
  rndData <- rbind(rndData, Dataset)
  
  MakeLatticePlotMutationOperator(rndData, title, filesuffix, datatype, color)
  
}

MakeLatticePlotSelectionMethodRS <- function(Dataset,
                                             title=
                                             "Selection Methods for Each Case Study Application",
                                             filesuffix=
                                             "_SELECTION_METHOD",
                                             datatype=1,
                                             color=FALSE) {
  
  rndData <- Dataset
  
  # this isn't true, but keeping the same names in the dataset is
  # necessary for rbind
  names(rndData)[16] <- "random_search_fitness"
  names(rndData)[17] <- "random_search_runtime"
  
  names(rndData)[24] <- "fitness"
  names(rndData)[25] <- "runtime"
  
  rndData$selection_method <- "Control (RS)"
      
  rndData <- rbind(rndData, Dataset)
  
  MakeLatticePlotSelectionMethod(rndData, title, filesuffix,
                                 datatype, color)
  
}


#MakeLatticePlotFitnessTransformRS <- function(Dataset,
# title="Fitness Transformations for Each Case Study Application",
# filesuffix="_FITNESS_TRANSFORM", datatype=1, color=FALSE) {
#    
#  rndData <- Dataset
#  
#  # this isn't true, but keeping the same names in the dataset
#  # is necessary for rbind
#  names(rndData)[16] <- "random_search_fitness"
#  names(rndData)[17] <- "random_search_runtime"
#  
#  names(rndData)[24] <- "fitness"
#  names(rndData)[25] <- "runtime"
#  
#  rndData$fitness_transform <- "Control (RS)"
#      
#  rndData <- rbind(rndData, Dataset)
#  
#  MakeLatticePlot(rndData, title, filesuffix, datatype, color)
#  
#}

#MakeLatticePlotMutationRateRS <- function(Dataset,
# title="Mutation Rates for Each Case Study Application",
# filesuffix="_MUTATION_RATE", datatype=1, color=FALSE) {
#    
#  rndData <- Dataset
#  
#  # this isn't true, but keeping the same names in the dataset
#  # is necessary for rbind
#  names(rndData)[16] <- "random_search_fitness"
#  names(rndData)[17] <- "random_search_runtime"
#  
#  names(rndData)[24] <- "fitness"
#  names(rndData)[25] <- "runtime"
#  
#  rndData$mutation_rate <- "Control (RS)"
#      
#  rndData <- rbind(rndData, Dataset)
#  
#  MakeLatticePlot(rndData, title, filesuffix, datatype, color)
#  
#}

#MakeLatticePlotChildDensityRS <- function(Dataset,
# title="Child Densities for Each Case Study Application",
# filesuffix="_CHILD_DENSITY", datatype=1, color=FALSE) {
#    
#  rndData <- Dataset
#  
#  # this isn't true, but keeping the same names in the dataset
#  # is necessary for rbind
#  names(rndData)[16] <- "random_search_fitness"
#  names(rndData)[17] <- "random_search_runtime"
#  
#  names(rndData)[24] <- "fitness"
#  names(rndData)[25] <- "runtime"
#  
#  rndData$child_density <- "Control (RS)"
#      
#  rndData <- rbind(rndData, Dataset)
#  
#  MakeLatticePlot(rndData, title, filesuffix, datatype, color)
#  
#}

#MakeLatticePlotPopSizeRS <- function(Dataset,
# title="Population Sizes for Each Case Study Application",
# filesuffix="_POP_SIZE", datatype=1, color=FALSE) {
#    
#  rndData <- Dataset
#  
#  # this isn't true, but keeping the same names in the dataset
#  # is necessary for rbind
#  names(rndData)[16] <- "random_search_fitness"
#  names(rndData)[17] <- "random_search_runtime"
#  
#  names(rndData)[24] <- "fitness"
#  names(rndData)[25] <- "runtime"
#  
#  rndData$pop_size <- "Control (RS)"
#      
#  rndData <- rbind(rndData, Dataset)
#  
#  MakeLatticePlot(rndData, title, filesuffix, datatype, color)
#  
#}


MakeLatticePlotMaxStagnancyRS <- function(Dataset,
                                          title=
                                          "Maximum Stagnancies for Each Case Study Application",
                                          filesuffix=
                                          "_MAX_STAGNANCY",
                                          datatype=1,
                                          color=FALSE) {
    
  rndData <- Dataset
  
  # this isn't true, but keeping the same names in the dataset
  # is necessary for rbind
  names(rndData)[16] <- "random_search_fitness"
  names(rndData)[17] <- "random_search_runtime"
  
  names(rndData)[24] <- "fitness"
  names(rndData)[25] <- "runtime"
  
  rndData$max_stagnancy <- "0 Control (RS)"
      
  rndData <- rbind(rndData, Dataset)
  
  MakeLatticePlotMaxStagnancy(rndData, title, filesuffix,
                              datatype, color)
  
}


# These function modify a dataset by taking the
# random_search_fitness and 
# random_search_runtime columns and copying them onto the fitness
# and 
# runtime columns to prepare for being plotted to a lattice. Be
# warned, this
# will double the size of the datafile. NOTE: this function plots
# a single
# lattice box only; useful for expanding a single data set.
MakeLatticePlotCrossoverOperatorSingleRS <- function(Dataset,
                                                     title=
                                                     "Crossover Operators for A Single Case Study Application",
                                                     filesuffix=
                                                     "_CROSSOVER_OPERATOR",
                                                     datatype=1,
                                                     color=FALSE) {
    
  rndData <- Dataset
  
  # this isn't true, but keeping the same names in the dataset
  # is necessary for rbind
  names(rndData)[16] <- "random_search_fitness"
  names(rndData)[17] <- "random_search_runtime"
  
  names(rndData)[24] <- "fitness"
  names(rndData)[25] <- "runtime"
  
  rndData$crossover_operator <- "Control (RS)"
    
  rndData <- rbind(rndData, Dataset)
  
  MakeLatticePlotCrossoverOperatorSingle(rndData, title,
                                         filesuffix, datatype,
                                         color)
  
}

MakeLatticePlotBestSingleRS <- function(Dataset,
                                        title="Best Configuration",
                                        filesuffix="BEST",
                                        datatype=1, color=FALSE) {
    
  rndData <- Dataset
  
  # this isn't true, but keeping the same names in the dataset
  # is necessary for rbind
  names(rndData)[16] <- "random_search_fitness"
  names(rndData)[17] <- "random_search_runtime"
  
  names(rndData)[24] <- "fitness"
  names(rndData)[25] <- "runtime"
  
  rndData$selection_method <- "Control (RS)"
    
  rndData <- rbind(rndData, Dataset)
  
  MakeLatticePlotBestSingle(rndData, title, filesuffix, datatype,
                            color)
  
}

MakeLatticePlotBestRS <- function(Dataset,
                                  title="Best Configuration",
                                  filesuffix="BEST", datatype=1,
                                  color=FALSE) {
    
  rndData <- Dataset
  
  # this isn't true, but keeping the same names in the dataset
  # is necessary for rbind
  names(rndData)[16] <- "random_search_fitness"
  names(rndData)[17] <- "random_search_runtime"
  
  names(rndData)[24] <- "fitness"
  names(rndData)[25] <- "runtime"
  
  rndData$selection_method<- "Control (RS)"
    
  rndData <- rbind(rndData, Dataset)
  
  MakeLatticePlotBest(rndData, title, filesuffix, datatype, color)
  
}

MakeLatticePlotBest <- function(Dataset, title="", filesuffix="",
                                datatype=1, color=FALSE) {
  
  if(datatype==1) {
    datapath <- RealDatafilePath
    label <- "REAL"
  }
  if(datatype==0) {
    datapath <- SynDatafilePath
    label <- "SYN"
  }

  if(color==FALSE) {
    lattice.options(default.theme = canonical.theme(color=FALSE))
  }
  
  X11(height=latticeheight, width=latticewidth)
  
  if(color==FALSE) {
    strip.background <- trellis.par.get("strip.background")
    trellis.par.set(strip.background=list(col=grey(7:1/8)))
    plot.symbol <- trellis.par.get("plot.symbol")
    trellis.par.set(plot.symbol=list(col=grey(5/8)))
  
    print(xyplot(fitness ~ runtime | dataset, 
		     data=Dataset, 
		     groups=selection_method,
		     layout=c(4,2),
		     par.settings=(list(superpose.symbol=
                                        list(pch=c(0,8),cex=0.5,
                                             col=c("grey5",
                                               "grey65")))),
		     auto.key = list(columns=2, lines=FALSE),

                     par.strip.text = list(cex=.7),
                     scales = list(x=list(cex=.7), ylist=(cex=.7)),
                     
		     xlab="Execution Time (in ms)",
		     ylab="Coverage Effectiveness",
		     main=title	
		      ))
  }

  else {
    
    print(xyplot(fitness ~ runtime | dataset, 
		     data=Dataset, 
		     groups=selection_method,
		     layout=c(4,2),
		     par.settings=(list(superpose.symbol=
                                        list(pch=c(0,8),cex=0.5,
                                             col=c("red","green")))),
		     auto.key = list(columns=2, lines=FALSE),
		     xlab="Execution Time (in ms)",
		     ylab="Coverage Effectiveness",
		     main=title	
		      ))
  }		      
  
  SaveGraphicLatticeWeb(paste(datapath,"lattice/web/LATTICE_",
                              filesuffix,".png",sep=""))
  SaveGraphicLatticePaper(paste(datapath,"lattice/paper/LATTICE_",
                                filesuffix,".eps",sep=""))
  
}

MakeLatticePlotBestSingle <- function(Dataset, title="",
                                      filesuffix="",
                                      datatype=1, color=FALSE) {
  
  if(datatype==1) {
    datapath <- RealDatafilePath
    label <- "REAL"
  }
  if(datatype==0) {
    datapath <- SynDatafilePath
    label <- "SYN"
  }

  if(color==FALSE) {
    lattice.options(default.theme = canonical.theme(color=FALSE))
  }
  
  X11(height=latticeheight, width=latticewidth)
  
  if(color==FALSE) {
    strip.background <- trellis.par.get("strip.background")
    trellis.par.set(strip.background=list(col=grey(7:1/8)))
    plot.symbol <- trellis.par.get("plot.symbol")
    trellis.par.set(plot.symbol=list(col=grey(5/8)))
  
    print(xyplot(fitness ~ runtime | dataset, 
		     data=Dataset, 
		     groups=selection_method,
		     layout=c(1,1),
		     par.settings=(list(superpose.symbol=
                                        list(pch=c(0,8),cex=0.5,
                                             col=c("grey5",
                                               "grey65")))),
		     auto.key = list(columns=2, lines=FALSE),
		     xlab="Execution Time (in ms)",
		     ylab="Coverage Effectiveness",
		     main=title	
		      ))
  }

  else {
    
    print(xyplot(fitness ~ runtime | dataset, 
		     data=Dataset, 
		     groups=selection_method,
		     layout=c(1,1),
		     par.settings=(list(superpose.symbol=
                                        list(pch=c(0,8),cex=0.5))),
		     auto.key = list(columns=2, lines=FALSE),
		     xlab="Execution Time (in ms)",
		     ylab="Coverage Effectiveness",
		     main=title	
		      ))
  }		      
  
  SaveGraphicLatticeWeb(paste(datapath,
                              "lattice/web/LATTICE_SINGLE_",
                              filesuffix,".png",sep=""))
  SaveGraphicLatticePaper(paste(datapath,
                                "lattice/paper/LATTICE_SINGLE_",
                                filesuffix,".eps",sep=""))
  
}

# Note: this plot will be very dense: it is highly recommended
# that the 
# dataset be reduced before trying to graph as a lattice plot.
# NOTE: this 
# function plots a single lattice box only; useful for expanding
# a single data set.
MakeLatticePlotCrossoverOperatorSingle <- function(Dataset,
                                                   title="",
                                                   filesuffix="",
                                                   datatype=1,
                                                   color=FALSE) {
  
  if(datatype==1) {
    datapath <- RealDatafilePath
    label <- "REAL"
  }
  if(datatype==0) {
    datapath <- SynDatafilePath
    label <- "SYN"
  }

  if(color==FALSE) {
    lattice.options(default.theme = canonical.theme(color=FALSE))
  }
  
  X11(height=latticeheight, width=latticewidth)
  
  if(color==FALSE) {
    strip.background <- trellis.par.get("strip.background")
    trellis.par.set(strip.background=list(col=grey(7:1/8)))
    plot.symbol <- trellis.par.get("plot.symbol")
    trellis.par.set(plot.symbol=list(col=grey(5/8)))
  
    print(xyplot(fitness ~ runtime | dataset, 
		     data=Dataset, 
		     groups=crossover_operator,
		     layout=c(1,1),
		     par.settings=(list(superpose.symbol=
                                        list(pch=c(0,1,2,3,4,5,6,
                                               8),cex=0.5,col=
                                             c("grey5","grey15",
                                               "grey25","grey35",
                                               "grey45","grey55",
                                               "grey65","grey75"))
                                        )),
		     auto.key = list(columns=4, lines=FALSE),
		     xlab="Execution Time (in ms)",
		     ylab="Coverage Effectiveness",
		     main=title	
		      ))
  }

  else {
    
    print(xyplot(fitness ~ runtime | dataset, 
		     data=Dataset, 
		     groups=crossover_operator,
		     layout=c(1,1),
		     par.settings=(list(superpose.symbol=
                                        list(pch=c(0,1,2,3,4,5,6,
                                               8),cex=0.5))),
		     auto.key = list(columns=4, lines=FALSE),
		     xlab="Execution Time (in ms)",
		     ylab="Coverage Effectiveness",
		     main=title	
		      ))
  }		      
  
  SaveGraphicLatticeWeb(paste(datapath,
                              "lattice/web/LATTICE_SINGLE_",
                              filesuffix,".png",sep=""))
  SaveGraphicLatticePaper(paste(datapath,
                                "lattice/paper/LATTICE_SINGLE_",
                                filesuffix,".eps",sep=""))
  
}

# This function compares the impact that each of the variables
# has on both 
# execution time and fitness of the regression trees, and returns
# the 
# difference of the %IncMSE for fitness - %IncMSE for runtime for
# each of the 
# variables.
# (not yet implemented)


# This function compares the impact that each of the variables has
# on both 
# execution time and fitness of the regression trees, and returns
# the 
# difference of the IncNodePurity for fitness - IncNodePurity for
# runtime for 
# each of the variables.
# (not yet implemented)


MakeAllLatticePlotsColor <- function() {
  
  #print(SubsetChildDensity(RealData,1.0))
  
  for (s in 1:(length(selection_methods)-1)) {
    #print(selection_methods[s])
    
    for (f in 1:(length(fitness_transforms)-1)) {
      #print(fitness_transforms[f])

      for (p in 1:(length(pop_sizes)-1)) {
        #print(pop_sizes[p])

        for (m in 1:(length(max_stagnancys)-1)) {
	#print(max_stagnancys[m])

          for (c in 1:(length(child_densitys)-1)) {
          #print(child_densitys[c])
            
            print(paste("sel:",selection_methods[s]," fit:",
                        fitness_transforms[f]," pop:",
                        pop_sizes[p]," stag:",max_stagnancys[m],
                        " child:",child_densitys[c],sep=""))
            #print(SubsetAll(RealData,
            #                selop=selection_methods[s],
            #                fitop=fitness_transforms[f],
            #                popsize=pop_sizes[p],
            #                maxstag=max_stagnancys[m],
            #                childrep=child_densitys[c]
            #                )$child_density)
            #print(paste("sel:",selection_methods[s]," fit:",
            # fitness_transforms[f]," pop:",pop_sizes[p]," stag:",
            # max_stagnancys[m],sep=""))
            
            MakeLatticePlotCrossoverOperatorRS(SubsetAll(RealData,
                                                         selop=
                                                         selection_methods[s],
                                                         fitop=
                                                         fitness_transforms[f],
                                                         popsize=
                                                         pop_sizes[p],
                                                         maxstag=
                                                         max_stagnancys[m],
                                                         childrep=
                                                         child_densitys[c]
                                                         ),
                                               title=paste(
                                                 "selop=",
                                                 selection_methods[s],
                                                 ", fitop=",
                                                 fitness_transforms[f],
                                                 ", popsize=",
                                                 pop_sizes[p],
                                                 ", maxstag=",
                                                 max_stagnancys[m],
                                                 ", childrep=",
                                                 child_densitys[c],
                                                 sep=""),
                                               #title="test",
                                               filesuffix=paste(
                                                 "selop.",
                                                 selection_methods[s],
                                                 "_fitop.",
                                                 fitness_transforms[f],
                                                 "_popsize.",
                                                 pop_sizes[p],
                                                 "_maxstag.",
                                                 max_stagnancys[m],
                                                 "_childrep.",
                                                 child_densitys[c],
                                                 "_COLOR",
                                                 sep=""),
                                               color=TRUE
                                               #filesuffix="test"
                                               )

            graphics.off()

          }
        }
      }
    }
  }
  
}

###########################################################
### predictors ###
### NOTE: this section is UNFINISHED! ###
### NOTE: some source code in this section was written by
###  Prof. G.M. Kapfhammer

MakeFitnessSVM <- function(Dataset,
                           title=
                           "Predictions for Coverage Effectiveness by Support Vector Machine",
                           filesuffix="CE_SVN", datatype=1) {

  # if predicting for real dataset, set appropriate path for output
  if(datatype==1) {
    datapath <- RealDatafilePath
    label <- "REAL"
  }
  # if predicting for syn dataset, set appropriate path for output
  if(datatype==0) {
    datapath <- SynDatafilePath
    label <- "SYN"
  }

  # remove non-parameter attributes from dataset
  # (ie, runtime, datatype, order, init_order, init_reverse_order,
  # random_search_order, random_search_fitness, dataset,
  # init_fitness, init_reverse_fitness, generations,
  # random_search_runtime, rep)
  masterData <- SubsetDataForFitnessTree(Dataset)

  print("making SVM...")
  SvmModel <- svm(fitness~., data=masterData, cost=10, gamma=1.5)

  print(SvmModel)
  summary(SvmModel)

  return(SvmModel)

}

MakeRuntimeSVM <- function(Dataset,
                           title=
                           "Predictions for Execution Time by Support Vector Machine",
                           filesuffix="RUNTIME_SVN", datatype=1) {

  # if predicting for real dataset, set appropriate path for output
  if(datatype==1) {
    datapath <- RealDatafilePath
    label <- "REAL"
  }
  # if predicting for syn dataset, set appropriate path for output
  if(datatype==0) {
    datapath <- SynDatafilePath
    label <- "SYN"
  }

  masterData <- SubsetDataForTimeTree(Dataset)

  print("making SVM...")
  SvmModel <- svm(runtime~., data=masterData, cost=10, gamma=1.5)

  print(SvmModel)
  summary(SvmModel)

  return(SvmModel)

}

MakeFitnessBagging <- function(Dataset,
                               title=
                               "Predictions for Coverage Effectiveness by Bagging Modeling",
                               filesuffix="CE_BAG", datatype=1) {
  
  datapath <- SetDatapath(datatype)
  label <- SetLabel(datatype)

  masterData <- SubsetDataForFitnessTree(Dataset)

  print("making Bagging model...")
  BaggingModel <- bagging(fitness~., data=masterData, coob=TRUE)

  print(BaggingModel)
  
  return(BaggingModel)
  
}

MakeRuntimeBagging <- function(Dataset,
                               title=
                               "Predictions for Execution Time by Bagging Modeling",
                               filesuffix="RUNTIME_BAG",
                               datatype=1) {
  
  datapath <- SetDatapath(datatype)
  label <- SetLabel(datatype)
  
  masterData <- SubsetDataForTimeTree(Dataset)
  
  print("making Bagging model...")
  BaggingModel <- bagging(runtime~., data=masterData, coob=TRUE)

  print(BaggingModel)
  
  return(BaggingModel)
  
}

MakeFitnessBoosting <- function(Dataset,
                                title=
                                "Predictions for Coverage Effectiveness by Boosting Modeling",
                                filesuffix="CE_BOOST",
                                datatype=1) {
  
  datapath <- SetDatapath(datatype)
  label <- SetLabel(datatype)
  
  masterData <- SubsetDataForFitnessTree(Dataset)
  
  print("making Bagging model...")
  BoostingModel = gbm(fitness~., data=masterData,
    distribution="gaussian",    # bernoulli, adaboost, gaussian,
    # poisson, coxph, and quantile available
          n.trees=1000,                # number of trees
          shrinkage=0.1,             # shrinkage or learning rate,
                                       # 0.001 to 0.1 usually work
          interaction.depth=1,
                                        # 1: additive model,
                                  # 2: two-way interactions, etc.
          bag.fraction = 0.5,           # subsampling fraction,
                                        # 0.5 is probably best
    train.fraction = 0.5,        # fraction of data for training,
                        # first train.fraction*N used for training
    n.minobsinnode = 2, # minimum total weight needed in each node
    cv.folds = 0,                # do 5-fold cross-validation
    keep.data=TRUE,   # keep a copy of the dataset with the object
    verbose=TRUE)                # print out progress

  print(BoostingModel)

  return(BoostingModel)
  
}

MakeRuntimeBoosting <- function(Dataset,
                                title=
                                "Predictions for Execution Time by Boosting Modeling",
                                filesuffix="RUNTIME_BOOST",
                                datatype=1) {
  
  datapath <- SetDatapath(datatype)
  label <- SetLabel(datatype)
  
  masterData <- SubsetDataForTimeTree(Dataset)
  
  print("making Bagging model...")
  BoostingModel = gbm(runtime~., data=masterData,
          distribution="gaussian",# bernoulli, adaboost, gaussian,
                          # poisson, coxph, and quantile available
          n.trees=1000,                # number of trees
          shrinkage=0.1,             # shrinkage or learning rate,
                                       # 0.001 to 0.1 usually work
          interaction.depth=1,
                                        # 1: additive model,
                                   # 2: two-way interactions, etc.
          bag.fraction = 0.5,           # subsampling fraction,
                                        # 0.5 is probably best
    train.fraction = 0.5,        # fraction of data for training,
                        # first train.fraction*N used for training
    n.minobsinnode = 2, # minimum total weight needed in each node
    cv.folds = 0,                # do 5-fold cross-validation
    keep.data=TRUE,   # keep a copy of the dataset with the object
    verbose=TRUE)                # print out progress

  print(BoostingModel)

  return(BoostingModel)
  
}

# Error function from the computeIndividualError function
# by GMK in KapfhammerAnalyzeResults.R.
ErrorFunction <- function(Actual, Predicted) {
  
  return(abs(Actual-Predicted)/Predicted)
  
}


CreateHoldoutDataset <- function(DataSet, HoldoutApp) {
  
  return(subset(DataSet, dataset != HoldoutApp))
  
}


PredictAndGatherData <- function(DataSet, HoldoutDataSet,
                                 DataModel) {
  
  Prediction <- predict(DataModel, HoldoutDataSet)
  GatheredData <- data.frame(crossover_operator,
                             mutation_operator, selection_method,
                             fitness_transform, mutation_rate,
                             child_density, pop_size,
                             max_stagnancy, fitness, Prediction)

  return(GatheredData)
  
}

# Dummy function for performing k-fold cross-validation using the
# mean
# of the entries rather than a more sophisticated learner. Based
# upon
# createMeanPredictorCE from KapfhammerAnalyzeResults.R
CreateMeanPredictorFitness <- function(TestingResults,
                                       TreeLabel = "I am a tree!",
                                       Visualize = TRUE) {
  
  actualMean <- mean(TestingResults$CE)
  predictionMeanCE <- function() {
    return(actualMean)
  }
  
  return(predictionMeanCE)
  
}

# Performs a k-fold cross validation for the predictions generated
# by the
# regression tree, random forest, bagging, and support vector
# machine
# predictors. Based upon PerformKFoldCrossValidationForCE from
# KapfhammerAnalyzeResults.R
PerformKFoldCrossValidationForFitness <- function(MasterData) {
  
  Predictor <- c()
  Error <- c()
  DataSet <- SubsetDataForFitnessTree(MasterData)
  
#  # "predict" with average
#  setClass("MeanPredictor", contains="numeric")
#  MP = new("MeanPredictor")
#  
#  error.AV <- numeric(10)
#
#  AV.predict <- function(object, newdata) {
#    
#    specialMeanPredictor = createMeanPredictorFitness(newdata)
#    return(specialMeanPredictor)
#      
#  }
#
#  print("'predicting' with average")
#  for (i in 1:10) {
#    error.AV[i] <- errorest(fitness~.,
#			    data = DataSet,
#			    model = MP,
#			    predict = AV.predict)
#  }
#  
#  PredictorName <- "AV"
#  PredictorNameRep <- rep(PredictorName, length(error.AV))
#  Predictor <- c(Predictor, PredictorNameRep)
#  Error <- c(Error, error.AV)
  
  # predict with regression tree
  error.TR <- numeric(10)
  for (i in 1:10) {
    print(paste("  begin tree: ",i," of 10"))
    error.TR[i] <- errorest(fitness~.,
                            data = DataSet,
                            model = tree,
                            est.para = control.errorest(
                              random=TRUE))$error
    #print(paste("  finish tree: ",i," of 10"))
  }

  print("Results for Tree:")
  print(error.TR)

  PredictorName <- "TR"
  PredictorNameRep <- rep(PredictorName, length(error.TR))
  Predictor <- c(Predictor, PredictorNameRep)
  Error <- c(Error, error.TR)

  
  # predict with random forest
  error.RF <- numeric(10)
  for (i in 1:10) {
    print(paste("  begin forest: ",i," of 10"))
    error.RF[i] <- errorest(fitness~.,
                            data = DataSet,
                            model = randomForest,
			    ntree = 42)$error
    #print(paste("  finish forest: ",i," of 10"))
  }

  print("Results for Forest:")
  print(error.RF)

  PredictorName <- "RF"
  PredictorNameRep <- rep(PredictorName, length(error.RF))
  Predictor <- c(Predictor, PredictorNameRep)
  Error <- c(Error, error.RF)

  
  # predict with bagging
  error.BG <- numeric(10)
  for (i in 1:10) {
    print(paste("  begin bag: ",i," of 10"))
    error.BG[i] <- errorest(fitness~.,
                            data = DataSet,
                            model = bagging)$error
    #print(paste("  finish bag: ",i," of 10"))
  }

  print("Results for Bagging:")
  print(error.BG)

  PredictorName <- "BG"
  PredictorNameRep <- rep(PredictorName, length(error.BG))
  Predictor <- c(Predictor, PredictorNameRep)
  Error <- c(Error, error.BG)
  
  
  # predict with support vector machine
  error.SV <- numeric(10)
  for (i in 1:10) {
    print(paste("  begin svm: ",i," of 10"))
    error.SV[i] <- errorest(fitness~.,
                            data = DataSet,
                            model = svm)$error
    #print(paste("  finish svm: ",i," of 10"))
  }

  print("Results for SVM:")
  print(error.SV)

  PredictorName <- "SV"
  PredictorNameRep <- rep(PredictorName, length(error.SV))
  Predictor <- c(Predictor, PredictorNameRep)
  Error <- c(Error, error.SV)

  return(data.frame(Predictor, Error))
  
}

PerformKFoldFitnessEachApp <- function(MasterData, visualize=TRUE) {
  
  predictor <- c()
  error <- c()
  dataset <- c()
  
  for (i in 1:length(RealConfigs)-1) {
    
    AppData <- subset(MasterData, dataset == RealConfigs[i+1])
    print(RealConfigs[i+1])
    print(i+1) 
    AppPredictError <- PerformKFoldCrossValidationForFitnessTRRFBG(AppData)
    dataset <- c(dataset,rep(RealConfigs[i+1], 40))
    error <- c(error,AppPredictError$Error)
    predictor <- c(predictor,AppPredictError$Predictor)
    
  }
  
  Predictions <- data.frame(predictor, error, dataset)
  
  if (visualize) {
    
    LatticeKFoldValidationErrorPlot(Predictions,
                                    filesuffix="eachapp",
                                    title=
                                    "K-Fold Cross Validation Error Ranges for Each Machine Learning Algorithm")
    
  }
  
  return(Predictions)
  
}



PerformKFoldCrossValidationForFitnessTRRFBG <- function(MasterData) {
  
  Predictor <- c()
  Error <- c()
  DataSet <- SubsetDataForFitnessTree(MasterData)
  
  # predict with regression tree
  error.TR <- numeric(10)
  for (i in 1:10) {
    print(paste("  begin tree: ",i," of 10"))
    error.TR[i] <- errorest(fitness~.,
                            data = DataSet,
                            model = tree,
                            est.para =
                            control.errorest(random=TRUE))$error
    print(paste("  finish tree: ",i," of 10"))
  }

  print("Results for Tree:")
  print(error.TR)

  PredictorName <- "TR"
  PredictorNameRep <- rep(PredictorName, length(error.TR))
  Predictor <- c(Predictor, PredictorNameRep)
  Error <- c(Error, error.TR)

  
  # predict with random forest
  error.RF <- numeric(10)
  for (i in 1:10) {
    print(paste("  begin forest: ",i," of 10"))
    error.RF[i] <- errorest(fitness~.,
                            data = DataSet,
                            model = randomForest)$error
    print(paste("  finish forest: ",i," of 10"))
  }
  
  print("Results for Forest:")
  print(error.RF)
  
  PredictorName <- "RF"
  PredictorNameRep <- rep(PredictorName, length(error.RF))
  Predictor <- c(Predictor, PredictorNameRep)
  Error <- c(Error, error.RF)

  
  # predict with bagging
  error.BG <- numeric(10)
  for (i in 1:10) {
    print(paste("  begin bag: ",i," of 10"))
    error.BG[i] <- errorest(fitness~.,
                            data = DataSet,
                            model = bagging)$error
    print(paste("  finish bag: ",i," of 10"))
  }

  print("Results for Bagging:")
  print(error.BG)

  PredictorName <- "BG"
  PredictorNameRep <- rep(PredictorName, length(error.BG))
  Predictor <- c(Predictor, PredictorNameRep)
  Error <- c(Error, error.BG)
  
  
  # predict with support vector machine
  #error.SV <- numeric(10)
  #for (i in 1:10) {
  #  print(paste("  begin svm: ",i," of 10"))
  #  error.SV[i] <- errorest(fitness~.,
  #                          data = DataSet,
  #                          model = svm)$error
  #  print(paste("  finish svm: ",i," of 10"))
  #}
  #
  #print("Results for SVM:")
  #print(error.SV)
  #
  #PredictorName <- "SV"
  #PredictorNameRep <- rep(PredictorName, length(error.SV))
  #Predictor <- c(Predictor, PredictorNameRep)
  #Error <- c(Error, error.SV)

  return(data.frame(Predictor, Error))
  
}

PerformKFoldCrossValidationForFitnessTRBGSV <- function(MasterData) {
  
  Predictor <- c()
  Error <- c()
  DataSet <- SubsetDataForFitnessTree(MasterData)
  
  # predict with regression tree
  error.TR <- numeric(10)
  for (i in 1:10) {
    print(paste("  begin tree: ",i," of 10"))
    error.TR[i] <- errorest(fitness~.,
                            data = DataSet,
                            model = tree,
                            est.para =
                            control.errorest(random=TRUE))$error
    print(paste("  finish tree: ",i," of 10"))
  }

  print("Results for Tree:")
  print(error.TR)

  PredictorName <- "TR"
  PredictorNameRep <- rep(PredictorName, length(error.TR))
  Predictor <- c(Predictor, PredictorNameRep)
  Error <- c(Error, error.TR)

  
  ## predict with random forest
  #error.RF <- numeric(10)
  #for (i in 1:10) {
  #  print(paste("  begin forest: ",i," of 10"))
  #  error.RF[i] <- errorest(fitness~.,
  #                          data = DataSet,
  #                          model = randomForest)$error
  #  print(paste("  finish forest: ",i," of 10"))
  #}
  #
  #print("Results for Forest:")
  #print(error.RF)
  #
  #PredictorName <- "RF"
  #PredictorNameRep <- rep(PredictorName, length(error.RF))
  #Predictor <- c(Predictor, PredictorNameRep)
  #Error <- c(Error, error.RF)

  
  # predict with bagging
  error.BG <- numeric(10)
  for (i in 1:10) {
    print(paste("  begin bag: ",i," of 10"))
    error.BG[i] <- errorest(fitness~.,
                            data = DataSet,
                            model = bagging)$error
    print(paste("  finish bag: ",i," of 10"))
  }

  print("Results for Bagging:")
  print(error.BG)

  PredictorName <- "BG"
  PredictorNameRep <- rep(PredictorName, length(error.BG))
  Predictor <- c(Predictor, PredictorNameRep)
  Error <- c(Error, error.BG)
  
  
  # predict with support vector machine
  error.SV <- numeric(10)
  for (i in 1:10) {
    print(paste("  begin svm: ",i," of 10"))
    error.SV[i] <- errorest(fitness~.,
                            data = DataSet,
                            model = svm)$error
    print(paste("  finish svm: ",i," of 10"))
  }
  
  print("Results for SVM:")
  print(error.SV)
  
  PredictorName <- "SV"
  PredictorNameRep <- rep(PredictorName, length(error.SV))
  Predictor <- c(Predictor, PredictorNameRep)
  Error <- c(Error, error.SV)

  return(data.frame(Predictor, Error))
  
}

PerformKFoldCrossValidationForFitnessTR <- function(MasterData) {
  
  Predictor <- c()
  Error <- c()
  DataSet <- SubsetDataForFitnessTree(MasterData)
  
  # predict with regression tree
  error.TR <- numeric(10)
  for (i in 1:10) {
    print(paste("  begin tree: ",i," of 10"))
    error.TR[i] <- errorest(fitness~.,
                            data = DataSet,
                            model = tree,
                            est.para =
                            control.errorest(random=TRUE))$error
    print(paste("  finish tree: ",i," of 10"))
  }

  print("Results for Tree:")
  print(error.TR)

  PredictorName <- "TR"
  PredictorNameRep <- rep(PredictorName, length(error.TR))
  Predictor <- c(Predictor, PredictorNameRep)
  Error <- c(Error, error.TR)

  
  ## predict with random forest
  #error.RF <- numeric(10)
  #for (i in 1:10) {
  #  print(paste("  begin forest: ",i," of 10"))
  #  error.RF[i] <- errorest(fitness~.,
  #                          data = DataSet,
  #                          model = randomForest)$error
  #  print(paste("  finish forest: ",i," of 10"))
  #}
  #
  #print("Results for Forest:")
  #print(error.RF)
  #
  #PredictorName <- "RF"
  #PredictorNameRep <- rep(PredictorName, length(error.RF))
  #Predictor <- c(Predictor, PredictorNameRep)
  #Error <- c(Error, error.RF)

  
#  # predict with bagging
#  error.BG <- numeric(10)
#  for (i in 1:10) {
#    print(paste("  begin bag: ",i," of 10"))
#    error.BG[i] <- errorest(fitness~.,
#                            data = DataSet,
#                            model = bagging)$error
#    print(paste("  finish bag: ",i," of 10"))
#  }
#
#  print("Results for Bagging:")
#  print(error.BG)
#
#  PredictorName <- "BG"
#  PredictorNameRep <- rep(PredictorName, length(error.BG))
#  Predictor <- c(Predictor, PredictorNameRep)
#  Error <- c(Error, error.BG)
  
  
#  # predict with support vector machine
#  error.SV <- numeric(10)
#  for (i in 1:10) {
#    print(paste("  begin svm: ",i," of 10"))
#    error.SV[i] <- errorest(fitness~.,
#                            data = DataSet,
#                            model = svm)$error
#    print(paste("  finish svm: ",i," of 10"))
#  }
#  
#  print("Results for SVM:")
#  print(error.SV)
#  
#  PredictorName <- "SV"
#  PredictorNameRep <- rep(PredictorName, length(error.SV))
#  Predictor <- c(Predictor, PredictorNameRep)
#  Error <- c(Error, error.SV)

  return(data.frame(Predictor, Error))
  
}


BeanKFoldValidationErrorPlot <- function(DataSet) {
  
  X11(height = 6, width = 6)

  beanplot(Error~Predictor,
           data = DataSet,
           xlab = "Prediction Technique",
           ylab = "Prediction Error"
	   )
           
  
}


LatticeKFoldValidationErrorPlot <- function(DataSet,
                                            filesuffix="",
                                            title="", datatype=1) {
  
  datapath <- SetDatapath(datatype)
  label <- SetLabel(datatype)
  
  lattice.options(default.theme = canonical.theme(color=FALSE))
  X11(height = latticeheight, width = latticewidth)

  strip.background <- trellis.par.get("strip.background")
  trellis.par.set(strip.background = list(col = grey(7:1/8)))
  plot.symbol <- trellis.par.get("plot.symbol")
  trellis.par.set(plot.symbol = list(col = "black", cex = .2,
                    pch = 21))
  border <- trellis.par.get("border")
  trellis.par.set(border = list(col = "black"))

  ## reorder the datasets in order to generate predictable boxplots
  #print(levels(DataSet$Predictor))
  #DataSet$Predictor <- reorder.factor(DataSet$Predictor,
  # new.order = chosenLearnerOrdering)
  #print(levels(DataSet$Predictor))

  print(bwplot(error~predictor | dataset,
               data = DataSet,
               layout = c(4,2),
               cex = .4,
               pch = 3,
               col = "black",
               border = "black",
               border = par("fg"),
               names = c("f", "g"),
               par.settings = list(box.umbrella
                 = list(lwd = 0.5)),
               par.strip.text = list(cex = .7),
               scales = list(x = list(cex = .7), y
                 = list(cex = .7)),
               xlab = list("Prediction Technique", cex = .7),
               ylab = list("Prediction Error", cex = .7),
	       main = title
               ))

  par.settings = (list(cex = 0.5,
                       cex.axis = .5,
                       border = c(1)))
  
  SaveGraphicLatticeWeb(paste(datapath,
                              "lattice/web/KFOLD_LATTICE_",
                              filesuffix,".png",sep=""))
  SaveGraphicLatticePaper(paste(datapath,
                                "lattice/paper/KFOLD_LATTICE_",
                                filesuffix,".eps",sep=""))
  
}


###########################################################
### misc. methods ###

SetDatapath <- function(datatype) {
  # if predicting for real dataset, set appropriate path for output
  if(datatype==1) {
    return(RealDatafilePath)
  }
  # if predicting for syn dataset, set appropriate path for output
  else if(datatype==0) {
    return(SynDatafilePath)
  }
}

SetLabel <- function(datatype) {
  if(datatype==1) {
    return("REAL")
  }
  else if(datatype==0) {
    return("SYN")
  }
}

FindRandomSearchMeanRuntime <- function(Dataset) {
  
  rsmean <- mean(Dataset$random_search_runtime)
  return(rsmean)

}

FindRandomSearchMeanFitness <- function(Dataset) {
  
  rsmean <- mean(Dataset$random_search_fitness)
  return(rsmean)

}

FindMeanInitialFitness <- function(Dataset) {
  
  initmean <- mean(Dataset$initial_fitness)
  return(initmean)  
  
}

RemoveDataset <- function(MasterData, Dataset) {
  
  SubsetData <- subset(RealData, dataset != Dataset)
  
  return(SubsetData)
  
}

TTestGA <- function(MasterData) {

  t.test(MasterData$fitness)

}

TTestRS <- function(MasterData) {

  t.test(MasterData$random_search_fitness)

}

CombineTouAndOtherDataset <- function(RealData, RealDataTou) {

  MasterData <- rbind(RealData, RealDataTou)

  return(MasterData)

}

MakeGenerationsBeanPlotForSelection <- function(MasterData, title="Beanplot for generation count", filesuffix="gen",datatype=1) {

  if(datatype==1) {
    datapath <- RealDatafilePath
    label <- "REAL"
  }
  if(datatype==0) {
    datapath <- SynDatafilePath
    label <- "SYN"
  }
  
  X11(height=6, width=11)
  
  subset_selection_method <- c("ROUE","ROUW","TRU60","TRU40","TOU","TOU5")
  
  beanplot(generations ~ factor(selection_method, levels=c("ROUE","ROU","ROUW","TRU60","TRU","TRU40","TOU","TOU3","TOU4","TOU5"), ordered=TRUE),
           data=MasterData,
           xlab="Selection Operator",
           outline=FALSE,
           maxstripline=0.0,
           ylab="Number of Generations",
           main=title,
           #names=c("ROUE","ROU","ROUL","TRU60","TRU50","TRU40","TOU2","TOU3","TOU4","TOU5"),
           cex=.7,
           names=c("RE","R","RL","R6","R5","R4","T2","T3","T4","T5"),
           col="grey75")
  
  #histolegend <- "Generations"
  #par(xpd=TRUE)
  #par(usr=c(0,1,0,1))
  #legend(x=.7, y=1.1, histolegend, bty="n", 
  #	 horiz=TRUE, fill="grey75")
  
  SaveGraphicBeanWeb(paste(datapath,"graphs/web/BEANPLOT_GENS_",
                       filesuffix,sep=""))
  SaveGraphicBeanPaper(paste(datapath,"graphs/paper/BEANPLOT_GENS_",
                         filesuffix,".eps",sep=""))
  
}
