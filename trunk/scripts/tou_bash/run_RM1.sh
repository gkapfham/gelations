#!/bin/bash
# note: /home/cs2/conrada/Catterwampus/bin/ must be in the classpath!

#user_path="/home/conrada/"
#user_path_labs="/home/conrada/"
user_path_labs="/home/cs2/conrada/"

data_path="${user_path_labs}Catterwampus/data/"
real_data_path="${data_path}real/"
syn_data_path="${data_path}syn/"

config_path="${user_path_labs}Catterwampus/configs/"
output_path="${user_path_labs}Catterwampus/results/"
syn_output_path="${output_path}syn/"
real_output_path="${output_path}real_tou/"

#syn_types=( "time-" "matrix-" "seed-" "results-" )
#syn_bals=( "r" "c" )
#syn_tests=( "s" "m" "l" )
#syn_reqs=( "s" "m" "l" )
#syn_pts=( "s" "m" "l" )

#syn_reps=( "1" "2" "3" "4" "5" "6" "7" "8" "9" "10" )
#do only the first matrix for each configuration for now
syn_reps=( "1" )

#real_configs=( "DS" "GB" "JD" "LF" "RM" "RP" "SK" "SR" "TM" )
real_configs=( "RM" )
real_types=( "Time.dat" "Coverage.dat" "Seed.dat" "Results.dat" )

#debug:
#echo $user_path
#echo $syn_data_path
#echo $real_data_path
#echo $config_path
#echo $output_path
#echo $syn_output_path
#echo $real_output_path
#echo ${syn_types[0]} ${syn_types[1]} ${syn_types[2]} 

#data_formats=( "0" "1" )
#do only real configs for now
data_formats=( "1" )

mut_operators=( "0" "1" "2" "3" "4" "5" )

cross_operators=( "0" "1" "2" "3" "4" "5" "6" )

select_operators=( "3" "4" "5" )

fit_transforms=( "1" )

#.67 from configuring_an_evolutionary_tool_...
mut_rates=("0.01" "0.33" "0.67")

#child_reps=( "0.6" "0.8" "1.0" )
child_reps=( "0.5" "0.75" "1.0" )

#pop_sizes=("50" "100" "150")
pop_sizes=("75" "150" "225")

max_times=( "10000000" )

max_fitnesses=( "1.0" )

#max_stags=("10" "25" "40")
max_stags=("20" "30" "40")

metrics=( "0" )

#only 1 rep due to time
reps=( "1" )

#echo "?"

for rep in ${reps[*]}
do

for metric in ${metrics[*]}
do

for max_stag in ${max_stags[*]}
do

for max_fitness in ${max_fitnesses[*]}
do

for max_time in ${max_times[*]}
do

for pop_size in ${pop_sizes[*]}
do

for child_rep in ${child_reps[*]}
do

for mut_rate in ${mut_rates[*]}
do

for fit_transform in ${fit_transforms[*]}
do

for select_operator in ${select_operators[*]}
do

for cross_operator in ${cross_operators[*]}
do

for mut_operator in ${mut_operators[*]}
do

for data_format in ${data_formats[*]}
do

for real_config in ${real_configs[*]}
do


echo "java gelations.RunPrioritizer $data_format $mut_operator $cross_operator $select_operator $fit_transform $mut_rate $child_rep $pop_size $max_time $max_fitness $max_stag $metric $real_data_path$real_config${real_types[1]} $real_data_path$real_config${real_types[0]} $real_data_path$real_config${real_types[2]} $real_output_path$real_config${real_types[3]} $rep"

java gelations.RunPrioritizer $data_format $mut_operator $cross_operator $select_operator $fit_transform $mut_rate $child_rep $pop_size $max_time $max_fitness $max_stag $metric $real_data_path$real_config${real_types[1]} $real_data_path$real_config${real_types[0]} $real_data_path$real_config${real_types[2]} $real_output_path$real_config${real_types[3]} $rep


done

done

done

done

done

done

done

done

done

done

done

done

done

done



exit 0