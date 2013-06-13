java BenchFMap iliad.txt 300 300 | grep -i -E 'Creating|Sorting|Extracting' | 
awk '
    BEGIN { 
        data[0]="HashMap"; 
        data[1]="TreeMap"; 
        data[2]="FMap" 
    }  
    {
        if(sum%3 == 0) {
            value=$(NF-1); 
            print $1 
        } 
        if($(NF-1) != 0) {
            print data[sum%3]"\t\t"$(NF-1)/value
        } else { 
            print data[sum%3]"\t\t"1 
        }  
        sum+=1
    }'

