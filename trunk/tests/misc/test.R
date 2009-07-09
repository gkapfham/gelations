source("AnalyzePrioritizations.R")

Dataset=SubsetMutationOperator(SubsetMutationRate(SubsetChildDensity(SubsetFitnessTransform(SubsetPopSize(RealData,50),"EXP"),0.6),0.1),"ISM")

MakeLatticePlotSelectionMethodRS(Dataset, 
    title="Selection Operators: CE Focused Real-World Data Set", 
    filesuffix="SELOP_MUTOP=ISM_MUTRT=0.1_CHLDR=1.0_FITOP=WIN_POPSZ=150",
    datatype=1, color=FALSE)