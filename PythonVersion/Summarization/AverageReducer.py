#!/usr/bin/python
'''
Created on Apr 28, 2013

@author: robouxe
'''
import sys
current_origin=None
current_sum=0
current_count=0

for line in sys.stdin:
    #trim
    line=line.strip()    
    try:
        origin, delay, count=line.split('\t')
        delay=float(delay)
        count=int(count)
    except ValueError:
        #Conversion failed
        continue
    
    #Because hadoop sort it...
    #We can modify the current working record
    if current_origin==origin:
        current_sum,current_count=current_sum+delay,current_count+count
    #or start a new month record and output current working record        
    else:
        if current_origin:
            print '%s\t%s\t%s' % (current_origin, current_sum, current_count)
        current_sum,current_count=delay,count
        current_origin=origin
#out put the last record
if current_origin==origin:
    print '%s\t%.2f' % (current_origin, current_sum/current_count)
    
