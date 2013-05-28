#!/usr/bin/python

'''
Created on May 26, 2013

@author: roboxue
'''
import sys
from datetime import datetime
from collections import defaultdict
dict_avg50=defaultdict(int)
dict_avg100=defaultdict(int)
dict_avg200=defaultdict(int)
dict_count=defaultdict(int)
for line in sys.stdin:
    #trim it
    line=line.strip()
    #split it
    datestring,avg50,avg100,avg200,count=line.split('\t')
    date=datetime.strptime(datestring.strip(), '%Y-%m-%d')
    dict_avg50[(date.year,date.month)]+=int(avg50)
    dict_avg100[(date.year,date.month)]+=int(avg100)
    dict_avg200[(date.year,date.month)]+=int(avg200)
    dict_count[(date.year,date.month)]+=int(count)

for datetuple in dict_avg50.keys():
    print '%s\t%s\t%s\t%s\t%s\t%s' % (datetuple[0],datetuple[1], dict_avg50[datetuple], dict_avg100[datetuple], dict_avg200[datetuple],dict_count[datetuple])
    
