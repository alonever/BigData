Lab2
==================
Write a java program that creates one big file from these 12 separate files.  
Include the following columns only (next page), and the final output file should have no titles 
(you will want to maintain them externally).





Year			Year
Quarter			Quarter (1-4)
Month			Month
DayofMonth		Day of Month
DayOfWeek		Day of Week
UniqueCarrier		Unique Carrier Code. When the same code has been used by multiple carriers, a numeric suffix is used for earlier users, for example, PA, PA(1), PA(2). Use this field for analysis across a range of years.
Origin			Origin Airport
OriginCityName		Origin Airport, City Name
OriginState		Origin Airport, State Code
Dest			Destination Airport
DestCityName		Destination Airport, City Name
DestState		Destination Airport, State Code
CRSDepTime		CRS Departure Time (local time: hhmm)
DepTime			Actual Departure Time (local time: hhmm)
DepDelay		Difference in minutes between scheduled and actual departure time. Early departures show negative numbers.
DepDelayMinutes		Difference in minutes between scheduled and actual departure time. Early departures set to 0.
DepDel15		Departure Delay Indicator, 15 Minutes or More (1=Yes)
DepartureDelayGroups	Departure Delay intervals, every (15 minutes from <-15 to >180)
TaxiOut			Taxi Out Time, in Minutes
TaxiIn	Taxi In Time, in Minutes
ArrDelay	Difference in minutes between scheduled and actual arrival time. Early arrivals show negative numbers.
ArrDelayMinutes	Difference in minutes between scheduled and actual arrival time. Early arrivals set to 0.
ArrDel15	Arrival Delay Indicator, 15 Minutes or More (1=Yes)
Cancelled	Cancelled Flight Indicator (1=Yes)
ActualElapsedTime	Elapsed Time of Flight, in Minutes
AirTime	Flight Time, in Minutes
Distance	Distance between airports (miles)
	
	
