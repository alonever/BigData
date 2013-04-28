#!/usr/bin/python
'''
Created on 2013-4-24

@author: roboxue
'''
from operator import itemgetter
import sys

current_month=None
current_min=float('Inf')
current_max=-float('Inf')
current_count=0
month=None

for line in sys.stdin:
    #trim
    line=line.strip()    
    try:
        month, delay=line.split('\t')
    delay=float(delay)
    except ValueError:
        #Conversion failed
        continue
    
    #because hadoop sort it...
    if current_month==month:
        current_count+=1
        current_max=max(delay,current_max)
        current_min=min(delay,current_min)
    else:
        if current_month:
            print '%s\t%s\t%s\t%s' % (current_month, current_min, current_max, current_count)
        current_count=1
        current_month=month
        current_min=delay
        current_max=delay
#end of for    
#out put the last month
if current_month==month:
    print '%s\t%s\t%s\t%s' % (current_month, current_min, current_max, current_count)
