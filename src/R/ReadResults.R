# file:        ReadResults.R
# author:      Alexander Conrad
# date:        24 October 2008

## This file contains the necessary functions for reading the
## results
## from the prioritization experiments.

# home path: (location of Catterwampus project)
#Home <- "/home/conrada/Catterwampus/"

# datafile locations:
SynDatafilePath <- paste(Home,"results/syn/",sep="")
RealDatafilePath <- paste(Home,"results/real/",sep="")
RealDatafilePathAdam <- paste(Home,"results/real_adam/",sep="") 
RealDatafilePathTou <- paste(Home,"results/real_tou/",sep="")
RealDatafilePathTru <- paste(Home,"results/real_tru/",sep="")

# real-world datafile identifiers
#RealConfigs <- c("DS","GB","JD","LF","RM","RP","SK","SR","TM")
RealConfigs <- c("DS","GB","JD","LF","RM","RP","SK","TM")

# synthetic datafile identifiers
SynBals <- c("r","c")
SynTests <- c("s","m","l")
SynReqs <- c("s","m","l")
SynPts <- c("s","m","l")
SynReps <- c("1")

# read in all data from the real-world-based datafiles
ReadAllRealData <- function() {
  
  MasterData <- read.table(paste(RealDatafilePath,RealConfigs
                                 [length(RealConfigs)],
                                 "ResultsMerge.dat",sep=""),header=T)
  
  # debug
  print(paste(RealDatafilePath,RealConfigs[length(RealConfigs)],
              "ResultsMerge.dat",sep=""))

  #print(MasterData[,2])

  for(i in 1:(length(RealConfigs)-1)) {
    
    MasterData <- rbind(MasterData, read.table
                        (paste(RealDatafilePath,RealConfigs[i],
                               "ResultsMerge.dat",sep=""),header=T))

    # debug
    #print(MasterData[,2])
    print(paste(RealDatafilePath,RealConfigs[i],"ResultsMerge.dat",
                sep=""));
    
  }
  
  return(MasterData)
  
}

# unless otherwise noted, data used in study is full, not adam's
ReadAllRealDataAdam <- function() {
  
  MasterData <- read.table(paste(RealDatafilePathAdam,RealConfigs
                                 [length(RealConfigs)],
                                 "Results.dat",sep=""),header=T)
  
  # debug
  print(paste(RealDatafilePathAdam,RealConfigs[length(RealConfigs)],
              "Results.dat",sep=""))

  #print(MasterData[,2])

  for(i in 1:(length(RealConfigs)-1)) {
    
    MasterData <- rbind(MasterData, read.table
                        (paste(RealDatafilePathAdam,RealConfigs[i],
                               "Results.dat",sep=""),header=T))

    # debug
    #print(MasterData[,2])
    print(paste(RealDatafilePathAdam,RealConfigs[i],"Results.dat",
                sep=""));
    
  }
  
  return(MasterData)
  
}

ReadAllRealDataTou <- function() {
  
  MasterData <- read.table(paste(RealDatafilePathTou,RealConfigs
                                 [length(RealConfigs)],
                                 "Results.dat",sep=""),header=T)
  
  # debug
  print(paste(RealDatafilePathTou,RealConfigs[length(RealConfigs)],
              "Results.dat",sep=""))

  #print(MasterData[,2])

  for(i in 1:(length(RealConfigs)-1)) {
    
    MasterData <- rbind(MasterData, read.table
                        (paste(RealDatafilePathTou,RealConfigs[i],
                               "Results.dat",sep=""),header=T))

    # debug
    #print(MasterData[,2])
    print(paste(RealDatafilePathTou,RealConfigs[i],"Results.dat",
                sep=""));
    
  }
  
  return(MasterData)
  
}

ReadAllRealDataTru <- function() {
  
  MasterData <- read.table(paste(RealDatafilePathTru,RealConfigs
                                 [length(RealConfigs)],
                                 "Results.dat",sep=""),header=T)
  
  # debug
  print(paste(RealDatafilePathTru,RealConfigs[length(RealConfigs)],
              "Results.dat",sep=""))

  #print(MasterData[,2])

  for(i in 1:(length(RealConfigs)-1)) {
    
    MasterData <- rbind(MasterData, read.table
                        (paste(RealDatafilePathTru,RealConfigs[i],
                               "Results.dat",sep=""),header=T))

    # debug
    #print(MasterData[,2])
    print(paste(RealDatafilePathTru,RealConfigs[i],"Results.dat",
                sep=""));
    
  }
  
  return(MasterData)
  
}


# read in all data from the synthetic-based datafiles
ReadAllSynData <- function() {
  
  
  
}

ReadTemplate <- function() {
  
  MasterData <- read.table(paste(RealDatafilePath,"Template.dat",sep=""),header=T)
  
  # debug
  print(paste(RealDatafilePath,"Template.dat",sep=""))

  #print(MasterData[,2])
  
  return(MasterData)
  
}

AdHocTemplate <- ReadTemplate()

# stores all real-world data, so that it doesn't have to be
# re-read for each 
# graph. NOTE: This variable, once assigned, should never be
# modified except 
# by ReloadAnalyzePrioritizations() or by re-sourcing the class!
RealData <- ReadAllRealData()

RealDataAdam <- ReadAllRealDataAdam()
RealDataTou <- ReadAllRealDataTou()
RealDataTru <- ReadAllRealDataTru()


# stores all synthetic data, so that it doesn't have to be
# re-read for each 
# graph. NOTE: This variable, once assigned, should never be
# modified except 
# by ReloadAnalyzePrioritizations() or by re-sourcing the class!
#SynData <- ReadAllSynData()
