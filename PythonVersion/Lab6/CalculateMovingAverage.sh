#!/bin/bash
echo 'remove headers and combine seperate file into one file'
awk 'NR>1' NASDAQ_daily_prices*.csv >> NASDAQ_full.csv

echo 'remove useless columns'
awk -F"," '{ print $2,"\t",$3,"\t",$9 }' NASDAQ_full.csv > average.csv

echo 'calculate moving average'
sudo python ~/GitHub/BigData/PythonVersion/Lab6/MovingAverage.py

echo 'map,combine,reduce'
cat ~/GitHub/BigData/PythonVersion/Lab6/StockWithMovingAverage.csv | ~/GitHub/BigData/PythonVersion/Lab6/StockPriceMapper.py | ~/GitHub/BigData/PythonVersion/Lab6/StockPriceCombiner.py | sort -k1,1 | ~/GitHub/BigData/PythonVersion/Lab6/StockPriceReducer.py > reducer-output.txt

vi reducer-output.txt


