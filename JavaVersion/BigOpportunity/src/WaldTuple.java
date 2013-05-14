import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;


import org.apache.hadoop.io.Writable;


public class WaldTuple implements Writable {
	private String StockName = "IBM";
	private int Number = 0;
	private int Indicator = 0;


	public void readFields(DataInput in) throws IOException {
		setStockName(in.readLine());
		setNumber(in.readInt());
		setIndicator(in.readInt());
	}


	public void write(DataOutput out) throws IOException {
		out.writeBytes(getStockName());
		out.writeInt(getNumber());
		out.writeInt(getIndicator());
	}


	public String toString() {
		return getStockName() + "\t" + getNumber() + "\t" + getIndicator();
	}


	public String getStockName() {
		return StockName;
	}


	public void setStockName(String stockName) {
		StockName = stockName;
	}


	public int getNumber() {
		return Number;
	}


	public void setNumber(int number) {
		Number = number;
	}


	public int getIndicator() {
		return Indicator;
	}


	public void setIndicator(int indicator) {
		Indicator = indicator;
	}
}

