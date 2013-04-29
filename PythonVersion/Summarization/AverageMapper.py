#!/usr/bin/python
'''
Created on 2013-4-24

@author: roboxue

Output the original airport and the delay of the flight
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
        #Origin Airport is the 7th
        #Delay is the 21th
            print '%s\t%s\t%s' % (data[6],data[20],1)
