'''
Created on Apr 20, 2013

@author: roboxue
'''
import csv
import numpy as np
wanted = ["\"Year\"", "\"Quarter\"",
"\"Month\"", "\"DayofMonth\"", "\"DayOfWeek\"",
"\"UniqueCarrier\"", "\"Origin\"", "\"OriginCityName\"",
"\"OriginState\"", "\"Dest\"", "\"DestCityName\"",
"\"DestState\"", "\"CRSDepTime\"", "\"DepTime\"",
"\"DepDelay\"", "\"DepDelayMinutes\"", "\"DepDel15\"",
"\"DepartureDelayGroups\"", "\"TaxiOut\"", "\"TaxiIn\"",
"\"ArrDelay\"", "\"ArrDelayMinutes\"", "\"ArrDel15\"",
"\"Cancelled\"", "\"ActualElapsedTime\"", "\"AirTime\"",
"\"Distance\""]
with open('AllFlights.txt', 'wb') as csvOut:
    for month in range(12):
        csvwriter = csv.writer(csvOut,quoting=csv.QUOTE_MINIMAL)
        with open('../../FlightData/On_Time_On_Time_Performance_2012_' + str(month + 1) + '.csv', 'rb') as csvIn:
            csvreader = csv.reader(csvIn)
            wantedIndex = np.zeros(wanted.__len__(), dtype="Int32")
            titleRow = np.array(csvIn.next().split(','))
            for i in range(wanted.__len__()):
                wantedIndex[i] = np.where(titleRow == wanted[i])[0][0]
            count = 0
            for row in csvreader:
                csvwriter.writerow(np.array(row, dtype="str")[wantedIndex])

