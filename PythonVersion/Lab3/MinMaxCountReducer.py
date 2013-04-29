#!/usr/bin/python
'''
Created on 2013-4-24

@author: roboxue
'''
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
    
    #Because hadoop sort it...
    #We can modify the current working record
    if current_month==month:
        current_min,current_max,current_count=min(delay,current_min),max(delay,current_max),current_count+1
    #or start a new month record and output current working record        
    else:
        if current_month:
            print '%s\t%s\t%s\t%s' % (current_month, current_min, current_max, current_count)
        current_min,current_max,current_count=delay,delay,1
        current_month=month
#out put the last record
if current_month==month:
    print '%s\t%s\t%s\t%s' % (current_month, current_min, current_max, current_count)
