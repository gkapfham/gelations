# file:        ReadBestResults.R
# author:      Alexander Conrad
# date:        12 February 2009

## This file contains the necessary functions for reading the
## results
## from the prioritization experiment for best configuration.


# datafile locations:
SynDatafilePathBest <- paste(Home,"results/syn_best/",sep="")
RealDatafilePathBest <- paste(Home,"results/real_best/",sep="")
RealDatafilePathBestTru <- paste(Home,"results/real_tou/",sep="")
RealDatafilePathBestTou <- paste(Home,"results/real_tru/",sep="")
RealDatafilePathBT <- paste(Home,"results/btm/",sep="")

# prefix containing results of execution for best configuration
BestConfig <- "BC"
BestConfigAD <- "AD"

ReadAllBestData <- function() {

  MasterData <- read.table(paste(RealDatafilePathBest,BestConfig,
                                 "Results.dat",sep=""),header=T)
  
  MasterData <- subset(MasterData, dataset != noquote("SR"))
  
  # debug
  print(paste(RealDatafilePathBest,BestConfig,"Results.dat",
              sep=""))

  return(MasterData)

}

ReadAllBestDataAD <- function() {

  MasterData <- read.table(paste(RealDatafilePathBest,
                                 BestConfigAD,"Results.dat",
                                 sep=""),header=T)

  # shouldn't be needed anymore
  #MasterData <- subset(MasterData, dataset != noquote("SR"))
  
  # debug
  print(paste(RealDatafilePathBest,BestConfigAD,"Results.dat",
              sep=""))

  return(MasterData)

}

ReadAllBestDataADTru <- function() {

  MasterData <- read.table(paste(RealDatafilePathBestTru,
                                 BestConfigAD,"Results.dat",
                                 sep=""),header=T)

  # shouldn't be needed anymore
  #MasterData <- subset(MasterData, dataset != noquote("SR"))
  
  # debug
  print(paste(RealDatafilePathBestTru,BestConfigAD,"Results.dat",
              sep=""))

  return(MasterData)

}

ReadAllBestDataBT <- function() {

  MasterData <- read.table(paste(RealDatafilePathBT,
                                 BestConfigAD,"Results.dat",
                                 sep=""),header=T)

  # shouldn't be needed anymore
  #MasterData <- subset(MasterData, dataset != noquote("SR"))
  
  # debug
  print(paste(RealDatafilePathBT,BestConfigAD,"Results.dat",
              sep=""))

  return(MasterData)

}

ReadAllBestDataADTou <- function() {

  MasterData <- read.table(paste(RealDatafilePathBestTou,
                                 BestConfigAD,"Results.dat",
                                 sep=""),header=T)

  # shouldn't be needed anymore
  #MasterData <- subset(MasterData, dataset != noquote("SR"))
  
  # debug
  print(paste(RealDatafilePathBestTou,BestConfigAD,"Results.dat",
              sep=""))

  return(MasterData)

}


# stores all data from best config, so that it doesn't have to be
# re-read for
# each graph. NOTE: This variable, once assigned, should never be
# modified
# except by ReloadAnalyzePrioritizations() or by re-sourcing the
# class!
BestData <- ReadAllBestData()
BestDataAD <- ReadAllBestDataAD()
BestDataADTru <- ReadAllBestDataADTru()
BestDataADTou <- ReadAllBestDataADTou()
BestDataBT <- ReadAllBestDataBT()
