#!/usr/bin/python

'''
Created on May 26, 2013

@author: roboxue
'''
import sys
import csv
for line in sys.stdin:
    #trim it
    line=line.strip()
    #split it
    for data in csv.reader([line]):
        avg50=1 if data[5]>data[2] else 0
        avg100=1 if data[5]>data[3] else 0
        avg200=1 if data[5]>data[4] else 0
        print '%s\t%s\t%s\t%s\t%s' % (data[1],avg50,avg100,avg200,1)