#!/usr/bin/python

'''
Created on May 26, 2013

@author: roboxue
'''
import sys
from datetime import datetime
from collections import defaultdict
current_date = None
total_avg50 = 0
total_avg100 = 0
total_avg200 = 0
for line in sys.stdin:
    # trim it
    line = line.strip()
    # split it
    datestring, avg50, avg100, avg200, count = line.split('\t')
    try:
        date=datetime.strptime(datestring.strip(), '%Y-%m-%d')
    except:
        print datestring
    if current_date == (date.year,date.month):
        total_avg50 += int(avg50)
        total_avg100 += int(avg100)
        total_avg200 += int(avg200)
        total_count += int(count)
    else:
        if current_date:
            print '%s\t%s\t%s\t%s\t%s\t%s' % (current_date[0],current_date[1], 
                                                      total_avg50, 
                                                      total_avg100,
                                                      total_avg200, total_count)
        total_avg50=int(avg50)
        total_avg100 = int(avg100)
        total_avg200 = int(avg200)
        total_count = int(count)
        current_date=(date.year,date.month)
        
if current_date==(date.year,date.month):
            print '%s\t%s\t%s\t%s\t%s\t%s' % (current_date[0],current_date[1], 
                                                      total_avg50, 
                                                      total_avg100,
                                                      total_avg200, total_count)
    
