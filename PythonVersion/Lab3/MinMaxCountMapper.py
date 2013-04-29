#!/usr/bin/python

'''
Created on 2013-4-24

@author: roboxue
'''
import sys
import csv
for line in sys.stdin:
    #trim it
    line=line.strip()
    #split it
    for data in csv.reader([line]):
        if data.__len__()==27:
        #output month and delaytime
            print '%s\t%s' % (data[2],data[20])
