#!/usr/bin/python

'''
Created on May 26, 2013

@author: roboxue
'''
import sys
from collections import defaultdict
dict_avg50=defaultdict(int)
dict_avg100=defaultdict(int)
dict_avg200=defaultdict(int)
dict_count=defaultdict(int)
for line in sys.stdin:
    #trim it
    line=line.strip()
    #split it
    date,avg50,avg100,avg200,count=line.split('\t')
    dict_avg50[date]+=int(avg50)
    dict_avg100[date]+=int(avg100)
    dict_avg200[date]+=int(avg200)
    dict_count[date]+=1

for date in dict_avg50.keys():
    print '%s\t%s\t%s\t%s\t%s' % (date, dict_avg50[date], dict_avg100[date], dict_avg200[date],dict_count[date])
