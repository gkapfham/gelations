# file:		MakeLatticePlots.R
# author:	Alexander Conrad
# date:		4 August 2008

## This script generates all desired lattice plots.

source("AnalyzePrioritizations.R")

# crossover ops, focus on ce

Dataset=SubsetMutationOperator(SubsetMutationRate(SubsetChildDensity(SubsetFitnessTransform(SubsetPopSize(RealData,150),"WIN"),1.0),0.1),"ISM")

MakeLatticePlotCrossoverOperatorRS(Dataset, 
    title="Crossover Operators: CE Focused Real-World Data Set", 
    filesuffix="CRSOP_MUTOP=ISM_MUTRT=0.1_CHLDR=1.0_FITOP=WIN_POPSZ=150",
    datatype=1, color=FALSE)

# crossover ops, focus on time

Dataset=SubsetMutationOperator(SubsetMutationRate(SubsetChildDensity(SubsetFitnessTransform(SubsetPopSize(RealData,50),"EXP"),0.6),0.1),"ISM")

MakeLatticePlotCrossoverOperatorRS(Dataset, 
    title="Crossover Operators: Execution Time Focused Real-World Data Set", 
    filesuffix="CRSOP_MUTOP=ISM_MUTRT=0.1_CHLDR=0.6_FITOP=EXP_POPSZ=50",
    datatype=1, color=FALSE)

# selection ops, focus on ce

Dataset=SubsetMutationOperator(SubsetMutationRate(SubsetChildDensity(SubsetFitnessTransform(SubsetPopSize(RealData,150),"WIN"),1.0),0.1),"ISM")

MakeLatticePlotSelectionMethodRS(Dataset, 
    title="Selection Operators: CE Focused Real-World Data Set", 
    filesuffix="SELOP_MUTOP=ISM_MUTRT=0.1_CHLDR=1.0_FITOP=WIN_POPSZ=150",
    datatype=1, color=FALSE)

# selection ops, focus on time

Dataset=SubsetMutationOperator(SubsetMutationRate(SubsetChildDensity(SubsetFitnessTransform(SubsetPopSize(RealData,50),"EXP"),0.6),0.1),"ISM")

MakeLatticePlotSelectionMethodRS(Dataset, 
    title="Selection Operators: Execution Time Focused Real-World Data Set", 
    filesuffix="SELOP_MUTOP=ISM_MUTRT=0.1_CHLDR=0.6_FITOP=EXP_POPSZ=50",
    datatype=1, color=FALSE)

# max stag, focus on ce

Dataset=SubsetMutationOperator(SubsetMutationRate(SubsetChildDensity(SubsetFitnessTransform(SubsetPopSize(RealData,150),"WIN"),1.0),0.1),"ISM")

MakeLatticePlotMaxStagnancyRS(Dataset, 
    title="Maximum Stagnancies: CE Focused Real-World Data Set", 
    filesuffix="MSTAG_MUTOP=ISM_MUTRT=0.1_CHLDR=1.0_FITOP=WIN_POPSZ=150",
    datatype=1, color=FALSE)

# max stag, focus on runtime

Dataset=SubsetMutationOperator(SubsetMutationRate(SubsetChildDensity(SubsetFitnessTransform(SubsetPopSize(RealData,50),"EXP"),0.6),0.1),"ISM")

MakeLatticePlotMaxStagnancyRS(Dataset, 
    title="Maximum Stagnancies: Execution Time Focused Real-World Data Set", 
    filesuffix="MSTAG_MUTOP=ISM_MUTRT=0.1_CHLDR=0.6_FITOP=EXP_POPSZ=50",
    datatype=1, color=FALSE)