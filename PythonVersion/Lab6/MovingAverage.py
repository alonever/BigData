'''
Created on May 26, 2013

@author: roboxue
'''
import csv
import pandas as pd
import numpy as np

data = pd.read_csv('average.csv', header=0,
                 # parse_dates=[1], 
                 names=['stock_symbol', 'date', 'stock_price_adj_close'])
# stockNames=data.stock_symbol.unique()
grouped = data.groupby("stock_symbol")
with open('StockWithMovingAverage.csv', 'wb') as csvfile:
    outfile = csv.writer(csvfile, delimiter=',')
    for name, group in grouped:
        price_series = group.sort_index(by='date', ascending=True)
        if(len(price_series) > 200):
            print name
            sum50 = sum(price_series['stock_price_adj_close'][150:199])
            sum100 = sum(price_series['stock_price_adj_close'][100:199])
            sum200 = sum(price_series['stock_price_adj_close'][0:199])
            
            for i in range(200, len(price_series)):
                price = price_series['stock_price_adj_close'].irow(i)
                outfile.writerow([name, price_series['date'].irow(i), round(sum50 / 50, 3), round(sum100 / 100, 3), round(sum200 / 200, 3), price])
                sum50 = sum50 + price - price_series['stock_price_adj_close'].irow(i - 50)
                sum100 = sum100 + price - price_series['stock_price_adj_close'].irow(i - 100)
                sum200 = sum200 + price - price_series['stock_price_adj_close'].irow(i - 200)



def main():
    pass


if __name__ == '__main__':
    main()
    
    
