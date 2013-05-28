#!/bin/bash
#Set executable
chmod 764 *.py
chmod 764 *.sh

if [ $1 ];
	then workpath=$1
	else workpath=""
fi
echo $workpath
if [ -f $workpath/NASDAQ_full.csv ];
	then
		echo 'NASDAQ_full.csv exists, skip combining step';
	else
		echo 'Remove headers and combine seperate file into one file';
		cat $workpath/NASDAQ/NASDAQ_daily_prices*.csv | grep -v "exchange,stock_symbol,date,stock_price_open,stock_price_high,stock_price_low,stock_price_close,stock_volume,stock_price_adj_close" > $workpath/NASDAQ_full.csv;
fi

if [ -f $workpath/average.csv ]
	then
		echo 'average.csv exists, skip extracting step'
	else
		echo 'Extract useful columns'
		awk -F"," '{ print $2","$3","$9 }' $workpath/NASDAQ_full.csv > $workpath/average.csv
fi

if [ -f $workpath/StockWithMovingAverage.csv ]
	then
		echo 'MovingAverage.py exists, skip calculation step'
	else
		echo 'calculate moving average'
		python $workpath/MovingAverage.py
fi

if [ ! -f $workpath/StockWithMovingAverage.csv ]
	then
		echo 'StockWithMovingAverage.csv not exist, cannot perform hadoop calculation'
	else
		echo "create hadoop folder 'Lab6'"
		hadoop dfs -mkdir Lab6
		hadoop dfs -rmr Lab6-output
		echo 'copy file into folder'
		hadoop dfs -copyFromLocal StockWithMovingAverage.csv Lab6
		echo 'map,combine,reduce'
		hadoop jar $HADOOP_HOME/contrib/streaming/hadoop-*streaming*.jar \
		-file $workpath/StockPriceMapper.py	-mapper $workpath/StockPriceMapper.py \
		-file $workpath/StockPriceCombiner.py	-combiner $workpath/StockPriceCombiner.py \
		-file $workpath/StockPriceReducer.py	-reducer $workpath/StockPriceReducer.py \
		-input Lab6/StockWithMovingAverage.csv	-output Lab6-output
		# cat $workpath/StockWithMovingAverage.csv | $workpath/StockPriceMapper.py | $workpath/StockPriceCombiner.py | sort -k1,1 | $workpath/StockPriceReducer.py > $workpath/reducer-output.txt
fi


