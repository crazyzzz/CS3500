java BenchFMap iliad.txt 300 300 
| grep -i -E 'Creating|Sorting|Extracting' | 
awk '
    BEGIN { 
        data[0]="HashMap"; 
        data[1]="TreeMap"; 
        data[2]="FMap" 
	sum = 0;
	print "\t\t"data[0]"\t\t"data[1]"\t\t"data[2]
    } {
        value[sum]=$(NF-1); 
	if (sum%3 == 0) {
		if (sum > 0)
			print "\t\t"value[sum-3]/(0.0+value[sum-2])"\t" 1 "\t"value[sum-1]/(0.0+value[sum-2])
		printf $1;
	}
        sum+=1
    } END {
	if(value[sum-1]) 
		print "\t\t"value[sum-3]/(0.0+value[sum-2])"\t" 1 "\t"value[sum-1]/(0.0+value[sum-2])
	else 
		print "\t\t1\t1\t1"
	
    } '

